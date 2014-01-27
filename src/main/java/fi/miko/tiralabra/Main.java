package fi.miko.tiralabra;

import java.util.List;
import java.util.Random;

public class Main {
	public static char [][] generateRandom(int width, int height, double freq) {
		char [][] graph = new char[height][width];
		Random rand = new Random();
		
		for(int i = 0; i < height; ++i) {
			for(int j = 0; j < width; ++j) {
				graph[i][j] = (rand.nextDouble() <= freq ? '#' : '.');
			}
		}
		
		graph[0][0] = 's';
		graph[width-1][height-1] = 't';
		
		return graph;
	}
	
	public static void main(String[] args) {
		
		char [][] graph = generateRandom(20, 20, 0.25);
		Graph g = GraphBuilder.buildGraph(graph);
		System.out.println(g);

		double start, end;
/*
		start = System.nanoTime();
		for (int i = 0; i < 100; ++i) {
			BellmanFord.runBellmanFord(g);
		}
		end = System.nanoTime();
		System.out.println("Bellman-Ford: " + (end - start) / 1000000000);
*/
		start = System.nanoTime();
		for (int i = 0; i < 10000; ++i) {
			Dijkstra.runDijkstra(g, Heuristic.None);
		}
		end = System.nanoTime();
		System.out.println("Dijkstra: " + (end - start) / 1000000000);
		
		start = System.nanoTime();
		for (int i = 0; i < 10000; ++i) {
			Dijkstra.runDijkstra(g, Heuristic.Euclidean);
		}
		end = System.nanoTime();
		System.out.println("A*: " + (end - start) / 1000000000);
		
		List<Node> path = GraphUtil.getShortestPath(g);
		
		BellmanFord.runBellmanFord(g);
		List<Node> pathb = GraphUtil.getShortestPath(g);
		assert(path == pathb);
		
		for(Node n : path) {
			n.setType('X');
		}
		
		System.out.println(g);
		
		/*
		for (int i = 0; i < 20; ++i) {
			char[][] graph = generateRandom(100, 100, 0.3);
			Graph g = GraphBuilder.buildGraph(graph);

			//BellmanFord.runBellmanFord(g);
			//List<Node> path1 = GraphUtil.getShortestPath(g);
			
			Dijkstra.runDijkstra(g);
			List<Node> path2 = GraphUtil.getShortestPath(g);
			
			assert(path1 == path2);
		}
		*/
	}

}