package com.mycompany.drivequestrentals.modelo;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Clase abstracta que representa un vehículo genérico dentro del sistema de arriendo.
 * Esta clase sirve como base para las subclases VehiculoCarga y VehiculoPasajeros.
 * Contiene atributos comunes como patente, marca, modelo, año, estado, foto y precio diario.
 */
public abstract class Vehiculo {

    protected String id;
    protected String patente;
    protected String marca;
    protected String modelo;
    protected int anio;
    protected String estado; // Disponible, Arrendado, En Mantención
    protected String fotoRuta;
    private double precioDiario;

    /**
     * Constructor de vehículo.
     */
    public Vehiculo(String id, String patente, String marca, String modelo, int anio, String estado, String fotoRuta) {
        setId(id);
        setPatente(patente);
        setMarca(marca);
        setModelo(modelo);
        setAnio(anio);
        setEstado(estado);
        setFotoRuta(fotoRuta);
    }
    public Vehiculo() {
        // Constructor vacío necesario para llamadas a super()
    }
    // ==== Getters y Setters con validaciones ====

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("El ID del vehículo no puede estar vacío.");
        }
        this.id = id;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        if (patente == null || patente.isBlank()) {
            throw new IllegalArgumentException("La patente del vehículo no puede estar vacía.");
        }
        this.patente = patente.toUpperCase();
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        if (marca == null || marca.isBlank()) {
            throw new IllegalArgumentException("La marca no puede estar vacía.");
        }
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        if (modelo == null || modelo.isBlank()) {
            throw new IllegalArgumentException("El modelo no puede estar vacío.");
        }
        this.modelo = modelo;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        int anioActual = java.time.Year.now().getValue();
        if (anio < 1900 || anio > anioActual) {
            throw new IllegalArgumentException("El año debe estar entre 1900 y el actual (" + anioActual + ").");
        }
        this.anio = anio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        if (estado == null || estado.isBlank()) {
            throw new IllegalArgumentException("El estado del vehículo no puede estar vacío.");
        }
        this.estado = estado;
    }

    public String getFotoRuta() {
        return fotoRuta;
    }

    public void setFotoRuta(String fotoRuta) {
        this.fotoRuta = fotoRuta;
    }

    public double getPrecioDiario() {
        return precioDiario;
    }

    public void setPrecioDiario(double precioDiario) {
        if (precioDiario < 0) {
            throw new IllegalArgumentException("El precio diario no puede ser negativo.");
        }
        this.precioDiario = precioDiario;
    }

    // ==== Métodos funcionales ====

    /**
     * Indica si el vehículo está disponible para arriendo.
     * @return true si el estado es "Disponible".
     */
    public boolean isDisponible() {
        return "Disponible".equalsIgnoreCase(this.estado);
    }

    /**
     * Cambia el estado de disponibilidad del vehículo.
     * @param disponible true para marcar como "Disponible", false como "Arrendado".
     */
    public void setDisponible(boolean disponible) {
        this.estado = disponible ? "Disponible" : "Arrendado";
    }

    /**
     * Método abstracto que obliga a las subclases a implementar la visualización de los datos del vehículo.
     * @return String con información detallada.
     */
    public abstract String mostrarDatos();

    /**
     * Método de placeholder para calcular el próximo mantenimiento.
     * Debe ser sobrescrito en subclases si aplica.
     * @return LocalDate con la fecha del próximo mantenimiento.
     */
    public LocalDate getProximoMantenimiento() {
        throw new UnsupportedOperationException("Este método debe ser implementado en una subclase.");
    }

    // ==== Métodos de igualdad ====

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Vehiculo)) return false;
        Vehiculo other = (Vehiculo) obj;
        return Objects.equals(this.patente, other.patente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(patente);
    }
}
