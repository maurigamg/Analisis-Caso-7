package logica;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author Jose Macias
 * @author Mauricio Gamboa
 * @version 26/09/2019
 *
 */
public class Conjunto {
	
  private Set <Character> conjuntoLetras = new HashSet<Character>();
  private Set <Character> conjuntoNumeros = new HashSet<Character>();
  
  public Conjunto(Set<Character> conjuntoLetras, Set<Character> conjuntoNumeros) {
	super();
	this.conjuntoLetras = conjuntoLetras;
	this.conjuntoNumeros = conjuntoNumeros;
  }
  
  public Set<Character> getConjuntoLetras() {
	return conjuntoLetras;
  }
  
  public void setConjuntoLetras(Set<Character> conjuntoLetras) {
	this.conjuntoLetras = conjuntoLetras;
  }
  
  public Set<Character> getConjuntoNumeros() {
	return conjuntoNumeros;
  }
  
  public void setConjuntoNumeros(Set<Character> conjuntoNumeros) {
	this.conjuntoNumeros = conjuntoNumeros;
  } 

}
