package conceptos_basicos.clases_objetos;

public class ClasesObjetos {
  public static void main(String[] args) {
    // Instanciamos dos objetos independientes creados del mismo plano
    Auto autoDeCarlos = new Auto("Toyota", "Corolla");
    Auto autoDeAlex = new Auto("Ford", "Mustang");

    System.out.println("=== Probando Auto 1 ===");
    autoDeCarlos.acelerar(10);
    autoDeCarlos.encender();
    autoDeCarlos.acelerar(40);

    System.out.println("\n=== Probando Auto 2 ===");
    autoDeAlex.encender();
    var velocidad = autoDeAlex.getVelocidadActual();
    System.out.println("Velocidad del " + autoDeAlex.getModelo() + ": " + velocidad + " km/h");
  }
}
