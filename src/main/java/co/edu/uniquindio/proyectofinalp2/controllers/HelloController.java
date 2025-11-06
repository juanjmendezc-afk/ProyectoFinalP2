package co.edu.uniquindio.proyectofinalp2.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        if (welcomeText != null) {
            welcomeText.setText("Bienvenido a la aplicación de Logística!");
        }
    }
}
