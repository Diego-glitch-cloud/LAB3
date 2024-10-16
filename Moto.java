public class Moto extends Vehiculo {
    // Atributos 
    String tipo;

    // Constructor
    public Moto(String placa, String marca, String modelo, int año, String color,
                float capacidadMotor, float capacidadTanque, float velocidadMax,
                Boolean transmision, double precio, float tamanioRuedas, int estado,
                String tipo) {
    
        super(placa, marca, modelo, año, color, capacidadMotor, capacidadTanque, velocidadMax,
              transmision, precio, tamanioRuedas, estado);
        
   
        this.tipo = tipo;
    }

    // Setters & Getters
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }     
}