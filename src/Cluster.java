import java.util.ArrayList;
import java.util.List;

/**
 * Cluster de puntos de nuestro conjunto.
 * 
 * @author https://github.com/xetorthio
 * <b>Repositorio original:<\b> https://github.com/xetorthio/kmeans
 * 
 * Modificado por alu0100961768 (Gérman Alfonso Teixidó) para cumplir las indicaciones para la práctica 4 de la asignatura DAA, Computación, grado de Informática de la ULL.  
 *
 */
public class Cluster {
	
    private List<Punto> puntos = new ArrayList<Punto>();	// Conjunto de puntos del Cluster
    private Punto centroide;								// Centroide del cluster
    private boolean termino = false;						// Si el Cluster se repite, se vuelve verdadero.

    // Getters
    public Punto getCentroide() {
    	return centroide;
    }

    public List<Punto> getPuntos() {
    	return puntos;
    }
    
    // Setters
    public void setCentroide(Punto centroide) {
    	this.centroide = centroide;
    }

    // Comprueba si el Cluster es definitivo.
    public boolean isTermino() {
    	return termino;
    }

    // Bloquea el Cluster.
    public void setTermino(boolean termino) {
    	this.termino = termino;
    }

    // Limpia la Lista de puntos
    public void limpiarPuntos() {
    	puntos.clear();
    }

    @Override
    public String toString() {
    	return centroide.toString();
    }
}
