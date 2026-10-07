package conceptos_intermedios.streams;

import java.util.Comparator;
import java.util.List;

public class StreamIntermediate {
  public record Item(String name, int durability) {}
  public record Player(String name, List<Item> inventory) {}

  // Ejemplo practico:
  public record Developer(String name, int score){}
  public record Team(String teamName, List<Developer> members) {}

  public static void main(String[] args) {
    // Tenemos dos jugadores con sus respectivos inventarios
    Player py1 = new Player("Marcus", List.of(
        new Item("Espada de hierro", 100),
        new Item("Pico de piedra", 20),
        new Item("Escudo de madera", 50)
    ));

    Player py2 = new Player("Carlos", List.of(
        new Item("Pico de piedra", 20),
        new Item("Arco", 80),
        new Item("Espada de oro", 90)
    ));

    List<Player> servidor = List.of(py1, py2);

    System.out.println("=== PROCESANDO ITEMS DEL SERVIDOR ===");

    List<Item> filteredUniqueItems = servidor.stream()
        // Extraemos las listas internas y las unimos en un solo stream
        .flatMap(player -> player.inventory().stream())

        // Eliminamos los items duplicados
        .distinct()

        // Dejamos pasar solo items con durabilidad mayor a 50
        .filter(item -> item.durability() >= 50)

        // Inspeccionamos los elememtos que lograron pasar el filtro
        .peek(item -> System.out.println("-> Paso el filtro: " + item.name()))

        // Ordenamos los items de mayor a menor durabilidad
        .sorted(Comparator.comparing(Item::durability).reversed())
        .toList();

    System.out.println("\n--- Resultado final ---");
    filteredUniqueItems.forEach(item ->
        System.out.println(item.name() + " - Durabilidad: " + item.durability())
    );

    System.out.println("\n===================================================");

    // Requerimiento
    // Escribe un programa que procese la lista de equipos y retorne una lista de nombres de
    // desarrolladores que cumpla los siguientes criterios:
    // 1. Extraer a todos los desarrolladores de todos los equipos en una sola secuencia lineal.
    // 2. Filtrar únicamente a los desarrolladores cuyo score sea mayor o igual a 80.
    // 3. Eliminar desarrolladores duplicados (mismo nombre y score) si aparecen en más de un equipo.
    // 4. Ordenar a los desarrolladores de mayor a menor score. Si dos tienen el mismo score, ordenarlos alfabéticamente por su nombre.
    // 5. Transformar los objetos Desarrollador para obtener únicamente un String con la estructura: "Nombre (Score: X)".

    // Datos de prueba:
    List<Team> equipos = List.of(
        new Team("Frontend", List.of(
            new Developer("Carlos", 85),
            new Developer("Ana", 92),
            new Developer("Marcos", 70)
        )),
        new Team("Backend", List.of(
            new Developer("Ana", 92),
            new Developer("David", 85),
            new Developer("Elena", 98)
        )),
        new Team("Mobile", List.of(
            new Developer("Sofia", 75),
            new Developer("Bruno", 85)
        ))
    );

    List<String> resultTeams = equipos.stream()
        .flatMap(team -> team.members().stream())
        .filter(s -> s.score() >= 80)
        .distinct()
        .sorted(
          Comparator.comparing(Developer::score)
            .reversed()
            .thenComparing(Developer::name)
        )
        .map(dev -> dev.name() + " (Score: " + dev.score() + ")")
        .toList();

    System.out.println("\n--- Resultado final ---");
    resultTeams.forEach(System.out::println);
  }
}
