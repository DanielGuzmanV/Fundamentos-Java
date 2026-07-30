package conceptos_basicos.colecciones;

public class Players {
  private String nombre;
  private int nivel;

  public Players(String nombre, int nivel) {
    this.nombre = nombre;
    this.nivel = nivel;
  }

  // Getters para obtener los datos
  public String getNombre() { return nombre; }
  public int getNivel() { return nivel; }

  // Indicamos a Java como imprimir esta clase en consola
  @Override
  public String toString() {
    return " {nombre='" + nombre + "', nivel=" + nivel + "}";
  }
}