package upo.graph.implementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.Stack;

import upo.graph.base.*;
import upo.graph.base.VisitForest.Color;
import upo.graph.base.VisitForest.VisitType;

/**
 * Implementazione mediante <strong>liste di adiacenza</strong> di un grafo <strong>non orientato pesato</strong>.
 * 
 * @author Matteo Faré 20014560
 *
 */
public class AdjListUndirWeight implements WeightedGraph{
	
	static class Edge {
		
		private int vertex;
		
		private double weight;
		
		public Edge(int v, double w) {
			vertex = v;
			weight = w;
		}
		
		public int getVertex() {
			return vertex;
		}

		public void setVertex(int vertex) {
			this.vertex = vertex;
		}

		public double getWeight() {
			return weight;
		}

		public void setWeight(double weight) {
			this.weight = weight;
		}
		
		public String toString(){
			
			return "( "+ vertex + ", " + weight + " )";
		}
	}
	
	public HashMap<Integer, ArrayList<Edge>> adjList;
	public ArrayList<Integer> vertexList = new ArrayList<Integer>();
	
	private int n = size();
	
	public AdjListUndirWeight(int dim) {
	
		adjList = new HashMap<Integer, ArrayList<Edge>>();
		vertexList = new ArrayList<Integer>();
		
		this.n = dim;
		
		for (int i = 0; i < n; i++) {
			vertexList.add(i);
			adjList.put(i, new ArrayList<Edge>());	
		}
	}

	@Override
	public int addVertex() {
		// TODO Auto-generated method stub
		int n = size();
		
		ArrayList<Edge> tmp = new ArrayList<>();
		
		adjList.put(n, tmp);
		vertexList.add(n);
		
		return vertexList.get(n);
	}

	@Override
	public boolean containsVertex(int index) {
		// TODO Auto-generated method stub
		
		for (int v : vertexList) {
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
		else {
			
			for (int i = 0; i < vertexList.size(); i++) {

				if (containsEdge(i, index)) {
					removeEdge(i, index);
				}
			}
			
			for (int i = 0; i < vertexList.size(); i++) {
				if (i == index) {
					vertexList.remove(index);
				}
			}
			for (int j = index; j < size(); j++) { // restore the index number progression.
				vertexList.set(j, j);
			}
		}
		
		
	}

	@Override
	public void addEdge(int sourceVertexIndex, int targetVertexIndex) throws IllegalArgumentException {
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
		else {
			
			if (!adjList.containsKey(sourceVertexIndex)) {
				
				ArrayList<Edge> tmp = new ArrayList<>();
				tmp.add(new Edge(targetVertexIndex, defaultEdgeWeight));
				
				adjList.put(sourceVertexIndex, tmp);		
			}
			
			if (!adjList.containsKey(targetVertexIndex)) {
				
				ArrayList<Edge> tmp = new ArrayList<>();
				tmp.add(new Edge(sourceVertexIndex, defaultEdgeWeight));
				
				adjList.put(targetVertexIndex, tmp);
			}
			adjList.get(sourceVertexIndex).add(new Edge(targetVertexIndex, defaultEdgeWeight));
			adjList.get(targetVertexIndex).add(new Edge(sourceVertexIndex, defaultEdgeWeight));
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
		
		if (containsVertex(sourceVertexIndex)) {
			if (adjList.containsKey(sourceVertexIndex)) {
				
				for (Edge e : adjList.get(sourceVertexIndex)) {
					if ((e.getVertex() == targetVertexIndex)) { 
						return true;
					}
				}
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
		else if (adjList.containsKey(sourceVertexIndex)) { // if (containsEdge(sourceVertexIndex, targetVertexIndex))
			
			ArrayList<Edge> tmp1 = adjList.get(sourceVertexIndex);
			Iterator<Edge> it1 = tmp1.iterator();
			
			while (it1.hasNext()) {
				Edge edge = it1.next();
				if (edge.getVertex() == targetVertexIndex) {
					it1.remove();
				}
			}
			
			ArrayList<Edge> tmp2 = adjList.get(targetVertexIndex);
			Iterator<Edge> it2 = tmp2.iterator();
			
			while (it2.hasNext()) {
				Edge edge = it2.next();
				if (edge.getVertex() == sourceVertexIndex) {
					it2.remove();
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
			for (int v = 0; v < size(); v++) {
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
		int numOfVertices = vertexList.size();
	
		return numOfVertices;
	}

	@Override
	public boolean isDirected() {
		// TODO Auto-generated method stub
		for (int v : vertexList) {
			getAdjacent(v); //adjList.get(v);
		}
		return false;
	}
	
	private boolean checkCycle(int currentVertexIndex, VisitForest visitForest) {
		// TODO Auto-generated method stub
		visitForest.setColor(vertexList.get(currentVertexIndex), Color.GRAY);
		
		for(int i : vertexList) {
			if (containsEdge(currentVertexIndex, i)) {
				if (visitForest.getColor(vertexList.get(i)) == Color.WHITE) {
					visitForest.setParent(vertexList.get(i), currentVertexIndex);
					if (checkCycle(i, visitForest)) {
						return true;
					}
				}
				else if (visitForest.getParent(currentVertexIndex) != i) {
					return true;
				}
			}
		}
		visitForest.setColor(vertexList.get(currentVertexIndex), Color.BLACK);
		return false;
	}

	@Override 
	public boolean isCyclic() {
		// TODO Auto-generated method stub
		
		VisitForest visit = new VisitForest(this, VisitType.DFS);
		
		/* INITIALIZE */
		for(int i : vertexList) {
			visit.setColor(i, Color.WHITE);
		}
		
		for(int j : vertexList) {
			if ((visit.getColor(j) == Color.WHITE) && (checkCycle(j, visit))) {
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
		return null;
	}

	@Override
	public VisitForest getDFSTree(int startingVertex) throws UnsupportedOperationException, IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VisitForest getDFSTOTForest(int startingVertex)
			throws UnsupportedOperationException, IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
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
		if (!isDirected()) {
			
			VisitForest visit = new VisitForest(this, VisitType.DFS_TOT);
			
			Set<Set<Integer>> setOfCC = new HashSet<>();
			Set<Integer> visitedSet = new HashSet<>();
			
			for (int i : vertexList) {
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
	
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		boolean verify = false;
		Set<Integer> adjSet = new HashSet<>();
		
		for(int i = 0; i < adjList.size(); i++) {
			adjSet.addAll(getAdjacent(i));
		}
		
		if (adjSet.contains(o)) {
			verify = true;
		}
		return verify;
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
		
		double weight = defaultEdgeWeight;
		
		for (Edge e : adjList.get(sourceVertexIndex)) {
			if (e.getVertex() == targetVertexIndex) {
				weight = e.getWeight();
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
		
		for (Edge e : adjList.get(sourceVertexIndex)) {
			if (e.getVertex() == targetVertexIndex) {
				e.setWeight(weight);
			}
		}
		
		for (Edge e : adjList.get(targetVertexIndex)) {
			if (e.getVertex() == sourceVertexIndex) {
				e.setWeight(weight);
			}
		}
	}

	@Override
	public WeightedGraph getFloydWarshallShortestPaths() throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Error: the implementatio of the graph does not used the matrix format \n");
	}

}