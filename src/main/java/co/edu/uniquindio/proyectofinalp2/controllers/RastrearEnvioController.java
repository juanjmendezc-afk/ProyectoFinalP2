package co.edu.uniquindio.proyectofinalp2.controllers;

import co.edu.uniquindio.proyectofinalp2.models.Envio;
import co.edu.uniquindio.proyectofinalp2.repositories.Database;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class RastrearEnvioController {

    @FXML
    private TextField txtIdEnvio;

    @FXML
    private Label lblResultado;

    private final Database db = Database.getInstancia();

    @FXML
    private void buscarEnvio() {
        String id = txtIdEnvio.getText().trim();

        if (id.isEmpty()) {
            lblResultado.setText("⚠ Por favor ingrese un ID de envío.");
            lblResultado.setStyle("-fx-text-fill: red;");
            return;
        }

        Envio envioEncontrado = db.getListaEnvios()
                .stream()
                .filter(e -> e.getId().equalsIgnoreCase(id))
                .findFirst()
                .orElse(null);

        if (envioEncontrado == null) {
            lblResultado.setText("❌ No se encontró ningún envío con el ID: " + id);
            lblResultado.setStyle("-fx-text-fill: red;");
            return;
        }

        // MOSTRAR INFORMACIÓN DEL ENVÍO
        lblResultado.setText(
                "✔ Envío encontrado\n\n" +
                        "• Origen: " + envioEncontrado.getOrigen().toString() + "\n" +
                        "• Destino: " + envioEncontrado.getDestino().toString() + "\n" +
                        "• Estado: " + envioEncontrado.getEstadoTexto() + "\n" +
                        "• Repartidor: " + envioEncontrado.getRepartidorTexto()
        );
        lblResultado.setStyle("-fx-text-fill: green;");
    }

    @FXML
    private void cerrarVentana() {
        Stage stage = (Stage) txtIdEnvio.getScene().getWindow();
        stage.close();
    }
}

