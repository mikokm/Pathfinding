package fi.miko.tiralabra;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

import fi.miko.tiralabra.algorithms.AStar;
import fi.miko.tiralabra.algorithms.BellmanFord;
import fi.miko.tiralabra.algorithms.Dijkstra;
import fi.miko.tiralabra.algorithms.Graph;
import fi.miko.tiralabra.algorithms.Heuristic;
import fi.miko.tiralabra.algorithms.Node;
import fi.miko.tiralabra.algorithms.PathFinder;
import fi.miko.tiralabra.datastructures.LinkedList;

public class ShortestPathTest {
	private static final double SQRT2 = Math.sqrt(2);

	private void testPathFinder(PathFinder f, double length) {
		f.findPath();

		LinkedList<Node> path = f.getShortestPath();

		if (length > 0) {
			assertTrue(isValidPath(f.getGraph(), path));
			assertEquals(length, getPathDistance(path), 1E-9);
		} else {
			assertTrue(path.isEmpty());
		}
	}

	private void testPathFinders(char[][] map, double length) {
		testPathFinder(new BellmanFord(new Graph(map)), length);
		testPathFinder(new Dijkstra(new Graph(map)), length);
		testPathFinder(new AStar(new Graph(map), Heuristic.Euclidean), length);
	}

	// @formatter:off
	@Test
	public void testTwoNodes() {
		testPathFinders(new char[][] {
				{ 's', 't' } }, 1);

		testPathFinders(new char[][] {
				{ 't', 's' } }, 1);

		testPathFinders(new char[][] {
				{ 's' },
				{ 't' } }, 1);

		testPathFinders(new char[][] {
				{ 't' },
				{ 's' } }, 1);
	}

	@Test
	public void testThreeNodes() {
		testPathFinders(new char[][] {
				{ 's', 'p', 't' } }, 2);

		testPathFinders(new char[][] {
				{ 't', 'p', 's' } }, 2);

		testPathFinders(new char[][] {
				{ 's' },
				{ 'p' },
				{ 't' } }, 2);

		testPathFinders(new char[][] {
				{ 't' },
				{ 'p' },
				{ 's' } }, 2);
	}

	@Test
	public void testThreeNodesWall() {
		testPathFinders(new char[][] {
				{ 's', '#', 't' } }, -1);

		testPathFinders(new char[][] {
				{'t', '#', 's' } }, -1);

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
	public void test3x2() {
		testPathFinders(new char[][] {
				{ 's', '#', 'n'},
				{ 'n', 'p', 't'} }, SQRT2 + 1);

		testPathFinders(new char[][] {
				{ 's', 'p', 'n'},
				{ 'n', '#', 't'} }, SQRT2 + 1);
	}

	@Test
	public void test2x3() {
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

	// @formatter:on

	public static char[][] generateRandom(int width, int height, double freq) {
		char[][] graph = new char[height][width];
		Random rand = new Random();

		for (int i = 0; i < height; ++i) {
			for (int j = 0; j < width; ++j) {
				graph[i][j] = (rand.nextDouble() <= freq ? '#' : '.');
			}
		}

		graph[0][0] = 's';
		graph[width - 1][height - 1] = 't';

		return graph;
	}

	public static double getPathDistance(LinkedList<Node> path) {
		if (path.size() == 0) {
			return 0;
		}

		return path.get(path.size() - 1).getDistance();
	}

	public static boolean isValidPath(Graph graph, LinkedList<Node> path) {
		Node prev = null;

		for (Node n : path) {
			if (n.getType() != 'p' && n.getType() != 's' && n.getType() != 't') {
				return false;
			}

			if (prev != null) {
				if (graph.getNeighbours(prev).contains(n) == false) {
					return false;
				}
			}

			prev = n;
		}

		return true;
	}

}
