package fi.miko.tiralabra;

import java.util.PriorityQueue;

public class Dijkstra extends PathFinder {
	private Heuristic heuristic;
	
	public Dijkstra(Graph graph, Heuristic heuristic) {
		super(graph);
		initializeEdges();
		
		this.heuristic = heuristic;
	}

	public void findPath() {
		initializeNodes(heuristic);

		PriorityQueue<Node> nodes = new PriorityQueue<>(getGraph().getNodes().size());
		for (Node node : getGraph().getNodes()) {
			nodes.add(node);
		}

		while (!nodes.isEmpty()) {
			Node node = nodes.poll();

			if (node == getTarget()) {
				return;
			}

			for (Edge e : node.getEdges()) {
				double d = e.v.getDistance();

				PathFinder.relax(e.u, e.v, e.w);

				if (e.v.getDistance() < d) {
					nodes.remove(e.v);
					nodes.add(e.v);
				}
			}
		}
	}
}
