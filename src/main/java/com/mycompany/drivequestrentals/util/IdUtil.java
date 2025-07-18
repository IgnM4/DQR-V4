package com.mycompany.drivequestrentals.util;

import java.util.UUID;

/**
 * Clase utilitaria para la generación de identificadores únicos (UUID).
 */
public class IdUtil {

    private static final String SEPARADOR = "-";

    /**
     * Genera un identificador único (UUID) con un prefijo dado.
     *
     * @param prefijo Prefijo que se antepondrá al identificador.
     * @return String con el identificador único prefijado.
     */
    public static String generarIdConPrefijo(String prefijo) {
        return String.format("%s%s%s", prefijo, SEPARADOR, generarUUID());
    }

    /**
     * Genera un UUID simple (sin prefijo).
     *
     * @return UUID generado como cadena.
     */
    public static String generarUUID() {
        return UUID.randomUUID().toString();
    }
}
