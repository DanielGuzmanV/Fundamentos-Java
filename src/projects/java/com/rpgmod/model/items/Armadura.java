package projects.java.com.rpgmod.model.items;

import projects.java.com.rpgmod.enums.TipoItem;

public class Armadura extends Items {
  private final int protectionPoints;
  private int durabilidad;

  public Armadura (String nombre, int puntosProteccion, int durabilidad) {
    super(nombre, TipoItem.ARMADURA);
    this.protectionPoints = puntosProteccion;
    this.durabilidad = durabilidad;
  }

  public int getProtectionPoints() { return protectionPoints; }
  public int getDurabilidad() { return durabilidad; }

  public void reducirDurabilidad() {
    if(durabilidad > 0) durabilidad--;
  }

  @Override
  public String getDetalles() {
    return "[" + getId() + "] " + getTipo() + ": " + getNombre() + " | Protección: " + protectionPoints + " | Durabilidad: " + durabilidad;
  }
}