package conceptos_basicos.poo;

public class EntityGame {

  // Private: Nadie fuera de EntityGame puede cambiar o leer la id directa
  private String idUnica;

  // Default: Solo visible para clases dentro del paquete
  int ticksVida;

  // Protected: las subclases pueden usarlo directamente
  protected int vidaActual;

  // Public: Disponible para cualquier parte del proyecto
  public String nombre;


  public EntityGame(String idUnica, int vidaActual, String nombre) {
    this.idUnica = idUnica;
    this.nombre = nombre;
    this.vidaActual = vidaActual;
    this.ticksVida = 0;
  }

  // Metodo publico para consultar la ID privada de forma segura (Getter)
  public String getIdUnica() {
    return this.idUnica;
  }
}
