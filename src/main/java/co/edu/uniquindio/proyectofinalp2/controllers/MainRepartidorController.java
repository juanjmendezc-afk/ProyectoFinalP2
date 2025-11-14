package co.edu.uniquindio.proyectofinalp2.controllers;

import co.edu.uniquindio.proyectofinalp2.models.Envio;
import co.edu.uniquindio.proyectofinalp2.models.EstadoEnvio;
import co.edu.uniquindio.proyectofinalp2.models.User;
import co.edu.uniquindio.proyectofinalp2.repositories.Database;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class MainRepartidorController {

    @FXML private TableView<Envio> tablaEnvios;
    @FXML private TableColumn<Envio, String> colId, colOrigen, colDestino, colEstado;

    @FXML private Label lblId, lblOrigen, lblDestino, lblCosto, lblCliente, lblEstado;

    private final Database db = Database.getInstancia();
    private User repartidorActual;

    public void setRepartidorActual(User repartidor) {
        this.repartidorActual = repartidor;
        cargarEnviosAsignados();
    }

    @FXML
    private void initialize() {

        colId.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(d.getValue().getId()));

        colOrigen.setCellValueFactory(d ->
                new javafx.beans.property.SimpleStringProperty(d.getValue().getOrigen().toString()));

        colDestino.setCellValueFactory(d ->
                new javafx.beans.property.SimpleStringProperty(d.getValue().getDestino().toString()));

        colEstado.setCellValueFactory(d ->
                new javafx.beans.property.SimpleStringProperty(d.getValue().getEstado().toString()));

        tablaEnvios.getSelectionModel().selectedItemProperty()
                .addListener((obs, oldVal, newVal) -> mostrarDetalles(newVal));
    }

    private void cargarEnviosAsignados() {

        var asignados = db.getListaEnvios()
                .stream()
                .filter(e -> e.getRepartidor() != null
                        && e.getRepartidor().getId().equals(repartidorActual.getId()))
                .toList();

        tablaEnvios.setItems(FXCollections.observableArrayList(asignados));
    }

    private void mostrarDetalles(Envio e) {
        if (e == null) return;

        lblId.setText(e.getId());
        lblOrigen.setText(e.getOrigen().toString());
        lblDestino.setText(e.getDestino().toString());
        lblCosto.setText(String.valueOf(e.getPago()));
        lblEstado.setText(e.getEstado().toString());

        if (e.getPedido() != null && e.getPedido().getCliente() != null)
            lblCliente.setText(e.getPedido().getCliente().getNombreCompleto());
        else
            lblCliente.setText("Desconocido");
    }

    // ======================== ESTADOS ==========================

    @FXML
    private void marcarEnCamino() {
        actualizarEstado(EstadoEnvio.EN_CAMINO);
    }

    @FXML
    private void marcarEntregado() {
        actualizarEstado(EstadoEnvio.ENTREGADO);
    }

    @FXML
    private void reportarProblema() {
        actualizarEstado(EstadoEnvio.INCIDENCIA);
    }

    private void actualizarEstado(EstadoEnvio nuevoEstado) {

        Envio seleccionado = tablaEnvios.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarAlerta("Seleccione un envío.");
            return;
        }

        seleccionado.setEstado(nuevoEstado);
        mostrarDetalles(seleccionado);
        cargarEnviosAsignados();
    }

    // ======================== CERRAR SESIÓN (CORREGIDO) ==========================

    @FXML
    private void cerrarSesion() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/co/edu/uniquindio/proyectofinalp2/login.fxml")
            );
            Parent root = loader.load();

            Stage stage = (Stage) tablaEnvios.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Iniciar Sesión");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void mostrarAlerta(String msg) {
        new Alert(Alert.AlertType.WARNING, msg).show();
    }
}
