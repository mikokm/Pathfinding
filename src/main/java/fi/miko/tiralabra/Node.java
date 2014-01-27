package fi.miko.tiralabra;

import java.util.LinkedList;
import java.util.List;

public class Node implements Comparable<Node> {
	private List<Edge> edges = new LinkedList<Edge>();
	private Node nearest = null;

	private char type = 0;
	private double distance = 0, distanceEstimate = 0;
	private int x, y;

	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Node(int x, int y, char type) {
		this(x, y);
		this.type = type;
	}

	@Override
	public int compareTo(Node node) {
		return Double.compare(distance + this.distanceEstimate, node.distance + node.distanceEstimate);
	}

	public double getDistance() {
		return distance;
	}

	public double getDistanceEstimate() {
		return distanceEstimate;
	}

	public List<Edge> getEdges() {
		return edges;
	}

	public Node getNearest() {
		return nearest;
	}

	public char getType() {
		return type;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public void setDistanceEstimate(double distanceEstimate) {
		this.distanceEstimate = distanceEstimate;
	}

	public void setEdges(List<Edge> edges) {
		this.edges = edges;
	}

	public void setNearest(Node nearest) {
		this.nearest = nearest;
	}

	public void setType(char type) {
		this.type = type;
	}

	public String toString() {
		return "[" + x + ", " + y + "]: " + type;
	}
}