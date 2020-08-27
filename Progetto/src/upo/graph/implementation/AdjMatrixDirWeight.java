package upo.graph.implementation;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import upo.graph.base.*;
import upo.graph.base.VisitForest.Color;
import upo.graph.base.VisitForest.VisitType;

/**
 * Implementazione mediante <strong>matrice di adiacenza</strong> di un grafo <strong>orientato pesato</strong>.
 * 
 * @author Matteo Faré 20014560
 *
 */
public class AdjMatrixDirWeight implements WeightedGraph {
	
	public int [] vertices;
	public double [][] adjMatrix;
	
	public AdjMatrixDirWeight(int dim) {
		// TODO Auto-generated constructor stub
		vertices = new int[dim];
		for (int i = 0; i < dim; i++) {
			vertices[i] = i;
		}
		adjMatrix = new double[dim][dim];	
	}
	
	public int findIndex(int vertex) {
		int index = 0;
		for (int i = 0; i < size(); i++) {
			if (vertices[i] == vertex) {
				index = i;
			}
		}
		return index;
	}

	@Override
	public int addVertex() {
		// TODO Auto-generated method stub
		int v = vertices[size()-1] + 1;
		double [][] newMatrix = new double [size()+1][size()+1];
		int [] newVertex = new int [size()+1];
		
		for (int i = 0; i < size(); i++) {
			for (int j = 0; j < size(); j++) {
				newMatrix[i][j] = adjMatrix[i][j];
			}
		}
		
		for (int i = 0; i < size(); i++) {
			newVertex[i] = vertices[i];
		}
		newVertex[size()] = v;
		
		vertices = newVertex;
		adjMatrix = newMatrix;
		
		return vertices[size()-1];
	}

