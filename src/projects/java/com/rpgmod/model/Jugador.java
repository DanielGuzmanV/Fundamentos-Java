package projects.java.com.rpgmod.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Jugador extends EntidadLiving {
  private final String nickname;
  private Item cascoEquipado;
  private Map<String, Item> inventario;

  public Jugador(String id, String nickname, int vidaMaxima) {
    super(id, vidaMaxima);
    this.nickname = nickname;
    this.inventario = new HashMap<>();
  }

  // Getter para ver el nombre:
  public String getNickname() { return nickname; }

  // Agregar un metodo o getter para ver el inventario:

  // Optional: evitamos el NullPointerException al pedir el casco equipado
  public Optional<Item> getCascoEquipado() {
    return Optional.ofNullable(this.cascoEquipado);
  }

  public void equiparCaso(Item casco) {
    this.cascoEquipado = casco;
    System.out.println("CASCO: " + nickname + " se equipo: " + casco.getNombre());
  }

  public void guardarEnInventario(Item item) {
    this.inventario.put(item.getId(), item);
    System.out.println("Se guardo: " + item.getNombre());
  }

  @Override
  public void ejecutarTickIA() {
    // Programacion funcional / lambdas: Reducimos la duracion de efectsp
    efectosActivos.forEach(EfectoEstado::reducirDuracion);

    // Limpiamos los efectos expirados mediante expresion lambda / Predicado
    efectosActivos.removeIf(efecto -> efecto.getDuracionTicks() <= 0);
  }
}
