package fi.miko.tiralabra;

public enum Heuristic {
	Euclidean, None;

	public double distance(int dx, int dy) {
		dx = Math.abs(dx);
		dy = Math.abs(dy);

		switch (this) {
		case Euclidean:
			return Math.sqrt(dx * dx + dy * dy);
		case None:
			return 0;
		}

		return 0;
	}
}
