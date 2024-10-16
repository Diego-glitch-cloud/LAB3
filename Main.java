import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int opcion; // opción elegida en el menú
        int opcion2; // opción para el menú 2
        boolean continuar = true; 


        String menu = """

                1 = Listar vehículos por categoría
                2 = Buscar vehículo por ID 
                3 = Agregar vehículo
                4 = Eliminar Vehículo
                5 = Mostrar estado de los vehículos
                6 = Calcular Monto total en Quetzales por vehículos según su estado
                7 = Salir

                Seleccione el número correspondiente a la opción que desea realizar: """;

        String menu2 = """

                1 = Autos
                2 = Motocicletas
                3 = Camiones 

                Seleccione el número del tipo de vehículo que desea listar/buscar: """;

        String autoscsv = "autos.csv";
        String motoscsv = "motocicletas.csv";
        String camionescsv = "camiones.csv";

        // Leer csv
        ArrayList<String[]> autos = new ArrayList<>();
        ArrayList<String[]> motos = new ArrayList<>();
        ArrayList<String[]> camiones = new ArrayList<>();

        autos = leerCsv(autoscsv);
        motos = leerCsv(motoscsv);
        camiones = leerCsv(camionescsv);

        while (continuar) {
            System.out.print(menu);
            
            // Comprobar si la entrada es un número
            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                // Verificar si el número está dentro del rango permitido por el menú
                if (opcion >= 1 && opcion <= 7) {
                    switch (opcion) {
                        case 1: 
                            ArrayList<String[]> lista = new ArrayList<>();

                            // Pide opción al usuario
                            System.out.print(menu2);
                            opcion2 = scanner.nextInt();
                            
                            switch (opcion2) {
                                case 1:
                                    lista = autos;
                                    break;
                                case 2:
                                    lista = motos;
                                    break;
                                case 3:
                                    lista = camiones;
                                    break;
                                default:
                                    System.out.println("Opción Inválida.");
                            }

                            if (lista.isEmpty()) {
                                break;  // sale del Switch si la lista está vacía
                            }

                            // Si no está vacía, imprime la lista
                            for (int i = 0; i < lista.size(); i++) {
                                String[] fila = lista.get(i);
                                for (int j = 0; j < fila.length; j++) {
                                    System.out.print(fila[j] + "| ");
                                }
                                System.out.println();
                            }
                            break;
                        case 2:
                            boolean encontrado = false;
                            ArrayList<String[]> base = new ArrayList<>();

                            // Pide opción al usuario y también la matrícula que busca
                            System.out.print(menu2);
                            opcion2 = scanner.nextInt();
                            System.out.print("Ingrese la matrícula que desea buscar: ");
                            String matricula = scanner.next();

                            switch (opcion2) {
                                case 1:
                                    base = autos;
                                    break;
                                case 2:
                                    base = motos;
                                    break;
                                case 3:
                                    base = camiones;
                                    break;
                                default:
                                    System.out.println("Opción Inválida.");
                            }
                            
                            // Busca si la matrícula coincide con la base de datos del tipo de vehículo
                            for (int i = 0; i < base.size(); i++) {
                                String[] fila = base.get(i);
                                if (fila[0].equals(matricula)){
                                    System.out.println("Vehículo encontrado, imprimiendo información...");
                                    encontrado = true; 
                                    for (int j = 0; j < fila.length; j++) {
                                        System.out.print(fila[j] + "| ");
                                    }
                                }
                            }
                            System.out.println();

                            if (!encontrado) {
                                System.out.println("Vehículo no encontrado.");
                            }
                        break; 
                        case 3:
                        break;
                        case 4:
                        break;
                        case 5:
                        break;
                        case 6:
                        break;
                        case 7:
                            continuar = false;
                            System.out.println("Saliendo...");
                            break;
                    }
                    
                } else {
                    System.out.println("Opción no válida. Vuelva a intentarlo.");
                }
            } else {
                // Si la entrada no es un número
                System.out.println("Opción no válida. Vuelva a intentarlo.");
                scanner.next(); // Limpiar el escáner
            }
        }

        
        scanner.close(); // Cerrar el escáner

        
    }


    
    // Métodos para leer y escribir de un CSV
    private static ArrayList<String[]> leerCsv(String urlCSV) {
        String fila_completa;
        String[] fila;
        ArrayList<String[]> filas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(urlCSV))) {
            while ((fila_completa = br.readLine()) != null) {
                // Usa el delimitador para dividir cada línea en columnas
                fila = fila_completa.split(",");

                filas.add(fila);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return filas;
    }

    // Función para escribir los cambios en el csv de la base de datos
    private static void escribirCsv(String urlCSV, String[] fila) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(urlCSV, true))) {
            // Escribe los datos de la fila como un mismo String separado por comas
            writer.write(String.join(",", fila));
            writer.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}