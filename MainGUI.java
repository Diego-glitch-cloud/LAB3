import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class MainGUI extends JFrame implements ActionListener {
    private ArrayList<String[]> autos;
    private ArrayList<String[]> motos;
    private ArrayList<String[]> camiones;

    private JButton listarButton;
    private JButton buscarButton;
    private JButton agregarButton;
    private JButton eliminarButton;
    private JButton estadoButton;
    private JButton montoButton;
    private JButton salirButton;

    private JTextArea outputArea;

    public MainGUI() {
        setTitle("Gestión de Vehículos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Crear área de salida
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        add(scrollPane, BorderLayout.CENTER);

        // Crear panel de botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(7, 1));

        listarButton = new JButton("Listar vehículos por categoría");
        buscarButton = new JButton("Buscar vehículo por ID");
        agregarButton = new JButton("Agregar vehículo");
        eliminarButton = new JButton("Eliminar Vehículo");
        estadoButton = new JButton("Mostrar estado de los vehículos");
        montoButton = new JButton("Calcular Monto total");
        salirButton = new JButton("Salir");

        // Añadir ActionListener
        listarButton.addActionListener(this);
        buscarButton.addActionListener(this);
        agregarButton.addActionListener(this);
        eliminarButton.addActionListener(this);
        estadoButton.addActionListener(this);
        montoButton.addActionListener(this);
        salirButton.addActionListener(this);

        // Añadir botones al panel
        buttonPanel.add(listarButton);
        buttonPanel.add(buscarButton);
        buttonPanel.add(agregarButton);
        buttonPanel.add(eliminarButton);
        buttonPanel.add(estadoButton);
        buttonPanel.add(montoButton);
        buttonPanel.add(salirButton);

        add(buttonPanel, BorderLayout.WEST);

        // Leer CSV
        String autoscsv = "autos.csv";
        String motoscsv = "motocicletas.csv";
        String camionescsv = "camiones.csv";

        autos = leerCsv(autoscsv);
        motos = leerCsv(motoscsv);
        camiones = leerCsv(camionescsv);
    }

    public static void main(String[] args) {
        // Usar SwingUtilities para asegurar que la creación de la GUI se ejecute en el hilo de despacho de eventos
        SwingUtilities.invokeLater(() -> {
            MainGUI gui = new MainGUI(); // Crear una instancia de MainGUI
            gui.setVisible(true); // Hacer visible la ventana
        });
    }

    // Métodos para leer y escribir de un CSV
    private ArrayList<String[]> leerCsv(String urlCSV) {
        String fila_completa;
        String[] fila;
        ArrayList<String[]> filas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(urlCSV))) {
            while ((fila_completa = br.readLine()) != null) {
                fila = fila_completa.split(",");
                filas.add(fila);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return filas;
    }

    private void escribirCsv(String urlCSV, String[] fila) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(urlCSV, true))) {
            writer.write(String.join(",", fila));
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == listarButton) {
            StringBuilder output = new StringBuilder("Seleccione el tipo de vehículo:\n");
            output.append("1 = Autos\n");
            output.append("2 = Motocicletas\n");
            output.append("3 = Camiones\n");

            String tipo = JOptionPane.showInputDialog(output.toString());
            if (tipo != null) {
                switch (tipo) {
                    case "1":
                        mostrarVehiculos(autos);
                        break;
                    case "2":
                        mostrarVehiculos(motos);
                        break;
                    case "3":
                        mostrarVehiculos(camiones);
                        break;
                    default:
                        JOptionPane.showMessageDialog(this, "Opción inválida");
                }
            }
        } else if (e.getSource() == salirButton) {
            System.exit(0);
        }
    }

    private void mostrarVehiculos(ArrayList<String[]> vehiculos) {
        StringBuilder output = new StringBuilder();
        for (String[] fila : vehiculos) {
            for (String dato : fila) {
                output.append(dato).append(" | ");
            }
            output.append("\n");
        }
        outputArea.setText(output.toString());
    }
}
