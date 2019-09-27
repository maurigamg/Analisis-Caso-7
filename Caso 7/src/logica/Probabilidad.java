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
  private final String KEYPART1 = "29dh120";
  private final String KEYPART2 = "dk1";
  private final String KEYPART3 = "3";
  private int intentos = 0; //contiene los intentos realizados
  private String encriptado; //Texto a desencriptar
  private String resultadoPrueba; //Mensaje de exito o fallo
  private Conjunto[] conjuntos = new Conjunto[12]; //conjuntos donde es posible hallar respuesta
  private ArrayList<Character> numeros = new ArrayList<Character>(); //array con las letras a utilizar para crear conjuntos
  private ArrayList<Character> letras = new ArrayList<Character>(); //array con los numeros a utilizar para crear conjuntos

  /**
   * Constructor de la clase Probabilidad
   * 
   * @param encriptado Corresponde al mensaje encriptado
   */
  public Probabilidad(String encriptado) {
    this.encriptado = encriptado;
    inicializarLetras();
    inicializarNumeros();
  }
  
  /**
   * Se inicializa el ArrayList letras con las letras de la 'a' a la 'z'
   */
  private void inicializarLetras() {
    for (char letra = 'a';letra<='z';letra++) {
      letras.add(letra);
    }
  }
  
  /**
   * Se inicializa el ArrayList numeros con los numeros del '0' al '9'
   */
  private void inicializarNumeros() {
    for (char numero = '0';numero<='9';numero++) {
      numeros.add(numero);
    }
  }
  
  /**
   * Metodo que crea los 12 conjuntos
   * Se crean 12 conjuntos de 7 letras y 3 numeros, pues 12*7*3= 252 <= 260
   * 
   * @return Un String con los intentos que realizo, el mensaje descifrado y la
   *         llave utilizada
   */

  private void crearConjuntos() {
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
    }
  }

  /**
   * Metodo que realiza las pruebas sobre los conjuntos hasta obtener una resultado correcto
   * 
   */
  public void realizarPrueba() {
    intentos = 0; //Reiniciamos los intentos
    crearConjuntos(); //Se crean nuevos conjuntos
    for (Conjunto conjunto : conjuntos) {
      for (char letra : conjunto.getConjuntoLetras()) {
        for (char numero : conjunto.getConjuntoNumeros()) {
          intentos++;
          String resultado = AES.decrypt(encriptado, KEYPART1 + letra + KEYPART2 + numero + KEYPART3);
          if (resultado != null) {
            this.resultadoPrueba = "Desencriptacion exitosa"; 
            // Mejorar las posibilidades de que se repita para la siguiente prueba
            subirPosibilidades(conjunto.getConjuntoLetras(), letras);
            subirPosibilidades(conjunto.getConjuntoNumeros(), numeros);
            return;
          }
        }
      }
    }
    this.resultadoPrueba = "Desencriptacion fallida";
    //Empeorar las posiilidades de que se repitan las letras y numeros utilizados en los conjuntos
    bajarPosibilidades();
  }
  
  /**
   * Metodo que agrega más posibilidades a ciertas letras o conjuntos
   * 
   * @param setUtil representa el subconjunto de letras o numeros de un conjunto que dio solucion
   * @param arrASumar ArrayList al cual se le van a agregar los elementos de setUtil
   */
  private void subirPosibilidades(Set<Character> setUtil, ArrayList<Character> arrASumar) {
    for (char caracterAMeter : setUtil) {
      arrASumar.add(caracterAMeter);
    }
  }

  /**
   * Metodo que baja las posibilidades de que aparezcan las letras y numeros utilizados en los conjuntos
   */
  private void bajarPosibilidades() {

    Set<Character> letrasInutiles = new HashSet<Character>(); //letras que son menos probables a dar solucion
    Set<Character> numerosInutiles = new HashSet<Character>(); //numeros que son menos probables a dar solucion
    
    //Se agregan las letras utilizadas en los conjuntos a letrasInutiles
    for (Conjunto conjunto : conjuntos) {
      for (char letraAQuitar : conjunto.getConjuntoLetras()) {
        letrasInutiles.add(letraAQuitar);
      }
    }
    
  //Se agregan los numeros utilizados en los conjuntos a numerosInutiles
    for (Conjunto conjunto : conjuntos) {
      for (char numeroAQuitar : conjunto.getConjuntoNumeros()) {
        numerosInutiles.add(numeroAQuitar);
      }
    }

    //Se actualizas los ArrayList letras y numeros
    actualizarLetras(letrasInutiles);
    actualizarNumeros(numerosInutiles);
    
  }
  
  /**
   * Metodo que disminuye en 1 la aparicion de las letrasInutiles en el ArrayList letras
   * 
   * @param letrasInutiles corresponde a las letras que menos posibilidades de que funcionen
   */
  private void actualizarLetras(Set<Character> letrasInutiles) {
    
    ArrayList<Character> actualizacion = new ArrayList<Character>(); //Almacena las nuevos letras a utilizar
    
    //La actualizacion empieza a partir de 26, pues las anteriores corresponden de la 'a' a la 'z'
    //hay posibles problemas al empezarlo en 0 pues algunas letras desaparecerian por completo
    //Se pasan las letras desde la posicion 26 de letras a actualizacion
    for (int indexLetra = 26; indexLetra < letras.size(); indexLetra++) {
      actualizacion.add(letras.get(indexLetra));
    }
    
    //Se quitan de letras las letras que se pasaron a actualizacion
    for (int indexLetra = 26; indexLetra < letras.size(); indexLetra++) {
      letras.remove(indexLetra);
    }
    
    //Se quitan de actualizacion las letras inutiles
    for (char letraAQuitar : letrasInutiles) {
      if (actualizacion.contains(letraAQuitar)) {
        actualizacion.remove(letraAQuitar);
      }
    }

    letras.addAll(actualizacion); //Se agregan a letras las letras actualizadas
  }
  
  /**
   * Metodo que disminuye en 1 la aparicion de los numeros en el ArrayList numeros
   * 
   * @param numerosInutiles corresponde a los numeros con menos posibilidades de que funcionen
   */
  private void actualizarNumeros(Set<Character> numerosInutiles) {
    
    ArrayList<Character> actualizacion = new ArrayList<Character>(); //Almacena los nuevos numeros a utilizar
    
    //Se pasan los numeros desde la posicion 10 de numeros a actualizacion
    //Es necesario empezar a partir del index 10 para no generar problemas
    for (int indexNumero = 10; indexNumero < numeros.size(); indexNumero++) {
      actualizacion.add(numeros.get(indexNumero));
    }
    
    //Se quitan de numeros los numeros que se pasaron a actualizacion
    for (int indexNumero = 10; indexNumero < numeros.size(); indexNumero++) {
      numeros.remove(indexNumero);
    }
    
    //Se quitan de actualizacion los numeros inutiles
    for (char numeroAQuitar : numerosInutiles) {
      if (actualizacion.contains(numeroAQuitar)) {
        actualizacion.remove(numeroAQuitar);
      }
    }

    numeros.addAll(actualizacion); //Se agregan a numeros los numeros actualizados
  }
  
  /**
   * 
   * @return Un mensaje con los datos obtenidos al realizar una prueba
   */
  public String verResultados() {
    String resultado = "Conjuntos utilizados:\n";
    int numeroConjunto = 1;
    for(Conjunto conjunto: conjuntos) {
      resultado += "Conjunto "+numeroConjunto+": "+conjunto.getConjuntoLetras().toString()+conjunto.getConjuntoNumeros().toString()+"\n";
      numeroConjunto++;
    }
    resultado += "Resultado: "+resultadoPrueba+"\n";
    resultado += "Intentos realizados: "+intentos+"\n";
    return resultado;
  }
}
