package com.mycompany.drivequestrentals.hilos;

import com.mycompany.drivequestrentals.modelo.Reserva;
import com.mycompany.drivequestrentals.servicios.ReservaService;

/**
 * Hilo que consulta y notifica el estado de una reserva específica en base a su código.
 */
public class HiloNotificarReserva extends Thread {

    private final ReservaService reservaService;
    private final String codigoReserva;

    /**
     * Constructor del hilo notificador.
     *
     * @param reservaService Servicio de reservas utilizado para la búsqueda.
     * @param codigoReserva Código único de la reserva a notificar.
     */
    public HiloNotificarReserva(ReservaService reservaService, String codigoReserva) {
        this.reservaService = reservaService;
        this.codigoReserva = codigoReserva;
    }

    /**
     * Método que ejecuta la lógica del hilo: buscar y notificar estado de la reserva.
     */
    @Override
    public void run() {
        try {
            Reserva reserva = reservaService.buscarReservaPorCodigo(codigoReserva);

            if (reserva == null) {
                System.out.printf("❌ No se encontró ninguna reserva con el código: %s%n", codigoReserva);
                return;
            }

            String estado = reserva.isConfirmada() ? "CONFIRMADA ✅" : "PENDIENTE ⏳";
            System.out.printf("📢 Estado de la reserva [%s]: %s%n", codigoReserva, estado);
            System.out.println("📝 Detalle: " + reserva);

        } catch (Exception e) {
            System.err.printf("⚠️ Error al notificar reserva [%s]: %s%n", codigoReserva, e.getMessage());
        }
    }
}
