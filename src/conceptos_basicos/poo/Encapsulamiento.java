package conceptos_basicos.poo;

public class Encapsulamiento {
  public static void main(String[] args) {
    AccountPlayer player = new AccountPlayer("Alan", 4, 100.0);

    String namePlayer = player.getNickname();
    double saldoPlayer = player.getSaldoMonedas();
    int levelPlayer = player.getNivel();

    System.out.println("Lectura de los atributos (Getters)");
    System.out.println("Jugador: " + namePlayer);
    System.out.println("Saldo actual: " + saldoPlayer);
    System.out.println("nivel: " + levelPlayer);


    System.out.println("\n Intentando asignar datos invalidos (Setters)");
    player.setSaldoMonedas(-500);
    player.setNivel(-10);

    System.out.println("\n Deposito de monedas con logica de negocio");
    player.depositarMonedas(250.0);

    System.out.println("\n Datos actualizados");
    System.out.println("Jugador: " + namePlayer);
    System.out.println("Saldo actual: " + player.getSaldoMonedas());
    System.out.println("nivel: " + player.getNivel());
  }
}
