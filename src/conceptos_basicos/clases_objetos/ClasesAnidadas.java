package conceptos_basicos.clases_objetos;

public class ClasesAnidadas {
  // Atributos privados de la Outer class
  private String ipServidor = "192.168.1.100";
  private int puerto = 25565;
  private int maxPlayers = 20;

  // INNER class (Puede ser static o no-static)
  public static class JugadorConectado {
    private String nickname;
    private String ping;

    // Constructor
    public JugadorConectado(String nickname, String ping) {
      this.nickname = nickname;
      this.ping = ping;
    }

    // Metodo
    public void viewInfoConnection(String ip, int puerto, int maxPlayers) {
      // Tiene acceso directo a los atributos privados (No los usaermos por el momennto)
      System.out.println("Jugador: " + this.nickname + " | Ping: " + ping);
      System.out.println("Connectado a: " + ip+ ":" + puerto);
      System.out.println("Limite del servidor: " + maxPlayers + " usuarios");
    }
  }

  public void connectedNewPlayer(String name){
    // La outer class crea la instancia de la inner class
    JugadorConectado player = new JugadorConectado(name, "45ms");
    player.viewInfoConnection(ipServidor, puerto, maxPlayers);
  }

  // Ejecutamos las clases:
  public static void main(String[] args) {
    // Creamos la instancia de la Outer class
    ClasesAnidadas server = new ClasesAnidadas();

    // Forma A: usamos el metodo del servidor
    server.connectedNewPlayer("Carlos");
    System.out.println("-----------------------------------");

    // Forma B: Instanciamos la inner class desde afuera
    ClasesAnidadas.JugadorConectado player = new ClasesAnidadas.JugadorConectado("player234", "12ms");
    player.viewInfoConnection(server.ipServidor, server.puerto, server.maxPlayers);
  }
}
