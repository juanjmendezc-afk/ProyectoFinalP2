package co.edu.uniquindio.proyectofinalp2.controllers;

import co.edu.uniquindio.proyectofinalp2.models.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Controlador principal del panel del administrador.
 * Funciones permitidas por requisitos RF-010 a RF-014.
 */
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
     * Método genérico para cargar una vista.
     */
    private void cargarVista(String ruta, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(ruta));
            Parent root = loader.load();

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
