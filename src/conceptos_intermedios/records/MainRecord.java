package conceptos_intermedios.records;


public class MainRecord {
  public static void main(String[] args) {

    // Clase tradicional
    ClassPosicion pos1 = new ClassPosicion(10, 20);

    System.out.println("X: " + pos1.getX());
    System.out.println("Y: " + pos1.getY());
    System.out.println(pos1);

    System.out.println("\n===========================================");

    // Usando Record
    RecordPosicion pos2 = new RecordPosicion(30, 40);

    // Acceso a datos (sin el prefijo 'get')
    System.out.println("X: " + pos2.x());
    System.out.println("Y: " + pos2.y());

    // toString generado automaticamente
    System.out.println(pos2);

    // Comparacion por valor con equals()
    RecordPosicion pos3 = new RecordPosicion(30, 40);
    System.out.println(pos2.equals(pos3));

    System.out.println("La distancia es: " + pos2.distanciaAlOrigen());

  }
}
