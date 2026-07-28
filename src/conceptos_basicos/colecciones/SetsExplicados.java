package conceptos_basicos.colecciones;

import java.util.*;

public class SetsExplicados {

  // Funcion para converitr una set
  public static List<String> convertirList(Set<String> setList) {
    return new ArrayList<>(setList);
  }

  // Funcion para comprobar un jugador
  public static void comprobarPlayer(Set<String> setJugadores, String getPlayer) {
    if(setJugadores.contains(getPlayer)) {
      System.out.println("Jugador: " + getPlayer + " esta conectado en el servidor");
    } else {
      System.out.println("Ningun jugador conectado");
    }
  }

  public static void main(String[] args) {
    System.out.println("=== 1. Set Basico (HashSet - Sin duplicados) ===");

    // Creamos un set de Jugadores en linea
    Set<String> jugadoresOnline = new HashSet<>();

    // Agregamos elementos (algunos duplicados)
    jugadoresOnline.add("CarlosDev");
    jugadoresOnline.add("AlexPro");
    jugadoresOnline.add("CarlosDev");
    System.out.println("Jugadores conectados (" + jugadoresOnline.size() + "): " + jugadoresOnline);

    // Convertimos el set a una lista
    List<String> jugadoresList = convertirList(jugadoresOnline);
    String getJugador = jugadoresList.get(1);
    String getJugador2 = "";

    // Comprobamos si existe un elemento al instante
    comprobarPlayer(jugadoresOnline, getJugador);
    comprobarPlayer(jugadoresOnline, getJugador2);

    // Ejemplo con una clase
    Set<Item> conjuntoDeItems = new HashSet<>();

    // Nuevas instancias
    Item espada1 = new Item("ITEM-1", "Espada de hierro");
    Item espada2 = new Item("ITEM-2", "Pico de piedra");
    Item espada3 = new Item("ITEM-1", "Espada de hierro");
    Item espada4 = new Item("ITEM-3", "Hacha de hierro");

    conjuntoDeItems.add(espada1);
    conjuntoDeItems.add(espada2); // HashSet lo ignorara porque equals/hashCode detecta que es la misma
    conjuntoDeItems.add(espada3);
    conjuntoDeItems.add(espada4);

    System.out.println("Cantidad de items unicos en Set: " + conjuntoDeItems.size());
    System.out.println("Contenido del Set: " + conjuntoDeItems);


    System.out.println("\n=== 2. Set Intermedio (Inmutabilidad y operaciones de conjuntos) ===");

    // Set inmutable
    Set<String> permisosAdmin = Set.of("ban", "Kick", "mute");
    // permisosAdmin.add("kickAll"); // Nos dara un error
    System.out.println("Permisos de admin: " + permisosAdmin);

    // Set mutable derivado del Set.of
    Set<String> misPermisos = new HashSet<>(permisosAdmin);
    misPermisos.add("muteAll");
    System.out.println("Mis permisos: " + misPermisos);

    // Operacion de Interseccion
    Set<String> permisosRequeridos = new HashSet<>(Set.of("ban", "fly", "godmode"));
    System.out.println("Los permisos requeridos son: " + permisosRequeridos);

    // Retenemos solo los permisos que el jugador Si tiene en comun
    permisosRequeridos.retainAll(misPermisos);
    System.out.println("Permisos en comun que coinciden: " + permisosRequeridos);


    System.out.println("\n=== 3. SET AVANZADO (Diferencias de Ordenamiento) ===");

    // HashSet: Sin orden
    Set<String> hashSet = new HashSet<>();
    // LinkedHashSet: Orden de llegada
    Set<String> linkedHashSet = new LinkedHashSet<>();
    // TreeSet: Orden alfabético/natural automático
    Set<String> treeSet = new TreeSet<>();

    List<String> datosEntrada = List.of("Zebra", "Manzana", "Barco", "Árbol");

    hashSet.addAll(datosEntrada);
    linkedHashSet.addAll(datosEntrada);
    treeSet.addAll(datosEntrada);

    System.out.println("HashSet (Sin orden):          " + hashSet);
    System.out.println("LinkedHashSet (Orden llegada): " + linkedHashSet);
    System.out.println("TreeSet (Orden alfabético):   " + treeSet);
  }
}
