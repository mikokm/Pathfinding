package fi.miko.tiralabra;

import static org.junit.Assert.*;

import org.junit.Test;

import fi.miko.tiralabra.algorithms.Node;

public class NodeTest {
	private static double delta = 1E-15;

	@Test
	public void testNodeConstructors() {
		Node node = new Node(1, 0);
		assertEquals(1, node.getX());
		assertEquals(0, node.getY());
		assertEquals(0, node.getType());

		node = new Node(0, 1);
		assertEquals(0, node.getX());
		assertEquals(1, node.getY());
		assertEquals(0, node.getType());

		node = new Node(1, 1, (char) 1);
		assertEquals(1, node.getX());
		assertEquals(1, node.getY());
		assertEquals(1, node.getType());
	}

	@Test
	public void testNodeDefaults() {
		Node node = new Node(0, 0);

		assertNull(node.getNearest());
		assertEquals(0, node.getDistance(), delta);
		assertEquals(0, node.getDistanceEstimate(), delta);
	}

	@Test
	public void testNodeChanges() {
		Node node = new Node(0, 0);

		node.setNearest(node);
		assertEquals(node, node.getNearest());

		node.setDistance(1);
		assertEquals(1, node.getDistance(), delta);

		node.setDistanceEstimate(2);
		assertEquals(2, node.getDistanceEstimate(), delta);

		node.setType((char) 1);
		assertEquals(1, node.getType());
	}

	@Test
	public void testNodeComparison() {
		Node n1 = new Node(0, 0);
		Node n2 = new Node(0, 0);

		n1.setDistance(0);
		n2.setDistance(1);
		assertEquals(-1, n1.compareTo(n2));

		n1.setDistance(1);
		n2.setDistance(1);
		assertEquals(0, n1.compareTo(n2));

		n1.setDistance(1);
		n2.setDistance(0);
		assertEquals(1, n1.compareTo(n2));

		n1.setDistance(1);
		n1.setDistanceEstimate(2);
		n2.setDistance(1);
		n2.setDistanceEstimate(1);
		assertEquals(1, n1.compareTo(n2));

		n1.setDistance(1);
		n1.setDistanceEstimate(1);
		n2.setDistance(1);
		n2.setDistanceEstimate(2);
		assertEquals(-1, n1.compareTo(n2));
	}
}
