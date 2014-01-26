package fi.miko.tiralabra;

import java.util.PriorityQueue;

public class Dijkstra {
	private Graph graph;

	public Dijkstra(Graph graph) {
		this.graph = graph;
	}

	public void run() {
		GraphUtil.initializeNodes(graph.getNodes());

		PriorityQueue<Node> nodes = new PriorityQueue<>(graph.getNodes().size());
		for (Node node : graph.getNodes()) {
			nodes.add(node);
		}

		while (!nodes.isEmpty()) {
			Node node = nodes.poll();

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
