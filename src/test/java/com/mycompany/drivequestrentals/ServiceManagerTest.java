package com.mycompany.drivequestrentals;

import com.mycompany.drivequestrentals.servicios.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ServiceManagerTest {

    @Test
    void todasLasInstanciasSonSingleton() {
        assertSame(ServiceManager.getClienteService(), ServiceManager.getClienteService());
        assertSame(ServiceManager.getFlotaService(), ServiceManager.getFlotaService());
        assertSame(ServiceManager.getArriendoService(), ServiceManager.getArriendoService());
        assertSame(ServiceManager.getPagoService(), ServiceManager.getPagoService());
        assertSame(ServiceManager.getMantenimientoService(), ServiceManager.getMantenimientoService());
        assertSame(ServiceManager.getReservaService(), ServiceManager.getReservaService());
    }

    @Test
    void losServiciosNoSonNulos() {
        assertNotNull(ServiceManager.getClienteService());
        assertNotNull(ServiceManager.getFlotaService());
        assertNotNull(ServiceManager.getArriendoService());
        assertNotNull(ServiceManager.getPagoService());
        assertNotNull(ServiceManager.getMantenimientoService());
        assertNotNull(ServiceManager.getReservaService());
    }
}
