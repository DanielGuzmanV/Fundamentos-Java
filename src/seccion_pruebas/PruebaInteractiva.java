package seccion_pruebas;

import java.util.Scanner;

public class PruebaInteractiva {

  public static class Jugador {
    private String nickname;
    private int nivel;
    private double vida;

    public Jugador(String nickname) {
      this.nickname = nickname;
      this.nivel = 0;
      this.vida = 100;
    }

    public void recibirDano(double cantidad) {
      this.vida -= cantidad;
      if (this.vida < 0) this.vida = 0;
      System.out.println(nickname + " recibió " + cantidad + " de daño. Vida restante: " + vida);
    }

    @Override
    public String toString() {
      return "Jugador: " + nickname + " | Nivel: " + nivel + " | Vida: " + vida;
    }
  }

  public static void main(String[] args) {
    // Objeto nativo de Java para leer la consola en tiempo real
    Scanner scanner = new Scanner(System.in);

    System.out.println("=== CREACIÓN DE JUGADOR EN TIEMPO REAL ===");

    // 1. Pedir String
    System.out.print("Escribe tu nickname: ");
    String nick = scanner.nextLine();

    // Instanciamos el objeto con tus datos reales de la consola
    Jugador jugadorReal = new Jugador(nick);
    System.out.println("\n Objeto creado con éxito: " + jugadorReal);

    // Bucle interactivo para provocar/probar acciones y errores
    boolean continuar = true;
    while (continuar) {
      System.out.print("\n¿Cuánto daño quieres hacerle al jugador? (Escribe 0 para salir): ");
      double dano = scanner.nextDouble();

      if (dano == 0) {
        continuar = false;
        System.out.println("Saliendo del programa interactivo...");
      } else {
        jugadorReal.recibirDano(dano);
      }
    }

    scanner.close();
  }
}