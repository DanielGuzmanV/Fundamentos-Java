package conceptos_basicos.programacion_funcional;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class PredicateFunction {

  // Record para el modelo de datos
  public record JugadorMC(String nombre, int nivel, boolean esBaneado, String rango) {}

  public static void evaluarEdad (Integer value, Predicate<Integer> validator) {
    if(validator.test(value)) {
      System.out.println("Usuario mayor de edad");
    } else {
      System.out.println("No permitido, es menor de edad");
    }
  }

  // Metodo generico helper para filtrar cualquier lista usando un Predicate
  public static List<JugadorMC> filtrarJugadores(List<JugadorMC> lista, Predicate<JugadorMC> filtro) {
    List<JugadorMC> resultado = new ArrayList<>();
    for(JugadorMC jugador : lista) {
      if(filtro.test(jugador)) {
         resultado.add(jugador);
      }
    }
    return resultado;
  }

  public static void main(String[] args) {
    System.out.println("=== 1. Evaluar condicion -> boolean ===");

    Predicate<String> esAdmin = rol -> rol.equalsIgnoreCase("ADMIN");
    System.out.println("Es ADMIN?: " + esAdmin.test("ADMIN"));
    System.out.println("Es USER??: " + esAdmin.test("USER"));


    System.out.println("\n === 2. Evalucion simples y negacion ===");
    // Variables de prueba;
    int numberValueOne = 20;
    int numberValueTwo = 15;
    String nameValueOne = "CarlosDev";
    String nameValueTwo = "";

    // Predicate para verificar edad
    Predicate<Integer> esMayorDeEdad = edad -> edad >= 18;

    // Predicate para verificar si el nombre es valido
    Predicate<String> esNombreValido = nombre -> nombre != null && !nombre.isBlank();
    // Invertimos con .negate()
    Predicate<String> esNombreInvalido = esNombreValido.negate();

    evaluarEdad(numberValueOne, esMayorDeEdad);
    evaluarEdad(numberValueTwo, esMayorDeEdad);

    System.out.println("'" + nameValueOne + "'" + " es nombre valido?: " + esNombreValido.test(nameValueOne));
    System.out.println("'" + nameValueTwo + "'" + " es invalido?: " + esNombreInvalido.test(nameValueTwo));


    System.out.println("\n === 3. Combinando condiciones (.and, .or) ===");

    // Variables de pruebas
    int numberOne = 60;
    int numberTwo = 55;

    // Primer ejemplo:
    Predicate<String> tienePermisoFly = perm -> perm.contains("fly");
    Predicate<String> esVIP = perm -> perm.contains("vip");

    // AND: debe ser VIP y tener permiso de vuelo
    Predicate<String> esVipFly = tienePermisoFly.and(esVIP);

    // OR: puede ser VIP o tener permiso de vuelo
    Predicate<String> accesoPermitio = tienePermisoFly.or(esVIP);

    // Segundo ejemplo:
    Predicate<Integer> esNivelAlto = nivel -> nivel >= 50;
    Predicate<Integer> esNivelPar = nivel -> nivel % 2 == 0;

    Predicate<Integer> nivelAltoYPar = esNivelAlto.and(esNivelPar);
    Predicate<Integer> levelAlto = esNivelAlto.or(esNivelPar);

    System.out.println(numberOne + " es nivel alto y par?: " + nivelAltoYPar.test(numberOne));
    System.out.println(numberTwo + " es nivel alto y par?: " + nivelAltoYPar.test(numberTwo));
    System.out.println(numberTwo + " es nivel alto o par?: " + levelAlto.test(numberTwo));


    System.out.println("\n === 3. Filtrando objetos con Predicados Compuestos ===");

    List<JugadorMC> servidor = List.of(
        new JugadorMC("CarlosDev", 85, false, "VIP"),
        new JugadorMC("AlexPro", 40, false, "VIP"),
        new JugadorMC("Pablito99", 99, true, "USER"),
        new JugadorMC("NoobMaster", 12, false, "USER")
    );

    // Reglas de negocio independientes para nuestro server
    Predicate<JugadorMC> noEstaBaneado = jugador -> !jugador.esBaneado();
    Predicate<JugadorMC> esVip = jugador -> jugador.rango().equals("VIP");
    Predicate<JugadorMC> nivelSuficiente = jugador -> jugador.nivel() >= 50;

    // Regla compuesta: Puede entrar al evento si no esta baneado y (es VIP o tiene Nivel >= 50)
    Predicate<JugadorMC> puedeEntrarAlEvento = noEstaBaneado.and(esVip.or(nivelSuficiente));

    System.out.println("--- Jugadores autorizados para el Evento Especial ---");
    List<JugadorMC> autorizados = filtrarJugadores(servidor, puedeEntrarAlEvento);

    for(JugadorMC user : autorizados) {
      System.out.println(" -> Permitidos: " + user.nombre() + "[Nivel: " + user.nivel() + " | Rango: " + user.rango() + "]");
    }

  }
}
