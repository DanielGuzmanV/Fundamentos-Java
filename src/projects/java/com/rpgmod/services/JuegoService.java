package projects.java.com.rpgmod.services;

import projects.java.com.rpgmod.exception.DamageInvalidException;
import projects.java.com.rpgmod.model.entities.LivingEntity;
import projects.java.com.rpgmod.model.entities.Player;
import projects.java.com.rpgmod.model.items.Arma;
import projects.java.com.rpgmod.model.items.Items;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JuegoService {
  private final List<LivingEntity> entidadesGlobales;

  public JuegoService() {
    this.entidadesGlobales = new ArrayList<>();
  }

  // Registrar cualquier tipo de entidad
  public void registrarEntidad(LivingEntity entidad) {
    this.entidadesGlobales.add(entidad);
    System.out.println("Entidad registrada: " + entidad.getNombre() + " [" + entidad.getId() + "] | Salud: " + entidad.getVidaActual() + "/" + entidad.getVidaMax());
  }

  // Mostrar la lista completa de entidades vivas o creadas
  public void mostrarEntidades() {
    if(entidadesGlobales.isEmpty()) {
      System.out.println("No hay entidades creadas en el juego.");
      return;
    }

    System.out.println("\n === Lista de entidades ===");
    for(int idx = 0; idx < entidadesGlobales.size(); idx++) {
      LivingEntity entidad = entidadesGlobales.get(idx);
      String tipo = (entidad instanceof Player) ? "Jugador" : "Entidad Custom";
      String estado = entidad.estaVivo() ? "Vivo (" + entidad.getVidaActual() + "/" + entidad.getVidaMax() + ") HP" : "Entidad sin vida";

      System.out.println((idx + 1) + ".- [" + tipo + "] " + entidad.getNombre() + " (ID: " + entidad.getId() + ") - " + estado);
    }
  }

  // Obtener entidad por indice del menu de consola
  public Optional<LivingEntity> obtenerPorIndice(int indice) {
    if(indice >= 0 && indice < entidadesGlobales.size()) {
      return Optional.of(entidadesGlobales.get(indice));
    }
    return Optional.empty();
  }

  // Logica de combate entre dos entidades
  public void ejecutarAtaque(LivingEntity atacante, LivingEntity objetivo, Items itemUsado) {
    if(!atacante.estaVivo()) {
      System.out.println("[" + atacante.getNombre() + "] no puede atacar porque está muerto.");
      return;
    }

    if (!objetivo.estaVivo()) {
      System.out.println("[" + objetivo.getNombre() + "] ya se encuentra derrotado.");
      return;
    }

    int damageDefault = 5;
    String nombreArma = "Manos";

    if(itemUsado instanceof Arma arma) {
      damageDefault = arma.getDamagePoints();
      nombreArma = arma.getNombre();
      arma.reducirDurabilidad();
    }

    System.out.println("\n COMBATE: [" + atacante.getNombre() + "] ataca a [" + objetivo.getNombre() + "] usando " + nombreArma + " (" + damageDefault + ") dmg");

    try {
      objetivo.aplicarDamage(damageDefault);
      if(!objetivo.estaVivo()) {
        System.out.println("MUERTO [" + objetivo.getNombre() + "] ha sido derrotado por [" + atacante.getNombre() + "]");
      }
    } catch (DamageInvalidException err) {
      System.out.println("ERROR: en combate: " + err.getMessage());
    }
  }

  public List<LivingEntity> getEntidadesGlobales() {
    return entidadesGlobales;
  }
}