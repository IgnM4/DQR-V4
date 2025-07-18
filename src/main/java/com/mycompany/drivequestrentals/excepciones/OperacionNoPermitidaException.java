/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.drivequestrentals.excepciones;

/**
 *
 * @author ignac
 */

/**
 * Se lanza cuando un usuario intenta realizar una acción sin permisos suficientes.
 */
public class OperacionNoPermitidaException extends Exception {
    public OperacionNoPermitidaException(String mensaje) {
        super(mensaje);
    }
    public OperacionNoPermitidaException() {
        super("No tiene permisos para realizar esta operación.");
    }
}
