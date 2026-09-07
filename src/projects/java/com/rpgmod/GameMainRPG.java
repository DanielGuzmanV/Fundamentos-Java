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

  // Metodo auxiliar para leer textos validos
  private static String readText(String message) {
    String textInput = "";
    while (textInput.trim().isEmpty()) {
      System.out.println(message);
      textInput = scanner.nextLine();
      if(textInput.trim().isEmpty()) {
        System.out.println("Error: el campo no puede estar vacio, Intente de nuevo");
      }
    }
    return textInput.trim();
  }

  // Validar entradas:
  private static int readInputs(String message) {
    int valor = -1;
    while (valor <= 0) {
      System.out.println(message);
      valor = leerEntero();
      if (valor <= 0) {
        System.out.println("Error: El valor debe ser un numero mayor a 0.");
      }
    }
    return valor;
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
    boolean enBloque = true;

    while (enBloque) {
      System.out.println("\n--- BLOQUE 1: ENTIDADES ---");
      System.out.println("1. Crear Jugador (Salud por defecto: 100 HP)");
      System.out.println("2. Crear Entidad Custom (Enemigo/NPC)");
      System.out.println("3. Ver Lista de Entidades");
      System.out.println("4. Volver a la 'Pantall de Juego'");
      System.out.print("Seleccione una opción: ");

      int op = leerEntero();
      switch (op) {
        case 1 -> {
          String nickname = readText("Ingrese el nickname del jugador: ");
          Player jugador = new Player(nickname);
          juegoService.registrarEntidad(jugador);
        }
        case 2 -> {
          String nombre = readText("Ingrese el nombre de la entidad custom: " );
          int salud = readInputs("Ingrese la salud maxima (debe ser mayor a 0): ");
          EntityCustom entidad = new EntityCustom(nombre, salud);
          juegoService.registrarEntidad(entidad);
        }
        case 3 -> juegoService.mostrarEntidades();
        case 4 -> {
          System.out.println("Regresando a la Pantalla de Juego...");
          enBloque = false;
        }
        default -> System.out.println("Opción inválida.");
      }
    }
  }

  // --- Bloque 2: items ---
  private static void bloqueItems() {
    boolean enBloque = true;

    while (enBloque) {
      System.out.println("\n--- BLOQUE 2: CREACIÓN DE ÍTEMS ---");
      System.out.println("1. Crear Arma");
      System.out.println("2. Crear Armadura");
      System.out.println("3. Crear Consumible");
      System.out.println("4. Ver Catálogo Global");
      System.out.println("5. Volver a la 'Pantalla de Juego'");
      System.out.print("Seleccione una opción: ");

      int op = leerEntero();
      switch (op) {
        case 1 -> {
          String nombre = readText("Nombre del Arma: " );
          int damage = readInputs("Ingres los puntos de daño: ");
          int durability = readInputs("Ingrese los puntos de durabilidad: ");
          catalogoService.registrarItem(new Arma(nombre, damage, durability));
        }
        case 2 -> {
          String nombre = readText("Nombre de la Armadura: ");
          int prot = readInputs("Punto de Proteccion: ");
          int durabilidad = readInputs("Durabilidad: ");
          catalogoService.registrarItem(new Armadura(nombre, prot, durabilidad));
        }
        case 3 -> {
          String nombre = readText("Nombre del Consumible: ");
          int curacion = readInputs("Puntos de Curacion (+HP): ");
          catalogoService.registrarItem(new Consumible(nombre, curacion));
        }
        case 4 -> catalogoService.mostrarCatalogo();
        case 5 -> {
          System.out.println("Regresando a la Pantalla de Juego...");
          enBloque = false;
        }
        default -> System.out.println("Opción inválida.");
      }
    }
  }

  // --- Bloque 3: inventario ---
  private static void bloqueInventario() {
    boolean enBloque = true;

    while (enBloque) {
      System.out.println("\n--- BLOQUE 3: GESTIÓN DE INVENTARIO ---");
      System.out.println("1. Dar Ítem del Catálogo a una Entidad");
      System.out.println("2. Ver Inventario de un Jugador");
      System.out.println("3. Volver a la 'Pantalla de Juego'");
      System.out.print("Seleccione una opción: ");

      int op = leerEntero();
      switch (op) {
        case 1 -> {
          if (juegoService.getEntidadesGlobales().isEmpty()) {
            System.out.println("No hay entidades creadas.");
            break;
          }
          if (catalogoService.getCatalogoGlobal().isEmpty()) {
            System.out.println("No hay ítems en el catálogo.");
            break;
          }

          juegoService.mostrarEntidades();
          int idxEntidad = readInputs("Seleccione el numero de la entidad: ") - 1;

          Optional<LivingEntity> optEntidad = juegoService.obtenerPorIndice(idxEntidad);
          if (optEntidad.isPresent() && optEntidad.get() instanceof Player jugador) {
            catalogoService.mostrarCatalogo();
            int idxItem = readInputs("Seleccione el numero del item a transferir: ") - 1;

            Optional<Items> optItem = catalogoService.obtenerPorIndice(idxItem);
            optItem.ifPresent(jugador::guardarEnInventario);
          } else {
            System.out.println("Selección inválida o la entidad no tiene inventario.");
          }
        }
        case 2 -> {
          if(juegoService.getEntidadesGlobales().isEmpty()) {
            System.out.println("No hay entidades creadas.");
            break;
          }

          juegoService.mostrarEntidades();
          int idx = readInputs("Seleccione el numero del jugador: ") - 1;

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
        case 3 -> {
          System.out.println("Regresando a la Pantalla de Juego...");
          enBloque = false;
        }
        default -> System.out.println("Opcion invalida.");
      }
    }
  }

  // --- Bloque 4: equipamiento ---
  private static void bloqueEquipamiento() {
    boolean enBloque = true;

    while (enBloque) {

      System.out.println("\n--- BLOQUE 4: EQUIPAMIENTO ---");
      System.out.println("1. Equipar item del Inventario");
      System.out.println("2. Volver a la pantalla de juego");
      System.out.print("Seleccione una opcion");

      int op = leerEntero();

      switch (op) {
        case 1 -> {
          // Validar si hay entidades registradas
          if(juegoService.getEntidadesGlobales().isEmpty()) {
            System.out.println("No hay entidades creadas en el juego. Crea un jugador primero.");
            break;
          }

          juegoService.mostrarEntidades();
          int idx = readInputs("Seleccione el numero del jugador a equipar: ") - 1;

          Optional<LivingEntity> opt = juegoService.obtenerPorIndice(idx);

          if(opt.isPresent() && opt.get() instanceof Player jugador) {
            if(jugador.getInventario().isEmpty()) {
              System.out.println("El jugador no tiene ítems en su inventario para equipar.");
              break;
            }

            System.out.println("\nÍtems disponibles en su inventario:");
            jugador.getInventario().values().forEach(i ->
                System.out.println(" - [" + i.getId() + "] " + i.getNombre() + " (" + i.getTipo() + ")")
            );

            String idItem = readText("Ingrese el ID exacto del item a equipar (ej: item_468): ");
            Items item = jugador.getInventario().get(idItem);

            if(item == null) {
              System.out.println("El item con ID '" + idItem + "' no existe en el inventario");
              break;
            }

            switch (item) {
              case Arma arma -> jugador.equiparArma(arma);
              case Armadura armadura -> jugador.equiparCasco(armadura);
              default -> System.out.println("El item seleccionado no es un equipamiento valido.");
            }
          } else {
            System.out.println("La entidad seleccionada no es un jugador válido.");
          }
        }
        case 2 -> {
          System.out.println("Regresando a la pantalla de juego...");
          enBloque = false;
        }
        default -> System.out.println("Opcion invalida");
      }
    }
  }

  // Bloque 5: zona de combate
  private static void bloqueCombate() {
    boolean enBloque = true;

    while (enBloque) {
      System.out.println("\n--- BLOQUE 5: ZONA DE COMBATE ---");
      System.out.println("1. Ejecutar un Ataque");
      System.out.println("2. Volver a la 'Pantalla de Juego'");
      System.out.print("Seleccione una opción: ");

      int op = leerEntero();

      switch (op) {
        case 1 -> {
          if (juegoService.getEntidadesGlobales().size() < 2) {
            System.out.println("Necesita al menos 2 entidades creadas en el juego para iniciar un combate.");
            break;
          }

          juegoService.mostrarEntidades();
          int idxAtacante = readInputs("Seleccione el numero del ATACANTE: ") - 1;
          int idxObjetivo = readInputs("Seleccione el numero del OBJETIVO/VICTIMA: ") - 1;

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
            System.out.println("Selección de combate inválida. Verifica los numeros seleccionados");
          }
        }
        case 2 -> {
          System.out.println("Regresando a la Pantalla de Juego...");
          enBloque = false;
        }
        default -> System.out.println("Opcion invalida.");
      }
    }
  }
}
