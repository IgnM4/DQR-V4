package com.mycompany.drivequestrentals.hilos;

import com.mycompany.drivequestrentals.modelo.Vehiculo;

import java.time.LocalDate;
import java.util.List;

/**
 * Hilo que revisa el estado de mantenimiento de los vehículos registrados
 * y notifica si alguno tiene el mantenimiento vencido o próximo.
 */
public class RegistroMantenimientoThread extends Thread {

    private final List<Vehiculo> vehiculos;

    /**
     * Constructor que recibe la lista de vehículos a revisar.
     *
     * @param vehiculos Lista de vehículos registrados en el sistema.
     */
    public RegistroMantenimientoThread(List<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }

    /**
     * Ejecuta el hilo que revisa las fechas de mantenimiento programado y muestra alertas.
     */
    @Override
    public void run() {
        if (vehiculos == null || vehiculos.isEmpty()) {
            System.out.println("ℹ️ No hay vehículos registrados para verificar mantenimiento.");
            return;
        }

        LocalDate hoy = LocalDate.now();
        boolean alertaMostrada = false;

        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo == null || vehiculo.getProximoMantenimiento() == null) continue;

            LocalDate fechaMantenimiento = vehiculo.getProximoMantenimiento();

            if (fechaMantenimiento.isBefore(hoy)) {
                System.out.printf("⚠️ Mantenimiento VENCIDO para vehículo [%s] (Fecha: %s)%n",
                        vehiculo.getPatente(), fechaMantenimiento);
                alertaMostrada = true;
            } else if (!fechaMantenimiento.isAfter(hoy.plusDays(7))) {
                System.out.printf("🔧 Mantenimiento PRÓXIMO para vehículo [%s] (Fecha: %s)%n",
                        vehiculo.getPatente(), fechaMantenimiento);
                alertaMostrada = true;
            }
        }

        if (!alertaMostrada) {
            System.out.println("✅ Todos los vehículos tienen mantenimientos en regla por ahora.");
        }
    }
}
