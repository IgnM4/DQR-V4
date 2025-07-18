package com.mycompany.drivequestrentals.modelo;

import java.util.Objects;
import java.util.UUID;

/**
 * Clase que representa un cliente de la empresa DriveQuest Rentals.
 * Incluye validaciones básicas y generación automática de identificador.
 */
public class Cliente {

    private String id;
    private String nombreCompleto;
    private String rutOPasaporte;
    private String telefono;
    private String correo;
    private String domicilio;
    private String numeroTarjetaCredito;

    /**
     * Constructor completo con todos los campos del cliente.
     */
    public Cliente(String id, String nombreCompleto, String rutOPasaporte, String telefono,
                   String correo, String domicilio, String numeroTarjetaCredito) {
        this.id = validarId(id);
        this.nombreCompleto = validarTexto(nombreCompleto, "Nombre completo");
        this.rutOPasaporte = validarTexto(rutOPasaporte, "RUT o Pasaporte");
        this.telefono = validarTexto(telefono, "Teléfono");
        this.correo = validarTexto(correo, "Correo");
        this.domicilio = validarTexto(domicilio, "Domicilio");
        this.numeroTarjetaCredito = numeroTarjetaCredito; // Puede ser null inicialmente
    }

    /**
     * Constructor que genera automáticamente el ID.
     */
    public Cliente(String nombreCompleto, String rutOPasaporte, String telefono,
                   String correo, String domicilio) {
        this(UUID.randomUUID().toString(), nombreCompleto, rutOPasaporte, telefono, correo, domicilio, null);
    }

    /**
     * Constructor vacío requerido por frameworks o para inicialización posterior.
     */
    public Cliente() {
    }

    // ========================== MÉTODOS GETTER Y SETTER ===========================

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = validarId(id);
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }
        /**
     * Alias para compatibilidad con código que usa getNombre().
     */
    public String getNombre() {
        return getNombreCompleto();
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = validarTexto(nombreCompleto, "Nombre completo");
    }

    public String getRutOPasaporte() {
        return rutOPasaporte;
    }

    public void setRutOPasaporte(String rutOPasaporte) {
        this.rutOPasaporte = validarTexto(rutOPasaporte, "RUT o Pasaporte");
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = validarTexto(telefono, "Teléfono");
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = validarTexto(correo, "Correo");
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = validarTexto(domicilio, "Domicilio");
    }

    public String getNumeroTarjetaCredito() {
        return numeroTarjetaCredito;
    }

    public void setNumeroTarjetaCredito(String numeroTarjetaCredito) {
        this.numeroTarjetaCredito = numeroTarjetaCredito;
    }

    // ============================ MÉTODOS AUXILIARES =============================

    /**
     * Valida que un campo de texto no esté vacío o nulo.
     */
    private String validarTexto(String valor, String campo) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException("El campo '" + campo + "' no puede estar vacío.");
        }
        return valor.trim();
    }

    /**
     * Valida que el ID no sea nulo ni vacío.
     */
    private String validarId(String id) {
        if (id == null || id.trim().isEmpty()) {
            return UUID.randomUUID().toString();
        }
        return id;
    }

    // ============================== SOBRESCRITURAS ===============================

    @Override
    public String toString() {
        return String.format("Cliente: %s | RUT/Pasaporte: %s | Tel: %s | Correo: %s",
                nombreCompleto, rutOPasaporte, telefono, correo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente)) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(rutOPasaporte, cliente.rutOPasaporte);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rutOPasaporte);
    }
}
