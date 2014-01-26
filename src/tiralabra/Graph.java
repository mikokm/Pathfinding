package tiralabra;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Graph {
	public static final char START = 's';
	public static final char TARGET = 't';
	public static final char WALL = '#';

	private Node[][] graph;
	private Node start = null;
	private Node target = null;

	private ArrayList<Edge> edges = new ArrayList<>();
	private ArrayList<Node> nodes = new ArrayList<>();

	public Graph(int width, int height) {
		assert (width > 0 && height > 0);

		this.graph = new Node[height][width];

		for (int y = 0; y < height; ++y) {
			for (int x = 0; x < width; ++x) {
				Node node = new Node(x, y);

				this.graph[y][x] = node;
				nodes.add(node);
			}
		}
	}

	public ArrayList<Edge> getEdges() {
		return edges;
	}

	public int getGraphHeight() {
		return graph.length;
	}

	public int getGraphWidth() {
		return graph[0].length;
	}

	private List<Node> getNeighbours(Node node) {
		List<Node> list = new ArrayList<>();

		int x0 = node.getX(), y0 = node.getY();

		for (int y = y0 - 1; y <= y0 + 1; ++y) {
			for (int x = x0 - 1; x <= x0 + 1; ++x) {
				// Skip the center node
				if (y == y0 && x == x0) {
					continue;
				}

				// Skip the nodes that are outside the graph
				if (!isWithinGraph(x, y)) {
					continue;
				}

				// Add the node to the neighbour list.
				Node n = graph[y][x];
				list.add(n);
				System.out.println("Found neighbour: " + n);
			}
		}

		return list;
	}

	public Node getNode(int x, int y) {
		assert (isWithinGraph(x, y));

		return graph[y][x];
	}

	public ArrayList<Node> getNodes() {
		return nodes;
	}

	public void initializeEdges() throws IllegalGraphException {
		edges.clear();
		start = null;
		target = null;

		for (Node node : getNodes()) {
			System.out.println("Node: " + node);
			
			if(node.getDistance() < 0) {
				throw new IllegalGraphException("The graph has a node with a negative weight!");
			}

			if (node.getType() == Graph.WALL) {
				continue;
			}

			if (node.getType() == Graph.START) {
				start = node;
			}

			if (node.getType() == Graph.TARGET) {
				target = node;
			}

			for (Node n : getNeighbours(node)) {
				if (n.getType() == Graph.WALL) {
					continue;
				}

				double dx = Math.pow(node.getX() - n.getX(), 2);
				double dy = Math.pow(node.getY() - n.getY(), 2);
				double distance = Math.sqrt(dx + dy);

				Edge e = new Edge(node, n, distance);
				edges.add(e);
				System.out.println("Adding edge: " + e);
			}
		}

		if (start == null || target == null) {
			throw new IllegalGraphException("The graph is missing the start or target node!");
		}
	}

	public void initializeNodes() {
		for (Node n : nodes) {
			if (n.getType() == Graph.START) {
				n.setDistance(0);
			} else {
				n.setDistance(Double.MAX_VALUE);
			}

			n.setNearest(null);
		}
	}

	public boolean isWithinGraph(int x, int y) {
		if (x >= getGraphWidth() || y >= getGraphHeight() || x < 0 || y < 0) {
			return false;
		}

		return true;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int y = 0; y < getGraphHeight(); ++y) {
			for (int x = 0; x < getGraphWidth(); ++x) {
				sb.append(graph[y][x].getType());
			}
			
			// Skip the last new line.
			if(y < getGraphHeight() - 1) {
				sb.append('\n');
			}
		}

		return sb.toString();
	}

	public ArrayList<Node> getPath() {
		ArrayList<Node> path = new ArrayList<>();

		if (start == null || target == null) {
			System.out.println("Error: No start or target found!");
			return null;
		}

		Node node = target.getNearest();

		Stack<Node> stack = new Stack<>();
		while (node != start) {
			if (node == null) {
				System.out.println("No path found!");
				return null;
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
}
