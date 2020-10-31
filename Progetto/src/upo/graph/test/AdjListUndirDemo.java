package upo.graph.test;

import upo.graph.implementation.AdjListUndir;

public class AdjListUndirDemo {
	
	private static void addVertex(AdjListUndir graph) {
		
		int v = graph.addVertex();
		System.out.print("\n Vertice ("+v+") aggiungo al grafo. \n");
	}
	
	private static void printVertices(AdjListUndir graph) {
		
		System.out.print("\n Vertici del Grafo: ");
		for (int v : graph.vertice) {
			System.out.print("(" + graph.vertice.get(v) + ") ");
		}
		System.out.println();
	}
	
	private static void verifyVertex(AdjListUndir graph, int vertex) {
		
		System.out.print("\n Il vertice ("+vertex+") è presente nel grafo ? ");
		if (graph.containsVertex(vertex)) {
			System.out.print(" SI.");
		}
		else System.out.print(" NO.");
	}
	
	private static void getSize(AdjListUndir graph) {
		
		System.out.print("\n Numero di vertici: "+graph.size());
		System.out.println();
	}
	
	private static void removeVertex(AdjListUndir graph, int index) {
		
		System.out.print("\n Rimosso vertice ("+index+") dal grafo.");
		graph.removeVertex(index);
	}
	
	private static void addEdge(AdjListUndir graph, int source, int target) {
		
		System.out.print("\n Aggiunto arco ["+source+"]-["+target+"] nel grafo.");
		graph.addEdge(source, target);
	}
	
	private static void verifyEdge(AdjListUndir graph, int source, int target) {
		
		System.out.print("\n L'arco ["+source+"]-["+target+"] è presente nel grafo ? ");
		Boolean verify = graph.containsEdge(source, target);
		
		if (verify) {
			System.out.print("SI.");
		}
		else {
			System.out.print("NO.");
		}
	}
	
	private static void removeEdge(AdjListUndir graph, int source, int target) {
		
		System.out.print("\n Rimosso arco ["+source+"]-["+target+"] dal grafo. ");
		graph.removeEdge(source, target);
	}
	
	private static void showAdjList(AdjListUndir graph) {
		
		for (int i = 0; i < graph.size(); i++) {
			System.out.print("\n Adjacency list of vertex "+i+": "+graph.getAdjacent(i));
		}
	}
	
	private static void isAdjacent(AdjListUndir graph, int target, int source) {
		
		System.out.print("\n Il vertice ("+target+") è adiacente a ("+source+") ? ");
		Boolean verifyAdjacency = graph.isAdjacent(target, source);
		
		if (verifyAdjacency) {
			System.out.print("SI.");
		}
		else {
			System.out.print("NO.");
		}
	}
	
	private static void isDirect(AdjListUndir graph) {
		
		System.out.print("\n Il grafo è diretto ? ");
		Boolean verifyDirect = graph.isDirected();
		if (verifyDirect) {
			System.out.print("SI.");
		}
		else {
			System.out.print("NO.");
		}
	}
	
	private static void isCyclic(AdjListUndir graph) {
		
		System.out.print("\n Il grafo contiene un ciclo ? ");
		Boolean verifyCycle = graph.isCyclic();
		if (verifyCycle) {
			System.out.print("SI.");
		}
		else {
			System.out.print("NO.");
		}
	}
	
	private static void isDAG(AdjListUndir graph) {
		
		System.out.print("\n Il grafo è un DAG ? ");
		Boolean verifyDAG = graph.isDAG();
		if (verifyDAG) {
			System.out.print("SI.");
		}
		else {
			System.out.print("NO.");
		}
	}
	
	private static void breathFirstSearch(AdjListUndir graph, int startingVertex) {
		
		System.out.print("\n BFS starting from ("+startingVertex+"): ");
		graph.getBFSTree(startingVertex);
		System.out.print("end.");
		
	}
	
	private static void depthFirstSearch(AdjListUndir graph, int startingVertex) {
		
		System.out.print("\n DFS starting from ("+startingVertex+"): ");
		graph.getDFSTree(startingVertex);
		System.out.print("end.");
	}
	
	private static void totalDFS(AdjListUndir graph, int startingVertex) {
		
		System.out.print("\n TOT-DFS starting from ("+startingVertex+"): ");
		graph.getDFSTOTForest(startingVertex);
		System.out.print("end.");
	}
	
	private static void totalDFSOrder(AdjListUndir graph, int [] order) {
		
		System.out.print("\n TOT-DFS following array { ");
		for (int i = 0; i < order.length; i++) {
			System.out.print(order[i]+" ");
		}
		System.out.print("}:\n"); 
		graph.getDFSTOTForest(order);
	}
	
	private static void printConnectedComponents(AdjListUndir graph) {
		
		System.out.print("\n Graph's Connected Componets: "+graph.connectedComponents());
	}
	
