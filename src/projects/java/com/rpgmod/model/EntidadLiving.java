package projects.java.com.rpgmod.model;

import projects.java.com.rpgmod.api.IDanable;
import projects.java.com.rpgmod.exception.DamageInvalidException;

import java.util.ArrayList;
import java.util.List;

public abstract class EntidadLiving implements IDanable {
  protected final String id;
  protected final int vidaMaxima;
  protected int vidaActual;
  protected List<EfectoEstado> efectosActivos;

  // Clase anidad estatica: No mantiene referencia a la clase padre
  public static class EfectoEstado {
    private final String nombre;
    private int duracionTicks;

    // Constructor:
    public EfectoEstado(String nombre, int duracionTicks) {
      this.nombre = nombre;
      this.duracionTicks = duracionTicks;
    }

    // Getters y metodos:
    public String getNombre() {return nombre;}
    public int getDuracionTicks() {return duracionTicks;}

    public void reducirDuracion() {
      if(duracionTicks > 0) duracionTicks--;
      System.out.println("La duracion actual es: " + duracionTicks);
    }
  }

  // Constructor de la clase padre
  public EntidadLiving(String id, int vidaMaxima) {
    this.id = id;
    this.vidaMaxima = vidaMaxima;
    this.vidaActual = vidaMaxima;
    this.efectosActivos = new ArrayList<>();
  }

  // Metodo propio de la clase
  public void agregarEfecto(EfectoEstado efecto){
    this.efectosActivos.add(efecto);
    System.out.println("Efecto '" + efecto.getNombre() + "' aplicado a [" + id + "]");
  }

  // Metodo abstracto obligatorio
  public abstract void ejecutarTickIA();

  // --- Metodos implementados de la clase IDanable ---
  @Override
  public void aplicarDamage(int cantidad) {
    if(cantidad < 0) {
      throw new DamageInvalidException("El ataque recibido no puede ser negativo: " + cantidad);
    }
    this.vidaActual = Math.max(0, this.vidaActual - cantidad);
    System.out.println("ATAQUE [" + id + "] Recibio " + cantidad + " de ataque | Vida: " + vidaActual + "/" + vidaMaxima);
  }

  @Override
  public boolean estaVivo() {
    return this.vidaActual > 0;
  }
  // --------------------------------------------------
}
