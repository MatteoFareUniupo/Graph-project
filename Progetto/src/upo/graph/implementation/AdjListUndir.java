package upo.graph.implementation;

import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

import upo.graph.base.*;
import upo.graph.base.VisitForest.Color;
import upo.graph.base.VisitForest.VisitType;


/**
 * Implementazione mediante <strong>liste di adiacenza</strong> di un grafo <strong>non orientato non pesato</strong>.
 * 
 * @author Matteo Faré 20014560
 *
 */
public class AdjListUndir implements Graph{
	
	public ArrayList<Integer> vertice = new ArrayList<Integer>();
	public ArrayList<ArrayList<Integer>> adjList;
	public static int time; /* visit time counter */
	
	private int numOfVertices = size();
	private Queue<Integer> queue; /* queue used by BFS algorithm. */
	
	public AdjListUndir(int num) {
		
		adjList = new ArrayList<ArrayList<Integer>>();
		vertice = new ArrayList<Integer>();
		this.numOfVertices = num;
		for (int i = 0; i < numOfVertices; i++) {
			adjList.add(new ArrayList<Integer>());
			vertice.add(i);
		}		
	}
	
	public int addVertex() {
		// TODO Auto-generated method stub
		int i = size();
		ArrayList<Integer> adjacentVertices = new ArrayList<Integer>();
		adjList.add(adjacentVertices);
		vertice.add(i);
		
		return vertice.get(i);
	}

	@Override
	public boolean containsVertex(int index) {
		// TODO Auto-generated method stub
		for (int v : vertice) {
			if (v == index) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void removeVertex(int index) throws NoSuchElementException {
		// TODO Auto-generated method stub
		if (!containsVertex(index)) {
			throw new NoSuchElementException("Error: vertex ("+index+") does not exist! \n");
		}
		for (int i = 0; i < size(); i++) {
			if (i == index) {
				vertice.remove(i); // remove the chosen vertex.
				adjList.remove(i);
				
				for (int j = index; j < size(); j++) { // restore the index number progression.
					vertice.set(j, j);
				}
			}
		}	
		for (int i = 0; i < size(); i++) {
			int posVertex = adjList.get(i).indexOf(index);
			if (posVertex > -1) {
				adjList.get(i).remove(posVertex);
			}
		}
	}

	@Override
	public void addEdge(int sourceVertexIndex, int targetVertexIndex) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		// check if vertices exist
		if (!containsVertex(sourceVertexIndex) && containsVertex(targetVertexIndex)) {
			throw new IllegalArgumentException("Error: vertex ("+sourceVertexIndex+") does not exist! \n");
		}
		else if (!containsVertex(targetVertexIndex) && containsVertex(sourceVertexIndex)) {
			throw new IllegalArgumentException("Error: vertex ("+targetVertexIndex+") does not exist! \n");
		}
		else if (!containsVertex(sourceVertexIndex) && !containsVertex(targetVertexIndex)) {
			throw new IllegalArgumentException("Error: vertices ("+sourceVertexIndex+") and ("+targetVertexIndex+") do not exist! \n");
		}
		else { // if the Edge does not exist add it
			
			int source = vertice.indexOf(sourceVertexIndex);
			int target = vertice.indexOf(targetVertexIndex);
			
			if (!adjList.get(source).contains(target)) {
				adjList.get(source).add(target);
				adjList.get(target).add(source);
			}
		}		
	}

	@Override
	public boolean containsEdge(int sourceVertexIndex, int targetVertexIndex) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		if (!containsVertex(sourceVertexIndex) && containsVertex(targetVertexIndex)) {
			throw new IllegalArgumentException("Error: vertex ("+sourceVertexIndex+") does not exist! \n");
		}
		else if (!containsVertex(targetVertexIndex) && containsVertex(sourceVertexIndex)) {
			throw new IllegalArgumentException("Error: vertex ("+targetVertexIndex+") does not exist! \n");
		}
		else if ((!containsVertex(sourceVertexIndex)) && (!containsVertex(targetVertexIndex))) {
			throw new IllegalArgumentException("Error: vertices ("+sourceVertexIndex+") and ("+targetVertexIndex+") do not exist! \n");
		}
		
		int source = vertice.indexOf(sourceVertexIndex);
		int target = vertice.indexOf(targetVertexIndex);
		
		if (containsVertex(source)) {
			if (adjList.get(source).contains(target)) { // contains(targetVertexIndex)
				return true;
			}
		}
		return false;
	}

