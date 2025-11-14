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

public class UsuariosAdminController {

    @FXML private TableView<User> tablaUsuarios;
    @FXML private TableColumn<User, String> colId;
    @FXML private TableColumn<User, String> colNombre;
    @FXML private TableColumn<User, String> colEmail;
    @FXML private TableColumn<User, String> colTelefono;
    @FXML private TableColumn<User, String> colRol;
    @FXML private TableColumn<User, String> colEstado;

    @FXML private Label lblMensaje;

    private final Database db = Database.getInstancia();

    @FXML
    public void initialize() {
        colId.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getId()));
        colNombre.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNombreCompleto()));
        colEmail.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getEmail()));
        colTelefono.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getTelefono()));
        colRol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getRol()));
        colEstado.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getEstado()));

        cargarUsuarios();
    }

    private void cargarUsuarios() {
        tablaUsuarios.setItems(FXCollections.observableArrayList(db.listarUsuarios()));
    }

    @FXML
    private void eliminarUsuario() {
        User seleccionado = tablaUsuarios.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            mostrar("Seleccione un usuario para eliminar", "red");
            return;
        }

        if (seleccionado.getRol().equals("ADMIN")) {
            mostrar("No se puede eliminar el administrador", "red");
            return;
        }

        db.listarUsuarios().remove(seleccionado);
        cargarUsuarios();
        mostrar("Usuario eliminado correctamente", "green");
    }

    @FXML
    private void cambiarEstado() {
        User seleccionado = tablaUsuarios.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            mostrar("Seleccione un usuario", "red");
            return;
        }

        if (seleccionado.getEstado().equals("ACTIVO")) {
            seleccionado.setEstado("INACTIVO");
        } else {
            seleccionado.setEstado("ACTIVO");
        }

        cargarUsuarios();
        mostrar("Estado actualizado correctamente", "green");
    }

    @FXML
    private void editarUsuario() {
        User seleccionado = tablaUsuarios.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            mostrar("Seleccione un usuario para editar", "red");
            return;
        }

        TextInputDialog dialog = new TextInputDialog(seleccionado.getNombreCompleto());
        dialog.setHeaderText("Editar Nombre del Usuario");
        dialog.setContentText("Nuevo nombre:");

        dialog.showAndWait().ifPresent(nombreNuevo -> {
            seleccionado.setNombreCompleto(nombreNuevo);
            cargarUsuarios();
        });

        mostrar("Usuario editado correctamente", "green");
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
            Stage stage = (Stage) tablaUsuarios.getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.setTitle("Panel del Administrador");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
