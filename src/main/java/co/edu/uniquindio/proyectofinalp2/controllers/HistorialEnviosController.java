package co.edu.uniquindio.proyectofinalp2.controllers;

import co.edu.uniquindio.proyectofinalp2.models.Envio;
import co.edu.uniquindio.proyectofinalp2.repositories.Database;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class HistorialEnviosController {

    @FXML
    private TableView<Envio> tablaEnvios;

    @FXML
    private TableColumn<Envio, String> colId, colOrigen, colDestino, colEstado;

    @FXML
    private TableColumn<Envio, Double> colCosto;

    private final Database db = Database.getInstancia();

    @FXML
    private void initialize() {
        // Configurar las columnas
        colId.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getId()));
        colOrigen.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(
                data.getValue().getOrigen().getDireccion() + " - " + data.getValue().getOrigen().getCiudad()));
        colDestino.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(
                data.getValue().getDestino().getDireccion() + " - " + data.getValue().getDestino().getCiudad()));
        colEstado.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(
                data.getValue().getEstado().toString()));
        colCosto.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getCosto()));

        // Cargar todos los envíos desde la base de datos
        tablaEnvios.setItems(FXCollections.observableArrayList(db.getListaEnvios()));
    }

    @FXML
    private void cerrarVentana() {
        Stage stage = (Stage) tablaEnvios.getScene().getWindow();
        stage.close();
    }
}
