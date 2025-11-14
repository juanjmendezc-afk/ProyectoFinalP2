package co.edu.uniquindio.proyectofinalp2.controllers;

import co.edu.uniquindio.proyectofinalp2.models.*;
import co.edu.uniquindio.proyectofinalp2.repositories.Database;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.ArrayList;

public class CrearEnvioController {

    @FXML private TextField txtOrigen, txtCiudadOrigen, txtDestino, txtCiudadDestino, txtCosto;
    @FXML private Label lblMensaje;

    private User usuarioActual;
    private final Database db = Database.getInstancia();

    public void setUsuarioActual(User usuario) {
        this.usuarioActual = usuario;
    }

    @FXML
    private void initialize() {
        try {
            if (co.edu.uniquindio.proyectofinalp2.controllers.CotizarEnvioController.costoEstimado > 0) {
                txtCosto.setText(String.valueOf(
                        co.edu.uniquindio.proyectofinalp2.controllers.CotizarEnvioController.costoEstimado
                ));
            }
        } catch (Exception ignored) {}
    }

    @FXML
    private void guardarEnvio() {
        try {
            String origen = txtOrigen.getText().trim();
            String ciudadOrigen = txtCiudadOrigen.getText().trim();
            String destino = txtDestino.getText().trim();
            String ciudadDestino = txtCiudadDestino.getText().trim();
            double costo = Double.parseDouble(txtCosto.getText().trim());

            if (origen.isEmpty() || destino.isEmpty() || ciudadOrigen.isEmpty() || ciudadDestino.isEmpty()) {
                mostrarError("Por favor complete todos los campos.");
                return;
            }

            // Crear direcciones
            Direccion dirOrigen = new Direccion(origen, ciudadOrigen);
            Direccion dirDestino = new Direccion(destino, ciudadDestino);

            // Crear envío
            String idEnvio = "E" + (db.getListaEnvios().size() + 1);
            Envio nuevo = new Envio(idEnvio, dirOrigen, dirDestino, null, EstadoEnvio.PENDIENTE, costo);

            // Crear pedido correctamente
            Pedido pedido = new Pedido(
                    "P" + idEnvio,
                    usuarioActual,
                    new ArrayList<>(),
                    destino,  // dirección simple
                    costo,
                    "PENDIENTE"
            );

            // Asignar pedido al envío
            nuevo.setPedido(pedido);

            // Guardar en DB (solo envíos, tu DB no maneja pedidos)
            db.agregarEnvio(nuevo);

            mostrarOK("Envío creado correctamente.");
            limpiarCampos();

        } catch (NumberFormatException e) {
            mostrarError("El costo debe ser un número válido.");
        } catch (Exception e) {
            mostrarError("Error al crear el envío.");
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

    private void mostrarError(String msg) {
        lblMensaje.setText(msg);
        lblMensaje.setStyle("-fx-text-fill: red;");
    }

    private void mostrarOK(String msg) {
        lblMensaje.setText(msg);
        lblMensaje.setStyle("-fx-text-fill: green;");
    }

    @FXML
    private void volver() {
        Stage stage = (Stage) txtOrigen.getScene().getWindow();
        stage.close();
    }
}

