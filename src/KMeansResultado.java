import java.util.ArrayList;
import java.util.List;

/**
 * Almacena los resultados finales del K-Means.
 * 
 * @author https://github.com/xetorthio
 * <b>Repositorio original:<\b> https://github.com/xetorthio/kmeans
 * 
 * Modificado por alu0100961768 (G�rman Alfonso Teixid�) para cumplir las indicaciones para la pr�ctica 4 de la asignatura DAA, Computaci�n, grado de Inform�tica de la ULL.  
 *
 */
public class KMeansResultado {
	
    private List<Cluster> clusters = new ArrayList<Cluster>();								// Lista definitiva de los Clusters
    private Double ofv;																		// OFV

    public KMeansResultado(List<Cluster> clusters, Double ofv) {
    	//super();
    	this.ofv = ofv;
    	this.clusters = clusters;
    }

    public List<Cluster> getClusters() {
    	return clusters;
    }

    public Double getOfv() {
    	return ofv;
    }
}
