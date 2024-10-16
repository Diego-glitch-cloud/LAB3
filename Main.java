import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

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