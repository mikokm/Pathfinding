package fi.miko.tiralabra;

import fi.miko.tiralabra.LinkedList;
import java.util.List;

/**
 * Node class contains the node information needed by graph search algorithms.
 */
public class Node implements Comparable<Node> {
	private List<Edge> edges = new LinkedList<Edge>();
	private Node nearest = null;

	private char type = 0;
	private double distance = 0, distanceEstimate = 0;
	private int x, y;

	/**
	 * Creates a new node with the given coordinates.
	 * @param x The x-coordinate of the node.
	 * @param y The y-coordinate of the node.
	 */
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Creates a new node with the given coordinates and type.
	 * @param x The x-coordinate of the node.
	 * @param y The y-coordinate of the node.
	 * @param type The type of the node.
	 */
	public Node(int x, int y, char type) {
		this(x, y);
		this.type = type;
	}


	@Override
	public int compareTo(Node node) {
		return Double.compare(distance + this.distanceEstimate, node.distance + node.distanceEstimate);
	}

	/**
	 * Returns the distance to the start node.
	 * @return The distance to the start node.
	 */
	public double getDistance() {
		return distance;
	}

	/**
	 * Returns the distance estimate to the target node.
	 * @return The distance estimate to the target node.
	 */
	public double getDistanceEstimate() {
		return distanceEstimate;
	}

	/**
	 * Returns a list of edges from this node to the neighbour nodes.
	 * @return A list of edges from this node to the neighbour nodes.
	 */
	public List<Edge> getEdges() {
		return edges;
	}

	/**
	 * Returns the node nearest to the start node that is accessible from this node.
	 * @return The node nearest to the start node that is accessible from this node.
	 */
	public Node getNearest() {
		return nearest;
	}

	/**
	 * Returns the type of the node.
	 * @return The type of the node.
	 */
	public char getType() {
		return type;
	}

	/**
	 * Returns the x-coordinate of the node.
	 * @return The x-coordinate of the node.
	 */
	public int getX() {
		return x;
	}
	/**
	 * Returns the y-coordinate of the node.
	 * @return The y-coordinate of the node.
	 */
	public int getY() {
		return y;
	}

	/**
	 * Sets the distance to the start node.
	 * @param distance The distance to the start node.
	 */
	public void setDistance(double distance) {
		this.distance = distance;
	}

	/**
	 * Sets the estimated distance to the target node.
	 * @param distanceEstimate The estimated distance to the target node.
	 */
	public void setDistanceEstimate(double distanceEstimate) {
		this.distanceEstimate = distanceEstimate;
	}

	/**
	 * Sets the edges for the node.
	 * @param edges The node edges.
	 */
	public void setEdges(List<Edge> edges) {
		this.edges = edges;
	}

	/**
	 * Sets the node nearest to the start node that is accessible from this node.
	 * @param nearest The node nearest to the start node that is accessible from this node.
	 */
	public void setNearest(Node nearest) {
		this.nearest = nearest;
	}

	/**
	 * Sets the type of the node.
	 * @param type The node type.
	 */
	public void setType(char type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "[" + x + ", " + y + "]: " + type;
	}
}