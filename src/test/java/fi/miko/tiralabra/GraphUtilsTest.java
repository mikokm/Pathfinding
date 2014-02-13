package fi.miko.tiralabra;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

import fi.miko.tiralabra.algorithms.Graph;
import fi.miko.tiralabra.algorithms.GraphUtils;
import fi.miko.tiralabra.algorithms.Node;
import fi.miko.tiralabra.datastructures.LinkedList;

public class GraphUtilsTest {
	@Test
	public void testIsValidPath() {
		LinkedList<Node> path = new LinkedList<>();
		Graph graph = new Graph(new char[][] { { 's', 'p', 't' } });
		assertTrue(GraphUtils.isValidPath(graph, path));

		path.add(graph.getNodes().get(0));
		assertFalse(GraphUtils.isValidPath(graph, path));

		path.add(graph.getNodes().get(2));
		assertFalse(GraphUtils.isValidPath(graph, path));

		path.clear();
		path.add(graph.getNodes().get(0));
		path.add(graph.getNodes().get(1));
		path.add(graph.getNodes().get(2));
		assertTrue(GraphUtils.isValidPath(graph, path));

		graph.getNode(1, 0).setType('#');
		assertFalse(GraphUtils.isValidPath(graph, path));
	}

	@Test
	public void testGetDistance() {
		LinkedList<Node> path = new LinkedList<>();
		assertEquals(0, GraphUtils.getPathDistance(path), 0);

		Node start = new Node(0, 0);
		start.setType('s');
		path.add(start);

		Node target = new Node(1, 1);
		path.add(target);

		target.setDistance(1);
		assertEquals(1, GraphUtils.getPathDistance(path), 0);

		target.setDistance(2);
		assertEquals(2, GraphUtils.getPathDistance(path), 0);
	}

	private void testGenerateRandomHelper() {
		Random rand = new Random();
		int width = 10 + rand.nextInt(200);
		int height = 10 + rand.nextInt(200);

		char[][] map = GraphUtils.generateRandom(width, height, 0.3);

		assertEquals(height, map.length);
		assertEquals(width, map[0].length);

		boolean start = false, target = false, wall = false;
		for (int i = 0; i < height; ++i) {
			for (int j = 0; j < width; ++j) {
				if (map[i][j] == '#') {
					wall = true;
				}

				if (map[i][j] == 's') {
					start = true;
				}

				if (map[i][j] == 't') {
					target = true;
				}
			}
		}

		assertTrue(start);
		assertTrue(target);
		assertTrue(wall);
	}

	@Test
	public void testGenerateRandom() {
		char[][] map = GraphUtils.generateRandom(3, 1, 0);

		assertEquals(1, map.length);
		assertEquals(3, map[0].length);

		boolean start = false, target = false;
		for (int i = 0; i < 3; ++i) {
			assertFalse(map[0][i] == '#');

			if (map[0][i] == 's') {
				start = true;
			}

			if (map[0][i] == 't') {
				target = true;
			}
		}

		assertTrue(start);
		assertTrue(target);

		for (int i = 0; i < 10000; ++i) {
			testGenerateRandomHelper();
		}
	}

	@Test
	public void testDummyConstructor() {
		new GraphUtils();
	}
}
