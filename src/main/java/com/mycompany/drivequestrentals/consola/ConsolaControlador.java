package com.mycompany.drivequestrentals.consola;

import com.mycompany.drivequestrentals.modelo.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 * Clase encargada de manejar la interacción por consola con el usuario.
 * Contiene métodos estáticos para gestionar las distintas funcionalidades
 * como clientes, vehículos, arriendos, pagos y mantenimiento.
 */
public class ConsolaControlador {

    private static final Registro registro = Registro.getInstancia();

    /**
     * Menú de opciones para gestionar clientes.
     */
    public static void gestionarClientes(Scanner scanner) {
        System.out.println("\n--- Gestión de Clientes ---");
        System.out.println("1. Registrar nuevo cliente");
        System.out.println("2. Buscar cliente por RUT");
        System.out.println("3. Listar todos los clientes");
        System.out.println("4. Volver al menú principal");
        System.out.print("Seleccione una opción: ");
        String opcion = scanner.nextLine();

        switch (opcion) {
            case "1":
                System.out.print("Ingrese nombre del cliente: ");
                String nombre = scanner.nextLine();
                System.out.print("Ingrese RUT del cliente: ");
                String rut = scanner.nextLine();
                System.out.print("Ingrese correo electrónico: ");
                String correo = scanner.nextLine();
                System.out.print("Ingrese teléfono: ");
                String telefono = scanner.nextLine();

                Cliente nuevoCliente = new Cliente(nombre, rut, telefono, correo, "");
                registro.agregarCliente(nuevoCliente);
                System.out.println("✅ Cliente registrado correctamente.");
                break;
            case "2":
                System.out.print("Ingrese el RUT del cliente a buscar: ");
                String rutBuscado = scanner.nextLine();
                Cliente cliente = registro.buscarClientePorRUT(rutBuscado);
                if (cliente != null) {
                    System.out.println("🔍 Cliente encontrado: " + cliente);
                } else {
                    System.out.println("❌ Cliente no encontrado.");
                }
                break;
            case "3":
                List<Cliente> clientes = registro.obtenerClientes();
                if (clientes.isEmpty()) {
                    System.out.println("📭 No hay clientes registrados.");
                } else {
                    System.out.println("📋 Lista de clientes:");
                    clientes.forEach(System.out::println);
                }
                break;
            case "4":
                return;
            default:
                System.out.println("⚠️ Opción inválida.");
        }
    }

    /**
     * Menú de opciones para gestionar vehículos.
     */
    public static void gestionarVehiculos(Scanner scanner) {
        System.out.println("\n--- Gestión de Vehículos ---");
        System.out.println("1. Registrar nuevo vehículo");
        System.out.println("2. Buscar vehículo por patente");
        System.out.println("3. Listar todos los vehículos");
        System.out.println("4. Volver al menú principal");
        System.out.print("Seleccione una opción: ");
        String opcion = scanner.nextLine();

        switch (opcion) {
            case "1":
                System.out.print("Ingrese marca: ");
                String marca = scanner.nextLine();
                System.out.print("Ingrese modelo: ");
                String modelo = scanner.nextLine();
                System.out.print("Ingrese patente: ");
                String patente = scanner.nextLine();
                System.out.print("Ingrese año: ");
                int anio = Integer.parseInt(scanner.nextLine());

                Vehiculo nuevoVehiculo = new VehiculoPasajeros("V-" + patente, patente, marca, modelo, anio,
                        "Disponible", "", 4);
                registro.agregarVehiculo(nuevoVehiculo);
                System.out.println("✅ Vehículo registrado correctamente.");
                break;
            case "2":
                System.out.print("Ingrese la patente del vehículo: ");
                String patenteBuscar = scanner.nextLine();
                Vehiculo vehiculo = registro.buscarVehiculoPorPatente(patenteBuscar);
                if (vehiculo != null) {
                    System.out.println("🔍 Vehículo encontrado: " + vehiculo);
                } else {
                    System.out.println("❌ Vehículo no encontrado.");
                }
                break;
            case "3":
                List<Vehiculo> vehiculos = registro.obtenerVehiculos();
                if (vehiculos.isEmpty()) {
                    System.out.println("📭 No hay vehículos registrados.");
                } else {
                    System.out.println("📋 Lista de vehículos:");
                    vehiculos.forEach(System.out::println);
                }
                break;
            case "4":
                return;
            default:
                System.out.println("⚠️ Opción inválida.");
        }
    }

