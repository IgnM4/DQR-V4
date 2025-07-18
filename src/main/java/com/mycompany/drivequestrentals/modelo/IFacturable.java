package com.mycompany.drivequestrentals.modelo;

/**
 * Interfaz funcional para operaciones de facturación en arriendos de vehículos.
 * Proporciona constantes fiscales estándar y un método para generar boletas detalladas.
 */
@FunctionalInterface
public interface IFacturable {

    // ✅ Constante de impuesto al valor agregado (IVA)
    double IVA = 0.19;

    // ✅ Descuento por vehículos de carga
    double DESCUENTO_CARGA = 0.07;

    // ✅ Descuento por vehículos de pasajeros
    double DESCUENTO_PASAJEROS = 0.12;

    /**
     * Genera una boleta detallada para un arriendo específico.
     * Calcula el subtotal, el IVA y, opcionalmente, un descuento.
     *
     * @param diasArriendo      cantidad de días del arriendo
     * @param tarifaDiaria      tarifa base diaria del vehículo
     * @param aplicarDescuento  indica si se debe aplicar descuento
     * @return texto con el detalle completo de la boleta
     */
    String generarBoleta(int diasArriendo, double tarifaDiaria, boolean aplicarDescuento);
}
