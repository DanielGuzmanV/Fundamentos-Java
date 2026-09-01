package projects.java.com.rpgmod.model.items;

import projects.java.com.rpgmod.enums.TipoItem;

import java.util.Random;

public abstract class Items {
  private final String id;
  private final String nombre;
  private final TipoItem tipo;

  public Items(String nombre, TipoItem tipo){
    // Auto-generacion de ID
    Random random = new Random();
    int numberRandom = 100 + random.nextInt(900);
    this.id = "item_" + numberRandom;
    this.nombre = nombre;
    this.tipo = tipo;
  }

  // Getters
  public String getId() {return id;}
  public String getNombre() {return nombre;}
  public TipoItem getTipo() {return tipo;}

  // Metodo abstracto
  public abstract String getDetalles();
}