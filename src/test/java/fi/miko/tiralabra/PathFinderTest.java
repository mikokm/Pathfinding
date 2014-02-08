package fi.miko.tiralabra;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fi.miko.tiralabra.algorithms.Graph;
import fi.miko.tiralabra.algorithms.InvalidGraphException;
import fi.miko.tiralabra.algorithms.Node;
import fi.miko.tiralabra.algorithms.PathFinder;

public class PathFinderTest {
	private class TestPathFinder extends PathFinder {
		public TestPathFinder(Graph graph) {
			super(graph);
		}

		@Override
		public void findPath() {
			initializeNodes();
		}
	};

	private Graph graph;
	private List<Node> nodes = new ArrayList<>();
	private PathFinder pf;

	private void addNodes(int count) {
		for (int i = 0; i < count; ++i) {
			nodes.add(new Node(0, i));
		}
	}

	@Before
	public void setUp() {
		graph = mock(Graph.class);
		when(graph.getNodes()).thenReturn(nodes);

		pf = new TestPathFinder(graph);
	}

	@Test
	public void testConstructorGraphEqualsGetGraph() {
		assertEquals(graph, pf.getGraph());
	}

	@Test
	public void testDefaults() {
		assertNull(pf.getStart());
		assertNull(pf.getTarget());
	}

	@Test(expected = InvalidGraphException.class)
	public void testFindPathInvalidGraphNoStart() {
		addNodes(1);
		nodes.get(0).setType('t');
		pf.findPath();
	}

	@Test(expected = InvalidGraphException.class)
	public void testFindPathInvalidGraphNoTarget() {
		addNodes(1);
		nodes.get(0).setType('s');

		pf.findPath();
	}

	@Test
	public void testGetDistance() {
		Node n1 = new Node(0, 0);
		Node n2 = new Node(1, 0);
		assertEquals(1, pf.getDistance(n1, n2), 0);

		n2 = new Node(0, 1);
		assertEquals(1, pf.getDistance(n1, n2), 0);

		n2 = new Node(1, 1);
		assertEquals(Math.sqrt(2), pf.getDistance(n1, n2), 0);
	}

	@Test
	public void testInitializeNodes() {
		addNodes(3);
		nodes.get(0).setType('s');
		nodes.get(1).setType('t');

		pf.findPath();
		verify(graph).getNodes();
	}

	@Test
	public void testSetGraph() {
		pf.setGraph(null);
		assertNull(pf.getGraph());

		pf.setGraph(graph);
		assertEquals(graph, pf.getGraph());
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

	@Test
	public void testShortestPathNoPath() {
		addNodes(2);
		nodes.get(0).setType('s');
		nodes.get(1).setType('t');
		pf.findPath();

		assertTrue(pf.getShortestPath().isEmpty());
	}

	@Test(expected = InvalidGraphException.class)
	public void testShortestPathNoStart() {
		addNodes(2);
		nodes.get(1).setType('t');
		pf.getShortestPath();
	}

	@Test(expected = InvalidGraphException.class)
	public void testShortestPathNoTarget() {
		addNodes(2);
		nodes.get(0).setType('s');
		pf.getShortestPath();
	}

	@Test
	public void testShortestPathOnlyStartAndtarget() {
		addNodes(2);
		nodes.get(0).setType('s');
		nodes.get(1).setType('t');

		pf.findPath();
		nodes.get(1).setNearest(nodes.get(0));

		List<Node> p = pf.getShortestPath();
		assertEquals(2, p.size());
		assertEquals(nodes.get(0), p.get(0));
		assertEquals(nodes.get(1), p.get(1));
	}

	@Test
	public void testShortestPathThreeNodes() {
		addNodes(3);
		nodes.get(0).setType('s');
		nodes.get(1).setType('.');
		nodes.get(2).setType('t');

		pf.findPath();
		nodes.get(1).setNearest(nodes.get(0));
		nodes.get(2).setNearest(nodes.get(1));

		List<Node> p = pf.getShortestPath();
		assertEquals(3, p.size());
		assertEquals(nodes.get(0), p.get(0));
		assertEquals(nodes.get(1), p.get(1));
		assertEquals(nodes.get(2), p.get(2));
	}
}
