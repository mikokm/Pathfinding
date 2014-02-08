package fi.miko.tiralabra;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fi.miko.tiralabra.algorithms.Heuristic;

public class HeuristicTest {

	@Test
	public void testDiagonal() {
		Heuristic h = Heuristic.Diagonal;
		assertEquals(Heuristic.Diagonal, Heuristic.valueOf("Diagonal"));

		assertEquals(0, h.distance(0, 0), 0);
		assertEquals(1, h.distance(1, 1), 0);
		assertEquals(2, h.distance(1, 2), 0);
		assertEquals(2, h.distance(2, 1), 0);
		assertEquals(2, h.distance(2, 2), 0);
	}

	@Test
	public void testEuclidean() {
		Heuristic h = Heuristic.Euclidean;
		assertEquals(Heuristic.Euclidean, Heuristic.valueOf("Euclidean"));

		assertEquals(0, h.distance(0, 0), 0);
		assertEquals(Math.sqrt(2), h.distance(1, 1), 0);
		assertEquals(Math.sqrt(5), h.distance(1, 2), 0);
		assertEquals(Math.sqrt(5), h.distance(2, 1), 0);
		assertEquals(Math.sqrt(8), h.distance(2, 2), 0);
	}

	@Test
	public void testManhattan() {
		Heuristic h = Heuristic.Manhattan;
		assertEquals(Heuristic.Manhattan, Heuristic.valueOf("Manhattan"));

		assertEquals(0, h.distance(0, 0), 0);
		assertEquals(2, h.distance(1, 1), 0);
		assertEquals(3, h.distance(1, 2), 0);
		assertEquals(3, h.distance(2, 1), 0);
		assertEquals(4, h.distance(2, 2), 0);
	}

	@Test
	public void testNone() {
		Heuristic h = Heuristic.None;

		assertEquals(0, h.distance(0, 0), 0);
		assertEquals(0, h.distance(1, 0), 0);
		assertEquals(0, h.distance(0, 1), 0);
		assertEquals(0, h.distance(1, 1), 0);
	}

}
