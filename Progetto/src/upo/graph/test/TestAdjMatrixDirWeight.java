package upo.graph.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import org.junit.Test;

import upo.graph.base.WeightedGraph;
import upo.graph.implementation.AdjMatrixDirWeight;

public class TestAdjMatrixDirWeight {
	
	/* NoSuchElementException */
	private NoSuchElementException noSuchElement;
	
	/* IllegalArgumentException */
	private IllegalArgumentException illegalArgument;
	
	/* UnsupportedOperationException */
	private UnsupportedOperationException unsupportedOperation;
	
	@Test /* test existence of vertices in the created graph */
	public void testVertex() {
		
		/* Initialize graph for testing */
		AdjMatrixDirWeight graph = new AdjMatrixDirWeight(5);
		
		/* Verify graph size */
		assertEquals(graph.size(), 5); 
		
		/*Verify vertices existence */
		assertEquals(graph.containsVertex(0), true);
		assertEquals(graph.containsVertex(1), true);
		assertEquals(graph.containsVertex(2), true);
		assertEquals(graph.containsVertex(3), true);
		assertEquals(graph.containsVertex(4), true);
		assertEquals(graph.containsVertex(5), false);
		assertEquals(graph.containsVertex(6), false);
		assertEquals(graph.containsVertex(10), false);
		
		/* Remove a vertex */
		graph.removeVertex(4);
		
		/* Verify graph size */
		assertEquals(graph.size(), 4);
		
		/*Verify vertices existence */
		assertEquals(graph.containsVertex(0), true);
		assertEquals(graph.containsVertex(1), true);
		assertEquals(graph.containsVertex(2), true);
		assertEquals(graph.containsVertex(3), true);
		assertEquals(graph.containsVertex(4), false);
		
		/* Remove a vertex */
		graph.removeVertex(2);
		
		/* Verify graph size */
		assertEquals(graph.size(), 3);
		
		/*Verify vertices existence */
		assertEquals(graph.containsVertex(0), true);
		assertEquals(graph.containsVertex(1), true);
		assertEquals(graph.containsVertex(2), true);
		assertEquals(graph.containsVertex(3), false);
		assertEquals(graph.containsVertex(4), false);
		
		/* Try to remove a vertex not present in the graph */
		/* Verify Exception */
		noSuchElement = assertThrows(NoSuchElementException.class, () -> graph.removeVertex(3));
		assertEquals("Error: vertex (3) does not exist! \n", noSuchElement.getMessage());
		
		noSuchElement = assertThrows(NoSuchElementException.class, () -> graph.removeVertex(10));
		assertEquals("Error: vertex (10) does not exist! \n", noSuchElement.getMessage());
	}
	
