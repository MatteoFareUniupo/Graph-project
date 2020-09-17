package upo.graph.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.NoSuchElementException;
import java.util.Set;
import org.junit.Test;

import upo.graph.implementation.AdjListUndir;

public class TestAdjListUndir {
	
	/* NoSuchElementException */
	private NoSuchElementException noSuchElement;
	
	/* IllegalArgumentException */
	private IllegalArgumentException illegalArgument;
	
	/* UnsupportedOperationException */
	private UnsupportedOperationException unsupportedOperation;
	
	@Test /* test existence of vertices in the created graph */
	public void testVertex() {
		
		/* Initialize graph for testing */
		AdjListUndir graph = new AdjListUndir(5);
		
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
		AdjListUndir graph = new AdjListUndir(4);
		
		/* Add Edges to the graph */
		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(0, 3);
		graph.addEdge(1, 2);
		graph.addEdge(2, 3);
		
		/* Edges [0-1] and [1-0] are the same - print an error */
		graph.addEdge(1, 0); 
		
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
		
		assertEquals(graph.containsEdge(1, 0), true);
		assertEquals(graph.containsEdge(2, 0), true);
		assertEquals(graph.containsEdge(3, 0), true);
		assertEquals(graph.containsEdge(2, 1), true);
		assertEquals(graph.containsEdge(3, 2), true);
		
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
		
		/* Verify that [0-1] is not anymore */
		assertEquals(graph.containsEdge(0, 1), false);
		assertEquals(graph.containsEdge(1, 0), false);
		
		/* Delete a vertex belonging to an arc and verify that this arc is removed */
		graph.removeVertex(1);
		
		assertEquals(graph.containsEdge(1, 2), false);
		assertEquals(graph.containsEdge(2, 1), false);
	}
	
	@Test
	public void testAdjacency() {
		
		/* Initialize graph for testing */
		AdjListUndir graph = new AdjListUndir(4);
		
		/* Add Edges to the graph */
		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(0, 3);
		graph.addEdge(1, 2);
		graph.addEdge(2, 3);
		
		/* Verify adjacency between vertices */
		assertEquals(graph.isAdjacent(1, 0), true);
		assertEquals(graph.isAdjacent(2, 0), true);
		assertEquals(graph.isAdjacent(3, 0), true);
		assertEquals(graph.isAdjacent(2, 1), true);
		assertEquals(graph.isAdjacent(2, 3), true);
		
		assertEquals(graph.isAdjacent(0, 1), true);
		assertEquals(graph.isAdjacent(0, 2), true);
		assertEquals(graph.isAdjacent(0, 3), true);
		assertEquals(graph.isAdjacent(1, 2), true);
		assertEquals(graph.isAdjacent(3, 2), true);
		
		assertEquals(graph.isAdjacent(1, 3), false);
		assertEquals(graph.isAdjacent(3, 1), false);
		
		/* Try to look for a vertex that doesn't exist */
		/* Verify Exception */
		illegalArgument = assertThrows(IllegalArgumentException.class, () -> graph.isAdjacent(0, 5));
		assertEquals("Error: vertex (5) does not exist! \n", illegalArgument.getMessage());
		
		illegalArgument = assertThrows(IllegalArgumentException.class, () -> graph.isAdjacent(6, 2));
		assertEquals("Error: vertex (6) does not exist! \n", illegalArgument.getMessage());
		
		illegalArgument = assertThrows(IllegalArgumentException.class, () -> graph.isAdjacent(7, 10));
		assertEquals("Error: vertices (10) and (7) do not exist! \n", illegalArgument.getMessage());
		
		/* Verify adjacency list */
		Set<Integer> adjOf_0 = graph.getAdjacent(0);
		Set<Integer> adjOf_1 = graph.getAdjacent(1);
		Set<Integer> adjOf_2 = graph.getAdjacent(2);
		Set<Integer> adjOf_3 = graph.getAdjacent(3);
		
		String expected_adjOf_0 = "[1, 2, 3]";
		String expected_adjOf_1 = "[0, 2]";
		String expected_adjOf_2 = "[0, 1, 3]";
		String expected_adjOf_3 = "[0, 2]";
		
		assertEquals(expected_adjOf_0, adjOf_0.toString());
		assertEquals(expected_adjOf_1, adjOf_1.toString());
		assertEquals(expected_adjOf_2, adjOf_2.toString());
		assertEquals(expected_adjOf_3, adjOf_3.toString());
		
		/* Try to look for a vertex that doesn't exist */
		/* Verify Exception */
		noSuchElement = assertThrows(NoSuchElementException.class, () -> graph.getAdjacent(10));
		assertEquals("Error: vertex (10) does not exist! \n", noSuchElement.getMessage());
	}
	
