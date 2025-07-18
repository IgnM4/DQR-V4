package com.mycompany.drivequestrentals;

import javafx.application.Application;
import java.util.Scanner;

/**
 * Punto de entrada de la aplicación. Aquí se inicializan los servicios
 * principales a través de {@link ServiceManager} y se delega la ejecución
 * en modo gráfico o consola.
 */

import com.mycompany.drivequestrentals.consola.ConsolaControlador;
import com.mycompany.drivequestrentals.ServiceManager;

public class Main {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("===========================================");
            System.out.println("🚗 DriveQuestrentals - Sistema de Arriendos");
            System.out.println("===========================================");
            System.out.println("Seleccione el modo de ejecución:");
            System.out.println("1. Modo Gráfico (JavaFX)");
            System.out.println("2. Modo Consola");
            System.out.print("Opción: ");

            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    System.out.println("🔵 Iniciando aplicación en modo gráfico...");
                    Application.launch(App.class, args);
                    break;
                case "2":
                    System.out.println("🟢 Iniciando aplicación en modo consola...");
                    iniciarModoConsola(scanner);
                    break;
                default:
                    System.out.println("❌ Opción no válida. Cerrando aplicación.");
            }
        }
    }

    /**
     * Lanza el modo consola con menú interactivo de opciones.
     */
    private static void iniciarModoConsola(Scanner scanner) {
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n==== Menú Principal (Consola) ====");
            System.out.println("1. Gestionar Clientes");
            System.out.println("2. Gestionar Vehículos");
            System.out.println("3. Registrar Arriendo");
            System.out.println("4. Registrar Pago");
            System.out.println("5. Registrar Mantenimiento");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    ConsolaControlador.gestionarClientes(scanner);
                    break;
                case "2":
                    ConsolaControlador.gestionarVehiculos(scanner);
                    break;
                case "3":
                    ConsolaControlador.registrarArriendo(scanner);
                    break;
                case "4":
                    ConsolaControlador.registrarPago(scanner);
                    break;
                case "5":
                    ConsolaControlador.registrarMantenimiento(scanner);
                    break;
                case "6":
                    System.out.println("👋 Cerrando sistema. ¡Hasta pronto!");
                    continuar = false;
                    break;
                default:
                    System.out.println("⚠️ Opción inválida. Intente nuevamente.");
            }
        }

    }
}
