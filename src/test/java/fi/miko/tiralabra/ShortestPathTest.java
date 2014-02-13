package fi.miko.tiralabra;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fi.miko.tiralabra.algorithms.AStar;
import fi.miko.tiralabra.algorithms.BellmanFord;
import fi.miko.tiralabra.algorithms.Dijkstra;
import fi.miko.tiralabra.algorithms.Graph;
import fi.miko.tiralabra.algorithms.GraphUtils;
import fi.miko.tiralabra.algorithms.Heuristic;
import fi.miko.tiralabra.algorithms.Node;
import fi.miko.tiralabra.algorithms.PathFinder;
import fi.miko.tiralabra.datastructures.LinkedList;

public class ShortestPathTest {
	private static final double SQRT2 = Math.sqrt(2);

	// @formatter:off
	@Test
	public void test1x2() {
		testPathFinders(new char[][] {
				{ 's', 't' } }, 1);

		testPathFinders(new char[][] {
				{ 't', 's' } }, 1);
	}

	@Test
	public void test1x3() {
		testPathFinders(new char[][] {
				{ 's' },
				{ 'p' },
				{ 't' } }, 2);

		testPathFinders(new char[][] {
				{ 't' },
				{ 'p' },
				{ 's' } }, 2);

		testPathFinders(new char[][] {
				{ 's' },
				{ '#' },
				{ 't' } }, -1);

		testPathFinders(new char[][] {
				{ 't' },
				{ '#' },
				{ 's' } }, -1);
	}

	@Test
	public void test2x1() {
		testPathFinders(new char[][] {
				{ 's' },
				{ 't' } }, 1);

		testPathFinders(new char[][] {
				{ 't' },
				{ 's' } }, 1);
	}

	@Test
	public void test2x2() {
		testPathFinders(new char[][] {
				{ 's', 't' },
				{ 'n', 'n' } }, 1);

		testPathFinders(new char[][] {
				{ 't', 's' },
				{ 'n', 'n' } }, 1);

		testPathFinders(new char[][] {
				{ 's', 'n' },
				{ 't', 'n' } }, 1);

		testPathFinders(new char[][] {
				{ 'n', 't' },
				{ 'n', 's' } }, 1);

		testPathFinders(new char[][] {
				{ 's', 'n' },
				{ 'n', 't' } }, SQRT2);

		testPathFinders(new char[][] {
				{ 't', 'n' },
				{ 'n', 's' } }, SQRT2);

		testPathFinders(new char[][] {
				{ 'n', 's' },
				{ 't', 'n' } }, SQRT2);

		testPathFinders(new char[][] {
				{ 'n', 't' },
				{ 's', 'n' } }, SQRT2);
	}

	@Test
	public void test2x3() {
		testPathFinders(new char[][] {
				{ 's', 'n' },
				{ '#', '#' },
				{ 'n', 't' } }, -1);

		testPathFinders(new char[][] {
				{ 't', 'n' },
				{ '#', '#' },
				{ 'n', 's' } }, -1);

		testPathFinders(new char[][] {
				{ 's', 'n' },
				{ '#', 'p' },
				{ 'n', 't' } }, SQRT2 + 1);

		testPathFinders(new char[][] {
				{ 's', 'n' },
				{ 'p', '#' },
				{ 'n', 't' } }, SQRT2 + 1);
	}

	@Test
	public void test3x1() {
		testPathFinders(new char[][] {
				{ 's', 'p', 't' } }, 2);

		testPathFinders(new char[][] {
				{ 't', 'p', 's' } }, 2);

		testPathFinders(new char[][] {
				{ 's', 'p', 't' } }, 2);

		testPathFinders(new char[][] {
				{ 't', 'p', 's' } }, 2);

		testPathFinders(new char[][] {
				{ 's', '#', 't' } }, -1);

		testPathFinders(new char[][] {
				{'t', '#', 's' } }, -1);

	}

