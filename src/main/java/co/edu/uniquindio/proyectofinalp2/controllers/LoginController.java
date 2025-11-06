package co.edu.uniquindio.proyectofinalp2.controllers;

import co.edu.uniquindio.proyectofinalp2.repositories.Database;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Label lblMensaje;

    @FXML
    private Button btnIngresar;

    @FXML
    private Button btnRegistrarse;

    @FXML
    private void iniciarSesion() {
        String email = txtEmail.getText();
        String password = txtPassword.getText();

        Database db = Database.getInstancia();

        if (db.validarLogin(email, password)) {
            lblMensaje.setText("Inicio de sesión exitoso");
            lblMensaje.setStyle("-fx-text-fill: green;");

            abrirPantallaUsuario();
        } else {
            lblMensaje.setText("Email o contraseña incorrectos");
            lblMensaje.setStyle("-fx-text-fill: red;");
        }
    }

    @FXML
    private void mostrarPantallaRegistro() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/proyectofinalp2/registro.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) txtEmail.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Registro de Usuario");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void abrirPantallaUsuario() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/proyectofinalp2/clientes.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) txtEmail.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Panel del Usuario");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
