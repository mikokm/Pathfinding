package fi.miko.tiralabra;

public enum Heuristic {
	Diagonal, Euclidean, None;

	public double distance(int dx, int dy) {
		dx = Math.abs(dx);
		dy = Math.abs(dy);

		switch (this) {
		case Diagonal:
			return Math.max(dx, dy);
		case Euclidean:
			return Math.sqrt(dx * dx + dy * dy);
		default:
			return 0;
		}
	}
}
