package conceptos_basicos.manejo_excepciones;

public class RegisterPlayer {
  private String nickname;
  private int nivel;

  // Metodo que valida y lanza excepciones si las reglas se rompen
  public void registrar(String nickname, int nivel) {
    // Verificamos el nickname:
    if(nickname == null || nickname.trim().isEmpty()) {
      throw new IllegalArgumentException("El nickname puede estar vacio o ser nulo.");
    }

    // Validamos el nivel:
    if(nivel < 1) {
      throw new IllegalArgumentException("El nivel inicial debe ser al menos 1. Valor recibido: " + nivel);
    }

    this.nickname = nickname;
    this.nivel = nivel;

    System.out.println("Jugador registrado con exito");

  }
}
