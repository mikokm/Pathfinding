package fi.miko.tiralabra.algorithms;

import java.util.List;
import java.util.PriorityQueue;

public class Dijkstra extends PathFinder {
	private Heuristic heuristic;

	public Dijkstra(Graph graph, Heuristic heuristic) {
		super(graph);
		initializeEdges();

		this.heuristic = heuristic;
	}

	@Override
	public void findPath() {
		initializeNodes(heuristic);

		List<Node> nodes = getGraph().getNodes();
		PriorityQueue<Node> pq = new PriorityQueue<>(nodes.size());
		for (Node node : nodes) {
			pq.add(node);
		}

		while (!pq.isEmpty()) {
			Node node = pq.poll();

			if (node == getTarget()) {
				return;
			}

			for (Edge e : node.getEdges()) {
				double d = e.v.getDistance();

				PathFinder.relax(e.u, e.v, e.w);

				if (e.v.getDistance() < d) {
					pq.remove(e.v);
					pq.add(e.v);
				}
			}
		}
	}
}
