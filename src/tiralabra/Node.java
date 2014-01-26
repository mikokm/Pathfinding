package tiralabra;

import java.util.ArrayList;
import java.util.List;

public class Node {
	private List<Edge> edges = new ArrayList<Edge>();
	private double distance;
	private Node nearest;
	
	private char type;
	private int x;
	private int y;
	
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
		
		this.nearest = null;
		this.distance = Double.MAX_VALUE;
		this.type = 0;
	}
	
	public double getDistance() {
		return distance;
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

	public void setDistance(double maxValue) {
		this.distance = maxValue;
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

	public List<Edge> getEdges() {
		return edges;
	}

	public void setEdges(List<Edge> edges) {
		this.edges = edges;
	}
}