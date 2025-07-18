package com.mycompany.drivequestrentals.modelo;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Clase que representa un mantenimiento realizado a un vehículo.
 * Contiene información sobre la fecha, costo, descripción, vehículo y kilometraje.
 */
public class Mantenimiento {

    private String id;
    private Vehiculo vehiculo;
    private String descripcion;
    private LocalDate fecha;
    private double costo;
    private double kilometraje;

    public Mantenimiento() {
    }

    public Mantenimiento(String id, Vehiculo vehiculo, String descripcion,
                         LocalDate fecha, double costo, double kilometraje) {
        setId(id);
        setVehiculo(vehiculo);
        setDescripcion(descripcion);
        setFecha(fecha);
        setCosto(costo);
        setKilometraje(kilometraje);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = validarTexto(id, "El ID del mantenimiento no puede estar vacío.");
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = Objects.requireNonNull(vehiculo, "Debe asociarse un vehículo al mantenimiento.");
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = validarTexto(descripcion, "La descripción del mantenimiento no puede estar vacía.");
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        if (fecha == null || fecha.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha del mantenimiento debe ser válida y no futura.");
        }
        this.fecha = fecha;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = validarPositivo(costo, "El costo del mantenimiento no puede ser negativo.");
    }

    public double getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(double kilometraje) {
        this.kilometraje = validarPositivo(kilometraje, "El kilometraje no puede ser negativo.");
    }

    /**
     * Representación legible del mantenimiento.
     */
    @Override
    public String toString() {
        return String.format("Mantenimiento [%s] - %s (%s) | Costo: $%.2f | Km: %.0f",
                id, descripcion, fecha, costo, kilometraje);
    }

    /**
     * Comparación basada en ID.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mantenimiento that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // ===== MÉTODOS DE VALIDACIÓN PRIVADOS =====

    private String validarTexto(String texto, String mensajeError) {
        if (texto == null || texto.isBlank()) {
            throw new IllegalArgumentException(mensajeError);
        }
        return texto;
    }

    private double validarPositivo(double valor, String mensajeError) {
        if (valor < 0) {
            throw new IllegalArgumentException(mensajeError);
        }
        return valor;
    }
}
