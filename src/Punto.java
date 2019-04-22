import java.util.ArrayList;
import java.util.List;

/**
 * Clase Punto. 
 * 
 * @author https://github.com/xetorthio
 * <b>Repositorio original:<\b> https://github.com/xetorthio/kmeans
 * 
 * Modificado por alu0100961768 (G�rman Alfonso Teixid�) para cumplir las indicaciones para la pr�ctica 4 de la asignatura DAA, Computaci�n, grado de Inform�tica de la ULL.  
 *
 */
public class Punto {
    private Float[] data;																				// Coordenadas de nuestro Punto.

    //Constructor. Lee uno a uno cada flotanten y los guarda en data.
    public Punto(String[] strings) {
	//super();
    	List<Float> puntos = new ArrayList<Float>();
    	for (String string : strings) {
    		puntos.add(Float.parseFloat(string));
    	}
    	this.data = puntos.toArray(new Float[strings.length]);
    }

    // Constructor con un array de flotantes.
    public Punto(Float[] data) {
    	this.data = data;
    }

    // Getters
    public float get(int dimension) {
    	//TODO: Devolver 0 si no existe la dimensi�n.
    	if(dimension<data.length)
    		return data[dimension];
    	else
    		return 0;
    }

    public int getGrado() {	// �No se debe usar este m�todo en la medida de lo posible!
    	//NOTA: Usar la dimension de cada punto teniendo en cuenta la dimensi�n pasada por referencia en el archivo de entrada, en lugar de calcularlo usando este m�todo.
    	return data.length;
    }

    
    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder();
	sb.append(data[0]);
	for (int i = 1; i < data.length; i++) {
	    sb.append(", ");
	    sb.append(data[i]);
	}
	return sb.toString();
    }

    //Calcula la Distancia Euclideana entre este punto y el de destino. Funciona gracias a Internet, magia y stackOverflow.
    public Double distanciaEuclideana(Punto destino) {
	Double d = 0d;
	for (int i = 0; i < data.length; i++) {
	    d += Math.pow(data[i] - destino.get(i), 2);
	}
	return Math.sqrt(d);
    }

    @Override
    public boolean equals(Object obj) {
	Punto other = (Punto) obj;
	for (int i = 0; i < data.length; i++) {
	    if (data[i] != other.get(i)) {
		return false;
	    }
	}
	return true;
    }
}