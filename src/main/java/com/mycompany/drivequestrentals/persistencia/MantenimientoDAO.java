package com.mycompany.drivequestrentals.persistencia;

import com.mycompany.drivequestrentals.modelo.Mantenimiento;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para operaciones de persistencia de Mantenimiento.
 * Permite guardar y cargar la lista de mantenimientos desde un archivo binario.
 */
public class MantenimientoDAO {

    private static final String ARCHIVO = "mantenimientos.dat";

    /**
     * Guarda la lista de mantenimientos en el archivo correspondiente.
     *
     * @param mantenimientos Lista de objetos Mantenimiento a guardar.
     * @throws IOException si ocurre un error al escribir el archivo.
     */
    public void guardarMantenimientos(List<Mantenimiento> mantenimientos) throws IOException {
        if (mantenimientos == null) return;
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO))) {
            oos.writeObject(mantenimientos);
        }
    }

    /**
     * Carga la lista de mantenimientos desde el archivo correspondiente.
     *
     * @return Lista de mantenimientos. Retorna una lista vacía si el archivo no existe o el contenido es inválido.
     * @throws IOException            si ocurre un error de lectura.
     * @throws ClassNotFoundException si los datos deserializados no corresponden a la clase esperada.
     */
    @SuppressWarnings("unchecked")
    public List<Mantenimiento> cargarMantenimientos() throws IOException, ClassNotFoundException {
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) return new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            Object objeto = ois.readObject();
            if (objeto instanceof List<?>) {
                return (List<Mantenimiento>) objeto;
            } else {
                return new ArrayList<>();
            }
        }
    }
}
