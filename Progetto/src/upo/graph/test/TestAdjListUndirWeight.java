package upo.graph.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import org.junit.Test;

import upo.graph.implementation.AdjListUndirWeight;

public class TestAdjListUndirWeight {
	
	/* NoSuchElementException */
	private NoSuchElementException noSuchElement;
	
	/* IllegalArgumentException */
	private IllegalArgumentException illegalArgument;
	
	/* UnsupportedOperationException */
	//private UnsupportedOperationException unsupportedOperation;
	
	@Test /* test existence of vertices in the created graph */
	public void testVertex() {
		
		/* Initialize graph for testing */
		AdjListUndirWeight graph = new AdjListUndirWeight(5);
		
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
		
		assertEquals(graph.size(), 5); 
		
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
		AdjListUndirWeight graph = new AdjListUndirWeight(4);
		
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
		
//		System.out.print("[1]-[2]: " + graph.containsEdge(1, 2) + "\n");
//		System.out.print("[2]-[1]: " + graph.containsEdge(2, 1) + "\n");
		
		assertEquals(graph.containsEdge(1, 2), false);
		assertEquals(graph.containsEdge(2, 1), false); // ERROR
	}
	
	@Test
	public void testAdjacent() {
		
		/* Initialize graph for testing */
		AdjListUndirWeight graph = new AdjListUndirWeight(4);
		
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
		AdjListUndirWeight graph = new AdjListUndirWeight(4);
		
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
	public void testConnectedComponets() {
		
		/* Initialize graph for testing */
		AdjListUndirWeight graph = new AdjListUndirWeight(10);
		
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
	public void testEquality() {
		
		/* Initialize graph for testing */
		AdjListUndirWeight firstGraph = new AdjListUndirWeight(4);
		
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
		AdjListUndirWeight secondGraph = new AdjListUndirWeight(4);
		
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
		AdjListUndirWeight thirdGraph = new AdjListUndirWeight(7);
		
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
	
	@Test
	public void testEdgeWeight() {
		
		/* Initialize graph for testing */
		AdjListUndirWeight graph = new AdjListUndirWeight(5);
		
		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(0, 3);
		graph.addEdge(1, 3);
		
		/* Verify that standard weight is 1.0 */
		assertEquals(1.0, graph.getEdgeWeight(0, 1), 0.00);
		assertEquals(1.0, graph.getEdgeWeight(0, 2), 0.00);
		assertEquals(1.0, graph.getEdgeWeight(0, 3), 0.00);
		assertEquals(1.0, graph.getEdgeWeight(1, 3), 0.00);
		assertEquals(1.0, graph.getEdgeWeight(1, 0), 0.00);
		assertEquals(1.0, graph.getEdgeWeight(2, 0), 0.00);
		assertEquals(1.0, graph.getEdgeWeight(3, 0), 0.00);
		assertEquals(1.0, graph.getEdgeWeight(3, 1), 0.00);
		
//		System.out.print(" 0:"+graph.adjList.get(0)+"\n");
//		System.out.print(" 1:"+graph.adjList.get(1)+"\n");
//		System.out.print(" 2:"+graph.adjList.get(2)+"\n");
//		System.out.print(" 3:"+graph.adjList.get(3)+"\n");
//		System.out.print(" 4:"+graph.adjList.get(4)+"\n");
		
		graph.setEdgeWeight(0, 1, 4.7);
		graph.setEdgeWeight(0, 2, 2.0);
		graph.setEdgeWeight(0, 3, 1.5);
		graph.setEdgeWeight(1, 3, 1.0);
		
		assertEquals(4.7, graph.getEdgeWeight(0, 1), 0.00);
		assertEquals(2.0, graph.getEdgeWeight(0, 2), 0.00);
		assertEquals(1.5, graph.getEdgeWeight(0, 3), 0.00);
		assertEquals(1.0, graph.getEdgeWeight(1, 3), 0.00);
		assertEquals(4.7, graph.getEdgeWeight(1, 0), 0.00);
		assertEquals(2.0, graph.getEdgeWeight(2, 0), 0.00);
		assertEquals(1.5, graph.getEdgeWeight(3, 0), 0.00);
		assertEquals(1.0, graph.getEdgeWeight(3, 1), 0.00);	
		
//		System.out.print(" 0:"+graph.adjList.get(0)+"\n");
//		System.out.print(" 1:"+graph.adjList.get(1)+"\n");
//		System.out.print(" 2:"+graph.adjList.get(2)+"\n");
//		System.out.print(" 3:"+graph.adjList.get(3)+"\n");
//		System.out.print(" 4:"+graph.adjList.get(4)+"\n");
		
	}
}
