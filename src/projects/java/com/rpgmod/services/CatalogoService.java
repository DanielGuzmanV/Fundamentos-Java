package projects.java.com.rpgmod.services;

import projects.java.com.rpgmod.model.items.Items;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CatalogoService {
  private final List<Items> catalogoGlobal;

  // Constructor:
  public CatalogoService() {
    this.catalogoGlobal = new ArrayList<>();
  }

  // Getter:
  public List<Items> getCatalogoGlobal() { return catalogoGlobal;}

  // Metodos:
  // Metodo 1: guardar cualquier subclase de item
  public void registrarItem(Items item) {
    this.catalogoGlobal.add(item);
    System.out.println("Item registrado en el Catalogo Global: " + item.getNombre() + " [" + item.getId() + "]");
  }

  // Metodo 2: ver la lista completa del catalogo
  public void mostrarCatalogo() {
    if(catalogoGlobal.isEmpty()) {
      System.out.println("El catalogo global esta vacio. Crea algunos items primero");
      return;
    }

    System.out.println("\n === Catalogo Global de ITEMS ===");
    for (int idx = 0; idx < catalogoGlobal.size(); idx++) {
      Items item = catalogoGlobal.get(idx);
      System.out.println((idx + 1) + ".- " + item.getDetalles());
    }
  }

  // Metodo 3: buscar un item por su ID
  public Optional<Items> buscarPorId(String id) {
    return catalogoGlobal.stream()
        .filter(item -> item.getId().equalsIgnoreCase(id))
        .findFirst();
  }

  // Metodo 4: obtener un item por su indice numerico
  public Optional<Items> obtenerPorIndice(int indice) {
    if(indice >= 0 && indice < catalogoGlobal.size()) {
      return Optional.of(catalogoGlobal.get(indice));
    }
    return Optional.empty();
  }
}
