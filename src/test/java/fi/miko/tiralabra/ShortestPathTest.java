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
				{ '.' },
				{ 't' } }, 2);

		testPathFinders(new char[][] {
				{ 't' },
				{ '.' },
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
				{ 'x', 'x' } }, 1);

		testPathFinders(new char[][] {
				{ 't', 's' },
				{ 'x', 'x' } }, 1);

		testPathFinders(new char[][] {
				{ 's', 'x' },
				{ 't', 'x' } }, 1);

		testPathFinders(new char[][] {
				{ 'x', 't' },
				{ 'x', 's' } }, 1);

		testPathFinders(new char[][] {
				{ 's', 'x' },
				{ 'x', 't' } }, SQRT2);

		testPathFinders(new char[][] {
				{ 't', 'x' },
				{ 'x', 's' } }, SQRT2);

		testPathFinders(new char[][] {
				{ 'x', 's' },
				{ 't', 'x' } }, SQRT2);

		testPathFinders(new char[][] {
				{ 'x', 't' },
				{ 's', 'x' } }, SQRT2);
	}

	@Test
	public void test2x3() {
		testPathFinders(new char[][] {
				{ 's', 'x' },
				{ '#', '#' },
				{ 'x', 't' } }, -1);

		testPathFinders(new char[][] {
				{ 't', 'x' },
				{ '#', '#' },
				{ 'x', 's' } }, -1);

		testPathFinders(new char[][] {
				{ 's', 'x' },
				{ '#', '.' },
				{ 'x', 't' } }, SQRT2 + 1);

		testPathFinders(new char[][] {
				{ 's', 'x' },
				{ '.', '#' },
				{ 'x', 't' } }, SQRT2 + 1);
	}

	@Test
	public void test3x1() {
		testPathFinders(new char[][] {
				{ 's', '.', 't' } }, 2);

		testPathFinders(new char[][] {
				{ 't', '.', 's' } }, 2);

		testPathFinders(new char[][] {
				{ 's', '.', 't' } }, 2);

		testPathFinders(new char[][] {
				{ 't', '.', 's' } }, 2);

		testPathFinders(new char[][] {
				{ 's', '#', 't' } }, -1);

		testPathFinders(new char[][] {
				{'t', '#', 's' } }, -1);

	}

	@Test
	public void test3x2() {
		testPathFinders(new char[][] {
				{ 's', '#', 'x'},
				{ 'x', '#', 't'} }, -1);

		testPathFinders(new char[][] {
				{ 't', '#', 'x'},
				{ 'x', '#', 's'} }, -1);

		testPathFinders(new char[][] {
				{ 's', '#', 'x'},
				{ 'x', '.', 't'} }, SQRT2 + 1);

		testPathFinders(new char[][] {
				{ 's', '.', 'x'},
				{ 'x', '#', 't'} }, SQRT2 + 1);
	}

	@Test
	public void test3x3() {
		testPathFinders(new char[][] {
				{ 's', '#', 'x'},
				{ '#', '#', 'x'},
				{ 'x', 'x', 't'} }, -1);

		testPathFinders(new char[][] {
				{ 's', 'x', 'x'},
				{ 'x', '#', '#'},
				{ 'x', '#', 't'} }, -1);

		testPathFinders(new char[][] {
				{ 's', '#', 'x'},
				{ '#', '.', 'x'},
				{ 'x', 'x', 't'} }, SQRT2 * 2);

		testPathFinders(new char[][] {
				{ 's', '.', 'x'},
				{ '#', '#', '.'},
				{ 'x', 'x', 't'} }, SQRT2 + 2);

	}

	@Test
	public void test4x4() {
		testPathFinders(new char[][] {
				{ 's', 'x', 'x', 'x'},
				{ 'x', '.', 'x', 'x'},
				{ 'x', 'x', '.', 'x'},
				{ 'x', 'x', 'x', 't'} }, SQRT2 * 3);

		testPathFinders(new char[][] {
				{ 't', 'x', 'x', 'x'},
				{ 'x', '.', 'x', 'x'},
				{ 'x', 'x', '.', 'x'},
				{ 'x', 'x', 'x', 's'} }, SQRT2 * 3);

		testPathFinders(new char[][] {
				{ 's', 'x', 'x', 'x'},
				{ '.', 'x', 'x', 'x'},
				{ '.', 'x', 'x', 'x'},
				{ 't', 'x', 'x', 'x'} }, 3);

		testPathFinders(new char[][] {
				{ 's', '.', '.', 't'},
				{ 'x', 'x', 'x', 'x'},
				{ 'x', 'x', 'x', 'x'},
				{ 'x', 'x', 'x', 'x'} }, 3);

		testPathFinders(new char[][] {
				{ 's', '#', '#', 't'},
				{ 'x', '.', '.', 'x'},
				{ 'x', 'x', 'x', 'x'},
				{ 'x', 'x', 'x', 'x'} }, SQRT2 * 2 + 1);

		testPathFinders(new char[][] {
				{ 's', 'x', '#', 't'},
				{ '.', 'x', '#', '.'},
				{ '.', '#', '#', '.'},
				{ 'x', '.', '.', 'x'} }, SQRT2 * 2 + 5);
	}

	@Test
	public void testHollowObstacle() {
		testPathFinders(new char[][] {
				{ 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},
				{ 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},
				{ 'x', 'x', '#', '#', '#', 'x', 'x', 'x'},
				{ 'x', 'x', 'x', 'x', '#', 'x', 'x', 'x'},
				{ 's', 'x', 'x', 'x', '#', 't', 'x', 'x'},
				{ 'x', '.', '#', '#', '#', '.', 'x', 'x'},
				{ 'x', 'x', '.', '.', '.', 'x', 'x', 'x'},
				{ 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'} }, SQRT2 * 3 + 3);

		testPathFinders(new char[][] {
				{ 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},
				{ 'x', 'x', '.', '.', '.', 'x', 'x', 'x'},
				{ 'x', '.', '#', '#', '#', '.', 'x', 'x'},
				{ 's', 'x', 'x', 'x', '#', 't', 'x', 'x'},
				{ 'x', 'x', 'x', 'x', '#', 'x', 'x', 'x'},
				{ 'x', 'x', '#', '#', '#', 'x', 'x', 'x'},
				{ 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},
				{ 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'} }, SQRT2 * 3 + 3);

		testPathFinders(new char[][] {
				{ 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},
				{ 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},
				{ 'x', 'x', '#', '#', '#', 'x', 'x', 'x'},
				{ 'x', 'x', 'x', 'x', '#', 'x', 'x', 'x'},
				{ 'x', 'x', '.', 's', '#', 't', 'x', 'x'},
				{ 'x', '.', '#', '#', '#', '.', 'x', 'x'},
				{ 'x', 'x', '.', '.', '.', 'x', 'x', 'x'},
				{ 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'} }, SQRT2 * 3 + 4);

		testPathFinders(new char[][] {
				{ 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},
				{ 'x', 'x', '.', '.', '.', 'x', 'x', 'x'},
				{ 'x', '.', '#', '#', '#', '.', 'x', 'x'},
				{ 'x', 'x', '.', 'x', '#', 't', 'x', 'x'},
				{ 'x', 'x', 'x', 's', '#', 'x', 'x', 'x'},
				{ 'x', 'x', '#', '#', '#', 'x', 'x', 'x'},
				{ 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},
				{ 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'} }, SQRT2 * 4 + 3);
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
		for (int i = 2; i <= 50; ++i) {
			for (int j = 2; j <= 50; ++j) {
				for (int k = 20; k <= 40; ++k) {
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
