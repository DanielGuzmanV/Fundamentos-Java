package conceptos_basicos.poo.herencias;

public class ClaseEntidad {
  protected String nombre;
  protected int vida;

  public ClaseEntidad(String nombre, int vida) {
    this.nombre = nombre;
    this.vida = vida;
  }

  public void atacar() {
    System.out.println(nombre + " realiza un ataque basico");
  }
}