	public static void smallGraphDemo() {
		
		System.out.println(); // next-line
		
		System.out.println("**********************************************************************");
		System.out.println("*** adjacency list - START OF SMALL GRAPH DEMO - algorithm project ***");
		System.out.println("**********************************************************************");
		
		AdjListUndir graph = new AdjListUndir(7);
		
		int [] order = {0, 1, 2, 3, 4, 5 , 6, 7};
		
		printVertices(graph);
		
		getSize(graph);
		
		verifyVertex(graph, 0);
		verifyVertex(graph, 1);
		verifyVertex(graph, 2);
		verifyVertex(graph, 3);
		verifyVertex(graph, 4);
		verifyVertex(graph, 5);
		verifyVertex(graph, 6);
		
		System.out.println(); // next-line
		
		addEdge(graph, 0, 1);
		addEdge(graph, 0, 2);
		addEdge(graph, 1, 2);
		addEdge(graph, 1, 3);
		addEdge(graph, 2, 4);
		addEdge(graph, 3, 4);
		addEdge(graph, 5, 6);
		
		System.out.println(); // next-line
		
		verifyEdge(graph, 0, 1);
		verifyEdge(graph, 0, 2);
		verifyEdge(graph, 1, 2);
		verifyEdge(graph, 1, 3);
		verifyEdge(graph, 2, 4);
		verifyEdge(graph, 3, 4);
		verifyEdge(graph, 5, 6);
		
		System.out.println(); // next-line
		
		showAdjList(graph);
		
		System.out.println(); // next-line
		
		addVertex(graph);
		
		showAdjList(graph);
		
		System.out.println(); // next-line
		
		isAdjacent(graph, 0, 1);
		isAdjacent(graph, 0, 2);
		isAdjacent(graph, 1, 2);
		isAdjacent(graph, 1, 3);
		isAdjacent(graph, 2, 4);
		isAdjacent(graph, 3, 4);
		isAdjacent(graph, 5, 6);
		
		System.out.println(); // next-line
		
		isCyclic(graph);
		isDirect(graph);
		isDAG(graph);
		
		System.out.println(); // next-line
		
		printConnectedComponents(graph);
		
		System.out.println(); // next-line
		
		breathFirstSearch(graph, 0);
		breathFirstSearch(graph, 1);
		breathFirstSearch(graph, 6);
		
		System.out.println(); // next-line
		
		depthFirstSearch(graph, 0);
		depthFirstSearch(graph, 2);
		depthFirstSearch(graph, 4);
		
		System.out.println(); // next-line
		
		totalDFS(graph, 0);
		totalDFS(graph, 5);
		totalDFS(graph, 6);
		totalDFS(graph, 7);
		
		System.out.println(); // next-line
		
		totalDFSOrder(graph, order);
		
		removeEdge(graph, 5, 6);
		removeVertex(graph, 7);
		
		System.out.println(); // next-line
		
		showAdjList(graph);
		
		System.out.println(); // next-line
		
		totalDFS(graph, 6);
		
		System.out.println(); /*** next-line ***/
		System.out.println(); /*** next-line ***/
				
		System.out.println("**********************************************************************");
		System.out.println("**** adjacency list - END OF SMALL GRAPH DEMO - algorithm project ****");
		System.out.println("**********************************************************************");			
	}
	
	public static void bigGraphDemo() {
		
		System.out.println(); // next-line
		
		System.out.println("**********************************************************************");
		System.out.println("**** adjacency list - START OF BIG GRAPH DEMO - algorithm project ****");
		System.out.println("**********************************************************************");
		
		AdjListUndir graph = new AdjListUndir(10);
		int [] order = {1, 0, 3, 2, 5, 4, 7, 6, 9, 8};
		
		printVertices(graph);

		addEdge(graph, 0, 1);
		addEdge(graph, 0, 4);
		addEdge(graph, 0, 5);
		addEdge(graph, 2, 3);
		addEdge(graph, 2, 6);
		addEdge(graph, 8, 9);
		
		System.out.println(); // next-line
		
		breathFirstSearch(graph, 0);
		
		System.out.println(); // next-line
		
		depthFirstSearch(graph, 0);
		
		System.out.println(); // next-line
		
		totalDFS(graph, 0);
		
		System.out.println(); // next-line
		
		totalDFSOrder(graph, order);
		
		printConnectedComponents(graph);
		
		System.out.println(); // next-line
		
		System.out.print("\n "+ graph.adjList+ "\n");
		
		showAdjList(graph);
		
		System.out.println(); // next-line
		
		removeVertex(graph, 6);
		
		System.out.println(); // next-line
		
		showAdjList(graph);
		
		System.out.println(); // next-line
		System.out.println(); // next-line
		
		System.out.println("**********************************************************************");
		System.out.println("***** adjacency list - END OF BIG GRAPH DEMO - algorithm project *****");
		System.out.println("**********************************************************************");
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		smallGraphDemo();
		
		System.out.print("\n|------------------------------NEW GRAPH------------------------------|\n");
		
		bigGraphDemo();
	
		System.out.print("\n|------------------------------NEW GRAPH------------------------------|\n");
		
		return;
	}
}