	@Test
	public void testEdge() {
		
		/* Initialize graph for testing */
		AdjMatrixDirWeight graph = new AdjMatrixDirWeight(4);
		
		/* Verify graph size */
		assertEquals(graph.size(), 4);
		
		/* Add Edges to the graph */
		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(0, 3);
		graph.addEdge(1, 2);
		graph.addEdge(2, 3);
		
		/* Try to create an edge with a vertex not present in the graph */
		/* Verify Exception */
		illegalArgument = assertThrows(IllegalArgumentException.class, () -> graph.addEdge(0, 4));
		assertEquals("Error: vertex (4) does not exist! \n", illegalArgument.getMessage());
		
		illegalArgument = assertThrows(IllegalArgumentException.class, () -> graph.addEdge(5, 0));
		assertEquals("Error: vertex (5) does not exist! \n", illegalArgument.getMessage());
		
		illegalArgument = assertThrows(IllegalArgumentException.class, () -> graph.addEdge(4, 5));
		assertEquals("Error: vertices (4) and (5) do not exist! \n", illegalArgument.getMessage());
		
		/* Verify edge existence */
		assertEquals(graph.containsEdge(0, 1), true);
		assertEquals(graph.containsEdge(0, 2), true);
		assertEquals(graph.containsEdge(0, 3), true);
		assertEquals(graph.containsEdge(1, 2), true);
		assertEquals(graph.containsEdge(2, 3), true);
		
		/* the graph is not direct, so [x-y] and [y-x] are two different edges */
		assertEquals(graph.containsEdge(1, 0), false);
		assertEquals(graph.containsEdge(2, 0), false);
		assertEquals(graph.containsEdge(3, 0), false);
		assertEquals(graph.containsEdge(2, 1), false);
		assertEquals(graph.containsEdge(3, 2), false);
		
		/* Try to look for an edge that doesn't exist */
		/* Verify Exception */
		illegalArgument = assertThrows(IllegalArgumentException.class, () -> graph.containsEdge(0, 4));
		assertEquals("Error: vertex (4) does not exist! \n", illegalArgument.getMessage());
		
		illegalArgument = assertThrows(IllegalArgumentException.class, () -> graph.containsEdge(5, 0));
		assertEquals("Error: vertex (5) does not exist! \n", illegalArgument.getMessage());
		
		illegalArgument = assertThrows(IllegalArgumentException.class, () -> graph.containsEdge(4, 5));
		assertEquals("Error: vertices (4) and (5) do not exist! \n", illegalArgument.getMessage());
		
		/* Remove an edge*/
		graph.removeEdge(0, 1);
		
		/* Add an Edge */
		graph.addEdge(1, 0);
		
		/* Verify that [0-1] is not anymore */
		assertEquals(graph.containsEdge(0, 1), false);
		assertEquals(graph.containsEdge(1, 0), true);
		
		/* Delete a vertex belonging to an arc and verify that this arc is removed */
		graph.removeVertex(2);
	
		assertEquals(graph.containsEdge(1, 2), false);
		
		illegalArgument = assertThrows(IllegalArgumentException.class, () -> graph.containsEdge(2, 3));
		assertEquals("Error: vertex (3) does not exist! \n", illegalArgument.getMessage());
	}
	
	@Test
	public void testAdjacency() {
		
		/* Initialize graph for testing */
		AdjMatrixDirWeight graph = new AdjMatrixDirWeight(6);
		
		/* Add Edges to the graph */
		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(0, 3);
		graph.addEdge(1, 2);
		graph.addEdge(1, 5);
		graph.addEdge(2, 3);
		graph.addEdge(5, 0);
		
		/* Verify adjacency between vertices */
		assertEquals(graph.isAdjacent(0, 1), true);
		assertEquals(graph.isAdjacent(0, 2), true);
		assertEquals(graph.isAdjacent(0, 3), true);
		assertEquals(graph.isAdjacent(1, 2), true);
		assertEquals(graph.isAdjacent(1, 5), true);
		assertEquals(graph.isAdjacent(2, 3), true);
		assertEquals(graph.isAdjacent(5, 0), true);
		assertEquals(graph.isAdjacent(0, 4), false);
		
		assertEquals(graph.isAdjacent(1, 0), false);
		assertEquals(graph.isAdjacent(2, 0), false);
		assertEquals(graph.isAdjacent(3, 0), false);
		assertEquals(graph.isAdjacent(2, 1), false);
		assertEquals(graph.isAdjacent(5, 1), false);
		assertEquals(graph.isAdjacent(3, 2), false);
		assertEquals(graph.isAdjacent(0, 5), false);
		
		/* Try to look for a vertex that doesn't exist */
		/* Verify Exception */
		illegalArgument = assertThrows(IllegalArgumentException.class, () -> graph.isAdjacent(0, 6));
		assertEquals("Error: vertex (6) does not exist! \n", illegalArgument.getMessage());
		
		illegalArgument = assertThrows(IllegalArgumentException.class, () -> graph.isAdjacent(7, 2));
		assertEquals("Error: vertex (7) does not exist! \n", illegalArgument.getMessage());
		
		illegalArgument = assertThrows(IllegalArgumentException.class, () -> graph.isAdjacent(8, 9));
		assertEquals("Error: vertices (9) and (8) do not exist! \n", illegalArgument.getMessage());
		
		/* Verify adjacency list */
		Set<Integer> set_0 = graph.getAdjacent(0);
		Set<Integer> set_1 = graph.getAdjacent(1);
		Set<Integer> set_2 = graph.getAdjacent(2);
		Set<Integer> set_3 = graph.getAdjacent(3);
		Set<Integer> set_4 = graph.getAdjacent(4);
		Set<Integer> set_5 = graph.getAdjacent(5);
		
		assertEquals("[1, 2, 3]", set_0.toString());
		assertEquals("[2, 5]", set_1.toString());
		assertEquals("[3]", set_2.toString());
		assertEquals("[]", set_3.toString());
		assertEquals("[]", set_4.toString());
		assertEquals("[0]", set_5.toString());
		
		/* Try to look for a vertex that doesn't exist */
		/* Verify Exception */
		illegalArgument = assertThrows(IllegalArgumentException.class, () -> graph.getAdjacent(10));
		assertEquals("Error: vertex (10) does not exist! \n", illegalArgument.getMessage());
	}
	
