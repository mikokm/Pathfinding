package fi.miko.tiralabra;

import java.util.List;

public class Main {
	public static void main(String[] args) {
		char [][] graph = new char[][] {
				{ 's', '1', '2' },
				{ '#', '#', '1' },
				{ '2', '1', '2' },
				{ '1', '#', '#' },
				{ '2', '1', '2' },
				{ '#', '#', '1' },
				{ '2', '1', '2' },
				{ '1', '#', '#' },
				{ '2', '1', '2' },
				{ '#', '#', '1' },
				{ '2', '2', '1' },
				{ '2', '2', 't' }
		};
		
		
		Graph g = GraphBuilder.buildGraph(graph);
		System.out.println(g);
		
/*		
		double start, end;
		
		start = System.nanoTime();
		for (int i = 0; i < 1000000; ++i) {
			BellmanFord.runBellmanFord(g);
		}
		end = System.nanoTime();
		System.out.println("Bellman-Ford: " + (end - start) / 1000000000);

		start = System.nanoTime();
		for (int i = 0; i < 1000000; ++i) {
			Dijkstra.runDijkstra(g);
		}
		end = System.nanoTime();
		System.out.println("Dijkstra: " + (end - start) / 1000000000);
*/
		
		g = GraphBuilder.buildGraph(graph);
		BellmanFord.runBellmanFord(g);
		List<Node> path1 = GraphUtil.getShortestPath(g);
		System.out.println(path1);
		
		g = GraphBuilder.buildGraph(graph);
		Dijkstra.runDijkstra(g);
		List<Node> path2 = GraphUtil.getShortestPath(g);
		System.out.println(path2);
		 
	}

}