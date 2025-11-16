package co.edu.uniquindio.proyectofinalp2.controllers;

import co.edu.uniquindio.proyectofinalp2.models.*;
import co.edu.uniquindio.proyectofinalp2.repositories.Database;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.File;
import java.io.IOException;

public class ComprobantesController {

    @FXML private TableView<Envio> tablaComprobantes;
    @FXML private TableColumn<Envio, String> colId;
    @FXML private TableColumn<Envio, String> colOrigen;
    @FXML private TableColumn<Envio, String> colDestino;
    @FXML private TableColumn<Envio, Double> colMonto;
    @FXML private TableColumn<Envio, String> colMetodoPago;

    private User usuarioActual;

    public void setUsuarioActual(User usuario) {
        this.usuarioActual = usuario;
        cargarComprobantes();
    }

    private void cargarComprobantes() {

        colId.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getId()));
        colOrigen.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getOrigen().getDireccion()));
        colDestino.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getDestino().getDireccion()));
        colMonto.setCellValueFactory(cell -> new javafx.beans.property.SimpleDoubleProperty(cell.getValue().getPago()).asObject());
        colMetodoPago.setCellValueFactory(cell ->
                new javafx.beans.property.SimpleStringProperty(cell.getValue().getEstadoPago().toString())
        );

        tablaComprobantes.getItems().setAll(
                Database.getInstancia().getListaEnvios()
                        .stream()
                        .filter(e -> e.getPedido() != null &&
                                e.getPedido().getCliente().equals(usuarioActual))
                        .toList()
        );
    }

    // MÉTODO QUE EXIGE EL FXML
    @FXML
    private void descargarComprobante() {

        Envio envio = tablaComprobantes.getSelectionModel().getSelectedItem();

        if (envio == null) {
            mostrarError("Seleccione un comprobante.");
            return;
        }

        FileChooser chooser = new FileChooser();
        chooser.setTitle("Guardar comprobante PDF");
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF", "*.pdf"));

        File archivo = chooser.showSaveDialog(new Stage());

        if (archivo != null) {
            generarPDF(envio, archivo);
            mostrarInfo("Comprobante descargado correctamente.");
        }
    }

    private void generarPDF(Envio envio, File archivo) {
        try (PDDocument doc = new PDDocument()) {

            PDPage page = new PDPage(PDRectangle.A4);
            doc.addPage(page);

            try (PDPageContentStream stream = new PDPageContentStream(doc, page)) {

                stream.beginText();
                stream.setFont(PDType1Font.HELVETICA_BOLD, 18);
                stream.newLineAtOffset(50, 750);
                stream.showText("Comprobante de Pago - Envío " + envio.getId());
                stream.endText();

                stream.beginText();
                stream.setFont(PDType1Font.HELVETICA, 12);
                stream.newLineAtOffset(50, 720);
                stream.showText("Cliente: " + envio.getPedido().getCliente().getNombreCompleto());
                stream.newLineAtOffset(0, -20);
                stream.showText("Origen: " + envio.getOrigen().getDireccion());
                stream.newLineAtOffset(0, -20);
                stream.showText("Destino: " + envio.getDestino().getDireccion());
                stream.newLineAtOffset(0, -20);
                stream.showText("Monto: $" + envio.getPago());
                stream.newLineAtOffset(0, -20);
                stream.showText("Método de Pago: " + envio.getEstadoPago());
                stream.endText();
            }

            doc.save(archivo);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void volver() {
        ((Stage) tablaComprobantes.getScene().getWindow()).close();
    }

    private void mostrarError(String msg) {
        Alert a = new Alert(Alert.AlertType.ERROR, msg);
        a.show();
    }

    private void mostrarInfo(String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION, msg);
        a.show();
    }
}
