package com.mycompany.drivequestrentals.servicios;

import com.mycompany.drivequestrentals.modelo.*;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * Servicio encargado de registrar arriendos, generar contratos y boletas,
 * y mantener un historial consultable de arriendos por cliente.
 */
public class ArriendoService {

    private final List<Arriendo> historialArriendos = new ArrayList<>();

    /**
     * Registra un nuevo arriendo con los datos entregados y genera contrato y boleta.
     *
     * @return objeto Arriendo generado y registrado
     */
    public Arriendo registrarArriendo(Cliente cliente, Vehiculo vehiculo,
                                      LocalDate inicio, LocalDate fin,
                                      double kilometraje, double tarifaDiaria,
                                      boolean aplicarDescuento, String contratoTipo) {

        validarVehiculoFacturable(vehiculo);
        validarFechas(inicio, fin);

        int dias = calcularDias(inicio, fin);
        String boletaTexto = ((IFacturable) vehiculo).generarBoleta(dias, tarifaDiaria, aplicarDescuento);

        double subtotal = tarifaDiaria * dias;
        double descuento = aplicarDescuento ? calcularDescuento(vehiculo, subtotal) : 0;
        double neto = subtotal - descuento;
        double total = neto + (neto * IFacturable.IVA);

        Arriendo nuevo = new Arriendo(
                UUID.randomUUID().toString(), cliente, vehiculo, inicio, fin,
                kilometraje, total, contratoTipo
        );

        historialArriendos.add(nuevo);
        guardarContratoTexto(nuevo, boletaTexto);
        return nuevo;
    }

    /**
     * Registra un arriendo preconstruido y guarda su contrato y boleta.
     */
    public Arriendo registrarArriendo(Arriendo arriendo) {
        validarVehiculoFacturable(arriendo.getVehiculo());
        validarFechas(arriendo.getFechaInicio(), arriendo.getFechaFin());

        int dias = calcularDias(arriendo.getFechaInicio(), arriendo.getFechaFin());
        double tarifaEstimada = arriendo.getMontoTotal() / (1 + IFacturable.IVA) / dias;

        String boletaTexto = ((IFacturable) arriendo.getVehiculo()).generarBoleta(dias, tarifaEstimada, false);

        historialArriendos.add(arriendo);
        guardarContratoTexto(arriendo, boletaTexto);
        return arriendo;
    }

    /**
     * Guarda un archivo de texto con el contrato y boleta del arriendo.
     */
    private void guardarContratoTexto(Arriendo arriendo, String boletaTexto) {
        String nombreArchivo = "contrato_" + arriendo.getId() + ".txt";
        try (FileWriter fw = new FileWriter("contratos/" + nombreArchivo)) {
            fw.write("===== CONTRATO DE ARRIENDO =====\n");
            fw.write("Cliente: " + arriendo.getCliente().getNombreCompleto() + "\n");
            fw.write("Vehículo: " + arriendo.getVehiculo().mostrarDatos() + "\n");
            fw.write("Inicio: " + arriendo.getFechaInicio() + " | Fin: " + arriendo.getFechaFin() + "\n");
            fw.write("Kilometraje: " + arriendo.getKilometrajeActual() + " km\n");
            fw.write("Tipo de contrato: " + arriendo.getContratoTipo() + "\n\n");
            fw.write(boletaTexto);
        } catch (IOException e) {
            System.err.println("Error al guardar contrato: " + e.getMessage());
        }
    }

    /**
     * Devuelve el historial completo de arriendos registrados.
     */
    public List<Arriendo> getHistorialArriendos() {
        return new ArrayList<>(historialArriendos);
    }

    /**
     * Filtra los arriendos asociados a un cliente específico.
     */
    public List<Arriendo> obtenerArriendosPorCliente(Cliente cliente) {
        List<Arriendo> resultado = new ArrayList<>();
        for (Arriendo a : historialArriendos) {
            if (a.getCliente().equals(cliente)) {
                resultado.add(a);
            }
        }
        return resultado;
    }

    /**
     * Busca un arriendo vigente asociado a un cliente por su RUT.
     */
    public Arriendo buscarArriendoActivoPorCliente(String rutCliente) {
        LocalDate hoy = LocalDate.now();
        for (Arriendo a : historialArriendos) {
            if (a.getCliente().getRutOPasaporte().equalsIgnoreCase(rutCliente)
                    && !hoy.isBefore(a.getFechaInicio())
                    && !hoy.isAfter(a.getFechaFin())) {
                return a;
            }
        }
        return null;
    }

    // ========== MÉTODOS DE VALIDACIÓN Y UTILIDAD ==========

    /**
     * Verifica que las fechas sean válidas.
     */
    private void validarFechas(LocalDate inicio, LocalDate fin) {
        if (inicio == null || fin == null || !fin.isAfter(inicio)) {
            throw new IllegalArgumentException("Fechas inválidas: la duración debe ser mayor a 0.");
        }
    }

    /**
     * Verifica que el vehículo implemente la interfaz IFacturable.
     */
    private void validarVehiculoFacturable(Vehiculo vehiculo) {
        if (!(vehiculo instanceof IFacturable)) {
            throw new IllegalArgumentException("El vehículo no permite generación de boleta.");
        }
    }

    /**
     * Calcula la cantidad de días entre dos fechas.
     */
    private int calcularDias(LocalDate inicio, LocalDate fin) {
        return (int) ChronoUnit.DAYS.between(inicio, fin);
    }

    /**
     * Calcula el descuento correspondiente al tipo de vehículo.
     */
    private double calcularDescuento(Vehiculo vehiculo, double subtotal) {
        if (vehiculo instanceof VehiculoCarga) {
            return subtotal * IFacturable.DESCUENTO_CARGA;
        } else {
            return subtotal * IFacturable.DESCUENTO_PASAJEROS;
        }
    }
}
