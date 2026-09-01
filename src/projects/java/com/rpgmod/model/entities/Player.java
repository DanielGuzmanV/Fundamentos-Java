package projects.java.com.rpgmod.model.entities;

import projects.java.com.rpgmod.model.items.Items;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Player extends LivingEntity {
  private Items cascoEquipado;
  private Items armaEquipada;
  private Map<String, Items> inventario;

  public Player(String nombre) {
    super("player_", nombre, 100);
    this.inventario = new HashMap<>();
  }

  // Getters:
  public Optional<Items> getCascoEquipado() {
    return Optional.ofNullable(this.cascoEquipado);
  }

  public Optional<Items> getArmaEquipada() {
    return Optional.ofNullable(this.armaEquipada);
  }

  // Metodos:
  public void equiparCasco(Items casco){
    this.cascoEquipado = casco;
    System.out.println(casco.getTipo() + "| " + getNombre() + " se equipo: " + casco.getNombre());
  }

  public void equiparArma(Items arma) {
    this.armaEquipada = arma;
    System.out.println(arma.getTipo() + "| " + getNombre() + " equipo como arma principal: " + arma.getNombre());
  }

  public void guardarEnInventario(Items item) {
    this.inventario.put(item.getId(), item);
    System.out.println("Se guardo en inventario de [" + getNombre() + "]: " + item.getNombre());
  }

  public Map<String, Items> getInventario () {
    return inventario;
  }

  @Override
  public void ejecutarTickIA() {
    efectosActivos.forEach(StateEffect::reducirDuracion);
    efectosActivos.removeIf(efecto -> efecto.getDuracionTicks() <= 0);
  }
}
