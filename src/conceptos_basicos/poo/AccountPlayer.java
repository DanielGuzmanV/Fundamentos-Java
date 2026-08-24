package conceptos_basicos.poo;

public class AccountPlayer {
  // 1. Atributos privado
  private String nickname;
  private int nivel;
  private double saldoMonedas;

  // constructor
  public AccountPlayer(String nickname, int nivelInicial, double saldoInicial) {
    this.nickname = nickname;
    setNivel(nivelInicial);
    setSaldoMonedas(saldoInicial);
  }

  // Getters y Setters
  // Getter para nickname
  public String getNickname(){ return this.nickname; }
  // Getter para nivel
  public int getNivel() { return this.nivel; }
  // Getter para saldoMonedas
  public double getSaldoMonedas() { return this.saldoMonedas; }

  // Setter para nivel con validacion
  public void setNivel(int nuevoNivel) {
    if(nuevoNivel < 1) {
      System.out.println("Error: el nivel no puede ser menor a 1. Se asigno nivel 1 por defecto");
      this.nivel = 1;
    } else {
      this.nivel = nuevoNivel;
    }
  }

  // Setter para saldoMonedas con validacion
  public void setSaldoMonedas(double cantidad) {
    if(cantidad < 0)  {
      System.out.println("ERROR: el saldo de monedas no puede ser negativo");
    } else {
      this.saldoMonedas = cantidad;
    }
  }

  // Metodo con logica de negocio
  public void depositarMonedas(double cantidad) {
    if(cantidad > 0) {
      this.saldoMonedas += cantidad;
      System.out.println("Monedas " + nickname + " deposito " + cantidad + " monedas. Saldo total: " + this.saldoMonedas);
    }
  }

}
