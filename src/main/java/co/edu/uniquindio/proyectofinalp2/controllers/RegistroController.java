package co.edu.uniquindio.proyectofinalp2.controllers;

import co.edu.uniquindio.proyectofinalp2.estructural.SistemaLogisticaFacade;
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

        String nombre = txtNombreRegistro.getText();
        String email = txtEmailRegistro.getText();
        String telefono = txtTelefonoRegistro.getText();
        String pass = txtPasswordRegistro.getText();
        String confirmar = txtConfirmarPassword.getText();
        String rol = comboRol.getValue();

        String tipoId = comboTipoId.getValue();
        String numeroId = txtNumeroId.getText();

        if (nombre.isEmpty() || email.isEmpty() || telefono.isEmpty() ||
                pass.isEmpty() || confirmar.isEmpty() || rol == null ||
                tipoId == null || numeroId.isEmpty()) {

            lblMensajeRegistro.setText("Debe completar todos los campos.");
            return;
        }

        if (!pass.equals(confirmar)) {
            lblMensajeRegistro.setText("Las contraseñas no coinciden.");
            return;
        }

        String rolReal = rol.equals("Usuario") ? "USER" : "REPARTIDOR";
        String estado = rolReal.equals("REPARTIDOR")
                ? "PENDIENTE_APROBACION"
                : "ACTIVO";

        boolean registrado = facade.registrarUsuario(
                nombre, email, telefono, pass, rolReal,
                tipoId, numeroId, estado
        );

        if (!registrado) {
            lblMensajeRegistro.setText("El email ya está registrado.");
            return;
        }

        lblMensajeRegistro.setText(
                rolReal.equals("REPARTIDOR")
                        ? "Solicitud enviada. Espere aprobación del administrador."
                        : "Usuario registrado correctamente."
        );

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
