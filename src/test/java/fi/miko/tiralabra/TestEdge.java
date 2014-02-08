package fi.miko.tiralabra;

import static org.junit.Assert.*;

import org.junit.Test;

import fi.miko.tiralabra.algorithms.Edge;
import fi.miko.tiralabra.algorithms.Node;

public class TestEdge {
	@Test(expected = RuntimeException.class)
	public void testCreateEdgeNegativeWeight() {
		new Edge(new Node(0, 0), new Node(1, 1), -1);
	}

	@Test
	public void testCreateEdgeValid() {
		Node n1 = new Node(0, 0);
		Node n2 = new Node(1, 1);
		Edge e = new Edge(n1, n2, Math.sqrt(2));

		assertEquals(n1, e.u);
		assertEquals(n2, e.v);
		assertEquals(Math.sqrt(2), e.w, 0);
	}

	@Test(expected = RuntimeException.class)
	public void testCreateEdgeWithUNullNode() {
		new Edge(null, new Node(0, 0), 0);
	}

	@Test(expected = RuntimeException.class)
	public void testCreateEdgeWithVNullNode() {
		new Edge(new Node(0, 0), null, 0);
	}

	@Test
	public void testToString() {
		Node n1 = new Node(0, 0);
		Node n2 = new Node(1, 1);
		Edge e = new Edge(n1, n2, 1);

		String s = e.toString();
		assertTrue(s.contains(n1.toString()));
		assertTrue(s.contains(n2.toString()));
	}
}
