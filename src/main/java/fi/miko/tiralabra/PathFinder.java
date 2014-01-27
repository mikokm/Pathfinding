package fi.miko.tiralabra;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

abstract class PathFinder {
	public static final char START = 's';
	public static final char TARGET = 't';
	public static final char WALL = '#';
	private static final double SQRT_2 = Math.sqrt(2);

	private Graph graph;
	private List<Edge> edges = new LinkedList<>();

	private Node start = null;
	private Node target = null;

	public PathFinder(Graph graph) {
		this.graph = graph;
	}

	protected List<Edge> getEdges() {
		return edges;
	}

	protected Graph getGraph() {
		return graph;
	}
	
	abstract void findPath();

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

	public Node getStart() {
		return start;
	}

	public Node getTarget() {
		return target;
	}

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

	protected void initializeNodes(Heuristic heuristic) {
		for (Node n : graph.getNodes()) {
			n.setDistance(Double.MAX_VALUE);
			n.setDistanceEstimate(heuristic.distance(target.getX() - n.getX(), target.getY() - n.getY()));
			n.setNearest(null);
		}

		start.setDistance(0);
		start.setDistanceEstimate(0);
	}

	protected void setGraph(Graph graph) {
		this.graph = graph;
	}

	public void setStart(Node start) {
		this.start = start;
	}

	public void setTarget(Node target) {
		this.target = target;
	}

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