	@Test
	public void test3x2() {
		testPathFinders(new char[][] {
				{ 's', '#', 'n'},
				{ 'n', '#', 't'} }, -1);

		testPathFinders(new char[][] {
				{ 't', '#', 'n'},
				{ 'n', '#', 's'} }, -1);

		testPathFinders(new char[][] {
				{ 's', '#', 'n'},
				{ 'n', 'p', 't'} }, SQRT2 + 1);

		testPathFinders(new char[][] {
				{ 's', 'p', 'n'},
				{ 'n', '#', 't'} }, SQRT2 + 1);
	}

	@Test
	public void test3x3() {
		testPathFinders(new char[][] {
				{ 's', '#', 'n'},
				{ '#', '#', 'n'},
				{ 'n', 'n', 't'} }, -1);

		testPathFinders(new char[][] {
				{ 's', 'n', 'n'},
				{ 'n', '#', '#'},
				{ 'n', '#', 't'} }, -1);

		testPathFinders(new char[][] {
				{ 's', '#', 'n'},
				{ '#', 'p', 'n'},
				{ 'n', 'n', 't'} }, SQRT2 * 2);

		testPathFinders(new char[][] {
				{ 's', 'p', 'n'},
				{ '#', '#', 'p'},
				{ 'n', 'n', 't'} }, SQRT2 + 2);

	}

	@Test
	public void test4x4() {
		testPathFinders(new char[][] {
				{ 's', 'n', 'n', 'n'},
				{ 'n', 'p', 'n', 'n'},
				{ 'n', 'n', 'p', 'n'},
				{ 'n', 'n', 'n', 't'} }, SQRT2 * 3);

		testPathFinders(new char[][] {
				{ 't', 'n', 'n', 'n'},
				{ 'n', 'p', 'n', 'n'},
				{ 'n', 'n', 'p', 'n'},
				{ 'n', 'n', 'n', 's'} }, SQRT2 * 3);

		testPathFinders(new char[][] {
				{ 's', 'n', 'n', 'n'},
				{ 'p', 'n', 'n', 'n'},
				{ 'p', 'n', 'n', 'n'},
				{ 't', 'n', 'n', 'n'} }, 3);

		testPathFinders(new char[][] {
				{ 's', 'p', 'p', 't'},
				{ 'n', 'n', 'n', 'n'},
				{ 'n', 'n', 'n', 'n'},
				{ 'n', 'n', 'n', 'n'} }, 3);

		testPathFinders(new char[][] {
				{ 's', '#', '#', 't'},
				{ 'n', 'p', 'p', 'n'},
				{ 'n', 'n', 'n', 'n'},
				{ 'n', 'n', 'n', 'n'} }, SQRT2 * 2 + 1);

		testPathFinders(new char[][] {
				{ 's', 'n', '#', 't'},
				{ 'p', 'n', '#', 'p'},
				{ 'p', '#', '#', 'p'},
				{ 'n', 'p', 'p', 'n'} }, SQRT2 * 2 + 5);
	}

