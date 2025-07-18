package com.mycompany.drivequestrentals.hilos;

import com.mycompany.drivequestrentals.modelo.Reserva;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * Hilo que verifica las reservas próximas a finalizar (mañana o pasado mañana)
 * y muestra un mensaje de recordatorio para anticipar la devolución del vehículo.
 */
public class RecordatorioFinReservaThread extends Thread {

    private final List<Reserva> reservas;

    /**
     * Constructor que recibe la lista de reservas activas.
     *
     * @param reservas Lista de reservas a verificar.
     */
    public RecordatorioFinReservaThread(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    /**
     * Ejecuta el hilo que recorre la lista de reservas y muestra recordatorios
     * para aquellas que están próximas a finalizar.
     */
    @Override
    public void run() {
        if (reservas == null || reservas.isEmpty()) {
            System.out.println("ℹ️ No hay reservas activas para verificar.");
            return;
        }

        LocalDate hoy = LocalDate.now();
        boolean seMostroRecordatorio = false;

        for (Reserva reserva : reservas) {
            if (reserva == null || reserva.getFechaFin() == null) continue;

            LocalDate fechaFin = reserva.getFechaFin().toLocalDate();

            if (fechaFin.isAfter(hoy) && fechaFin.isBefore(hoy.plusDays(3))) {
                System.out.printf("📅 Recordatorio: La reserva de %s con el vehículo [%s] finaliza el día %s.%n",
                        reserva.getCliente().getNombre(),
                        reserva.getVehiculo().getPatente(),
                        fechaFin);
                seMostroRecordatorio = true;
            }
        }

        if (!seMostroRecordatorio) {
            System.out.println("✅ No hay reservas próximas a finalizar en los próximos 2 días.");
        }
    }
}
