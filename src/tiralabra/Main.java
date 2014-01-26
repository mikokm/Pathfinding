package tiralabra;

import java.util.ArrayList;

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
		
		/*
		Graph g = GraphBuilder.buildGraph(graph);
		System.out.println(g);
		*/
		
		double start, end, init;
		
		start = System.nanoTime();
		for(int i = 0; i < 100000; ++i) {
			Graph b = GraphBuilder.buildGraph(graph);
		}
		end = System.nanoTime();
		init = end-start;
		
		start = System.nanoTime();
		for(int i = 0; i < 100000; ++i) {
			BellmanFord b = new BellmanFord(GraphBuilder.buildGraph(graph));
			b.run();
		}
		end = System.nanoTime();
		System.out.println( (end-start-init)/1000000000 );
		
		
		start = System.nanoTime();
		for(int i = 0; i < 100000; ++i) {
			Dijkstra d = new Dijkstra(GraphBuilder.buildGraph(graph));
			d.run();
		}
		end = System.nanoTime();
		System.out.println( (end-start-init)/1000000000 );
		
		/*
		Dijkstra d = new Dijkstra(g);
		d.run();
		
		ArrayList<Node> path = g.getPath();
		System.out.println(path);
		*/
	}

}