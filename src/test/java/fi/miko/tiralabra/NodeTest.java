package fi.miko.tiralabra;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fi.miko.tiralabra.algorithms.Node;

public class NodeTest {
	private static double delta = 1E-15;

	@Test
	public void testChanges() {
		Node node = new Node(0, 0);

		node.setNearest(node);
		assertEquals(node, node.getNearest());

		node.setDistance(1);
		assertEquals(1, node.getDistance(), delta);

		node.setDistanceEstimate(2);
		assertEquals(2, node.getDistanceEstimate(), delta);

		node.setType((char) 1);
		assertEquals(1, node.getType());

		assertFalse(node.isClosed());
		assertFalse(node.isOpen());
		assertFalse(node.isVisited());

		node.setClosed();
		node.setOpen();
		node.setVisited();

		assertTrue(node.isClosed());
		assertTrue(node.isOpen());
		assertTrue(node.isVisited());

		assertEquals(-1, node.getIndex());
		node.setIndex(1);
		assertEquals(1, node.getIndex());
	}

	@Test
	public void testConstructors() {
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

		node = new Node(0, 0);
		testDefaults(node);
	}

	private void testDefaults(Node node) {
		assertNull(node.getNearest());
		assertEquals(Double.MAX_VALUE, node.getDistance(), delta);
		assertEquals(0, node.getDistanceEstimate(), delta);
		assertFalse(node.isOpen());
		assertFalse(node.isClosed());
		assertFalse(node.isVisited());
	}

	@Test
	public void testEquals() {
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

		assertFalse(n1.equals(null));
		assertFalse(n1.equals(new Object()));
	}

	@Test
	public void testEqualsDifferent() {
		Node n1 = new Node(1, 0);
		Node n2 = new Node(0, 0);
		assertFalse(n1.equals(n2));

		n1 = new Node(0, 0);
		n2 = new Node(0, 1);
		assertFalse(n1.equals(n2));

		n2 = null;
		assertFalse(n1.equals(n2));
	}

	@Test
	public void testEqualsSame() {
		Node n1 = new Node(0, 0);
		Node n2 = new Node(0, 0);
		assertTrue(n1.equals(n2));

		n1 = new Node(0, 1);
		n2 = new Node(0, 1);
		assertTrue(n1.equals(n2));

		n1 = new Node(1, 0);
		n2 = new Node(1, 0);
		assertTrue(n1.equals(n2));
	}

	@Test
	public void testGetKeyReturnsRight() {
		Node node = new Node(0, 0);

		node.setDistance(0);
		node.setDistanceEstimate(0);
		assertEquals(0, node.getKey(), delta);

		node.setDistance(1);
		assertEquals(1, node.getKey(), delta);

		node.setDistanceEstimate(1);
		assertEquals(2, node.getKey(), delta);
	}

	@Test
	public void testHashCodeSimple() {
		Node node = new Node(0, 0);
		int h1 = node.hashCode();

		node = new Node(1, 1);
		int h2 = node.hashCode();

		assertNotEquals(h1, h2);
	}

	@Test
	public void testReset() {
		Node node = new Node(0, 0);
		testDefaults(node);

		node.setDistance(1);
		assertEquals(1, node.getDistance(), delta);

		node.reset();
		testDefaults(node);
	}

	@Test
	public void testToString() {
		Node node = new Node(0, 0);
		assertEquals("[0, 0]", node.toString());

		node = new Node(1, 1);
		assertEquals("[1, 1]", node.toString());
	}

}
