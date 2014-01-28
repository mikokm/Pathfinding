package fi.miko.tiralabra.algorithms;

/**
 * Heuristic calculates the distance between two points.
 */
public enum Heuristic {
	Diagonal, Euclidean, Manhattan, None;

	public double distance(int dx, int dy) {
		dx = Math.abs(dx);
		dy = Math.abs(dy);

		switch (this) {
		case Diagonal:
			return Math.max(dx, dy);
		case Euclidean:
			return Math.sqrt(dx * dx + dy * dy);
		case Manhattan:
			return dx + dy;
		default:
			return 0;
		}
	}
}
