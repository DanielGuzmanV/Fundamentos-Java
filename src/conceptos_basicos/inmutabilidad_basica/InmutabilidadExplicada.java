package conceptos_basicos.inmutabilidad_basica;

import java.util.ArrayList;
import java.util.List;

public final class InmutabilidadExplicada {
  public static void main(String[] args) {
    System.out.println("=== ANALIZANDO INMUTABILIDAD CON FINAL ===");

    // Instanciamos un jugador
    Jugador jugador1 = new Jugador("UUID-9981", "CarlosDev");

    // Intentamos reasignar en variable local final:
    final int limiteIntentos = 3;
    // limiteIntentos = 5; // Nos saldra un error de compilacion

    // Intento de cambiar un atributo final
    // jugador1.idJugador = "UUID-0000"; // Error: el idJugador es private y final

    // Lo que si podemos realizar: modificar el estaod interno permitido
    System.out.println("Jugador: " + jugador1.getUsername() + " | Nivel actual: " + jugador1.getNivel());
    jugador1.subirNivel();

    System.out.println("Despues de subir nivel | Nivel actual: " + jugador1.getNivel());
    System.out.println("Llego al nivel maximo?: " + jugador1.esNivelMaximo());

    System.out.println("=============================================");

    // Prubas con listas finales e inmutables
    // --------------------------------------
    final ArrayList<String> inventario = new ArrayList<>(); // Array final
    ArrayList<String> datos = new ArrayList<>(); // Array sin final

    // No podemos cambiar la direccion para que apunte a otra lado
    //inventario = new ArrayList<>();

    // Aqui si podemos cambiarlo
    datos = new ArrayList<>();

    // Lo que si podemos realizar, la etiqueta sigue apuntando al mismo lugar, solo se modifica lo que hay dentro
    inventario.add("Espada de Fuego");
    inventario.add("Escudo");
    inventario.add("Antorcha");
    inventario.remove(0);
    System.out.println("Datos del inventario: " + inventario);

    // Inmutabilidad Total: Aqui no cambia la referencia, ni se puede agregar o quitar
    // -------------------------------------------------------------------------------
    final List<String> inventarioInmutable = List.of("Espada", "Escudo");

    // Inventamos agregar:
    // inventarioInmutable.add("Casco"); // Saldra un error en tiempo de ejecucion
    System.out.println("Lista inmutable: " + inventarioInmutable);
  }
}
