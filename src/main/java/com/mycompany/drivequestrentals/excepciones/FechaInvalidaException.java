/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.drivequestrentals.excepciones;


/**
 * Se lanza cuando las fechas ingresadas no tienen sentido lógico.
 */
public class FechaInvalidaException extends Exception {
    public FechaInvalidaException(String mensaje) {
        super(mensaje);
    }
    public FechaInvalidaException() {
        super("Fechas inválidas: verifique el rango seleccionado.");
    }
}
