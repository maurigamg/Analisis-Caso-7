package logica;

import java.util.HashSet;
import java.util.Set;

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
  private Conjunto[] conjuntos = new Conjunto[12];

  /**
   * Constructor de la clase Probabilidad
   * 
   * @param encriptado Corresponde al mensaje encriptado
   */
  public Probabilidad(String encriptado) {
    this.encriptado = encriptado;
  }

  /**
   * Metodo que crea los 12 conjuntos
   * 
   * @return Un String con los intentos que realizo, el mensaje descifrado y la
   *         llave utilizada
   */

  public void crearConjuntos() {
    for (int conjuntoNuevo = 0; conjuntoNuevo < 12; conjuntoNuevo++) {

      Set<Character> tempCharacters = new HashSet<Character>();
      Set<Character> tempNumeros = new HashSet<Character>();

      while (tempCharacters.size() < 7) {
        tempCharacters.add(letras[(int) (Math.random() * 26)]);
      }
      while (tempNumeros.size() < 3) {
        tempNumeros.add(numeros[(int) (Math.random() * 10)]);
      }

      conjuntos[conjuntoNuevo] = new Conjunto(tempCharacters, tempNumeros);

      System.out.println("Conjunto Nuevo ------");
      System.out.println(tempCharacters.toString());
      System.out.println(tempNumeros.toString());
    }
  }
  
  /**
   * Metodo que realiza las pruebas hasta obtener una combinacion correcta para
   * descifrar
   * 
   * @return Un String con los intentos que realizo, el mensaje descifrado y la
   *         llave utilizada
   */
  public String realizarPrueba() {
    for(Conjunto conjunto: conjuntos) {
      for(char letra: conjunto.getConjuntoLetras()) {
        for(char numero: conjunto.getConjuntoNumeros()){
          intentos++;
          if(AES.decrypt(encriptado, keyPart1+letra+keyPart2+numero+keyPart3)!=null) {
            System.out.println(intentos);
            return AES.decrypt(encriptado, keyPart1+letra+keyPart2+numero+keyPart3);
          }
        }
      }
    }
    return null;
  }
}