	@Test
	public void testDAG() {
		
		/* Initialize graph for testing */
		AdjListUndir graph = new AdjListUndir(4);
		
		/* Add Edges to the graph */
		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(0, 3);
		graph.addEdge(1, 2);
		graph.addEdge(2, 3);
		
		/*Verify if graph is direct */
		assertEquals(graph.isDirected(), false);
		
		/* Verify if graph have a cycle */ 
		assertEquals(graph.isCyclic(), true);
		
		/* Verify if graph is a DAG*/
		assertEquals(graph.isDAG(), false);
		
		/* Remove edges to remove Cycle */
		graph.removeEdge(1, 2);
		graph.removeEdge(0, 3);
		
		/* Verify if graph have a cycle */ 
		assertEquals(graph.isCyclic(), false);
	}
	
	@Test
	public void testBFS() {
		
		/* Initialize graph for testing */
		AdjListUndir graph = new AdjListUndir(7);
		
		/* Add Edges to the graph */
		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(1, 3);
		graph.addEdge(2, 5);
		graph.addEdge(4, 3);
		graph.addEdge(5, 1);
		
		/* Verify parents of vertices */
		/* starting visit from (0) */
		System.out.print("\n• BFS algorith execution: \n");
		assertThrows(NullPointerException.class, () -> graph.getBFSTree(0).getParent(0));
		System.out.println("end. "); // next-line
		assertEquals(0, graph.getBFSTree(0).getParent(1));
		System.out.println("end. "); // next-line
		assertEquals(0, graph.getBFSTree(0).getParent(2));
		System.out.println("end. "); // next-line
		assertEquals(1, graph.getBFSTree(0).getParent(3));
		System.out.println("end. "); // next-line
		assertEquals(3, graph.getBFSTree(0).getParent(4));
		System.out.println("end. "); // next-line
		assertEquals(1, graph.getBFSTree(0).getParent(5));
		System.out.println("end. "); // next-line
		assertThrows(NullPointerException.class, () -> graph.getBFSTree(0).getParent(6));
		System.out.println("end. "); // next-line
		
		/* starting visit from (1) */
		assertThrows(NullPointerException.class, () -> graph.getBFSTree(1).getParent(1));
		System.out.println("end. "); // next-line
		assertEquals(1, graph.getBFSTree(1).getParent(0));
		System.out.println("end. "); // next-line
		assertEquals(0, graph.getBFSTree(1).getParent(2));
		System.out.println("end. "); // next-line
		assertEquals(1, graph.getBFSTree(1).getParent(3));
		System.out.println("end. "); // next-line
		assertEquals(3, graph.getBFSTree(1).getParent(4));
		System.out.println("end. "); // next-line
		assertEquals(1, graph.getBFSTree(1).getParent(5));
		System.out.println("end. "); // next-line
		assertThrows(NullPointerException.class, () -> graph.getBFSTree(1).getParent(6));
		System.out.println("end. "); // next-line
		
		/* Verify distances of vertices */
		/* starting visit from (0) */
		assertEquals(0.0, graph.getBFSTree(0).getDistance(0), 0.00);
		System.out.println("end. "); // next-line
		assertEquals(1.0, graph.getBFSTree(0).getDistance(1), 0.00);
		System.out.println("end. "); // next-line
		assertEquals(1.0, graph.getBFSTree(0).getDistance(2), 0.00);
		System.out.println("end. "); // next-line
		assertEquals(2.0, graph.getBFSTree(0).getDistance(3), 0.00);
		System.out.println("end. "); // next-line
		assertEquals(3.0, graph.getBFSTree(0).getDistance(4), 0.00);
		System.out.println("end. "); // next-line
		assertEquals(2.0, graph.getBFSTree(0).getDistance(5), 0.00);
		System.out.println("end. "); // next-line
		
		/* starting visit from (1) */
		assertEquals(1.0, graph.getBFSTree(1).getDistance(0), 0.00);
		System.out.println("end. "); // next-line
		assertEquals(0.0, graph.getBFSTree(1).getDistance(1), 0.00);
		System.out.println("end. "); // next-line
		assertEquals(2.0, graph.getBFSTree(1).getDistance(2), 0.00);
		System.out.println("end. "); // next-line
		assertEquals(1.0, graph.getBFSTree(1).getDistance(3), 0.00);
		System.out.println("end. "); // next-line
		assertEquals(2.0, graph.getBFSTree(1).getDistance(4), 0.00);
		System.out.println("end. "); // next-line
		assertEquals(1.0, graph.getBFSTree(1).getDistance(5), 0.00);
		System.out.println("end. "); // next-line
		
		/*Verify colors of vertices at the end of the visit */
		/* starting visit from (0) */
		assertEquals("BLACK", graph.getBFSTree(0).getColor(0).toString());
		System.out.println("end. "); // next-line
		assertEquals("BLACK", graph.getBFSTree(0).getColor(1).toString());
		System.out.println("end. "); // next-line
		assertEquals("BLACK", graph.getBFSTree(0).getColor(2).toString());
		System.out.println("end. "); // next-line
		assertEquals("BLACK", graph.getBFSTree(0).getColor(3).toString());
		System.out.println("end. "); // next-line
		assertEquals("BLACK", graph.getBFSTree(0).getColor(4).toString());
		System.out.println("end. "); // next-line
		assertEquals("BLACK", graph.getBFSTree(0).getColor(5).toString());
		System.out.println("end. "); // next-line
		assertEquals("WHITE", graph.getBFSTree(0).getColor(6).toString());
		System.out.println("end. "); // next-line	
	}
	
