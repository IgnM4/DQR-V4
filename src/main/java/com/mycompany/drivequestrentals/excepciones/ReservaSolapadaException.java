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
 * Excepción que se lanza cuando se detecta un traslape de fechas
 * entre reservas para un mismo vehículo.
 */
public class ReservaSolapadaException extends Exception {

    /**
     * Constructor por defecto.
     */
    public ReservaSolapadaException() {
        super("La reserva se solapa con otra reserva existente para el vehículo.");
    }

    /**
     * Constructor con mensaje personalizado.
     * @param mensaje Mensaje detallado del error.
     */
    public ReservaSolapadaException(String mensaje) {
        super(mensaje);
    }
}
