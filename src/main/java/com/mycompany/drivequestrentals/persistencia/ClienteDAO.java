package com.mycompany.drivequestrentals.persistencia;

import com.mycompany.drivequestrentals.modelo.Cliente;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase DAO encargada de la persistencia de objetos Cliente.
 * Permite guardar y recuperar datos de clientes desde un archivo binario.
 */
public class ClienteDAO {

    private static final String ARCHIVO = "clientes.dat";

    /**
     * Guarda una lista de clientes en un archivo serializado.
     *
     * @param clientes Lista de clientes a guardar.
     * @throws IOException si ocurre un error durante la escritura del archivo.
     */
    public void guardarClientes(List<Cliente> clientes) throws IOException {
        if (clientes == null) return;
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO))) {
            oos.writeObject(clientes);
        }
    }

    /**
     * Carga y devuelve la lista de clientes desde el archivo.
     *
     * @return Lista de clientes, o una lista vacía si el archivo no existe o está corrupto.
     * @throws IOException            si ocurre un error de lectura.
     * @throws ClassNotFoundException si los datos no corresponden a la clase Cliente.
     */
    @SuppressWarnings("unchecked")
    public List<Cliente> cargarClientes() throws IOException, ClassNotFoundException {
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) return new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            Object objeto = ois.readObject();
            if (objeto instanceof List<?>) {
                return (List<Cliente>) objeto;
            } else {
                return new ArrayList<>();
            }
        }
    }
}
