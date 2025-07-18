/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.drivequestrentals.utilidades;

/**
 *
 * @author ignac
 */
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import org.junit.jupiter.api.Test;

public class ValidadorTest {

    @Test
    void testLeerRutValido() {
        String input = "abc\n12.345.678-5\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
        String rut = Validador.leerRutValido(scanner);
        assertEquals("12.345.678-5", rut);
    }

    @Test
    void testLeerTextoNoVacio() {
        String input = "   \nHola Mundo\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
        String texto = Validador.leerTextoNoVacio(scanner, "Ingrese texto: ");
        assertEquals("Hola Mundo", texto);
    }
}
