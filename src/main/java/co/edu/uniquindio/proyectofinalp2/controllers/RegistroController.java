package co.edu.uniquindio.proyectofinalp2.controllers;

import co.edu.uniquindio.proyectofinalp2.estructural.SistemaLogisticaFacade;
import co.edu.uniquindio.proyectofinalp2.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegistroController {

    @FXML private TextField txtNombreRegistro;
    @FXML private TextField txtEmailRegistro;
    @FXML private TextField txtTelefonoRegistro;
    @FXML private ComboBox<String> comboRol;
    @FXML private ComboBox<String> comboTipoId;
    @FXML private TextField txtNumeroId;
    @FXML private PasswordField txtPasswordRegistro;
    @FXML private PasswordField txtConfirmarPassword;
    @FXML private Label lblMensajeRegistro;

    private SistemaLogisticaFacade facade = SistemaLogisticaFacade.getInstancia();

    @FXML
    public void initialize() {
        comboRol.getItems().addAll("Usuario", "Repartidor");
        comboTipoId.getItems().addAll("CC", "TI", "CE");
    }

    @FXML
    public void registrarUsuario(ActionEvent event) {

        String nombre = txtNombreRegistro.getText().trim();
        String email = txtEmailRegistro.getText().trim();
        String telefono = txtTelefonoRegistro.getText().trim();
        String pass = txtPasswordRegistro.getText().trim();
        String confirmar = txtConfirmarPassword.getText().trim();
        String rol = comboRol.getValue();
        String tipoId = comboTipoId.getValue();
        String numeroId = txtNumeroId.getText().trim();

        // ================================
        // VALIDACIONES SIMPLES
        // ================================
        if (nombre.isEmpty() || email.isEmpty() || telefono.isEmpty() ||
                pass.isEmpty() || confirmar.isEmpty() || rol == null ||
                tipoId == null || numeroId.isEmpty()) {

            lblMensajeRegistro.setText("Debe completar todos los campos.");
            lblMensajeRegistro.setStyle("-fx-text-fill: red;");
            return;
        }

        // ✔ validar teléfono: solo números y 10 dígitos
        if (!telefono.matches("\\d{10}")) {
            lblMensajeRegistro.setText("Teléfono inválido. Debe tener 10 números.");
            lblMensajeRegistro.setStyle("-fx-text-fill: red;");
            return;
        }

        // ✔ validar identificación: solo números, mínimo 6 dígitos
        if (!numeroId.matches("\\d{6,}")) {
            lblMensajeRegistro.setText("Número de identificación inválido.");
            lblMensajeRegistro.setStyle("-fx-text-fill: red;");
            return;
        }

        if (!pass.equals(confirmar)) {
            lblMensajeRegistro.setText("Las contraseñas no coinciden.");
            lblMensajeRegistro.setStyle("-fx-text-fill: red;");
            return;
        }

        // Convertimos el rol visible al rol real
        String rolReal = rol.equals("Usuario") ? "USER" : "REPARTIDOR";

        String estado = rolReal.equals("REPARTIDOR")
                ? "PENDIENTE_APROBACION"
                : "ACTIVO";

        // Crear usuario como siempre
        User nuevoUsuario = new User(
                "TEMPORAL", nombre, email, pass, telefono,
                rolReal, tipoId, numeroId, estado
        );

        boolean registrado = facade.registrarUsuario(nuevoUsuario);

        if (!registrado) {
            lblMensajeRegistro.setText("El email ya está registrado.");
            lblMensajeRegistro.setStyle("-fx-text-fill: red;");
            return;
        }

        lblMensajeRegistro.setText(
                rolReal.equals("REPARTIDOR")
                        ? "Solicitud enviada. Espere aprobación."
                        : "Usuario registrado correctamente."
        );
        lblMensajeRegistro.setStyle("-fx-text-fill: green;");

        volverAlLogin(event);
    }

    @FXML
    public void volverAlLogin(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/co/edu/uniquindio/proyectofinalp2/login.fxml")
            );
            Parent root = loader.load();

            Stage stage = (Stage) txtNombreRegistro.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            lblMensajeRegistro.setText("No se pudo volver al login.");
        }
    }
}
