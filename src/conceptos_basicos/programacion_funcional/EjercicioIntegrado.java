package conceptos_basicos.programacion_funcional;

import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class EjercicioIntegrado {

  // Modelo 1: Orden de compra original
  public record OrdenCompra(String id, String usuario, double monto, boolean esVip) {}

  // Modelo 2: Recibo procesado (DTO)
  public record ReciboProceso(String idOrden, String usuario, double totalConIva, String mensaje) {}

  public static void main(String[] args) {

    // Uso de supplier: Generamos una orden con UUID aleatoria
    Supplier<OrdenCompra> crearOrdenDemo = () -> new OrdenCompra(
        UUID.randomUUID().toString().substring(0,8),
        "CarlosDev",
        100.0,
        true
    );

    // Uso de predicate: validamos que el monto sea > 0 y que no supere el limite de 500$
    Predicate<OrdenCompra> esMontoValido = orden -> orden.monto() > 0;
    Predicate<OrdenCompra> esMontoSeguro = orden -> orden.monto() <= 500.0;
    Predicate<OrdenCompra> ordenValida = esMontoValido.and(esMontoSeguro);

    // Uso de function: transformamos la orden en un recibo (aplicando el 10% de descuento si es VIP y calculando el 21% IVA)
    Function<OrdenCompra, ReciboProceso> generarRecibo = orden -> {
      double montoBase = orden.esVip() ? orden.monto() * 0.90 : orden.monto();
      double totalIva = montoBase * 1.21;
      String nota = orden.esVip() ? "Descuento VIP aplicado (10%)" : "Tarifa regular";

      return new ReciboProceso(orden.id(), orden.usuario(), totalIva, nota);
    };

    // Uso de consumer: accion final (Imprimimos el recibo procesado en consola)
    Consumer<ReciboProceso> enviarNotificacion = recibo -> {
      System.out.println(" --- RECIBO DE COMPRA ---");
      System.out.println("ID orden   : " + recibo.idOrden());
      System.out.println("Usuario    : " + recibo.usuario());
      System.out.println("Total IVA  : $" + String.format("%.2f", recibo.totalConIva()));
      System.out.println("Detalle    : " + recibo.mensaje());
    };

    // --- EJECUCION DEL FLUJO INTEGRADOR ---
    System.out.println("=== PROCESANDO ORDEN ===");

    procesarTransaccion(
        crearOrdenDemo,
        ordenValida,
        generarRecibo,
        enviarNotificacion
    );
  }

  // Metodo orquestador generico. Recibe las 4 piezas para ejecutar
  public static void procesarTransaccion(
      Supplier<OrdenCompra> proveedorOrden,
      Predicate<OrdenCompra> validador,
      Function<OrdenCompra, ReciboProceso> transformador,
      Consumer<ReciboProceso> accionFinal
  ) {
      // Generamos el supplier
    OrdenCompra orden = proveedorOrden.get();
    System.out.println("Orden generada para: " + orden.usuario() + " por $" + orden.monto());

    // Validamos con predicate
    if(!validador.test(orden)) {
      System.out.println("Error: La orden no supero las validaciones de seguridad");
      return;
    }
    System.out.println("Orden validada correctamente.");

    // Transformamos con function
    ReciboProceso recibo = transformador.apply(orden);

    // Ejecutamos el efecto secundario con consumer
    accionFinal.accept(recibo);
  }
}
