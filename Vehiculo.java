public class Vehiculo {
    // Atributos
    String placa;
    String marca;
    String modelo;
    int año;
    String color;
    float capacidadMotor;
    float capacidadTanque;
    float velocidadMax;
    Boolean transmision;
    double precio;
    float tamanioRuedas;
    int estado;

    // Constructor
    public Vehiculo(String placa, String marca, String modelo, int año, String color,
                    float capacidadMotor, float capacidadTanque, float velocidadMax,
                    Boolean transmision, double precio, float tamanioRuedas, int estado) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.año = año;
        this.color = color;
        this.capacidadMotor = capacidadMotor;
        this.capacidadTanque = capacidadTanque;
        this.velocidadMax = velocidadMax;
        this.transmision = transmision;
        this.precio = precio;
        this.tamanioRuedas = tamanioRuedas;
        this.estado = estado;
    }

    // Setters & Getters
    public String getPlaca() {
        return placa;
    }
    public void setPlaca(String placa) {
        this.placa = placa;
    }
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public int getAño() {
        return año;
    }
    public void setAño(int año) {
        this.año = año;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public float getCapacidadMotor() {
        return capacidadMotor;
    }
    public void setCapacidadMotor(float capacidadMotor) {
        this.capacidadMotor = capacidadMotor;
    }
    public float getCapacidadTanque() {
        return capacidadTanque;
    }
    public void setCapacidadTanque(float capacidadTanque) {
        this.capacidadTanque = capacidadTanque;
    }
    public float getVelocidadMax() {
        return velocidadMax;
    }
    public void setVelocidadMax(float velocidadMax) {
        this.velocidadMax = velocidadMax;
    }
    public Boolean getTransmision() {
        return transmision;
    }
    public void setTransmision(Boolean transmision) {
        this.transmision = transmision;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public float getTamanioRuedas() {
        return tamanioRuedas;
    }
    public void setTamanioRuedas(float tamanioRuedas) {
        this.tamanioRuedas = tamanioRuedas;
    }
    public int getEstado() {
        return estado;
    }
    public void setEstado(int estado) {
        this.estado = estado;
    }
}