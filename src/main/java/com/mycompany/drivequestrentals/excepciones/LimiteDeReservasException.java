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
 * Se lanza cuando un cliente supera el límite de reservas simultáneas.
 */
public class LimiteDeReservasException extends Exception {
    public LimiteDeReservasException(String mensaje) {
        super(mensaje);
    }
    public LimiteDeReservasException() {
        super("Se ha alcanzado el límite de reservas permitidas para este cliente.");
    }
}
