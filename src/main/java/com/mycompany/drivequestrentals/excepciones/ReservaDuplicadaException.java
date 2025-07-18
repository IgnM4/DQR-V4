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
 * Excepción que se lanza cuando se intenta registrar una reserva que ya existe
 * para el mismo vehículo, cliente y rango de fechas.
 */
public class ReservaDuplicadaException extends Exception {

    /**
     * Constructor por defecto.
     */
    public ReservaDuplicadaException() {
        super("La reserva ya existe. No se puede registrar una duplicada.");
    }

    /**
     * Constructor con mensaje personalizado.
     * @param mensaje Mensaje detallado del error.
     */
    public ReservaDuplicadaException(String mensaje) {
        super(mensaje);
    }
}
