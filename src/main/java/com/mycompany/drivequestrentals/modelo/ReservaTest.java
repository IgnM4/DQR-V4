/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.drivequestrentals.modelo;

/**
 *
 * @author ignac
 */
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class ReservaTest {

    @Test
    void getVehiculoReturnsAssignedVehicle() {
        VehiculoPasajeros vehiculo = new VehiculoPasajeros("1", "AA-BB-11", "Ford", "Fiesta", 2020, "Disponible", null, 4);
        vehiculo.setPrecioDiario(30000);
        Reserva reserva = new Reserva("R1", new Cliente(), vehiculo,
                LocalDateTime.now(), LocalDateTime.now().plusDays(2), true);
        assertSame(vehiculo, reserva.getVehiculo());
    }

    @Test
    void getPrecioFinalCalculatesBasedOnDaysAndPrice() {
        VehiculoPasajeros vehiculo = new VehiculoPasajeros("2", "BB-CC-22", "Toyota", "Yaris", 2019, "Disponible", null, 4);
        vehiculo.setPrecioDiario(25000);
        LocalDateTime inicio = LocalDateTime.of(2024, 1, 1, 10, 0);
        LocalDateTime fin = LocalDateTime.of(2024, 1, 3, 12, 0);
        Reserva reserva = new Reserva("R2", new Cliente(), vehiculo, inicio, fin, false);
        double esperado = 2 * vehiculo.getPrecioDiario();
        assertEquals(esperado, reserva.getPrecioFinal(), 0.001);
    }
}