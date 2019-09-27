package logica;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Mauricio Gamboa
 * @author Jose Macias
 * @version 24/09/2019
 */
public class Probabilidad {
  private final char letrasTEMP[] = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
      's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
  private final char numerosTEMP[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
  private ArrayList<Character> numeros = new ArrayList<Character>();
  private ArrayList<Character> letras = new ArrayList<Character>();
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
    for(char numero : numerosTEMP) {
    	numeros.add(numero);
    }
    
    for(char letra: letrasTEMP) {
    	letras.add(letra);
    }
    
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
        tempCharacters.add(letras.get((int) (Math.random() * letras.size())));
      }
      while (tempNumeros.size() < 3) {
        tempNumeros.add(numeros.get((int) (Math.random() * numeros.size())));
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
  public void realizarPrueba() {
	boolean encontroRespuesta = false;
    for(Conjunto conjunto: conjuntos) {
      for(char letra: conjunto.getConjuntoLetras()) {
        for(char numero: conjunto.getConjuntoNumeros()){
          intentos++;
          if(AES.decrypt(encriptado, keyPart1+letra+keyPart2+numero+keyPart3)!=null) {
            System.out.println(intentos);
            System.out.println(AES.decrypt(encriptado, keyPart1+letra+keyPart2+numero+keyPart3));
            //Mejorar las posibilidades de que se repita
            subirPosibilidades(conjunto.getConjuntoLetras(),letras);
            subirPosibilidades(conjunto.getConjuntoNumeros(),numeros);
            encontroRespuesta = true;
            intentos = 0;
            return;
          }
        }
      }
    }
    //Empeorar las posiilidades de que se repita
    if(!encontroRespuesta) {
    	bajarPosibilidades();
    }
  }
  
  public void subirPosibilidades(Set <Character> setUtil, ArrayList<Character> arrASumar) {
	  for(char caracterAMeter : setUtil) {
		  arrASumar.add(caracterAMeter);
	  }
  }
  
  public void bajarPosibilidades() {

	  Set<Character> charInutilesTEMPLetras = new HashSet<Character>();
	  Set<Character> charInutilesTEMPNumeros = new HashSet<Character>();

		  for(Conjunto conjunto : conjuntos) {
			  for(char charAQuitar : conjunto.getConjuntoLetras()) {
				  charInutilesTEMPLetras.add(charAQuitar);
			  }
		  }

		  for(Conjunto conjunto : conjuntos) {
			  for(char charAQuitar : conjunto.getConjuntoNumeros()) {
				  charInutilesTEMPNumeros.add(charAQuitar);
			  }
		  }

	  //Arreglar Letras
	  ArrayList<Character> charLetras = new ArrayList<Character>();
	  for(int charNecesario = 26; charNecesario < letras.size(); charNecesario++) {  
		  charLetras.add(letras.get(charNecesario));
	  }
	  
	  for(int charNecesario = 26; charNecesario < letras.size(); charNecesario++) {
		  letras.remove(charNecesario);
	  }
	  
	  for(char charAQuitar : charInutilesTEMPLetras) {
		  if(charLetras.contains(charAQuitar)) {
			  charLetras.remove(charAQuitar);
		  }
	  }
	  
	  letras.addAll(charLetras);
	  
	//Arreglar Numeros
	  ArrayList<Character> charNumeros = new ArrayList<Character>();
	  for(int charNecesario = 10; charNecesario < numeros.size(); charNecesario++) {  
		  charNumeros.add(numeros.get(charNecesario));
	  }
	  
	  for(int charNecesario = 10; charNecesario < numeros.size(); charNecesario++) {
		  numeros.remove(charNecesario);
	  }
	  
	  for(char charAQuitar : charInutilesTEMPNumeros) {
		  if(charNumeros.contains(charAQuitar)) {
			  charNumeros.remove(charAQuitar);
		  }
	  }
	  
	  numeros.addAll(charNumeros);
	  
	  

  }
}
