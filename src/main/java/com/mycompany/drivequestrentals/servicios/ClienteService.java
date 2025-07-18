package com.mycompany.drivequestrentals.servicios;

import com.mycompany.drivequestrentals.modelo.Cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Servicio que gestiona la creación, búsqueda, validación y actualización de clientes
 * en la aplicación DriveQuest Rentals.
 */
public class ClienteService {

    private final List<Cliente> listaClientes;

    private static final String REGEX_CORREO = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
    private static final String REGEX_TELEFONO = "^\\+?\\d{8,15}$";

    /**
     * Constructor que inicializa la lista de clientes.
     */
    public ClienteService() {
        this.listaClientes = new ArrayList<>();
    }

    /**
     * Registra un nuevo cliente validando duplicados y datos obligatorios.
     *
     * @param cliente Cliente a registrar
     * @return true si se registró exitosamente, false si ya existe o los datos son inválidos
     */
    public boolean registrarCliente(Cliente cliente) {
        if (cliente == null || !validarDatos(cliente)) {
            System.err.println("❌ Datos inválidos al registrar cliente.");
            return false;
        }

        if (buscarClientePorIdentificador(cliente.getRutOPasaporte()).isPresent()) {
            System.err.println("⚠️ Cliente con RUT/Pasaporte ya registrado.");
            return false;
        }

        listaClientes.add(cliente);
        return true;
    }

    /**
     * Valida los datos esenciales del cliente utilizando expresiones regulares.
     *
     * @param cliente Cliente a validar
     * @return true si todos los datos son válidos
     */
    private boolean validarDatos(Cliente cliente) {
        return cliente.getNombreCompleto() != null && !cliente.getNombreCompleto().isBlank()
                && cliente.getRutOPasaporte() != null && !cliente.getRutOPasaporte().isBlank()
                && cliente.getCorreo() != null && cliente.getCorreo().matches(REGEX_CORREO)
                && cliente.getTelefono() != null && cliente.getTelefono().matches(REGEX_TELEFONO);
    }

    /**
     * Busca cliente por RUT o Pasaporte.
     *
     * @param rutOPasaporte identificador único
     * @return Optional con el cliente encontrado o vacío
     */
    public Optional<Cliente> buscarClientePorIdentificador(String rutOPasaporte) {
        return listaClientes.stream()
                .filter(c -> c.getRutOPasaporte().equalsIgnoreCase(rutOPasaporte))
                .findFirst();
    }

    /**
     * Busca cliente por correo electrónico.
     *
     * @param correo dirección de correo
     * @return Optional con el cliente encontrado o vacío
     */
    public Optional<Cliente> buscarClientePorCorreo(String correo) {
        return listaClientes.stream()
                .filter(c -> c.getCorreo().equalsIgnoreCase(correo))
                .findFirst();
    }

    /**
     * Devuelve la lista de todos los clientes registrados.
     *
     * @return lista de clientes
     */
    public List<Cliente> obtenerTodosLosClientes() {
        return new ArrayList<>(listaClientes); // Previene modificación externa
    }

    /**
     * Elimina un cliente por su identificador (RUT o Pasaporte).
     *
     * @param rutOPasaporte identificador del cliente
     * @return true si se eliminó, false si no se encontró
     */
    public boolean eliminarCliente(String rutOPasaporte) {
        return listaClientes.removeIf(c -> c.getRutOPasaporte().equalsIgnoreCase(rutOPasaporte));
    }

    /**
     * Actualiza los datos de un cliente existente.
     *
     * @param clienteActualizado cliente con nuevos datos
     * @return true si se actualizó correctamente
     */
    public boolean actualizarCliente(Cliente clienteActualizado) {
        return buscarClientePorIdentificador(clienteActualizado.getRutOPasaporte())
                .map(encontrado -> {
                    listaClientes.remove(encontrado);
                    listaClientes.add(clienteActualizado);
                    return true;
                }).orElse(false);
    }

    /**
     * Devuelve directamente un cliente por RUT o Pasaporte. Retorna null si no se encuentra.
     *
     * @param rut RUT o pasaporte
     * @return cliente encontrado o null
     */
    public Cliente buscarPorRutOPasaporte(String rut) {
        return buscarClientePorIdentificador(rut).orElse(null);
    }
}
