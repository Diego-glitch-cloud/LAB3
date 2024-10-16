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
        boolean continuar = true; 


        String menu = """
                Seleccione el número correspondiente a la opción que desea realizar: 

                1 = Listar vehículos por categoría
                2 = Buscar vehículo por ID 
                3 = Agregar vehículo
                4 = Eliminar Vehículo
                5 = Mostrar estado de los vehículos
                6 = Calcular Monto total en Quetzales por vehículos según su estado
                7 = Salir
                """;

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
            System.out.println(menu);
            System.out.print("Ingrese la opción que desea ejecutar: ");
            
            // Comprobar si la entrada es un número
            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                // Verificar si el número está dentro del rango permitido por el menú
                if (opcion >= 1 && opcion <= 7) {

                    // switch switch switch 
                    switch (opcion) {
                        case 1: 
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                        case 6:
                        case 7:
                            continuar = false;
                            System.out.println("Saliendo...");
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