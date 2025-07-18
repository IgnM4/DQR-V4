module com.mycompany.drivequestrentals {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.drivequestrentals to javafx.fxml;
    opens com.mycompany.drivequestrentals.interfaz to javafx.fxml;
    exports com.mycompany.drivequestrentals;
    exports com.mycompany.drivequestrentals.util;
    requires junit.jupiter.api;
}
