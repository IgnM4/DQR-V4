package com.mycompany.drivequestrentals.modelo;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import com.mycompany.drivequestrentals.modelo.Vehiculo;

/**
 * Clase que representa una reserva futura de un vehículo por parte de un cliente.
 * Una reserva puede ser convertida en un arriendo efectivo al momento de entrega.
 */
public class Reserva {

    private String codigoReserva;
    private Cliente cliente;
    /** Vehículo asociado a la reserva. */
    private Vehiculo vehiculo;
    private String rutCliente;
    private String patenteVehiculo;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private boolean confirmada;

    /**
     * Constructor completo de la clase Reserva.
     *
     * @param codigoReserva Código único de la reserva.
     * @param rutCliente RUT del cliente.
     * @param patenteVehiculo Patente del vehículo reservado.
     * @param fechaInicio Fecha y hora de inicio.
     * @param fechaFin Fecha y hora de término.
     * @param confirmada Indica si la reserva está confirmada.
     */
    public Reserva(String codigoReserva, String rutCliente, String patenteVehiculo,
                   LocalDateTime fechaInicio, LocalDateTime fechaFin, boolean confirmada) {
        this.codigoReserva = codigoReserva;
        this.rutCliente = rutCliente;
        this.patenteVehiculo = patenteVehiculo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.confirmada = confirmada;
    }
    
        /**
     * Constructor que recibe directamente el cliente y el vehículo asociados.
     */
    public Reserva(String codigoReserva, Cliente cliente, Vehiculo vehiculo,
                   LocalDateTime fechaInicio, LocalDateTime fechaFin, boolean confirmada) {
        this(codigoReserva,
             cliente != null ? cliente.getRutOPasaporte() : null,
             vehiculo != null ? vehiculo.getPatente() : null,
             fechaInicio, fechaFin, confirmada);
        this.cliente = cliente;
        this.vehiculo = vehiculo;
    }


    /**
     * Constructor vacío necesario para frameworks o instanciación manual.
     */
    public Reserva() {
    }

    // ================== Getters y Setters ==================

    public String getCodigoReserva() {
        return codigoReserva;
    }

    public void setCodigoReserva(String codigoReserva) {
        this.codigoReserva = codigoReserva;
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

    public String getRutCliente() {
        return rutCliente;
    }

    public void setRutCliente(String rutCliente) {
        this.rutCliente = rutCliente;
    }

    public String getPatenteVehiculo() {
        return patenteVehiculo;
    }

    public void setPatenteVehiculo(String patenteVehiculo) {
        this.patenteVehiculo = patenteVehiculo;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }

    public boolean isConfirmada() {
        return confirmada;
    }

    public void setConfirmada(boolean confirmada) {
        this.confirmada = confirmada;
    }

    // ================== Validaciones ==================

    /**
     * Valida si esta reserva se solapa con otra (en base a la misma patente de vehículo).
     *
     * @param otraReserva Reserva a comparar.
     * @return true si hay traslape, false si no.
     */
    public boolean seSolapaCon(Reserva otraReserva) {
        return this.patenteVehiculo.equals(otraReserva.getPatenteVehiculo()) &&
               this.fechaInicio.isBefore(otraReserva.getFechaFin()) &&
               this.fechaFin.isAfter(otraReserva.getFechaInicio());
    }

    // ================== Métodos sobreescritos ==================

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Reserva)) return false;
        Reserva other = (Reserva) obj;
        return Objects.equals(this.codigoReserva, other.codigoReserva);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoReserva);
    }

    @Override
    public String toString() {
        return String.format("Reserva[codigo=%s, rutCliente=%s, patente=%s, desde=%s, hasta=%s, confirmada=%s]",
                codigoReserva, rutCliente, patenteVehiculo, fechaInicio, fechaFin, confirmada);
    }

   // ================== Métodos adicionales ==================

    /**
        * Calcula el costo estimado de la reserva según los días y el precio diario del vehículo.
     * Si falta algún dato esencial, retorna 0.
     */
    public double getPrecioFinal() {
        if (vehiculo == null || fechaInicio == null || fechaFin == null) {
            return 0.0;
        }
        long dias = ChronoUnit.DAYS.between(fechaInicio, fechaFin);
        if (dias <= 0) {
            dias = 1;
        }
        return dias * vehiculo.getPrecioDiario();
    }
}
