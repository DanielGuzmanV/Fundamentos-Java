package conceptos_basicos.poo.abstract_interface;

// 1. Interfaces (contrato de comportamiento / habilidades)
interface Volador {
  void despegar();
}

interface AtacanteAereo {
  void lanzarFuego(int damage);
}

interface GuardableEnBD {
  String obtenerDatosGuardado();
}

// 2. Clase base (clase abstracta con codigo en comun)
abstract class Entidad {
  protected String nombre;
  protected int vida;

  public Entidad(String nombre, int vida) {
    this.nombre = nombre;
    this.vida = vida;
  }

  public abstract void emitirSonido();
}

// 3. Clase completa con herencia multiple de interfaces
public class DragonBoss extends Entidad implements Volador, AtacanteAereo, GuardableEnBD {
  public DragonBoss(String nombre, int vida) {
    super(nombre, vida);
  }

  // Metodo obligatorio por la clase abstracta "Entidad"
  @Override
  public void emitirSonido() {
    System.out.println("SONIDO: " + nombre + " emite un rugido ensordecedor!");
  }

  // Metodo obligatorio por la interfaces "Volador"
  @Override
  public void despegar() {
    System.out.println("VOLAR: " + nombre + " despliega sus alas y sube al cielo.");
  }

  // Metodo obligatorio por la interfaces "AtacanteAereo"
  @Override
  public void lanzarFuego(int damage) {
    System.out.println("FUEGO!!: " + nombre + " lanza una ráfaga de fuego causando " + damage + " de daño.");
  }

  // Metodo obligatorio por la interfaces "GuardableEnBD"
  @Override
  public String obtenerDatosGuardado() {
    return "Dragon: {nombre: '" + nombre + "' , vida:" + vida + "}";
  }
}
