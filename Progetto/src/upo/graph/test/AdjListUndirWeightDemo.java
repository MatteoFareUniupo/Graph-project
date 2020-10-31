package upo.graph.test;

import upo.graph.implementation.AdjListUndirWeight;

public class AdjListUndirWeightDemo {
	
	private static void getSize(AdjListUndirWeight graph) {
		
		System.out.print("\n Vertices' Number: "+graph.size() + "\n");
	}
	
	private static void printVertices(AdjListUndirWeight graph) {
		
		System.out.print("\n Graph's Vertices: ");
		for (int v : graph.vertexList) {
			System.out.print("(" + graph.vertexList.get(v) + ") ");
		}
		System.out.println();
	}
	
	private static void addVertex(AdjListUndirWeight graph) {
		
		int v = graph.addVertex();
		System.out.print("\n Vertex ("+v+") added to the graph. \n");
	}
	
	private static void removeVertex(AdjListUndirWeight graph, int vertex) {
		
		System.out.print("\n Vertex ("+vertex+") removed. \n");
		graph.removeVertex(vertex);
	}
 	
	private static void verifyVertex(AdjListUndirWeight graph, int vertex) {
		
		System.out.print("\n Does the vertex ("+vertex+") exist in the graph? ");
		if (graph.containsVertex(vertex)) {
			System.out.print("YES. \n");
		}
		else System.out.print("NO. \n");
	}
	
	private static void addEdge(AdjListUndirWeight graph, int source, int target) {
		
		graph.addEdge(source, target);
		System.out.print("\n Added the edge ["+source+"]-["+target+"] into the graph. \n");
	}
	
	private static void verifyEdge(AdjListUndirWeight graph, int source, int target) {
		
		System.out.print("\n Does the edge ["+source+"]-["+target+"] exist? ");
		Boolean verify = graph.containsEdge(source, target);
		
		if (verify) {
			System.out.print("YES. \n");
		}
		else {
			System.out.print("NO. \n");
		}
	}
	
	private static void removeEdge(AdjListUndirWeight graph, int source, int target) {
		
		graph.removeEdge(source, target);
		System.out.print("\n Removed the edge ["+source+"]-["+target+"] from the graph. \n");
	}
	
	private static void getAdjacent(AdjListUndirWeight graph) {
		
		for (int i = 0; i < graph.size(); i++) {
			System.out.print("\n Adjacency list of vertex "+i+": "+graph.getAdjacent(i));
		}
		System.out.print("\n");
	}
	
	private static void isAdjacent(AdjListUndirWeight graph, int target, int source) {
		
		System.out.print("\n Vertex ("+target+") is adjacent to vertex ("+source+")? ");
		Boolean verifyAdjacency = graph.isAdjacent(target, source);
		
		if (verifyAdjacency) {
			System.out.print("YES.");
		}
		else {
			System.out.print("NO.");
		}
		System.out.print("\n");
		
	}
	
	private static void getWeightAdjList(AdjListUndirWeight graph) {
		
		System.out.print("\n Adjacency List with associated weight: \n");
		
		for (int i : graph.vertexList) {
			System.out.print(" "+ i + ": "+ graph.adjList.get(i) + "\n");
		}
	}
	
	private static void isDirect(AdjListUndirWeight graph) {
		
		System.out.print("\n is the graph direct? ");
		Boolean verifyDirect = graph.isDirected();
		if (verifyDirect) {
			System.out.print("YES.");
		}
		else {
			System.out.print("NO.");
		}
		System.out.print("\n");
	}
	
	private static void isCyclic(AdjListUndirWeight graph) {
		
		System.out.print("\n Does the graph contain a cycle? ");
		Boolean verifyCycle = graph.isCyclic();
		
		if (verifyCycle) {
			System.out.print("YES.");
		}
		else {
			System.out.print("NO.");
		}
		System.out.print("\n");
	}
	
