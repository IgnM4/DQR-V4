package com.mycompany.drivequestrentals.excepciones;

/**
 * Se lanza cuando hay errores de validación de datos ingresados.
 */
public class ValidacionException extends Exception {
    public ValidacionException(String mensaje) {
        super(mensaje);
    }
}
