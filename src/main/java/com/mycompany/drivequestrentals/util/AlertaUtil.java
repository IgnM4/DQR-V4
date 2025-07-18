package com.mycompany.drivequestrentals.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Clase utilitaria para mostrar cuadros de diálogo en la interfaz.
 */
public class AlertaUtil {

    private static final String TITULO_POR_DEFECTO = "Información";

    /**
     * Muestra una alerta de información con el mensaje especificado.
     *
     * @param mensaje Mensaje a mostrar en el cuadro de diálogo.
     */
    public static void mostrarAlerta(String mensaje) {
        mostrarAlerta(mensaje, TITULO_POR_DEFECTO, AlertType.INFORMATION);
    }

    /**
     * Método genérico para mostrar diferentes tipos de alertas.
     *
     * @param mensaje Mensaje a mostrar.
     * @param titulo  Título de la ventana de alerta.
     * @param tipo    Tipo de alerta (INFORMATION, WARNING, ERROR, etc.).
     */
    public static void mostrarAlerta(String mensaje, String titulo, AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
