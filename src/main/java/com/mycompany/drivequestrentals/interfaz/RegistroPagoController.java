package com.mycompany.drivequestrentals.interfaz;

import com.mycompany.drivequestrentals.App;
import com.mycompany.drivequestrentals.modelo.Arriendo;
import com.mycompany.drivequestrentals.modelo.Pago;
import com.mycompany.drivequestrentals.servicios.ArriendoService;
import com.mycompany.drivequestrentals.servicios.PagoService;
import com.mycompany.drivequestrentals.ServiceManager;
import com.mycompany.drivequestrentals.util.AlertaUtil;
import com.mycompany.drivequestrentals.util.IdUtil;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;

/**
 * Controlador para la vista de registro de pagos.
 * Gestiona la validación y creación de pagos asociados a arriendos activos.
 */
public class RegistroPagoController {

    @FXML
    private TextField txtRutCliente;

    @FXML
    private TextField txtMetodoPago;

    @FXML
    private DatePicker datePago;

    private final ArriendoService arriendoService = ServiceManager.getArriendoService();
    private final PagoService pagoService = ServiceManager.getPagoService();

    /**
     * Acción principal para registrar un pago de arriendo.
     * Valida los campos requeridos y verifica si el cliente posee un arriendo activo.
     */
    @FXML
    private void registrarPago() {
        String rut = txtRutCliente.getText().trim();
        String metodoPago = txtMetodoPago.getText().trim();
        LocalDate fecha = datePago.getValue();

        if (!validarCampos(rut, metodoPago, fecha)) return;

        Arriendo arriendo = arriendoService.buscarArriendoActivoPorCliente(rut);
        if (arriendo == null) {
            mostrarAlerta("No existe un arriendo activo para el cliente ingresado.");
            return;
        }

        double montoNeto = arriendo.calcularMontoTotal();
        double iva = calcularIVA(montoNeto);
        double total = montoNeto + iva;

        Pago nuevoPago = new Pago(
                generarIdUnico(),
                arriendo,
                montoNeto,
                iva,
                total,
                fecha,
                metodoPago
        );

        pagoService.registrarPago(arriendo, montoNeto, metodoPago);
        mostrarAlerta("Pago registrado exitosamente.");
        limpiarCampos();
    }

    /**
     * Valida que todos los campos obligatorios estén correctamente completados.
     */
    private boolean validarCampos(String rut, String metodoPago, LocalDate fecha) {
        if (rut.isEmpty() || metodoPago.isEmpty() || fecha == null) {
            mostrarAlerta("Todos los campos son obligatorios.");
            return false;
        }
        return true;
    }

    /**
     * Calcula el IVA correspondiente (19%) al monto neto.
     */
    private double calcularIVA(double montoNeto) {
        return montoNeto * 0.19;
    }

    /**
     * Limpia los campos del formulario de registro de pagos.
     */
    private void limpiarCampos() {
        txtRutCliente.clear();
        txtMetodoPago.clear();
        datePago.setValue(null);
    }

    /**
     * Muestra una alerta informativa al usuario.
     */
    private void mostrarAlerta(String mensaje) {
        AlertaUtil.mostrarAlerta(mensaje);
    }

    /**
     * Genera un identificador único con prefijo para el pago.
     */
    private String generarIdUnico() {
        return IdUtil.generarIdConPrefijo("PAGO-");
    }

    /**
     * Regresa a la vista del menú principal.
     */
    @FXML
    private void volverAlMenu() {
        try {
            App.setRoot("menuPrincipal");
        } catch (Exception e) {
            mostrarAlerta("Error al volver al menú principal.");
            e.printStackTrace();
        }
    }
}