	private static void printConnectedComponent(AdjListUndirWeight graph) {
		
		System.out.print("\n Graph's Connected Componets: "+graph.connectedComponents());
		System.out.print("\n");
	}
	
	private static void getEdgeWeight(AdjListUndirWeight graph, int source, int target) {
		
		double weight = graph.getEdgeWeight(source, target);
		System.out.print("\n Edge Weight ["+source+"-"+target+"]: " + weight);
		System.out.print("\n ");
	}
	
	private static void setEdgeWeight(AdjListUndirWeight graph, int source, int target, double weight) {
		
		double old_value = graph.getEdgeWeight(source, target);
		
		graph.setEdgeWeight(source, target, weight);
		System.out.print("\n Set the Weight of ["+source+"-"+target+"]: old weight = "+ old_value + " -> ");
		System.out.print("new weight = " + weight);
	}
	
	
	public static void smallGraphDemo() {
		
		System.out.println(); // next-line
		
		System.out.println("**********************************************************************");
		System.out.println("*** adjacency list - START OF SMALL GRAPH DEMO - algorithm project ***");
		System.out.println("**********************************************************************");
			
		AdjListUndirWeight graph = new AdjListUndirWeight(5);
		
		printVertices(graph);
		
		getSize(graph);
		
		addVertex(graph);
		
		printVertices(graph);
		
		getSize(graph);
		
		verifyVertex(graph, 0); // TRUE
		verifyVertex(graph, 1); // TRUE
		verifyVertex(graph, 2); // TRUE
		verifyVertex(graph, 3); // TRUE
		verifyVertex(graph, 4); // TRUE
		verifyVertex(graph, 5); // FALSE
		verifyVertex(graph, 6); // FALSE
		
		removeVertex(graph, 5);
		
		verifyVertex(graph, 5);
		
		getSize(graph);
		
		addEdge(graph, 0, 1);
		addEdge(graph, 0, 2);
		addEdge(graph, 0, 3);
		addEdge(graph, 1, 3);
		addEdge(graph, 4, 1);
		
		verifyEdge(graph, 0, 1);
		verifyEdge(graph, 0, 2);
		verifyEdge(graph, 0, 3);
		verifyEdge(graph, 1, 3);
		verifyEdge(graph, 4, 1);
		
		verifyEdge(graph, 1, 0);
		verifyEdge(graph, 2, 0);
		verifyEdge(graph, 3, 0);
		verifyEdge(graph, 3, 1);
		verifyEdge(graph, 1, 4);
		
		getWeightAdjList(graph);
		
		getAdjacent(graph);
		
		isCyclic(graph);
		
		removeEdge(graph, 1, 4);
		
		isAdjacent(graph, 0, 1);
		isAdjacent(graph, 1, 0);
		isAdjacent(graph, 0, 2);
		isAdjacent(graph, 0, 3);
		isAdjacent(graph, 1, 3);
		
		//removeEdge(graph, 0, 1);
		removeVertex(graph, 1);
		
		verifyEdge(graph, 0, 1);
		verifyEdge(graph, 1, 0);
		
		getWeightAdjList(graph);
		getAdjacent(graph);
		
		isAdjacent(graph, 0, 1);
		isAdjacent(graph, 1, 0);
		isAdjacent(graph, 0, 2);
		isAdjacent(graph, 0, 3);
		isAdjacent(graph, 1, 3);
		
		isDirect(graph);
		isCyclic(graph);
		
		printConnectedComponent(graph);
		
		getEdgeWeight(graph, 0, 2);
		getEdgeWeight(graph, 2, 0);
//		getEdgeWeight(graph, 1, 0);
		
		setEdgeWeight(graph, 0, 2, 4.7);
		
		System.out.println(); /*** next-line ***/
		System.out.println(); /*** next-line ***/
				
		System.out.println("**********************************************************************");
		System.out.println("**** adjacency list - END OF SMALL GRAPH DEMO - algorithm project ****");
		System.out.println("**********************************************************************");			
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		smallGraphDemo();
		
		return;
	}

}
