package logica;
import java.util.*; 
public class Conjunto {
	
  private Set <Character> conjuntoLetras = new HashSet<Character>();
  private Set <Integer> conjuntoNumeros = new HashSet<Integer>();
  
  public Conjunto(Set<Character> conjuntoLetras, Set<Integer> conjuntoNumeros) {
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
  
  public Set<Integer> getConjuntoNumeros() {
	return conjuntoNumeros;
  }
  
  public void setConjuntoNumeros(Set<Integer> conjuntoNumeros) {
	this.conjuntoNumeros = conjuntoNumeros;
  } 

}
