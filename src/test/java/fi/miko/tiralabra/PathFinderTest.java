package fi.miko.tiralabra;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import fi.miko.tiralabra.algorithms.Graph;
import fi.miko.tiralabra.algorithms.Node;
import fi.miko.tiralabra.algorithms.PathFinder;

public class PathFinderTest {
	private static final double delta = 1E-15;

	private class TestPathFinder extends PathFinder {
		public TestPathFinder(Graph graph) {
			super(graph);
		}

		@Override
		public void findPath() {
		}
	};

	private PathFinder pf;

	@Before
	public void setUp() {
		Graph graph = mock(Graph.class);
		pf = new TestPathFinder(graph);
	}

	@Test
	public void testGetDistance() {
		Node n1 = new Node(0, 0);
		Node n2 = new Node(1, 0);

		assertEquals(1, pf.getDistance(n1, n2), delta);

		n2 = new Node(1, 1);
		assertEquals(Math.sqrt(2), pf.getDistance(n1, n2), delta);
	}

	@Test
	public void testConstructorGraphEqualsGetGraph() {
		Graph g = mock(Graph.class);
		PathFinder pf = new TestPathFinder(g);
		assertEquals(g, pf.getGraph());
	}

	@Test
	public void testDefaults() {
		assertNull(pf.getStart());
		assertNull(pf.getTarget());
	}

	@Test
	public void testSetStartAndTarget() {
		Node n1 = new Node(0, 1);
		Node n2 = new Node(1, 0);

		pf.setStart(n1);
		pf.setTarget(n2);
		assertEquals(n1, pf.getStart());
		assertEquals(n2, pf.getTarget());
	}
}
