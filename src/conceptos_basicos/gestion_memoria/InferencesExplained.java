package conceptos_basicos.gestion_memoria;

import java.util.ArrayList;

public class InferencesExplained {
  public static void main(String[] args) {
    System.out.println("=== Analizando la inferencia de tipos locales ===");

    // Nivel basico
    // ------------
    var age = 36;
    var name = "Carlos";
    System.out.println("[Basico] Edad inferida: " + age);
    System.out.println("[Basico] Nombre inferid: " + name);

    // Nivel intermedio: tipado fijo vs dinamico, no deja cambiar el tipo despues
    // --------------------------------------------------------------------------
    var activeControl = true; // Tipo fijado como boolean en compilacion
    // activeControl = "Si"; // No podremos cambiarlo a otro tipo
    System.out.println("\n[Intermedio] El tipo sigue siendo boolean: " + activeControl);

    // Nivel avanzado: Donde usar y no usar var
    // ----------------------------------------
    // Ejemplo donde si se usa: evita escribir nombre de tipos redundantes y masivos
    var listaDeUsuarios = new ArrayList<UserAccount>();

    listaDeUsuarios.add(new UserAccount());
    System.out.println("\n[Avanzado] Instanciacion simplificada con var: " + listaDeUsuarios.getClass().getSimpleName());
  }
}