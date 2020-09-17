package upo.graph.test;

import upo.graph.base.WeightedGraph;
import upo.graph.implementation.AdjMatrixDirWeight;

public class AdjMatrixDirWeightDemo {
	
	private static void addVertex(AdjMatrixDirWeight graph) {
		int v = graph.addVertex();
		System.out.print("\n Vertice ("+v+") aggiungo al grafo. \n");
	}
	
	private static void printVertices(AdjMatrixDirWeight graph) {
		System.out.print("\n Vertici del Grafo: ");
		for (int i = 0; i < graph.size(); i++) {
			System.out.print("(" + graph.vertices[i] + ")");
		}
		System.out.println();
	}
	
	private static void verifyVertex(AdjMatrixDirWeight graph, int index) {
		System.out.print("\n Il vertice ("+index+") è presente nel grafo ? ");
		if (graph.containsVertex(index)) {
			System.out.print("SI. ");
		}
		else System.out.print("NO. ");
	}
	
	private static void getSize(AdjMatrixDirWeight graph) {
		System.out.print("\n Numero di vertici: "+graph.size());
		System.out.println();
	}
	
	private static void removeVertex(AdjMatrixDirWeight graph, int index) {
		System.out.print("\n Rimosso vertice ("+index+") dal grafo. \n");
		graph.removeVertex(index);
	}
	
	private static void addEdge(AdjMatrixDirWeight graph, int source, int target) {
		System.out.print("\n Aggiunto arco ["+source+"]⟶["+target+"] nel grafo.");
		graph.addEdge(source, target);
	}
	
	private static void verifyEdge(AdjMatrixDirWeight graph, int source, int target) {
		System.out.print("\n L'arco ["+source+"]⟶["+target+"] è presente nel grafo ? ");
		Boolean verify = graph.containsEdge(source, target);
		
		if (verify) {
			System.out.print("SI.");
		}
		else {
			System.out.print("NO.");
		}
	}
	
	private static void removeEdge(AdjMatrixDirWeight graph, int source, int target) {
		System.out.print("\n Rimosso arco ["+source+"]-["+target+"] dal grafo -> ");
		graph.removeEdge(source, target);
	}
	
	private static void showAsAdjList(AdjMatrixDirWeight graph) {
		for (int index = 0; index < graph.size(); index++) {
			System.out.print("\n Lista di adiacenza del vertice "+index+": "+graph.getAdjacent(index));
		}
	}
	
	private static void isAdjacent(AdjMatrixDirWeight graph, int target, int source) {
		System.out.print("\n Il vertice "+target+" è adiacente a "+source+" ? ");
		Boolean verifyAdjacency = graph.isAdjacent(target, source);
		
		if (verifyAdjacency) {
			System.out.print("SI.");
		}
		else {
			System.out.print("NO.");
		}
	}
	
	private static void isDirect(AdjMatrixDirWeight graph) {
		System.out.print("\n Il grafo è diretto ? ");
		Boolean verifyDirect = graph.isDirected();
		if (verifyDirect) {
			System.out.print("SI.");
		}
		else {
			System.out.print("NO.");
		}
	}
	
	private static void isCyclic(AdjMatrixDirWeight graph) {
		System.out.print("\n Il grafo contiene un ciclo ? ");
		Boolean verifyCycle = graph.isCyclic();
		if (verifyCycle) {
			System.out.print("SI.");
		}
		else {
			System.out.print("NO.");
		}
	}
	
	private static void isDAG(AdjMatrixDirWeight graph) {
		System.out.print("\n Il grafo è un DAG ? ");
		Boolean verifyDAG = graph.isDAG();
		if (verifyDAG) {
			System.out.print("SI.");
		}
		else {
			System.out.print("NO.");
		}
	}
	
//	private static void breathFirstSearch(AdjMatrixDirWeight graph, int startingVertex) {
//		System.out.print("\n BFS starting from ("+startingVertex+"): ");
//		graph.getBFSTree(startingVertex);
//	}
//	
//	private static void depthFirstSearch(AdjMatrixDirWeight graph, int startingVertex) {
//		System.out.print("\n DFS starting from ("+startingVertex+"): ");
//		graph.getDFSTree(startingVertex);
//		System.out.print("end.");
//	}
//	
//	private static void totalDFS(AdjMatrixDirWeight graph, int startingVertex) {
//		System.out.print("\n TOT-DFS starting from ("+startingVertex+"): ");
//		graph.getDFSTOTForest(startingVertex);
//		System.out.print("end.");
//	}
//	
//	private static void totalOrderDFS(AdjMatrixDirWeight graph, int[] order) {
//		System.out.print("\n DFS visit following this order "+Arrays.toString(order)+": ");
//		graph.getDFSTOTForest(order);
//		System.out.print("end.");
//	}
	
//	private static void topologicalSort(AdjMatrixDirWeight graph) {
//		System.out.print("\n Ordinamento topologico: ");
//		int[] ord = graph.topologicalSort();
//		for (int i = 0; i < graph.size()-1; i++) {
//			System.out.print("("+ord[i]+")-");
//		}
//		System.out.print("("+ord[graph.size()-1]+")");
//		System.out.println(); // next-line
//	}
	
//	private static void stronglyCC(AdjMatrixDirWeight graph) {
//		System.out.print("\n Graph's Strongly Connected Components: "+graph.stronglyConnectedComponents());
//		System.out.println(); // next-line
//	}
	
//	private static void printConnectedComponents(AdjMatrixDirWeight graph) {
//		System.out.print("\n Graph's Connected Components: "+graph.connectedComponents());
//	}
	
