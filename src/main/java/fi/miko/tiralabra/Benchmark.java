package fi.miko.tiralabra;

import fi.miko.tiralabra.algorithms.AStar;
import fi.miko.tiralabra.algorithms.BellmanFord;
import fi.miko.tiralabra.algorithms.Dijkstra;
import fi.miko.tiralabra.algorithms.Graph;
import fi.miko.tiralabra.algorithms.GraphUtils;
import fi.miko.tiralabra.algorithms.Heuristic;
import fi.miko.tiralabra.algorithms.Node;
import fi.miko.tiralabra.algorithms.PathFinder;
import fi.miko.tiralabra.datastructures.LinkedList;

public class Benchmark {
	private int iterations;

	private int getVisitedCount(LinkedList<Node> nodes) {
		int visited = 0;
		for (Node node : nodes) {
			if (node.isVisited()) {
				visited++;
			}
		}

		return visited;
	}

	private void measure(PathFinder f, String name) {
		LinkedList<Node> path = null;

		long start = System.nanoTime();

		for (int i = 0; i < iterations; ++i) {
			f.findPath();
			path = f.getShortestPath();
		}

		long end = System.nanoTime();
		double elapsed = (end - start) / 1E9;

		int visited = getVisitedCount(f.getGraph().getNodes());

		System.out.println(name + ": " + elapsed + "s");
		System.out.println("Path length: " + path.size() + " cost: " + GraphUtils.getPathDistance(path) + " visited: "
				+ visited);
		System.out.println();
	}

	public void run(int width, int height, double freq, int iterations) {
		this.iterations = iterations;

		char[][] map;

		while (true) {
			map = GraphUtils.generateRandom(width, height, freq);
			PathFinder f = new AStar(new Graph(map), Heuristic.Euclidean);
			f.findPath();

			if (f.getShortestPath().isEmpty() == false) {
				break;
			}
		}

		measure(new Dijkstra(new Graph(map)), "Dijkstra");
		measure(new AStar(new Graph(map), Heuristic.None), "A* No Heuristic");
		measure(new AStar(new Graph(map), Heuristic.Euclidean), "A* Euclidean");
		measure(new BellmanFord(new Graph(map)), "Bellman-Ford");
	}
}
