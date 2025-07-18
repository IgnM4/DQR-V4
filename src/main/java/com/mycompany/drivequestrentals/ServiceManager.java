package com.mycompany.drivequestrentals;

import com.mycompany.drivequestrentals.servicios.*;

/**
 * Clase utilitaria que centraliza todas las instancias de servicios de la
 * aplicación. Permite un acceso sencillo y evita instanciaciones duplicadas
 * en los distintos controladores.
 */
public final class ServiceManager {

    // Instancia única para seguir el patrón Singleton
    private static ServiceManager instance;

    private final ClienteService clienteService;
    private final FlotaVehiculosService flotaService;
    private final ArriendoService arriendoService;
    private final PagoService pagoService;
    private final MantenimientoService mantenimientoService;
    private final ReservaService reservaService;

    private ServiceManager() {
        clienteService = new ClienteService();
        flotaService = new FlotaVehiculosService();
        arriendoService = new ArriendoService();
        pagoService = new PagoService();
        mantenimientoService = new MantenimientoService();
        reservaService = new ReservaService();
    }

    /**
     * Obtiene la instancia única del gestor de servicios.
     *
     * @return instancia singleton de {@code ServiceManager}
     */
    public static synchronized ServiceManager getInstance() {
        if (instance == null) {
            instance = new ServiceManager();
        }
        return instance;
    }

    public static ClienteService getClienteService() {
        return getInstance().clienteService;
    }

    public static FlotaVehiculosService getFlotaService() {
        return getInstance().flotaService;
    }

    public static ArriendoService getArriendoService() {
        return getInstance().arriendoService;
    }

    public static PagoService getPagoService() {
        return getInstance().pagoService;
    }

    public static MantenimientoService getMantenimientoService() {
        return getInstance().mantenimientoService;
    }

    public static ReservaService getReservaService() {
        return getInstance().reservaService;
    }
}
