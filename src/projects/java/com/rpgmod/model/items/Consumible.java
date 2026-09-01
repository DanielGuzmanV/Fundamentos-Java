package projects.java.com.rpgmod.model.items;

import projects.java.com.rpgmod.enums.TipoItem;

public class Consumible extends  Items {
  private final int healingPoints;

  public Consumible(String nombre, int puntosCuracion) {
    super(nombre, TipoItem.CONSUMIBLE);
    this.healingPoints = puntosCuracion;
  }

  public int getHealingPoints() { return healingPoints; }

  @Override
  public String getDetalles() {
    return "[" + getId() + "] " + getTipo() + ": " + getNombre() + " | Curación: +" + healingPoints+ " HP";
  }
}