	@Test
	public void testDFS() {
		
		/* Initialize graph for testing */
		AdjListUndir graph = new AdjListUndir(7);
		
		/* Add Edges to the graph */
		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(1, 3);
		graph.addEdge(2, 5);
		graph.addEdge(4, 3);
		
		System.out.print("\n• DFS algorith execution: \n");
		/* Verify parents of vertices */
		/* starting visit from (0) */
		assertThrows(NullPointerException.class, () -> graph.getDFSTree(0).getParent(0));
		System.out.println("end. "); // next-line
		assertEquals(0, graph.getDFSTree(0).getParent(1));
		System.out.println("end. "); // next-line
		assertEquals(0, graph.getDFSTree(0).getParent(2));
		System.out.println("end. "); // next-line
		assertEquals(1, graph.getDFSTree(0).getParent(3));
		System.out.println("end. "); // next-line
		assertEquals(3, graph.getDFSTree(0).getParent(4));
		System.out.println("end. "); // next-line
		assertEquals(2, graph.getDFSTree(0).getParent(5));
		System.out.println("end. "); // next-line
		assertThrows(NullPointerException.class, () -> graph.getDFSTree(0).getParent(6));
		System.out.println("end. "); // next-line
		
		/* starting visit from (1) */
		assertThrows(NullPointerException.class, () -> graph.getDFSTree(1).getParent(1));
		System.out.println("end. "); // next-line
		assertEquals(1, graph.getDFSTree(1).getParent(0));
		System.out.println("end. "); // next-line
		assertEquals(0, graph.getDFSTree(1).getParent(2));
		System.out.println("end. "); // next-line
		assertEquals(1, graph.getDFSTree(1).getParent(3));
		System.out.println("end. "); // next-line
		assertEquals(3, graph.getDFSTree(1).getParent(4));
		System.out.println("end. "); // next-line
		assertEquals(2, graph.getDFSTree(1).getParent(5));
		System.out.println("end. "); // next-line
		assertThrows(NullPointerException.class, () -> graph.getDFSTree(1).getParent(6));
		System.out.println("end. "); // next-line
		
		/* Verify the Starting and Ending time of vertices */
		/* starting visit from (0) */
		assertEquals(0, graph.getDFSTree(0).getStartTime(0));
		System.out.println("end. "); // next-line
		assertEquals(1, graph.getDFSTree(0).getStartTime(1));
		System.out.println("end. "); // next-line
		assertEquals(7, graph.getDFSTree(0).getStartTime(2));
		System.out.println("end. "); // next-line
		assertEquals(2, graph.getDFSTree(0).getStartTime(3));
		System.out.println("end. "); // next-line
		assertEquals(3, graph.getDFSTree(0).getStartTime(4));
		System.out.println("end. "); // next-line
		assertEquals(8, graph.getDFSTree(0).getStartTime(5));
		System.out.println("end. "); // next-line
		
		assertEquals(11, graph.getDFSTree(0).getEndTime(0));
		System.out.println("end. "); // next-line
		assertEquals(6, graph.getDFSTree(0).getEndTime(1));
		System.out.println("end. "); // next-line
		assertEquals(10, graph.getDFSTree(0).getEndTime(2));
		System.out.println("end. "); // next-line
		assertEquals(5, graph.getDFSTree(0).getEndTime(3));
		System.out.println("end. "); // next-line
		assertEquals(4, graph.getDFSTree(0).getEndTime(4));
		System.out.println("end. "); // next-line
		assertEquals(9, graph.getDFSTree(0).getEndTime(5));	
		System.out.println("end. "); // next-line
		
		/* Verify vertices color at the end of the visit */
		/* starting visit from (0) */
		assertEquals("BLACK", graph.getDFSTree(0).getColor(0).toString());
		System.out.println("end. "); // next-line
		assertEquals("BLACK", graph.getDFSTree(0).getColor(1).toString());
		System.out.println("end. "); // next-line
		assertEquals("BLACK", graph.getDFSTree(0).getColor(2).toString());
		System.out.println("end. "); // next-line
		assertEquals("BLACK", graph.getDFSTree(0).getColor(3).toString());
		System.out.println("end. "); // next-line
		assertEquals("BLACK", graph.getDFSTree(0).getColor(4).toString());
		System.out.println("end. "); // next-line
		assertEquals("BLACK", graph.getDFSTree(0).getColor(5).toString());
		System.out.println("end. "); // next-line
		assertEquals("WHITE", graph.getDFSTree(0).getColor(6).toString());
		System.out.println("end. "); // next-line
	}
	
