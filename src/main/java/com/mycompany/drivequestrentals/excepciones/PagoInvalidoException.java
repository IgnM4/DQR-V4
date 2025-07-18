/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.drivequestrentals.excepciones;

/**
 *
 * @author ignac

/**
 * Se lanza cuando el monto de pago es incorrecto o insuficiente.
 */
public class PagoInvalidoException extends Exception {
    public PagoInvalidoException(String mensaje) {
        super(mensaje);
    }
    public PagoInvalidoException() {
        super("El pago ingresado es inválido.");
    }
}
