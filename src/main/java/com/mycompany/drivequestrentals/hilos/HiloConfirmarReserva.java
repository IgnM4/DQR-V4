package com.mycompany.drivequestrentals.hilos;

import com.mycompany.drivequestrentals.servicios.ReservaService;

/**
 * Hilo encargado de confirmar una reserva pendiente a través del servicio ReservaService.
 */
public class HiloConfirmarReserva extends Thread {

    private final ReservaService reservaService;
    private final String codigoReserva;

    /**
     * Constructor que inicializa el hilo con el servicio de reservas y el código de la reserva.
     *
     * @param reservaService Servicio que gestiona las reservas.
     * @param codigoReserva  Código identificador de la reserva a confirmar.
     */
    public HiloConfirmarReserva(ReservaService reservaService, String codigoReserva) {
        this.reservaService = reservaService;
        this.codigoReserva = codigoReserva;
    }

    /**
     * Ejecuta la lógica del hilo: confirma la reserva mediante el servicio correspondiente.
     */
    @Override
    public void run() {
        System.out.printf("🔄 Iniciando confirmación de reserva %s...\n", codigoReserva);

        try {
            boolean confirmada = reservaService.confirmarReserva(codigoReserva);

            if (confirmada) {
                System.out.printf("✅ Reserva confirmada con éxito: %s\n", codigoReserva);
            } else {
                System.out.printf("⚠️ No se encontró la reserva con código: %s\n", codigoReserva);
            }
        } catch (Exception e) {
            System.err.printf("❌ Error al confirmar la reserva %s: %s\n", codigoReserva, e.getMessage());
        }
    }
}
