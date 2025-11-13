package co.edu.uniquindio.proyectofinalp2.services;

import co.edu.uniquindio.proyectofinalp2.models.Envio;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ReportGeneratorCSV {

    public void generarReporte(ArrayList<Envio> listaEnvios) {
        String nombreArchivo = "reporte_envios.csv";

        try (FileWriter writer = new FileWriter(nombreArchivo)) {
            // Encabezado
            writer.write("ID;Estado;Pago;Repartidor;Cliente\n");

            // Escribir los datos de cada envío
            for (Envio envio : listaEnvios) {
                writer.write(formatearEnvio(envio) + "\n");
            }

            System.out.println("Reporte generado correctamente: " + nombreArchivo);

        } catch (IOException e) {
            System.out.println("Error al generar el reporte: " + e.getMessage());
        }
    }

    /**
     * Convierte un objeto Envio a una línea de texto CSV.
     */
    private String formatearEnvio(Envio envio) {
        String id = obtenerTexto(envio.getId());
        String estado = (envio.getEstado() != null) ? envio.getEstado().toString() : "Sin estado";
        String pago = String.valueOf(envio.getPago());
        String repartidor = (envio.getRepartidor() != null) ? envio.getRepartidor().getNombreCompleto() : "No asignado";
        String cliente = (envio.getPedido() != null && envio.getPedido().getCliente() != null)
                ? envio.getPedido().getCliente().getNombreCompleto()
                : "No registrado";

        return id + ";" + estado + ";" + pago + ";" + repartidor + ";" + cliente;
    }

    /**
     * Método auxiliar para evitar nulls en texto.
     */
    private String obtenerTexto(String texto) {
        return (texto != null) ? texto : "No disponible";
    }
}
