package conceptos_basicos.poo.abstract_interface;

public class MainInterfaces {
  public static void main(String[] args) {
    DragonBoss dragon = new DragonBoss("Dragon GOAT", 1000);

    dragon.emitirSonido();
    dragon.despegar();
    dragon.lanzarFuego(150);

    System.out.println("Guardando estado: " + dragon.obtenerDatosGuardado());
  }
}
