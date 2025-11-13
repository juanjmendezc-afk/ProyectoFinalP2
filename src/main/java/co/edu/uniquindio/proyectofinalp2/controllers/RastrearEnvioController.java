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

    /**
     * Busca el envío en la base de datos por su ID.
     */
    @FXML
    private void buscarEnvio() {
        String id = txtIdEnvio.getText().trim();

        if (id.isEmpty()) {
            lblResultado.setText("⚠️ Por favor ingrese un ID de envío.");
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
        } else {
            lblResultado.setText(
                    " Envío encontrado:\n" +
                            "Origen: " + envioEncontrado.getDireccionOrigen().getCiudad() + "\n" +
                            "Destino: " + envioEncontrado.getDireccionDestino().getCiudad() + "\n" +
                            "Estado: " + envioEncontrado.getEstado()
            );
            lblResultado.setStyle("-fx-text-fill: green;");
        }
    }

    /**
     * Cierra la ventana actual.
     */
    @FXML
    private void cerrarVentana() {
        Stage stage = (Stage) txtIdEnvio.getScene().getWindow();
        stage.close();
    }
}
