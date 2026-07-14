package conceptos_basicos.gestion_memoria;

// Definimos una clase pequeña para simular un objeto en el heap
class UserAccount {
  String username;
  int saldo;
}

public class ReferencesExplained {
  public static void main(String[] args) {
    System.out.println("=== Analizando tipos de referencia (Punteros al heap) ===");

    // Nivel basico: Mutacion involuntaria por copia de punteros
    // ---------------------------------------------------------
    int[] originalNotes = {70, 80, 90};
    System.out.println("[Basico] Array original sin modificar: " + originalNotes[0]);

    int[] copyNotes = originalNotes; // Se copia la direccion, no los datos
    copyNotes[0] = 100;
    System.out.println("[Basico] Array original modificado: " + originalNotes[0]);

    // Nivel intermedio: Objetos que contienen primitivos
    // --------------------------------------------------
    UserAccount accountA = new UserAccount(); // Instancia creada en el heap
    accountA.username = "Marco";
    accountA.saldo = 500;
    System.out.println("\n[Intermedio] Saldo cuenta A (sin modificar): " + accountA.saldo);

    // Nueva instancia
    UserAccount accountB = accountA;
    accountB.saldo = 1000;
    System.out.println("\n[Intermedio] Saldo cuenta B (modificado): " + accountB.saldo);

    // Nivel avanzado: Peligro del operador '==' en referencias
    int[] arrayOne = {1, 2, 3};
    int[] arrayTwo = {1, 2, 3};

    // Aunque contienen los mismos datos, viven en diferentes cajas del Heap (ej: 0x333 y 0x444).
    boolean samePointer = (arrayOne == arrayTwo);
    System.out.println("\n[Avanzado] Es el mismo puntero?: " + samePointer);

    // Para Strings, que son objetos, siempre se usa .equals() para comparar el contenido del Heap y no las direcciones.
    String str1 = new String("Manzana");
    String str2 = new String("Manzana");
    System.out.println("[Avanzado] Comparacion de direcciones en Strings: " + (str1 == str2));
    System.out.println("[Avanzado] Comparacion de contenido real: " + str1.equals(str2));
  }
}
