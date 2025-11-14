module co.edu.uniquindio.proyectofinalp2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.apache.pdfbox;

    opens co.edu.uniquindio.proyectofinalp2 to javafx.fxml;
    opens co.edu.uniquindio.proyectofinalp2.controllers to javafx.fxml;
    opens co.edu.uniquindio.proyectofinalp2.models to javafx.fxml;

    exports co.edu.uniquindio.proyectofinalp2;
    exports co.edu.uniquindio.proyectofinalp2.controllers;
    exports co.edu.uniquindio.proyectofinalp2.models;
}

