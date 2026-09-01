package projects.java.com.rpgmod.model.entities;

import projects.java.com.rpgmod.api.IDanable;
import projects.java.com.rpgmod.exception.DamageInvalidException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class LivingEntity implements IDanable {
  protected final String id;
  protected final String nombre;
  protected final int vidaMax;
  protected int vidaActual;
  protected List<StateEffect> efectosActivos;

  // Clase anidada:
  public static class StateEffect {
    private final String nombre;
    private int duracionTicks;

    public StateEffect(String nombre, int duracionTicks) {
      this.nombre = nombre;
      this.duracionTicks = duracionTicks;
    }

    public String getNombre() { return nombre; }
    public int getDuracionTicks() { return duracionTicks; }

    public void reducirDuracion() {
      if(duracionTicks > 0) duracionTicks--;
      System.out.println("Duracion actual de " + nombre + " | " + duracionTicks + " ticks");
    }
  }

  // Constructor padre
  public LivingEntity(String prefijoId, String nombre, int vidaMaxima) {
    Random randmo = new Random();
    int numberRandom = 100 + randmo.nextInt(900);

    this.id = prefijoId + numberRandom;
    this.nombre = nombre;
    this.vidaMax = vidaMaxima;
    this.vidaActual = vidaMaxima;
    this.efectosActivos = new ArrayList<>();
  }

  // Getters:
  public String getId() {return id;}
  public String getNombre() {return nombre;}
  public int getVidaMax() {return vidaMax;}
  public int getVidaActual() {return vidaActual;}

  // Metodo:
  public void agregarEfecto(StateEffect efecto) {
    this.efectosActivos.add(efecto);
    System.out.println("Efecto '" + efecto.getNombre() + "' aplicado a [" + nombre + "]");
  }

  // Metodo abstracto:
  public abstract void ejecutarTickIA();

  // Metodos de implementados de IDanable
  @Override
  public void aplicarDamage(int cantidad) {
    if(cantidad < 0) {
      throw new DamageInvalidException("El ataque recibido no puede ser negativo: " + cantidad);
    }
    this.vidaActual = Math.max(0, this.vidaActual - cantidad);
    System.out.println("DAMAGE [" + nombre + "] Recibio " + cantidad + " de daño | vida " + vidaActual + "/" + vidaMax);
  }

  @Override
  public boolean estaVivo() {
    return this.vidaActual > 0;
  }
}