package fi.miko.tiralabra.tiralabra;

import java.util.ArrayList;

public class GraphUtil {

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

	public static void initializeNodes(ArrayList<Node> nodes) {
		for (Node n : nodes) {
			if (n.getType() == Graph.START) {
				n.setDistance(0);
				n.setDistanceEstimate(0);
			} else {
				n.setDistance(Double.POSITIVE_INFINITY);
			}

			n.setDistanceEstimate(0);
			n.setNearest(null);
		}
	}
}
