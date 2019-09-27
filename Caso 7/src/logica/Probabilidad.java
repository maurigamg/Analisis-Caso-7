package logica;

/**
 *
 * @author Mauricio Gamboa
 * @author Jose Macias
 * @version 24/09/2019
 */
public class Probabilidad {
  private char letras[] = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
      's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
  private char numeros[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
  private final String keyPart1 = "29dh120";
  private final String keyPart2 = "dk1";
  private final String keyPart3 = "3";
  private String encriptado;
  private int intentos = 0;

  /**
   * Constructor de la clase Probabilidad
   * 
   * @param encriptado Corresponde al mensaje encriptado
   */
  public Probabilidad(String encriptado) {
	this.encriptado = encriptado;
  }
  /**
   * Metodo que realiza las pruebas hasta obtener una combinacion correcta para descifrar
   * 
   * @return Un String con los intentos que realizo, el mensaje descifrado y la llave utilizada
   */
  public String realizarPrueba() {
	
	return null;
  }
}
