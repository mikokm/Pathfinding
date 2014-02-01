package fi.miko.tiralabra.algorithms;

import java.util.List;
import java.util.PriorityQueue;

/**
 * Dijkstra class uses Dijkstra's algorithm to find the shortest path in the graph. This algorithm implementation is
 * taken from Tietorakenteet ja algoritmit course material. Dijkstra's algorithm in this form is a tree search algorithm
 * and it visits all of the nodes.
 */
public class Dijkstra extends PathFinder {
	/**
	 * Creates a new Dijkstra finder.
	 * 
	 * @param graph
	 *            The graph to search.
	 */
	public Dijkstra(Graph graph) {
		super(graph);
	}

	@Override
	public void findPath() {
		initializeNodes();

		// Add the initialized nodes to the priority queue.
		final List<Node> nodes = getGraph().getNodes();
		final PriorityQueue<Node> pq = new PriorityQueue<>(nodes.size());
		for (Node node : nodes) {
			pq.add(node);
		}

		while (!pq.isEmpty()) {
			// Select the node closest to the target node.
			final Node node = pq.poll();

			if (node == getTarget()) {
				return;
			}

			for (Node neighbour : getGraph().getNeighbours(node)) {
				if (neighbour.getType() == WALL) {
					continue;
				}

				double distance = node.getDistance() + getDistance(node, neighbour);

				if (distance < neighbour.getDistance()) {
					neighbour.setDistance(distance);
					neighbour.setNearest(node);

					pq.remove(neighbour);
					pq.add(neighbour);
				}
			}
		}
	}
}
