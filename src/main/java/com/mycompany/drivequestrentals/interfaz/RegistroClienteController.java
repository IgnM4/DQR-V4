package com.mycompany.drivequestrentals.interfaz;

import com.mycompany.drivequestrentals.modelo.Cliente;
import com.mycompany.drivequestrentals.servicios.ClienteService;
import com.mycompany.drivequestrentals.ServiceManager;
import com.mycompany.drivequestrentals.App;
import com.mycompany.drivequestrentals.util.AlertaUtil;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Controlador para la vista de registro de clientes.
 * Permite agregar clientes al sistema validando los campos requeridos.
 */
public class RegistroClienteController {

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellido;
    @FXML
    private TextField txtRutOPasaporte;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtEmail;

    private final ClienteService clienteService = ServiceManager.getClienteService();

    /**
     * Método principal para registrar un nuevo cliente.
     * Valida los campos y registra el cliente si son válidos.
     */
    @FXML
    private void registrarCliente() {
        if (!validarCampos()) {
            mostrarAlerta("⚠️ Todos los campos son obligatorios.");
            return;
        }

        Cliente cliente = new Cliente(
                txtNombre.getText().trim(),
                txtApellido.getText().trim(),
                txtRutOPasaporte.getText().trim(),
                txtTelefono.getText().trim(),
                txtEmail.getText().trim()
        );

        clienteService.registrarCliente(cliente);
        mostrarAlerta("✅ Cliente registrado correctamente.");
        limpiarCampos();
    }

    /**
     * Valida que todos los campos de texto estén completos.
     *
     * @return true si todos los campos contienen datos, false en caso contrario.
     */
    private boolean validarCampos() {
        return !txtNombre.getText().trim().isEmpty()
                && !txtApellido.getText().trim().isEmpty()
                && !txtRutOPasaporte.getText().trim().isEmpty()
                && !txtTelefono.getText().trim().isEmpty()
                && !txtEmail.getText().trim().isEmpty();
    }

    /**
     * Limpia todos los campos del formulario.
     */
    private void limpiarCampos() {
        txtNombre.clear();
        txtApellido.clear();
        txtRutOPasaporte.clear();
        txtTelefono.clear();
        txtEmail.clear();
    }

    /**
     * Muestra una alerta informativa reutilizando el utilitario.
     *
     * @param mensaje Mensaje a mostrar.
     */
    private void mostrarAlerta(String mensaje) {
        AlertaUtil.mostrarAlerta(mensaje);
    }

    /**
     * Navega de regreso al menú principal.
     */
    @FXML
    private void volverAlMenu() {
        try {
            App.setRoot("menuPrincipal");
        } catch (Exception e) {
            System.err.println("Error al volver al menú: " + e.getMessage());
        }
    }
}
