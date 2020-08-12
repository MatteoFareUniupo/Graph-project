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
		visit.setDistance(startingVertex, 0);
		
		queue.add(startingVertex);
		
		while (queue.size() != 0)
		{	
			startingVertex = queue.poll();
			System.out.print(startingVertex+" ⟶ ");
			
			for (int i : getAdjacent(startingVertex)) {  //  (int i = 0; i < size(); i++) così esplora tutti i vertici
				if (visit.getColor(i) == Color.WHITE) { 
					visit.setColor(i, Color.GRAY);
					visit.setParent(i, startingVertex);
					visit.setDistance(i, startingVertex + 1);
					queue.add(i);
				}
			}
			visit.setColor(startingVertex, Color.BLACK);
		}
		System.out.print("end.");
		return visit;
	}

	@Override
	public VisitForest getDFSTree(int startingVertex) throws UnsupportedOperationException, IllegalArgumentException {
		// TODO Auto-generated method stub
		VisitForest visit = new VisitForest(this, VisitType.DFS);
		System.out.print(startingVertex+" ⟶ ");
		visit.setColor(startingVertex, Color.GRAY);
		
		for (int v : getAdjacent(startingVertex)) {
			if (visit.getColor(v) == Color.WHITE) {
				visit.setColor(v, Color.GRAY);
				visit.setParent(v, startingVertex);
				getDFSTree(v);
			}
		}
		visit.setColor(startingVertex, Color.BLACK);
		return visit;
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
					DFSUtil(j, visit);
				}
			}
		}
		
		return visit;
	}
	
	private void topologicalSortUtil(int v, boolean[] visited, Stack<Integer> stack) {
		// TODO Auto-generated method stub
		visited[v] = true;
		int i;
		
		Iterator<Integer> it = getAdjacent(v).iterator();
		while(it.hasNext()) {
			i = it.next();
			if (!visited[i]) {
				topologicalSortUtil(i, visited, stack);
			}
		}
		stack.push(v);	
	}

	@Override
	public int[] topologicalSort() throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		if (isDAG()) {
			
			Stack<Integer> stack = new Stack<>();
			
			int[] ord = new int[size()];
 			boolean[] visited = new boolean[size()];
 			
 			int pos = 0;
 			int j;
 			
 			for (int i = 0; i < size(); i++) {
				visited[i] = false;
			}
 			
 			for (int i = 0; i < size(); i++) {
				if (visited[i] == false) {
					topologicalSortUtil(i, visited, stack);
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
			return null;
		}
		else throw new UnsupportedOperationException("\n Error: this operation is not allowed on this graph. ");
	}

	@Override
	public Set<Set<Integer>> connectedComponents() throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		if (!isDirected()) {
			return null;
		}
		else throw new UnsupportedOperationException("\n Error: this operation is not allowed on this graph. ");
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
