package fi.miko.tiralabra.tiralabra;

public class GraphBuilder {

	public static Graph buildGraph(char[][] graph) {
		Graph g = new Graph(graph[0].length, graph.length);

		for (int y = 0; y < graph.length; ++y) {
			for (int x = 0; x < graph[0].length; ++x) {
				Node n = g.getNode(x, y);
				n.setType(graph[y][x]);

				// System.out.println("Adding node: " + n);
			}
		}

		try {
			g.initializeEdges();
		} catch (IllegalGraphException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return g;
	}
}
