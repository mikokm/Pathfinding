package fi.miko.tiralabra;

import java.util.PriorityQueue;

public class Dijkstra {
	public static void runDijkstra(Graph graph) {
		GraphUtil.initializeNodes(graph, Heuristic.Euclidean);

		PriorityQueue<Node> nodes = new PriorityQueue<>(graph.getNodes().size());
		for (Node node : graph.getNodes()) {
			nodes.add(node);
		}

		while (!nodes.isEmpty()) {
			Node node = nodes.poll();

			// TODO: Test that this works.
			if (node == graph.getTarget()) {
				return;
			}

			for (Edge e : node.getEdges()) {
				double d = e.v.getDistance();

				GraphUtil.relax(e.u, e.v, e.w);

				if (e.v.getDistance() < d) {
					nodes.remove(e.v);
					nodes.add(e.v);
				}
			}
		}
	}
}