	@Test
	public void isDAG() {
		
		/* Initialize graph for testing */
		AdjMatrixDirWeight graph = new AdjMatrixDirWeight(5);
		
		/* Add Edges to the graph */
		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(3, 2);
		graph.addEdge(3, 4);
		
		/*Verify if graph is direct */
		assertEquals(graph.isDirected(), true);
		
		/* Verify if graph have a cycle */ 
		assertEquals(graph.isCyclic(), false);
		
		/* Verify if graph is a DAG*/
		assertEquals(graph.isDAG(), true);
		
		/* Add some edges to create a cycle */
		graph.addEdge(1, 3);
		graph.addEdge(4, 1);
		
		/* Verify if graph have a cycle */ 
		assertEquals(graph.isCyclic(), true);
		
		/* Verify if graph is a DAG*/
		assertEquals(graph.isDAG(), false);
	}
	
	@Test
	public void testBFS() {
		
		/* Initialize graph for testing */
		AdjMatrixDirWeight graph = new AdjMatrixDirWeight(8);
		
		/* Add Edges to the graph */
		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(1, 2);
		graph.addEdge(1, 3);
		graph.addEdge(2, 3);
		graph.addEdge(2, 5);
		graph.addEdge(3, 4);
		graph.addEdge(7, 6);
		
		/* Verify that BFS is not supported for weighted graph */
		assertThrows(UnsupportedOperationException.class, () -> graph.getBFSTree(0));	
		
	}
	
	@Test
	public void testDFS() {
		
		/* Initialize graph for testing */
		AdjMatrixDirWeight graph = new AdjMatrixDirWeight(5);
		
		/* Add Edges to the graph */
		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(0, 3);
		graph.addEdge(1, 3);
		graph.addEdge(1, 4);
		graph.addEdge(2, 4);
		graph.addEdge(3, 4);
		
		assertThrows(UnsupportedOperationException.class, () -> graph.getDFSTree(0));	
	}
	
	@Test
	public void testDFSTOT() {
		
		/* Initialize graph for testing */
		AdjMatrixDirWeight graph = new AdjMatrixDirWeight(8);
		
		/* Add Edges to the graph */
		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(1, 2);
		graph.addEdge(1, 3);
		graph.addEdge(2, 3);
		graph.addEdge(2, 5);
		graph.addEdge(3, 4);
		graph.addEdge(7, 6);
		
		/* Verify that DFS is not supported for weighted graph */
		assertThrows(UnsupportedOperationException.class, () -> graph.getDFSTOTForest(0));
	}
	
	@Test
	public void testDFSTOTOrdering() {
		
		/* Initialize graph for testing */
		AdjMatrixDirWeight graph = new AdjMatrixDirWeight(8);
		
		/* Add Edges to the graph */
		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(1, 2);
		graph.addEdge(1, 3);
		graph.addEdge(2, 3);
		graph.addEdge(2, 5);
		graph.addEdge(3, 4);
		graph.addEdge(7, 6);
		
		/* Verify that DFS is not supported for weighted graph */
		assertThrows(UnsupportedOperationException.class, () -> graph.getDFSTOTForest(0));
	}
	
