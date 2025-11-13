package co.edu.uniquindio.proyectofinalp2.controllers;

import co.edu.uniquindio.proyectofinalp2.models.Direccion;
import co.edu.uniquindio.proyectofinalp2.models.Envio;
import co.edu.uniquindio.proyectofinalp2.models.EstadoEnvio;
import co.edu.uniquindio.proyectofinalp2.models.User;
import co.edu.uniquindio.proyectofinalp2.repositories.Database;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class CrearEnvioController {

    @FXML
    private TextField txtOrigen, txtCiudadOrigen, txtDestino, txtCiudadDestino, txtCosto;

    @FXML
    private Label lblMensaje;

    private User usuarioActual;
    private final Database db = Database.getInstancia();

    public void setUsuarioActual(User usuario) {
        this.usuarioActual = usuario;
    }

    @FXML
    private void guardarEnvio() {
        try {
            String origen = txtOrigen.getText();
            String ciudadOrigen = txtCiudadOrigen.getText();
            String destino = txtDestino.getText();
            String ciudadDestino = txtCiudadDestino.getText();
            double costo = Double.parseDouble(txtCosto.getText());

            if (origen.isEmpty() || destino.isEmpty() || ciudadOrigen.isEmpty() || ciudadDestino.isEmpty()) {
                lblMensaje.setText("Por favor complete todos los campos.");
                lblMensaje.setStyle("-fx-text-fill: red;");
                return;
            }

            // Crear direcciones
            Direccion dirOrigen = new Direccion(origen, ciudadOrigen);
            Direccion dirDestino = new Direccion(destino, ciudadDestino);

            // Estado asignado automáticamente
            EstadoEnvio estado = EstadoEnvio.PENDIENTE;

            // Crear envío
            String id = "E" + (db.getListaEnvios().size() + 1);
            Envio nuevo = new Envio(id, dirOrigen, dirDestino, null, estado, costo);

            db.agregarEnvio(nuevo);

            lblMensaje.setText("✅ Envío creado correctamente (estado: PENDIENTE).");
            lblMensaje.setStyle("-fx-text-fill: green;");
            limpiarCampos();

        } catch (NumberFormatException e) {
            lblMensaje.setText("El costo debe ser un número válido.");
            lblMensaje.setStyle("-fx-text-fill: red;");
        } catch (Exception e) {
            lblMensaje.setText("Error al crear el envío.");
            lblMensaje.setStyle("-fx-text-fill: red;");
            e.printStackTrace();
        }
    }

    private void limpiarCampos() {
        txtOrigen.clear();
        txtCiudadOrigen.clear();
        txtDestino.clear();
        txtCiudadDestino.clear();
        txtCosto.clear();
    }
    @FXML
    private void initialize() {
        // Si existe un costo cotizado, lo muestra automáticamente
        if (CotizarEnvioController.costoEstimado > 0) {
            txtCosto.setText(String.valueOf(CotizarEnvioController.costoEstimado));
        }
    }

    @FXML
    private void volver() {
        Stage stage = (Stage) txtOrigen.getScene().getWindow();
        stage.close();
    }
}
