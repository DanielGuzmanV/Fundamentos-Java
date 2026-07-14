package conceptos_basicos.gestion_memoria;

public class PrimivitesExplained {
  public static void main(String[] args) {
    System.out.println("===Analizando datos primitivos en el Stack===");

    // Nivel basico: declaracion, asignacion y copia direct
    int ageOriginal = 32;
    int ageCopy = ageOriginal; // Clonamos el valor en otro casillero
    ageCopy = 30; // Modificar la copia no altera al original

    System.out.println("[Basico] Original: " + ageOriginal);
    System.out.println("[Basico] Copia modificada: " + ageCopy);

    // Nivel intermedio: Desbordamiento de memoria fija
    byte smallNumber = 127; // Este tipo solo soporta de -128 a 127
    smallNumber++; // Esto produce el desbordamiento dando la vuelta y volviendo el valor a -128

    System.out.println("\n[Intermedio] Desbordamiento de un byte: " + smallNumber);

    // Nivel avanzado: Comparacion directa de bits vs Punteros
    double priceOne = 19.99;
    double priceTwo = 19.99;

    // El operador '==' en primitivos compara directamente si los bits guardados en el stack son identicos
    boolean isEquals = (priceOne == priceTwo);
    System.out.println("\n[Avanzado] Comparacion de valores en stack: " + isEquals);
  }
}
