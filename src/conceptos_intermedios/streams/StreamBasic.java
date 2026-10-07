package conceptos_intermedios.streams;

import java.util.ArrayList;
import java.util.List;

public class StreamBasic {

  // Record para representar un item con un precio
  public record Item(String nombre, double precio) {}

  public static void main(String[] args) {

    System.out.println("\n--- Enfoque tradicional (Imperativo) ---");
    List<String> names = List.of("marco", "albert", "adam", "devy");
    List<String> result = new ArrayList<>();

    for(String name : names) {
      if(name.startsWith("a")) {
        result.add(name.toUpperCase());
        System.out.println("Los nombres son: " + name);
      }
    }
    System.out.println("Resultado final: " + result);

    System.out.println("\n--- Enfoque con Streams (Declarativo) ---");
    List<String> newResult = names.stream()
            .filter(n -> n.startsWith("a"))
            .map(String::toUpperCase) // Evitamos el metodo: .map(n -> n.toUpperCase())
            .toList();
    System.out.println("Nuevo resultado: " + newResult);

    System.out.println("\n-----------------------------------------------");

    List<Item> store = List.of(
        new Item("Espada de Madera", 5.0),
        new Item("Espada de Titanio", 45.0),
        new Item("Pico de Titanio", 50.0),
        new Item("Escudo de Cuero", 12.0)
    );

    List<String> itemsValiosos = store.stream()
        // 1. Solo pasan los items con precio >= 40.0
        .filter(item -> item.precio() >= 40.0)
        // 2. Transformamos el objeto item a solo un string
        .map(item -> item.nombre())
        // Convertimos el stream en una lista
        .toList();

    System.out.println("Ite ms valiosos encontrados:");
    for (String name : itemsValiosos) {
      System.out.println(" -> " + name);
    }
  }
}
