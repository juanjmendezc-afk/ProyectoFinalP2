package co.edu.uniquindio.proyectofinalp2.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class UsuariosAdminController {

    @FXML
    private void volver() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/co/edu/uniquindio/proyectofinalp2/views/MainAdminView.fxml")
            );

            Parent root = loader.load();
            Stage stage = (Stage) Stage.getWindows()
                    .stream()
                    .filter(window -> window.isShowing())
                    .findFirst()
                    .get();

            stage.setScene(new Scene(root));
            stage.setTitle("Panel del Administrador");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
