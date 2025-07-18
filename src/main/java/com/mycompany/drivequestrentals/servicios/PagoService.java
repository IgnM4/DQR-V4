package com.mycompany.drivequestrentals.servicios;

import com.mycompany.drivequestrentals.modelo.Arriendo;
import com.mycompany.drivequestrentals.modelo.Pago;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Servicio encargado de registrar y gestionar los pagos de arriendos.
 */
public class PagoService {

    private static final double IVA = 0.19;
    private final List<Pago> pagosRegistrados = new ArrayList<>();

    /**
     * Registra un nuevo pago asociado a un arriendo.
     * Calcula automáticamente el IVA y el monto total.
     *
     * @param arriendo   Arriendo al que se asocia el pago.
     * @param montoNeto  Monto neto (sin IVA).
     * @param metodoPago Método de pago utilizado (ej: tarjeta, efectivo).
     * @return Objeto Pago creado y registrado.
     * @throws IllegalArgumentException si los parámetros son inválidos.
     */
    public Pago registrarPago(Arriendo arriendo, double montoNeto, String metodoPago) {
        validarDatosPago(arriendo, montoNeto, metodoPago);

        String id = generarIdUnico();
        double montoIVA = calcularIVA(montoNeto);
        double total = montoNeto + montoIVA;
        LocalDate fecha = LocalDate.now();

        Pago nuevoPago = new Pago(id, arriendo, montoNeto, montoIVA, total, fecha, metodoPago.trim());
        pagosRegistrados.add(nuevoPago);
        return nuevoPago;
    }

    /**
     * Valida los datos necesarios para registrar un pago.
     */
    private void validarDatosPago(Arriendo arriendo, double montoNeto, String metodoPago) {
        if (arriendo == null) {
            throw new IllegalArgumentException("El arriendo no puede ser nulo.");
        }
        if (montoNeto <= 0) {
            throw new IllegalArgumentException("El monto neto debe ser mayor a cero.");
        }
        if (metodoPago == null || metodoPago.isBlank()) {
            throw new IllegalArgumentException("El método de pago no puede estar vacío.");
        }
    }

    /**
     * Calcula el IVA correspondiente al monto neto.
     */
    private double calcularIVA(double montoNeto) {
        return montoNeto * IVA;
    }

    /**
     * Genera un identificador único para el pago.
     */
    private String generarIdUnico() {
        return "PAGO-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    /**
     * Devuelve una copia de la lista de pagos registrados.
     *
     * @return Lista inmutable de pagos.
     */
    public List<Pago> obtenerPagos() {
        return Collections.unmodifiableList(new ArrayList<>(pagosRegistrados));
    }

    /**
     * Calcula el total bruto pagado (monto neto + IVA).
     *
     * @return Total acumulado de todos los pagos.
     */
    public double calcularTotalPagado() {
        return pagosRegistrados.stream()
                .mapToDouble(Pago::getTotalPago)
                .sum();
    }

    /**
     * Busca y devuelve una lista de pagos según el método de pago especificado.
     *
     * @param metodo Método de pago buscado (insensible a mayúsculas).
     * @return Lista de pagos que coinciden con el método.
     */
    public List<Pago> buscarPorMetodo(String metodo) {
        if (metodo == null || metodo.isBlank()) return Collections.emptyList();
        return pagosRegistrados.stream()
                .filter(p -> p.getMetodoPago().equalsIgnoreCase(metodo.trim()))
                .collect(Collectors.toList());
    }
}
