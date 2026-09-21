package conceptos_intermedios.records;

public record RecordPosicion (int x, int y) {
  // constructor compacto para validaciones
  public RecordPosicion {
    if(x < 0 || y < 0) {
      throw new IllegalArgumentException("Las coordenadas no pueden ser negativas");
    }
  }

  // Metodo personalizado
  public double distanciaAlOrigen() {
    return Math.sqrt(x * x + y * y);
  }
}
