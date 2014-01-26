package fi.miko.tiralabra;

public class BellmanFord {
	public static void runBellmanFord(Graph graph) {
		GraphUtil.initializeNodes(graph, Heuristic.None);

		for (int i = 0; i < graph.getNodes().size() - 1; ++i) {
			for (Edge e : graph.getEdges()) {
				GraphUtil.relax(e.u, e.v, e.w);
			}
		}
	}
}
