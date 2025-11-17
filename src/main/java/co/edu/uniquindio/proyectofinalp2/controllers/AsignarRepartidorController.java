package co.edu.uniquindio.proyectofinalp2.controllers;

import co.edu.uniquindio.proyectofinalp2.models.Envio;
import co.edu.uniquindio.proyectofinalp2.models.EstadoEnvio;
import co.edu.uniquindio.proyectofinalp2.models.EstadoPago;
import co.edu.uniquindio.proyectofinalp2.models.User;
import co.edu.uniquindio.proyectofinalp2.repositories.Database;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.stream.Collectors;

public class AsignarRepartidorController {

    @FXML private TableView<Envio> tablaEnvios;
    @FXML private TableColumn<Envio, String> colId, colOrigen, colDestino;
    @FXML private TableColumn<Envio, Double> colCosto;

    @FXML private ComboBox<User> comboRepartidores;
    @FXML private Label lblMensaje;

    private final Database db = Database.getInstancia();

    @FXML
    private void initialize() {

        colId.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getId()));

        colOrigen.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getOrigen().getCalle() + " - " +
                                data.getValue().getOrigen().getCiudad()));

        colDestino.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getDestino().getCalle() + " - " +
                                data.getValue().getDestino().getCiudad()));

        colCosto.setCellValueFactory(data ->
                new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getPago()));

        cargarEnviosPendientes();
        cargarRepartidores();
    }

    /**
     *  SOLO muestra envíos:
     *  Estado = PENDIENTE
     *  estadoPago = PAGADO o CONTRA_ENTREGA
     */
    private void cargarEnviosPendientes() {
        var pendientes = db.getListaEnvios()
                .stream()
                .filter(e -> e.getEstado() == EstadoEnvio.PENDIENTE)
                .filter(e -> e.getEstadoPago() == EstadoPago.PAGADO || e.getEstadoPago() == EstadoPago.CONTRA_ENTREGA)
                .collect(Collectors.toList());

        tablaEnvios.setItems(FXCollections.observableArrayList(pendientes));
    }

    private void cargarRepartidores() {
        var repartidores = db.listarUsuarios()
                .stream()
                .filter(u -> "REPARTIDOR".equals(u.getRol()))
                .filter(u -> "ACTIVO".equals(u.getEstado()))
                .collect(Collectors.toList());

        comboRepartidores.setItems(FXCollections.observableArrayList(repartidores));
    }

    @FXML
    private void asignarRepartidor() {

        Envio envioSeleccionado = tablaEnvios.getSelectionModel().getSelectedItem();
        User repartidorSeleccionado = comboRepartidores.getValue();

        if (envioSeleccionado == null) {
            mostrarMensaje("Seleccione un envío.", "red");
            return;
        }

        if (repartidorSeleccionado == null) {
            mostrarMensaje("Seleccione un repartidor.", "red");
            return;
        }

        // VALIDACIÓN DEL PAGO
        if (envioSeleccionado.getEstadoPago() != EstadoPago.PAGADO && envioSeleccionado.getEstadoPago() != EstadoPago.CONTRA_ENTREGA) {
            mostrarMensaje("No se puede asignar porque el envío no está pagado.", "red");
            return;
        }

        envioSeleccionado.setRepartidor(repartidorSeleccionado);
        envioSeleccionado.setEstado(EstadoEnvio.EN_CAMINO);

        mostrarMensaje("Repartidor asignado correctamente.", "green");

        cargarEnviosPendientes();
    }

    private void mostrarMensaje(String mensaje, String color) {
        lblMensaje.setText(mensaje);
        lblMensaje.setStyle("-fx-text-fill: " + color + ";");
    }

    @FXML
    private void volver() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/co/edu/uniquindio/proyectofinalp2/views/MainAdminView.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) tablaEnvios.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Panel del Administrador");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            mostrarMensaje("Error al regresar al menú.", "red");
        }
    }
}
