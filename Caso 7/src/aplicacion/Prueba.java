package aplicacion;

import logica.Probabilidad;

/**
 * 
 * @author Mauricio Gamboa
 * @author Jose Macias
 * @version 21/09/2019
 */
public class Prueba {
  public static void main(String[] args) {
    
	Probabilidad probabilidad;
	probabilidad = new Probabilidad("xZwM7BWIpSjYyGFr9rhpEa+cYVtACW7yQKmy"
	    + "N6OYSCv0ZEg9jWbc6lKzzCxRSSIvOvlimQZBMZOYnOwiA9yy3YU8zk4abFSItoW6Wj0ufQ0=");
	
	for(int numPrueba=1;numPrueba<=10;numPrueba++) {
	  probabilidad.realizarPrueba();
	}
	System.out.println(probabilidad.verResultados());
  }
}
