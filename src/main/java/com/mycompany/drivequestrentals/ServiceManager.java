package com.mycompany.drivequestrentals;

import com.mycompany.drivequestrentals.servicios.*;

/**
 * Clase utilitaria que centraliza todas las instancias de servicios de la
 * aplicación. Permite un acceso sencillo y evita instanciaciones duplicadas
 * en los distintos controladores.
 */
public final class ServiceManager {

    /**
     * Instancia singleton del administrador de servicios. Se crea de forma
     * perezosa cuando alguno de los servicios es solicitado por primera vez.
     */
    private static ServiceManager instance;

    private ClienteService clienteService;
    private FlotaVehiculosService flotaService;
    private ArriendoService arriendoService;
    private PagoService pagoService;
    private MantenimientoService mantenimientoService;
    private ReservaService reservaService;

    private ServiceManager() {
        // constructor privado para evitar instanciación externa
    }

    /**
     * Devuelve la instancia única del ServiceManager utilizando inicialización
     * perezosa.
     */
    private static synchronized ServiceManager getInstance() {
        if (instance == null) {
            instance = new ServiceManager();
        }
        return instance;
    }

    public static ClienteService getClienteService() {
        return getInstance().obtenerClienteService();
    }

    public static FlotaVehiculosService getFlotaService() {
        return getInstance().obtenerFlotaService();
    }

    public static ArriendoService getArriendoService() {
        return getInstance().obtenerArriendoService();
    }

    public static PagoService getPagoService() {
        return getInstance().obtenerPagoService();
    }

    public static MantenimientoService getMantenimientoService() {
        return getInstance().obtenerMantenimientoService();
    }

    public static ReservaService getReservaService() {
        return getInstance().obtenerReservaService();
    }

    // ===== Métodos de inicialización perezosa por servicio =====
    private ClienteService obtenerClienteService() {
        if (clienteService == null) {
            clienteService = new ClienteService();
        }
        return clienteService;
    }

    private FlotaVehiculosService obtenerFlotaService() {
        if (flotaService == null) {
            flotaService = new FlotaVehiculosService();
        }
        return flotaService;
    }

    private ArriendoService obtenerArriendoService() {
        if (arriendoService == null) {
            arriendoService = new ArriendoService();
        }
        return arriendoService;
    }

    private PagoService obtenerPagoService() {
        if (pagoService == null) {
            pagoService = new PagoService();
        }
        return pagoService;
    }

    private MantenimientoService obtenerMantenimientoService() {
        if (mantenimientoService == null) {
            mantenimientoService = new MantenimientoService();
        }
        return mantenimientoService;
    }

    private ReservaService obtenerReservaService() {
        if (reservaService == null) {
            reservaService = new ReservaService();
        }
        return reservaService;
    }
}
