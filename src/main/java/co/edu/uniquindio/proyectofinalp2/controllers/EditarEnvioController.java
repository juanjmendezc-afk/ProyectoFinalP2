package co.edu.uniquindio.proyectofinalp2.controllers;

import co.edu.uniquindio.proyectofinalp2.models.Direccion;
import co.edu.uniquindio.proyectofinalp2.models.Envio;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditarEnvioController {

    @FXML private TextField txtOrigen;
    @FXML private TextField txtDestino;
    @FXML private TextField txtPago;

    private Envio envio;
    private Runnable refrescarTabla; // ← cambiamos Consumer<Void> por Runnable

    public void setEnvio(Envio e, Runnable refrescar) {
        this.envio = e;
        this.refrescarTabla = refrescar;

        txtOrigen.setText(e.getOrigen().getCalle() + " - " + e.getOrigen().getCiudad());
        txtDestino.setText(e.getDestino().getCalle() + " - " + e.getDestino().getCiudad());
        txtPago.setText(String.valueOf(e.getPago()));
    }

    @FXML
    private void guardarCambios() {

        try {
            // Origen
            String[] partesOrigen = txtOrigen.getText().split("-");
            if (partesOrigen.length < 2) {
                alert("Formato inválido para ORIGEN (calle - ciudad)");
                return;
            }
            Direccion nuevaOrigen = new Direccion(partesOrigen[0].trim(), partesOrigen[1].trim());

            // Destino
            String[] partesDestino = txtDestino.getText().split("-");
            if (partesDestino.length < 2) {
                alert("Formato inválido para DESTINO (calle - ciudad)");
                return;
            }
            Direccion nuevoDestino = new Direccion(partesDestino[0].trim(), partesDestino[1].trim());

            // Pago
            double nuevoPago = Double.parseDouble(txtPago.getText());

            envio.setOrigen(nuevaOrigen);
            envio.setDestino(nuevoDestino);
            envio.setPago(nuevoPago);

            alert("Cambios guardados correctamente.");

            //  ahora sí funciona PERFECTO
            refrescarTabla.run();

            cerrar();

        } catch (Exception e) {
            alert("Datos inválidos");
        }
    }

    @FXML
    private void cerrar() {
        ((Stage) txtOrigen.getScene().getWindow()).close();
    }

    private void alert(String msg) {
        new Alert(Alert.AlertType.INFORMATION, msg).show();
    }
}