	@Test
	public void testTopologicalSort() {
		
		/* Initialize graph for testing */
		AdjMatrixDirWeight graph = new AdjMatrixDirWeight(7);
		
		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(0, 5);
		graph.addEdge(1, 4);
		graph.addEdge(2, 1);
		graph.addEdge(2, 3);
		graph.addEdge(2, 4);
		graph.addEdge(2, 6);
		graph.addEdge(4, 3);
		graph.addEdge(5, 2);
		graph.addEdge(5, 6);
		graph.addEdge(6, 3);
		
//		/* Verify topological Order */
//		int[] ord = graph.topologicalSort();
//		ArrayList<Integer> topologicalOrder = new ArrayList<>();
//		
//		for (int i = 0; i < graph.size(); i++) {
//			topologicalOrder.add(ord[i]);
//		}
//		assertEquals("[0, 5, 2, 6, 1, 4, 3]", topologicalOrder.toString());
		
		unsupportedOperation = assertThrows(UnsupportedOperationException.class, () -> graph.connectedComponents());
		assertEquals("Error: this type of operation is not supported by this graph! \n", unsupportedOperation.getMessage());
	}
	
	@Test
	public void testConnectedComponets() {
		
		/* Initialize graph for testing */
		AdjMatrixDirWeight graph = new AdjMatrixDirWeight(5);
		
		/* Add Edges to the graph */
		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(3, 2);
		graph.addEdge(3, 4);
		
		unsupportedOperation = assertThrows(UnsupportedOperationException.class, () -> graph.connectedComponents());
		assertEquals("Error: this type of operation is not supported by this graph! \n", unsupportedOperation.getMessage());
	}
	
	@Test
	public void testStronglyConnectedComponets() {
		
		/* Initialize graph for testing */
		AdjMatrixDirWeight graph = new AdjMatrixDirWeight(10);
		
		/* Add Edges */
		graph.addEdge(0, 4);
		graph.addEdge(0, 5);
		graph.addEdge(1, 0);
		graph.addEdge(2, 1);
		graph.addEdge(2, 6);
		graph.addEdge(2, 3);
		graph.addEdge(3, 2);
		graph.addEdge(4, 0);
		graph.addEdge(4, 7);
		graph.addEdge(5, 1);
		graph.addEdge(5, 4);
		graph.addEdge(6, 2);
		graph.addEdge(6, 5);
		graph.addEdge(6, 8);
		graph.addEdge(8, 7);
		graph.addEdge(8, 9);
		graph.addEdge(9, 8);
		
//		/* Verify Strongly Connected Components */
//		Set<Set<Integer>> stronglyConnectedSetComponets = graph.stronglyConnectedComponents();
//		assertEquals("[[8, 9], [7], [0, 1, 4, 5], [2, 3, 6]]", stronglyConnectedSetComponets.toString());

		unsupportedOperation = assertThrows(UnsupportedOperationException.class, () -> graph.stronglyConnectedComponents());
		assertEquals("Error: this type of operation is not supported by this graph! \n", unsupportedOperation.getMessage());
	}
	
