package com.mycompany.drivequestrentals.interfaz;

import com.mycompany.drivequestrentals.App;
import com.mycompany.drivequestrentals.modelo.Arriendo;
import com.mycompany.drivequestrentals.modelo.Cliente;
import com.mycompany.drivequestrentals.modelo.Vehiculo;
import com.mycompany.drivequestrentals.servicios.ArriendoService;
import com.mycompany.drivequestrentals.servicios.ClienteService;
import com.mycompany.drivequestrentals.servicios.FlotaVehiculosService;
import com.mycompany.drivequestrentals.ServiceManager;
import com.mycompany.drivequestrentals.util.AlertaUtil;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;

/**
 * Controlador de la vista de registro de arriendos.
 * Gestiona el ingreso y validación de datos para crear nuevos arriendos en el sistema.
 */
public class RegistroArriendoController {

    @FXML
    private TextField txtRutCliente;
    @FXML
    private TextField txtPatenteVehiculo;
    @FXML
    private DatePicker dateInicio;
    @FXML
    private DatePicker dateFin;

    private final ClienteService clienteService = ServiceManager.getClienteService();
    private final FlotaVehiculosService vehiculoService = ServiceManager.getFlotaService();
    private final ArriendoService arriendoService = ServiceManager.getArriendoService();

    /**
     * Registra un arriendo tras validar campos y disponibilidad.
     */
    @FXML
    private void registrarArriendo() {
        String rut = txtRutCliente.getText().trim();
        String patente = txtPatenteVehiculo.getText().trim();
        LocalDate fechaInicio = dateInicio.getValue();
        LocalDate fechaFin = dateFin.getValue();

        if (!camposValidos(rut, patente, fechaInicio, fechaFin)) return;

        Cliente cliente = clienteService.buscarPorRutOPasaporte(rut);
        if (cliente == null) {
            mostrarAlerta("Cliente no encontrado.");
            return;
        }

        Vehiculo vehiculo = vehiculoService.buscarPorPatente(patente);
        if (vehiculo == null) {
            mostrarAlerta("Vehículo no encontrado.");
            return;
        }

        if (!vehiculo.isDisponible()) {
            mostrarAlerta("El vehículo no está disponible.");
            return;
        }

        try {
            Arriendo arriendo = new Arriendo(cliente, vehiculo, fechaInicio, fechaFin);
            arriendoService.registrarArriendo(arriendo);
            vehiculo.setDisponible(false);
            mostrarAlerta("Arriendo registrado correctamente.");
            limpiarCampos();
        } catch (Exception e) {
            mostrarAlerta("Error al registrar arriendo: " + e.getMessage());
        }
    }

    /**
     * Valida los campos del formulario de arriendo.
     */
    private boolean camposValidos(String rut, String patente, LocalDate inicio, LocalDate fin) {
        if (rut.isEmpty() || patente.isEmpty() || inicio == null || fin == null) {
            mostrarAlerta("Todos los campos son obligatorios.");
            return false;
        }
        if (fin.isBefore(inicio)) {
            mostrarAlerta("La fecha de término no puede ser anterior a la fecha de inicio.");
            return false;
        }
        return true;
    }

    /**
     * Limpia los campos del formulario de arriendo.
     */
    private void limpiarCampos() {
        txtRutCliente.clear();
        txtPatenteVehiculo.clear();
        dateInicio.setValue(null);
        dateFin.setValue(null);
    }

    /**
     * Muestra una alerta informativa.
     */
    private void mostrarAlerta(String mensaje) {
        AlertaUtil.mostrarAlerta(mensaje);
    }

    /**
     * Vuelve a la vista del menú principal.
     */
    @FXML
    private void volverAlMenu() {
        try {
            App.setRoot("menuPrincipal");
        } catch (Exception e) {
            mostrarAlerta("Error al volver al menú: " + e.getMessage());
        }
    }
}
