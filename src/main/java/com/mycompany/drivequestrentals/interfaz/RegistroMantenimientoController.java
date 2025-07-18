package com.mycompany.drivequestrentals.interfaz;

import com.mycompany.drivequestrentals.App;
import com.mycompany.drivequestrentals.modelo.Mantenimiento;
import com.mycompany.drivequestrentals.modelo.Vehiculo;
import com.mycompany.drivequestrentals.servicios.FlotaVehiculosService;
import com.mycompany.drivequestrentals.servicios.MantenimientoService;
import com.mycompany.drivequestrentals.ServiceManager;
import com.mycompany.drivequestrentals.util.AlertaUtil;
import com.mycompany.drivequestrentals.util.IdUtil;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;

/**
 * Controlador para registrar mantenimientos de vehículos.
 * Valida los datos ingresados, asocia el mantenimiento al vehículo y lo guarda en el sistema.
 */
public class RegistroMantenimientoController {

    @FXML
    private TextField txtPatenteVehiculo;
    @FXML
    private TextField txtDescripcion;
    @FXML
    private DatePicker dateMantenimiento;
    @FXML
    private TextField txtCosto;
    @FXML
    private TextField txtKilometraje;

    private final FlotaVehiculosService vehiculoService = ServiceManager.getFlotaService();
    private final MantenimientoService mantenimientoService = ServiceManager.getMantenimientoService();

    /**
     * Maneja el evento de registro de mantenimiento.
     * Valida los datos y registra el mantenimiento si todo es correcto.
     */
    @FXML
    private void registrarMantenimiento() {
        if (!validarCampos()) return;

        String patente = txtPatenteVehiculo.getText().trim();
        String descripcion = txtDescripcion.getText().trim();
        LocalDate fecha = dateMantenimiento.getValue();
        double costo = Double.parseDouble(txtCosto.getText().trim());
        double kilometraje = Double.parseDouble(txtKilometraje.getText().trim());

        Vehiculo vehiculo = vehiculoService.buscarPorPatente(patente);
        if (vehiculo == null) {
            mostrarAlerta("No se encontró un vehículo con la patente ingresada.");
            return;
        }

        Mantenimiento mantenimiento = new Mantenimiento(
                generarIdUnico(),
                vehiculo,
                descripcion,
                fecha,
                costo,
                kilometraje
        );

        mantenimientoService.registrarMantenimiento(mantenimiento);
        mostrarAlerta("Mantenimiento registrado correctamente.");
        limpiarCampos();
    }

    /**
     * Valida que todos los campos estén completos y que costo/kilometraje sean numéricos.
     * @return true si los datos son válidos, false si hay errores.
     */
    private boolean validarCampos() {
        String patente = txtPatenteVehiculo.getText().trim();
        String descripcion = txtDescripcion.getText().trim();
        LocalDate fecha = dateMantenimiento.getValue();
        String costoTexto = txtCosto.getText().trim();
        String kmTexto = txtKilometraje.getText().trim();

        if (patente.isEmpty() || descripcion.isEmpty() || fecha == null ||
            costoTexto.isEmpty() || kmTexto.isEmpty()) {
            mostrarAlerta("Debe completar todos los campos.");
            return false;
        }

        try {
            Double.parseDouble(costoTexto);
            Double.parseDouble(kmTexto);
        } catch (NumberFormatException e) {
            mostrarAlerta("El costo y el kilometraje deben ser numéricos.");
            return false;
        }

        return true;
    }

    /**
     * Limpia todos los campos del formulario.
     */
    private void limpiarCampos() {
        txtPatenteVehiculo.clear();
        txtDescripcion.clear();
        dateMantenimiento.setValue(null);
        txtCosto.clear();
        txtKilometraje.clear();
    }

    /**
     * Muestra una alerta informativa en pantalla.
     * @param mensaje Texto a mostrar.
     */
    private void mostrarAlerta(String mensaje) {
        AlertaUtil.mostrarAlerta(mensaje);
    }

    /**
     * Genera un identificador único para el mantenimiento.
     * @return ID único como cadena.
     */
    private String generarIdUnico() {
        return IdUtil.generarIdConPrefijo("MTTO-");
    }

    /**
     * Navega al menú principal.
     */
    @FXML
    private void volverAlMenu() {
        try {
            App.setRoot("menuPrincipal");
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error al regresar al menú.");
        }
    }
}