	@Test
	public void testDFSTOT() {
		
		/* Initialize graph for testing */
		AdjListUndir graph = new AdjListUndir(8);
		
		/* Add Edges to the graph */
		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(1, 2);
		graph.addEdge(1, 3);
		graph.addEdge(2, 3);
		graph.addEdge(2, 5);
		graph.addEdge(3, 4);
		graph.addEdge(7, 6);
		
		System.out.print("\n• DFS-TOT algorith execution: \n");
		/* Verify parents of vertices */
		/* starting visit from (0) */
		assertThrows(NullPointerException.class, () -> graph.getDFSTOTForest(0).getParent(0));
		System.out.println("end. "); // next-line
		assertEquals(0, graph.getDFSTOTForest(0).getParent(1));
		System.out.println("end. "); // next-line
		assertEquals(1, graph.getDFSTOTForest(0).getParent(2));
		System.out.println("end. "); // next-line
		assertEquals(2, graph.getDFSTOTForest(0).getParent(3));
		System.out.println("end. "); // next-line
		assertEquals(3, graph.getDFSTOTForest(0).getParent(4));
		System.out.println("end. "); // next-line
		assertEquals(2, graph.getDFSTOTForest(0).getParent(5));
		System.out.println("end. "); // next-line
		assertThrows(NullPointerException.class, () -> graph.getDFSTOTForest(0).getParent(6));
		System.out.println("end. "); // next-line
		assertEquals(6, graph.getDFSTOTForest(0).getParent(7));
		System.out.println("end. "); // next-line
		
		/* starting visit from (1) */
		assertThrows(NullPointerException.class, () -> graph.getDFSTOTForest(1).getParent(1));
		System.out.println("end. "); // next-line
		assertEquals(1, graph.getDFSTOTForest(1).getParent(0));
		System.out.println("end. "); // next-line
		assertEquals(0, graph.getDFSTOTForest(1).getParent(2));
		System.out.println("end. "); // next-line
		assertEquals(2, graph.getDFSTOTForest(1).getParent(3));
		System.out.println("end. "); // next-line
		assertEquals(3, graph.getDFSTOTForest(1).getParent(4));
		System.out.println("end. "); // next-line
		assertEquals(2, graph.getDFSTOTForest(1).getParent(5));
		System.out.println("end. "); // next-line
		assertThrows(NullPointerException.class, () -> graph.getDFSTOTForest(1).getParent(6));
		System.out.println("end. "); // next-line
		assertEquals(6, graph.getDFSTOTForest(1).getParent(7));
		System.out.println("end. "); // next-line
		
		/* Verify the Starting and Ending time of vertices */
		/* starting visit from (0) */
		assertEquals(0, graph.getDFSTOTForest(0).getStartTime(0));
		System.out.println("end. "); // next-line
		assertEquals(1, graph.getDFSTOTForest(0).getStartTime(1));
		System.out.println("end. "); // next-line
		assertEquals(2, graph.getDFSTOTForest(0).getStartTime(2));
		System.out.println("end. "); // next-line
		assertEquals(3, graph.getDFSTOTForest(0).getStartTime(3));
		System.out.println("end. "); // next-line
		assertEquals(4, graph.getDFSTOTForest(0).getStartTime(4));
		System.out.println("end. "); // next-line
		assertEquals(7, graph.getDFSTOTForest(0).getStartTime(5));
		System.out.println("end. "); // next-line
		assertEquals(12, graph.getDFSTOTForest(0).getStartTime(6));
		System.out.println("end. "); // next-line
		assertEquals(13, graph.getDFSTOTForest(0).getStartTime(7));
		System.out.println("end. "); // next-line
		
		assertEquals(11, graph.getDFSTOTForest(0).getEndTime(0));
		System.out.println("end. "); // next-line
		assertEquals(10, graph.getDFSTOTForest(0).getEndTime(1));
		System.out.println("end. "); // next-line
		assertEquals(9, graph.getDFSTOTForest(0).getEndTime(2));
		System.out.println("end. "); // next-line
		assertEquals(6, graph.getDFSTOTForest(0).getEndTime(3));
		System.out.println("end. "); // next-line
		assertEquals(5, graph.getDFSTOTForest(0).getEndTime(4));
		System.out.println("end. "); // next-line
		assertEquals(8, graph.getDFSTOTForest(0).getEndTime(5));
		System.out.println("end. "); // next-line
		assertEquals(15, graph.getDFSTOTForest(0).getEndTime(6));
		System.out.println("end. "); // next-line
		assertEquals(14, graph.getDFSTOTForest(0).getEndTime(7));
		System.out.println("end. "); // next-line
		
		/* Verify vertices color at the end of the visit */
		/* starting visit from (0) */
		assertEquals("BLACK", graph.getDFSTOTForest(0).getColor(0).toString());
		System.out.println("end. "); // next-line
		assertEquals("BLACK", graph.getDFSTOTForest(0).getColor(1).toString());
		System.out.println("end. "); // next-line
		assertEquals("BLACK", graph.getDFSTOTForest(0).getColor(2).toString());
		System.out.println("end. "); // next-line
		assertEquals("BLACK", graph.getDFSTOTForest(0).getColor(3).toString());
		System.out.println("end. "); // next-line
		assertEquals("BLACK", graph.getDFSTOTForest(0).getColor(4).toString());
		System.out.println("end. "); // next-line
		assertEquals("BLACK", graph.getDFSTOTForest(0).getColor(5).toString());
		System.out.println("end. "); // next-line
		assertEquals("BLACK", graph.getDFSTOTForest(0).getColor(6).toString());
		System.out.println("end. "); // next-line
		assertEquals("BLACK", graph.getDFSTOTForest(0).getColor(7).toString());	
		System.out.println("end. "); // next-line
	}
	