	@Override
	public void removeEdge(int sourceVertexIndex, int targetVertexIndex)
			throws IllegalArgumentException, NoSuchElementException {
		// TODO Auto-generated method stub
		if (!containsVertex(sourceVertexIndex) && containsVertex(targetVertexIndex)) {
			throw new IllegalArgumentException("Error: vertex ("+sourceVertexIndex+") does not exist! \n");
		}
		else if (!containsVertex(targetVertexIndex) && containsVertex(sourceVertexIndex)) {
			throw new IllegalArgumentException("Error: vertex ("+targetVertexIndex+") does not exist! \n");
		}
		else if (!containsVertex(sourceVertexIndex) && !containsVertex(targetVertexIndex)) {
			throw new IllegalArgumentException("Error: vertices ("+sourceVertexIndex+") and ("+targetVertexIndex+") do not exist! \n");
		}
		else if (!containsEdge(sourceVertexIndex, targetVertexIndex)) {
			throw new NoSuchElementException("Error: edge ["+sourceVertexIndex+"]-["+targetVertexIndex+"] does not exist! \n");
		}
		else if (adjList.get(sourceVertexIndex).contains(targetVertexIndex)) {
			for (int i = 0; i < adjList.get(sourceVertexIndex).size(); i++) {
				if (targetVertexIndex == adjList.get(sourceVertexIndex).get(i)) {
					adjList.get(sourceVertexIndex).remove(i);
				}
			}
			for (int j = 0; j < adjList.get(targetVertexIndex).size(); j++) {
				if (sourceVertexIndex == adjList.get(targetVertexIndex).get(j)) {
					adjList.get(targetVertexIndex).remove(j);
				}
			}
		}
	}

	@Override
	public Set<Integer> getAdjacent(int vertexIndex) throws NoSuchElementException {
		// TODO Auto-generated method stub
		Set<Integer> adjOfVertex = new HashSet<Integer>();
		
		if (!containsVertex(vertexIndex)) {
			throw new NoSuchElementException("Error: vertex ("+vertexIndex+") does not exist! \n");
		}
		else {
			for (int v = 0; v < vertice.size(); v++) {
				if (containsEdge(vertexIndex, v)) {
					adjOfVertex.add(v);
				}
			}
		}
		return adjOfVertex;
	}
	
	@Override
	public boolean isAdjacent(int targetVertexIndex, int sourceVertexIndex) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		if (!containsVertex(sourceVertexIndex) && containsVertex(targetVertexIndex)) {
			throw new IllegalArgumentException("Error: vertex ("+sourceVertexIndex+") does not exist! \n");
		}
		else if (!containsVertex(targetVertexIndex) && containsVertex(sourceVertexIndex)) {
			throw new IllegalArgumentException("Error: vertex ("+targetVertexIndex+") does not exist! \n");
		}
		else if (!containsVertex(sourceVertexIndex) && !containsVertex(targetVertexIndex)) {
			throw new IllegalArgumentException("Error: vertices ("+sourceVertexIndex+") and ("+targetVertexIndex+") do not exist! \n");
		}
		else if (containsEdge(sourceVertexIndex, targetVertexIndex)) {
			return true;
		}
		else 
			return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		int numeroDiVertici = vertice.size();
		
