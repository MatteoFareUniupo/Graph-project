package upo.graph.test;

//import java.util.Scanner;

import upo.graph.implementation.AdjListUndir;

public class AdjListUndirDemo {

	static AdjListUndir grafo = new AdjListUndir();

	private static void StampaVertici() {
		System.out.println();
		System.out.print(" Vertici del Grafo: ");
		for (int v : grafo.vertice) {
			System.out.print("(" + grafo.vertice.get(v) + ") ");
		}
	}
	
	private static void VerifyVertex(int vertex) {
		System.out.println(); // next-line
		System.out.println("  Il vertice "+vertex+" è presente ? ");
		if (grafo.containsVertex(vertex)) {
			System.out.println("  SI, "+vertex+" fa' parte del grafo.");
		}
		else System.out.println("  "+vertex+" NON esiste.");
	}
	
	private static void VerifyEdge(int v1, int v2) {
		
		System.out.print("  Verifica la presenza dell'arco ["+v1+"-"+v2+"]: ");
		if (grafo.containsEdge(v1, v2)) {
			System.out.print("l'arco appartiene al grafo.\n");
		}
		else {
			System.out.print("l'arco NON appartiene al grafo.\n");
		}
	}
	
	// Print Adjacency List 
	private	static void printAdjList() {
			for (int i = 0; i < grafo.size(); i++) {
				System.out.print("  Adjacency list of vertex " + i + " -> ");
					System.out.println(grafo.getAdjacent(i));	
				}
				System.out.println(); // next-line
		}
	
	private static void detectCycle() {
		System.out.print("  Il grafo presenta un ciclo ?"); 
		if (grafo.isCyclic() == true) {
			System.out.print(" SI.");
		}
		else {
			System.out.println("  NO.");
		}
	}
	
	private static void isDAG() {
		
		if (grafo.isDAG() == true) {
			System.out.println("\n  Il grafo è un DAG");
		}
		else {
			System.out.println("\n  Il grafo NON è un DAG.");
		}
	}
	
	private static void getSize() {
		
		System.out.print("\n  Numero di vettori: "+grafo.size()+"\n");
	}
	
	public static void smallGraphDemo() {
		
		System.out.println(); // next-line
		
		System.out.println("*********************************");
		System.out.println("*** START OF SMALL GRAPH DEMO ***");
		System.out.println("*********************************");
		
		grafo.addVertex();
		grafo.addVertex();
		grafo.addVertex();
		grafo.addVertex();
		grafo.addVertex();
		
		StampaVertici();
		System.out.println(); // next-line
		
		VerifyVertex(0);
		VerifyVertex(1);
		VerifyVertex(2);
		VerifyVertex(3);
		VerifyVertex(4);
		
		grafo.initAdjList();
		
		System.out.println(); // next-line
		printAdjList();
		
		grafo.addEdge(0, 1);
		grafo.addEdge(0, 2);
		grafo.addEdge(1, 2);
		grafo.addEdge(2, 3);
		
		VerifyEdge(0, 1);
		VerifyEdge(0, 2);
		VerifyEdge(1, 2);
		VerifyEdge(2, 3);
		VerifyEdge(1, 4);
		
		System.out.println(); // next-line
		
		printAdjList();
		
		detectCycle();
		
		isDAG();
		
		System.out.print("\n* VISITA IN AMPIEZZA: ");
		grafo.getBFSTree(0);
		grafo.getBFSTree(1);
		grafo.getBFSTree(2);
		grafo.getBFSTree(3);
		
		System.out.println(); // next-line
		
		System.out.print("\n* VISITA IN PROFONDITA': ");
		grafo.getDFSTree(0);
		grafo.getDFSTree(1);
		grafo.getDFSTree(2);
		grafo.getDFSTree(3);
		
		System.out.println(); // next-line
		
		StampaVertici();
		
		System.out.println(); // next-line
		
		getSize();
		
		System.out.println(); // next-line
		
		printAdjList();
		
		System.out.print("* VISITA IN PROFONDITA' TOTALE: ");
		grafo.getDFSTOTForest(0);
		grafo.getDFSTOTForest(1);
		grafo.getDFSTOTForest(2);
		grafo.getDFSTOTForest(3);
		grafo.getDFSTOTForest(4);
		
		System.out.println(); // next-line
		System.out.println(); // next-line
				
		System.out.println("*******************************");
		System.out.println("*** END OF SMALL GRAPH DEMO ***");
		System.out.println("*******************************");
		
		System.out.println(); // next-line
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		smallGraphDemo();
	}
}
