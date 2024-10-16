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
            listarVehiculos();
        } else if (e.getSource() == buscarButton) {
            buscarVehiculo();
        } else if (e.getSource() == agregarButton) {
            agregarVehiculo();
        } else if (e.getSource() == eliminarButton) {
            eliminarVehiculo();
        } else if (e.getSource() == estadoButton) {
            mostrarEstadoVehiculos();
        } else if (e.getSource() == montoButton) {
            calcularMontoTotal();
        } else if (e.getSource() == salirButton) {
            System.exit(0);
        }
    }

    private void listarVehiculos() {
        StringBuilder output = new StringBuilder("Seleccione el tipo de vehículo:\n");
        output.append("1 = Autos\n");
        output.append("2 = Motocicletas\n");
        output.append("3 = Camiones\n");

        String tipo = JOptionPane.showInputDialog(output.toString());
        ArrayList<String[]> lista = null;

        switch (tipo) {
            case "1":
                lista = autos;
                break;
            case "2":
                lista = motos;
                break;
            case "3":
                lista = camiones;
                break;
            default:
                JOptionPane.showMessageDialog(this, "Opción inválida");
                return;
        }

        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay vehículos en esta categoría.");
            return;
        }

        StringBuilder listaOutput = new StringBuilder();
        for (String[] fila : lista) {
            for (String dato : fila) {
                listaOutput.append(dato).append(" | ");
            }
            listaOutput.append("\n");
        }

        outputArea.setText(listaOutput.toString());
    }

    private void buscarVehiculo() {
        StringBuilder output = new StringBuilder("Seleccione el tipo de vehículo:\n");
        output.append("1 = Autos\n");
        output.append("2 = Motocicletas\n");
        output.append("3 = Camiones\n");

        String tipo = JOptionPane.showInputDialog(output.toString());
        String matricula = JOptionPane.showInputDialog("Ingrese la matrícula que desea buscar:");
        ArrayList<String[]> base = null;

        switch (tipo) {
            case "1":
                base = autos;
                break;
            case "2":
                base = motos;
                break;
            case "3":
                base = camiones;
                break;
            default:
                JOptionPane.showMessageDialog(this, "Opción inválida.");
                return;
        }

        boolean encontrado = false;
        for (String[] fila : base) {
            if (fila[0].equals(matricula)) {
                StringBuilder vehiculoOutput = new StringBuilder("Vehículo encontrado:\n");
                for (String dato : fila) {
                    vehiculoOutput.append(dato).append(" | ");
                }
                outputArea.setText(vehiculoOutput.toString());
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            JOptionPane.showMessageDialog(this, "Vehículo no encontrado.");
        }
    }

    private void agregarVehiculo() {
        StringBuilder output = new StringBuilder("Seleccione el tipo de vehículo:\n");
        output.append("1 = Autos\n");
        output.append("2 = Motocicletas\n");
        output.append("3 = Camiones\n");
    
        String tipo = JOptionPane.showInputDialog(output.toString());
        
        if (tipo == null || !(tipo.equals("1") || tipo.equals("2") || tipo.equals("3"))) {
            JOptionPane.showMessageDialog(this, "Opción inválida.");
            return;
        }
    
        // Solicitar datos comunes
        String placa = JOptionPane.showInputDialog("Ingrese la placa del vehículo:");
        String marca = JOptionPane.showInputDialog("Ingrese la marca del vehículo:");
        String modelo = JOptionPane.showInputDialog("Ingrese el modelo del vehículo:");
        int año = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el año de fabricación:"));
        String color = JOptionPane.showInputDialog("Ingrese el color del vehículo:");
        float capacidadMotor = Float.parseFloat(JOptionPane.showInputDialog("Ingrese la capacidad del motor (en L o cc para motos):"));
        float capacidadTanque = Float.parseFloat(JOptionPane.showInputDialog("Ingrese la capacidad del tanque (en L):"));
        float velocidadMax = Float.parseFloat(JOptionPane.showInputDialog("Ingrese la velocidad máxima (en km/h):"));
        boolean transmision = Boolean.parseBoolean(JOptionPane.showInputDialog("Ingrese el tipo de transmisión (true para manual, false para automática):"));
        double precio = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el precio del vehículo:"));
        int estado = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el estado del vehículo (1 = disponible, 2 = reservado, 3 = vendido):"));
    
        String[] fila = null;
    
        if (tipo.equals("1")) { // Autos
            int numeroPuertas = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de puertas:"));
            float capacidadMaletero = Float.parseFloat(JOptionPane.showInputDialog("Ingrese la capacidad del maletero (en litros):"));
    
            fila = new String[]{placa, marca, modelo, String.valueOf(año), color, String.valueOf(capacidadMotor),
                                String.valueOf(capacidadTanque), String.valueOf(velocidadMax),
                                String.valueOf(transmision), String.valueOf(precio), String.valueOf(estado),
                                String.valueOf(numeroPuertas), String.valueOf(capacidadMaletero)};
    
            autos.add(fila);
            escribirCsv("autos.csv", fila);
    
        } else if (tipo.equals("2")) { // Motocicletas
            String tipoMoto = JOptionPane.showInputDialog("Ingrese el tipo de motocicleta (deportiva, crucero, etc.):");
    
            fila = new String[]{placa, marca, modelo, String.valueOf(año), color, String.valueOf(capacidadMotor),
                                String.valueOf(capacidadTanque), String.valueOf(velocidadMax),
                                String.valueOf(transmision), String.valueOf(precio), String.valueOf(estado),
                                tipoMoto};
    
            motos.add(fila);
            escribirCsv("motocicletas.csv", fila);
    
        } else if (tipo.equals("3")) { // Camiones
            float capacidadCarga = Float.parseFloat(JOptionPane.showInputDialog("Ingrese la capacidad de carga (en toneladas):"));
            int ejes = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de ejes:"));
    
            fila = new String[]{placa, marca, modelo, String.valueOf(año), color, String.valueOf(capacidadMotor),
                                String.valueOf(capacidadTanque), String.valueOf(velocidadMax),
                                String.valueOf(transmision), String.valueOf(precio), String.valueOf(estado),
                                String.valueOf(capacidadCarga), String.valueOf(ejes)};
    
            camiones.add(fila);
            escribirCsv("camiones.csv", fila);
        }
    
        JOptionPane.showMessageDialog(this, "Vehículo agregado exitosamente.");
    }
    
    private void eliminarVehiculo() {
        StringBuilder output = new StringBuilder("Seleccione el tipo de vehículo:\n");
        output.append("1 = Autos\n");
        output.append("2 = Motocicletas\n");
        output.append("3 = Camiones\n");
    
        String tipo = JOptionPane.showInputDialog(output.toString());
        ArrayList<String[]> baseParaEliminar = null;
        String archivo = "";
    
        switch (tipo) {
            case "1":
                baseParaEliminar = autos;
                archivo = "autos.csv";
                break;
            case "2":
                baseParaEliminar = motos;
                archivo = "motocicletas.csv";
                break;
            case "3":
                baseParaEliminar = camiones;
                archivo = "camiones.csv";
                break;
            default:
                JOptionPane.showMessageDialog(this, "Opción inválida.");
                return;
        }
    
        String placaAEliminar = JOptionPane.showInputDialog("Ingrese la placa del vehículo que desea eliminar:");
        boolean eliminado = false;
    
        // Buscar el vehículo en la copia
        for (int i = 0; i < baseParaEliminar.size(); i++) {
            String[] fila = baseParaEliminar.get(i);
    
            if (fila[0].equals(placaAEliminar)) { // La placa está en la primera columna
                baseParaEliminar.remove(i); // Eliminar el vehículo de la copia
                eliminado = true;
                break; // Salir del bucle después de eliminar
            }
        }
    
        if (eliminado) {
            // Sobrescribir el archivo CSV con la copia actualizada
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, false))) {
                for (String[] fila : baseParaEliminar) {
                    writer.write(String.join(",", fila));
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
    
            JOptionPane.showMessageDialog(this, "Vehículo eliminado exitosamente.");
        } else {
            JOptionPane.showMessageDialog(this, "No se encontró un vehículo con esa placa.");
        }
    }
    
    private void mostrarEstadoVehiculos() {
        int totalDisponiblesAutos = 0;
        int totalReservadosAutos = 0;
        int totalVendidosAutos = 0;
    
        int totalDisponiblesMotos = 0;
        int totalReservadosMotos = 0;
        int totalVendidosMotos = 0;
    
        int totalDisponiblesCamiones = 0;
        int totalReservadosCamiones = 0;
        int totalVendidosCamiones = 0;
    
        // Contar estados de los autos
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
    
        // Contar estados de las motocicletas
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
    
        // Mostrar resultados
        StringBuilder resultados = new StringBuilder("Total de vehículos por categoría:\n");
        resultados.append("Autos: Disponibles: ").append(totalDisponiblesAutos)
                  .append(", Reservados: ").append(totalReservadosAutos)
                  .append(", Vendidos: ").append(totalVendidosAutos).append("\n");
        resultados.append("Motocicletas: Disponibles: ").append(totalDisponiblesMotos)
                  .append(", Reservados: ").append(totalReservadosMotos)
                  .append(", Vendidos: ").append(totalVendidosMotos).append("\n");
        resultados.append("Camiones: Disponibles: ").append(totalDisponiblesCamiones)
                  .append(", Reservados: ").append(totalReservadosCamiones)
                  .append(", Vendidos: ").append(totalVendidosCamiones).append("\n");
    
        JOptionPane.showMessageDialog(this, resultados.toString());
    }

    private void calcularMontoTotal() {
        double totalDisponibles = 0;
        double totalReservados = 0;
        double totalVendidos = 0;
    
        // Calcular total para los autos
        for (String[] auto : autos) {
            switch (auto[10]) { // Suponiendo que el estado está en la posición 10
                case "1":
                    totalDisponibles += Double.parseDouble(auto[9]); // Precio en la posición 9
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
            switch (moto[10]) { // Suponiendo que el estado está en la posición 10
                case "1":
                    totalDisponibles += Double.parseDouble(moto[9]); // Precio en la posición 9
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
            switch (camion[10]) { // Suponiendo que el estado está en la posición 10
                case "1":
                    totalDisponibles += Double.parseDouble(camion[9]); // Precio en la posición 9
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
        StringBuilder resultados = new StringBuilder("Monto total en Quetzales:\n");
        resultados.append("Disponibles: Q").append(String.format("%.2f", totalDisponibles * 7.8)).append("\n");
        resultados.append("Reservados: Q").append(String.format("%.2f", totalReservados * 7.8)).append("\n");
        resultados.append("Vendidos: Q").append(String.format("%.2f", totalVendidos * 7.8)).append("\n");
    
        JOptionPane.showMessageDialog(this, resultados.toString());
    }
    
}