		return numeroDiVertici;
	}

	@Override
	public boolean isDirected() {
		// TODO Auto-generated method stub
		for (int v : vertice) {
			getAdjacent(v); //adjList.get(v);
		}
		return false;
	}

	@Override
	public boolean isCyclic() {
		// TODO Auto-generated method stub
		boolean result = false;
		VisitForest visit = new VisitForest(this, VisitType.DFS); 
		
		for (int i = 0; i < size(); i++) {
			if (visit.getColor(i) == Color.WHITE) { 
				if (isCycle(i, visit, -1)) { 
					result = true;
				}
			}
		}	
		return result;
	}
	
	private boolean isCycle(int currentVertex, VisitForest visit, int parent) {
		// TODO Auto-generated method stub
		visit.setColor(currentVertex, Color.GRAY);
		
		for (int i = 0; i < adjList.get(currentVertex).size(); i++) {
			int vertex = adjList.get(currentVertex).get(i);
			
			if (vertex != parent) {
				if (visit.getColor(vertex) == Color.GRAY) { 
					return true;
				}
				else {
					if (isCycle(vertex, visit, currentVertex)) { 
						return true;
					}
				}
			}	
		}
		return false;
	}

	@Override
	public boolean isDAG() {
		// TODO Auto-generated method stub
		if (!isCyclic() && isDirected())
			return true;
		else
			return false;
	}

	@Override
	public VisitForest getBFSTree(int startingVertex) throws UnsupportedOperationException, IllegalArgumentException {
		// TODO Auto-generated method stub
		if (!this.containsVertex(startingVertex)) {
			throw new IllegalArgumentException("Error: vertex ("+startingVertex+") does not exist! \n");
		}
		if (WeightedGraph.class.isAssignableFrom(getClass())) {
			throw new UnsupportedOperationException("Error: dfs algorith is not apllicable on this graph! \n"); 
		}
		VisitForest visita = new VisitForest(this, VisitType.BFS);
		queue = new LinkedList<Integer>();
		
		visita.setColor(startingVertex, Color.GRAY);
		queue.add(startingVertex);
		
		visita.setDistance(startingVertex, 0.0);
		
		while (!queue.isEmpty()) {
			startingVertex = queue.peek();
			System.out.print(startingVertex+" ⟶ ");
			
			Iterator<Integer> iterator = getAdjacent(startingVertex).iterator();
			while (iterator.hasNext()) {
				
				int n = iterator.next();
				
				if (visita.getColor(n) == Color.WHITE) {
					
					visita.setColor(n, Color.GRAY);
					visita.setDistance(n, visita.getDistance(startingVertex) + 1.0);
					visita.setParent(n, startingVertex);
					
					queue.add(n);
				}
			}
			visita.setColor(startingVertex, Color.BLACK);
			queue.remove();
		}
//		System.out.print("end. \n");
		return visita;
	}
	
	/**
	 * Utility function for DFS algorithm.
	 * @param sourceVertex
	 * @param visit
	 */
	private VisitForest dfsUtil(int sourceVertex, VisitForest visit) { 
		// TODO Auto-generated method stub
		visit.setColor(sourceVertex, Color.GRAY);
		visit.setStartTime(sourceVertex, time);
		time++;
		
		System.out.print(sourceVertex+" ⟶ ");
		
		for (int vertex : getAdjacent(sourceVertex)) {
			if (visit.getColor(vertex) == Color.WHITE) {
				visit.setParent(vertex, sourceVertex);
				dfsUtil(vertex, visit);
			}
		}
		visit.setColor(sourceVertex, Color.BLACK);
		visit.setEndTime(sourceVertex, time);
		time++;
		
		return visit;
	}

	@Override
	public VisitForest getDFSTree(int startingVertex) throws UnsupportedOperationException, IllegalArgumentException {
		// TODO Auto-generated method stub
		if (!this.containsVertex(startingVertex)) {
			throw new IllegalArgumentException("Error: vertex ("+startingVertex+") does not exist! \n");
		}
		if (WeightedGraph.class.isAssignableFrom(getClass())) {
			throw new UnsupportedOperationException("Error: dfs algorith is not apllicable on this graph! \n"); 
		}
		VisitForest visit = new VisitForest(this, VisitType.DFS);
		
		//System.out.print("dfs("+startingVertex+"): ");
		
		for (int i : vertice) {
			visit.setColor(i, Color.WHITE); /* Initialize vertices to WHITE */
			visit.setStartTime(i, (int)Double.POSITIVE_INFINITY); /* Initialize vertices to infinite */
			visit.setEndTime(i, (int)Double.POSITIVE_INFINITY); /* Initialize vertices to infinite */
		}
		time = 0; /* Initialize counter to zero */
		
		visit = dfsUtil(startingVertex, visit); 
		
		//System.out.print("end. \n");
		return visit;
	}

	@Override
	public VisitForest getDFSTOTForest(int startingVertex)
			throws UnsupportedOperationException, IllegalArgumentException {
		// TODO Auto-generated method stub
		if (!containsVertex(startingVertex)) {
			throw new IllegalArgumentException("\n The vertex ("+startingVertex+") does not exist! \n");
		}
		if (WeightedGraph.class.isAssignableFrom(getClass())) {
			throw new UnsupportedOperationException("Error: dfs algorith is not apllicable on this graph! \n"); 
		}
		VisitForest visit = new VisitForest(this, VisitType.DFS_TOT);
		
		for (int i : vertice) {
			visit.setColor(i, Color.WHITE); /* Initialize vertices to WHITE */
			visit.setStartTime(i, (int)Double.POSITIVE_INFINITY); /* Initialize vertices to infinite */
			visit.setEndTime(i, (int)Double.POSITIVE_INFINITY); /* Initialize vertices to infinite */
		}
		time = 0; /* Initialize counter to zero */
		
		//System.out.print("dfs-tot("+startingVertex+"): ");
		
		dfsUtil(startingVertex, visit);

		for (int i : vertice) {
			if (visit.getColor(i) == Color.WHITE) {
				visit = dfsUtil(i, visit);
			}
		}
		//System.out.print("\n");
		return visit;
	}

	@Override
	public VisitForest getDFSTOTForest(int[] vertexOrdering)
			throws UnsupportedOperationException, IllegalArgumentException {
		// TODO Auto-generated method stub
		for (int i : vertice) {
			if (!containsVertex(vertexOrdering[i])) {
				throw new IllegalArgumentException("\n The vertex ("+vertexOrdering[i]+") does not exist! \n");
			}
		}
		if (WeightedGraph.class.isAssignableFrom(getClass())) {
			throw new UnsupportedOperationException("Error: dfs algorith is not apllicable on this graph! \n"); 
		}
		VisitForest visit = new VisitForest(this, VisitType.DFS_TOT);
		
		for (int i : vertice) {
			visit.setColor(i, Color.WHITE); /* Initialize vertices to WHITE */
			visit.setStartTime(i, (int)Double.POSITIVE_INFINITY); /* Initialize vertices to infinite */
			visit.setEndTime(i, (int)Double.POSITIVE_INFINITY); /* Initialize vertices to infinite */
		}
		time = 0; /* Initialize counter to zero */
		
		for (int i : vertexOrdering) {
			System.out.print(" • ");
			if (visit.getColor(i) == Color.WHITE) {
				getDFSTOTForest(i);
			}
			System.out.println("end. ");
		}
		return visit;
	}
		

	
	/**
	 * Support method to find topological sort.
	 * @param v
	 * @param visit
	 * @param stack
	 */
	private void topologicalSortUtil(int v, VisitForest visit, Stack<Integer> stack) {
		// TODO Auto-generated method stub
		visit.setColor(v, Color.GRAY);
 		int i;
		
		Iterator<Integer> it = getAdjacent(v).iterator();
		while(it.hasNext()) {
			i = it.next();
			if (visit.getColor(i) == Color.WHITE) {
				topologicalSortUtil(i, visit, stack);
			}
		}
		stack.push(v);	
	}

	@Override
	public int[] topologicalSort() throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		if (isDAG()) {
			VisitForest visit = new VisitForest(this, VisitType.DFS_TOT);
			Stack<Integer> stack = new Stack<>();
			
			int[] ord = new int[size()];
 			
 			int pos = 0;
 			int j;
 			
 			for (int i = 0; i < size(); i++) {
				visit.setColor(i, Color.WHITE);
			}
 			
 			for (int i = 0; i < size(); i++) {
				if (visit.getColor(i) == Color.WHITE) {
					topologicalSortUtil(i, visit, stack);
				}
			}
 			
 			while (!stack.isEmpty()) {
 				j = stack.pop();
 				ord[pos++] = j;
 			}
			return ord;
		}
		else throw new UnsupportedOperationException("\n Error: this operation is not allowed on this graph - It must be a DAG!!! \n"); 
	}

	@Override
	public Set<Set<Integer>> stronglyConnectedComponents() throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		if (isDirected()) {
			
			Set<Set<Integer>> setOfSCC = new HashSet<>();
			
			Stack<Integer> stack = new Stack<>();
			
			VisitForest visitForest = new VisitForest(this, VisitType.DFS_TOT);
			
			for (int i = 0; i < size(); i++) {
				visitForest.setColor(i, Color.WHITE);
			}
			
			for (int i = 0; i < size(); i++) {
				if (visitForest.getColor(i) == Color.WHITE) {
					fillOrder(i, visitForest, stack);
				}
			}
			
			AdjListUndir trGraph = getTranspose();
			VisitForest trForest = new VisitForest(trGraph, VisitType.DFS_TOT);
			
			for (int i = 0; i < trGraph.size(); i++) {
				trForest.setColor(i, Color.WHITE);
			}
			
			while (!stack.isEmpty()) {
				
				int v = stack.pop();
				Set<Integer> set = new HashSet<>();
				
				if (trForest.getColor(v) == Color.WHITE) {
					trGraph.dfsSCC(v, trForest, set);
					setOfSCC.add(set);
				}
			}
			return setOfSCC;
		}
		else throw new UnsupportedOperationException("\n Error: this operation is not allowed on this graph - It must be Direct!!! \n");
	}
	
	/**
	 * Method that visits the graph and saves the
	 * order of visit of the vertices in a stack.
	 * @param v - vertex of the graph.
	 * @param forest - visit forest.
	 * @param stack - stack used to save the visiting order.
	 */
	private void fillOrder(int v, VisitForest forest, Stack<Integer> stack) {
		// TODO Auto-generated method stub
		forest.setColor(v, Color.GRAY);
		
		Iterator<Integer> iterator = getAdjacent(v).iterator();
		
		while (iterator.hasNext()) {
			int n = iterator.next();
			
			if (forest.getColor(n) == Color.WHITE) {
				fillOrder(n, forest, stack);
			}
		}
		stack.push(v);
	}
	
	/**
	 * Method that given a graph, creates the transposed graph.
	 * @return graph - the graph.
	 */
	AdjListUndir getTranspose() {
		// TODO Auto-generated method stub
		AdjListUndir graph = new AdjListUndir(size());
		
		for (int i = 0; i < size(); i++) {
			
			Iterator<Integer> iterator = getAdjacent(i).iterator();
			
			while (iterator.hasNext()) {
				graph.addEdge(iterator.next(), i);
			}
		}
		return graph;
	}
	
	/**
	 * Method that performs a DFS-TOT visit on the transposed graph.
	 * It inserts the visited vertices into an Integer Set.
	 * @param v - vertex of the graph.
	 * @param forest - visit forest.
	 * @param set - set of Integer.
	 */
	private void dfsSCC(int v, VisitForest forest, Set<Integer> set) {
		// TODO Auto-generated method stub
		forest.setColor(v, Color.GRAY);
		set.add(v);
		
		int n;
		
		Iterator<Integer> iterator = getAdjacent(v).iterator();
		
		while (iterator.hasNext()) {
			n = iterator.next();
			if (forest.getColor(n) == Color.WHITE) {
				dfsSCC(n, forest, set);
			}
		}	
	}
	
	@Override
	public Set<Set<Integer>> connectedComponents() throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		if (!isDirected()) {
			
			VisitForest visit = new VisitForest(this, VisitType.DFS_TOT);
			
			Set<Set<Integer>> setOfCC = new HashSet<>();
			Set<Integer> visitedSet = new HashSet<>();
			
			for (int i : vertice) {
				if (!visitedSet.contains(i)) {
					
					Set<Integer> resultedSet = new HashSet<>();

					Stack<Integer> stack = new Stack<>();
					
					stack.push(i);
					
					while(!stack.isEmpty()) {
						
						int vertex = stack.pop();
						visit.setColor(vertex, Color.GRAY);
						
						visitedSet.add(vertex);
						resultedSet.add(vertex);
						
						for (int v : getAdjacent(vertex)) {
							if (!visitedSet.contains(v)) {
								stack.push(v);
							}
						}
					}
					setOfCC.add(resultedSet);
				}
			}
			return setOfCC;
		}
		else throw new UnsupportedOperationException("Error: this type of operation is not supported by this graph - It must be undirect!!! \n");
	}
}
