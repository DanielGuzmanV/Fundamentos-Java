package projects.java.com.rpgmod;

import projects.java.com.rpgmod.model.entities.EntityCustom;
import projects.java.com.rpgmod.model.entities.LivingEntity;
import projects.java.com.rpgmod.model.entities.Player;
import projects.java.com.rpgmod.model.items.Arma;
import projects.java.com.rpgmod.model.items.Armadura;
import projects.java.com.rpgmod.model.items.Consumible;
import projects.java.com.rpgmod.model.items.Items;
import projects.java.com.rpgmod.services.CatalogoService;
import projects.java.com.rpgmod.services.JuegoService;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

public class GameMainRPG {
  private static final Scanner scanner = new Scanner(System.in);
  private static final CatalogoService catalogoService = new CatalogoService();
  private static final JuegoService juegoService = new JuegoService();

  // Metodo auxiliar para evitar errores en el scanner
  private static int leerEntero() {
    try {
      int valor = scanner.nextInt();
      scanner.nextLine();
      return  valor;
    } catch (InputMismatchException err) {
      scanner.nextLine();
      return -1;
    }
  }

  public static void main(String[] args) {
    System.out.println("=== Simulador RPG Interactivo ===");
    boolean ejecutandoGame = true;

    while (ejecutandoGame) {
      System.out.println("\n --- Menu Principal ---");
      System.out.println("1. Iniciar Juego");
      System.out.println("2. Finalizar");
      System.out.println("Seleccione una opcion: ");

      int opcion = leerEntero();

      switch (opcion) {
        case 1 -> menuJuegoInteractio();
        case 2 -> {
          System.out.println("\n Cerrando el scanner y finalizando la simulacion");
          ejecutandoGame = false;
        }
        default -> System.out.println("Opcion invalida. Intente de nuevo");
      }
    }
    scanner.close();
  }

  // --- Bucle interactivo del juego ---
  private  static void menuJuegoInteractio() {
    boolean enJuego = true;

    while (enJuego) {
      System.out.println("\n==========================================");
      System.out.println("          PANTALLA DE JUEGO       ");
      System.out.println("==========================================");
      System.out.println("1. Bloque 1: Entidades (Crear Jugador / Custom)");
      System.out.println("2. Bloque 2: Ítems (Crear en Catálogo Global)");
      System.out.println("3. Bloque 3: Inventario (Asignar y Consultar)");
      System.out.println("4. Bloque 4: Equipamiento (Equipar Armas/Cascos)");
      System.out.println("5. Bloque 5: Zona de Combate (Atacar)");
      System.out.println("6. Volver al Menú Principal");
      System.out.print("Seleccione un bloque: ");

      int opcion  = leerEntero();

      switch (opcion) {
        case 1 -> bloqueEntidades();
        case 2 -> bloqueItems();
        case 3 -> bloqueInventario();
        case 4 -> bloqueEquipamiento();
        case 5 -> bloqueCombate();
        case 6 -> {
          System.out.println("Regresando al Menú Principal...");
          enJuego = false;
        }
        default -> System.out.println("Opción inválida.");
      }
    }
  }

  // --- Bloque 1: entidades ---
  private static void bloqueEntidades() {
    System.out.println("\n--- BLOQUE 1: ENTIDADES ---");
    System.out.println("1. Crear Jugador (Salud por defecto: 100 HP)");
    System.out.println("2. Crear Entidad Custom (Enemigo/NPC)");
    System.out.println("3. Ver Lista de Entidades");
    System.out.print("Seleccione una opción: ");

    int op = leerEntero();
    switch (op) {
      case 1 -> {
        System.out.print("Ingrese el nickname del jugador: ");
        String nickname = scanner.nextLine();
        Player jugador = new Player(nickname);
        juegoService.registrarEntidad(jugador);
      }
      case 2 -> {
        System.out.print("Ingrese el nombre de la entidad custom: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese la salud máxima: ");
        int salud = leerEntero();
        EntityCustom entidad = new EntityCustom(nombre, salud);
        juegoService.registrarEntidad(entidad);
      }
      case 3 -> juegoService.mostrarEntidades();
      default -> System.out.println("Opción inválida.");
    }
  }

  // --- Bloque 2: items ---
  private static void bloqueItems() {
    System.out.println("\n--- BLOQUE 2: CREACIÓN DE ÍTEMS ---");
    System.out.println("1. Crear Arma");
    System.out.println("2. Crear Armadura");
    System.out.println("3. Crear Consumible");
    System.out.println("4. Ver Catálogo Global");
    System.out.print("Seleccione una opción: ");

    int op = leerEntero();
    switch (op) {
      case 1 -> {
        System.out.print("Nombre del Arma: ");
        String nombre = scanner.nextLine();
        System.out.print("Puntos de Daño: ");
        int dano = leerEntero();
        System.out.print("Durabilidad: ");
        int durabilidad = leerEntero();
        catalogoService.registrarItem(new Arma(nombre, dano, durabilidad));
      }
      case 2 -> {
        System.out.print("Nombre de la Armadura: ");
        String nombre = scanner.nextLine();
        System.out.print("Puntos de Protección: ");
        int prot = leerEntero();
        System.out.print("Durabilidad: ");
        int durabilidad = leerEntero();
        catalogoService.registrarItem(new Armadura(nombre, prot, durabilidad));
      }
      case 3 -> {
        System.out.print("Nombre del Consumible: ");
        String nombre = scanner.nextLine();
        System.out.print("Puntos de Curación (+HP): ");
        int curacion = leerEntero();
        catalogoService.registrarItem(new Consumible(nombre, curacion));
      }
      case 4 -> catalogoService.mostrarCatalogo();
      default -> System.out.println("Opción inválida.");
    }
  }

