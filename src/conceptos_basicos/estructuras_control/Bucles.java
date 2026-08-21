package conceptos_basicos.estructuras_control;

import java.util.ArrayList;
import java.util.List;

public class Bucles {
  public void iterarEjemplos(){
    // 1. Bucle for tradicional
    for(int index = 0; index < 5; index++) {
      System.out.println("Iteracion contador: " + index);
    }
    System.out.println("\n ==========================");

    // 2. For (para recorrer colecciones o areglos)
    String[] listNames = {"Carlos", "Marco", "Albert"};
    for(String name : listNames) {
      System.out.println("Nombres: " + name);
    }
    System.out.println("\n ==========================");

    // 3. For-each
    List<String> objetos = new ArrayList<>();
    objetos.add("Madera");
    objetos.add("Agua");
    objetos.add("Sabana");

    objetos.forEach(item -> {
      System.out.println("Mostrar objetos: " + item);
    });
    System.out.println("\n ==========================");

    // 4. While
    int vida = 100;

    System.out.println("vida actual: " + vida);
    while(vida > 0) {
      vida -= 25;
      System.out.println("vida restante: " + vida);
    }
    System.out.println("\n ==========================");

    // 5. Do-while
    int intentos = 0;
    System.out.println("Numero de intentos: " + intentos);
    do {
      intentos++;
      System.out.println("Numero de intentos: " + intentos);
    } while (intentos <= 1);
    System.out.println("Fin de intentos");
  }

  public static void main(String[] args) {
    Bucles iterar = new Bucles();

    iterar.iterarEjemplos();
  }
}
