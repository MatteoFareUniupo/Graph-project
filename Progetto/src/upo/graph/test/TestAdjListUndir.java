package upo.graph.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.NoSuchElementException;

import org.junit.Test;

import upo.graph.implementation.AdjListUndir;

public class TestAdjListUndir {
	
	AdjListUndir graph = new AdjListUndir(5);
	
	NoSuchElementException thrown_0;
	IllegalArgumentException thrown_1;

	@Test
	public void testVertex() {
		
		assertEquals(graph.size(), 0);
		
		graph.addVertex();
		graph.addVertex();
		graph.addVertex();
		graph.addVertex();
		
		assertEquals(graph.size(), 4);
		
		assert(graph.containsVertex(0));
		assert(graph.containsVertex(1));
		assert(graph.containsVertex(2));
		assert(graph.containsVertex(3));
		
		graph.removeVertex(3);
		
		assertEquals(graph.size(), 3);
		
		graph.addVertex();
		graph.addVertex();
		graph.addVertex();
		
		assertEquals(graph.size(), 6);
		
		assert(graph.containsVertex(0));
		assert(graph.containsVertex(1));
		assert(graph.containsVertex(2));
		assert(graph.containsVertex(3));
		assert(graph.containsVertex(4));
		assert(graph.containsVertex(5));
		
		
		thrown_0 = assertThrows(NoSuchElementException.class, () -> graph.removeVertex(8));
		assertEquals("Error: the vertex (8) does not exist!", thrown_0.getMessage());
		thrown_0 = assertThrows(NoSuchElementException.class, () -> graph.removeVertex(15));
		assertEquals("Error: the vertex (15) does not exist!", thrown_0.getMessage());	
	}
	
	@Test
	public void TestEdge()
	{			
		graph.addEdge(0, 1);
		graph.addEdge(0, 3);
		graph.addEdge(2, 3);
		graph.addEdge(1, 2);
		
		assert(graph.containsEdge(0, 1));
		assert(graph.containsEdge(0, 3));
		assert(graph.containsEdge(2, 3));
		assert(graph.containsEdge(1, 2));
		
		assert(!graph.containsEdge(2, 0));
		assert(!graph.containsEdge(0, 2));
		
		graph.removeEdge(0, 1);
		assert(!graph.containsEdge(0, 1));
		
		graph.removeVertex(3);
		assert(!graph.containsVertex(3));
	
		thrown_1 = assertThrows(IllegalArgumentException.class, () -> graph.addEdge(1, 3));
		assertEquals("Errore: uno dei vertici inseriti, o entrambi, non sono presenti nel grafo", thrown_1.getMessage());
		thrown_1 = assertThrows(IllegalArgumentException.class, () -> graph.addEdge(10, 30));
		assertEquals("Errore: uno dei vertici inseriti, o entrambi, non sono presenti nel grafo", thrown_1.getMessage());
		
		thrown_0 = assertThrows(NoSuchElementException.class, () -> graph.removeEdge(0, 2));
		assertEquals("Errore: l'arco [0-2] non è presente nel grafo", thrown_0.getMessage());
		
		
	}
}