	private static void getEdgeWeight(AdjMatrixDirWeight graph, int source, int target) {
		double weight = graph.getEdgeWeight(source, target);
		System.out.print("\n Peso dell'arco ["+source+"-"+target+"]: "+weight);
	}
	
	private static void setEdgeWeight(AdjMatrixDirWeight graph, int source, int target, double weight) {
		graph.setEdgeWeight(source, target, weight);
		System.out.print("\n Aggiorno il peso dell'arco ["+source+"-"+target+"]: ");
		System.out.print("nuovo valore = "+weight);
	}
	
	private static WeightedGraph floydWarshall(AdjMatrixDirWeight graph) {
		WeightedGraph floydWarshall = graph.getFloydWarshallShortestPaths();
		return floydWarshall;
	}
	
	private static String showAsMatrix(AdjMatrixDirWeight graph) {
		
		String str = " ------";
		for (int i = 0; i < graph.size(); i++) {
			str += "----";
		}
		str += "-\n";
		str += " | X |";
		
		for (int i = 0; i < graph.size(); i++) {
			str += "  " + graph.vertices[i] + " ";
		}
		str += " ";
		str += "|\n";
		str += " ------";
		
		for (int i = 0; i < graph.size(); i++) {
			str += "----";
		}
		str += "-\n";
		
		for (int i = 0; i < graph.adjMatrix.length; i++) {
			str += " | " + graph.vertices[i] + " | ";
			for (int j = 0; j < graph.adjMatrix[i].length; j++) {
				if ((graph.adjMatrix[i][j] == Double.POSITIVE_INFINITY) && (i != j)) {
					str += "INF ";
				}
				else str += graph.adjMatrix[i][j] + " ";
			}
			str += "|\n";
		}
		str += " ------";
		
		for (int i = 0; i < graph.size(); i++) {
			str += "----";
		}
		str += "-\n";
		
		System.out.print(str);
		return str;
	}

