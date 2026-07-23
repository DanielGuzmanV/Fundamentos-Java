package conceptos_basicos.inmutabilidad_basica;

public final class Jugador {
  // Atributos inmutables (final) y mutables
  private final String idJugador;
  private final String username;
  private int level;

  // Constante global
  public static final int NIVEL_MAXIMO = 100;

  // Constructor
  public Jugador(String idJugador, String username) {
    this.idJugador = idJugador;
    this.username = username;
    this.level = 1;
  }

  // Getters:
  public String getIdJugador() { return idJugador; }
  public String getUsername() { return username; }
  public int getNivel() { return level; }

  // Metodos:
  public final boolean esNivelMaximo() {
    return this.level >= NIVEL_MAXIMO;
  }

  public void subirNivel() {
    if(!esNivelMaximo()) {
      this.level++;
    }
  }
}