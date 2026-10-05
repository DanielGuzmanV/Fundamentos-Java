package conceptos_intermedios.colecciones;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class Mapas {

  // Enumeracion de niveles de raridad de un item
  public enum Raridad {
    COMUN,
    RARO,
    EPICO,
    LEGENDARIO
  }

  public static void main(String[] args) {
    // Nivel basico: HashMap:
    // Clave: Nombre del item y valor
    Map<String, Integer> inventory = new HashMap<>();

    // Data
    String name1 = "raw_titanium";
    Integer amount1 = 64;
    String name2 = "titanium_ingot";
    Integer amount2 = 12;

    // Insertar o actualizar elementos
    inventory.put(name1, amount1);
    inventory.put(name2, amount2);

    System.out.println("Datos del Map: " + inventory);

    // Sobreescribimos el valor de "raw_titanium"
    inventory.put(name1, inventory.get(name1) + 16);
    System.out.println("Datos actualizados: " + inventory);

    // Busqueda rapida por clave
    int amountTItanium = inventory.getOrDefault(name1, 0);
    System.out.println("Cantidad de Raw Titanium: " + amountTItanium);

    // Recorrer las entradas del HasMap
    System.out.println("\n--- Contenido del Inventario ---");
    for(Map.Entry<String, Integer> inputData : inventory.entrySet()) {
      System.out.println(inputData.getKey() + " -> " + inputData.getValue());
    }

    System.out.println("\n=================================================");

    // Nivel avanzado: EnumMap
    // Requerimos especificar la clase del enum en el constructor
    Map<Raridad, Double> dropsMultipliers = new EnumMap<>(Raridad.class);

    dropsMultipliers.put(Raridad.COMUN, 1.0);
    dropsMultipliers.put(Raridad.RARO, 1.5);
    dropsMultipliers.put(Raridad.EPICO, 2.5);
    dropsMultipliers.put(Raridad.LEGENDARIO, 5.0);

    // Obtencion de un valor
    double multEpic = dropsMultipliers.get(Raridad.EPICO);
    System.out.println("Multiplicador para epico: x" + multEpic);

    // Si iteramos se garantiza el orden segun fueron declarados
    System.out.println("\n--- Tabla de multiplicadores ordenada ---");
    for(Map.Entry<Raridad, Double> input : dropsMultipliers.entrySet()) {
      System.out.println(input.getKey() + ": x" + input.getValue());
    }

  }
}
