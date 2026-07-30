package conceptos_basicos.colecciones;

import java.util.ArrayList;
import java.util.List;

public class Inventario {
  private List<String> armas;

  public Inventario() {
    this.armas = new ArrayList<>();
  }

  public void agregarArma(String arma) {
    this.armas.add(arma);
  }

  public List<String> getArmas() {
    return armas;
  }
}
