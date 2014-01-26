package tiralabra;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;

public class Dijkstra {
	private Graph graph;
	private Set<Node> set = new TreeSet<>();
	
	public Dijkstra(Graph graph) {
		this.graph = graph;
	}
	
	public void run() {
		graph.initializeNodes();
		
		ArrayList<Node> nodes = graph.getNodes();
		while(!nodes.isEmpty()) {
			Node node = nodes.get(0);
			
			for(Node n : nodes) {
				if(n.getDistance() < node.getDistance()) {
					node = n;
				}
			}
			
			nodes.remove(node);
			
			for(Edge e : node.getEdges()) {
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
