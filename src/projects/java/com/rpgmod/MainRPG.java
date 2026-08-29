package projects.java.com.rpgmod;

import projects.java.com.rpgmod.exception.DamageInvalidException;
import projects.java.com.rpgmod.model.EntidadLiving;
import projects.java.com.rpgmod.model.Item;
import projects.java.com.rpgmod.model.Jugador;

public class MainRPG {
  public static void main(String[] args) {
    System.out.println("=== Simulador RPG MOD backend ===\n");

    // Instanciacion del jugador
    Jugador carlitos = new Jugador("player_012", "Carlitos", 100);

   // Uso de static nested class
    EntidadLiving.EfectoEstado regeneracion = new EntidadLiving.EfectoEstado("Regeneracion II", 2);
    carlitos.agregarEfecto(regeneracion);

    // Uso de optional para evitar NPE
    System.out.println("\n --- Probando casco (Optional) ---");
    carlitos.getCascoEquipado().ifPresentOrElse(
        casco -> System.out.println("Casco actual: " + casco.getNombre()),
        () -> System.out.println("El jugador no tiene casco equipado")
    );

    Item cascoHierro = new Item("iron_helmet", "Casco de Hierro", 250);
    carlitos.equiparCaso(cascoHierro);

    System.out.println("Ver el item: " + cascoHierro.getNombre() + " | " + cascoHierro.getDurabilidad());

    carlitos.getCascoEquipado().ifPresent(
        casco -> System.out.println("Casco actual: " + casco.getNombre())
    );

    carlitos.guardarEnInventario(cascoHierro);

    // Prueba de daño y manejo de excepciones (try-catch)
    System.out.println("\n --- Aplicando daño y excepciones ---");
    try {
      carlitos.aplicarDamage(5);
      carlitos.aplicarDamage(-10);
    } catch (DamageInvalidException e) {
      System.out.println("ERROR controlado: " + e.getMessage());
    } finally {
      System.out.println("Estado final de vida: " + carlitos.estaVivo());
    }

    // Simulacion de ticks
    System.out.println("\n --- Ejecutando ticks de juego ---");
    carlitos.ejecutarTickIA();
    //carlitos.ejecutarTickIA();

  }
}
