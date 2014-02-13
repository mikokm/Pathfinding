package fi.miko.tiralabra.algorithms;

import fi.miko.tiralabra.datastructures.LinkedList;

/**
 * Graph class manages a grid type graph.
 */
public class Graph {
	private Node[][] graph;
	private final LinkedList<Node> nodes = new LinkedList<>();

	/**
	 * Creates a new graph and sets the content from the given map.
	 * 
	 * @param map
	 *            An array containing the graph data.
	 */
	public Graph(char[][] map) {
		setMap(map);
	}

	/**
	 * Allocates memory for node array used to store the graph data.
	 * 
	 * @param width
	 *            The width of the graph.
	 * @param height
	 *            The height of the graph.
	 */
	private void createNodes(int width, int height) {
		graph = new Node[height][width];
	}

	/**
	 * Returns the height of the graph.
	 * 
	 * @return The height of the graph.
	 */
	public int getHeight() {
		return graph.length;
	}

	/**
	 * Returns a list of nodes containing the neighbours for the given node.
	 * 
	 * @param node
	 *            The node to get neighbours for.
	 * @return A list of nodes containing the neighbours for the given node.
	 */
	public LinkedList<Node> getNeighbours(Node node) {
		LinkedList<Node> list = new LinkedList<>();

		int x0 = node.getX(), y0 = node.getY();

		if (!isWithinGraph(x0, y0)) {
			return list;
		}

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
				list.add(graph[y][x]);
			}
		}

		return list;
	}

	/**
	 * Returns the node with the given (x, y) coordinates.
	 * 
	 * @param x
	 *            The x-coordinate of the node.
	 * @param y
	 *            The y-coordinate of the node.
	 * @return The node with the given (x, y) coordinates.
	 */
	public Node getNode(int x, int y) {
		return graph[y][x];
	}

	/**
	 * Returns the list of all the nodes in the graph.
	 * 
	 * @return The list of all the nodes in the graph.
	 */
	public LinkedList<Node> getNodes() {
		return nodes;
	}

	/**
	 * Returns the width of the graph.
	 * 
	 * @return The width of the graph.
	 */
	public int getWidth() {
		return graph[0].length;
	}

	/**
	 * Returns true if the point (x, y) within the graph; false otherwise.
	 * 
	 * @param x
	 *            The x-coordinate to check.
	 * @param y
	 *            The y-coordinate to check.
	 * @return true if the point (x, y) within the graph; false otherwise.
	 */
	public boolean isWithinGraph(int x, int y) {
		if (x >= getWidth() || y >= getHeight() || x < 0 || y < 0) {
			return false;
		}

		return true;
	}

	/**
	 * Sets the graph content from the given map. The previous content is replaced.
	 * 
	 * @param map
	 *            The graph content.
	 */
	public void setMap(char[][] map) {
		int height = map.length;
		int width = map[0].length;

		if (width <= 0 || height <= 0) {
			throw new RuntimeException("Cannot create graph with zero height or width!");
		}

		createNodes(width, height);

		for (int y = 0; y < getHeight(); ++y) {
			for (int x = 0; x < getWidth(); ++x) {
				Node node = new Node(x, y, map[y][x]);
				nodes.add(node);
				graph[y][x] = node;
			}
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int y = 0; y < getHeight(); ++y) {
			for (int x = 0; x < getWidth(); ++x) {
				sb.append(graph[y][x].getType());
			}

			// Skip the last new line.
			if (y < getHeight() - 1) {
				sb.append('\n');
			}
		}

		return sb.toString();
	}
}
