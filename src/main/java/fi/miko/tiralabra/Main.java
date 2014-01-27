package fi.miko.tiralabra;

import java.util.List;
import java.util.Random;

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
		double elapsed = (double) (end - start) / 1E9;

		System.out.println(name + ": " + elapsed + "s");

	}

	public static void benchmark() {
		char[][] graph = generateRandom(50, 50, 0.25);
		Graph g = new Graph(graph);
		System.out.println(g);

		BellmanFord f = new BellmanFord(g);
		f.findPath();
		List<Node> path = f.getShortestPath();

		Dijkstra d = new Dijkstra(g, Heuristic.None);
		measure(d, "Dijkstra", path);

		Dijkstra a = new Dijkstra(g, Heuristic.Euclidean);
		measure(a, "A*", path);
	}

	public static void main(String[] args) {
		benchmark();
	}

}