package com.mycompany.drivequestrentals.excepciones;

/**
 * Se lanza cuando no se encuentra una reserva por su identificador.
 */
public class ReservaNoEncontradaException extends Exception {
    public ReservaNoEncontradaException(String mensaje) {
        super(mensaje);
    }
    public ReservaNoEncontradaException() {
        super("Reserva no encontrada.");
    }
}
