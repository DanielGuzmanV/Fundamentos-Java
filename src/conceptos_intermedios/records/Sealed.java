package conceptos_intermedios.records;

import java.util.Optional;

public class Sealed {

  // Con 'permits' limitamos las unicas clases/records autorizadas a implementar esta interfaz
  public sealed interface Recompensa permits ItemRecompensa, OroRecompensa, SinRecompensa{}

  // 1. Recompensa de tipo Item
  public record ItemRecompensa (String nombreItem, int cantidad) implements Recompensa {}

  // 2. Recompensa de tipo Monedas
  public record OroRecompensa (int cantidadMonedas) implements Recompensa{}

  // 3. Recompensa Vacia
  public record SinRecompensa() implements Recompensa{}

  // ====================================================================================

  public sealed interface ResultadoAtaque permits Impacto, Fallo, Bloqueo{}

  // 1. Ataaqu certero
  public record Impacto(int danoInfligido, boolean esCritico) implements ResultadoAtaque{
    public Impacto{
      if(danoInfligido <= 0) {
        throw new IllegalArgumentException("El dano no puede ser negativo o 0");
      }
    }
  }

  // 2. Ataque fallido
  public record Fallo(String razon) implements ResultadoAtaque{
    public Fallo {
      razon = Optional.ofNullable(razon)
          .filter(n -> !n.isBlank())
          .orElse("La razon del fallo no puede estar vacia");
    }
  }

  // 3. Ataque bloqueado
  public record Bloqueo(int danoOriginal, int danoMitigado) implements ResultadoAtaque{
    public Bloqueo {
      if(danoOriginal <= 0 || danoMitigado <= 0) {
        throw new IllegalArgumentException("Los tipos de dano no pueden ser negativos");
      }
    }
  }
}
