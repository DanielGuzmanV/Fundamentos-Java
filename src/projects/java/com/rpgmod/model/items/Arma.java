package projects.java.com.rpgmod.model.items;

import projects.java.com.rpgmod.enums.TipoItem;

public class Arma extends Items {
  private final int damagePoints;
  private int durabilidad;

  public Arma(String nombre, int puntosDano, int durabilidad) {
    super(nombre, TipoItem.ARMA);
    this.damagePoints = puntosDano;
    this.durabilidad = durabilidad;
  }

  public int getDamagePoints() { return damagePoints; }
  public int getDurabilidad() { return  durabilidad; }

  public void reducirDurabilidad() {
    if(durabilidad > 0) durabilidad--;
  }

  @Override
  public String getDetalles() {
    return "[" + getId() + "] " + getTipo() + ": " + getNombre() + " | Damage: " + damagePoints + " | Durabilidad: " + durabilidad;
  }
}