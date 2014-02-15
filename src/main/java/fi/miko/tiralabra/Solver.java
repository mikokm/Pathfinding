package fi.miko.tiralabra;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import fi.miko.tiralabra.algorithms.AStar;
import fi.miko.tiralabra.algorithms.Graph;
import fi.miko.tiralabra.algorithms.GraphUtils;
import fi.miko.tiralabra.algorithms.Heuristic;
import fi.miko.tiralabra.algorithms.Node;
import fi.miko.tiralabra.algorithms.PathFinder;
import fi.miko.tiralabra.datastructures.LinkedList;

public class Solver {

	public Solver(String filename) {
		char[][] map = readMapFromFile(filename);

		if (map == null) {
			System.out.println("Failed to open the file: " + filename);
			return;
		}

		solve(map);
	}

	public Solver() {
	}

	public void solve(char[][] map) {
		System.out.println("*** Solving the map ***");
		Graph graph = new Graph(map);
		System.out.println(graph);

		AStar f = new AStar(graph, Heuristic.Euclidean);
		f.findPath();

		LinkedList<Node> path = f.getShortestPath();
		if (path.size() == 0) {
			System.out.println("Can't find path from start to the target!");
			return;
		}

		System.out.println("Found shortest path of length: " + path.size() + " and distance: "
				+ (int) GraphUtils.getPathDistance(path));

		for (Node n : path) {
			if (n.getType() != PathFinder.START && n.getType() != PathFinder.TARGET) {
				n.setType('o');
			}
		}

		System.out.println(graph);
	}

	private char[][] readMapFromFile(String filename) {
		System.out.println(System.getProperty("user.dir"));
		LinkedList<String> lines = new LinkedList<>();

		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			while (reader.ready()) {
				String line = reader.readLine();
				lines.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (lines.isEmpty() || lines.get(0).isEmpty()) {
			return null;
		}

		int length = lines.get(0).length();
		char[][] map = new char[lines.size()][length];

		for (int i = 0; i < lines.size(); ++i) {
			String line = lines.get(i);

			if (line.length() != length) {
				return null;
			}

			for (int j = 0; j < length; ++j) {
				map[i][j] = line.charAt(j);
			}
		}

		return map;
	}
}
