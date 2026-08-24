package conceptos_basicos.poo.herencias;

import java.util.ArrayList;
import java.util.List;

public class MainHerencia {
  public static void main(String[] args) {
    // Polimorfismo: Referencias de tipo 'ClaseEntidad' apuntando a instancias hijas
    ClaseEntidad player = new ClaseJugador("Marco", 100, "Espada de hierro");
    ClaseEntidad zombie = new ClaseZombie("Zombie common", 50);

    // Lista polimorfica:
    List<ClaseEntidad> listaEntidades = new ArrayList<>();
    listaEntidades.add(player);
    listaEntidades.add(zombie);

    System.out.println("=== Turno de Ataques ===");
    for(ClaseEntidad e : listaEntidades) {
      e.atacar();
    }



  }
}
