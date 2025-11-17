package co.edu.uniquindio.proyectofinalp2.controllers;

import co.edu.uniquindio.proyectofinalp2.comportamiento.*;
import co.edu.uniquindio.proyectofinalp2.estructural.*;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class CotizarEnvioController {

    @FXML private ComboBox<String> comboTipoPaquete;
    @FXML private ComboBox<String> comboDestino;
    @FXML private CheckBox chkSeguro;
    @FXML private CheckBox chkEmpaque;
    @FXML private Label lblResultado;

    public static double costoEstimado = 0;

    @FXML
    private void initialize() {
        comboTipoPaquete.getItems().addAll(
                "Liviano (0-5 kg)",
                "Mediano (6-15 kg)",
                "Pesado (16-30 kg)"
        );
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

        // STRATEGY
        CalculadoraCostoEnvio calculadora = new CalculadoraCostoEnvio();

        switch (tipo) {
            case "Liviano (0-5 kg)" -> calculadora.setEstrategia(new CostoLiviano());
            case "Mediano (6-15 kg)" -> calculadora.setEstrategia(new CostoMediano());
            case "Pesado (16-30 kg)" -> calculadora.setEstrategia(new CostoPesado());
        }

        double costoBase = calculadora.calcular(destino);

        // DECORATOR
        CostoExtra costo = base -> base;  // no extra al inicio

        if (chkSeguro.isSelected()) {
            costo = new SeguroDecorator(costo);
        }
        if (chkEmpaque.isSelected()) {
            costo = new EmpaqueEspecialDecorator(costo);
        }

        costoEstimado = costo.aplicar(costoBase);

        lblResultado.setText("Costo estimado: $" + costoEstimado);
        lblResultado.setStyle("-fx-text-fill: green;");
    }

    @FXML
    public void volver() {
        Stage stage = (Stage) comboTipoPaquete.getScene().getWindow();
        stage.close();
    }
}
