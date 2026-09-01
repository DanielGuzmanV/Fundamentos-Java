package projects.java.com.rpgmod.model.entities;

public class EntityCustom extends LivingEntity {

  public EntityCustom(String nombre, int vidaMaxima) {
    super("entidad_", nombre, vidaMaxima);
  }

  @Override
  public void ejecutarTickIA() {
    efectosActivos.forEach(StateEffect::reducirDuracion);
    efectosActivos.removeIf(efecto -> efecto.getDuracionTicks() <= 0);
  }
}
