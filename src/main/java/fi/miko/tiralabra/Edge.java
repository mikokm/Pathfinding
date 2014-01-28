package fi.miko.tiralabra;

/**
 * Edge class represents a single, directed edge in the graph.
 */
public class Edge {
	public Node u;
	public Node v;
	public double w;

	/**
	 * Creates a new edge from the node u to node v with the weight w.
	 *
	 * @param u
	 *            The source node.
	 * @param v
	 *            The destination node.
	 * @param w
	 *            The edge weight.
	 */
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
