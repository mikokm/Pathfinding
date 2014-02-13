package fi.miko.tiralabra.algorithms;

import java.util.Stack;

import fi.miko.tiralabra.datastructures.LinkedList;

/**
 * PathFinder is a helper class that is used to implement Dijkstra and Bellman-Ford algorithms.
 */
public abstract class PathFinder {
	private static final double SQRT_2 = Math.sqrt(2);
	public static final char START = 's';
	public static final char TARGET = 't';

	public static final char WALL = '#';

	private Graph graph;
	private Node start = null;
	private Node target = null;

	/**
	 * Creates a new pathfinder that searches in the given graph.
	 * 
	 * @param graph
	 *            The graph to search paths in.
	 */
	public PathFinder(Graph graph) {
		this.graph = graph;
	}

	/**
	 * Finds the shortest path between the start and target nodes.
	 */
	public abstract void findPath();

	/**
	 * Returns the distance between the adjacent nodes.
	 * 
	 * @param n1
	 *            First node.
	 * @param n2
	 *            Second node.
	 * @return The distance between the adjacent nodes.
	 */
	public double getDistance(Node n1, Node n2) {
		final int dx = Math.abs(n1.getX() - n2.getX());
		final int dy = Math.abs(n1.getY() - n2.getY());

		if (dx > 1 || dy > 1) {
			throw new RuntimeException("Called getDistance() on non-adjacent nodes!");
		}

		return (dx != 0 && dy != 0 ? SQRT_2 : 1);
	}

	/**
	 * Returns the graph used by this pathfinder.
	 * 
	 * @return The graph used by this pathfinder.
	 */
	public Graph getGraph() {
		return graph;
	}

	/**
	 * Returns the list of nodes containing the shortest path between the start and target nodes. If there isn't a path,
	 * an empty list is returned.
	 * 
	 * @return The list of nodes in between the start and target nodes, or an empty list.
	 */
	public LinkedList<Node> getShortestPath() {
		LinkedList<Node> path = new LinkedList<>();

		if (start == null || target == null) {
			throw new RuntimeException("No start or target node specified!");
		}

		Stack<Node> stack = new Stack<>();

		Node node = target.getNearest();
		while (node != start) {
			if (node == null) {
				return path;
			}

			stack.push(node);
			node = node.getNearest();
		}

		path.add(start);

		while (!stack.isEmpty()) {
			path.add(stack.pop());
		}

		path.add(target);

		return path;
	}

	/**
	 * Returns the start node.
	 * 
	 * @return The start node.
	 */
	public Node getStart() {
		return start;
	}

	/**
	 * Returns the target node.
	 * 
	 * @return The target node.
	 */
	public Node getTarget() {
		return target;
	}

	/**
	 * Initializes the graph nodes with initial values.
	 */
	protected void initializeNodes() {
		for (Node n : graph.getNodes()) {
			n.reset();

			if (n.getType() == START) {
				start = n;
				start.setDistance(0);
				start.setDistanceEstimate(0);
			}

			if (n.getType() == TARGET) {
				target = n;
			}
		}

		if (start == null || target == null) {
			throw new RuntimeException("No start or target node specified!");
		}
	}

	/**
	 * Sets the graph used by the pathfinder.
	 * 
	 * @param graph
	 *            The graph to search paths in.
	 */
	public void setGraph(Graph graph) {
		this.graph = graph;
	}

	/**
	 * Sets the start node.
	 * 
	 * @param start
	 *            The start node.
	 */
	public void setStart(Node start) {
		this.start = start;
	}

	/**
	 * Sets the target node.
	 * 
	 * @param target
	 *            The target node.
	 */
	public void setTarget(Node target) {
		this.target = target;
	}
}
