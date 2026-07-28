package conceptos_basicos.colecciones;

import java.util.Objects;

public final class Item {
  // Atributos
  private final String id;
  private final String nombre;

  // Constructor
  public Item(String id, String nombre) {
    this.id = id;
    this.nombre = nombre;
  }

  // Getters
  public String getId() { return id; }
  public String getNombre() { return nombre; }

  // Nota: Para que HashSet/HashMap reconozcan si dos objetos son iguales,
  // debemos sobreescribir equals() y hashcod
  @Override
  public boolean equals(Object o) {
    if(this == o) return true; // ¿Es literalmente la misma caja? Sí, son iguales.
    if (o == null || getClass() != o.getClass()) return false; // ¿La otra caja está vacía o es de otro tipo? No son iguales.

    Item item = (Item) o;
    return Objects.equals(id, item.id); // Si los ID coinciden, ¡son iguales!
  }

  @Override
  public int hashCode() {
    return Objects.hash(id); // Genera un número único usando el 'id'

  }

  @Override
  public String toString() {
    return  nombre + " (" + id + ") ";
  }
}