	@Test
	public void testWeight() {
		
		/* Initialize graph for testing */
		AdjMatrixDirWeight graph = new AdjMatrixDirWeight(5);
		
		/* Add Edges */
		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(1, 2);
		graph.addEdge(2, 3);
		graph.addEdge(2, 4);
		graph.addEdge(3, 4);
		
		/* Verify that standard weight is 1.0 */
		assertEquals(1.0, graph.getEdgeWeight(0, 1), 0.00);
		assertEquals(1.0, graph.getEdgeWeight(0, 2), 0.00);
		assertEquals(1.0, graph.getEdgeWeight(1, 2), 0.00);
		assertEquals(1.0, graph.getEdgeWeight(2, 3), 0.00);
		assertEquals(1.0, graph.getEdgeWeight(2, 4), 0.00);
		assertEquals(1.0, graph.getEdgeWeight(3, 4), 0.00);
		
		/* Assign weights to edges */
		graph.setEdgeWeight(0, 1, 2.0);
		graph.setEdgeWeight(0, 2, 7.0);
		graph.setEdgeWeight(1, 2, 4.0);
		graph.setEdgeWeight(2, 3, 1.0);
		graph.setEdgeWeight(2, 4, 4.0);
		graph.setEdgeWeight(3, 4, 2.0);
		
		/* Verify edges weight */
		assertEquals(2.0, graph.getEdgeWeight(0, 1), 0.00);
		assertEquals(7.0, graph.getEdgeWeight(0, 2), 0.00);
		assertEquals(4.0, graph.getEdgeWeight(1, 2), 0.00);
		assertEquals(1.0, graph.getEdgeWeight(2, 3), 0.00);
		assertEquals(4.0, graph.getEdgeWeight(2, 4), 0.00);
		assertEquals(2.0, graph.getEdgeWeight(3, 4), 0.00);
	}
	
	@Test
	public void testFloydWarshall() {
		
		/* Initialize graph for testing */
		AdjMatrixDirWeight graph = new AdjMatrixDirWeight(4);
		
		/* Add Edges */
		graph.addEdge(0, 1);
		graph.addEdge(0, 3);
		graph.addEdge(1, 2);
		graph.addEdge(3, 1);
		graph.addEdge(3, 2);
		
		/* Set Weight of the Edges */
		graph.setEdgeWeight(0, 1, 4.0);
		graph.setEdgeWeight(0, 3, -5.0);
		graph.setEdgeWeight(1, 2, -1.0);
		graph.setEdgeWeight(3, 1, 0.0);
		graph.setEdgeWeight(3, 2, 2.0);
		
		/* Initialize WeightedGraph for testing */
		WeightedGraph floydWarshall_01 = graph.getFloydWarshallShortestPaths();
		
		System.out.print("\n FIRST GRAPH - Before Floyd-Warshall \n");
		
		/* Print the adjacency matrix of the graph */
		showAsMatrix((AdjMatrixDirWeight) graph);
		
		System.out.print("\n FIRST GRAPH -  After Floyd-Warshall \n");
		
		/* Print the matrix managed by Floyd-Warshall algorithm */
		showAsMatrix((AdjMatrixDirWeight) floydWarshall_01);
		
		/* Initialize other graph for testing negative cycle */
		AdjMatrixDirWeight otherGraph = new AdjMatrixDirWeight(8);
		
		/* Add Edges */
		otherGraph.addEdge(0, 1);
		otherGraph.addEdge(0, 2);
		otherGraph.addEdge(2, 4);
		otherGraph.addEdge(2, 5);
		otherGraph.addEdge(3, 0);
		otherGraph.addEdge(3, 2);
		otherGraph.addEdge(4, 3);
		otherGraph.addEdge(4, 6);
		otherGraph.addEdge(5, 1);
		otherGraph.addEdge(5, 4);
		otherGraph.addEdge(6, 5);
		otherGraph.addEdge(6, 7);
		otherGraph.addEdge(7, 4);
		
		/* Set Weight of the Edges */
		otherGraph.setEdgeWeight(0, 1, 4.0);
		otherGraph.setEdgeWeight(0, 2, 4.0);
		otherGraph.setEdgeWeight(2, 4, 4.0);
		otherGraph.setEdgeWeight(2, 5, -2.0);
		otherGraph.setEdgeWeight(3, 0, 3.0);
		otherGraph.setEdgeWeight(3, 2, 2.0);
		otherGraph.setEdgeWeight(4, 3, 1.0);
		otherGraph.setEdgeWeight(4, 6, -2.0);
		otherGraph.setEdgeWeight(5, 1, 3.0);
		otherGraph.setEdgeWeight(5, 4, -3.0);
		otherGraph.setEdgeWeight(6, 5, 2.0);
		otherGraph.setEdgeWeight(6, 7, 2.0);
		otherGraph.setEdgeWeight(7, 4, -2.0);
		
		System.out.print("\n SECOND GRAPH - Before Floyd-Warshall \n");
		
		showAsMatrix(otherGraph);
		
		assertThrows(UnsupportedOperationException.class, () -> otherGraph.getFloydWarshallShortestPaths());
	}
	/* print graph as a matrix */
	private static String showAsMatrix(AdjMatrixDirWeight graph) {
		
		String str = " ------";
		for (int i = 0; i < graph.size(); i++) {
			str += "----";
		}
		str += "-\n";
		str += " | X |";
		
		for (int i = 0; i < graph.size(); i++) {
			str += "  " + graph.vertices[i] + " ";
		}
		str += " ";
		str += "|\n";
		str += " ------";
		
		for (int i = 0; i < graph.size(); i++) {
			str += "----";
		}
		str += "-\n";
		
		for (int i = 0; i < graph.adjMatrix.length; i++) {
			str += " | " + graph.vertices[i] + " | ";
			for (int j = 0; j < graph.adjMatrix[i].length; j++) {
				if ((graph.adjMatrix[i][j] == Double.POSITIVE_INFINITY) && (i != j)) {
					str += "INF ";
				}
				else str += graph.adjMatrix[i][j] + " ";
			}
			str += "|\n";
		}
		str += " ------";
		
		for (int i = 0; i < graph.size(); i++) {
			str += "----";
		}
		str += "-\n";
		
		System.out.print(str);
		return str;
	}
	
