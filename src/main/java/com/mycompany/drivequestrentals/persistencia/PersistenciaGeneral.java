package com.mycompany.drivequestrentals.persistencia;

import com.mycompany.drivequestrentals.modelo.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de la persistencia de listas de entidades del sistema.
 * Cada método permite guardar o cargar una lista específica desde un archivo binario.
 */
public class PersistenciaGeneral {

    private static final String ARCHIVO_CLIENTES = "clientes.dat";
    private static final String ARCHIVO_VEHICULOS = "vehiculos.dat";
    private static final String ARCHIVO_ARRIENDOS = "arriendos.dat";
    private static final String ARCHIVO_PAGOS = "pagos.dat";
    private static final String ARCHIVO_MANTENIMIENTOS = "mantenimientos.dat";

    // ---------- MÉTODOS CLIENTES ----------

    public void guardarClientes(List<Cliente> clientes) throws IOException {
        guardarLista(ARCHIVO_CLIENTES, clientes);
    }

    public List<Cliente> cargarClientes() throws IOException, ClassNotFoundException {
        return cargarLista(ARCHIVO_CLIENTES);
    }

    // ---------- MÉTODOS VEHÍCULOS ----------

    public void guardarVehiculos(List<Vehiculo> vehiculos) throws IOException {
        guardarLista(ARCHIVO_VEHICULOS, vehiculos);
    }

    public List<Vehiculo> cargarVehiculos() throws IOException, ClassNotFoundException {
        return cargarLista(ARCHIVO_VEHICULOS);
    }

    // ---------- MÉTODOS ARRIENDOS ----------

    public void guardarArriendos(List<Arriendo> arriendos) throws IOException {
        guardarLista(ARCHIVO_ARRIENDOS, arriendos);
    }

    public List<Arriendo> cargarArriendos() throws IOException, ClassNotFoundException {
        return cargarLista(ARCHIVO_ARRIENDOS);
    }

    // ---------- MÉTODOS PAGOS ----------

    public void guardarPagos(List<Pago> pagos) throws IOException {
        guardarLista(ARCHIVO_PAGOS, pagos);
    }

    public List<Pago> cargarPagos() throws IOException, ClassNotFoundException {
        return cargarLista(ARCHIVO_PAGOS);
    }

    // ---------- MÉTODOS MANTENIMIENTOS ----------

    public void guardarMantenimientos(List<Mantenimiento> mantenimientos) throws IOException {
        guardarLista(ARCHIVO_MANTENIMIENTOS, mantenimientos);
    }

    public List<Mantenimiento> cargarMantenimientos() throws IOException, ClassNotFoundException {
        return cargarLista(ARCHIVO_MANTENIMIENTOS);
    }

    // ---------- MÉTODOS GENÉRICOS DE GUARDADO Y CARGA ----------

    /**
     * Guarda una lista genérica de objetos serializables en un archivo.
     */
    private <T> void guardarLista(String archivo, List<T> lista) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(lista);
        }
    }

    /**
     * Carga una lista genérica de objetos serializables desde un archivo.
     */
    @SuppressWarnings("unchecked")
    private <T> List<T> cargarLista(String archivo) throws IOException, ClassNotFoundException {
        File file = new File(archivo);
        if (!file.exists()) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<T>) ois.readObject();
        }
    }
}
