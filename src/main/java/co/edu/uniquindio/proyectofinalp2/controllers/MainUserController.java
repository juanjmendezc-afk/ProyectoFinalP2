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

    // 🔹 Usuario autenticado
    private User usuarioActual;

    // 🔹 Este método lo llamará el LoginController
    public void setUsuarioActual(User usuario) {
        this.usuarioActual = usuario;
        System.out.println("Usuario recibido en MainUserController: " + usuario.getNombreCompleto());
    }

    /**
     * Abrir cotizador.
     */
    @FXML
    private void abrirCotizador() {
        abrirVentanaSimple("/co/edu/uniquindio/proyectofinalp2/views/CotizarEnvioView.fxml",
                "Cotizar envío");
    }

    /**
     * Abrir crear envío enviando el usuario actual
     */
    @FXML
    private void abrirCrearEnvio() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/co/edu/uniquindio/proyectofinalp2/views/CrearEnvioView.fxml"));
            Parent root = loader.load();

            CrearEnvioController controller = loader.getController();
            controller.setUsuarioActual(usuarioActual); // 🔹 Enviamos el usuario

            Stage stage = new Stage();
            stage.setTitle("Crear nuevo envío");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            System.out.println("Error al abrir Crear Envío");
            e.printStackTrace();
        }
    }

    /**
     * Abrir rastreo.
     */
    @FXML
    private void abrirRastreo() {
        abrirVentanaSimple("/co/edu/uniquindio/proyectofinalp2/views/RastrearEnvioView.fxml",
                "Rastrear envío");
    }

    /**
     * Abrir el historial, enviando usuarioActual
     */
    @FXML
    private void abrirHistorial() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/co/edu/uniquindio/proyectofinalp2/views/HistorialEnviosView.fxml"));
            Parent root = loader.load();

            HistorialEnviosController controller = loader.getController();
            controller.setUsuarioActual(usuarioActual); // 🔹 PASO IMPORTANTE

            Stage stage = new Stage();
            stage.setTitle("Historial de envíos");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            System.out.println("Error al abrir Historial");
            e.printStackTrace();
        }
    }

    /**
     * Cerrar sesión
     */
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
            System.out.println("Error al regresar al login.");
            e.printStackTrace();
        }
    }

    /**
     * Método rápido para abrir ventanas que NO requieren usuarioActual.
     */
    private void abrirVentanaSimple(String rutaFXML, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaFXML));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle(titulo);
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            System.out.println("Error al abrir la ventana: " + titulo);
            e.printStackTrace();
        }
    }
}
