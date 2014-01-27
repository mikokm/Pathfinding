package fi.miko.tiralabra;

public class BellmanFord extends PathFinder {
	public BellmanFord(Graph graph) {
		super(graph);
		initializeEdges();
	}

	@Override
	public void findPath() {
		initializeNodes(Heuristic.None);

		for (int i = 0; i < getGraph().getNodes().size() - 1; ++i) {
			for (Edge e : getEdges()) {
				PathFinder.relax(e.u, e.v, e.w);
			}
		}
	}
}
