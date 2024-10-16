public class Camion extends Vehiculo {
    // Atributos 
    float capacidadCarga;
    int ejes;

    // Constructor 
    public Camion(String placa, String marca, String modelo, int año, String color,
                float capacidadMotor, float capacidadTanque, float velocidadMax,
                Boolean transmision, double precio, float tamanioRuedas, int estado,
                float capacidadCarga, int ejes) {
    
        super(placa, marca, modelo, año, color, capacidadMotor, capacidadTanque, velocidadMax,
              transmision, precio, tamanioRuedas, estado);
        
   
        this.capacidadCarga = capacidadCarga;
        this.ejes = ejes;
    }

    // Getters & Setters 
    public float getCapacidadCarga() {
        return capacidadCarga;
    }
    public void setCapacidadCarga(float capacidadCarga) {
        this.capacidadCarga = capacidadCarga;
    }
    public int getEjes() {
        return ejes;
    }
    public void setEjes(int ejes) {
        this.ejes = ejes;
    } 
    
}