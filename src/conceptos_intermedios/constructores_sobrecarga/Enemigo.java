package conceptos_intermedios.constructores_sobrecarga;

import java.util.Optional;

public class Enemigo {
  private String nombre;
  private int salud;

  // Constructor privado, nadie fuera de la clase lo llama directamente
  private Enemigo(String nombre, int salud) {
    this.nombre = nombre;
    this.salud = salud;
  }

  // Getters
  public String getNombre() { return  nombre; }
  public int getSalud() { return  salud; }

  // Metodo de fabrica para crear un jefe
  public static Enemigo crearJefe(String nombre) {
    return new Enemigo("Jefe: " + nombre, 500);
  }

  // Metodo de fabrica para crear un esbirro
  public static Enemigo crearEsbirro(String nombre) {
    return new Enemigo("Esbirro: " + nombre, 50);
  }

  // Metodo de fabrica para un enemigo custom
  public static Enemigo enemigoCustom(String nombre, int salud) {
    String nombreValido = Optional.ofNullable(nombre)
        .filter(n -> !n.isBlank())
        .orElse("El nombre del enemigo no puede estar vacio");

    int saludValida = (salud <= 0) ? 10 : salud;

    return new Enemigo("Enemigo: " + nombreValido, saludValida);
  }

  @Override
  public String toString() {
    return "Enemigo { '" + nombre + "' , salud = " + salud + " }";
  }
}
