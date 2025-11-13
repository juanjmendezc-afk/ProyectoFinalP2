package co.edu.uniquindio.proyectofinalp2.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class CotizarEnvioController {

    @FXML
    private ComboBox<String> comboTipoPaquete;

    @FXML
    private ComboBox<String> comboDestino;

    @FXML
    private Label lblResultado;

    // 🔹 Valor calculado que podrá ser leído desde CrearEnvio
    public static double costoEstimado = 0;

    @FXML
    private void initialize() {
        comboTipoPaquete.getItems().addAll("Liviano (0-5 kg)", "Mediano (6-15 kg)", "Pesado (16-30 kg)");
        comboDestino.getItems().addAll("Regional", "Nacional");
    }

    @FXML
    private void calcularCosto() {
        String tipo = comboTipoPaquete.getValue();
        String destino = comboDestino.getValue();

        if (tipo == null || destino == null) {
            lblResultado.setText("Seleccione tipo de paquete y destino.");
            lblResultado.setStyle("-fx-text-fill: red;");
            return;
        }

        double costoBase = switch (tipo) {
            case "Liviano (0-5 kg)" -> 10000;
            case "Mediano (6-15 kg)" -> 20000;
            case "Pesado (16-30 kg)" -> 35000;
            default -> 0;
        };

        if (destino.equals("Nacional")) {
            costoBase *= 1.5;
        }

        costoEstimado = costoBase; // 🔹 Guardamos el valor para usarlo luego
        lblResultado.setText("Costo estimado: $" + costoEstimado);
        lblResultado.setStyle("-fx-text-fill: green;");
    }

    @FXML
    private void volver() {
        Stage stage = (Stage) comboTipoPaquete.getScene().getWindow();
        stage.close();
    }
}
