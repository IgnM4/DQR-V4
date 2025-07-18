package com.mycompany.drivequestrentals.hilos;

import com.mycompany.drivequestrentals.modelo.Vehiculo;

/**
 * Hilo que verifica periódicamente el estado de disponibilidad de un vehículo.
 * Si el vehículo no está disponible, se mostrará un mensaje de advertencia.
 */
public class VerificadorVehiculoThread extends Thread {

    private final Vehiculo vehiculo;
    private final long intervaloVerificacion = 10_000; // 10 segundos

    /**
     * Constructor que recibe el vehículo a verificar.
     *
     * @param vehiculo Vehículo que será monitoreado por este hilo.
     */
    public VerificadorVehiculoThread(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    /**
     * Método principal del hilo que verifica continuamente el estado del vehículo.
     */
    @Override
    public void run() {
        if (vehiculo == null) {
            System.out.println("❌ Vehículo no válido para verificación.");
            return;
        }

        try {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("🔍 Verificando estado del vehículo: " + vehiculo.getPatente());

                if (!vehiculo.isDisponible()) {
                    System.out.println("⚠️ Vehículo no disponible: " + vehiculo.getPatente());
                } else {
                    System.out.println("✅ Vehículo disponible: " + vehiculo.getPatente());
                }

                Thread.sleep(intervaloVerificacion);
            }
        } catch (InterruptedException e) {
            System.out.println("🛑 Hilo de verificación interrumpido para el vehículo: " + vehiculo.getPatente());
            Thread.currentThread().interrupt(); // restablecer estado de interrupción
        } catch (Exception e) {
            System.out.println("❗ Error al verificar vehículo: " + e.getMessage());
        }
    }
}
