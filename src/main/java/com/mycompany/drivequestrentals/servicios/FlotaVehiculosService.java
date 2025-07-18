package com.mycompany.drivequestrentals.servicios;

import com.mycompany.drivequestrentals.modelo.Vehiculo;
import com.mycompany.drivequestrentals.modelo.Arriendo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Servicio encargado de gestionar la flota de vehículos.
 * Maneja operaciones como agregar, buscar, listar, filtrar y eliminar vehículos.
 */
public class FlotaVehiculosService {

    // Lista sincronizada para acceso seguro desde múltiples hilos
    private final List<Vehiculo> vehiculos = Collections.synchronizedList(new ArrayList<>());

    /**
     * Agrega un nuevo vehículo si no existe otro con la misma patente.
     *
     * @param vehiculo objeto a registrar
     * @return true si se agregó correctamente, false si ya existe
     */
    public boolean agregarVehiculo(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculo, "El vehículo no puede ser null");
        synchronized (vehiculos) {
            return vehiculos.stream()
                    .noneMatch(v -> v.getPatente().equalsIgnoreCase(vehiculo.getPatente())) 
                    && vehiculos.add(vehiculo);
        }
    }

    /**
     * Retorna la lista completa de vehículos registrados.
     *
     * @return lista de vehículos
     */
    public List<Vehiculo> listarVehiculos() {
        synchronized (vehiculos) {
            return new ArrayList<>(vehiculos);
        }
    }

    /**
     * Busca un vehículo por su patente.
     *
     * @param patente patente a buscar
     * @return vehículo encontrado o null
     */
    public Vehiculo buscarPorPatente(String patente) {
        if (patente == null || patente.isBlank()) return null;
        synchronized (vehiculos) {
            return vehiculos.stream()
                    .filter(v -> v.getPatente().equalsIgnoreCase(patente))
                    .findFirst()
                    .orElse(null);
        }
    }

    /**
     * Cambia el estado de un vehículo.
     *
     * @param patente     patente del vehículo
     * @param nuevoEstado nuevo estado a asignar
     * @return true si se actualizó, false si no se encontró el vehículo
     */
    public boolean cambiarEstado(String patente, String nuevoEstado) {
        Vehiculo vehiculo = buscarPorPatente(patente);
        if (vehiculo != null && nuevoEstado != null) {
            vehiculo.setEstado(nuevoEstado);
            return true;
        }
        return false;
    }

    /**
     * Filtra vehículos que han sido arrendados por más de X días.
     *
     * @param arriendos lista de arriendos activos
     * @param diasMin   cantidad mínima de días para considerar "arriendo prolongado"
     * @return lista de vehículos filtrados
     */
    public List<Vehiculo> obtenerVehiculosConArriendosLargos(List<Arriendo> arriendos, long diasMin) {
        if (arriendos == null || diasMin <= 0) return Collections.emptyList();
        List<Vehiculo> resultado = new ArrayList<>();
        for (Arriendo arriendo : arriendos) {
            if (arriendo != null && arriendo.getDuracionDias() >= diasMin) {
                resultado.add(arriendo.getVehiculo());
            }
        }
        return resultado;
    }

    /**
     * Elimina un vehículo por su patente.
     *
     * @param patente patente del vehículo a eliminar
     * @return true si se eliminó, false si no se encontró
     */
    public boolean eliminarVehiculo(String patente) {
        if (patente == null || patente.isBlank()) return false;
        synchronized (vehiculos) {
            return vehiculos.removeIf(v -> v.getPatente().equalsIgnoreCase(patente));
        }
    }
}
