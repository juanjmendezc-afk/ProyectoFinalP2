package co.edu.uniquindio.proyectofinalp2.controllers;

import co.edu.uniquindio.proyectofinalp2.models.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

public class MainAdminController {

    private User adminActual;

    public void setAdminActual(User admin) {
        this.adminActual = admin;
    }

    @FXML
    private void abrirGestionUsuarios() {
        cargarVista("/co/edu/uniquindio/proyectofinalp2/views/UsuariosAdminView.fxml",
                "Gestión de Usuarios");
    }

    @FXML
    private void abrirGestionRepartidores() {
        cargarVista("/co/edu/uniquindio/proyectofinalp2/views/RepartidoresAdminView.fxml",
                "Gestión de Repartidores");
    }

    @FXML
    private void abrirAsignarEnvios() {
        cargarVista("/co/edu/uniquindio/proyectofinalp2/views/AsignarRepartidorView.fxml",
                "Asignar Envíos a Repartidores");
    }

    @FXML
    private void abrirReportes() {
        cargarVista("/co/edu/uniquindio/proyectofinalp2/views/ReportesAdminView.fxml",
                "Reportes y Métricas");
    }

    @FXML
    private void cerrarSesion() {
        cargarVista("/co/edu/uniquindio/proyectofinalp2/login.fxml",
                "Iniciar Sesión");
    }

    /**
     * Método SEGURO para cargar vistas sin depender de un botón del FXML.
     */
    private void cargarVista(String ruta, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(ruta));
            Parent root = loader.load();

            // ✔ Obtiene el Stage ACTUAL de manera universal, sin riesgo de null
            Window window = Stage.getWindows()
                    .stream()
                    .filter(Window::isShowing)
                    .findFirst()
                    .orElse(null);

            if (window == null) {
                System.err.println("ERROR: No hay una ventana abierta.");
                return;
            }

            Stage stage = (Stage) window;
            stage.setScene(new Scene(root));
            stage.setTitle(titulo);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
