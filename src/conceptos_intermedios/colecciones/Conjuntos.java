package conceptos_intermedios.colecciones;

import java.util.*;

public class Conjuntos {

  public record PlayerScore(String name, int points){}

  public static void main(String[] args) {

    // Nivel basico HashSet ================================
    Set<String> playersOnline = new HashSet<>();

    // Intentamos agregar duplicados
    playersOnline.add("AlexP");
    playersOnline.add("Marco");
    playersOnline.add("AlexP");
    playersOnline.add("BenitoJ");

    System.out.println("Total de jugadores unicos: " + playersOnline.size());

    // La salida de nombres no garantiza ningun orden en particulas
    for(String player : playersOnline) {
      System.out.println("Jugador: " + player);
    }

    System.out.println("\n============================================");

    // Nivel intermedio LinkedHashSet ================================
    // Mantenemos el orden exacto de insercion
    Set<String>  commandHistory = new LinkedHashSet<>();

    commandHistory.add("/tp 0 67 0");
    commandHistory.add("/gamemode creative");
    commandHistory.add("/tp 0 67 0");
    commandHistory.add("/time set day");
    commandHistory.add("/gamemode survival");

    System.out.println("Historial de comandos: ");
    for(String command : commandHistory) {
      System.out.println(" -> " + command);
    }

    System.out.println("\n============================================");

    // Nivel complejo TreeSet ===================================
    // Orden personalizado, mayor puntaje primero
    Set<PlayerScore> leaderTable = new TreeSet<>(
        Comparator.comparing(PlayerScore::points).reversed()
    );

    leaderTable.add(new PlayerScore("Carlos", 1500));
    leaderTable.add(new PlayerScore("Manuel", 800));
    leaderTable.add(new PlayerScore("Alberto", 2300));

    System.out.println("Tabla de posiciones (Ordenada por puntos):");

    for(PlayerScore input : leaderTable){
      System.out.println(input.name() + " - " + input.points() + " pts");
    }
  }
}
