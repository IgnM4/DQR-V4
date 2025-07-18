package com.mycompany.drivequestrentals.modelo;

/**
 * Clase que representa un vehículo destinado al transporte de pasajeros.
 * Extiende la clase Vehiculo e implementa la interfaz IFacturable.
 * Permite calcular el detalle de una boleta para arriendo con o sin descuento.
 */
public class VehiculoPasajeros extends Vehiculo implements IFacturable {

    private int numeroPasajeros;

    /**
     * Constructor vacío necesario para frameworks o instancias manuales.
     */
    public VehiculoPasajeros() {
        super(); // Llama al constructor vacío de la superclase Vehiculo
    }

    /**
     * Constructor completo que inicializa todos los atributos del vehículo.
     * 
     * @param id              Identificador único del vehículo
     * @param patente         Patente del vehículo
     * @param marca           Marca del vehículo
     * @param modelo          Modelo del vehículo
     * @param anio            Año de fabricación
     * @param estado          Estado del vehículo (disponible, arrendado, en mantención)
     * @param fotoRuta        Ruta de la imagen del vehículo
     * @param numeroPasajeros Capacidad de pasajeros
     */
    public VehiculoPasajeros(String id, String patente, String marca, String modelo, int anio,
                             String estado, String fotoRuta, int numeroPasajeros) {
        super(id, patente, marca, modelo, anio, estado, fotoRuta);
        this.numeroPasajeros = numeroPasajeros;
    }

    public int getNumeroPasajeros() {
        return numeroPasajeros;
    }

    public void setNumeroPasajeros(int numeroPasajeros) {
        this.numeroPasajeros = numeroPasajeros;
    }

    /**
     * Retorna una descripción detallada del vehículo de pasajeros.
     *
     * @return String con información clave del vehículo.
     */
    @Override
    public String mostrarDatos() {
        return String.format("Vehículo de Pasajeros - Patente: %s | Marca: %s | Modelo: %s | Año: %d | Estado: %s | Capacidad: %d pasajeros",
                getPatente(), getMarca(), getModelo(), getAnio(), getEstado(), numeroPasajeros);
    }

    /**
     * Calcula y genera el detalle de una boleta de arriendo para el vehículo.
     * 
     * @param diasArriendo     Número de días de arriendo
     * @param tarifaDiaria     Costo diario del arriendo
     * @param aplicarDescuento Si se aplica descuento por tipo de vehículo
     * @return Boleta detallada como String
     */
    @Override
    public String generarBoleta(int diasArriendo, double tarifaDiaria, boolean aplicarDescuento) {
        double subtotal = diasArriendo * tarifaDiaria;
        double descuento = aplicarDescuento ? subtotal * DESCUENTO_PASAJEROS : 0;
        double neto = subtotal - descuento;
        double iva = neto * IVA;
        double total = neto + iva;

        return String.format("""
                === BOLETA DE ARRIENDO ===
                Vehículo de Pasajeros - Patente: %s
                Días de arriendo     : %d
                Tarifa diaria        : $%.2f
                Subtotal             : $%.2f
                Descuento aplicado   : $%.2f
                Neto                 : $%.2f
                IVA (19%%)            : $%.2f
                TOTAL A PAGAR        : $%.2f
                """, getPatente(), diasArriendo, tarifaDiaria, subtotal, descuento, neto, iva, total);
    }
}