	public static void smallGraphDemo() {
		
		System.out.println(); // next-line
		
		System.out.println("****************************************************************************************");
		System.out.println("*** adjacency matrix - START OF SMALL WEIGHTED DIRECT GRAPH DEMO - algorithm project ***");
		System.out.println("****************************************************************************************");
		
		AdjMatrixDirWeight graph = new AdjMatrixDirWeight(5);
		
		printVertices(graph);
		getSize(graph);
		
		verifyVertex(graph, 0);
		verifyVertex(graph, 1);
		verifyVertex(graph, 2);
		verifyVertex(graph, 3);
		verifyVertex(graph, 4);
		verifyVertex(graph, 5);
		
		System.out.println(); // next-line
		
		addEdge(graph, 0, 1);
		addEdge(graph, 0, 2);
		addEdge(graph, 1, 2);
		addEdge(graph, 1, 3);
		addEdge(graph, 1, 4);
		addEdge(graph, 2, 3);
		addEdge(graph, 3, 4);
		
		verifyEdge(graph, 0, 1);
		verifyEdge(graph, 0, 2);
		verifyEdge(graph, 1, 2);
		verifyEdge(graph, 1, 3);
		verifyEdge(graph, 1, 4);
		verifyEdge(graph, 2, 3);
		verifyEdge(graph, 3, 4);
		verifyEdge(graph, 0, 3);
		
		System.out.println(); // next-line
		
		addVertex(graph);
		addVertex(graph);

		getSize(graph);
		
		System.out.println(); // next-line
		
		isAdjacent(graph, 0, 1);
		isAdjacent(graph, 0, 2);
		isAdjacent(graph, 1, 2);
		isAdjacent(graph, 1, 3);
		isAdjacent(graph, 1, 4);
		isAdjacent(graph, 2, 3);
		isAdjacent(graph, 3, 4);
		isAdjacent(graph, 0, 3);
		
		System.out.println(); // next-line
		
		showAsMatrix(graph);
		
		showAsAdjList(graph);
		
		addEdge(graph, 4, 5);
		
		System.out.println(); // next-line
		
		showAsMatrix(graph);
		
		removeEdge(graph, 4, 5);
		
		showAsMatrix(graph);
		
		removeVertex(graph, 5);
		
		showAsMatrix(graph);
		
		isDirect(graph);
		isCyclic(graph);
		isDAG(graph);
		
		System.out.println(); // next-line
		
		getEdgeWeight(graph, 0, 1);
		getEdgeWeight(graph, 0, 2);
		getEdgeWeight(graph, 1, 2);
		getEdgeWeight(graph, 1, 3);
		getEdgeWeight(graph, 1, 4);
		getEdgeWeight(graph, 2, 3);
		getEdgeWeight(graph, 3, 4);
		
		System.out.println(); // next-line
		
		setEdgeWeight(graph, 0, 1, 2.0);
		setEdgeWeight(graph, 0, 2, 5.0);
		setEdgeWeight(graph, 1, 2, 5.0);
		setEdgeWeight(graph, 1, 3, 0.5);
		setEdgeWeight(graph, 1, 4, 5.0);
		setEdgeWeight(graph, 2, 3, 3.0);
		setEdgeWeight(graph, 3, 4, 3.5);
		
		System.out.println("\n\n Pre Floyd-Warsahll ");
		
		showAsMatrix(graph);
		
		System.out.print("\n Post Floyd-Warsahll \n");
		
		showAsMatrix((AdjMatrixDirWeight) floydWarshall(graph));
		
		System.out.println(); // next-line
		System.out.println(); // next-line
		
		System.out.println("****************************************************************************************");
		System.out.println("**** adjacency matrix - END OF SMALL WEIGHTED DIRECT GRAPH DEMO - algorithm project ****");
		System.out.println("****************************************************************************************");
	}
	
	public static void bigGraphDemo() {
		
		System.out.println(); // next-line
		
		System.out.println("****************************************************************************************");
		System.out.println("**** adjacency matrix - START OF BIG WEIGHTED DIRECT GRAPH DEMO - algorithm project ****");
		System.out.println("****************************************************************************************");
		
		AdjMatrixDirWeight graph = new AdjMatrixDirWeight(8); 
		
		printVertices(graph);
		
		System.out.println(); // next-line
		
		addEdge(graph, 0, 1);
		addEdge(graph, 0, 2);
		addEdge(graph, 2, 4);
		addEdge(graph, 2, 5);
		addEdge(graph, 3, 0);
		addEdge(graph, 3, 2);
		addEdge(graph, 4, 3);
		addEdge(graph, 4, 6);
		addEdge(graph, 5, 1);
		addEdge(graph, 5, 4);
		addEdge(graph, 6, 5);
		addEdge(graph, 6, 7);
		addEdge(graph, 7, 4);
		
		System.out.println(); // next-line
		
		setEdgeWeight(graph, 0, 1, 4.0);
		setEdgeWeight(graph, 0, 2, 4.0);
		setEdgeWeight(graph, 2, 4, 4.0);
		setEdgeWeight(graph, 2, 5, -2.0);
		setEdgeWeight(graph, 3, 0, 3.0);
		setEdgeWeight(graph, 3, 2, 2.0);
		setEdgeWeight(graph, 4, 3, 1.0);
		setEdgeWeight(graph, 4, 6, -2.0);
		setEdgeWeight(graph, 5, 1, 3.0);
		setEdgeWeight(graph, 5, 4, -3.0);
		setEdgeWeight(graph, 6, 5, 2.0);
		setEdgeWeight(graph, 6, 7, 2.0);
		setEdgeWeight(graph, 7, 4, -2.0);
		
		System.out.println(); // next-line
		System.out.println(); // next-line
		
		showAsMatrix(graph);
		
		// Negative Cycle
		showAsMatrix((AdjMatrixDirWeight)floydWarshall(graph));
		
		System.out.println(); // next-line
		System.out.println(); // next-line
		
		System.out.println("****************************************************************************************");
		System.out.println("***** adjacency matrix - END OF BIG WEIGHTED DIRECT GRAPH DEMO - algorithm project *****");
		System.out.println("****************************************************************************************");
	}
	
	public static void main(String[] args) {
		
		smallGraphDemo();
		
		System.out.print("\n|---------------------------------------NEW GRAPH---------------------------------------|\n");
		
		bigGraphDemo();
		
		return;
	}

}

