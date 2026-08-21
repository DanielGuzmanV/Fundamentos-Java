package conceptos_basicos.estructuras_control;

import java.util.Scanner;

public class Condicionales {
  public void evaluarAcceso(int nivel, int rol, int dia) {
    // 1. switch - moderno
    String role = switch (rol) {
      case 1 -> "ADMIN";
      case 2, 3 -> "MODERADOR";
      case 4 -> "VIP";
      default -> "Desconocido";
    };
    System.out.println("El rol es: " + role);

    // 2 if - else (tradicional)
    if(nivel >= 50 && role.equals("ADMIN")) {
      System.out.println("Acceso Total");
      System.out.println("Nivel: " + nivel + " | nivel adecuado");
    } else if(nivel >= 25) {
      System.out.println("Acceso Estandar");
      System.out.println("Nivel: " + nivel + " | nivel regular");
    } else {
      System.out.println("Acceso denegado");
      System.out.println("Nivel: " + nivel + " | muy bajo");
    }

    // 3. switch - clasico
    String nombreDia;
      switch (dia) {
        case 1:
          nombreDia = "Lunes";
          break;
        case 2:
          nombreDia = "Martes";
          break;
        case 3:
          nombreDia = "Miercoles";
          break;
        case 4:
          nombreDia = "Jueves";
          break;
        default:
          nombreDia = "Desconocido";
          break;
      }
    System.out.println("El dia es: " + nombreDia);
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Condicionales condiciones = new Condicionales();

    System.out.println(" --- Prueba interactiva de rangos y niveles ---");

    System.out.println("Ingrese tu nivel (ej: 10, 25, 50): ");
    int nivelIngresado = scanner.nextInt();
    scanner.nextLine();

    System.out.println("Ingresa tu rol (1, 2, 3 o 4): ");
    int rolIngresado = scanner.nextInt();
    scanner.nextLine();

    System.out.println("Ingresa el dia (1, 2, 3 o 4): ");
    int diaIngresado = scanner.nextInt();

    condiciones.evaluarAcceso(nivelIngresado, rolIngresado, diaIngresado);

    scanner.close();
  }
}
