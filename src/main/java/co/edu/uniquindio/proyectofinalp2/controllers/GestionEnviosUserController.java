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

public class GestionEnviosUserController {

    @FXML private TableView<Envio> tablaEnvios;
    @FXML private TableColumn<Envio, String> colId, colOrigen, colDestino, colEstado, colPago;

    private User usuarioActual;
    private final Database db = Database.getInstancia();

    // Recibe el usuario desde MainUserController
    public void setUsuarioActual(User user) {
        this.usuarioActual = user;
        cargarEnvios();
    }

    @FXML
    private void initialize() {
        colId.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(d.getValue().getId()));
        colOrigen.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(d.getValue().getOrigen().toString()));
        colDestino.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(d.getValue().getDestino().toString()));
        colEstado.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(d.getValue().getEstado().toString()));
        colPago.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty("$" + d.getValue().getPago()));
    }

    private void cargarEnvios() {
        var envios = db.getListaEnvios().stream()
                .filter(e -> e.getPedido() != null &&
                        e.getPedido().getCliente() != null &&
                        e.getPedido().getCliente().getId().equals(usuarioActual.getId()))
                .toList();

        tablaEnvios.setItems(FXCollections.observableArrayList(envios));
    }

    @FXML
    private void abrirEditarEnvio() {
        Envio seleccionado = tablaEnvios.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            alert("Seleccione un envío a editar.");
            return;
        }

        // No se puede editar si YA fue asignado a repartidor
        if (seleccionado.getEstado() != EstadoEnvio.PENDIENTE) {
            alert("Solo puedes editar envíos que aún NO han sido asignados.");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/co/edu/uniquindio/proyectofinalp2/views/EditarEnvioView.fxml"));

            Parent root = loader.load();
            EditarEnvioController controller = loader.getController();
            controller.setEnvio(seleccionado, this::cargarEnvios);

            Stage stage = new Stage();
            stage.setTitle("Editar envío");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            alert("Error al abrir editor.");
        }
    }

    @FXML
    private void cancelarEnvio() {
        Envio seleccionado = tablaEnvios.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            alert("Seleccione un envío a cancelar.");
            return;
        }

        if (seleccionado.getEstado() != EstadoEnvio.PENDIENTE) {
            alert("Solo puedes cancelar envíos aún no asignados.");
            return;
        }

        db.getListaEnvios().remove(seleccionado);
        cargarEnvios();
        alert("Envío cancelado correctamente.");
    }

    @FXML
    private void volver() {
        ((Stage) tablaEnvios.getScene().getWindow()).close();
    }

    private void alert(String msg) {
        new Alert(Alert.AlertType.INFORMATION, msg).show();
    }
}
