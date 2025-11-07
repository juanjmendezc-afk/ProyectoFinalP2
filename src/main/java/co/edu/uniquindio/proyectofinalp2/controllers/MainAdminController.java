package co.edu.uniquindio.proyectofinalp2.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Controlador para la vista principal del administrador.
 * Métodos: abren vistas (placeholder) o vuelven al login.
 */
public class MainAdminController {

    @FXML
    private void abrirGestionUsuarios() {
        // carga la vista de gestión de usuarios (a implementar)
        cargarVista("/co/edu/uniquindio/proyectofinalp2/views/UsuariosAdminView.fxml", "Gestión de Usuarios");
    }

    @FXML
    private void abrirGestionRepartidores() {
        cargarVista("/co/edu/uniquindio/proyectofinalp2/views/RepartidoresAdminView.fxml", "Gestión de Repartidores");
    }

    @FXML
    private void abrirGestionEnvios() {
        cargarVista("/co/edu/uniquindio/proyectofinalp2/views/PedidosAdminView.fxml", "Gestión de Envíos");
    }

    @FXML
    private void abrirReportes() {
        cargarVista("/co/edu/uniquindio/proyectofinalp2/views/ReportesAdminView.fxml", "Reportes");
    }

    @FXML
    private void cerrarSesion() {
        // vuelve al login (no cierra la app, reemplaza la escena actual por login)
        cargarVista("/co/edu/uniquindio/proyectofinalp2/login.fxml", "Iniciar Sesión");
    }

    /**
     * Método reutilizable para cargar cualquier FXML y reemplazar la escena actual.
     * @param ruta ruta del FXML dentro del resources (con / al inicio)
     * @param titulo título de la ventana
     */
    private void cargarVista(String ruta, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(ruta));
            Parent root = loader.load();

            // ventana stage
            Stage stage = (Stage) Stage.getWindows()
                    .stream()
                    .filter(window -> window.isShowing())
                    .findFirst()
                    .get();

            stage.setScene(new Scene(root));
            stage.setTitle(titulo);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
