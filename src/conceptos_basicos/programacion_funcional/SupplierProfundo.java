package conceptos_basicos.programacion_funcional;

import conceptos_basicos.colecciones.Item;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

public class SupplierProfundo {
  public record ItemValue(String id, String nombre, int durabilidad) {}

  // Metodo simulado pesado
  public static String generarUsuarioPorDefecto() {
    System.out.println("[SISTEMA] Generando usuario por defecto en memoria...");
    return "Guest_" + (int) (Math.random() * 1000);
  }

  // Metodo helper generico que usa supplier para llenar una lista con objetos nuevos
  public static <T> List<T> crearLoteItems(int cantidad, Supplier<T> fabrica) {
    List<T> lista = new ArrayList<>();
    for(int idx = 0; idx < cantidad; idx++) {
      lista.add(fabrica.get());
    }
    return  lista;
  }

  public static void main(String[] args) {
    System.out.println("=== 1. Generacion de datos (.get) ===");

    // Primer ejemplo:
    Supplier<String> obtenerHoraActual = () -> LocalDateTime.now()
        .format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    System.out.println("Hora actual: " + obtenerHoraActual.get());

    // Segundo ejemplo:
    Supplier<Integer> generarDado = () -> (int) (Math.random() * 100) + 1;
    System.out.println("Tirada 1: " + generarDado.get());
    System.out.println("Tirada 2: " + generarDado.get());


    System.out.println("\n === 2. Lazy evaluation con Optional.orElseGet ===");

    String userInCache = null;
    String userFinal = Optional.ofNullable(userInCache)
        .orElseGet(() -> generarUsuarioPorDefecto());
    System.out.println("Usuario final cargado: " + userFinal);


    System.out.println("\n === 3. Fabrica de objetos (Factory Pattern) ===");

    // Supplier que actua como fabrica generando items unicos con UUIDs aleatorios
    Supplier<ItemValue> fabricaEspadas = () -> new ItemValue(
        UUID.randomUUID().toString().substring(0, 8),
        "Espada de hierro",
        1561
    );

    // Generamos 3 espadas unicas usando nuestra fabrica
    List<ItemValue> inventario = crearLoteItems(3, fabricaEspadas);
    for(ItemValue item : inventario) {
      System.out.println(" -> Item Generado: [" + item.id() + "] " + item.nombre() + "(Durabilidad: " + item.durabilidad() + ")");
    }
  }
}
