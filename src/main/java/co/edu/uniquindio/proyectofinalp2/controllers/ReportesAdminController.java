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
    private final ReportGeneratorCSV generador = new ReportGeneratorCSV();

    @FXML
    public void initialize() {
        // Configurar las columnas con las propiedades de la clase Envio
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estadoTexto"));
        colPago.setCellValueFactory(new PropertyValueFactory<>("pagoTexto"));
        colRepartidor.setCellValueFactory(new PropertyValueFactory<>("repartidorTexto"));
        colCliente.setCellValueFactory(new PropertyValueFactory<>("clienteTexto"));

        // Cargar los datos desde la base simulada
        ObservableList<Envio> lista = FXCollections.observableArrayList(db.getListaEnvios());
        tablaEnvios.setItems(lista);
    }

    @FXML
    private void generarReporteCSV() {
        generador.generarReporte(db.getListaEnvios());
        mostrarAlerta("Reporte generado correctamente (reporte_envios.csv)");
    }

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
        }
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
