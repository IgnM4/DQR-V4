package com.mycompany.drivequestrentals.hilos;

import com.mycompany.drivequestrentals.modelo.Reserva;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Hilo que verifica si existen reservas activas en la fecha actual.
 * Una reserva activa es aquella cuya fecha actual está entre la fecha de inicio y la fecha de término (inclusive).
 */
public class VerificadorReservasActivasThread extends Thread {

    private final List<Reserva> reservas;

    /**
     * Constructor del hilo que recibe la lista de reservas a verificar.
     *
     * @param reservas Lista de reservas registradas en el sistema.
     */
    public VerificadorReservasActivasThread(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    /**
     * Método principal que ejecuta la verificación de reservas activas.
     */
    @Override
    public void run() {
        if (reservas == null || reservas.isEmpty()) {
            System.out.println("ℹ️ No hay reservas registradas para verificar.");
            return;
        }

        LocalDateTime ahora = LocalDateTime.now();
        boolean hayReservasActivas = false;

        for (Reserva reserva : reservas) {
            if (reserva == null || reserva.getFechaInicio() == null || reserva.getFechaFin() == null) {
                continue; // Saltar reservas mal definidas
            }

            boolean activa =
                (!reserva.getFechaInicio().isAfter(ahora)) &&
                (!reserva.getFechaFin().isBefore(ahora));

            if (activa) {
                hayReservasActivas = true;
                String nombreCliente = reserva.getCliente() != null ? reserva.getCliente().getNombre() : "Desconocido";
                String patente = reserva.getVehiculo() != null ? reserva.getVehiculo().getPatente() : "N/A";

                System.out.printf("✅ Reserva activa: Cliente: %s | Vehículo: %s | Desde: %s | Hasta: %s%n",
                        nombreCliente, patente, reserva.getFechaInicio(), reserva.getFechaFin());
            }
        }

        if (!hayReservasActivas) {
            System.out.println("✅ No hay reservas activas para el día de hoy (" + ahora.toLocalDate() + ").");
        }
    }
}
