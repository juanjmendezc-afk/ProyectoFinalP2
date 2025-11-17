package co.edu.uniquindio.proyectofinalp2.controllers;

import co.edu.uniquindio.proyectofinalp2.models.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Controlador principal del menú del usuario.
 */
public class MainUserController {

    @FXML
    private Button btnCerrarSesion;

    private User usuarioActual;

    public void setUsuarioActual(User usuario) {
        this.usuarioActual = usuario;
        System.out.println("Usuario recibido en MainUserController: " + usuario.getNombreCompleto());
    }

    // COTIZAR ENVÍO

    @FXML
    private void abrirCotizador() {
        abrirVentanaSimple(
                "/co/edu/uniquindio/proyectofinalp2/views/CotizarEnvioView.fxml",
                "Cotizar envío"
        );
    }


    @FXML
    private void abrirCrearEnvio() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/co/edu/uniquindio/proyectofinalp2/views/CrearEnvioView.fxml"));

            Parent root = loader.load();

            CrearEnvioController controller = loader.getController();
            controller.setUsuarioActual(usuarioActual);

            Stage stage = new Stage();
            stage.setTitle("Crear nuevo envío");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            System.out.println("Error al abrir Crear Envío");
            e.printStackTrace();
        }
    }

    @FXML
    private void abrirGestionEnvios() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/co/edu/uniquindio/proyectofinalp2/views/GestionEnviosUserView.fxml"));

            Parent root = loader.load();

            GestionEnviosUserController controller = loader.getController();
            controller.setUsuarioActual(usuarioActual);

            Stage stage = new Stage();
            stage.setTitle("Mis Envíos");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            System.out.println("Error al abrir Gestión de Envíos");
            e.printStackTrace();
        }
    }

    @FXML
    private void abrirRastreo() {
        abrirVentanaSimple(
                "/co/edu/uniquindio/proyectofinalp2/views/RastrearEnvioView.fxml",
                "Rastrear envío"
        );
    }

    @FXML
    private void abrirHistorial() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/co/edu/uniquindio/proyectofinalp2/views/HistorialEnviosView.fxml"));

            Parent root = loader.load();

            HistorialEnviosController controller = loader.getController();
            controller.setUsuarioActual(usuarioActual);

            Stage stage = new Stage();
            stage.setTitle("Historial de envíos");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            System.out.println("Error al abrir Historial");
            e.printStackTrace();
        }
    }

    @FXML
    private void abrirComprobantes() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/co/edu/uniquindio/proyectofinalp2/views/ComprobantesView.fxml"));

            Parent root = loader.load();

            ComprobantesController controller = loader.getController();
            controller.setUsuarioActual(usuarioActual);

            Stage stage = new Stage();
            stage.setTitle("Comprobantes de Pago");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            System.out.println("Error al abrir Comprobantes");
            e.printStackTrace();
        }
    }

    @FXML
    private void cerrarSesion() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/co/edu/uniquindio/proyectofinalp2/login.fxml"));

            Parent root = loader.load();

            Stage stage = (Stage) btnCerrarSesion.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Iniciar sesión");
            stage.show();

        } catch (Exception e) {
            System.out.println("Error al regresar al login");
            e.printStackTrace();
        }
    }

    private void abrirVentanaSimple(String rutaFXML, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaFXML));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle(titulo);
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            System.out.println("Error al abrir ventana: " + titulo);
            e.printStackTrace();
        }
    }
}
