module lab2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires transitive javafx.graphics;
	requires transitive java.desktop;
    
	
    opens com.project.lab2.controllers;
    opens com.project.lab2.dao;
    opens com.project.lab2.models;
    exports com.project.lab2.controllers;
    exports com.project.lab2.dao;
    exports com.project.lab2.models;
}
