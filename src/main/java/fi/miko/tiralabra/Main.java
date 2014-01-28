package fi.miko.tiralabra;

import java.util.List;
import java.util.Random;

import fi.miko.tiralabra.algorithms.BellmanFord;
import fi.miko.tiralabra.algorithms.Dijkstra;
import fi.miko.tiralabra.algorithms.Graph;
import fi.miko.tiralabra.algorithms.Heuristic;
import fi.miko.tiralabra.algorithms.Node;
import fi.miko.tiralabra.algorithms.PathFinder;

public class Main {
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

	public static void measure(PathFinder f, String name, List<Node> path) {
		long start = System.nanoTime();

		for (int i = 0; i < 1000; ++i) {
			f.findPath();

			assert (f.getShortestPath() == path);
		}

		long end = System.nanoTime();
		double elapsed = (end - start) / 1E9;

		System.out.println(name + ": " + elapsed + "s");

	}

	public static void benchmark() {
		char[][] graph = generateRandom(100, 100, 0.30);
		Graph g = new Graph(graph);
		System.out.println(g);

		BellmanFord f = new BellmanFord(g);
		Dijkstra d = new Dijkstra(g, Heuristic.None);
		Dijkstra a = new Dijkstra(g, Heuristic.Diagonal);
		Dijkstra a2 = new Dijkstra(g, Heuristic.Euclidean);

		f.findPath();
		List<Node> path = f.getShortestPath();
		if (path.isEmpty()) {
			return;
		}

		measure(d, "Dijkstra", path);
		measure(a, "A* Diagonal", path);
		measure(a2, "A* Euclidean", path);
	}

	public static void main(String[] args) {
		benchmark();
	}

}