package com.mycompany.drivequestrentals.consola;

import com.mycompany.drivequestrentals.modelo.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Registro central utilizado por el modo consola. Mantiene las listas en
 * memoria de clientes, vehículos, arriendos, pagos y mantenimientos. Se
 * implementa con el patrón Singleton para disponer de una única instancia
 * accesible desde {@link ConsolaControlador}.
 */
public class Registro {

    private static Registro instancia;

    private final List<Cliente> clientes = new ArrayList<>();
    private final List<Vehiculo> vehiculos = new ArrayList<>();
    private final List<Arriendo> arriendos = new ArrayList<>();
    private final List<Pago> pagos = new ArrayList<>();
    private final List<Mantenimiento> mantenimientos = new ArrayList<>();

    /**
     * Constructor privado para el patrón Singleton.
     */
    private Registro() {
    }

    /**
     * Devuelve la instancia única del registro. Si aún no se ha creado, la
     * instancia se inicializa.
     *
     * @return instancia única del registro
     */
    public static synchronized Registro getInstancia() {
        if (instancia == null) {
            instancia = new Registro();
        }
        return instancia;
    }

    // ======= Gestión de clientes =======

    /**
     * Agrega un cliente al registro.
     */
    public void agregarCliente(Cliente cliente) {
        if (cliente != null) {
            clientes.add(cliente);
        }
    }

    /**
     * Busca un cliente por su RUT o pasaporte.
     */
    public Cliente buscarClientePorRUT(String rut) {
        if (rut == null) return null;
        return clientes.stream()
                .filter(c -> rut.equalsIgnoreCase(c.getRutOPasaporte()))
                .findFirst()
                .orElse(null);
    }

    /**
     * Devuelve una copia de la lista de clientes registrados.
     */
    public List<Cliente> obtenerClientes() {
        return new ArrayList<>(clientes);
    }

    // ======= Gestión de vehículos =======

    public void agregarVehiculo(Vehiculo vehiculo) {
        if (vehiculo != null) {
            vehiculos.add(vehiculo);
        }
    }

    public Vehiculo buscarVehiculoPorPatente(String patente) {
        if (patente == null) return null;
        return vehiculos.stream()
                .filter(v -> patente.equalsIgnoreCase(v.getPatente()))
                .findFirst()
                .orElse(null);
    }

    public List<Vehiculo> obtenerVehiculos() {
        return new ArrayList<>(vehiculos);
    }

    // ======= Gestión de arriendos =======

    public void registrarArriendo(Arriendo arriendo) {
        if (arriendo != null) {
            arriendos.add(arriendo);
        }
    }

    public Arriendo buscarArriendoPorId(int id) {
        String idTexto = String.valueOf(id);
        for (Arriendo a : arriendos) {
            if (a.getId() != null) {
                if (a.getId().equals(idTexto)) {
                    return a;
                }
                try {
                    if (Integer.parseInt(a.getId()) == id) {
                        return a;
                    }
                } catch (NumberFormatException ignored) {
                }
            }
        }
        return null;
    }

    // ======= Gestión de pagos =======

    public void registrarPago(Pago pago) {
        if (pago != null) {
            pagos.add(pago);
        }
    }

    // ======= Gestión de mantenimientos =======

    public void registrarMantenimiento(Mantenimiento mantenimiento) {
        if (mantenimiento != null) {
            mantenimientos.add(mantenimiento);
        }
    }
}

