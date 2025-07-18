package com.mycompany.drivequestrentals.utilidades;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Clase utilitaria para validaciones comunes y entradas seguras por consola.
 * Proporciona métodos estáticos para validar RUT chileno, correos electrónicos,
 * lectura segura de enteros y doubles desde consola, entre otros.
 */
public class Validador {

    /**
     * Valida si un RUT chileno tiene un formato correcto y dígito verificador válido.
     *
     * @param rut RUT en formato con o sin guion (ej: 12345678K o 12.345.678-K).
     * @return true si el RUT es válido, false en caso contrario.
     */
    public static boolean validarRut(String rut) {
        if (rut == null) return false;

        rut = rut.replace(".", "").replace("-", "").toUpperCase();

        if (!rut.matches("\\d{7,8}[0-9K]")) return false;

        String cuerpo = rut.substring(0, rut.length() - 1);
        char dv = rut.charAt(rut.length() - 1);

        int suma = 0;
        int multiplicador = 2;
        for (int i = cuerpo.length() - 1; i >= 0; i--) {
            suma += Character.getNumericValue(cuerpo.charAt(i)) * multiplicador;
            multiplicador = (multiplicador == 7) ? 2 : multiplicador + 1;
        }

        int resto = suma % 11;
        char dvEsperado = (resto == 1) ? 'K' : (resto == 0) ? '0' : (char) ('0' + (11 - resto));

        return dv == dvEsperado;
    }

    /**
     * Lee un número entero dentro de un rango específico desde consola.
     *
     * @param scanner Scanner activo para entrada.
     * @param min     Valor mínimo aceptado.
     * @param max     Valor máximo aceptado.
     * @return El número entero ingresado dentro del rango.
     */
    public static int leerEnteroConRango(Scanner scanner, int min, int max) {
        int numero;
        while (true) {
            try {
                numero = Integer.parseInt(scanner.nextLine());
                if (numero < min || numero > max) {
                    System.out.print("❌ Valor fuera de rango (" + min + " - " + max + "). Intente nuevamente: ");
                } else {
                    return numero;
                }
            } catch (NumberFormatException e) {
                System.out.print("❌ Entrada no válida. Ingrese un número entero: ");
            }
        }
    }

    /**
     * Lee un número decimal (double) positivo desde consola.
     *
     * @param scanner Scanner activo para entrada.
     * @return Número double mayor que cero.
     */
    public static double leerDoublePositivo(Scanner scanner) {
        double numero;
        while (true) {
            try {
                numero = Double.parseDouble(scanner.nextLine());
                if (numero <= 0) {
                    System.out.print("❌ El valor debe ser mayor que cero. Intente nuevamente: ");
                } else {
                    return numero;
                }
            } catch (NumberFormatException e) {
                System.out.print("❌ Entrada no válida. Ingrese un número decimal válido: ");
            }
        }
    }

    /**
     * Valida si un correo electrónico tiene un formato estándar válido.
     *
     * @param email Cadena que representa un email.
     * @return true si el formato es válido, false en caso contrario.
     */
    public static boolean validarEmail(String email) {
        if (email == null) return false;
        String regex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        return Pattern.matches(regex, email);
    }

    /**
     * Verifica si un texto no es nulo ni está vacío después de recortar espacios.
     *
     * @param texto Texto a validar.
     * @return true si es no vacío y no nulo, false en caso contrario.
     */
    public static boolean textoNoVacio(String texto) {
        return texto != null && !texto.trim().isEmpty();
    }

    /**
     * Solicita al usuario una confirmación del tipo Sí/No.
     *
     * @param scanner Scanner activo para entrada.
     * @return true si el usuario responde 'S', false si responde 'N'.
     */
    public static boolean confirmarSN(Scanner scanner) {
        while (true) {
            System.out.print("¿Desea continuar? (S/N): ");
            String respuesta = scanner.nextLine().trim().toUpperCase();

            if (respuesta.equals("S")) return true;
            if (respuesta.equals("N")) return false;

            System.out.println("❌ Respuesta no válida. Escriba 'S' o 'N'.");
        }
    }

    public static String leerRutValido(Scanner scanner) {
        System.out.print("Ingrese un RUT válido: ");
        while (true) {
            String rut = scanner.nextLine().trim();
            if (textoNoVacio(rut) && validarRut(rut)) {
                return rut;
            }
            System.out.print("❌ RUT inválido. Intente nuevamente: ");
        }
     }
public static String leerTextoNoVacio(Scanner scanner, String mensaje) {
        System.out.print(mensaje);
        while (true) {
            String texto = scanner.nextLine().trim();
            if (textoNoVacio(texto)) {
                return texto;
            }
            System.out.print("❌ El texto no puede estar vacío. Intente nuevamente: ");
        }
      }
}
