package co.edu.uniquindio.proyectofinalp2;

import co.edu.uniquindio.proyectofinalp2.repositories.Database;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Database.getInstancia();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root, 400, 500);
        stage.setTitle("Sistema de Logística - Login");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        System.out.println("Aplicación iniciada - Pantalla de login cargada");
    }

    public static void main(String[] args) {
        launch();
    }
}