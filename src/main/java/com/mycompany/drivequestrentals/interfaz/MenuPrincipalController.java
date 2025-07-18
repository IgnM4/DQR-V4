package com.mycompany.drivequestrentals.interfaz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import com.mycompany.drivequestrentals.App;

/**
 * Controlador para el menú principal de navegación.
 * Permite acceder a los distintos módulos del sistema.
 */
public class MenuPrincipalController {

    @FXML
    private Button btnClientes, btnVehiculos, btnArriendos, btnPagos, btnMantenimientos;

    /**
     * Método genérico para cambiar de vista.
     * @param vista Nombre del archivo FXML (sin extensión .fxml)
     */
    private void cambiarVista(String vista) {
        try {
            App.setRoot(vista);
        } catch (Exception e) {
            System.err.println("Error al intentar cambiar a la vista: " + vista);
            e.printStackTrace();
        }
    }

    @FXML
    private void irAClientes(ActionEvent event) {
        cambiarVista("registroCliente");
    }

    @FXML
    private void irAVehiculos(ActionEvent event) {
        cambiarVista("registroVehiculo");
    }

    @FXML
    private void irAArriendos(ActionEvent event) {
        cambiarVista("registroArriendo");
    }

    @FXML
    private void irAPagos(ActionEvent event) {
        cambiarVista("registroPago");
    }

    @FXML
    private void irAMantenimientos(ActionEvent event) {
        cambiarVista("registroMantenimiento");
    }
}
