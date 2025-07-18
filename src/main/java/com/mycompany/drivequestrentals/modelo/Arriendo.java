package com.mycompany.drivequestrentals.modelo;

import java.time.temporal.ChronoUnit;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * Clase que representa un arriendo de vehículo por parte de un cliente.
 */
public class Arriendo {

    private String id;
    private Cliente cliente;
    private Vehiculo vehiculo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private double kilometrajeActual;
    private double montoTotal;
    private String contratoTipo; // Ej: Básico, Premium, Corporativo

    // Constructor vacío
    public Arriendo() {
    }

    // Constructor completo
    public Arriendo(String id, Cliente cliente, Vehiculo vehiculo,
                    LocalDate fechaInicio, LocalDate fechaFin,
                    double kilometrajeActual, double montoTotal,
                    String contratoTipo) {
        this.id = id;
        this.cliente = cliente;
        this.vehiculo = vehiculo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.kilometrajeActual = kilometrajeActual;
        this.montoTotal = montoTotal;
        this.contratoTipo = contratoTipo;
    }

    /**
     * Constructor simplificado para usar en el controlador.
     * Genera ID automáticamente y asigna valores iniciales por defecto.
     */
    public Arriendo(Cliente cliente, Vehiculo vehiculo,
                    LocalDate fechaInicio, LocalDate fechaFin) {
        this.id = UUID.randomUUID().toString();
        this.cliente = cliente;
        this.vehiculo = vehiculo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.kilometrajeActual = 0.0;
        this.montoTotal = 0.0;
        this.contratoTipo = "Básico"; // Puedes cambiar según lógica futura
    }

    // Getters y Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public double getKilometrajeActual() {
        return kilometrajeActual;
    }

    public void setKilometrajeActual(double kilometrajeActual) {
        this.kilometrajeActual = kilometrajeActual;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public String getContratoTipo() {
        return contratoTipo;
    }

    public void setContratoTipo(String contratoTipo) {
        this.contratoTipo = contratoTipo;
    }

    /**
     * Calcula la duración del arriendo en días.
     */
    public long getDuracionDias() {
        return fechaInicio != null && fechaFin != null
                ? java.time.temporal.ChronoUnit.DAYS.between(fechaInicio, fechaFin)
                : 0;
    }

    @Override
    public String toString() {
        return String.format("Arriendo [%s] - Cliente: %s, Vehículo: %s, Inicio: %s, Fin: %s, Total: $%.2f",
                id, cliente.getNombreCompleto(), vehiculo.getPatente(),
                fechaInicio, fechaFin, montoTotal);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Arriendo)) return false;
        Arriendo that = (Arriendo) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    /**
 * Calcula el monto total del arriendo sin impuestos.
 * Se basa en la cantidad de días entre fechaInicio y fechaFin, multiplicado por el precio diario del vehículo.
 *
 * @return Monto total sin IVA.
 */
public double calcularMontoTotal() {
    long dias = ChronoUnit.DAYS.between(fechaInicio, fechaFin);
    if (dias == 0) {
        dias = 1; // mínimo 1 día
    }
    return dias * vehiculo.getPrecioDiario();
}
}
