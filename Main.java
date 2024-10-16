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
                            System.out.print(menu2);
                            opcion2 = scanner.nextInt();

                            if (opcion2 >= 1 && opcion2 <= 3) {
                                // Solicitar datos comunes
                                System.out.print("Ingrese la placa del vehículo: ");
                                String placa = scanner.next();
                                System.out.print("Ingrese la marca del vehículo: ");
                                String marca = scanner.next();
                                System.out.print("Ingrese el modelo del vehículo: ");
                                String modelo = scanner.next();
                                System.out.print("Ingrese el año de fabricación: ");
                                int año = scanner.nextInt();
                                System.out.print("Ingrese el color del vehículo: ");
                                String color = scanner.next();
                                System.out.print("Ingrese la capacidad del motor (en L o cc para motos): ");
                                float capacidadMotor = scanner.nextFloat();
                                System.out.print("Ingrese la capacidad del tanque (en L): ");
                                float capacidadTanque = scanner.nextFloat();
                                System.out.print("Ingrese la velocidad máxima (en km/h): ");
                                float velocidadMax = scanner.nextFloat();
                                System.out.print("Ingrese el tipo de transmisión (true para manual, false para automática): ");
                                boolean transmision = scanner.nextBoolean();
                                System.out.print("Ingrese el precio del vehículo: ");
                                double precio = scanner.nextDouble();
                                System.out.print("Ingrese el estado del vehículo (1 = disponible, 2 = reservado, 3 = vendido): ");
                                int estado = scanner.nextInt();

                                // Crear fila para agregar al CSV y a la lista correspondiente
                                String[] fila = null;

                                if (opcion2 == 1) { // Autos
                                    System.out.print("Ingrese el número de puertas: ");
                                    int numeroPuertas = scanner.nextInt();
                                    System.out.print("Ingrese la capacidad del maletero (en litros): ");
                                    float capacidadMaletero = scanner.nextFloat();

                                    // Crear array de String para representar el auto
                                    fila = new String[] { placa, marca, modelo, String.valueOf(año), color, String.valueOf(capacidadMotor),
                                                        String.valueOf(capacidadTanque), String.valueOf(velocidadMax),
                                                        String.valueOf(transmision), String.valueOf(precio), String.valueOf(estado),
                                                        String.valueOf(numeroPuertas), String.valueOf(capacidadMaletero) };

                                    // Añadir a la lista de autos
                                    autos.add(fila);
                                    escribirCsv(autoscsv, fila);

                                } else if (opcion2 == 2) { // Motocicletas
                                    System.out.print("Ingrese el tipo de motocicleta (deportiva, crucero, etc.): ");
                                    String tipo = scanner.next();

                                    // Crear array de String para representar la motocicleta
                                    fila = new String[] { placa, marca, modelo, String.valueOf(año), color, String.valueOf(capacidadMotor),
                                                        String.valueOf(capacidadTanque), String.valueOf(velocidadMax),
                                                        String.valueOf(transmision), String.valueOf(precio), String.valueOf(estado),
                                                        tipo };

                                    // Añadir a la lista de motos
                                    motos.add(fila);
                                    escribirCsv(motoscsv, fila);

                                } else if (opcion2 == 3) { // Camiones
                                    System.out.print("Ingrese la capacidad de carga (en toneladas): ");
                                    float capacidadCarga = scanner.nextFloat();
                                    System.out.print("Ingrese el número de ejes: ");
                                    int ejes = scanner.nextInt();

                                    // Crear array de String para representar el camión
                                    fila = new String[] { placa, marca, modelo, String.valueOf(año), color, String.valueOf(capacidadMotor),
                                                        String.valueOf(capacidadTanque), String.valueOf(velocidadMax),
                                                        String.valueOf(transmision), String.valueOf(precio), String.valueOf(estado),
                                                        String.valueOf(capacidadCarga), String.valueOf(ejes) };

                                    // Añadir a la lista de camiones
                                    camiones.add(fila);
                                    escribirCsv(camionescsv, fila);
                                }

                                System.out.println("Vehículo agregado exitosamente.");
                            } else {
                                System.out.println("Opción inválida.");
                            }
                        break;                        
                        case 4:
                            System.out.print(menu2);
                            opcion2 = scanner.nextInt();

                            ArrayList<String[]> baseParaEliminar = null; // esta es una copia de la base de datos que luego se va a sobreescribir en el csv
                            String archivo = ""; // Archivo csv según el tipo de vehículo que desea eliminar, es donde se sobreescribirá la base de datos al final 

                            switch (opcion2) {
                                case 1:
                                    baseParaEliminar = autos;
                                    archivo = autoscsv;
                                    break;
                                case 2:
                                    baseParaEliminar = motos;
                                    archivo = motoscsv;
                                    break;
                                case 3:
                                    baseParaEliminar = camiones;
                                    archivo = camionescsv;
                                    break;
                                default:
                                    System.out.println("Opción inválida.");
                                    break;
                            }

                                System.out.print("Ingrese la placa del vehículo que desea eliminar: ");
                                String placaAEliminar = scanner.next();
                                boolean eliminado = false;

                                // Buscar el vehículo en la copia
                                for (int i = 0; i < baseParaEliminar.size(); i++) {
                                    String[] fila = baseParaEliminar.get(i);

                                    if (fila[0].equals(placaAEliminar)) { // La placa está en la primera columna index 0
                                        baseParaEliminar.remove(i); // Eliminar el vehículo de la copia
                                        eliminado = true;
                                        break; // Salir del bucle después de eliminar para que no siga corriendo
                                    }
                                }

                                // en caso de que se haya eliminado el vehículo
                                if (eliminado) {
                                    // Sobrescribir el archivo CSV con la copia actualizada
                                    // es una función similar que la utilizada para leer los csv
                                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, false))) {
                                        for (String[] fila : baseParaEliminar) {
                                            writer.write(String.join(",", fila));
                                            writer.newLine();
                                        }
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }

                                    System.out.println("Vehículo eliminado exitosamente.");
                                } else {
                                    System.out.println("No se encontró un vehículo con esa placa.");
                                }
                        break;
                        case 5:
                            int totalDisponiblesAutos = 0;
                            int totalReservadosAutos = 0;
                            int totalVendidosAutos = 0;

                            int totalDisponiblesMotos = 0;
                            int totalReservadosMotos = 0;
                            int totalVendidosMotos = 0;

                            int totalDisponiblesCamiones = 0;
                            int totalReservadosCamiones = 0;
                            int totalVendidosCamiones = 0;

                            // itero en la lista de autos y cuento sus estados
                            for (String[] auto : autos) {
                                switch (auto[10]) { 
                                    case "1":
                                        totalDisponiblesAutos++;
                                        break;
                                    case "2":
                                        totalReservadosAutos++;
                                        break;
                                    case "3":
                                        totalVendidosAutos++;
                                        break;
                                }
                            }

                            // Cuento los estados de las motos
                            for (String[] moto : motos) {
                                switch (moto[10]) { 
                                    case "1":
                                        totalDisponiblesMotos++;
                                        break;
                                    case "2":
                                        totalReservadosMotos++;
                                        break;
                                    case "3":
                                        totalVendidosMotos++;
                                        break;
                                }
                            }

                            // Contar estados de los camiones
                            for (String[] camion : camiones) {
                                switch (camion[10]) { 
                                    case "1":
                                        totalDisponiblesCamiones++;
                                        break;
                                    case "2":
                                        totalReservadosCamiones++;
                                        break;
                                    case "3":
                                        totalVendidosCamiones++;
                                        break;
                                }
                            }

                            // Ahora imprimo los resultados obtenidos
                            System.out.println("Total de vehículos por categoría:");
                            System.out.println("Autos: Disponibles: " + totalDisponiblesAutos + ", Reservados: " + totalReservadosAutos + ", Vendidos: " + totalVendidosAutos);
                            System.out.println("Motocicletas: Disponibles: " + totalDisponiblesMotos + ", Reservados: " + totalReservadosMotos + ", Vendidos: " + totalVendidosMotos);
                            System.out.println("Camiones: Disponibles: " + totalDisponiblesCamiones + ", Reservados: " + totalReservadosCamiones + ", Vendidos: " + totalVendidosCamiones);
                            break;

                        case 6:
                            double totalDisponibles = 0;
                            double totalReservados = 0;
                            double totalVendidos = 0;

                            // Calcular total para los autos
                            for (String[] auto : autos) {
                                switch (auto[10]) {
                                    case "1":
                                        totalDisponibles += Double.parseDouble(auto[9]);
                                        break;
                                    case "2":
                                        totalReservados += Double.parseDouble(auto[9]);
                                        break;
                                    case "3":
                                        totalVendidos += Double.parseDouble(auto[9]);
                                        break;
                                }
                            }

                            // Calcular total para las motocicletas
                            for (String[] moto : motos) {
                                switch (moto[10]) { 
                                    case "1":
                                        totalDisponibles += Double.parseDouble(moto[9]); 
                                        break;
                                    case "2":
                                        totalReservados += Double.parseDouble(moto[9]);
                                        break;
                                    case "3":
                                        totalVendidos += Double.parseDouble(moto[9]);
                                        break;
                                }
                            }

                            // Calcular total para los camiones
                            for (String[] camion : camiones) {
                                switch (camion[10]) { 
                                    case "1":
                                        totalDisponibles += Double.parseDouble(camion[9]); 
                                        break;
                                    case "2":
                                        totalReservados += Double.parseDouble(camion[9]);
                                        break;
                                    case "3":
                                        totalVendidos += Double.parseDouble(camion[9]);
                                        break;
                                }
                            }

                            // Mostrar resultados
                            System.out.println("Monto total en Quetzales:");
                            System.out.printf("Disponibles: Q%.2f%n", totalDisponibles * 7.8);
                            System.out.printf("Reservados: Q%.2f%n", totalReservados * 7.8);
                            System.out.printf("Vendidos: Q%.2f%n", totalVendidos * 7.8);
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

    // Función para traducir el estado de un número a letras con la intención de imprimirlo
    private static String obtenerEstado(String estado) {
        switch (estado) {
            case "1":
                return "Disponible";
            case "2":
                return "Reservado";
            case "3":
                return "Vendido";
            default:
                return "Desconocido";
        }
    }
    
}