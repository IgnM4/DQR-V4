package com.mycompany.drivequestrentals;

import com.mycompany.drivequestrentals.servicios.*;

/**
 * Clase utilitaria que centraliza todas las instancias de servicios de la
 * aplicación. Permite un acceso sencillo y evita instanciaciones duplicadas
 * en los distintos controladores.
 */
public final class ServiceManager {

    private static final ClienteService CLIENTE_SERVICE = new ClienteService();
    private static final FlotaVehiculosService FLOTA_SERVICE = new FlotaVehiculosService();
    private static final ArriendoService ARRIENDO_SERVICE = new ArriendoService();
    private static final PagoService PAGO_SERVICE = new PagoService();
    private static final MantenimientoService MANTENIMIENTO_SERVICE = new MantenimientoService();
    private static final ReservaService RESERVA_SERVICE = new ReservaService();

    private ServiceManager() {
        // Evita instanciación
    }

    public static ClienteService getClienteService() {
        return CLIENTE_SERVICE;
    }

    public static FlotaVehiculosService getFlotaService() {
        return FLOTA_SERVICE;
    }

    public static ArriendoService getArriendoService() {
        return ARRIENDO_SERVICE;
    }

    public static PagoService getPagoService() {
        return PAGO_SERVICE;
    }

    public static MantenimientoService getMantenimientoService() {
        return MANTENIMIENTO_SERVICE;
    }

    public static ReservaService getReservaService() {
        return RESERVA_SERVICE;
    }
}
