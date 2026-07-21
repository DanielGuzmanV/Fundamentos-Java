package conceptos_basicos.manejo_nulos;

import java.util.Optional;

public class NullSafetyExplained {

  // Forma 1: Metodo con programacion defensiva clasica
  public static void fnValidarNombre(String nombre) {
    if(nombre != null) {
      System.out.println("Longitud: " + nombre.length());
    } else {
      System.out.println("El nombre es nulo, evitamos el crash");
    }
  }

  // Forma 2: El metodo retorna un Optional, un metodo que busca, procesa o devuelve algo que puede ser null
  public static void fnValidarNombreOptional(String nombre) {
    Optional<String> nombreOpcional = Optional.ofNullable(nombre);

    // 1. Si la caja esta vacia, devuelve "Desconocido"
    String resultado = nombreOpcional.orElse("Desconocido");
    System.out.println("Resultado seguro: " + resultado);

    // 2. Ejecutamos algo solo si hay un valor real dentro
    nombreOpcional.ifPresent(n -> System.out.println("El nombre es: " + n));
  }

  public static void main(String[] args) {
    System.out.println("=== Analizando seguridad ante nulos ===");

    String nombreNull = null; // Aqui el puntero no apunta a nada
    String nombreReal = "Alyx"; // Aqui el puntero apunta al nombre

    System.out.println("--- Probando con NULL ---");
    fnValidarNombre(nombreNull);

    System.out.println("\n--- Probando con VALOR REAL ---");
    fnValidarNombreOptional(nombreReal);
  }
}