	@Override
	public boolean containsVertex(int index) {
		// TODO Auto-generated method stub
		for (int i : vertices) {
			if (vertices[i] == index) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void removeVertex(int index) throws NoSuchElementException {
		// TODO Auto-generated method stub
		if (!containsVertex(index)) {
			throw new NoSuchElementException("The vertex "+index+" does not exist in the graph.");
		}
		
		int indexOfVertex = findIndex(index);
		double [][] newMatrix = new double [size()-1][size()-1];
		int [] newVertices = new int [size()-1];
		
		for (int i = 0; i < indexOfVertex; i++) {
			newVertices[i] = vertices[i];
		}
		
		for (int i = indexOfVertex; i < size()-1; i++) {
			newVertices[i] = vertices[i+1];
		}
		
		for (int i = 0; i < indexOfVertex; i++) {
			for (int j = 0; j < indexOfVertex; j++) {
				newMatrix[i][j] = adjMatrix[i][j];
			}
		}
		
		for (int i = indexOfVertex; i < size()-1; i++) {
			for (int j = indexOfVertex; j < indexOfVertex; j++) {
				newMatrix[i][j] = adjMatrix[i+1][j];
			}
		}
		
		for (int i = indexOfVertex; i < size()-1; i++) {
			for (int j = indexOfVertex; j < size()-1; j++) {
				newMatrix[i][j] = adjMatrix[i+1][j+1];
			}
		}
		
		for (int i = 0; i < indexOfVertex; i++) {
			for (int j = indexOfVertex; j < size()-1; j++) {
				newMatrix[i][j] = adjMatrix[i][j+1];
			}
		}
		/* Restore the index/value number progression */
		for (int i = indexOfVertex; i < size()-1; i++) { 
			newVertices[i] = vertices[i];
		}
		
		vertices = newVertices;
		adjMatrix = newMatrix;
	}

	@Override
	public void addEdge(int sourceVertexIndex, int targetVertexIndex) throws IllegalArgumentException {
		// TODO Auto-generated method stub		
		if (!containsVertex(sourceVertexIndex) || (sourceVertexIndex < 0)) {
			throw new IllegalArgumentException("\n The vertex "+sourceVertexIndex+" it is not a valid argument. \n");
		}
		if (!containsVertex(targetVertexIndex) || (targetVertexIndex) < 0) {
			throw new IllegalArgumentException("\n The vertex "+targetVertexIndex+" it is not a valid argument. \n");
		}
		int indexOfSource = findIndex(sourceVertexIndex);
		int indexOfTarget = findIndex(targetVertexIndex);
		
		if (adjMatrix[indexOfSource][indexOfTarget] == 0) {
			adjMatrix[indexOfSource][indexOfTarget] = defaultEdgeWeight;
		}
		else if (adjMatrix[indexOfTarget][indexOfSource] == 0) {
			adjMatrix[indexOfTarget][indexOfSource] = defaultEdgeWeight;
		}
		else {
			
			if (adjMatrix[indexOfSource][indexOfTarget] != 0) {
				System.out.print("\n The edge ["+indexOfSource+"]-["+indexOfTarget+"] is already in the graph. \n");
			}
			if (adjMatrix[indexOfTarget][indexOfSource] != 0) {
				System.out.print("\n The edge ["+indexOfTarget+"]-["+indexOfSource+"] is already in the graph. \n");
			}
		}
	}

	@Override
	public boolean containsEdge(int sourceVertexIndex, int targetVertexIndex) throws IllegalArgumentException {
		// TODO Auto-generated method stub		
		if (!containsVertex(sourceVertexIndex) || (sourceVertexIndex < 0)) {
			throw new IllegalArgumentException("\n The vertex "+sourceVertexIndex+" it is not a valid argument. \n");
		}
		if (!containsVertex(targetVertexIndex) || (targetVertexIndex) < 0) {
			throw new IllegalArgumentException("\n The vertex "+targetVertexIndex+" it is not a valid argument. \n");
		}
		int indexOfSource = findIndex(sourceVertexIndex);
		int indexOfTarget = findIndex(targetVertexIndex);
		
		if (adjMatrix[indexOfSource][indexOfTarget] != 0) {
			return true;
		}
		else if (adjMatrix[indexOfTarget][indexOfSource] != 0) {
			return true;
		}
		else 
			return false;
	}

	@Override
	public void removeEdge(int sourceVertexIndex, int targetVertexIndex)
			throws IllegalArgumentException, NoSuchElementException {
		// TODO Auto-generated method stub
		if (sourceVertexIndex < 0) {
			throw new IllegalArgumentException("\n The vertex "+sourceVertexIndex+" it is not a valid argument. \n");
		}
		if (!containsVertex(sourceVertexIndex)) {
			throw new NoSuchElementException("The vertex "+sourceVertexIndex+" does not exist in the graph.");
		}
		if (targetVertexIndex < 0) {
			throw new IllegalArgumentException("\n The vertex "+targetVertexIndex+" it is not a valid argument. \n");
		}
		if (!containsVertex(targetVertexIndex)) {
			throw new NoSuchElementException("The vertex "+targetVertexIndex+" does not exist in the graph.");
		}
		int indexOfSource = findIndex(sourceVertexIndex);
		int indexOfTarget = findIndex(targetVertexIndex);
		
		if (adjMatrix[indexOfSource][indexOfTarget] != 0) {
			adjMatrix[indexOfSource][indexOfTarget] = 0;
			System.out.print(" The edge ["+indexOfSource+"-"+indexOfTarget+"] is removed. \n");
		}
		else if (adjMatrix[indexOfTarget][indexOfSource] != 0) {
			adjMatrix[indexOfSource][indexOfTarget] = 0;
			System.out.print(" The edge ["+indexOfTarget+"-"+indexOfSource+"] is removed. \n");
		}
		else {
			System.out.print(" The edge ["+indexOfSource+"-"+indexOfTarget+"] does not exist in the graph. \n");
		}
	}

	@Override
	public Set<Integer> getAdjacent(int vertexIndex) throws NoSuchElementException {
		// TODO Auto-generated method stub
		if (!containsVertex(vertexIndex)) {
			throw new IllegalArgumentException("\n The vertex "+vertexIndex+" does not exist in the graph. \n");
		}
		Set<Integer> adj = new HashSet<Integer>();
		int indexOfVertex = findIndex(vertexIndex);
		
		for (int i = 0; i < size(); i++) {
			if (adjMatrix[indexOfVertex][i] != 0) {
				adj.add(vertices[i]);
			}
		}
		return adj;
	}

	@Override
	public boolean isAdjacent(int targetVertexIndex, int sourceVertexIndex) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		if (!containsVertex(sourceVertexIndex) || (sourceVertexIndex < 0)) {
			throw new IllegalArgumentException("\n The vertex "+sourceVertexIndex+" it is not a valid argument. \n");
		}
		if (!containsVertex(targetVertexIndex) || (targetVertexIndex) < 0) {
			throw new IllegalArgumentException("\n The vertex "+targetVertexIndex+" it is not a valid argument. \n");
		}
		int indexOfSource = findIndex(sourceVertexIndex);
		int indexOfTarget = findIndex(targetVertexIndex);
		
		if (adjMatrix[indexOfSource][indexOfTarget] != 0) {
			return true;
		}
		else 
			return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return vertices.length;
	}
	
	private void transpose(double adjMatrix[][], double tr[][]) {
		// TODO Auto-generated method stub
		for (int i = 0; i < size(); i++) {
			for (int j = 0; j < size(); j++) {
				tr[i][j] = adjMatrix[j][i];	
			}
		}
	}

	@Override
	public boolean isDirected() {
		// TODO Auto-generated method stub
		double tr[][] = new double[size()][size()];
		transpose(adjMatrix, tr);
		for (int i = 0; i < size(); i++) {
			for (int j = 0; j < size(); j++) {
				/* if the matrix is not symmetric, the graph is direct. */
				if (adjMatrix[i][j] != tr[i][j]) {
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean checkCycle(int currentVertexIndex, VisitForest visitForest) {
		// TODO Auto-generated method stub
		int indexOfVertex = findIndex(currentVertexIndex);
		visitForest.setColor(vertices[indexOfVertex], Color.GRAY);
		
		for (int i = 0; i < size(); i++) {
			if (adjMatrix[indexOfVertex][i] != 0) {
				visitForest.setParent(vertices[i], indexOfVertex);
				if (visitForest.getColor(vertices[i]) == Color.WHITE) {
					if (checkCycle(i, visitForest)) {
						return true;
					}
				}
				else if (visitForest.getColor(vertices[i]) == Color.GRAY) {
					return true;
				}
			}
		}
		visitForest.setColor(vertices[indexOfVertex], Color.BLACK);
		return false;
	}

	@Override
	public boolean isCyclic() {
		// TODO Auto-generated method stub
		VisitForest visit = new VisitForest(this, VisitType.DFS);
		/* INIZIALIZZA */
		for (int i = 0; i < size(); i++) {
			visit.setColor(vertices[i], Color.WHITE);
		}
		
		for (int i = 0; i < size(); i++) {
			if ((visit.getColor(vertices[i]) == Color.WHITE) && (checkCycle(i, visit))) {
				return true;
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
		VisitForest visit = new VisitForest(this, VisitType.BFS);
		Queue<Integer> queue = new LinkedList<Integer>();
		
		for (int i = 0; i < size(); i++) {
			visit.setColor(i, Color.WHITE);
			visit.setDistance(i, Double.POSITIVE_INFINITY);
		}

		visit.setColor(startingVertex, Color.GRAY);	
		visit.setDistance(startingVertex, 0.0);
		
		queue.add(startingVertex);
		
		while (queue.size() != 0)
		{	
			int head = queue.peek(); /* Equal to: "int head = queue.poll();" -> with this method "queue.remove()" must be removed. */
			System.out.print(head+" ⟶ "); // DA TOGLIERE 
			
			for (int i : getAdjacent(head)) {  //  (int i = 0; i < size(); i++) così esplora tutti i vertici
				if (visit.getColor(i) == Color.WHITE) { 
					visit.setColor(i, Color.GRAY);
					visit.setParent(i, head);
					visit.setDistance(i, visit.getDistance(head) + 1.0);
					queue.add(i);
				}
			}
			visit.setColor(head, Color.BLACK);
			queue.remove();
		}
		System.out.print("end."); // DA TOGLIERE
		return visit;
	}
	
	/**
	 * Utility function for DFS algorithm.
	 * @param sourceVertex
	 * @param visit
	 */
	private void DFSUtil(int sourceVertex, VisitForest visit) {
		// TODO Auto-generated method stub
		visit.setColor(sourceVertex, Color.GRAY);
		System.out.print(sourceVertex+" ⟶ ");// DA TOGLIERE
		
		for (int i : getAdjacent(sourceVertex)) {
			if (visit.getColor(i) == Color.WHITE) {
				DFSUtil(i, visit);
			}
		}	
	}

	@Override
	public VisitForest getDFSTree(int startingVertex) throws UnsupportedOperationException, IllegalArgumentException {
		// TODO Auto-generated method stub
		VisitForest visit = new VisitForest(this, VisitType.DFS);
		int time = 0;
		
		System.out.print(startingVertex+" ⟶ "); // DA TOGLIERE
		visit.setColor(startingVertex, Color.GRAY);
		visit.setStartTime(startingVertex, time++);
		visit.setDistance(startingVertex, 0.0);
		
		for (int v : getAdjacent(startingVertex)) {
			if (visit.getColor(v) == Color.WHITE) {
				visit.setParent(v, startingVertex);
				visit.setDistance(v, visit.getDistance(startingVertex) + 1.0);
				visit.setColor(v, Color.GRAY);
				DFSUtil(v, visit);
			}
		}
		visit.setColor(startingVertex, Color.BLACK);
		visit.setEndTime(startingVertex, time++);
		
		return visit;
	}
	
	/**
	 * Utility function for TOT-DFS algorithm.
	 * @param sourceVertex
	 * @param visit
	 */
	private void DFSTotUtil(int sourceVertex, VisitForest visit) {
		// TODO Auto-generated method stub
		visit.setColor(sourceVertex, Color.GRAY);
		System.out.print(sourceVertex+" ⟶ ");// DA TOGLIERE
		
		for (int i = 0; i < size(); i++) {
			if (visit.getColor(i) == Color.WHITE) {
				DFSTotUtil(i, visit);
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
				DFSTotUtil(i, visit);
			}
		}
		return visit;
	}

	@Override /* MAYBE IS WRONG, I DON'T KNOW */
	public VisitForest getDFSTOTForest(int[] vertexOrdering)
			throws UnsupportedOperationException, IllegalArgumentException {
		// TODO Auto-generated method stub
		for (int i = 0; i < vertexOrdering.length; i++) {
			if (!containsVertex(vertexOrdering[i])) {
				throw new IllegalArgumentException("\n The vertex ("+vertexOrdering[i]+") it is not a valid argument. ");
			}
		}
		VisitForest visit = new VisitForest(this, VisitType.DFS_TOT);
		
		for (int i = 0; i < vertexOrdering.length; i++) {
			for (int j = vertexOrdering[i]; j < vertexOrdering.length; j++) {
				if (visit.getColor(j) == Color.WHITE) {
					DFSTotUtil(j, visit);
				}
			}
		}
		return visit;
	}
	
	/**
	 * Utility function to find topological sort.
	 * @param v
	 * @param visit
	 * @param stack
	 */
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
		else throw new UnsupportedOperationException("\n Error: this operation is not allowed on this graph. "); 
	}
	
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
			
			AdjMatrixDirWeight trGraph = getTranspose();
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
		else throw new UnsupportedOperationException("\n Error: this operation is not allowed on this graph. ");
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
	AdjMatrixDirWeight getTranspose() {
		// TODO Auto-generated method stub
		AdjMatrixDirWeight graph = new AdjMatrixDirWeight(size());
		
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
			
			for (int i : vertices) {
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
		else throw new UnsupportedOperationException("Error: this type of operation is not supported by this graph. The graph must be Undirected! ");
	}

	@Override
	public double getEdgeWeight(int sourceVertexIndex, int targetVertexIndex)
			throws IllegalArgumentException, NoSuchElementException {
		// TODO Auto-generated method stub
		if (!containsVertex(sourceVertexIndex)) {
			throw new IllegalArgumentException("\n The vertex "+sourceVertexIndex+" it is not a valid argument. ");
		}
		if (!containsVertex(targetVertexIndex)) {
			throw new IllegalArgumentException("\n The vertex "+targetVertexIndex+" it is not a valid argument. ");
		}
		if (!containsEdge(sourceVertexIndex, targetVertexIndex)) {
			throw new NoSuchElementException("\n The edge ["+sourceVertexIndex+"-"+targetVertexIndex+"] does not exist in the graph. ");
		}
		int indexOfSource = findIndex(sourceVertexIndex);
		int indexOfTarget = findIndex(targetVertexIndex);
		double weight = 0.0;
		
		for (int i = 0; i < size(); i++) {
			for (int j = 0; j < size(); j++) {
				if ((i == indexOfSource) && (j == indexOfTarget)) {
					weight = adjMatrix[indexOfSource][indexOfTarget];
				}
			}
		}
		return weight;
	}

	@Override
	public void setEdgeWeight(int sourceVertexIndex, int targetVertexIndex, double weight)
			throws IllegalArgumentException, NoSuchElementException {
		// TODO Auto-generated method stub
		if (!containsVertex(sourceVertexIndex)) {
			throw new IllegalArgumentException("\n The vertex "+sourceVertexIndex+" it is not a valid argument. ");
		}
		if (!containsVertex(targetVertexIndex)) {
			throw new IllegalArgumentException("\n The vertex "+targetVertexIndex+" it is not a valid argument. ");
		}
		if (!containsEdge(sourceVertexIndex, targetVertexIndex)) {
			throw new NoSuchElementException("\n The edge ["+sourceVertexIndex+"-"+targetVertexIndex+"] does not exist in the graph. ");
		}
		int indexOfSource = findIndex(sourceVertexIndex);
		int indexOfTarget = findIndex(targetVertexIndex);
		
		for (int i = 0; i < size(); i++) {
			for (int j = 0; j < size(); j++) {
				if ((i == indexOfSource) && (j == indexOfTarget)) {
					adjMatrix[indexOfSource][indexOfTarget] = weight;
				}
			}
		}
	}
}
