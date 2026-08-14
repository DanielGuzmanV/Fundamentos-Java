package conceptos_basicos.clases_objetos;

public class Auto {
  // Atributos
  private String marca;
  private String modelo;
  private int velocidadActual;
  private boolean encendido;

  // Constructor (Inicializador)
  public Auto(String marca, String modelo) {
    this.marca = marca;
    this.modelo = modelo;
    this.velocidadActual = 0;
    this.encendido = false;
  }

  // Metodos (Comportamientos)
  public void encender() {
    this.encendido = true;
    System.out.println("El auto " + marca + " | " + modelo + " ha sido encendido");
  }

  public void acelerar(int incremento) {
    if(this.encendido) {
      this.velocidadActual += incremento;
      System.out.println("Acelerando a " + this.velocidadActual + " km/h");
    } else {
      System.out.println("No puedes acelerar, El auto esta apagado");
    }
  }

  // Getters para consultar el estado interno
  public String getMarca() { return marca; }
  public String getModelo() { return modelo; }
  public int getVelocidadActual() { return velocidadActual; }
  public boolean isEncendido() { return encendido; }

}
