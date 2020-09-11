package upo.graph.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Set;

import org.junit.Test;

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
		
		//System.out.print(" "+graph.getDFSTree(0).toString());
		
		/* Verify parents of vertices */
		/* starting visit from vertex (0) */
		assertThrows(NullPointerException.class, () -> graph.getDFSTree(0).getParent(0));
		assertEquals(0, graph.getDFSTree(0).getParent(1));
		assertEquals(0, graph.getDFSTree(0).getParent(2));
		assertEquals(1, graph.getDFSTree(0).getParent(3));
		graph.getDFSTree(0).getParent(4);
		
		/* starting visit from vertex (1) */
		assertThrows(NullPointerException.class, () -> graph.getDFSTree(1).getParent(1));
		assertThrows(NullPointerException.class, () -> graph.getDFSTree(1).getParent(0));
		assertThrows(NullPointerException.class, () -> graph.getDFSTree(1).getParent(2));
		assertEquals(1, graph.getDFSTree(1).getParent(3));
		assertEquals(3, graph.getDFSTree(1).getParent(4));
		
		/* starting visit from vertex (2) */
		assertThrows(NullPointerException.class, () -> graph.getDFSTree(2).getParent(2));
		assertThrows(NullPointerException.class, () -> graph.getDFSTree(2).getParent(1));
		assertThrows(NullPointerException.class, () -> graph.getDFSTree(2).getParent(0));
		assertThrows(NullPointerException.class, () -> graph.getDFSTree(2).getParent(3));
		assertEquals(2, graph.getDFSTree(2).getParent(4));
		
		/* starting visit from vertex (3) */
		assertThrows(NullPointerException.class, () -> graph.getDFSTree(3).getParent(3));
		assertThrows(NullPointerException.class, () -> graph.getDFSTree(3).getParent(0));
		assertThrows(NullPointerException.class, () -> graph.getDFSTree(3).getParent(1));
		assertThrows(NullPointerException.class, () -> graph.getDFSTree(3).getParent(2));
		assertEquals(3, graph.getDFSTree(3).getParent(4));
		
		/* starting visit from vertex (4) */
		assertThrows(NullPointerException.class, () -> graph.getDFSTree(4).getParent(4));
		assertThrows(NullPointerException.class, () -> graph.getDFSTree(4).getParent(0));
		assertThrows(NullPointerException.class, () -> graph.getDFSTree(4).getParent(1));
		assertThrows(NullPointerException.class, () -> graph.getDFSTree(4).getParent(2));
		assertThrows(NullPointerException.class, () -> graph.getDFSTree(4).getParent(3));
		
		/* Verify the Starting and Ending time of vertices */
		assertEquals(0, graph.getDFSTree(0).getStartTime(0));
		assertEquals(1, graph.getDFSTree(0).getStartTime(1));
		assertEquals(7, graph.getDFSTree(0).getStartTime(2));
		assertEquals(2, graph.getDFSTree(0).getStartTime(3));
		assertEquals(3, graph.getDFSTree(0).getStartTime(4));
		
		assertEquals(9, graph.getDFSTree(0).getEndTime(0));
		assertEquals(6, graph.getDFSTree(0).getEndTime(1));
		assertEquals(8, graph.getDFSTree(0).getEndTime(2));
		assertEquals(5, graph.getDFSTree(0).getEndTime(3));
		assertEquals(4, graph.getDFSTree(0).getEndTime(4));
		
		assertEquals((int)Double.POSITIVE_INFINITY, graph.getDFSTree(4).getStartTime(0));		
	}
	
	@Test
	public void testDFSTOT() {
		
	}
	
	@Test
	public void testDFSTOTOrdering() {
		
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
		
		/* Verify topological Order */
		int[] ord = graph.topologicalSort();
		ArrayList<Integer> topologicalOrder = new ArrayList<>();
		
		for (int i = 0; i < graph.size(); i++) {
			topologicalOrder.add(ord[i]);
		}
		assertEquals("[0, 5, 2, 6, 1, 4, 3]", topologicalOrder.toString());
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
		assertEquals("Error: this type of operation is not supported by this graph. The graph must be Undirected! \n", unsupportedOperation.getMessage());
	}
	
	@Test
	public void testStronglyConnectedComponets() {
		
		/* Initialize graph for testing */
		AdjMatrixDirWeight graph = new AdjMatrixDirWeight(10);
		
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
		
		/* Verify Strongly Connected Components */
		Set<Set<Integer>> stronglyConnectedSetComponets = graph.stronglyConnectedComponents();
		assertEquals("[[8, 9], [7], [0, 1, 4, 5], [2, 3, 6]]", stronglyConnectedSetComponets.toString());
	}
}
