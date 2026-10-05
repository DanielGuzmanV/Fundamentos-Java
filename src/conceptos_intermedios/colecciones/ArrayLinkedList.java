package conceptos_intermedios.colecciones;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ArrayLinkedList {

  // Record auxiliar para simular un item
  public record ItemDrop(String nombre, int cantidad) {}

  // Simulamos la tabla de drops de un bloque al ser destruido
  public List<ItemDrop> getLootDrops(int fortuneLevel) {
    List<ItemDrop> drops = new ArrayList<>();

    // Siempre suelta el recurso base
    drops.add(new ItemDrop("Minel de hierro", 1));

    // Probabilidad de bonus por encantamiento
    if (fortuneLevel > 0 && Math.random() < 0.35) {
      drops.add(new ItemDrop("Pepita de hierro", fortuneLevel * 2));
    }

    return drops;
  }

  // ===================================================================

  // Ejemplo intermedio con ArrayList
  // Simulacion de la entidad jugador
  public static class Player {
    private final String nombre;
    public boolean crash = false;

    public Player(String nombre) {
      this.nombre = nombre;
    }

    public String getNombre() {
      return nombre;
    }
  }

  private final List<Player> playersInDash = new ArrayList<>();

  public void activeDash(Player player) {
    if (!playersInDash.contains(player)) {
      playersInDash.add(player);
    }
  }

  // Procesamos el estado en cada ciclo
  public void tick() {
    for (int index = playersInDash.size() - 1; index >= 0; index--) {
      Player player1 = playersInDash.get(index);
      System.out.println("Aplicando velocidad a: " + player1.getNombre());

      // Removemos si la condicion de termino se cumple
      if (player1.crash) {
        System.out.println("Colision detectada. Removiendo a: " + player1.getNombre());
        playersInDash.remove(index);
      }
    }
  }

  // ====================================================================

  public record Vector3d(double x, double y, double z) {}

  private final int MAX_NODOS_HISTORIAL = 3;
  private final LinkedList<Vector3d> historialTrayectoria = new LinkedList<>();

  public void updatePosition(Vector3d currentPosition) {
    // Agregamos el punto mas reciente al final
    System.out.println("Se agrego una nueva posicion: " + currentPosition);
    historialTrayectoria.addLast(currentPosition);

    // Eliminamos el mas antiguo al superar el limite
    if(historialTrayectoria.size() > MAX_NODOS_HISTORIAL) {
      historialTrayectoria.removeFirst();
      System.out.println("Se elimino la primera posicion");
    }

  }

  public void renderingParticles() {
    System.out.println("Generando particulas en las posiciones: ");
    for(Vector3d pos : historialTrayectoria) {
      System.out.println(" -> Posicion: [" + pos.x() + ", " + pos.y() + ", " + pos.z() + "]");
    }
  }


  public static void main(String[] args) {
    // Prueba del ejemplo basico:
    ArrayLinkedList block = new ArrayLinkedList();
    List<ItemDrop> getLoot = block.getLootDrops(2);

    // Iteramos sobre el array
    for (ItemDrop item : getLoot) {
      System.out.println("Drop: " + item.nombre() + " x " + item.cantidad());
    }

    System.out.println("\n ==================================================");

    // Prueba del nivel intermedio:
    ArrayLinkedList manager = new ArrayLinkedList();
    Player p1 = new Player("Player1");
    Player p2 = new Player("Player2");

    manager.activeDash(p1);
    manager.activeDash(p2);

    p1.crash = true;

    System.out.println("--- Primer Tick ---");
    manager.tick();

    System.out.println("--- Segundo Tick ---");
    manager.tick();

    System.out.println("\n ==================================================");

    ArrayLinkedList tracker = new ArrayLinkedList();

    Vector3d position1 = new Vector3d(1.0, 64.0, 1.0);
    Vector3d position2 = new Vector3d(2.0, 64.0, 2.0);
    Vector3d position3 = new Vector3d(3.0, 64.0, 3.0);
    Vector3d position4 = new Vector3d(4.0, 64.0, 4.0);

    tracker.updatePosition(position1);
    tracker.updatePosition(position2);
    tracker.updatePosition(position3);

    // Agregar una cuarta posicion descartara la primera
    tracker.updatePosition(position4);

    tracker.renderingParticles();

  }
}
