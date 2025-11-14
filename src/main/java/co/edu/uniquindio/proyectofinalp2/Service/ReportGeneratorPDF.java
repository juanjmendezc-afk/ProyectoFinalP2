package co.edu.uniquindio.proyectofinalp2.services;

import co.edu.uniquindio.proyectofinalp2.models.Envio;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;
import java.util.ArrayList;

public class ReportGeneratorPDF {

    public void generarReportePDF(ArrayList<Envio> listaEnvios, String nombreArchivo) {

        try (PDDocument documento = new PDDocument()) {

            PDPage pagina = new PDPage();
            documento.addPage(pagina);

            PDPageContentStream contenido = new PDPageContentStream(documento, pagina);

            contenido.beginText();
            contenido.setFont(PDType1Font.HELVETICA_BOLD, 18);
            contenido.newLineAtOffset(50, 750);
            contenido.showText("REPORTE DE ENVIOS");
            contenido.endText();

            int y = 720;

            for (Envio envio : listaEnvios) {
                contenido.beginText();
                contenido.setFont(PDType1Font.HELVETICA, 12);
                contenido.newLineAtOffset(50, y);

                String linea = "ID: " + envio.getId() +
                        " | Estado: " + envio.getEstadoTexto() +
                        " | Pago: " + envio.getPagoTexto() +
                        " | Cliente: " + envio.getClienteTexto() +
                        " | Repartidor: " + envio.getRepartidorTexto();

                contenido.showText(linea);
                contenido.endText();

                y -= 20;

                if (y < 60) {
                    contenido.close();
                    pagina = new PDPage();
                    documento.addPage(pagina);
                    contenido = new PDPageContentStream(documento, pagina);
                    y = 750;
                }
            }

            contenido.close();
            documento.save(nombreArchivo);

            System.out.println("PDF generado correctamente: " + nombreArchivo);

        } catch (IOException e) {
            System.out.println("Error al crear PDF: " + e.getMessage());
        }
    }
}
