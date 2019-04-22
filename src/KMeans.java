import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Clase encargada de operar el algoritmo.
 * 
 * @author https://github.com/xetorthio
 * <b>Repositorio original:<\b> https://github.com/xetorthio/kmeans
 * 
 * Modificado por alu0100961768 (Gérman Alfonso Teixidó) para cumplir las indicaciones para la práctica 4 de la asignatura DAA, Computación, grado de Informática de la ULL.  
 *
 */
public class KMeans {
	
	public int grado= 0;
	
	public KMeans(int dimension) {
		this.grado= dimension;
	}
	
    public KMeansResultado calcular(List<Punto> puntos, Integer k) {
    	List<Cluster> clusters = elegirCentroides(puntos, k);							// Se seleccionan los clusters inciales.

    	while (!finalizo(clusters)) {													// Mientras no sea inmutable.
    		prepararClusters(clusters);												    // Limpiamos la lista de punto de los clusters.
    		asignarPuntos(puntos, clusters);											// Asignamos a cada cluster los puntos que poseen.						
    		recalcularCentroides(clusters);												// Se recalculan los centroides de cada punto.
    	}

    	Double ofv = calcularFuncionObjetivo(clusters);

    	return new KMeansResultado(clusters, ofv);
    }

    private void recalcularCentroides(List<Cluster> clusters) {
    	for (Cluster c : clusters) {
    		if (c.getPuntos().isEmpty()) {												// Si el Cluster no tiene puntos, se lockea.
    			c.setTermino(true);
    			continue;
    		}

    		Float[] d = new Float[c.getPuntos().get(0).getGrado()];						//TODO: Este getGrado puede sustituirse por el grado que le pasemos por fichero, da menos problemas de esa forma.
    		Arrays.fill(d, 0f);
    		for (Punto p : c.getPuntos()) {
    			for (int i = 0; i < this.grado; i++) {
    				d[i] += (p.get(i) / c.getPuntos().size());							// Recalculamos el centrroide del Cluster
    			}
    		}

    		Punto nuevoCentroide = new Punto(d);										// Creamos el punto que es el nuevo centroide del cluster

    		if (nuevoCentroide.equals(c.getCentroide())) {								// Si el centroide no ha cambiado desde la última vez
    			c.setTermino(true);														// Lockeamos el cluster.
    		} else {																	// En otro caso, tan solo updateamos el centroide temporal actual.
    			c.setCentroide(nuevoCentroide);
    		}
    	}
    }

    /**
     * Asigna cada punto a su cluster más cercano.
     * @param puntos
     * @param clusters
     */
    private void asignarPuntos(List<Punto> puntos, List<Cluster> clusters) {
    	for (Punto punto : puntos) {													// Para cada punto
    		Cluster masCercano = clusters.get(0);										
    		Double distanciaMinima = Double.MAX_VALUE;
    		for (Cluster cluster : clusters) {											// Comprueba que cluster está mas cerca.
    			Double distancia = punto.distanciaEuclideana(cluster.getCentroide());	
    			if (distanciaMinima > distancia) {										
    				distanciaMinima = distancia;
    				masCercano = cluster;
    			}
    		}
    		masCercano.getPuntos().add(punto);											// Se guarda el punto en el cluster más cercano.
    	}
    }

    /**
     * Limpia la lista de puntos de cada cluster.
     * @param clusters
     */
    private void prepararClusters(List<Cluster> clusters) {
    	for (Cluster c : clusters) {
    		c.limpiarPuntos();
    	}
    }

    /**
     * Calcula el ofv.
     * @param clusters
     * @return
     */
    private Double calcularFuncionObjetivo(List<Cluster> clusters) {
	Double ofv = 0d;		//ObjetiveFuncionValue.

	for (Cluster cluster : clusters) {
	    for (Punto punto : cluster.getPuntos()) {
		ofv += punto.distanciaEuclideana(cluster.getCentroide());
	    }
	}

	return ofv;
    }

    /**
     * Si todos los cluster permanecen inmutables, KMeans ha acabado y devuelve true. En otro caso, devuelve false.
     * @param clusters
     * @return
     */
    private boolean finalizo(List<Cluster> clusters) {
    	for (Cluster cluster : clusters) {
    		if (!cluster.isTermino()) {
    			return false;
    		}
    	}
    	return true;
    }

    /**
     * Selecciona los centroides iniciales de forma aleatoria.
     * @param puntos
     * @param k
     * @return
     */
    private List<Cluster> elegirCentroides(List<Punto> puntos, Integer k) {
    	List<Cluster> centroides = new ArrayList<Cluster>();

    	// Se fijan el máximo y mínimo de cada dimensión
    	List<Float> maximos = new ArrayList<Float>();
    	List<Float> minimos = new ArrayList<Float>();

    	for (int i = 0; i < this.grado; i++) {
    		Float min = Float.POSITIVE_INFINITY, max = Float.NEGATIVE_INFINITY;

    		for (Punto punto : puntos) {
    			min = min > punto.get(i) ? punto.get(0) : min;
    			max = max < punto.get(i) ? punto.get(i) : max;
    		}

    		maximos.add(max);
    		minimos.add(min);
    	}

    	Random random = new Random();

    	for (int i = 0; i < k; i++) {
    		Float[] data = new Float[this.grado];
    		Arrays.fill(data, 0f);
    		for (int d = 0; d < this.grado; d++) {
    			data[d] = random.nextFloat()
    					* (maximos.get(d) - minimos.get(d)) + minimos.get(d);		//Para cada coordenada: NumeroRandom*(puntomax-puntomin)+puntomin.
    		}

    		Cluster c = new Cluster();												//Creamos un nuevo cluster con un centroide aleatorio.
    		Punto centroide = new Punto(data);
    		c.setCentroide(centroide);
    		centroides.add(c);
    	}

	return centroides;
    }
}
