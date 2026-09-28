package conceptos_intermedios.records;

public class MainSealed {

  // Metodo para las recompensas
  public static void procesarRecompensa(Sealed.Recompensa recompensa) {
    // Pattern matching con records dentro de un switch
    String mensaje = switch (recompensa) {
      case Sealed.ItemRecompensa(String nombre, int cantidad) -> "Recibiste " + cantidad + "x " + nombre;
      case Sealed.OroRecompensa(int monedas) -> "Obtuviste " + monedas + " monedas de oro";
      case Sealed.SinRecompensa() -> "No encontraste nada en el cofre";
    };
    System.out.println(mensaje);
  }

  // Metodo para evaluar los tipos de daño
  public static void procesarResultado(Sealed.ResultadoAtaque resultado) {
    String messageResult = switch (resultado) {
      case Sealed.Impacto(int dano, boolean esCritico) when esCritico ->
          "GOLPE CRITICO infligido " + dano + " puntos de dano";

      case Sealed.Impacto(int dano, boolean esCritico) ->
        "Impacto normal de " + dano + " de dano";

      case Sealed.Fallo(String razon) -> "El ataque fallo: [" + razon + "]";
      case Sealed.Bloqueo(int danoOriginal, int danoMitigado) -> "Ataque bloqueado. Dano reducido de " + danoMitigado + " a " + danoOriginal + " puntos. [" + (danoOriginal - danoMitigado) + "]";
    };
    System.out.println(messageResult);
  }


  public static void main(String[] args) {
    Sealed.Recompensa r1 = new Sealed.ItemRecompensa("Pocion de vida", 3);
    Sealed.Recompensa r2 = new Sealed.OroRecompensa(150);
    Sealed.Recompensa r3 = new Sealed.SinRecompensa();

    procesarRecompensa(r1);
    procesarRecompensa(r2);
    procesarRecompensa(r3);

    System.out.println("\n ========================================================================");


    Sealed.ResultadoAtaque ra1 = new Sealed.Impacto(30, true);
    Sealed.ResultadoAtaque ra2 = new Sealed.Fallo("Fuera de alcance");
    Sealed.ResultadoAtaque ra3 = new Sealed.Bloqueo(30, 10);

    procesarResultado(ra1);
    procesarResultado(ra2);
    procesarResultado(ra3);

  }
}