    /**
     * Permite registrar un arriendo de un vehículo.
     */
    public static void registrarArriendo(Scanner scanner) {
        System.out.println("\n--- Registro de Arriendo ---");
        System.out.print("Ingrese RUT del cliente: ");
        String rut = scanner.nextLine();
        Cliente cliente = registro.buscarClientePorRUT(rut);

        if (cliente == null) {
            System.out.println("❌ Cliente no encontrado.");
            return;
        }

        System.out.print("Ingrese patente del vehículo: ");
        String patente = scanner.nextLine();
        Vehiculo vehiculo = registro.buscarVehiculoPorPatente(patente);

        if (vehiculo == null) {
            System.out.println("❌ Vehículo no encontrado.");
            return;
        }

        System.out.print("Ingrese fecha de inicio (yyyy-mm-dd): ");
        String fechaInicio = scanner.nextLine();
        System.out.print("Ingrese fecha de término (yyyy-mm-dd): ");
        String fechaTermino = scanner.nextLine();

        LocalDate inicio = LocalDate.parse(fechaInicio);
        LocalDate termino = LocalDate.parse(fechaTermino);
        Arriendo arriendo = new Arriendo(cliente, vehiculo, inicio, termino);
        registro.registrarArriendo(arriendo);
        System.out.println("✅ Arriendo registrado correctamente.");
    }

    /**
     * Permite registrar un pago de arriendo.
     */
    public static void registrarPago(Scanner scanner) {
        System.out.println("\n--- Registro de Pago ---");
        System.out.print("Ingrese ID del arriendo a pagar: ");
        int idArriendo = Integer.parseInt(scanner.nextLine());
        Arriendo arriendo = registro.buscarArriendoPorId(idArriendo);

        if (arriendo == null) {
            System.out.println("❌ Arriendo no encontrado.");
            return;
        }

        System.out.print("Ingrese monto del pago: ");
        double monto = Double.parseDouble(scanner.nextLine());
        double neto = monto / 1.19;
        double iva = monto - neto;
        Pago pago = new Pago(java.util.UUID.randomUUID().toString(), arriendo, neto, iva,
                monto, LocalDate.now(), "Efectivo");
        registro.registrarPago(pago);
        System.out.println("✅ Pago registrado correctamente.");
    }

    /**
     * Permite registrar un mantenimiento de vehículo.
     */
    public static void registrarMantenimiento(Scanner scanner) {
        System.out.println("\n--- Registro de Mantenimiento ---");
        System.out.print("Ingrese patente del vehículo: ");
        String patente = scanner.nextLine();
        Vehiculo vehiculo = registro.buscarVehiculoPorPatente(patente);

        if (vehiculo == null) {
            System.out.println("❌ Vehículo no encontrado.");
            return;
        }

        System.out.print("Ingrese descripción del mantenimiento: ");
        String descripcion = scanner.nextLine();
        System.out.print("Ingrese fecha del mantenimiento (yyyy-mm-dd): ");
        String fecha = scanner.nextLine();

        LocalDate fechaMant = LocalDate.parse(fecha);
        Mantenimiento mantenimiento = new Mantenimiento(
                java.util.UUID.randomUUID().toString(), vehiculo, descripcion,
                fechaMant, 0.0, 0.0);
        registro.registrarMantenimiento(mantenimiento);
        System.out.println("✅ Mantenimiento registrado correctamente.");
    }
}