	@Test
	public void testHollowObstacle() {
		testPathFinders(new char[][] {
				{ 'n', 'n', 'n', 'n', 'n', 'n', 'n', 'n'},
				{ 'n', 'n', 'n', 'n', 'n', 'n', 'n', 'n'},
				{ 'n', 'n', '#', '#', '#', 'n', 'n', 'n'},
				{ 'n', 'n', 'n', 'n', '#', 'n', 'n', 'n'},
				{ 's', 'n', 'n', 'n', '#', 't', 'n', 'n'},
				{ 'n', 'p', '#', '#', '#', 'p', 'n', 'n'},
				{ 'n', 'n', 'p', 'p', 'p', 'n', 'n', 'n'},
				{ 'n', 'n', 'n', 'n', 'n', 'n', 'n', 'n'} }, SQRT2 * 3 + 3);

		testPathFinders(new char[][] {
				{ 'n', 'n', 'n', 'n', 'n', 'n', 'n', 'n'},
				{ 'n', 'n', 'p', 'p', 'p', 'n', 'n', 'n'},
				{ 'n', 'p', '#', '#', '#', 'p', 'n', 'n'},
				{ 's', 'n', 'n', 'n', '#', 't', 'n', 'n'},
				{ 'n', 'n', 'n', 'n', '#', 'n', 'n', 'n'},
				{ 'n', 'n', '#', '#', '#', 'n', 'n', 'n'},
				{ 'n', 'n', 'n', 'n', 'n', 'n', 'n', 'n'},
				{ 'n', 'n', 'n', 'n', 'n', 'n', 'n', 'n'} }, SQRT2 * 3 + 3);

		testPathFinders(new char[][] {
				{ 'n', 'n', 'n', 'n', 'n', 'n', 'n', 'n'},
				{ 'n', 'n', 'n', 'n', 'n', 'n', 'n', 'n'},
				{ 'n', 'n', '#', '#', '#', 'n', 'n', 'n'},
				{ 'n', 'n', 'n', 'n', '#', 'n', 'n', 'n'},
				{ 'n', 'n', 'p', 's', '#', 't', 'n', 'n'},
				{ 'n', 'p', '#', '#', '#', 'p', 'n', 'n'},
				{ 'n', 'n', 'p', 'p', 'p', 'n', 'n', 'n'},
				{ 'n', 'n', 'n', 'n', 'n', 'n', 'n', 'n'} }, SQRT2 * 3 + 4);

		testPathFinders(new char[][] {
				{ 'n', 'n', 'n', 'n', 'n', 'n', 'n', 'n'},
				{ 'n', 'n', 'p', 'p', 'p', 'n', 'n', 'n'},
				{ 'n', 'p', '#', '#', '#', 'p', 'n', 'n'},
				{ 'n', 'n', 'p', 'n', '#', 't', 'n', 'n'},
				{ 'n', 'n', 'n', 's', '#', 'n', 'n', 'n'},
				{ 'n', 'n', '#', '#', '#', 'n', 'n', 'n'},
				{ 'n', 'n', 'n', 'n', 'n', 'n', 'n', 'n'},
				{ 'n', 'n', 'n', 'n', 'n', 'n', 'n', 'n'} }, SQRT2 * 4 + 3);
	}

	// @formatter:on

	private LinkedList<Node> getShortestPath(PathFinder f) {
		f.findPath();

		LinkedList<Node> path = f.getShortestPath();
		assertTrue(GraphUtils.isValidPath(f.getGraph(), path));

		return path;
	}

	private void testPathFinder(PathFinder f, double length) {
		LinkedList<Node> path = getShortestPath(f);

		if (length > 0) {
			assertEquals(length, GraphUtils.getPathDistance(path), 1E-9);
		} else {
			assertTrue(path.isEmpty());
		}
	}

	private void testPathFinders(char[][] map, double length) {
		testPathFinder(new BellmanFord(new Graph(map)), length);
		testPathFinder(new Dijkstra(new Graph(map)), length);
		testPathFinder(new AStar(new Graph(map), Heuristic.Euclidean), length);
	}

	@Test
	public void testRandomGraph() {
		for (int i = 2; i < 50; ++i) {
			for (int j = 2; j < 50; ++j) {
				for (int k = 20; k < 40; ++k) {
					testRandomGraphHelper(i, j, k / 100);
				}
			}
		}
	}

	private void testRandomGraphHelper(int width, int height, double freq) {
		char[][] map = GraphUtils.generateRandom(width, height, freq);

		LinkedList<Node> p1 = getShortestPath(new Dijkstra(new Graph(map)));
		LinkedList<Node> p2 = getShortestPath(new AStar(new Graph(map), Heuristic.Euclidean));

		assertEquals(p1.size(), p2.size());
		assertEquals(GraphUtils.getPathDistance(p1), GraphUtils.getPathDistance(p2), 1E-9);

		if (width < 20 && height < 20) {
			LinkedList<Node> p3 = getShortestPath(new BellmanFord(new Graph(map)));

			assertEquals(p2.size(), p3.size());
			assertEquals(GraphUtils.getPathDistance(p2), GraphUtils.getPathDistance(p3), 1E-9);
		}
	}

}
