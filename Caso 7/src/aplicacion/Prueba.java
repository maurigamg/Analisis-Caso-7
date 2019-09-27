package aplicacion;

import java.util.Scanner;

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
	
	probabilidad.crearConjuntos();
	probabilidad.realizarPrueba();
	
	Scanner scanner = new Scanner(System.in);
    String readString = scanner.nextLine();
    while(readString!=null) {
        System.out.println(readString);
        probabilidad.realizarPrueba();
        if (readString.isEmpty()) {
            System.out.println("Gracias por usar el programa");
            break;
        }

        if (scanner.hasNextLine()) {
            readString = scanner.nextLine();
        } else {
            readString = null;
        }
    }
  }
}
