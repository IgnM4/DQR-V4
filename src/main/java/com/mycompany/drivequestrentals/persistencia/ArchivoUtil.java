package com.mycompany.drivequestrentals.persistencia;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Clase utilitaria para manejo de archivos de texto y objetos serializados.
 * Proporciona métodos reutilizables para guardar y leer información desde archivos.
 */
public class ArchivoUtil {

    /**
     * Guarda una lista de líneas en un archivo de texto. Sobrescribe el archivo si ya existe.
     *
     * @param ruta   Ruta completa del archivo.
     * @param lineas Lista de líneas a escribir.
     * @throws IOException si ocurre un error al escribir el archivo.
     */
    public static void guardarLineas(String ruta, List<String> lineas) throws IOException {
        if (ruta == null || ruta.isEmpty() || lineas == null) return;
        Path path = Paths.get(ruta);
        Files.write(path, lineas, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    /**
     * Lee todas las líneas de un archivo de texto.
     *
     * @param ruta Ruta completa del archivo.
     * @return Lista de líneas leídas. Si el archivo no existe, retorna una lista vacía.
     * @throws IOException si ocurre un error al leer el archivo.
     */
    public static List<String> leerLineas(String ruta) throws IOException {
        if (ruta == null || ruta.isEmpty()) return Collections.emptyList();
        Path path = Paths.get(ruta);
        if (!Files.exists(path)) return new ArrayList<>();
        return Files.readAllLines(path);
    }

    /**
     * Guarda un objeto serializable en un archivo binario.
     *
     * @param ruta   Ruta completa del archivo.
     * @param objeto Objeto a guardar. Debe implementar Serializable.
     * @throws IOException si ocurre un error al escribir el archivo.
     */
    public static void guardarObjeto(String ruta, Object objeto) throws IOException {
        if (ruta == null || ruta.isEmpty() || objeto == null) return;
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta))) {
            oos.writeObject(objeto);
        }
    }

    /**
     * Carga un objeto serializado desde un archivo binario.
     *
     * @param ruta Ruta completa del archivo.
     * @return Objeto deserializado, o null si el archivo no existe o es inválido.
     * @throws IOException            si ocurre un error al leer el archivo.
     * @throws ClassNotFoundException si no se puede convertir el objeto al tipo esperado.
     */
    public static Object cargarObjeto(String ruta) throws IOException, ClassNotFoundException {
        if (ruta == null || ruta.isEmpty()) return null;
        File archivo = new File(ruta);
        if (!archivo.exists()) return null;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            return ois.readObject();
        }
    }
}
