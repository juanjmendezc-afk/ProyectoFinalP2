package co.edu.uniquindio.proyectofinalp2.services;

import co.edu.uniquindio.proyectofinalp2.models.Envio;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ReportGeneratorCSV {

    public void generarReporte(ArrayList<Envio> listaEnvios, String nombreArchivo) {

        try (FileWriter writer = new FileWriter(nombreArchivo)) {

            // Encabezado del archivo CSV
            writer.write("ID;Estado;Pago;Repartidor;Cliente\n");

            // Escribir cada envío
            for (Envio envio : listaEnvios) {
                writer.write(formatearEnvio(envio) + "\n");
            }

            System.out.println("Reporte generado: " + nombreArchivo);

        } catch (IOException e) {
            System.out.println("Error al generar el reporte: " + e.getMessage());
        }
    }

    private String formatearEnvio(Envio envio) {
        String id = envio.getId();
        String estado = envio.getEstado() != null ? envio.getEstado().toString() : "Sin estado";
        String pago = String.valueOf(envio.getPago());
        String repartidor = envio.getRepartidor() != null ? envio.getRepartidor().getNombreCompleto() : "No asignado";
        String cliente = (envio.getPedido() != null && envio.getPedido().getCliente() != null)
                ? envio.getPedido().getCliente().getNombreCompleto()
                : "No registrado";

        return id + ";" + estado + ";" + pago + ";" + repartidor + ";" + cliente;
    }
}