	@Test
	public void testFollowOrderDFSTOT() {
		
		/* Initialize graph for testing */
		AdjListUndir graph = new AdjListUndir(7);
		
		/* Add Edges to the graph */
		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(1, 3);
		graph.addEdge(2, 5);
		graph.addEdge(4, 3);
		
		int [] order = {1, 0, 2, 3, 4, 5, 6};
		
		System.out.print("\n• TOT-DFS following array { ");
		for (int i = 0; i < order.length; i++) {
			System.out.print(order[i]+" ");
		}
		System.out.print("}:\n"); 
		graph.getDFSTOTForest(order);
		
	}
	
	@Test
	public void testTopologicalSort() {
		
		/* Initialize graph for testing */
		AdjListUndir graph = new AdjListUndir(4);
		
		unsupportedOperation = assertThrows(UnsupportedOperationException.class, () -> graph.topologicalSort());
		assertEquals("\n Error: this operation is not allowed on this graph - It must be a DAG!!! \n", unsupportedOperation.getMessage());
	}
	
	@Test
	public void testConnectedComponets() {
		
		/* Initialize graph for testing */
		AdjListUndir graph = new AdjListUndir(10);
		
		graph.addEdge(0, 1);
		graph.addEdge(0, 4);
		graph.addEdge(0, 5);
		graph.addEdge(2, 3);
		graph.addEdge(2, 6);
		graph.addEdge(8, 9);
		
		/* Verify Connected Components */
		Set<Set<Integer>> connectedComponets = graph.connectedComponents();
		String expected = "[[8, 9], [7], [0, 1, 4, 5], [2, 3, 6]]";
		
		assertEquals(expected, connectedComponets.toString());
		
	}
	
	@Test
	public void testStronglyConnectedComponets() {
		
		/* Initialize graph for testing */
		AdjListUndir graph = new AdjListUndir(4);
		
		unsupportedOperation = assertThrows(UnsupportedOperationException.class, () -> graph.stronglyConnectedComponents());
		assertEquals("\n Error: this operation is not allowed on this graph - It must be Direct!!! \n", unsupportedOperation.getMessage());
	}
}
