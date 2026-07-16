package conceptos_basicos.manejo_nulos;

import java.util.Optional;

public class NullSafetyExplained {

  // Validar nombre
  public static void fnValidarNombre(String nombre) {
    if(nombre != null) {
      System.out.println("Longitud: " + nombre.length());
    } else {
      System.out.println("El nombre es nulo, evitamos el crash");
    }
  }

  public static void main(String[] args) {
    System.out.println("=== Analizando seguridad ante nulos ===");

    String nombreNull = "Pamela"; // Aqui el puntero no apunta a nada

    // Forma 1: Defensive programming
    // ------------------------------
    fnValidarNombre(nombreNull);

    // Forma 2: optional<T> (forma moderna)
    // ------------------------------------
    Optional<String> nombreOpcional = Optional.ofNullable(nombreNull);

    // Si la caja esta vacia, devuelve "Desconocido"
    String resultado = nombreOpcional.orElse("Desconocido");
    System.out.println("Resultado seguro: " + resultado);

    // Ejecutamos algo si hay algo de valor
    nombreOpcional.ifPresent(n -> System.out.println("El nombre es: " +n));
  }
}
