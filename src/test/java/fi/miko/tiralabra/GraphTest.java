package fi.miko.tiralabra;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fi.miko.tiralabra.algorithms.Graph;
import fi.miko.tiralabra.algorithms.Node;
import fi.miko.tiralabra.datastructures.LinkedList;

public class GraphTest {
	@Test
	public void testCreateGraphValid() {
		Graph g = new Graph(new char[1][1]);
		assertEquals(1, g.getHeight());
		assertEquals(1, g.getWidth());
		assertEquals(1, g.getNodes().size());

		assertEquals(new Node(0, 0), g.getNodes().get(0));

		g = new Graph(new char[2][2]);
		assertEquals(2, g.getHeight());
		assertEquals(2, g.getWidth());
		assertEquals(4, g.getNodes().size());

		assertTrue(g.getNodes().contains(new Node(0, 0)));
		assertTrue(g.getNodes().contains(new Node(0, 1)));
		assertTrue(g.getNodes().contains(new Node(1, 0)));
		assertTrue(g.getNodes().contains(new Node(1, 1)));

		g = new Graph(new char[2][1]);
		assertEquals(2, g.getHeight());
		assertEquals(1, g.getWidth());
		assertEquals(2, g.getNodes().size());

		assertTrue(g.getNodes().contains(new Node(0, 0)));
		assertTrue(g.getNodes().contains(new Node(0, 1)));
	}

	@Test(expected = RuntimeException.class)
	public void testCreateGraphZeroHeight() {
		new Graph(new char[0][1]);
	}

	@Test(expected = RuntimeException.class)
	public void testCreateGraphZeroWidth() {
		new Graph(new char[1][0]);
	}

	@Test
	public void testGetNeighboursInvalidNode() {
		Graph g = new Graph(new char[2][2]);
		assertTrue(g.getNeighbours(new Node(2, 2)).isEmpty());
	}

	@Test
	public void testGetNeighboursOneNode() {
		Graph g = new Graph(new char[1][1]);
		assertTrue(g.getNeighbours(new Node(0, 0)).isEmpty());
	}

	@Test
	public void testGetNeighboursValidNode() {
		Graph g = new Graph(new char[2][2]);
		LinkedList<Node> neighbours = g.getNeighbours(new Node(0, 0));
		assertEquals(3, neighbours.size());
		assertTrue(neighbours.contains(new Node(0, 1)));
		assertTrue(neighbours.contains(new Node(1, 0)));
		assertTrue(neighbours.contains(new Node(1, 1)));
	}

	@Test
	public void testGetNodeInsideGraph() {
		Graph g = new Graph(new char[][] { { '.' } });
		assertEquals(new Node(0, 0), g.getNode(0, 0));
		assertEquals('.', g.getNode(0, 0).getType());
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetNodeOutsideGraph() {
		Graph g = new Graph(new char[1][1]);
		g.getNode(1, 1);
	}

	@Test
	public void testIsWithinGraphInside() {
		Graph g = new Graph(new char[2][2]);
		assertTrue(g.isWithinGraph(0, 0));
		assertTrue(g.isWithinGraph(1, 1));
	}

	@Test
	public void testIsWithinGraphOutside() {
		Graph g = new Graph(new char[1][1]);
		assertFalse(g.isWithinGraph(0, 1));
		assertFalse(g.isWithinGraph(1, 0));
		assertFalse(g.isWithinGraph(1, 1));
		assertFalse(g.isWithinGraph(-1, 0));
		assertFalse(g.isWithinGraph(0, -1));
	}

	@Test
	public void testToString() {
		Graph g = new Graph(new char[][] { { 'a', 'b' }, { 'c', 'd' } });
		String s = g.toString();

		assertTrue(s.contains("a"));
		assertTrue(s.contains("b"));
		assertTrue(s.contains("c"));
		assertTrue(s.contains("d"));
	}
}
