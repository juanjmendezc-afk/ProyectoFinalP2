package co.edu.uniquindio.proyectofinalp2.controllers;

import co.edu.uniquindio.proyectofinalp2.estructural.SistemaLogisticaFacade;
import co.edu.uniquindio.proyectofinalp2.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML private TextField txtEmail;
    @FXML private PasswordField txtPassword;
    @FXML private Label lblMensaje;

    private SistemaLogisticaFacade facade = SistemaLogisticaFacade.getInstancia();

    @FXML
    public void iniciarSesion(ActionEvent event) {

        String email = txtEmail.getText();
        String password = txtPassword.getText();

        if (email.isEmpty() || password.isEmpty()) {
            lblMensaje.setText("Debe ingresar email y contraseña.");
            return;
        }

        User user = facade.login(email, password);

        if (user == null) {
            lblMensaje.setText("Datos incorrectos o usuario no activo.");
            return;
        }

        // Bloqueo de repartidor pendiente
        if (user.getEstado().equals("PENDIENTE_APROBACION")) {
            lblMensaje.setText("Su solicitud aún no ha sido aprobada.");
            return;
        }

        try {
            FXMLLoader loader;

            switch (user.getRol()) {
                case "ADMIN" -> loader = new FXMLLoader(
                        getClass().getResource("/co/edu/uniquindio/proyectofinalp2/views/MainAdminView.fxml"));
                case "REPARTIDOR" -> loader = new FXMLLoader(
                        getClass().getResource("/co/edu/uniquindio/proyectofinalp2/views/MainRepartidorView.fxml"));
                default -> loader = new FXMLLoader(
                        getClass().getResource("/co/edu/uniquindio/proyectofinalp2/views/MainUserView.fxml"));
            }

            Parent root = loader.load();

            // 🔥 IMPORTANTE: ENVIAR USUARIO AL CONTROLADOR CORRESPONDIENTE
            Object controller = loader.getController();

            if (controller instanceof MainAdminController c1)
                c1.setAdminActual(user);

            if (controller instanceof MainRepartidorController c2)
                c2.setRepartidorActual(user);

            if (controller instanceof MainUserController c3)
                c3.setUsuarioActual(user);

            Stage stage = (Stage) txtEmail.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            lblMensaje.setText("Error cargando menú.");
        }
    }

    @FXML
    public void mostrarPantallaRegistro(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/co/edu/uniquindio/proyectofinalp2/registro.fxml")
            );
            Parent root = loader.load();

            Stage stage = (Stage) txtEmail.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            lblMensaje.setText("Error al abrir el registro.");
        }
    }
}
