package com.mycompany.drivequestrentals.persistencia;

import com.mycompany.drivequestrentals.modelo.Vehiculo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase DAO responsable de la persistencia de objetos Vehiculo.
 * Permite guardar y cargar vehículos desde un archivo binario serializado.
 */
public class VehiculoDAO {

    private static final String ARCHIVO = "vehiculos.dat";

    /**
     * Guarda una lista de vehículos en el archivo especificado.
     *
     * @param vehiculos Lista de vehículos a guardar.
     * @throws IOException si ocurre un error al escribir en el archivo.
     */
    public void guardarVehiculos(List<Vehiculo> vehiculos) throws IOException {
        if (vehiculos == null) return;
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO))) {
            oos.writeObject(vehiculos);
        }
    }

    /**
     * Carga y devuelve una lista de vehículos desde el archivo.
     *
     * @return Lista de vehículos cargada, o lista vacía si el archivo no existe o es incompatible.
     * @throws IOException            si ocurre un error de lectura del archivo.
     * @throws ClassNotFoundException si el contenido del archivo no corresponde a la clase esperada.
     */
    @SuppressWarnings("unchecked")
    public List<Vehiculo> cargarVehiculos() throws IOException, ClassNotFoundException {
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) return new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            Object objeto = ois.readObject();
            if (objeto instanceof List<?>) {
                return (List<Vehiculo>) objeto;
            } else {
                return new ArrayList<>();
            }
        }
    }
}
