package upo.graph.implementation;

import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.ArrayList;
import java.util.HashSet;
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
	
	/**
	 * Initialize adjacency list.
	 */
	public void initAdjList() {
		adjList = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < vertice.size(); i++) {
				adjList.add(new ArrayList<Integer>());
		}
	}
	
	public int addVertex() {
		// TODO Auto-generated method stub
		int i = size();
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
			throw new NoSuchElementException("Error: the vertex (" + index + ") does not exist!");
		}
		for (int i = 0; i < size(); i++) {
			if (i == index) {
				vertice.remove(i); // remove the chosen vertex.
				
				for (int j = index; j < size(); j++) { // restore the index number progression.
					vertice.set(j, j);
				}
			}
		}	
	}

	@Override
	public void addEdge(int sourceVertexIndex, int targetVertexIndex) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		int source = vertice.indexOf(sourceVertexIndex);
		int target = vertice.indexOf(targetVertexIndex);
		
		// check if vertices exist
		if (!containsVertex(source) || !containsVertex(target)) {
			throw new IllegalArgumentException("Errore: uno dei vertici inseriti, o entrambi, non sono presenti nel grafo");
		}
		else { // if the Edge does not exist add it
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
		int source = vertice.indexOf(sourceVertexIndex);
		int target = vertice.indexOf(targetVertexIndex);
		
		if (!containsVertex(source) || !containsVertex(target)) {
			throw new IllegalArgumentException("Errore: uno dei vertici inseriti, o entrambi, non sono presenti nel grafo");
		}
		
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

		int source = vertice.indexOf(sourceVertexIndex);
		int target = vertice.indexOf(targetVertexIndex);
		
		if (!containsVertex(sourceVertexIndex) || !containsVertex(targetVertexIndex)) {
			throw new IllegalArgumentException("Errore: uno dei vertici inseriti, o entrambi, non sono presenti nel grafo");
		}

		if (adjList.get(sourceVertexIndex).contains(targetVertexIndex)) {
			for (int i = 0; i < adjList.get(source).size(); i++) {
				if (targetVertexIndex == adjList.get(source).get(i)) {
					adjList.get(source).remove(i);
				}
			}
			for (int j = 0; j < adjList.get(target).size(); j++) {
				if (sourceVertexIndex == adjList.get(target).get(j)) {
					adjList.get(target).remove(j);
				}
			}
		}
		else {
			throw new NoSuchElementException("Errore: l'arco ["+sourceVertexIndex+"-"+targetVertexIndex+"] non è presente nel grafo");
		}
	}

	@Override
	public Set<Integer> getAdjacent(int vertexIndex) throws NoSuchElementException {

		Set<Integer> adjOfVertex = new HashSet<Integer>();
		
		int index = vertice.indexOf(vertexIndex);
		
		if (!containsVertex(index)) {
			throw new NoSuchElementException("Errore: vertice non presente nel grafo");
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
		if (!containsVertex(sourceVertexIndex) || !containsVertex(targetVertexIndex)) {
			throw new IllegalArgumentException("Errore: uno dei vertici inseriti, o entrambi, non sono presenti nel grafo");
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
			//adjList.get(v);
			getAdjacent(v);
		}
		return false;
	}

	@Override
	public boolean isCyclic() {
		// TODO Auto-generated method stub
		boolean result = false;
		
		boolean [] visited = new boolean[size()];
		
		for (int i = 0; i < size(); i++) {
			if (visited[i] == false) {
				if (isCycle(i, visited, -1)) {
					result = true;
				}
			}
		}	
		return result;
	}
	
	private boolean isCycle(int currentVertex, boolean [] visited, int parent) {
		
		visited[currentVertex] = true;
		
		for (int i = 0; i < adjList.get(currentVertex).size(); i++) {
			
			int vertex = adjList.get(currentVertex).get(i);
			
			if (vertex != parent) {
				if (visited[vertex]) {
					return true;
				}
				else {
					if (isCycle(vertex, visited, currentVertex)) {
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
		if (!isCyclic())
			return true;
		else
			return false;
	}

	@Override
	public VisitForest getBFSTree(int startingVertex) throws UnsupportedOperationException, IllegalArgumentException {
		// TODO Auto-generated method stub
		VisitForest visita = new VisitForest(this, VisitType.BFS);
		
		if (visita.visitType != VisitType.BFS) {
			throw new UnsupportedOperationException("\nErrore: operazione non supportata");
		}
		if (!this.containsVertex(startingVertex)) {
			throw new IllegalArgumentException("\nErrore: il vertice "+startingVertex+" non è presente nel grafo");
		}
		
		Queue<Integer> queue = new LinkedList<Integer>();
//		boolean [] visited = new boolean[size()];
		
		for (int i = 0; i < size(); i++) {
			visita.setColor(i, Color.WHITE);
			//visita.setParent(i, i);
			visita.setDistance(i, Double.POSITIVE_INFINITY);
		}
		
		visita.setColor(startingVertex, Color.GRAY);
//		visited[startingVertex] = true;
		
		visita.setDistance(startingVertex, 0);
		System.out.print("\n  Visita BFS partendo da ("+startingVertex+"): ");
		
		queue.add(startingVertex);

		while (queue.size() != 0)
		{	
			startingVertex = queue.poll();
			System.out.print(startingVertex+" -> ");
			
			for (int i = 0; i < adjList.get(startingVertex).size(); i++) {
				
				int vertex = adjList.get(startingVertex).get(i);
				
				if (visita.getColor(vertex) == Color.WHITE) { // !visited[vertex]
					visita.setColor(vertex, Color.GRAY);
					visita.setParent(vertex, startingVertex);
//					visited[vertex] = true;
					visita.setDistance(vertex, startingVertex + 1);
					queue.add(vertex);
				}
			}
			visita.setColor(startingVertex, Color.BLACK);
		}
		System.out.print("Fine.");
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
		
		Stack<Integer> stack = new Stack<>();
		stack.add(startingVertex);

		System.out.print("\n  Visita DFS partendo da ("+startingVertex+"): ");
		while (!stack.isEmpty()) {
			
			int vertex = stack.pop();
			
			if (visita.getColor(vertex) == Color.WHITE) {
				System.out.print(vertex + " -> ");
				visita.setColor(vertex, Color.GRAY);
			}
			
			for (int i = 0; i < adjList.get(vertex).size(); i++) {
				
				int u = adjList.get(vertex).get(i);
				
				if ((isAdjacent(u, vertex)) && (visita.getColor(u) == Color.WHITE)) {
					stack.add(u);
				}
			}	
		}
		visita.setColor(startingVertex, Color.BLACK);
		System.out.print("Fine.");
		
		return visita;
	}

	@Override
	public VisitForest getDFSTOTForest(int startingVertex)
			throws UnsupportedOperationException, IllegalArgumentException {
		// TODO Auto-generated method stub
		VisitForest visita = new VisitForest(this, VisitType.DFS_TOT);
		
		if (visita.visitType != VisitType.DFS_TOT || isDirected()) {
			throw new UnsupportedOperationException("\nErrore: operazione non supportata");
		}
		if (!this.containsVertex(startingVertex)) {
			throw new IllegalArgumentException("\nErrore: il vertice "+startingVertex+" non è presente nel grafo");
		}
		
		for (int i = 0; i < size(); i++) {
			if (visita.getColor(i) == Color.WHITE) {
				getDFSTree(startingVertex);
				return visita;
			}
		}
		return visita;
	}

	@Override
	public VisitForest getDFSTOTForest(int[] vertexOrdering)
			throws UnsupportedOperationException, IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] topologicalSort() throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Set<Integer>> stronglyConnectedComponents() throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Set<Integer>> connectedComponents() throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}
}
