package conceptos_basicos.programacion_funcional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class FunctionProfunda {

  // 1. Modelo de datos interno
  public record JugadorEntity(String id, String nickname, int experiencia, String claveHash) {}

  // 2. DTO seguro para enviar al cliente
  public record JugadorDTO(String nickname, int nivel){}

  // Funcion usando .andThe
  public static <T, R> void fnPipelineAndThen(T value, Function<T, R> fnPrueba, String message) {
    R valueResponse = Optional.ofNullable(value)
        .filter(v -> !(v instanceof  String str) || !str.isBlank())
        .map(fnPrueba)
        .orElse(null);

    if(valueResponse != null) {
      System.out.println(message + valueResponse);
    } else {
      System.out.println(message + "[Valor nulo, vacio o invalido]");
    }
  }

  public static void main(String[] args) {
    System.out.println("=== 1. Transformación de Tipos (.apply) ===");

    // Recibimos un Integer y devolvemos su longitud en String
    Function<Integer, String> formatearMonedas = monedas -> "$" + monedas + "USD";

    int valorMoney = 1500;
    String nomedaFormateda = formatearMonedas.apply(valorMoney);
    System.out.println("El dinero: " + valorMoney + " | formateado: " + nomedaFormateda);

    // Recibimos un String y devolvemos un Integer
    Function<String, Integer> contarLetras = texto -> texto.length();

    String nombreUser = "CarlosDev";
    int letras = contarLetras.apply(nombreUser);
    System.out.println("Longitud del nombre: " + nombreUser + " es: " + letras);


    System.out.println("\n === 2. Encadenamiento (.andThen) ===");

    // Ejemplo 1:
    int numberValue = 5;
    Function<Integer, Integer> duplicar = number -> number * 2;
    Function<Integer, Integer> sumarDiez = number -> number + 10;

    // Primero duplicamos y luego sumamos
    Function<Integer, Integer> duplicarYSumar = duplicar.andThen(sumarDiez);
    System.out.println("Resultado de ( " + numberValue + " * 2 + 10 ): " + duplicarYSumar.apply(numberValue));

    // Usando .compose(): primero sumamos y luego duplicamos
    Function<Integer, Integer> sumarYDuplicar = duplicar.compose(sumarDiez);
    System.out.println("Resultado de ( " + numberValue + " + 10 * 2 ): " + sumarYDuplicar.apply(numberValue));

    // Ejemplo 2:
    String valueString = "   Hola java  ";
    String valueNull = null;
    String valueEmpty = "";
    Function<String, String> limpiarTexto = text -> text.trim();
    Function<String, String> convertMayus = text -> text.toUpperCase();
    Function<String, Integer> countCaracteres = text -> text.length();

    // Realizar prueba: Primero limpia espacios, luego convierte en mayusculas y luego cuenta los caracteres
    Function<String, Integer> pipeLineAndThen = limpiarTexto
        .andThen(convertMayus)
        .andThen(countCaracteres);

    fnPipelineAndThen(valueString, limpiarTexto, "Resultado sin espacios: ");
    fnPipelineAndThen(valueString, convertMayus, "Resultado con espacios y en MAYUSCULA: ");
    fnPipelineAndThen(valueString, pipeLineAndThen, "La logitud es: ");
    fnPipelineAndThen(valueNull, pipeLineAndThen, "El valor es: ");
    fnPipelineAndThen(valueEmpty, pipeLineAndThen, "El valor es: ");


    System.out.println("\n === 3. Mapeo de entidad a DTO ===");

    List<JugadorEntity> baseDatos = List.of(
        new JugadorEntity("UUID-1", "CarlosDev", 4500, "hash_secreto_123"),
        new JugadorEntity("UUID-2", "AlexPro", 9200, "hash_secreto_456"),
        new JugadorEntity("UUID-3", "NoobMaster", 1700, "hash_secreto_789")
    );

    // Mapear function: convertimos JugadorEntity a JugadorDTO
    Function<JugadorEntity, JugadorDTO> mapeadorDTO = entidad -> {
      int nivelCalculado = entidad.experiencia() / 100;
      return new JugadorDTO(entidad.nickname(), nivelCalculado);
    };

    List<JugadorDTO> listaPublica = transformList(baseDatos, mapeadorDTO);

    for (JugadorDTO dto : listaPublica) {
      System.out.println("-> DTO publico: " + dto.nickname() + " | Nivel: " + dto.nivel());
    }

  }

  // Metodo helper que aplica una funcion a cada elemento de una lista
  public static <T, R> List<R> transformList(List<T> listaOrigen, Function<T, R> transformador) {
    List<R> listaResultado = new ArrayList<>();
    for (T elemento : listaOrigen) {
      // Aplicamos la transformacion a cada elemento
      R elementoTransformado = transformador.apply(elemento);
      listaResultado.add(elementoTransformado);
    }
    return listaResultado;
  }
}