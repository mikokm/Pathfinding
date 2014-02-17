package fi.miko.tiralabra.algorithms;

import java.util.Random;

import fi.miko.tiralabra.datastructures.LinkedList;

/**
 * GraphUtils provides few utility methods for testing and benchmarking.
 */
public class GraphUtils {
	/**
	 * Generates a random graph.
	 *
	 * @param width
	 *            The width of the graph.
	 * @param height
	 *            The height of the graph.
	 * @param freq
	 *            The wall frequency.
	 * @return The generated graph.
	 */
	public static char[][] generateRandom(int width, int height, double freq) {
		if (width < 1 || height < 1) {
			throw new RuntimeException("Graph width and height must be positive integers!");
		}

		char[][] graph = new char[height][width];
		Random rand = new Random();

		for (int i = 0; i < height; ++i) {
			for (int j = 0; j < width; ++j) {
				graph[i][j] = (rand.nextDouble() <= freq ? '#' : '.');
			}
		}

		graph[rand.nextInt(height)][rand.nextInt(width)] = 's';

		while (true) {
			int h = rand.nextInt(height);
			int w = rand.nextInt(width);

			if (graph[h][w] != 's') {
				graph[h][w] = 't';
				break;
			}
		}

		return graph;
	}

	/**
	 * Returns the distance between start and target nodes in the given graph.
	 *
	 * @param path
	 *            The path to measure.
	 * @return The distance between start and target nodes in the given graph.
	 */
	public static double getPathDistance(LinkedList<Node> path) {
		if (path.size() == 0) {
			return 0;
		}

		return path.get(path.size() - 1).getDistance();
	}

	/**
	 * Checks that the given path has edges connecting each successive nodes.
	 *
	 * @param graph
	 *            The graph for the path is for.
	 * @param path
	 *            The path to check.
	 * @return True if the path is valid; otherwise false.
	 */
	public static boolean isValidPath(Graph graph, LinkedList<Node> path) {
		if (path.size() == 1) {
			return false;
		}

		Node prev = null;
		for (Node n : path) {
			if (n.getType() == 'x' || n.getType() == PathFinder.WALL) {
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
