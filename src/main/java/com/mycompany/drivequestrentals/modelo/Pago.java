package com.mycompany.drivequestrentals.modelo;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Clase que representa un pago realizado por el arriendo de un vehículo.
 */
public class Pago {

    private String id;
    private Arriendo arriendo;
    private double montoNeto;
    private double montoIVA;
    private double totalPago;
    private LocalDate fechaPago;
    private String metodoPago; // Efectivo, Transferencia, Tarjeta

    public Pago() {
    }

    /**
     * Constructor completo que inicializa un pago con validaciones.
     */
    public Pago(String id, Arriendo arriendo, double montoNeto, double montoIVA,
                double totalPago, LocalDate fechaPago, String metodoPago) {
        setId(id);
        setArriendo(arriendo);
        setMontoNeto(montoNeto);
        setMontoIVA(montoIVA);
        setTotalPago(totalPago);
        setFechaPago(fechaPago);
        setMetodoPago(metodoPago);
    }

    public String getId() {
        return id;
    }

    /**
     * Establece el ID del pago, validando que no sea nulo o vacío.
     */
    public void setId(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("El ID del pago no puede estar vacío.");
        }
        this.id = id;
    }

    public Arriendo getArriendo() {
        return arriendo;
    }

    /**
     * Asocia un arriendo al pago, validando que no sea nulo.
     */
    public void setArriendo(Arriendo arriendo) {
        if (arriendo == null) {
            throw new IllegalArgumentException("Debe asociarse un arriendo al pago.");
        }
        this.arriendo = arriendo;
    }

    public double getMontoNeto() {
        return montoNeto;
    }

    /**
     * Establece el monto neto del pago, validando que no sea negativo.
     */
    public void setMontoNeto(double montoNeto) {
        if (montoNeto < 0) {
            throw new IllegalArgumentException("El monto neto no puede ser negativo.");
        }
        this.montoNeto = montoNeto;
    }

    public double getMontoIVA() {
        return montoIVA;
    }

    /**
     * Establece el monto del IVA del pago, validando que no sea negativo.
     */
    public void setMontoIVA(double montoIVA) {
        if (montoIVA < 0) {
            throw new IllegalArgumentException("El monto del IVA no puede ser negativo.");
        }
        this.montoIVA = montoIVA;
    }

    public double getTotalPago() {
        return totalPago;
    }

    /**
     * Establece el total pagado, validando que no sea negativo.
     */
    public void setTotalPago(double totalPago) {
        if (totalPago < 0) {
            throw new IllegalArgumentException("El total del pago no puede ser negativo.");
        }
        this.totalPago = totalPago;
    }

    public LocalDate getFechaPago() {
        return fechaPago;
    }

    /**
     * Establece la fecha del pago, validando que no sea nula ni futura.
     */
    public void setFechaPago(LocalDate fechaPago) {
        if (fechaPago == null || fechaPago.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha del pago debe ser válida y no futura.");
        }
        this.fechaPago = fechaPago;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    /**
     * Establece el método de pago, validando que no sea nulo ni vacío.
     */
    public void setMetodoPago(String metodoPago) {
        if (metodoPago == null || metodoPago.isBlank()) {
            throw new IllegalArgumentException("El método de pago no puede estar vacío.");
        }
        this.metodoPago = metodoPago;
    }

    @Override
    public String toString() {
        return String.format("Pago [%s] - Total: $%.2f | Fecha: %s | Método: %s | Cliente: %s",
                id, totalPago, fechaPago, metodoPago,
                (arriendo != null && arriendo.getCliente() != null)
                    ? arriendo.getCliente().getNombreCompleto()
                    : "N/A");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pago)) return false;
        Pago pago = (Pago) o;
        return Objects.equals(id, pago.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
