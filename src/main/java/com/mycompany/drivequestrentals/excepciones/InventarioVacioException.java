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
 * Se lanza cuando no hay vehículos disponibles en el inventario.
 */
public class InventarioVacioException extends Exception {
    public InventarioVacioException(String mensaje) {
        super(mensaje);
    }
    public InventarioVacioException() {
        super("No hay vehículos disponibles en el inventario.");
    }
}

