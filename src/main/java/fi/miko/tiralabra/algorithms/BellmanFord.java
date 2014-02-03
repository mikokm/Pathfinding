package fi.miko.tiralabra.algorithms;

import java.util.List;

import fi.miko.tiralabra.datastructures.LinkedList;

/**
 * BellmanFord class implements Bellman-Ford's algorithm. The implementation is taken from Datastructures and algorithms
 * course material.
 */
public class BellmanFord extends PathFinder {
	private final List<Edge> edges = new LinkedList<Edge>();

	public BellmanFord(Graph graph) {
		super(graph);

		initializeEdges();
	}

	@Override
	public void findPath() {
		initializeNodes();

		final List<Node> nodes = getGraph().getNodes();

		for (int i = 0; i < nodes.size() - 1; ++i) {
			for (Edge e : edges) {
				relax(e.u, e.v, e.w);
			}
		}
	}

	/**
	 * Initializes the list of edges for Bellman-Ford algorithm.
	 */
	public void initializeEdges() {
		edges.clear();

		for (Node node : getGraph().getNodes()) {
			for (Node neighbour : getGraph().getNeighbours(node)) {
				if (neighbour.getType() == PathFinder.WALL) {
					continue;
				}

				// Add the edge to the list of all edges.
				Edge e = new Edge(node, neighbour, getDistance(node, neighbour));
				edges.add(e);
			}
		}
	}

	/**
	 * Replaces the current path to the start node from node v with a shorter path through node u if such path is
	 * possible.
	 * 
	 * @param u
	 *            The node that the path will go through.
	 * @param v
	 *            The node that is being inspected.
	 * @param w
	 *            The distance between u and v (edge weight).
	 */
	private void relax(Node u, Node v, double w) {
		if (u.getDistance() == Double.MAX_VALUE) {
			return;
		}

		double uw = u.getDistance() + w;

		if (v.getDistance() > uw) {
			v.setDistance(uw);
			v.setNearest(u);
		}
	}
}
