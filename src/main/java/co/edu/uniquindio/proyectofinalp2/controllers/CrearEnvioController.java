package co.edu.uniquindio.proyectofinalp2.controllers;

import co.edu.uniquindio.proyectofinalp2.creacional.EnvioFactory;
import co.edu.uniquindio.proyectofinalp2.models.*;
import co.edu.uniquindio.proyectofinalp2.repositories.Database;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class CrearEnvioController {

    @FXML private TextField txtOrigen, txtCiudadOrigen, txtDestino, txtCiudadDestino, txtCosto;
    @FXML private RadioButton rbTarjeta, rbContraEntrega;
    @FXML private Label lblMensaje;

    private User usuarioActual;
    private final Database db = Database.getInstancia();

    public void setUsuarioActual(User usuario) {
        this.usuarioActual = usuario;
    }

    @FXML
    private void initialize() {
        // cargar costo si viene desde el cotizador
        if (CotizarEnvioController.costoEstimado > 0) {
            txtCosto.setText(String.valueOf(CotizarEnvioController.costoEstimado));
        }
    }

    @FXML
    private void guardarEnvio() {
        try {
            // validar usuario
            if (usuarioActual == null) {
                mostrarError("No hay usuario autenticado.");
                return;
            }

            // obtener campos
            String origen = txtOrigen.getText().trim();
            String ciudadOrigen = txtCiudadOrigen.getText().trim();
            String destino = txtDestino.getText().trim();
            String ciudadDestino = txtCiudadDestino.getText().trim();
            String textoCosto = txtCosto.getText().trim();

            // validar vacíos
            if (origen.isEmpty() || ciudadOrigen.isEmpty() ||
                    destino.isEmpty() || ciudadDestino.isEmpty()) {
                mostrarError("Complete todos los campos.");
                return;
            }

            // validar que origen y destino no sean iguales
            if (origen.equalsIgnoreCase(destino) &&
                    ciudadOrigen.equalsIgnoreCase(ciudadDestino)) {
                mostrarError("El origen y el destino no pueden ser iguales.");
                return;
            }

            // validar costo
            double costo;
            try {
                costo = Double.parseDouble(textoCosto);
                if (costo <= 0) {
                    mostrarError("Costo inválido.");
                    return;
                }
            } catch (Exception e) {
                mostrarError("Costo inválido.");
                return;
            }

            // validar método de pago
            if (!rbTarjeta.isSelected() && !rbContraEntrega.isSelected()) {
                mostrarError("Seleccione un método de pago.");
                return;
            }

            // crear direcciones
            Direccion dirOrigen = new Direccion(origen, ciudadOrigen);
            Direccion dirDestino = new Direccion(destino, ciudadDestino);

            // id del envío
            String idEnvio = "E" + (db.getListaEnvios().size() + 1);

            // texto de dirección para el pedido
            String direccionEntrega = destino + ", " + ciudadDestino;

            // usar factory
            boolean pagado = rbTarjeta.isSelected();

            Envio nuevo = EnvioFactory.crearEnvio(
                    idEnvio,
                    dirOrigen,
                    dirDestino,
                    usuarioActual,
                    direccionEntrega,
                    costo,
                    pagado
            );

            if (nuevo == null) {
                mostrarError("No se pudo crear el envío.");
                return;
            }

            db.agregarEnvio(nuevo);

            mostrarOK("Envío creado correctamente.");
            limpiarCampos();

            CotizarEnvioController.costoEstimado = 0;

        } catch (Exception e) {
            mostrarError("Error al crear el envío.");
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
        lblMensaje.setStyle("-fx-text-fill: red;");
        lblMensaje.setText(msg);
    }

    private void mostrarOK(String msg) {
        lblMensaje.setStyle("-fx-text-fill: green;");
        lblMensaje.setText(msg);
    }

    @FXML
    private void volver() {
        ((Stage) txtOrigen.getScene().getWindow()).close();
    }
}

