package com.mycompany.drivequestrentals.servicios;

import com.mycompany.drivequestrentals.modelo.Mantenimiento;
import com.mycompany.drivequestrentals.modelo.Vehiculo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Servicio para registrar y gestionar mantenimientos de vehículos.
 */
public class MantenimientoService {

    private final List<Mantenimiento> mantenimientos = new ArrayList<>();

    /**
     * Registra un mantenimiento a partir de sus datos individuales.
     *
     * @param vehiculo    Vehículo asociado
     * @param descripcion Descripción del mantenimiento
     * @param fecha       Fecha del mantenimiento
     * @param costo       Costo total
     * @param kilometraje Kilometraje al momento
     * @return Mantenimiento registrado
     * @throws IllegalArgumentException si algún parámetro es inválido
     */
    public Mantenimiento registrarMantenimiento(Vehiculo vehiculo, String descripcion, LocalDate fecha,
                                                double costo, double kilometraje) {

        validarParametros(vehiculo, descripcion, fecha, costo, kilometraje);

        Mantenimiento nuevo = new Mantenimiento(generarIdUnico(), vehiculo, descripcion, fecha, costo, kilometraje);
        mantenimientos.add(nuevo);
        return nuevo;
    }

    /**
     * Registra un mantenimiento ya construido.
     *
     * @param mantenimiento Objeto a registrar
     * @return Objeto registrado
     * @throws IllegalArgumentException si el mantenimiento es nulo
     */
    public Mantenimiento registrarMantenimiento(Mantenimiento mantenimiento) {
        if (mantenimiento == null) {
            throw new IllegalArgumentException("El mantenimiento no puede ser nulo.");
        }
        mantenimientos.add(mantenimiento);
        return mantenimiento;
    }

    /**
     * Valida los parámetros del mantenimiento.
     */
    private void validarParametros(Vehiculo vehiculo, String descripcion, LocalDate fecha,
                                   double costo, double kilometraje) {
        if (vehiculo == null) {
            throw new IllegalArgumentException("El vehículo no puede ser nulo.");
        }
        if (descripcion == null || descripcion.isBlank()) {
            throw new IllegalArgumentException("La descripción no puede estar vacía.");
        }
        if (fecha == null || fecha.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha no puede ser futura ni nula.");
        }
        if (costo < 0) {
            throw new IllegalArgumentException("El costo no puede ser negativo.");
        }
        if (kilometraje < 0) {
            throw new IllegalArgumentException("El kilometraje no puede ser negativo.");
        }
    }

    /**
     * Genera un identificador único para un mantenimiento.
     */
    private String generarIdUnico() {
        return "MTTO-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    /**
     * Retorna la lista completa de mantenimientos registrados.
     */
    public List<Mantenimiento> obtenerMantenimientos() {
        return new ArrayList<>(mantenimientos); // protección de la lista interna
    }

    /**
     * Retorna los mantenimientos realizados a un vehículo específico.
     *
     * @param vehiculo Vehículo a consultar
     * @return Lista de mantenimientos encontrados
     */
    public List<Mantenimiento> buscarPorVehiculo(Vehiculo vehiculo) {
        return mantenimientos.stream()
                .filter(m -> m.getVehiculo().equals(vehiculo))
                .collect(Collectors.toList());
    }

    /**
     * Calcula el costo total de todos los mantenimientos registrados.
     *
     * @return Suma total de costos
     */
    public double calcularCostoTotal() {
        return mantenimientos.stream()
                .mapToDouble(Mantenimiento::getCosto)
                .sum();
    }
}
