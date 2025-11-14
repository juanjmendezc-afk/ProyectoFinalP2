package co.edu.uniquindio.proyectofinalp2.controllers;

import co.edu.uniquindio.proyectofinalp2.models.Envio;
import co.edu.uniquindio.proyectofinalp2.repositories.Database;
import co.edu.uniquindio.proyectofinalp2.services.ReportGeneratorCSV;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ReportesAdminController {

    @FXML
    private TableView<Envio> tablaEnvios;

    @FXML
    private TableColumn<Envio, String> colId;

    @FXML
    private TableColumn<Envio, String> colEstado;

    @FXML
    private TableColumn<Envio, String> colPago;

    @FXML
    private TableColumn<Envio, String> colRepartidor;

    @FXML
    private TableColumn<Envio, String> colCliente;

    private final Database db = Database.getInstancia();
    private final ReportGeneratorCSV generadorCSV = new ReportGeneratorCSV();

    @FXML
    public void initialize() {

        // Asocia cada columna con su propiedad visible en el TableView
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estadoTexto"));
        colPago.setCellValueFactory(new PropertyValueFactory<>("pagoTexto"));
        colRepartidor.setCellValueFactory(new PropertyValueFactory<>("repartidorTexto"));
        colCliente.setCellValueFactory(new PropertyValueFactory<>("clienteTexto"));

        // Carga la tabla con los envíos del "Database"
        ObservableList<Envio> lista = FXCollections.observableArrayList(db.getListaEnvios());
        tablaEnvios.setItems(lista);
    }

    /**
     * Genera el reporte CSV usando la clase ReportGeneratorCSV.
     */
    @FXML
    private void generarReporteCSV() {
        try {
            // Se envían: lista de envíos + nombre del archivo
            generadorCSV.generarReporte(db.getListaEnvios(), "reporte_envios.csv");

            mostrarAlerta("Reporte CSV generado correctamente:\nreporte_envios.csv");

        } catch (Exception e) {
            mostrarAlerta("Error al generar el reporte CSV.");
            e.printStackTrace();
        }
    }

    /**
     * Regresa al panel principal del administrador.
     */
    @FXML
    private void volver() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/co/edu/uniquindio/proyectofinalp2/views/MainAdminView.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) tablaEnvios.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Panel del Administrador");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error al volver al panel del administrador.");
        }
    }

    /**
     * Muestra una alerta informativa.
     */
    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
