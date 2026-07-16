package conceptos_basicos.clases_envolventes;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class WrapperExplicados {
  public static void main(String[] args) {
    // Autoboxing
    // ----------
    int primitiveNumber = 100; // Dato primitivo
    // Metemos ese dato en la caja de Integer
    Integer wrapperNumber = primitiveNumber;
    System.out.println("[Autoboxing] Primitivo convertido a objeto: " + wrapperNumber);

    // Unboxing
    // --------
    Integer heapBox = Integer.valueOf(500); // Creamos una caja de forma explicita
    int primitiveValue = heapBox; // Sacamos el dato de la caja y lo envolvemos en un primitivo simple
    System.out.println("[Unboxing] Objeto extraido a primitivo: " + primitiveValue);

    // Uso 1: Las colecciones no aceptan primitivos
    // --------------------------------------------
    // ArrayList<int> lista; // Nos dara error de compilacion
    ArrayList<Integer> listBoxes = new ArrayList<>();

    listBoxes.add(99);
    listBoxes.add(120);

    System.out.println("\n[Colecciones] Elemento de la lista: " + listBoxes.get(0));

    // Uso 2: El soporte de nulidad (null)
    // -----------------------------------
    // int saldoReal = null; // El dato primitivo no puede estar vacio
    Integer saldoPendiente = null;
    System.out.println("[Nulos] El saldo pendiente es: " + saldoPendiente);

    // Uso 3: Metodos estaticos
    String numberText = "1234"; // Las clases envolventes tienen utilidades

    // Convertimos un String de texto a numero entero primitivo
    int numeroConvertido = Integer.parseInt(numberText);
    System.out.println("[Utilidades] Texto convertido a entero: " + (numeroConvertido + 6));
  }
}
