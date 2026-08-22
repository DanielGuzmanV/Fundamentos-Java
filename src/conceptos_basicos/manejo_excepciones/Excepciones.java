package conceptos_basicos.manejo_excepciones;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Excepciones {

  // Manejo errores de entrada con "Scanner":
  public static void errorTesting(Scanner scanner) {
    try {
      System.out.println("Ingresa el nivel de jugador (Valor numerico): ");
      int nivel = scanner.nextInt();

      System.out.println("Ingresa divisor para calcular experiencia: ");
      int divisor = scanner.nextInt();

      int resultado = nivel / divisor;
      System.out.println("Calculo exitoso: " + resultado);

    } catch (InputMismatchException e) {
      System.out.println("ERROR: Debes ingresar un numero entero valido, no texto");
    } catch (ArithmeticException e) {
      System.out.println("ERROR: No se puede dividir entre cero");
    } catch (Exception e) {
      System.out.println("Ocurrio un error inesperado: " + e.getMessage());
    } finally {
      System.out.println("Limpiando y cerrando recursos...");
      scanner.close();
    }

    System.out.println("Programa continuando su ejecucion normal sin colapsar");
  }

  // Metodo con validacion manual mediante throw:
  public static void validationCustom(RegisterPlayer registro) {
    try {
      registro.registrar("Manuel", 10);
    } catch (IllegalArgumentException e) {
      System.out.println("Error de registro: " + e.getMessage());
    }

    System.out.println("---------------------------------------");

    try {
      registro.registrar("Marco", -10);
    } catch (IllegalArgumentException error) {
      System.out.println("Error capturado correctamente: " + error.getMessage());
    }

    System.out.println("\n Programa sin colapsarse y puede continuar funcionando");
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    RegisterPlayer registro = new RegisterPlayer();

    Excepciones.validationCustom(registro);

    Excepciones.errorTesting(scanner);

  }
}
