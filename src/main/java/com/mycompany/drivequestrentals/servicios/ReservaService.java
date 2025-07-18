package com.mycompany.drivequestrentals.servicios;

import com.mycompany.drivequestrentals.modelo.Reserva;
import com.mycompany.drivequestrentals.excepciones.ReservaDuplicadaException;
import com.mycompany.drivequestrentals.excepciones.ReservaSolapadaException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Servicio que gestiona las reservas de vehículos.
 * Permite crear, eliminar, buscar y listar reservas activas, validando conflictos.
 */
public class ReservaService {

    private final List<Reserva> reservas;

    public ReservaService() {
        // Lista sincronizada para evitar errores en entornos concurrentes
        this.reservas = Collections.synchronizedList(new ArrayList<>());
    }

    /**
     * Registra una nueva reserva validando duplicados y solapamientos.
     *
     * @param nuevaReserva reserva a registrar.
     * @throws ReservaDuplicadaException si el código ya existe.
     * @throws ReservaSolapadaException si hay otra reserva activa que se solapa.
     */
    public void registrarReserva(Reserva nuevaReserva)
            throws ReservaDuplicadaException, ReservaSolapadaException {

        synchronized (reservas) {
            for (Reserva reserva : reservas) {
                if (reserva.getCodigoReserva().equals(nuevaReserva.getCodigoReserva())) {
                    throw new ReservaDuplicadaException("Ya existe una reserva con ese código: " + nuevaReserva.getCodigoReserva());
                }
                if (reserva.seSolapaCon(nuevaReserva)) {
                    throw new ReservaSolapadaException("La reserva se solapa con otra existente para el mismo vehículo.");
                }
            }
            reservas.add(nuevaReserva);
        }
    }

    /**
     * Elimina una reserva según su código.
     *
     * @param codigoReserva código de la reserva.
     * @return true si fue eliminada, false si no se encontró.
     */
    public boolean eliminarReserva(String codigoReserva) {
        synchronized (reservas) {
            return reservas.removeIf(r -> r.getCodigoReserva().equals(codigoReserva));
        }
    }

    /**
     * Busca una reserva por su código.
     *
     * @param codigoReserva código único.
     * @return Reserva encontrada o null si no existe.
     */
    public Reserva buscarReservaPorCodigo(String codigoReserva) {
        synchronized (reservas) {
            return reservas.stream()
                    .filter(r -> r.getCodigoReserva().equals(codigoReserva))
                    .findFirst()
                    .orElse(null);
        }
    }

    /**
     * Devuelve una copia de todas las reservas registradas.
     *
     * @return lista de reservas activas.
     */
    public List<Reserva> listarReservas() {
        synchronized (reservas) {
            return new ArrayList<>(reservas);
        }
    }

    /**
     * Marca como confirmada una reserva específica.
     *
     * @param codigoReserva código de la reserva.
     * @return true si fue confirmada, false si no se encontró.
     */
    public boolean confirmarReserva(String codigoReserva) {
        synchronized (reservas) {
            Reserva reserva = buscarReservaPorCodigo(codigoReserva);
            if (reserva != null) {
                reserva.setConfirmada(true);
                return true;
            }
            return false;
        }
    }
}