	@Test
	public void testEquals() {
		
		/* Initialize graph for testing */
		AdjMatrixDirWeight firstGraph = new AdjMatrixDirWeight(4);
		
		Set<Integer> firstSet = new HashSet<>();
		
		/* Add Edges to the graph */
		firstGraph.addEdge(0, 1);
		firstGraph.addEdge(0, 2);
		firstGraph.addEdge(0, 3);
		firstGraph.addEdge(1, 2);
		firstGraph.addEdge(2, 3);
		
		for (int i = 0; i < firstGraph.size(); i++) {
			firstSet.addAll(firstGraph.getAdjacent(i));
		}
		
		/* Initialize graph for testing */
		AdjMatrixDirWeight secondGraph = new AdjMatrixDirWeight(4);
		
		Set<Integer> secondSet = new HashSet<>();
		
		/* Add Edges to the graph */
		secondGraph.addEdge(0, 1);
		secondGraph.addEdge(0, 2);
		secondGraph.addEdge(0, 3);
		secondGraph.addEdge(1, 2);
		secondGraph.addEdge(2, 3);
		
		for (int i = 0; i < secondGraph.size(); i++) {
			secondSet.addAll(secondGraph.getAdjacent(i));
		}
		
		/* Verify that firstGraph is equal to secondGraph and vice versa */
		assertEquals(true, firstSet.equals(secondSet));
		assertEquals(true, secondSet.equals(firstSet));
		
		/* Initialize graph for testing */
		AdjMatrixDirWeight thirdGraph = new AdjMatrixDirWeight(7);
		
		Set<Integer> thirdSet = new HashSet<>();
		
		/* Add Edges to the graph */
		thirdGraph.addEdge(0, 1);
		thirdGraph.addEdge(0, 2);
		thirdGraph.addEdge(1, 3);
		thirdGraph.addEdge(2, 5);
		thirdGraph.addEdge(4, 3);
		
		for (int i = 0; i < thirdGraph.size(); i++) {
			thirdSet.addAll(thirdGraph.getAdjacent(i));
		}
		
		/* Verify that thirdGraph is NOT equal to secondGraph or firstGraph */
		assertEquals(false, firstSet.equals(thirdSet));
		assertEquals(false, thirdSet.equals(secondSet));
	}
}
