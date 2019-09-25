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
  private final String passwordPart1 = "29dh120";
  private final String passwordPart2 = "dk1";
  private final String passwordPart3 = "3";
  private String encriptado;

  public Probabilidad(String encriptado) {
	this.encriptado = encriptado;
  }

  public void setEncriptado(String encriptado) {
	this.encriptado = encriptado;
  }

  /**
   * Metodo para tener un orden random de letras
   */
  public void randomizeLetras() {
	int maximo = letras.length;
	int cambio;
	for (int actual = 0; actual < maximo; actual++) {
	  cambio = (int) (Math.random() * 26);
	  char temporal = letras[cambio];
	  letras[cambio] = letras[actual];
	  letras[actual] = temporal;
	}
  }

  /**
   * Metodo para tener un orden random de numeros
   */
  public void randomizeNumeros() {
	int maximo = numeros.length;
	int cambio;
	for (int actual = 0; actual < maximo; actual++) {
	  cambio = (int) (Math.random() * 10);
	  char temporal = numeros[cambio];
	  numeros[cambio] = numeros[actual];
	  numeros[actual] = temporal;
	}
  }
}
