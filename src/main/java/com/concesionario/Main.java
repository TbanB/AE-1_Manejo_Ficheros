package com.concesionario;

import com.concesionario.controllers.CocheController;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CocheController controller = new CocheController();
        Scanner scanner = new Scanner(System.in);

        int opcion;
        do {
            mostrarMenu();
            System.out.print("Selecciona una opción: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Opcion incorrecta.");
                scanner.next();
            }
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    opcionAñadirCoche(controller, scanner);
                    break;
                case 2:
                    opcionEliminarCoche(controller, scanner);
                    break;
                case 3:
                    opcionConsultarCoche(controller, scanner);
                    break;
                case 4:
                    opcionListarCoches(controller);
                    break;
                case 5:
                    System.out.println(controller.exportCarsToCSV());
                    break;
                case 6:
                    System.out.println(controller.saveData());
                    System.out.println("Cerrando la app. ¡Hasta pronto!");
                    break;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        } while (opcion != 6);

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n--- Menú ---");
        System.out.println("1. Añadir nuevo coche");
        System.out.println("2. Borrar coche por ID");
        System.out.println("3. Consultar coche por ID");
        System.out.println("4. Listar todos los coches");
        System.out.println("5. Exportar coches a archivo CSV");
        System.out.println("6. Guardar y salir");
    }

    private static void opcionAñadirCoche(CocheController controller, Scanner scanner) {
        try {
            System.out.print("ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Matrícula: ");
            String matricula = scanner.nextLine();
            System.out.print("Marca: ");
            String marca = scanner.nextLine();
            System.out.print("Modelo: ");
            String modelo = scanner.nextLine();
            System.out.print("Color: ");
            String color = scanner.nextLine();

            System.out.println(controller.addCar(id, matricula, marca, modelo, color));
        } catch (Exception e) {
            System.out.println("Error al añadir el coche: " + e.getMessage());
            scanner.nextLine();
        }
    }

    private static void opcionEliminarCoche(CocheController controller, Scanner scanner) {
        try {
            System.out.print("ID del coche a eliminar: ");
            int idEliminar = scanner.nextInt();
            System.out.println(controller.removeCarById(idEliminar));
        } catch (Exception e) {
            System.out.println("Error al eliminar el coche: " + e.getMessage());
            scanner.nextLine();
        }
    }

    private static void opcionConsultarCoche(CocheController controller, Scanner scanner) {
        try {
            System.out.print("ID del coche a consultar: ");
            int idConsultar = scanner.nextInt();
            System.out.println(controller.getCarById(idConsultar));
        } catch (Exception e) {
            System.out.println("Error al consultar el coche: " + e.getMessage());
            scanner.nextLine();
        }
    }

    private static void opcionListarCoches(CocheController controller) {
        System.out.println(controller.getAllCars());
    }
}
