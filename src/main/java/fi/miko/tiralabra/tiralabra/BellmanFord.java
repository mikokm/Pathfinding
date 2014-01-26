package fi.miko.tiralabra.tiralabra;

public class BellmanFord {
	private Graph graph;
	
	public BellmanFord(Graph graph) {
		this.graph = graph;
	}
	
	public void run() {
		GraphUtil.initializeNodes(graph.getNodes());
		
		for(int i = 0; i < graph.getNodes().size() - 1; ++i) {
			for(Edge e : graph.getEdges()) {
				//System.out.println(e);	
				GraphUtil.relax(e.u, e.v, e.w);
			}
		}
	}
}
