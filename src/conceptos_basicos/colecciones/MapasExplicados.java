package conceptos_basicos.colecciones;

import java.util.*;

public class MapasExplicados {

  // Uso de Record que genera automaticamente los genericos, el constructor y el toString()
  public record PlayersRecord(String nombre, int nivel){}

  public static void main(String[] args) {
    System.out.println("\n === 1. Map basico (hashMap) ===");

    // Creamos un mapa que asocia el username (clave) con el nivel de jugador (Valor)
    Map<String, Integer> nivelesJugadores = new HashMap<>();

    // Insertamos datos
    nivelesJugadores.put("CarlosDev", 34);
    nivelesJugadores.put("AlexPro", 70);
    nivelesJugadores.put("JoseSoni", 90);
    nivelesJugadores.put("NoobMaster", 45);
    System.out.println("Mapa de niveles: " + nivelesJugadores);

    // Reemplazamos un valor (Si la clave ya existe, sobrescribe)
    nivelesJugadores.put("CarlosDev", 46);
    System.out.println("Mapa de niveles actualizados: " + nivelesJugadores);

    // Obtener un dato directo por clave
    int nivelAlex = nivelesJugadores.get("AlexPro");
    System.out.println("Nivel de AlexPro: " + nivelAlex);

    // Uso de getOrDefault para evitar NullPointerException si la clave no existe
    int nivelDesconocido = nivelesJugadores.getOrDefault("JugadorFantasma", 1);
    System.out.println("Nivel de jugadoresFantasma: " + nivelDesconocido);


    System.out.println("\n=== 2. Map intermedio (Inmutabilidad y Recorridos) ===");

    // Mapa inmutable (maximo 10 pares clave-valor con Map.of
    Map<String, String> roles = Map.of("CarlosDev", "ADMIN", "AlexPro", "MODERADOR");
    System.out.println("Los roles son: " + roles);

    // Recorrer el mapa usando entrySet()
    System.out.println("--- Lista de Roles ---");
    for(Map.Entry<String, String> fila: roles.entrySet()) {
      System.out.println("Jugador: " + fila.getKey() + " -> Rango: " + fila.getValue());
    }


    System.out.println("\n=== 3. Map avanzado (Diferencias de ordenamiento)");

    Map<String, Integer> hash = new HashMap<>();
    Map<String, Integer> linked = new LinkedHashMap<>();
    Map<String, Integer> tree = new TreeMap<>();

    // Insertamos datos desordenados
    List<String> claves = List.of("Zebra", "Manzana", "Barco", "Arbol");
    System.out.println("Lista de claves: " + claves);

    for(int index = 0; index < claves.size(); index++) {
      hash.put(claves.get(index), index);
      linked.put(claves.get(index), index);
      tree.put(claves.get(index), index);
    }

    System.out.println("HashMap (sin orden): " + hash);
    System.out.println("LinkedHashMap (orden llegada): " + linked);
    System.out.println("TreeMap (orden alfabetico): " + tree);

    System.out.println("\n Ejemplo con la clase item:");
    // Asociamos al UUID de un jugador String con su objeto Item equipado
    Map<String, Item> equipamientoPlayer = new HashMap<>();

    // Insertamos los datos
    Item value1 = new Item("ITEM-1", "Espada de Hierro");
    Item value2 = new Item("ITEM-2", "Hacha de Hierro");
    Item value3 = new Item("ITEM-3", "Pico de Hierro");

    equipamientoPlayer.put("UUID-1", value1);
    equipamientoPlayer.put("UUID-2", value2);
    equipamientoPlayer.put("UUID-3", value3);

    Item getEquipamiento = equipamientoPlayer.get("UUID-1");
    System.out.println("El equipamiento es: " + getEquipamiento);

    // Recorrer un mapa mediante sus entradas
    System.out.println("\n Recorriendo un Map");
    for(Map.Entry<String, Item> entrada : equipamientoPlayer.entrySet()) {
      System.out.println("Jugador: " + entrada.getKey() + " | Item: " + entrada.getValue());
    }


    System.out.println("\n --- Lista de Objetos --- ");

    List<Players> listaJugadores = new ArrayList<>();
    listaJugadores.add(new Players("CarlosDev", 45));
    listaJugadores.add(new Players("AlexPro", 55));
    listaJugadores.add(new Players("NoobMaster", 90));

    System.out.println("Lista de jugadores: " + listaJugadores);

    String nombre = listaJugadores.getFirst().getNombre();
    System.out.println("El primer jugador: " + nombre);


    System.out.println("\n--- Clave entera y valor de lista ---");

    // Map donde la clave es un ID y el valor es una lista de jugadores
    Map<Integer, List<String>> jugadoresPorNivel = new HashMap<>();

    // Para agregar datos, instancias la lista en el valor
    jugadoresPorNivel.put(2, new ArrayList<>(List.of("Carlos", "Albert")));
    jugadoresPorNivel.put(1, new ArrayList<>(List.of("player1", "player2", "player3")));
    jugadoresPorNivel.put(3, new ArrayList<>(List.of("Robert22")));
    System.out.println("Lista de jugadore: " + jugadoresPorNivel);

    // Agregar un jugador nuevo al nivel 3
    jugadoresPorNivel.get(3).add("newPlayer23");
    System.out.println("Lista actualizada: " + jugadoresPorNivel);


    System.out.println("\n--- Objeto de listas ---");

    Inventario miInventario = new Inventario();
    miInventario.agregarArma("Espada de Diamante");
    miInventario.agregarArma("Arco");

    System.out.println("Mis armas: " + miInventario.getArmas());


    System.out.println("\n --- Records --- ");
    List<PlayersRecord> newListPlayers = new ArrayList<>();
    newListPlayers.add(new PlayersRecord("CarlosDev", 43));
    newListPlayers.add(new PlayersRecord("MarcoQA", 27));
    newListPlayers.add(new PlayersRecord("Guest123", 12));

    // Imprimir automaticamente sin configurar nada
    System.out.println("Lista de jugadores: " + newListPlayers);

    // Obtener ambos datos del primer elemento de la lista:
    PlayersRecord playerOne = newListPlayers.getFirst();
    System.out.println("Nombre: " + playerOne.nombre() + " | nivel: " + playerOne.nivel());

  }
}
