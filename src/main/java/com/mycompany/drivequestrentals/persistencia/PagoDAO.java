package com.mycompany.drivequestrentals.persistencia;

import com.mycompany.drivequestrentals.modelo.Pago;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase DAO encargada de la persistencia de objetos Pago.
 * Guarda y carga pagos desde un archivo binario serializado.
 */
public class PagoDAO {

    private static final String ARCHIVO = "pagos.dat";

    /**
     * Guarda una lista de objetos Pago en el archivo especificado.
     *
     * @param pagos Lista de pagos a guardar
     * @throws IOException si ocurre un error de escritura
     */
    public void guardarPagos(List<Pago> pagos) throws IOException {
        if (pagos == null) return;
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO))) {
            oos.writeObject(pagos);
        }
    }

    /**
     * Carga y devuelve una lista de pagos desde el archivo.
     *
     * @return Lista de pagos cargada, o lista vacía si el archivo no existe
     * @throws IOException            si ocurre un error de lectura
     * @throws ClassNotFoundException si el archivo contiene clases incompatibles
     */
    @SuppressWarnings("unchecked")
    public List<Pago> cargarPagos() throws IOException, ClassNotFoundException {
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) return new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            Object objeto = ois.readObject();
            if (objeto instanceof List<?>) {
                return (List<Pago>) objeto;
            } else {
                return new ArrayList<>();
            }
        }
    }
}
