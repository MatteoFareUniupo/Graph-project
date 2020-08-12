package upo.graph.test;

//import java.util.Scanner;

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
		System.out.print("\n Rimosso arco ["+source+"]-["+target+"] dal grafo -> ");
		graph.removeEdge(source, target);
	}
	
	private static void showAdjList(AdjListUndir graph) {
		for (int index = 0; index < graph.size(); index++) {
			System.out.print("\n Adjacency list of vertex "+index+": "+graph.getAdjacent(index));
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
	
	public static void smallGraphDemo() {
		
		System.out.println(); // next-line
		
		System.out.println("**********************************************************************");
		System.out.println("*** adjacency list - START OF SMALL GRAPH DEMO - algorithm project ***");
		System.out.println("**********************************************************************");
		
		AdjListUndir graph = new AdjListUndir(5);
		
		printVertices(graph);
		
		System.out.println(); /*** next-line ***/
		
		getSize(graph);
		
		System.out.println(); /*** next-line ***/
		
		verifyVertex(graph, 0);
		verifyVertex(graph, 1);
		verifyVertex(graph, 2);
		verifyVertex(graph, 3);
		verifyVertex(graph, 4);
		verifyVertex(graph, 5);
		
		System.out.println(); /*** next-line ***/
		
		addVertex(graph);
		verifyVertex(graph, 5);
		
		System.out.println(); /*** next-line ***/
		
		showAdjList(graph);
		
		System.out.println(); /*** next-line ***/
		
		addEdge(graph, 0, 1);
		addEdge(graph, 0, 2);
		addEdge(graph, 0, 3);
		addEdge(graph, 3, 4);
		addEdge(graph, 3, 5);
		
		System.out.println(); /*** next-line ***/
		
		verifyEdge(graph, 0, 1);
		verifyEdge(graph, 1, 0);
		verifyEdge(graph, 0, 2);
		verifyEdge(graph, 2, 0);
		verifyEdge(graph, 0, 3);
		verifyEdge(graph, 3, 0);
		verifyEdge(graph, 3, 4);
		verifyEdge(graph, 3, 5);
		verifyEdge(graph, 3, 1);
		
		System.out.println(); /*** next-line ***/
		
		showAdjList(graph);
		
		System.out.println(); /*** next-line ***/
		
		removeVertex(graph, 5);
		verifyVertex(graph, 5);
		/*** verifyEdge(graph, 3, 5); throws exception because vertex (5) is no more. ***/
		/*** removeEdge(graph, 3, 5); throws exception because vertex (5) is no more. ***/
		System.out.println(); /*** next-line ***/
		
		printVertices(graph);
		
		removeEdge(graph, 3, 4);
		
		showAdjList(graph);
		
		System.out.println(); /*** next-line ***/
		
		isAdjacent(graph, 1, 0);
		isAdjacent(graph, 2, 0);
		isAdjacent(graph, 3, 0);
		isAdjacent(graph, 4, 3);
		isAdjacent(graph, 0, 1);
		
		System.out.println(); /*** next-line ***/
		
		isCyclic(graph);
		isDirect(graph);
		isDAG(graph);
		
		System.out.println(); /*** next-line ***/
		
		breathFirstSearch(graph, 0);
		breathFirstSearch(graph, 1);
		breathFirstSearch(graph, 2);
		breathFirstSearch(graph, 3);
		breathFirstSearch(graph, 4);
		
		System.out.println(); /*** next-line ***/
		
		depthFirstSearch(graph, 0);
		depthFirstSearch(graph, 1);
		depthFirstSearch(graph, 2);
		depthFirstSearch(graph, 3);
		depthFirstSearch(graph, 4);
		
		System.out.println(); /*** next-line ***/
		
		totalDFS(graph, 0);
		totalDFS(graph, 1);
		totalDFS(graph, 2);
		totalDFS(graph, 3);
		totalDFS(graph, 4);
		
		System.out.println(); /*** next-line ***/
		System.out.println(); /*** next-line ***/
				
		System.out.println("***********************************************************************");
		System.out.println("**** adjacency list - END OF SMALL GRAPH DEMO - algorithm project  ****");
		System.out.println("***********************************************************************");
				
	}
	/*
	public static void bigGraphDemo() {
		
		System.out.println(); // next-line
		
		System.out.println("**********************************************************************");
		System.out.println("**** adjacency list - START OF BIG GRAPH DEMO - algorithm project ****");
		System.out.println("**********************************************************************");
		
		AdjListUndir graph = new AdjListUndir(10);		
		
		System.out.println(); // next-line
		System.out.println(); // next-line
		
		System.out.println("*********************************");
		System.out.println("***** END OF BIG GRAPH DEMO *****");
		System.out.println("*********************************");
	}
	*/
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		smallGraphDemo();
		System.out.print("\n|-----------NEW GRAPH-----------|\n");
		//bigGraphDemo();
		
		return;
	}
}
