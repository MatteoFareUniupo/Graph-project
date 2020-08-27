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
			throw new NoSuchElementException("Errore: il vertice ("+index+") non esiste!");
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
		if (!containsVertex(sourceVertexIndex)) {
			throw new IllegalArgumentException("Errore: il vertice ("+sourceVertexIndex+") non è presente nel grafo. \n");
		}
		if (!containsVertex(targetVertexIndex)) {
			throw new IllegalArgumentException("Errore: il vertice ("+targetVertexIndex+") non è presente nel grafo. \n");
		}
		else { // if the Edge does not exist add it
			
			int source = vertice.indexOf(sourceVertexIndex);
			int target = vertice.indexOf(targetVertexIndex);
			
			if (!adjList.get(source).contains(target)) {
				adjList.get(source).add(target);
				adjList.get(target).add(source);
			}
			else {
				System.out.println("Errore: l'arco [" + source + "]-[" + target + "] e' gia presente nel grafo!");
			}
		}		
	}

	@Override
	public boolean containsEdge(int sourceVertexIndex, int targetVertexIndex) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		if (!containsVertex(sourceVertexIndex)) {
			throw new IllegalArgumentException("Errore: il vertice ("+sourceVertexIndex+") non è presente nel grafo. \n");
		}
		if (!containsVertex(targetVertexIndex)) {
			throw new IllegalArgumentException("Errore: il vertice ("+targetVertexIndex+") non è presente nel grafo. \n");
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
		if (!containsVertex(sourceVertexIndex)) {
			throw new IllegalArgumentException("Errore: il vertice ("+sourceVertexIndex+") non è presente nel grafo. \n");
		}
		if (!containsVertex(targetVertexIndex)) {
			throw new IllegalArgumentException("Errore: il vertice ("+targetVertexIndex+") non è presente nel grafo. \n");
		}
		if (!containsEdge(sourceVertexIndex, targetVertexIndex)) {
			throw new NoSuchElementException("Errore: l'arco ["+sourceVertexIndex+"]-["+targetVertexIndex+"] non esiste. \n");
		}

		if (adjList.get(sourceVertexIndex).contains(targetVertexIndex)) {
			for (int i = 0; i < adjList.get(sourceVertexIndex).size(); i++) {
				if (targetVertexIndex == adjList.get(sourceVertexIndex).get(i)) {
					adjList.get(sourceVertexIndex).remove(i);
					System.out.print(" The edge ["+sourceVertexIndex+"-"+targetVertexIndex+"] / ["+targetVertexIndex+"-"+sourceVertexIndex+"] is removed. \n");
				}
			}
			for (int j = 0; j < adjList.get(targetVertexIndex).size(); j++) {
				if (sourceVertexIndex == adjList.get(targetVertexIndex).get(j)) {
					adjList.get(targetVertexIndex).remove(j);
				}
			}
		}
		else {
			throw new NoSuchElementException("Errore: l'arco ["+sourceVertexIndex+"-"+targetVertexIndex+"] non è presente nel grafo");
		}
	}

	@Override
	public Set<Integer> getAdjacent(int vertexIndex) throws NoSuchElementException {
		// TODO Auto-generated method stub
		Set<Integer> adjOfVertex = new HashSet<Integer>();
		
		if (!containsVertex(vertexIndex)) {
			throw new NoSuchElementException("Errore: il vertice ("+vertexIndex+") non esiste. \n");
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
		if (!containsVertex(sourceVertexIndex)) {
			throw new IllegalArgumentException("Errore: il vertice ("+sourceVertexIndex+") non è presente nel grafo. \n");
		}
		if (!containsVertex(targetVertexIndex)) {
			throw new IllegalArgumentException("Errore: il vertice ("+targetVertexIndex+") non è presente nel grafo. \n");
		}
		if (containsEdge(sourceVertexIndex, targetVertexIndex)) {
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
		VisitForest visit = new VisitForest(this, VisitType.DFS); // boolean [] visited = new boolean[size()];
		
		for (int i = 0; i < size(); i++) {
			if (visit.getColor(i) == Color.WHITE) { // if (visited[i] == false)
				if (isCycle(i, visit, -1)) { // if (isCycle(i, visited, -1))
					result = true;
				}
			}
		}	
		return result;
	}
	
	private boolean isCycle(int currentVertex, VisitForest visit, int parent) { // private boolean isCycle(int currentVertex, boolean [] visited, int parent)
		// TODO Auto-generated method stub
		visit.setColor(currentVertex, Color.GRAY);; // visited[currentVertex] = true;
		
		for (int i = 0; i < adjList.get(currentVertex).size(); i++) {
			int vertex = adjList.get(currentVertex).get(i);
			
			if (vertex != parent) {
				if (visit.getColor(vertex) == Color.GRAY) { // if (visited[vertex])
					return true;
				}
				else {
					if (isCycle(vertex, visit, currentVertex)) { // if (isCycle(vertex, visited, currentVertex))
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
		VisitForest visita = new VisitForest(this, VisitType.BFS);
		
		if (visita.visitType != VisitType.BFS) {
			throw new UnsupportedOperationException("Errore: operazione non supportata. \n");
		}
		if (!this.containsVertex(startingVertex)) {
			throw new IllegalArgumentException("Errore: il vertice ("+startingVertex+") non esiste. \n");
		}
		queue = new LinkedList<Integer>();
		
		visita.setColor(startingVertex, Color.GRAY);
		queue.add(startingVertex);
		
		while (!queue.isEmpty()) {
			startingVertex = queue.poll();
			System.out.print(startingVertex+" ⟶ ");
			
			Iterator<Integer> iterator = getAdjacent(startingVertex).iterator();
			while (iterator.hasNext()) {
				int n = iterator.next();
				if (visita.getColor(n) == Color.WHITE) {
					visita.setColor(n, Color.GRAY);
					queue.add(n);
				}
			}
			visita.setColor(startingVertex, Color.BLACK);
		}
		return visita;
	}

	@Override
	public VisitForest getDFSTree(int startingVertex) throws UnsupportedOperationException, IllegalArgumentException {
		// TODO Auto-generated method stub
		VisitForest visita = new VisitForest(this, VisitType.DFS);
		
		if (visita.visitType != VisitType.DFS || isDirected()) {
			throw new UnsupportedOperationException("\nErrore: operazione non supportata");
		}
		if (!this.containsVertex(startingVertex)) {
			throw new IllegalArgumentException("\nErrore: il vertice "+startingVertex+" non è presente nel grafo");
		}
		
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(startingVertex);
		
		while (!stack.isEmpty()) {
			
			startingVertex = stack.peek();
			stack.pop();
			
			if (visita.getColor(startingVertex) == Color.WHITE) {
				System.out.print(startingVertex + " ⟶ ");
				visita.setColor(startingVertex, Color.GRAY);
			}
			
			Iterator<Integer> itr = adjList.get(startingVertex).iterator();
			
			while (itr.hasNext()) {
				int v = itr.next();
				if (visita.getColor(v) == Color.WHITE) {
					stack.push(v);
				}
			}
		}
		visita.setColor(startingVertex, Color.BLACK);
		return visita;
	}

	/**
	 * Utility function for TOT-DFS algorithm.
	 * @param sourceVertex
	 * @param visit
	 */
	private void DFSUtil(int sourceVertex, VisitForest visit) {
		// TODO Auto-generated method stub
		visit.setColor(sourceVertex, Color.GRAY);
		System.out.print(sourceVertex+" ⟶ ");
		
		for (int i = 0; i < size(); i++) {
			if (visit.getColor(i) == Color.WHITE) {
				DFSUtil(i, visit);
			}
		}	
	}

	@Override
	public VisitForest getDFSTOTForest(int startingVertex)
			throws UnsupportedOperationException, IllegalArgumentException {
		// TODO Auto-generated method stub
		if (!containsVertex(startingVertex)) {
			throw new IllegalArgumentException("\n The vertex ("+startingVertex+") it is not a valid argument. \n");
		}
		VisitForest visit = new VisitForest(this, VisitType.DFS_TOT);
		
		for (int i = startingVertex; i < size(); i++) {
			
			if (visit.getColor(i) == Color.WHITE) {
				DFSUtil(i, visit);
			}
		}
		return visit;
	}

	@Override
	public VisitForest getDFSTOTForest(int[] vertexOrdering)
			throws UnsupportedOperationException, IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	private void topologicalSortUtil(int v, VisitForest visit, Stack<Integer> stack) { // topologicalSortUtil(int v, boolean[] visited, Stack<Integer> stack)
		// TODO Auto-generated method stub
		visit.setColor(v, Color.GRAY); // visited[v] = true;
 		int i;
		
		Iterator<Integer> it = getAdjacent(v).iterator();
		while(it.hasNext()) {
			i = it.next();
			if (visit.getColor(i) == Color.WHITE) { // !visited[i]
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
 			// boolean[] visited = new boolean[size()];
 			
 			int pos = 0;
 			int j;
 			
 			for (int i = 0; i < size(); i++) {
				visit.setColor(i, Color.WHITE); // visited[i] = false;
			}
 			
 			for (int i = 0; i < size(); i++) {
				if (visit.getColor(i) == Color.WHITE) { // visited[i] == false
					topologicalSortUtil(i, visit, stack);
				}
			}
 			
 			while (!stack.isEmpty()) {
 				j = stack.pop();
 				ord[pos++] = j;
 			}
			return ord;
		}
		else throw new UnsupportedOperationException("\n Error: this operation is not allowed on this graph. "); 
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
		else throw new UnsupportedOperationException("\n Error: this operation is not allowed on this graph. The graph must be Direct! ");
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
		else throw new UnsupportedOperationException("Error: this type of operation is not supported by this graph. ");
	}
}
