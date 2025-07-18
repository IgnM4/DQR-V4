package com.mycompany.drivequestrentals.excepciones;

/**
 * Se lanza cuando un vehículo ya está reservado o fuera de servicio.
 */
public class VehiculoNoDisponibleException extends Exception {
    public VehiculoNoDisponibleException(String mensaje) {
        super(mensaje);
    }
    public VehiculoNoDisponibleException() {
        super("El vehículo no está disponible.");
    }
}
