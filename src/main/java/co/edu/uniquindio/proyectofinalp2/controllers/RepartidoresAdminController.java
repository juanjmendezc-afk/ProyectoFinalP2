package co.edu.uniquindio.proyectofinalp2.controllers;

import co.edu.uniquindio.proyectofinalp2.models.User;
import co.edu.uniquindio.proyectofinalp2.repositories.Database;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.stream.Collectors;

public class RepartidoresAdminController {

    @FXML private TableView<User> tablaRepartidores;
    @FXML private TableColumn<User, String> colId, colNombre, colEmail, colTelefono;

    @FXML private TableView<User> tablaSolicitudes;
    @FXML private TableColumn<User, String> colSNombre, colSEmail, colSTelefono;

    @FXML private Label lblMensaje;

    private final Database db = Database.getInstancia();

    @FXML
    private void initialize() {

        // ========= TABLA DE REPARTIDORES ACTIVOS =========
        colId.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getId()));
        colNombre.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNombreCompleto()));
        colEmail.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getEmail()));
        colTelefono.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getTelefono()));

        // ========= TABLA DE SOLICITUDES =========
        colSNombre.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNombreCompleto()));
        colSEmail.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getEmail()));
        colSTelefono.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getTelefono()));

        cargarTablas();
    }

    private void cargarTablas() {

        // Repartidores activos
        var repartidoresActivos = db.listarUsuarios()
                .stream()
                .filter(u -> u.getRol().equals("REPARTIDOR") && u.getEstado().equals("ACTIVO"))
                .collect(Collectors.toList());

        // Solicitudes pendientes
        var solicitudes = db.listarSolicitudes();

        tablaRepartidores.setItems(FXCollections.observableArrayList(repartidoresActivos));
        tablaSolicitudes.setItems(FXCollections.observableArrayList(solicitudes));
    }

    @FXML
    private void aprobarSolicitud() {
        User seleccionado = tablaSolicitudes.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            mostrar("Seleccione una solicitud.", "red");
            return;
        }

        seleccionado.setEstado("ACTIVO");
        seleccionado.setRol("REPARTIDOR"); // ← CORRECCIÓN CLAVE

        db.aprobarRepartidor(seleccionado);

        mostrar("Solicitud aprobada correctamente.", "green");

        cargarTablas();
    }

    @FXML
    private void rechazarSolicitud() {
        User seleccionado = tablaSolicitudes.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            mostrar("Seleccione una solicitud.", "red");
            return;
        }

        db.rechazarRepartidor(seleccionado);
        mostrar("Solicitud rechazada.", "green");

        cargarTablas();
    }

    private void mostrar(String msg, String color) {
        lblMensaje.setText(msg);
        lblMensaje.setStyle("-fx-text-fill:" + color + ";");
    }

    @FXML
    private void volver() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/co/edu/uniquindio/proyectofinalp2/views/MainAdminView.fxml"));

            Parent root = loader.load();
            Stage stage = (Stage) tablaRepartidores.getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.setTitle("Panel del Administrador");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
