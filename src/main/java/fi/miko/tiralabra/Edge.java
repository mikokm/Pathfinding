package fi.miko.tiralabra;

public class Edge {
	public Node u;
	public Node v;
	public double w;

	public Edge(Node u, Node v, double w) {
		this.u = u;
		this.v = v;
		this.w = w;
	}

	@Override
	public String toString() {
		return u.toString() + " -> " + v.toString() + " : " + w;
	}
}
