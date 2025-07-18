package com.mycompany.drivequestrentals.hilos;

import com.mycompany.drivequestrentals.modelo.Reserva;
import com.mycompany.drivequestrentals.servicios.ReservaService;

import java.util.List;

/**
 * Hilo encargado de listar todas las reservas activas registradas en el sistema.
 */
public class HiloListadoReservas extends Thread {

    private final ReservaService reservaService;

    /**
     * Constructor que recibe el servicio de reservas a utilizar.
     *
     * @param reservaService Servicio encargado de gestionar las reservas.
     */
    public HiloListadoReservas(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    /**
     * Ejecuta el hilo que muestra en consola el listado completo de reservas.
     */
    @Override
    public void run() {
        System.out.println("🔎 Listado de reservas activas:\n");

        try {
            List<Reserva> reservas = reservaService.listarReservas();

            if (reservas.isEmpty()) {
                System.out.println("⚠️ No hay reservas registradas en el sistema.");
            } else {
                for (Reserva reserva : reservas) {
                    System.out.println("🔸 " + reserva);
                }
                System.out.printf("\n✅ Total de reservas listadas: %d\n", reservas.size());
            }

        } catch (Exception e) {
            System.err.println("❌ Error al obtener el listado de reservas: " + e.getMessage());
        }
    }
}
