package fi.miko.tiralabra;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class GraphUtil {
	static public List<Node> getShortestPath(Graph graph) {
		Stack<Node> stack = new Stack<>();
		Node start = graph.getStart(), target = graph.getTarget();
		
		Node node = target.getNearest();
		while (node != start) {
			if (node == null) {
				System.out.println("No path found!");
				return null;
			}

			stack.push(node);
			node = node.getNearest();
		}

		System.out.println("Path length is " + stack.size() + " nodes.");

		List<Node> path = new LinkedList<>();
		path.add(start);
		
		while (!stack.isEmpty()) {
			path.add(stack.pop());
		}
		
		path.add(target);

		return path;
	}

	public static void initializeNodes(Graph graph, Heuristic heuristic) {
		Node target = graph.getTarget(), start = graph.getStart();
		
		for (Node n : graph.getNodes()) {
			n.setDistance(Double.POSITIVE_INFINITY);
			n.setDistanceEstimate(heuristic.distance(target.getX() - n.getX(), target.getY() - n.getY()));
			n.setNearest(null);
		}

		start.setDistance(0);
		start.setDistanceEstimate(0);
	}

	public static void relax(Node u, Node v, double w) {
		if (u.getDistance() == Double.POSITIVE_INFINITY) {
			return;
		}

		double uw = u.getDistance() + w;

		if (v.getDistance() > uw) {
			v.setDistance(uw);
			v.setNearest(u);
		}
	}
}
