package tiralabra;

public class BellmanFord {
	private Graph graph;
	
	public BellmanFord(Graph graph) {
		this.graph = graph;
	}
	
	public void run() {
		graph.initializeNodes();
		
		for(int i = 0; i < graph.getNodes().size() - 1; ++i) {
			for(Edge e : graph.getEdges()) {
				//System.out.println(e);	
				relax(e.u, e.v, e.w);
			}
		}
	}

	private void relax(Node u, Node v, double w) {
		//System.out.println("v: " + v.getDistance() + " u:  "+ u.getDistance());
		
		double uw = u.getDistance() + w;
		
		if(u.getDistance() == Double.MAX_VALUE) {
			return;
		}

		if(v.getDistance() > uw) {
			v.setDistance(uw);
			v.setNearest(u);
		}
	}
}
