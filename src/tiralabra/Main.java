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
		

		Graph g = GraphBuilder.buildGraph(graph);
		System.out.println(g);
		
		BellmanFord b = new BellmanFord(g);
		b.run();
		
		ArrayList<Node> path = g.getPath();
		System.out.println(path);
	}

}