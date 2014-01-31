package fi.miko.tiralabra.algorithms;

import java.util.PriorityQueue;

/**
 * AStar class uses Dijkstra's algorithm to find the shortest path in the graph. If an heuristic is given the algorithm
 * transforms to A*.
 */
public class AStar extends PathFinder {
	private final Heuristic heuristic;
	private PriorityQueue<Node> pq;

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
		pq = new PriorityQueue<>(getGraph().getNodes().size());

		pq.add(getStart());

		while (!pq.isEmpty()) {
			// Select the node with the best distance.
			final Node node = pq.poll();

			node.setClosed();

			// If the the node is the target node, the shortest path has been found.
			if (node == getTarget()) {
				return;
			}

			for (Node neighbour : getGraph().getNeighbours(node)) {
				processNode(node, neighbour);
			}
		}
	}

	void processNode(Node node, Node neighbour) {
		// If the node is wall or if it is in the closed set, skip it.
		if (neighbour.getType() == PathFinder.WALL || neighbour.isClosed()) {
			return;
		}

		// Calculate the edge weight
		final int ex = node.getX() - neighbour.getX();
		final int ey = node.getY() - neighbour.getY();
		double distance = node.getDistance() + (ex != 0 && ey != 0 ? SQRT_2 : 1);

		if (distance < neighbour.getDistance()) {
			neighbour.setDistance(distance);
			neighbour.setNearest(node);

			if (neighbour.isOpen()) {
				pq.remove(neighbour);
				pq.add(neighbour);
			}

			if (!neighbour.isOpen()) {
				// Estimate the distance to the target node.
				final int dx = neighbour.getX() - getTarget().getX();
				final int dy = neighbour.getY() - getTarget().getY();
				neighbour.setDistanceEstimate(heuristic.distance(dx, dy));

				neighbour.setOpen();
				pq.add(neighbour);
			}
		}
	}
}