  // --- Bloque 3: inventario ---
  private static void bloqueInventario() {
    System.out.println("\n--- BLOQUE 3: GESTIÓN DE INVENTARIO ---");
    System.out.println("1. Dar Ítem del Catálogo a una Entidad");
    System.out.println("2. Ver Inventario de un Jugador");
    System.out.print("Seleccione una opción: ");

    int op = leerEntero();
    if (op == 1) {
      if (juegoService.getEntidadesGlobales().isEmpty()) {
        System.out.println("No hay entidades creadas.");
        return;
      }
      if (catalogoService.getCatalogoGlobal().isEmpty()) {
        System.out.println("No hay ítems en el catálogo.");
        return;
      }

      juegoService.mostrarEntidades();
      System.out.print("Seleccione el número de la entidad: ");
      int idxEntidad = leerEntero() - 1;

      Optional<LivingEntity> optEntidad = juegoService.obtenerPorIndice(idxEntidad);
      if (optEntidad.isPresent() && optEntidad.get() instanceof Player jugador) {
        catalogoService.mostrarCatalogo();
        System.out.print("Seleccione el número del ítem a transferir: ");
        int idxItem = leerEntero() - 1;

        Optional<Items> optItem = catalogoService.obtenerPorIndice(idxItem);
        optItem.ifPresent(jugador::guardarEnInventario);
      } else {
        System.out.println("Selección inválida o la entidad no tiene inventario.");
      }
    } else if (op == 2) {
      juegoService.mostrarEntidades();
      System.out.print("Seleccione el número del jugador: ");
      int idx = leerEntero() - 1;
      Optional<LivingEntity> opt = juegoService.obtenerPorIndice(idx);

      if (opt.isPresent() && opt.get() instanceof Player jugador) {
        System.out.println("\n Inventario de [" + jugador.getNombre() + "]:");
        if (jugador.getInventario().isEmpty()) {
          System.out.println("   (Inventario vacío)");
        } else {
          jugador.getInventario().values().forEach(i -> System.out.println("   - " + i.getDetalles()));
        }
      } else {
        System.out.println("El objetivo seleccionado no es un jugador válido.");
      }
    }
  }

  // --- Bloque 4: equipamiento ---
  private static void bloqueEquipamiento() {
    System.out.println("\n--- BLOQUE 4: EQUIPAMIENTO ---");

    // Validar si hay entidades registradas
    if(juegoService.getEntidadesGlobales().isEmpty()) {
      System.out.println("No hay entidades creadas en el juego. Crea un jugador primero.");
      return;
    }
    juegoService.mostrarEntidades();
    System.out.print("Seleccione el número del jugador a equipar: ");

    int idx = leerEntero() - 1;

    Optional<LivingEntity> opt = juegoService.obtenerPorIndice(idx);
    if(opt.isPresent() && opt.get() instanceof  Player jugador) {
      if(jugador.getInventario().isEmpty()) {
        System.out.println("El jugador no tiene ítems en su inventario para equipar.");
        return;
      }

      System.out.println("\nÍtems disponibles en su inventario:");
      jugador.getInventario().values().forEach(i ->
          System.out.println(" - [" + i.getId() + "] " + i.getNombre() + " (" + i.getTipo() + ")")
      );

      System.out.print("Ingrese el ID exacto del ítem a equipar (ej: item_482): ");
      String idItem = scanner.nextLine();

      Items item = jugador.getInventario().get(idItem);
      if (item instanceof Arma arma) {
        jugador.equiparArma(arma);
      } else if (item instanceof Armadura armadura) {
        jugador.equiparCasco(armadura);
      } else {
        System.out.println("El ítem no existe en su inventario o no es equipable.");
      }
    } else {
      System.out.println("La entidad seleccionada no es un jugador válido.");
    }
  }

  // Bloque 5: zona de combate
  private static void bloqueCombate() {
    System.out.println("\n--- BLOQUE 5: ZONA DE COMBATE ---");
    if (juegoService.getEntidadesGlobales().size() < 2) {
      System.out.println("Necesita al menos 2 entidades creadas en el juego para iniciar un combate.");
      return;
    }

    juegoService.mostrarEntidades();
    System.out.print("Seleccione el número del ATACANTE: ");
    int idxAtacante = leerEntero() - 1;

    System.out.print("Seleccione el número del OBJETIVO/VÍCTIMA: ");
    int idxObjetivo = leerEntero() - 1;

    Optional<LivingEntity> optAtacante = juegoService.obtenerPorIndice(idxAtacante);
    Optional<LivingEntity> optObjetivo = juegoService.obtenerPorIndice(idxObjetivo);

    if (optAtacante.isPresent() && optObjetivo.isPresent()) {
      LivingEntity atacante = optAtacante.get();
      LivingEntity objetivo = optObjetivo.get();

      Items armaUsada = null;
      if (atacante instanceof Player jugador) {
        armaUsada = jugador.getArmaEquipada().orElse(null);
      }

      juegoService.ejecutarAtaque(atacante, objetivo, armaUsada);
    } else {
      System.out.println("Selección de combate inválida.");
    }
  }

}
