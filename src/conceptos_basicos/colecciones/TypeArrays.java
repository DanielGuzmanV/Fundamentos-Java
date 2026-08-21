package conceptos_basicos.colecciones;

import java.util.ArrayList;
import java.util.List;

public class TypeArrays {
  // 1. Arreglos estaticos (Tienen un tamaño fijo)
  private int[] puntajes = new int[5];
  private String[] itemsIniciales = {"Espada", "Escudo", "Pocion"};

  // 2. Arreglos dinamicos (Crecen y se reducen dinamicamente)
  private List<String> inventory = new ArrayList<>();
  private List<Integer> historialPuntajes = new ArrayList<>();

  // OPERACIONES ESTATICAS:
  public void operacionPuntajes() {
    puntajes[0] = 100;
    puntajes[3] = 25;
    puntajes[4] = 73;

    for(int value : puntajes) {
      System.out.println("Datos: " + value);
    }
  }

  public void operacionItems() {
    for(String value : itemsIniciales) {
      System.out.println("Items inciales: " + value);
    }
  }

  // OPERACIONES DINAMICAS:
  public void operacioColecciones() {
    inventory.add("Manzana");
    inventory.add("Platano");
    inventory.add("Agua");
    inventory.add("Madera");

    inventory.remove("Madera");

    int totalItmes = inventory.size();

    inventory.forEach(item -> {
      System.out.println("Values: " + item);
    });
    System.out.println("\n Total items: " + totalItmes);

  }

  public static void main(String[] args) {
    TypeArrays type = new TypeArrays();

    // 1. Operacion
    type.operacionPuntajes();
    System.out.println("=============================");

    // 2. Operacion
    type.operacionItems();
    System.out.println("=============================");

    // 3. Operaciones
    type.operacioColecciones();
  }

}
