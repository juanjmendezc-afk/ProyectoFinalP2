package co.edu.uniquindio.proyectofinalp2.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Controlador principal del menú del usuario.
 * - Cotizar envío
 * - Crear envío
 * - Rastrear paquete
 * - Ver historial
 * - Cerrar sesión
 */
public class MainUserController {

    @FXML
    private Button btnCerrarSesion;

    /**
     * Abre la ventana del cotizador de envíos.
     */
    @FXML
    private void abrirCotizador() {
        abrirVentana("/co/edu/uniquindio/proyectofinalp2/views/CotizarEnvioView.fxml",
                "Cotizar envío");
    }

    /**
     * Abre la ventana para crear un nuevo envío.
     */
    @FXML
    private void abrirCrearEnvio() {
        abrirVentana("/co/edu/uniquindio/proyectofinalp2/views/CrearEnvioView.fxml",
                "Crear nuevo envío");
    }

    /**
     * Abre la vista de rastreo de paquetes.
     */
    @FXML
    private void abrirRastreo() {
        abrirVentana("/co/edu/uniquindio/proyectofinalp2/views/RastrearEnvioView.fxml",
                "Rastrear envío");
    }

    /**
     * Abre la vista del historial de envíos.
     */
    @FXML
    private void abrirHistorial() {
        abrirVentana("/co/edu/uniquindio/proyectofinalp2/views/HistorialEnviosView.fxml",
                "Historial de envíos");
    }

    /**
     * Cierra la sesión actual y regresa al login principal.
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

            System.out.println(" Sesión cerrada correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(" Error al volver al login.");
        }
    }

    /**
     * Método auxiliar para abrir ventanas de manera uniforme.
     * @param rutaFXML Ruta del archivo FXML a cargar.
     * @param titulo   Título de la nueva ventana.
     */
    private void abrirVentana(String rutaFXML, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaFXML));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle(titulo);
            stage.setScene(new Scene(root));
            stage.show();

            System.out.println(" Ventana abierta correctamente: " + titulo);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(" Error al abrir la ventana: " + titulo);
        }
    }
}
