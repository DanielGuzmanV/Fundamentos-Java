package conceptos_intermedios.records;

public class ClassItem {
  public record Item (String nombre, double precio) {
    public Item {
      if(precio <= 0.0) {
        throw new IllegalArgumentException("El precio no puede ser negativo o 0");
      }
    }
  }

  public static void main(String[] args) {
    Item item1 = new Item("Espada de hierro", 10);

    System.out.println("Item: " + item1.nombre());
    System.out.println("Item: " + item1.precio());
  }
}
