package com.mycompany.drivequestrentals.interfaz;

import com.mycompany.drivequestrentals.App;
import javafx.fxml.FXML;

/**
 * Controlador principal de la interfaz de usuario.
 * Permite la navegación hacia los distintos módulos del sistema:
 * registro de clientes, vehículos, arriendos, pagos y mantenimientos.
 */
public class PrimaryController {

    /**
     * Navega a la vista especificada.
     * @param vista nombre del archivo FXML (sin extensión).
     */
    private void cambiarVista(String vista) {
        try {
            App.setRoot(vista);
        } catch (Exception e) {
            System.err.println("Error al cambiar a la vista: " + vista);
            e.printStackTrace();
        }
    }

    /**
     * Redirige a la vista de registro de clientes.
     */
    @FXML
    private void irRegistroCliente() {
        cambiarVista("registroCliente");
    }

    /**
     * Redirige a la vista de registro de vehículos.
     */
    @FXML
    private void irRegistroVehiculo() {
        cambiarVista("registroVehiculo");
    }

    /**
     * Redirige a la vista de registro de arriendos.
     */
    @FXML
    private void irRegistroArriendo() {
        cambiarVista("registroArriendo");
    }

    /**
     * Redirige a la vista de registro de pagos.
     */
    @FXML
    private void irRegistroPago() {
        cambiarVista("registroPago");
    }

    /**
     * Redirige a la vista de registro de mantenimientos.
     */
    @FXML
    private void irRegistroMantenimiento() {
        cambiarVista("registroMantenimiento");
    }

    /**
     * Método para salir de la aplicación.
     */
    @FXML
    private void salirDelSistema() {
        System.exit(0);
    }
}
