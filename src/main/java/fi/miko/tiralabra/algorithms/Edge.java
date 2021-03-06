package fi.miko.tiralabra.algorithms;

/**
 * Edge class represents a single, directed edge in the graph.
 */
public class Edge {
	final public Node u;
	final public Node v;
	final public double w;

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
		if (u == null || v == null) {
			throw new RuntimeException("Cannot create edge with null node!");
		}

		if (w == -1) {
			throw new RuntimeException("Cannot create edge with negative weight!");
		}

		this.u = u;
		this.v = v;
		this.w = w;
	}

	@Override
	public String toString() {
		return u.toString() + " -> " + v.toString() + " : " + w;
	}
}
