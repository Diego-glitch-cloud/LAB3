public class Auto extends Vehiculo {
    // Atributos 
    int numeroPuertas;
    float capacidadMaletero;

    // Constructor
    public Auto(String placa, String marca, String modelo, int año, String color,
                float capacidadMotor, float capacidadTanque, float velocidadMax,
                Boolean transmision, double precio, float tamanioRuedas, int estado,
                int numeroPuertas, float capacidadMaletero) {
    
        super(placa, marca, modelo, año, color, capacidadMotor, capacidadTanque, velocidadMax,
              transmision, precio, tamanioRuedas, estado);
        
   
        this.numeroPuertas = numeroPuertas;
        this.capacidadMaletero = capacidadMaletero;
    }

    // Setters & Getters
    public int getNumeroPuertas() {
        return numeroPuertas;
    }
    public void setNumeroPuertas(int numeroPuertas) {
        this.numeroPuertas = numeroPuertas;
    }
    public float getCapacidadMaletero() {
        return capacidadMaletero;
    }
    public void setCapacidadMaletero(float capacidadMaletero) {
        this.capacidadMaletero = capacidadMaletero;
    }
}