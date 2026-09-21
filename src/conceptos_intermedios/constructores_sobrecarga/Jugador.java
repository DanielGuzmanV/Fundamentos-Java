package conceptos_intermedios.constructores_sobrecarga;

public class Jugador {
  private String nombre;
  public int nivel;
  private int puntosVida;
  private boolean estaVivo;

  // ---- Sobrecarga de constructores ----
  // Constructor 1: recibe todos los datos
  public Jugador(String nombre, int nivel, int puntosVida, boolean estaVivo) {
    this.nombre = nombre;
    this.nivel = nivel;
    this.puntosVida = puntosVida;
    this.estaVivo = estaVivo;
  }

  // Constructor 2: solo recibe el nombre (asignamos valores por defecto)
  public Jugador(String nombre) {
    this.nombre = nombre;
    this.nivel = 1;
    this.puntosVida = 100;
    this.estaVivo = true;
  }
  // --------

  // ---- Encadenamiento de constructores ----
  // Constructor secundario 1: Delega al constructor principal
  public Jugador(String nombre, int nivel){
    this(nombre, nivel, 100, true);
  }

  // Constructor secundario 2: Delega al secundario 1
  public Jugador(String nombre, int puntosVida, boolean estaVivo) {
    this(nombre, 1, puntosVida, estaVivo);
  }

  public Jugador() {
    this.nombre = "guest_default";
    this.nivel = 0;
    this.puntosVida = 100;
    this.estaVivo = true;
  }

  // Getters:
  public String getNombre() { return this.nombre; }
  public int getNivel() {return nivel;}
  public int getPuntosVida() {return puntosVida;}
  public boolean isEstaVivo() {return estaVivo;}

  @Override
  public String toString() {
    return "nombre: " + nombre +  " | nivel: " + nivel + " | vida: " + puntosVida + " | esta vivo: " + estaVivo;
  }

  // =============================================
  public static void main(String[] args) {
    Jugador player1 = new Jugador("marco");
    Jugador player2 = new Jugador("carlos", 50);
    Jugador player3 = new Jugador("alberto", 0, false);
    Jugador player4 = new Jugador("jose", 10, 25, false);

    int nivel = player3.nivel;
    String nombre = player3.nombre;

    System.out.println("El nombre es: " + nombre + " | el nivel es: " + nivel);

    System.out.println("Player 1: ");
    System.out.println("Dato del jugador: " + player1);

    System.out.println("\nPlayer 2: ");
    System.out.println("Dato del jugador: " + player2);

    System.out.println("\nPlayer 3: ");
    System.out.println("Dato del jugador: " + player3);

    System.out.println("\nPlayer 4: ");
    System.out.println("Dato del jugador: " + player4);

  }

}
