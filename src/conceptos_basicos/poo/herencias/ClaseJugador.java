package conceptos_basicos.poo.herencias;

public class ClaseJugador extends ClaseEntidad {
  private String arma;

  public ClaseJugador (String nombre, int vida, String arma) {
    super(nombre, vida);
    this.arma = arma;
  }

  @Override
  public void atacar() {
    System.out.println("GOLPE " + nombre + " ataca ferozmente con su " + arma );
  }


}
