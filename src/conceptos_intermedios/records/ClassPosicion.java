package conceptos_intermedios.records;

public class ClassPosicion {
  private final int x;
  private final int y;

  public ClassPosicion(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX(){ return x; }
  public int getY(){ return y; }

  @Override
  public String toString() {
    return "Posicion { " + "x=" + x + ", y=" + y + '}';
  }
}
