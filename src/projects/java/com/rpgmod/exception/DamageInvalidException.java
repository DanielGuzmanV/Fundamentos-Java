package projects.java.com.rpgmod.exception;

public class DamageInvalidException extends RuntimeException{
  public DamageInvalidException(String mensaje){
    super(mensaje);
  }
}