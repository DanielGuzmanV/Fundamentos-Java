package conceptos_basicos.colecciones;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListasExplicadas {
  public static void main(String[] args) {
    System.out.println("=== 1. Listas Basicas (ArrayList) ===");

    // Creacion de una lista dinamica limpia
    List<String> inventario = new ArrayList<>();

    // Agregar elementos (Pueden ser duplicados)
    inventario.add("Espada de Hierro");
    inventario.add("Casco de cuero");
    inventario.add("Espada de Hierro");

    System.out.println("Inventario completo: " + inventario);
    System.out.println("Elemento en la posición 1: " + inventario.get(1));
    System.out.println("Cantidad total de elementos: " + inventario.size());

    // Modificar y eliminar
    inventario.set(2, "Pila de madera"); // Reemplaza la casilla 2
    inventario.remove(0);

    System.out.println("Inventario tras modificar y borrar: " + inventario);

    // Recorrer listas de las 3 formas mas usadas:
    System.out.println("\n Uso del forEach:");
    inventario.forEach(item -> {
      System.out.println("Item encontrado: " + item);
    });

    System.out.println("\n Uso del for-each:");
    for(String item : inventario) {
      System.out.println("Precesando: " + item);
    }

    System.out.println("\n Uso del for:");
    for(int idx = 0; idx < inventario.size(); idx++) {
      System.out.println("Posicion " + idx + ": " + inventario.get(idx));
    }

    System.out.println("\n Uso del .map():");
    List<String> enMayus = inventario.stream()
        .map(item -> item.toUpperCase())
        .toList();
    System.out.println(enMayus);

    System.out.println("\n Agregar elementos recorriendo la lista:");
    List<String> todosLosItems = List.of("Espada", "Pocion", "Manzana", "Casco", "Pocion");
    List<String> equipamiento = new ArrayList<>();

    // Recorremos y agregamos dinamicamente si cumple una condicion
    for(String item : todosLosItems) {
      if(!item.equals("Pocion")) {
        equipamiento.add(item);
      }
    }
    System.out.println("Equipamiento filtrado: " + equipamiento);


    System.out.println("\n=== 2. Listas intermedias (Inmutable vs Mutable) ===");

    // Inmutable: Colecciones estaticas y ultra rapidas, que no se puede agregar ni quitar elementos
    // despues de averce creado
    List<String> comandosPermitidos = List.of("/tp", "/gamemode", "/give");
    // comandosPermitidos.add("kick"); // Nos mostrara un error

    // Mutable: Aqui podemos modificar la lista de comandosPermitidos a mutable para trabajar con ella
    List<String> misComandos = new ArrayList<>(comandosPermitidos);
    misComandos.add("/fly");
    System.out.println("Comandos activos: " + misComandos);


    System.out.println("\n=== 3. Listas Avanzadas (LinkedList para colas) ===");

    // Usamos LinkedList cuando necesitamos insertar o sacar cosas
    List<String> colaDeJugadores = new LinkedList<>();
    List<String> jugadoresAgregados = new LinkedList<>();

    colaDeJugadores.add("CarlosDev");
    colaDeJugadores.add("AlexPro123");
    colaDeJugadores.add("Player0032");
    colaDeJugadores.add("noobMaster123");

    System.out.println("Jugadores esperando para entrar al servidor: " + colaDeJugadores);

    // Simular que el primero en la lista entra
    String jugadorEntrando = colaDeJugadores.remove(0);
    jugadoresAgregados.add(jugadorEntrando);
    jugadoresAgregados.add(colaDeJugadores.remove(2));

    System.out.println("Entraron: " + jugadoresAgregados);
    System.out.println("Siguen en cola: " + colaDeJugadores);
    System.out.println("Primero en entrar: " + jugadoresAgregados.getFirst());

  }
}
