import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Clase principal del programa. Lee los archivos de entrada y salida, el nº de k centroides y resuelve el KMeans.
 * 
 * @author https://github.com/xetorthio
 * <b>Repositorio original:<\b> https://github.com/xetorthio/kmeans
 * 
 * Modificado por alu0100961768 (Gérman Alfonso Teixidó) para cumplir las indicaciones para la práctica 4 de la asignatura DAA, Computación, grado de Informática de la ULL.   
 */
public class Reader {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader reader;																// Entrada
		reader= new BufferedReader(new FileReader("prob2.txt"));
		FileWriter writer = new FileWriter("salida.txt");									// Salida
		
		Scanner scanner = new Scanner(System.in);											// Pedimos el nº de K por pantalla.
		System.out.print("Introduzca el nº de k: ");			
		int k= scanner.nextInt();
		System.out.println();	
		
		reader.readLine(); //Número de puntos.
		reader.readLine(); //Dimensión de los puntos.	
		//int k=Integer.parseInt(reader.readLine());
		
		String line= reader.readLine();														
		List<String[]> myEntries= new ArrayList<String[]>();								// Lista de coordenadas de cada punto, necesario para crear los puntos.
		List<Punto> puntos = new ArrayList<Punto>();										// Lista de puntos de nuestro conjunto de datos.
		
		while(line != null) {
			line= line.replace(',', '.');													// Reemplazo las "," por "." para poder leerlos como floats.
			myEntries.add(line.split("\\s+"));												// Las coordenadas han de estar separadas por espacios o tabs.
			//System.out.println(line);
			line= reader.readLine();
		}
		reader.close();
		
		for (String[] strings : myEntries) {
		    Punto p = new Punto(strings);													// Se crea un Punto a partir de las coordenadas guardadas en myEntries.
		    puntos.add(p);																	// Guardo el Punto en la lsita de puntos.
		}
		
		////////////////////////////////////////////////////////////////////////
		KMeans kmeans = new KMeans();
		
		KMeansResultado resultado = kmeans.calcular(puntos, k);								// Para nuestra lista de puntos, para el número de k-agrupamientos solicitado, calcular KMeans.
		
		writer.write("------- Para k centroides=" + k + ": ofv=" + resultado.getOfv()
	    + "-------\n\n");
		int i = 0;
		for (Cluster cluster : resultado.getClusters()) {									// Para cada Cluster.
			i++;
			writer.write("-- Cluster " + i + " --\n");
			for (Punto punto : cluster.getPuntos()) {										// Escribimos los puntos que lo componen.
				writer.write(punto.toString() + "\n");
			}
			writer.write("\n");
			writer.write("Centroide: " + cluster.getCentroide().toString());				// Y Finalmente su centroide.
			writer.write("\n\n");
		}
		writer.flush();
		writer.close();
		System.out.println("¡La ejecución ha terminado!");
	}

}
