module lab2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires transitive javafx.graphics;
    
    opens com.project.lab2.controllers;
    exports com.project.lab2.controllers;
}