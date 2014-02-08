package fi.miko.tiralabra.algorithms;

import java.util.List;

import fi.miko.tiralabra.datastructures.MinimumHeap;

/**
 * Dijkstra class implements Dijkstra's algorithm. This implementation is taken from Datastructures and algorithms
 * course material. Dijkstra's algorithm in this form is a tree search algorithm and it visits all of the nodes.
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
		final MinimumHeap<Node> heap = new MinimumHeap<>(1);
		for (Node node : nodes) {
			heap.insert(node, Double.MAX_VALUE);
		}

		while (!heap.isEmpty()) {
			// Select the node closest to the target node.
			final Node node = heap.poll();
			node.setVisited();

			if (node == getTarget()) {
				return;
			}

			for (Node neighbour : getGraph().getNeighbours(node)) {
				neighbour.setVisited();

				if (neighbour.getType() == WALL) {
					continue;
				}

				double distance = node.getDistance() + getDistance(node, neighbour);

				if (distance < neighbour.getDistance()) {
					neighbour.setDistance(distance);
					neighbour.setNearest(node);

					heap.decreaseKey(neighbour);
				}
			}
		}
	}
}
