package fi.miko.tiralabra.algorithms;

import fi.miko.tiralabra.datastructures.MinimumHeap;

/**
 * AStar class uses Dijkstra's algorithm to find the shortest path in the graph. If an heuristic is given the algorithm
 * transforms to A*.
 */
public class AStar extends PathFinder {
	private MinimumHeap<Node> heap;
	private final Heuristic heuristic;

	/**
	 * Creates a new A* pathfinder. The given heuristic is used to estimate the node distances to the target node.
	 *
	 * @param graph
	 *            The graph to search.
	 * @param heuristic
	 *            The heuristic used in distance estimation.
	 */
	public AStar(Graph graph, Heuristic heuristic) {
		super(graph);

		this.heuristic = heuristic;
	}

	@Override
	public void findPath() {
		initializeNodes();
		heap = new MinimumHeap<>();
		heap.insert(getStart());

		while (!heap.isEmpty()) {
			// Select the node with the best distance.
			final Node node = heap.getMin();

			// Mark the node as visited and closed.
			node.setVisited();
			node.setClosed();

			// If the the node is the target node, the shortest path has been found.
			if (node == getTarget()) {
				return;
			}

			// Process all the edges leaving from node to all the neighbours of that node.
			for (Node neighbour : getGraph().getNeighbours(node)) {
				processNode(node, neighbour);
			}
		}
	}

	/**
	 * Process the edge between the given node and the given neighbour.
	 *
	 * @param node
	 *            The node.
	 * @param neighbour
	 *            The neighbour.
	 */
	void processNode(Node node, Node neighbour) {
		// Mark the neighbour visited.
		neighbour.setVisited();

		// Skip the nodes marked wall or closed.
		if (neighbour.getType() == PathFinder.WALL || neighbour.isClosed()) {
			return;
		}

		final double distance = node.getDistance() + getDistance(node, neighbour);

		// Check if the path through this node is shorter than the previous path found for neighbour.
		if (distance < neighbour.getDistance()) {
			// Update the path for the neighbour node.
			neighbour.setDistance(distance);
			neighbour.setNearest(node);

			// If the neighbour is in the heap, the previous distance to the target node has been decreased.
			if (neighbour.isOpen()) {
				// Update the neighbour position in the heap.
				heap.decreaseKey(neighbour);
			}

			// The neighbour hasn't been added to the heap yet.
			if (!neighbour.isOpen()) {
				// Estimate the distance to the target node.
				final int dx = neighbour.getX() - getTarget().getX();
				final int dy = neighbour.getY() - getTarget().getY();
				neighbour.setDistanceEstimate(heuristic.distance(dx, dy));

				// Mark the neighbour as open and add it to the minimum heap.
				neighbour.setOpen();
				heap.insert(neighbour);
			}
		}
	}
}
