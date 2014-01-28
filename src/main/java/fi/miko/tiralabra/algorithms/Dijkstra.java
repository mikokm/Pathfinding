package fi.miko.tiralabra.algorithms;

import java.util.List;
import java.util.PriorityQueue;

/**
 * Dijkstra class uses Dijkstra's algorithm to find the shortest path in the
 * graph. If a heuristic besides Heuristic.None is given, the algorithm
 * transforms to A*.
 */
public class Dijkstra extends PathFinder {
	private Heuristic heuristic;

	/**
	 * Creates a new Dijkstra finder. The given heuristic is used to estimate
	 * the node distances to the target node.
	 * 
	 * @param graph
	 *            The graph to search.
	 * @param heuristic
	 *            The heuristic used in distance estimation.
	 */
	public Dijkstra(Graph graph, Heuristic heuristic) {
		super(graph);
		initializeEdges();

		this.heuristic = heuristic;
	}

	@Override
	public void findPath() {
		initializeNodes(heuristic);

		// Add the initialized nodes to the priority queue.
		List<Node> nodes = getGraph().getNodes();
		PriorityQueue<Node> pq = new PriorityQueue<>(nodes.size());
		for (Node node : nodes) {
			pq.add(node);
		}

		while (!pq.isEmpty()) {
			// Select the node closest to the target node.
			Node node = pq.poll();

			// If the next node is the priority queue is the target node, the
			// shortest path has been found.
			if (node == getTarget()) {
				return;
			}

			// Update the neighbouring node distances.
			for (Edge e : node.getEdges()) {
				// The node is removed and added to priority queue, because
				// decrease-key operation is not supported.
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
