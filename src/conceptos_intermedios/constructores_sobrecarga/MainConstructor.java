package conceptos_intermedios.constructores_sobrecarga;

public class MainConstructor {
  public static void main(String[] args) {
    Jugador user1 = new Jugador();
    int nivelUser = user1.nivel;

    System.out.println("El nombre del jugador: " + user1.getNombre() + " | nivel: " + nivelUser);


    Enemigo jefe = Enemigo.crearJefe("Marco");
    Enemigo minion = Enemigo.crearEsbirro("Goblin");
    Enemigo custom = Enemigo.enemigoCustom("", 0);
    Enemigo custom2 = Enemigo.enemigoCustom("SuperBoss", 5000);

    System.out.println("Nuevo Jefe: " + jefe);
    System.out.println("Nuevo Minion: " + minion);
    System.out.println("Jefe custom: " + custom);
    System.out.println("Jefe custom: " + custom2);

  }
}
