package fi.miko.tiralabra.algorithms;

import fi.miko.tiralabra.datastructures.Indexable;

/**
 * Node class contains the node information needed by graph search algorithms.
 */
public class Node implements Comparable<Node>, Indexable {
	private Node nearest = null;

	private char type;
	private double distance;
	private double distanceEstimate;
	private int x, y;

	private boolean open;
	private boolean closed;
	private int heapIndex = -1;

	/**
	 * Creates a new node with the given coordinates.
	 * 
	 * @param x
	 *            The x-coordinate of the node.
	 * @param y
	 *            The y-coordinate of the node.
	 */
	public Node(int x, int y) {
		this.x = x;
		this.y = y;

		reset();
	}

	/**
	 * Creates a new node with the given coordinates and type.
	 * 
	 * @param x
	 *            The x-coordinate of the node.
	 * @param y
	 *            The y-coordinate of the node.
	 * @param type
	 *            The type of the node.
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
	 * 
	 * @return The distance to the start node.
	 */
	public double getDistance() {
		return distance;
	}

	/**
	 * Returns the distance estimate to the target node.
	 * 
	 * @return The distance estimate to the target node.
	 */
	public double getDistanceEstimate() {
		return distanceEstimate;
	}

	/**
	 * Returns the node nearest to the start node that is accessible from this node.
	 * 
	 * @return The node nearest to the start node that is accessible from this node.
	 */
	public Node getNearest() {
		return nearest;
	}

	/**
	 * Returns the type of the node.
	 * 
	 * @return The type of the node.
	 */
	public char getType() {
		return type;
	}

	/**
	 * Returns the x-coordinate of the node.
	 * 
	 * @return The x-coordinate of the node.
	 */
	public int getX() {
		return x;
	}

	/**
	 * Returns the y-coordinate of the node.
	 * 
	 * @return The y-coordinate of the node.
	 */
	public int getY() {
		return y;
	}

	/**
	 * Returns true if the node is closed; otherwise false.
	 * 
	 * @return True if the node is closed; otherwise false.
	 */
	public boolean isClosed() {
		return closed;
	}

	/**
	 * Returns true if the node is open; otherwise false.
	 * 
	 * @return True if the node is open; otherwise false.
	 */
	public boolean isOpen() {
		return open;
	}

	/**
	 * Resets the node data to initial values.
	 */
	public void reset() {
		open = false;
		closed = false;
		distance = Double.MAX_VALUE;
		distanceEstimate = 0;
		nearest = null;
	}

	/**
	 * Marks the node as closed;
	 */
	public void setClosed() {
		closed = true;
	}

	/**
	 * Sets the distance to the start node.
	 * 
	 * @param distance
	 *            The distance to the start node.
	 */
	public void setDistance(double distance) {
		this.distance = distance;
	}

	/**
	 * Sets the estimated distance to the target node.
	 * 
	 * @param distanceEstimate
	 *            The estimated distance to the target node.
	 */
	public void setDistanceEstimate(double distanceEstimate) {
		this.distanceEstimate = distanceEstimate;
	}

	/**
	 * Sets the node nearest to the start node that is accessible from this node.
	 * 
	 * @param nearest
	 *            The node nearest to the start node that is accessible from this node.
	 */
	public void setNearest(Node nearest) {
		this.nearest = nearest;
	}

	/**
	 * Sets the node as open.
	 */
	public void setOpen() {
		open = true;
	}

	/**
	 * Sets the type of the node.
	 * 
	 * @param type
	 *            The node type.
	 */
	public void setType(char type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "[" + x + ", " + y + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (getClass() != obj.getClass()) {
			return false;
		}

		Node other = (Node) obj;

		if (x != other.x || y != other.y) {
			return false;
		}

		return true;
	}

	@Override
	public int getIndex() {
		return heapIndex;
	}

	@Override
	public void setIndex(int index) {
		heapIndex = index;
	}

}