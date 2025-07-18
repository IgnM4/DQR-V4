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
 * Se lanza cuando un archivo necesario no es encontrado en disco.
 */
public class ArchivoNoEncontradoException extends Exception {
    public ArchivoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
    public ArchivoNoEncontradoException() {
        super("Archivo requerido no encontrado.");
    }
}
