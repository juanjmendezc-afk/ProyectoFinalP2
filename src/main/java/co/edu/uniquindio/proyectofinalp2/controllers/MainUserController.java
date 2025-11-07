package co.edu.uniquindio.proyectofinalp2.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainUserController {

    @FXML
    private Button btnCerrarSesion;

    @FXML
    private void abrirCotizador() {
        System.out.println("Abrir cotizador");
        // vista del cotizador
    }

    @FXML
    private void abrirCrearEnvio() {
        System.out.println("Crear solicitud de envío");
        // vista de registro o envío
    }

    @FXML
    private void abrirRastreo() {
        System.out.println("Rastrear paquete");
        // vista de rastreo
    }

    @FXML
    private void abrirHistorial() {
        System.out.println("Ver historial");
        // vista del historial
    }

    @FXML
    private void cerrarSesion() {
        System.out.println("Cerrando sesión...");

        Stage stage = (Stage) btnCerrarSesion.getScene().getWindow();
        stage.close();
    }
}
