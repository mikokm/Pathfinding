package fi.miko.tiralabra;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * PathFinder is a helper class that is used to implement Dijkstra and Bellman-Ford algorithms.
 */
abstract class PathFinder {
	public static final char START = 's';
	public static final char TARGET = 't';
	public static final char WALL = '#';
	private static final double SQRT_2 = Math.sqrt(2);

	private Graph graph;
	private List<Edge> edges = new LinkedList<>();

	private Node start = null;
	private Node target = null;

	/**
	 * Creates a new pathfinder that searches in the given graph.
	 * @param graph The graph to search paths in.
	 */
	public PathFinder(Graph graph) {
		this.graph = graph;
	}

	/**
	 * Returns the list of edges for all nodes of the graph.
	 * @return The list of edges for all nodes of the graph.
	 */
	protected List<Edge> getEdges() {
		return edges;
	}

	/**
	 * Returns the graph used by this pathfinder.
	 * @return The graph used by this pathfinder.
	 */
	protected Graph getGraph() {
		return graph;
	}

	/**
	 * Finds the shortest path between the start and target nodes.
	 */
	abstract void findPath();

	/**
	 * Returns the list of nodes containing the shortest path between the start and target nodes.
	 * If there isn't a path, an empty list is returned.
	 * @return The list of nodes in between the start and target nodes, or an empty list.
	 */
	public List<Node> getShortestPath() {
		assert (start != null && target != null);

		List<Node> path = new LinkedList<>();
		Stack<Node> stack = new Stack<>();

		Node node = target.getNearest();
		while (node != start) {
			if (node == null) {
				System.out.println("No path found!");
				return path;
			}

			stack.push(node);
			node = node.getNearest();
		}

		System.out.println("Path length is " + stack.size() + " nodes.");

		path.add(start);

		while (!stack.isEmpty()) {
			path.add(stack.pop());
		}

		path.add(target);

		return path;
	}

	/**
	 * Returns the start node.
	 * @return The start node.
	 */
	public Node getStart() {
		return start;
	}

	/**
	 * Returns the target node.
	 * @return The target node.
	 */
	public Node getTarget() {
		return target;
	}

	/**
	 * Initializes edge lists for pathfinding algorithms.
	 */
	public void initializeEdges() {
		edges.clear();

		start = null;
		target = null;

		for (Node node : graph.getNodes()) {
			if (node.getDistance() < 0) {
				throw new IllegalGraphException("The graph has a node with a negative weight!");
			}

			switch (node.getType()) {
			case PathFinder.START:
				start = node;
				break;
			case PathFinder.TARGET:
				target = node;
				break;
			case PathFinder.WALL:
				continue;
			}

			List<Edge> nodeEdges = new LinkedList<Edge>();

			for (Node n : graph.getNeighbours(node)) {
				if (n.getType() == PathFinder.WALL) {
					continue;
				}

				// Calculate the diagonal movement weight.
				double distance = (node.getX() != n.getX() && node.getY() != n.getY() ? SQRT_2 : 1);

				Edge e = new Edge(node, n, distance);

				// Add the edge to the list of all edges.
				edges.add(e);

				// Add the edge to the node edges.
				nodeEdges.add(e);

				// System.out.println("Adding edge: " + e);
			}

			node.setEdges(nodeEdges);
		}

		if (start == null || target == null) {
			throw new IllegalGraphException("The graph is missing the start or target node!");
		}
	}

	/**
	 * Initializes nodes with initial values.
	 * The given heuristic is used to calculate the estimated distance to the target node.
	 * @param heuristic The heuristic to use for target distance estimation.
	 */
	protected void initializeNodes(Heuristic heuristic) {
		for (Node n : graph.getNodes()) {
			n.setDistance(Double.MAX_VALUE);
			n.setDistanceEstimate(heuristic.distance(target.getX() - n.getX(), target.getY() - n.getY()));
			n.setNearest(null);
		}

		start.setDistance(0);
		start.setDistanceEstimate(0);
	}

	/**
	 * Sets the graph used by the pathfinder.
	 * @param graph The graph to search paths in.
	 */
	protected void setGraph(Graph graph) {
		this.graph = graph;
	}

	/**
	 * Sets the start node.
	 * @param start The start node.
	 */
	public void setStart(Node start) {
		this.start = start;
	}

	/**
	 * Sets the target node.
	 * @param target The target node.
	 */
	public void setTarget(Node target) {
		this.target = target;
	}

	/**
	 * Replaces the current path to the start node from node v with
	 * a shorter path through node u if such path is possible. 
	 * @param u The node that the path will go through.
	 * @param v The node that is being inspected.
	 * @param w The distance between u and v (edge weight).
	 */
	protected static void relax(Node u, Node v, double w) {
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
