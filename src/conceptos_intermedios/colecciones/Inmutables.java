package conceptos_intermedios.colecciones;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Inmutables {

  // Ejemplo de List.of:
  // Endpoints publicos de una API (Inmutables y de solo lectura)
  public static final List<String> PUBLIC_ENDPOINTS = List.of(
      "/api/v1/auth/login",
      "/api/v1/auth/register",
      "/api/v1/health",
      "/swagger-ui/**"
  );

  public boolean isPublicRoute(String requestPath) {
    return PUBLIC_ENDPOINTS.contains(requestPath);
  }

  // Ejemplo de Set.of:
  // Roles con privilegios de admin
  private static final Set<String> ADMIN_ROLES = Set.of(
      "ROLE_SUPER_ADMIN",
      "ROLE_SYSTEM_OPERATOR",
      "ROLE_AUDITOR"
  );

  public boolean haveElevatedAccess(Set<String> rolesUser) {
    return rolesUser.stream().anyMatch(ADMIN_ROLES::contains);
  }

  // Ejemplo de Map.of:
  // Mapeo de extensiones permitidas (Podemos tener 10 pares clave-valor)
  private static final Map<String, String>  MIME_TYPES_ALLOWED = Map.of(
      "png",  "image/png",
      "jpg",  "image/jpeg",
      "jpeg", "image/jpeg",
      "pdf",  "application/pdf",
      "json", "application/json"
  );

  public boolean isValidExtension(String extension, String mimeType) {
    String mimeWaiting = MIME_TYPES_ALLOWED.get(extension.toLowerCase());
    return mimeWaiting != null && mimeWaiting.equals(mimeType);
  }

  record CaseTest(String ext, String mime, boolean waiting) {}

  public static void main(String[] args) {
    // Lista inmutalbe (no podemos agregar mas elementos a la lista ya definida)
    List<String> typesEnemies = List.of("Zombie", "Esqueleto", "Orco", "Bruja");

    // Set inmutable (no permite duplicados)
    Set<String> validAttributes = Set.of("Fuerza", "Agilidad", "Inteligencia");

    // Map inmutable (clave, valor)
    Map<String, Integer> baseDamage = Map.of(
        "Espada", 15,
        "Arco", 10,
        "Hacha", 20
    );

    System.out.println("\n=============================================================");

    Inmutables exampleList = new Inmutables();

    boolean isPublic = exampleList.isPublicRoute("/api/v1/auth/login");
    System.out.println("Es '/api/v1/auth/login' publica?: " + isPublic);
    System.out.println("Es 'login' publica?: " + exampleList.isPublicRoute("login"));

    // Ejemplo usanod Record con List.of y Map.of
    Inmutables exampleVariant = new Inmutables();

    List<CaseTest> listTest = List.of(
        new CaseTest("jpg", "image/jpeg", true),
        new CaseTest("PDF", "application/pdf", true),
        new CaseTest("png", "application/json", false),
        new CaseTest("docx", "application/docx", false)
    );

    listTest.forEach(test -> {
      boolean result = exampleVariant.isValidExtension(test.ext(), test.mime());
      String state = (result == test.waiting()) ? "PASO" : "FALLO";
      System.out.println("[" + state + "] archivo: " + test.ext() + " | resultado: " + result);
    });


    System.out.println("\n=============================================================");

    Inmutables examplesSet = new Inmutables();

    // Caso 1:
    Set<String> rolesUser1 = Set.of("ROLE_USER", "ROLE_AUDITOR");
    boolean haveAccess1 = examplesSet.haveElevatedAccess(rolesUser1);
    System.out.println("Usuario 1 tiene acceso elevado?: " + haveAccess1);

    // Caso 2:
    Set<String> rolesUser2 = Set.of("ROLE_USER", "ROLE_GUEST");
    boolean haveAccess2 = examplesSet.haveElevatedAccess(rolesUser2);
    System.out.println("Usuario 2 tiene acceso elevado?: " + haveAccess2);

    // Caso 3:
    Set<String> rolesUser3 = Set.of();
    System.out.println("Usuario 3 tiene acceso elevado?: " + examplesSet.haveElevatedAccess(rolesUser3));

    System.out.println("\n=============================================================");

    Inmutables exampleMap = new Inmutables();

    Map<String, String> casosTest = Map.ofEntries(
        Map.entry("jpg", "image/jpeg"),
        Map.entry("PDF", "application/pdf"),
        Map.entry("png", "application/json"),
        Map.entry("docx", "application/docx")
    );

    casosTest.forEach((extension, mimeType) -> {
      boolean isValid = exampleMap.isValidExtension(extension, mimeType);
      System.out.println("Es '" + extension + "' con '" + mimeType + "' valido? -> " + isValid);
    });

    System.out.println("\n=============================================================");




  }
}
