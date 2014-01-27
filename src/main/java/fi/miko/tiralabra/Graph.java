package fi.miko.tiralabra;

import java.util.LinkedList;
import java.util.List;

public class Graph {
	private Node[][] graph;
	private LinkedList<Node> nodes = new LinkedList<>();

	public Graph(char[][] map) {
		setMap(map);
	}

	public Graph(int width, int height) {
		createNodes(width, height);
	}

	private void createNodes(int width, int height) {
		assert (width > 0 && height > 0);
		graph = new Node[height][width];
	}

	public int getGraphHeight() {
		return graph.length;
	}

	public int getGraphWidth() {
		return graph[0].length;
	}

	public List<Node> getNeighbours(Node node) {
		List<Node> list = new LinkedList<>();

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
				list.add(graph[y][x]);
			}
		}

		return list;
	}

	public Node getNode(int x, int y) {
		assert (isWithinGraph(x, y));
		return graph[y][x];
	}

	public LinkedList<Node> getNodes() {
		return nodes;
	}

	public boolean isWithinGraph(int x, int y) {
		if (x >= getGraphWidth() || y >= getGraphHeight() || x < 0 || y < 0) {
			return false;
		}

		return true;
	}

	public void setMap(char[][] map) {
		createNodes(map.length, map[0].length);

		for (int y = 0; y < getGraphHeight(); ++y) {
			for (int x = 0; x < getGraphWidth(); ++x) {
				Node node = new Node(x, y, map[y][x]);
				nodes.add(node);
				graph[y][x] = node;
			}
		}
	}

	public void setNode(int x, int y, Node node) {
		assert (isWithinGraph(x, y));
		graph[y][x] = node;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int y = 0; y < getGraphHeight(); ++y) {
			for (int x = 0; x < getGraphWidth(); ++x) {
				sb.append(graph[y][x].getType());
			}

			// Skip the last new line.
			if (y < getGraphHeight() - 1) {
				sb.append('\n');
			}
		}

		return sb.toString();
	}
}
