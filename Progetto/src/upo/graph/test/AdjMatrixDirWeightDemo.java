package upo.graph.test;

import java.util.Arrays;

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
	
	private static void breathFirstSearch(AdjMatrixDirWeight graph, int startingVertex) {
		System.out.print("\n BFS starting from ("+startingVertex+"): ");
		graph.getBFSTree(startingVertex);
	}
	// THIS
	private static void depthFirstSearch(AdjMatrixDirWeight graph, int startingVertex) {
		System.out.print("\n DFS starting from ("+startingVertex+"): ");
		graph.getDFSTree(startingVertex);
		System.out.print("end.");
	}
	
	private static void totalDFS(AdjMatrixDirWeight graph, int startingVertex) {
		System.out.print("\n TOT-DFS starting from ("+startingVertex+"): ");
		graph.getDFSTOTForest(startingVertex);
		System.out.print("end.");
	}
	
	private static void totalOrderDFS(AdjMatrixDirWeight graph, int[] order) {
		System.out.print("\n DFS visit following this order "+Arrays.toString(order)+": ");
		graph.getDFSTOTForest(order);
		System.out.print("end.");
	}
	
	private static void topologicalSort(AdjMatrixDirWeight graph) {
		System.out.print("\n Ordinamento topologico: ");
		int[] ord = graph.topologicalSort();
		for (int i = 0; i < graph.size()-1; i++) {
			System.out.print("("+ord[i]+")-");
		}
		System.out.print("("+ord[graph.size()-1]+")");
	}
	
	private static void stronglyCC(AdjMatrixDirWeight graph) {
		System.out.print("\n Graph's Strongly Connected Components: "+graph.stronglyConnectedComponents());
	}
	
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
	
	private static String showAsMatrix(AdjMatrixDirWeight graph) {
		System.out.print("\n Adjacency Matrix Rapresentation: \n");
		
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
				str += graph.adjMatrix[i][j] + " ";
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
		
		int[] order_1 = new int[] {0, 1, 2, 3, 4};
		int[] order_2 = new int[] {1, 2, 3, 4, 0};
		int[] order_3 = new int[] {4, 1, 2, 3, 0};
		int[] order_4 = new int[] {4, 3, 2, 1, 0};
		
		printVertices(graph);
		getSize(graph);
		
		System.out.println(); // next-line
		
		verifyVertex(graph, 0);
		verifyVertex(graph, 1);
		verifyVertex(graph, 2);
		verifyVertex(graph, 3);
		verifyVertex(graph, 4);
		verifyVertex(graph, 5);
		
		System.out.println(); // next-line
		
		addVertex(graph);
		
		printVertices(graph);
		getSize(graph);
		
		System.out.println(); // next-line
		
		verifyVertex(graph, 5);
		verifyVertex(graph, 6);
		
		System.out.println(); // next-line
		
		removeVertex(graph, 4);
		
		printVertices(graph);
		
		System.out.println(); // next-line
		
		addEdge(graph, 0, 1);
		addEdge(graph, 1, 4);
		addEdge(graph, 2, 0);
		addEdge(graph, 2, 3);
		addEdge(graph, 3, 4);
		
		System.out.println(); // next-line
		
		showAsAdjList(graph);
		
		System.out.println(); // next-line
		
		verifyEdge(graph, 0, 1);
		verifyEdge(graph, 1, 0);
		verifyEdge(graph, 1, 4);
		verifyEdge(graph, 2, 0);
		verifyEdge(graph, 2, 3);
		verifyEdge(graph, 3, 4);
		verifyEdge(graph, 4, 3);
		verifyEdge(graph, 4, 0);
		
		System.out.println(); // next-line
		
		removeEdge(graph, 0, 1);
		
		verifyEdge(graph, 0, 1);
		
		System.out.println(); // next-line
		
		addEdge(graph, 1, 0);
		
		System.out.println(); // next-line
		
		showAsAdjList(graph);
		
		System.out.println(); // next-line
		
		isAdjacent(graph, 0, 1);
		isAdjacent(graph, 0, 2);
		isAdjacent(graph, 4, 1);
		isAdjacent(graph, 3, 2);
		isAdjacent(graph, 1, 0);
		
		System.out.println(); // next-line
		
		isDirect(graph);
		
		isCyclic(graph);
		
		isDAG(graph);
		
		System.out.println(); // next-line
		
		System.out.print("\n Breath-First Search visit: ");
		breathFirstSearch(graph, 0);
		breathFirstSearch(graph, 1);
		breathFirstSearch(graph, 2);
		breathFirstSearch(graph, 3);
		breathFirstSearch(graph, 4);
		
		System.out.println(); // next-line
		
		System.out.print("\n Depth-First Search visit: ");
//		depthFirstSearch(graph, 0);
//		depthFirstSearch(graph, 1);
//		depthFirstSearch(graph, 2);
//		depthFirstSearch(graph, 3);
//		depthFirstSearch(graph, 4);
		
		System.out.print("\n\n Complete Depth-First Search visit: ");
		totalDFS(graph, 0);
		totalDFS(graph, 1);
		totalDFS(graph, 2);
		totalDFS(graph, 3);
		totalDFS(graph, 4);
		
		System.out.print("\n\n Complete Depth-First Search visit: ");
		totalOrderDFS(graph, order_1);
		totalOrderDFS(graph, order_2);
		totalOrderDFS(graph, order_3);
		totalOrderDFS(graph, order_4);
		/*
		System.out.println(); // next-line
		
		addEdge(graph, 4, 2);
		
		System.out.println(); // next-line
		
		showAsAdjList(graph);
		
		System.out.println(); // next-line
		
		isDirect(graph);
		isCyclic(graph);
		isDAG(graph);
		*/
		System.out.println(); // next-line
		
		topologicalSort(graph);
		
		System.out.println(); // next-line
		
		getEdgeWeight(graph, 1, 0);
		setEdgeWeight(graph, 1, 0, 2.5);
		getEdgeWeight(graph, 1, 0);
		
		System.out.println(); // next-line
		
		// addEdge(graph, 0, 2);
		
		showAsMatrix(graph);
		
//		printConnectedComponents(graph);
		
		stronglyCC(graph);
		
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
		
		AdjMatrixDirWeight graph = new AdjMatrixDirWeight(10);
		
		printVertices(graph);
		
		System.out.println(); // next-line
		
		addEdge(graph, 0, 4);
		addEdge(graph, 0, 5);
		addEdge(graph, 1, 0);
		addEdge(graph, 2, 1);
		addEdge(graph, 2, 6);
		addEdge(graph, 2, 3);
		addEdge(graph, 3, 2);
		addEdge(graph, 4, 0);
		addEdge(graph, 4, 7);
		addEdge(graph, 5, 1);
		addEdge(graph, 5, 4);
		addEdge(graph, 6, 2);
		addEdge(graph, 6, 5);
		addEdge(graph, 6, 8);
		addEdge(graph, 8, 7);
		addEdge(graph, 8, 9);
		addEdge(graph, 9, 8);
		
		System.out.println(); // next-line
		
		depthFirstSearch(graph, 0);
		
		System.out.println(); // next-line
		
		stronglyCC(graph);
		
		System.out.println(); // next-line
		System.out.println(); // next-line
		
		System.out.println("****************************************************************************************");
		System.out.println("***** adjacency matrix - END OF BIG WEIGHTED DIRECT GRAPH DEMO - algorithm project *****");
		System.out.println("****************************************************************************************");
	}
	
	private static void testGraph() {
		
		/* Initialize graph for testing */
		AdjMatrixDirWeight graph = new AdjMatrixDirWeight(7);
		
		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(0, 5);
		graph.addEdge(1, 4);
		graph.addEdge(2, 1);
		graph.addEdge(2, 3);
		graph.addEdge(2, 4);
		graph.addEdge(2, 6);
		graph.addEdge(4, 3);
		graph.addEdge(5, 2);
		graph.addEdge(5, 6);
		graph.addEdge(6, 3);
		
		showAsAdjList(graph);
		
		System.out.println(); // next-line
		
		depthFirstSearch(graph, 0);
		
		System.out.println(); // next-line
		
		topologicalSort(graph);	
		
		System.out.println(); // next-line
		System.out.println(); // next-line
	}
	
	public static void main(String[] args) {
		
		smallGraphDemo();
		
		System.out.print("\n|---------------------------------------NEW GRAPH---------------------------------------|\n");
		
		bigGraphDemo();
		
		System.out.print("\n|---------------------------------------NEW GRAPH---------------------------------------|\n");	
		
		testGraph();
		
		return;
	}

}

