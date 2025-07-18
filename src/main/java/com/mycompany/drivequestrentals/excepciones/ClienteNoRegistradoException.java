package com.mycompany.drivequestrentals.excepciones;

/**
 * Se lanza si se intenta operar con un cliente no registrado.
 */
public class ClienteNoRegistradoException extends Exception {
    public ClienteNoRegistradoException(String mensaje) {
        super(mensaje);
    }
    public ClienteNoRegistradoException() {
        super("Cliente no registrado en el sistema.");
    }
}
