package com.mycompany.drivequestrentals.persistencia;

import com.mycompany.drivequestrentals.modelo.Arriendo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase DAO encargada de la persistencia de objetos Arriendo.
 * Permite guardar y recuperar datos de arriendos desde un archivo binario.
 */
public class ArriendoDAO {

    private static final String ARCHIVO = "arriendos.dat";

    /**
     * Guarda una lista de arriendos en un archivo serializado.
     *
     * @param arriendos Lista de arriendos a guardar.
     * @throws IOException si ocurre un error durante la escritura del archivo.
     */
    public void guardarArriendos(List<Arriendo> arriendos) throws IOException {
        if (arriendos == null) return;
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO))) {
            oos.writeObject(arriendos);
        }
    }

    /**
     * Carga y devuelve la lista de arriendos desde el archivo.
     *
     * @return Lista de arriendos, o una lista vacía si el archivo no existe o el contenido es inválido.
     * @throws IOException            si ocurre un error de lectura.
     * @throws ClassNotFoundException si los datos no corresponden a la clase Arriendo.
     */
    @SuppressWarnings("unchecked")
    public List<Arriendo> cargarArriendos() throws IOException, ClassNotFoundException {
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) return new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            Object objeto = ois.readObject();
            if (objeto instanceof List<?>) {
                return (List<Arriendo>) objeto;
            } else {
                return new ArrayList<>();
            }
        }
    }
}
