package co.edu.uniquindio.proyectofinalp2.controllers;

import co.edu.uniquindio.proyectofinalp2.models.Envio;
import co.edu.uniquindio.proyectofinalp2.models.User;
import co.edu.uniquindio.proyectofinalp2.repositories.Database;
import co.edu.uniquindio.proyectofinalp2.services.ReportGeneratorCSV;
import co.edu.uniquindio.proyectofinalp2.services.ReportGeneratorPDF;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class HistorialEnviosController {

    @FXML private TableView<Envio> tablaEnvios;
    @FXML private TableColumn<Envio, String> colId, colOrigen, colDestino, colEstado;
    @FXML private TableColumn<Envio, Double> colCosto;

    private final Database db = Database.getInstancia();
    private User usuarioActual;

    public void setUsuarioActual(User usuario) {
        this.usuarioActual = usuario;
        if (tablaEnvios != null) cargarEnviosDelUsuario();
    }

    @FXML
    private void initialize() {
        colId.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getId()));
        colOrigen.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(
                data.getValue().getOrigen().getCalle() + " - " + data.getValue().getOrigen().getCiudad()));
        colDestino.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(
                data.getValue().getDestino().getCalle() + " - " + data.getValue().getDestino().getCiudad()));
        colEstado.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(
                data.getValue().getEstado().toString()));
        colCosto.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getPago()));

    }

    private void cargarEnviosDelUsuario() {
        if (usuarioActual == null) return;

        var listaFiltrada = db.getListaEnvios()
                .stream()
                .filter(envio ->
                        envio.getPedido() != null &&
                                envio.getPedido().getCliente() != null &&
                                envio.getPedido().getCliente().getId().equals(usuarioActual.getId())
                ).collect(Collectors.toList());

        tablaEnvios.setItems(FXCollections.observableArrayList(listaFiltrada));
    }

    @FXML
    private void exportarPDF() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar reporte PDF");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivo PDF (*.pdf)", "*.pdf"));
        fileChooser.setInitialFileName("historial_envios.pdf");
        File archivo = fileChooser.showSaveDialog(tablaEnvios.getScene().getWindow());
        if (archivo != null) {
            ReportGeneratorPDF generador = new ReportGeneratorPDF();
            generador.generarReportePDF(new ArrayList<>(tablaEnvios.getItems()), archivo.getAbsolutePath());
            mostrarAlerta("PDF generado", "El archivo PDF se guardó correctamente.", Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    private void exportarCSV() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar reporte CSV");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivo CSV (*.csv)", "*.csv"));
        fileChooser.setInitialFileName("historial_envios.csv");
        File archivo = fileChooser.showSaveDialog(tablaEnvios.getScene().getWindow());
        if (archivo != null) {
            ReportGeneratorCSV generador = new ReportGeneratorCSV();
            generador.generarReporte(new ArrayList<>(tablaEnvios.getItems()), archivo.getAbsolutePath());
            mostrarAlerta("Reporte generado", "El archivo CSV se guardó correctamente.", Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    private void cerrarVentana() {
        Stage stage = (Stage) tablaEnvios.getScene().getWindow();
        stage.close();
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.show();
    }
}

