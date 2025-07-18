package com.mycompany.drivequestrentals.modelo;

/**
 * Clase que representa un vehículo de carga.
 * Extiende la clase Vehiculo e implementa IFacturable para calcular boleta de arriendo.
 */
public class VehiculoCarga extends Vehiculo implements IFacturable {

    private double capacidadCarga; // en kilogramos

    /**
     * Constructor con parámetros para inicializar un vehículo de carga.
     */
    public VehiculoCarga(String id, String patente, String marca, String modelo, int anio,
                         String estado, String fotoRuta, double capacidadCarga) {
        super(id, patente, marca, modelo, anio, estado, fotoRuta);
        this.capacidadCarga = capacidadCarga;
    }

    public double getCapacidadCarga() {
        return capacidadCarga;
    }

    public void setCapacidadCarga(double capacidadCarga) {
        this.capacidadCarga = capacidadCarga;
    }

    /**
     * Retorna los datos del vehículo de carga en un formato legible.
     */
    @Override
    public String mostrarDatos() {
        return String.format(
                "Carga - Patente: %s, Marca: %s, Modelo: %s, Año: %d, Estado: %s, Capacidad de carga: %.2f kg",
                getPatente(), getMarca(), getModelo(), getAnio(), getEstado(), capacidadCarga);
    }

    /**
     * Genera una boleta con desglose de costos para el arriendo del vehículo.
     * Incluye días, tarifa diaria, descuento y cálculo de IVA.
     */
    @Override
    public String generarBoleta(int diasArriendo, double tarifaDiaria, boolean aplicarDescuento) {
        double subtotal = diasArriendo * tarifaDiaria;
        double descuento = aplicarDescuento ? subtotal * DESCUENTO_CARGA : 0;
        double neto = subtotal - descuento;
        double iva = neto * IVA;
        double total = neto + iva;

        return String.format("""
                === BOLETA DE ARRIENDO ===
                Vehículo de Carga - Patente: %s
                Días de arriendo: %d
                Tarifa diaria: $%.2f
                Subtotal: $%.2f
                Descuento aplicado: $%.2f
                Neto: $%.2f
                IVA (19%%): $%.2f
                TOTAL A PAGAR: $%.2f
                """, getPatente(), diasArriendo, tarifaDiaria, subtotal, descuento, neto, iva, total);
    }
}
