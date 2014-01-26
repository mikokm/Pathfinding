package tiralabra;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Dijkstra {
	private Graph graph;
	
	public Dijkstra(Graph graph) {
		this.graph = graph;
	}
	
	public void runWithoutHeap() {
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
	
	public void run() {
		graph.initializeNodes();
		
		PriorityQueue<Node> nodes = new PriorityQueue<>(graph.getNodes().size(), new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return Double.compare(o1.getDistance(), o2.getDistance());
			}
		});
		
		for(Node node : graph.getNodes()) {
			nodes.add(node);
		}
		
		while(!nodes.isEmpty()) {
			Node node = nodes.poll();
			
			for(Edge e : node.getEdges()) {
				double d = e.v.getDistance();
				relax(e.u, e.v, e.w);
				if(e.v.getDistance() < d) {
					nodes.remove(e.v);
					nodes.add(e.v);
				}
			}
		}
	}
	
	private void relax(Node u, Node v, double w) {
		//System.out.println("v: " + v.getDistance() + " u:  "+ u.getDistance());
		
		if(u.getDistance() == Double.POSITIVE_INFINITY) {
			return;
		}
		
		double uw = u.getDistance() + w;

		if(v.getDistance() > uw) {
			v.setDistance(uw);
			v.setNearest(u);
		}
	}
}
