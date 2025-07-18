package com.mycompany.drivequestrentals.interfaz;

import com.mycompany.drivequestrentals.modelo.Vehiculo;
import com.mycompany.drivequestrentals.modelo.VehiculoCarga;
import com.mycompany.drivequestrentals.modelo.VehiculoPasajeros;
import com.mycompany.drivequestrentals.servicios.FlotaVehiculosService;
import com.mycompany.drivequestrentals.ServiceManager;
import com.mycompany.drivequestrentals.App;
import com.mycompany.drivequestrentals.util.AlertaUtil;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * Controlador para la vista de registro de vehículos.
 * Permite registrar vehículos de carga o pasajeros al sistema.
 */
public class RegistroVehiculoController {

    private static final String DISPONIBLE = "Disponible";
    private static final String IMAGEN_DEFAULT = "ruta/por/defecto.jpg";

    @FXML private TextField txtPatente;
    @FXML private TextField txtMarca;
    @FXML private TextField txtModelo;
    @FXML private TextField txtAnio;
    @FXML private TextField txtValorDiario;
    @FXML private ComboBox<String> comboTipoVehiculo;
    @FXML private TextField txtCapacidadCarga;
    @FXML private TextField txtCantidadAsientos;

    private final FlotaVehiculosService flotaService = ServiceManager.getFlotaService();

    @FXML
    public void initialize() {
        comboTipoVehiculo.getItems().addAll("Carga", "Pasajeros");
        comboTipoVehiculo.setOnAction(e -> actualizarCampos());
    }

    /**
     * Habilita los campos correspondientes según tipo de vehículo.
     */
    private void actualizarCampos() {
        String tipo = comboTipoVehiculo.getValue();
        txtCapacidadCarga.setDisable(!"Carga".equals(tipo));
        txtCantidadAsientos.setDisable(!"Pasajeros".equals(tipo));
    }

    /**
     * Valida los campos básicos requeridos para cualquier tipo de vehículo.
     */
    private boolean validarCamposBase() {
        if (txtPatente.getText().isEmpty() || txtMarca.getText().isEmpty() ||
            txtModelo.getText().isEmpty() || comboTipoVehiculo.getValue() == null) {
            mostrarAlerta("Todos los campos son obligatorios.");
            return false;
        }
        return true;
    }

    /**
     * Registra un nuevo vehículo, validando todos los campos necesarios según tipo.
     */
    @FXML
    private void registrarVehiculo() {
        if (!validarCamposBase()) return;

        try {
            String patente = txtPatente.getText().trim();
            String marca = txtMarca.getText().trim();
            String modelo = txtModelo.getText().trim();
            int anio = Integer.parseInt(txtAnio.getText().trim());
            double valorDiario = Double.parseDouble(txtValorDiario.getText().trim());
            String tipo = comboTipoVehiculo.getValue();

            Vehiculo vehiculo = crearVehiculoSegunTipo(tipo, patente, marca, modelo, anio, valorDiario);
            if (vehiculo == null) return;

            flotaService.agregarVehiculo(vehiculo);
            mostrarAlerta("✅ Vehículo registrado correctamente.");
            limpiarCampos();

        } catch (NumberFormatException e) {
            mostrarAlerta("❌ Error en formatos numéricos. Verifique año, valor, capacidad o asientos.");
        }
    }

    /**
     * Crea el objeto Vehiculo según el tipo seleccionado.
     */
    private Vehiculo crearVehiculoSegunTipo(String tipo, String patente, String marca, String modelo, int anio, double valorDiario) {
        String uuid = java.util.UUID.randomUUID().toString();

        try {
            if ("Carga".equals(tipo)) {
                double capacidad = Double.parseDouble(txtCapacidadCarga.getText().trim());
                VehiculoCarga vehiculoCarga = new VehiculoCarga(uuid, patente, marca, modelo, anio, DISPONIBLE, IMAGEN_DEFAULT, capacidad);
                vehiculoCarga.setPrecioDiario(valorDiario);
                return vehiculoCarga;

            } else if ("Pasajeros".equals(tipo)) {
                int asientos = Integer.parseInt(txtCantidadAsientos.getText().trim());
                VehiculoPasajeros vehiculoPasajeros = new VehiculoPasajeros(uuid, patente, marca, modelo, anio, DISPONIBLE, IMAGEN_DEFAULT, asientos);
                vehiculoPasajeros.setPrecioDiario(valorDiario);
                return vehiculoPasajeros;
            } else {
                mostrarAlerta("Tipo de vehículo no válido.");
                return null;
            }
        } catch (NumberFormatException e) {
            mostrarAlerta("❌ Error en datos específicos del tipo de vehículo.");
            return null;
        }
    }

    /**
     * Limpia todos los campos del formulario.
     */
    private void limpiarCampos() {
        txtPatente.clear();
        txtMarca.clear();
        txtModelo.clear();
        txtAnio.clear();
        txtValorDiario.clear();
        txtCapacidadCarga.clear();
        txtCantidadAsientos.clear();
        comboTipoVehiculo.getSelectionModel().clearSelection();
        actualizarCampos();
    }

    /**
     * Muestra un mensaje informativo al usuario.
     */
    private void mostrarAlerta(String mensaje) {
        AlertaUtil.mostrarAlerta(mensaje);
    }

    /**
     * Regresa al menú principal.
     */
    @FXML
    private void volverAlMenu() {
        try {
            App.setRoot(" ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
