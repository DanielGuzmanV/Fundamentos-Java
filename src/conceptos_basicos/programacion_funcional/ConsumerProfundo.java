package conceptos_basicos.programacion_funcional;

import java.util.List;
import java.util.function.Consumer;

class PlayerConsumer {
  private String nickname;
  private int vida;
  private int oro;

  public PlayerConsumer(String nickname, int vida, int oro) {
    this.nickname = nickname;
    this.vida = vida;
    this.oro = oro;
  }

  // Obtenemos los valores de nickname, vida y oro
  public String getNickname() { return nickname; }
  public int getVida() { return vida; }
  public int getOro() { return oro; }

  // Agregamos valores a vida y oro
  public void setVida( int vida ) {this.vida = vida; }
  public void setOro(int oro) { this.oro = oro; }

  @Override
  public String toString(){
    return nickname + "[vida: " + vida + " | Oro: " + oro + "]";
  }

}

public class ConsumerProfundo {
  public static void main(String[] args) {
    System.out.println("=== 1. Impresion y accion (.accept) ===");

    Consumer<String> notificarJugador = mensaje -> System.out.println("MESSAGE SERVER: " + mensaje);
    notificarJugador.accept("El servidor se reiniciara en 5 min.");

    Consumer<Double> mostrarPrecioConIva = precio -> System.out.println("Precio final: $" + (precio * 1.21));
    mostrarPrecioConIva.accept(180.50);


    System.out.println("\n === 2. Encadenando acciones (.andThen) ===");

    System.out.println("\n Primer ejemplo simple:");
    Consumer<String> logEnConsola = msg -> System.out.println("[LOG] " + msg);
    Consumer<String> guardarEnHistorial = msg -> System.out.println("[BD] Guardando log: " + msg);

    // .andThen() encadenamos ambas acciones para que se ejecuten una tras otra
    Consumer<String> pipelineNotificacion = logEnConsola.andThen(guardarEnHistorial);
    pipelineNotificacion.accept("Jugador 'CarlosDev' inicio sesion");

    System.out.println("\n Segundo ejemplo usando una clase:");
    Consumer<PlayerConsumer> curar = playUser -> playUser.setVida(100);
    Consumer<PlayerConsumer> darRecompensa = playUser -> playUser.setOro(playUser.getOro() + 50);
    Consumer<PlayerConsumer> mostrarEstado = playUser -> System.out.println("Estado actual: " + playUser);

    // Creamos una rutina compuesta: Cura -> Da oro -> muestra estado
    Consumer<PlayerConsumer> rutinaRestauracion = curar
        .andThen(darRecompensa)
        .andThen(mostrarEstado);

    PlayerConsumer player1 = new PlayerConsumer("CarlosDev", 20, 100);
    System.out.println("Estado antes: " + player1);

    rutinaRestauracion.accept(player1);


    System.out.println("\n === 3. Procesar coleccion con Consumer Helper ===");

    List<PlayerConsumer> listaJugadores = List.of(
        new PlayerConsumer("AlexPro123", 50, 200),
        new PlayerConsumer("Hacker67", 10, 500)
    );

    Consumer<PlayerConsumer> comprarVida = value -> {
      value.setOro(value.getOro() - 50); // Compramos mas vida
      System.out.println("Jugadores que compraron vida: " + value.getNickname() + " | Oro restante: " + value.getOro() + " | Vida actual: " + value.getVida());
    };

    procesarColeccion(listaJugadores, comprarVida);

    Consumer<PlayerConsumer> consumerVida = value -> {
      value.setVida(value.getVida() + 25); // Obtiene una pocion de vida (+25 HP)
      System.out.println("Pocion aplicada a " + value.getNickname() + " -> Vida: " + value.getVida() + " Oro: " + value.getOro());
    };

    // Pasamos la lista y la accion que queremos ejecutar sobre cada elemento
    procesarColeccion(listaJugadores, consumerVida);

  }

  // Metodo helper generico que acepta una lista y un Consumer
  public static <T> void procesarColeccion(List<T> lista, Consumer<T> accion) {
    for(T elemento : lista) {
      accion.accept(elemento);
    }
  }
}