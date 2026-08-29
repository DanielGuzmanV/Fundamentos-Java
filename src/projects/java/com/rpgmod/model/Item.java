package projects.java.com.rpgmod.model;

public class Item {
  private final String id;
  private final String nombre;
  private int durabilidad;

  // Constructor principal:
  public Item(String id, String nombre, int durabilidad) {
    this.id = id;
    this.nombre = nombre;
    this.durabilidad = durabilidad;
  }

  // Constructor sobrecargado (Asumimos la durabilidad de 100 por defecto)
  public Item(String id, String nombre) {
    this(id, nombre, 100);
  }

  // Getters:
  public String getId() { return id; }
  public String getNombre() { return nombre; }
  public int getDurabilidad() { return durabilidad; }

  public void usar() {
    if(durabilidad > 0) {
      durabilidad--;
    }
  }
}
