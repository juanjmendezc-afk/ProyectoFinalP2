package co.edu.uniquindio.proyectofinalp2.controllers;

import co.edu.uniquindio.proyectofinalp2.models.User;
import co.edu.uniquindio.proyectofinalp2.repositories.Database;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    // Campos para LOGIN
    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Button btnLogin;

    @FXML
    private Label lblMensaje;

    // Campos para REGISTRO
    @FXML private TextField txtNombreRegistro;
    @FXML private TextField txtEmailRegistro;
    @FXML private TextField txtTelefonoRegistro;
    @FXML private PasswordField txtPasswordRegistro;
    @FXML private PasswordField txtConfirmarPassword;
    @FXML private Button btnRegistrar;
    @FXML private Button btnVolverLogin;
    @FXML private Label lblMensajeRegistro;

    private Database database;

    @FXML
    public void initialize() {
        database = Database.getInstancia();
        if (btnLogin != null) {
            btnLogin.setOnAction(event -> onLoginClick());
        }
        if (txtPassword != null) {
            txtPassword.setOnAction(event -> onLoginClick());
        }

        if (btnRegistrar != null) {
            btnRegistrar.setOnAction(event -> onRegistrarClick());
        }
        if (btnVolverLogin != null) {
            btnVolverLogin.setOnAction(event -> volverALogin());
        }

        System.out.println("LoginController inicializado");
    }


    @FXML
    private void onLoginClick() {
        String email = txtEmail.getText().trim();
        String password = txtPassword.getText().trim();

        if (validarCamposLogin(email, password)) {
            if (database.autenticar(email, password)) {
                mostrarMensajeExito("Login exitoso!");
                redirigirSegunTipoUsuario();
            } else {
                mostrarError("Credenciales incorrectas");
            }
        }
    }

    private boolean validarCamposLogin(String email, String password) {
        if (email.isEmpty() || password.isEmpty()) {
            mostrarError("Por favor complete todos los campos");
            return false;
        }

        // Validación básica de email
        if (!email.contains("@")) {
            mostrarError("Ingrese un email válido");
            return false;
        }

        return true;
    }

    private void redirigirSegunTipoUsuario() {
        if (database.esAdmin()) {
            System.out.println("Redirigiendo a PANEL ADMINISTRADOR");
        } else {
            System.out.println("Redirigiendo a PANEL USUARIO");
        }
    }


    @FXML
    private void onRegistrarClick() {
        String nombre = txtNombreRegistro.getText().trim();
        String email = txtEmailRegistro.getText().trim();
        String telefono = txtTelefonoRegistro.getText().trim();
        String password = txtPasswordRegistro.getText().trim();
        String confirmarPassword = txtConfirmarPassword.getText().trim();

        if (validarRegistro(nombre, email, telefono, password, confirmarPassword)) {
            User nuevoUsuario = new User(
                    generarIdUsuario(),
                    nombre,
                    email,
                    password,
                    telefono,
                    "USUARIO"
            );

            database.agregarUsuario(nuevoUsuario);
            mostrarMensajeRegistroExito("Usuario registrado exitosamente!");
            limpiarCamposRegistro();
        }
    }

    private boolean validarRegistro(String nombre, String email, String telefono, String password, String confirmarPassword) {
        if (nombre.isEmpty() || email.isEmpty() || telefono.isEmpty() || password.isEmpty() || confirmarPassword.isEmpty()) {
            mostrarErrorRegistro("Todos los campos son obligatorios");
            return false;
        }

        if (!email.contains("@")) {
            mostrarErrorRegistro("Ingrese un email válido");
            return false;
        }

        if (!password.equals(confirmarPassword)) {
            mostrarErrorRegistro("Las contraseñas no coinciden");
            return false;
        }

        if (password.length() < 4) {
            mostrarErrorRegistro("La contraseña debe tener al menos 4 caracteres");
            return false;
        }

        if (emailExiste(email)) {
            mostrarErrorRegistro("El email ya está registrado");
            return false;
        }

        return true;
    }

    private boolean emailExiste(String email) {
        for (User usuario : database.getUsuarios()) {
            if (usuario.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

    private String generarIdUsuario() {
        return "U" + String.format("%03d", database.getUsuarios().size() + 1);
    }


    @FXML
    public void mostrarPantallaRegistro() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/proyectofinalp2/registro.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) btnLogin.getScene().getWindow();
            stage.setScene(new Scene(root, 450, 600));
            stage.setTitle("Registro de Usuario");
        } catch (IOException e) {
            mostrarError("Error al cargar pantalla de registro: " + e.getMessage());
        }
    }

    @FXML
    public void volverALogin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/proyectofinalp2/login.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) btnVolverLogin.getScene().getWindow();
            stage.setScene(new Scene(root, 400, 500));
            stage.setTitle("Sistema de Logística - Login");
        } catch (IOException e) {
            mostrarError("Error al cargar pantalla de login: " + e.getMessage());
        }
    }


    private void mostrarError(String mensaje) {
        if (lblMensaje != null) {
            lblMensaje.setText(mensaje);
            lblMensaje.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
        }
    }

    private void mostrarMensajeExito(String mensaje) {
        if (lblMensaje != null) {
            lblMensaje.setText(mensaje);
            lblMensaje.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
        }
    }

    private void mostrarErrorRegistro(String mensaje) {
        if (lblMensajeRegistro != null) {
            lblMensajeRegistro.setText(mensaje);
            lblMensajeRegistro.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
        }
    }

    private void mostrarMensajeRegistroExito(String mensaje) {
        if (lblMensajeRegistro != null) {
            lblMensajeRegistro.setText(mensaje);
            lblMensajeRegistro.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
        }
    }

    public void limpiarCampos() {
        if (txtEmail != null) txtEmail.clear();
        if (txtPassword != null) txtPassword.clear();
        if (lblMensaje != null) lblMensaje.setText("");
    }

    private void limpiarCamposRegistro() {
        if (txtNombreRegistro != null) txtNombreRegistro.clear();
        if (txtEmailRegistro != null) txtEmailRegistro.clear();
        if (txtTelefonoRegistro != null) txtTelefonoRegistro.clear();
        if (txtPasswordRegistro != null) txtPasswordRegistro.clear();
        if (txtConfirmarPassword != null) txtConfirmarPassword.clear();
    }
}