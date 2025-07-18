package com.mycompany.drivequestrentals.hilos;

import com.mycompany.drivequestrentals.modelo.Reserva;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Runnable que exporta la lista de reservas a un archivo CSV para respaldo o seguimiento externo.
 */
public class ExportadorReservasRunnable implements Runnable {

    private final List<Reserva> reservas;
    private final String rutaArchivo;
    private static final String SEPARADOR = ",";
    private static final String SALTO_LINEA = "\n";
    private static final String ENCABEZADO_CSV = "Cliente,Vehículo,FechaInicio,FechaFin,PrecioFinal";

    /**
     * Constructor que recibe la lista de reservas y la ruta de destino.
     *
     * @param reservas    Lista de reservas a exportar.
     * @param rutaArchivo Ruta del archivo destino (ej: "reservas.csv").
     */
    public ExportadorReservasRunnable(List<Reserva> reservas, String rutaArchivo) {
        this.reservas = reservas;
        this.rutaArchivo = rutaArchivo;
    }

    /**
     * Ejecuta el proceso de exportación de reservas a archivo CSV.
     */
    @Override
    public void run() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {
            writer.write(ENCABEZADO_CSV + SALTO_LINEA);

            for (Reserva reserva : reservas) {
                String linea = String.join(SEPARADOR,
                        escape(reserva.getCliente().getNombre()),
                        escape(reserva.getVehiculo().getPatente()),
                        reserva.getFechaInicio().toString(),
                        reserva.getFechaFin().toString(),
                        String.valueOf(reserva.getPrecioFinal())
                );
                writer.write(linea + SALTO_LINEA);
            }

            System.out.println("✅ Reservas exportadas exitosamente a " + rutaArchivo);
        } catch (IOException e) {
            System.err.println("❌ Error al exportar las reservas: " + e.getMessage());
        }
    }

    /**
     * Escapa valores que puedan contener caracteres especiales como comas o comillas.
     *
     * @param valor Cadena original
     * @return Cadena escapada para formato CSV
     */
    private String escape(String valor) {
        if (valor.contains(SEPARADOR) || valor.contains("\"")) {
            return "\"" + valor.replace("\"", "\"\"") + "\"";
        }
        return valor;
    }
}
