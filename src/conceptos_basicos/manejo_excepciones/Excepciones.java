package conceptos_basicos.manejo_excepciones;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Excepciones {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.println("=== Prueba de manejo de excepciones ===");
    try{
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
}
