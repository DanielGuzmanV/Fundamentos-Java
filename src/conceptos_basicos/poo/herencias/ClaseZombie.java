package conceptos_basicos.poo.herencias;

public class ClaseZombie extends ClaseEntidad{
  public ClaseZombie(String nombre, int vida) {
    super(nombre, vida);
  }

  @Override
  public void atacar() {
    System.out.println("ZOMBIE " + nombre + " muerde y causa heridas");
  }
}
