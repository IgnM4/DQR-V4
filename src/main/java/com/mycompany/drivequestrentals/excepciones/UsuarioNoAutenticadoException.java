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
 * Se lanza cuando se requiere autenticación y el usuario no ha iniciado sesión.
 */
public class UsuarioNoAutenticadoException extends Exception {
    public UsuarioNoAutenticadoException(String mensaje) {
        super(mensaje);
    }
    public UsuarioNoAutenticadoException() {
        super("Usuario no autenticado. Inicie sesión para continuar.");
    }
}
