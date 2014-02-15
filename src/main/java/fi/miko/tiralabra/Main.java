package fi.miko.tiralabra;

import fi.miko.tiralabra.algorithms.Graph;
import fi.miko.tiralabra.algorithms.GraphUtils;

public class Main {
	public static void main(String[] args) {
		if (args.length >= 1) {
			switch (args[0]) {
			case "-b":
				benchmark(args);
				break;
			case "-r":
				randomize(args);
				break;
			case "-s":
				solve(args);
				break;
			case "-sr":
				solveRandom(args);
				break;
			}
		} else {
			usage();
		}
	}

	private static char[][] generateRandom(String[] args) {
		// -a 100 100 20
		if (args.length != 4) {
			return null;
		}

		int width = Integer.parseInt(args[1]);
		int height = Integer.parseInt(args[2]);
		double freq = Double.parseDouble(args[3]);

		return GraphUtils.generateRandom(width, height, freq);
	}

	private static void solveRandom(String[] args) {
		char[][] map = generateRandom(args);

		if (map != null) {
			Solver s = new Solver();
			s.solve(map);
		}
	}

	private static void usage() {
		System.out.println("Usage:");
		System.out.println("Benchmark algorithms: -b <width> <height> <freq> <iterations>");
		System.out.println("Generate random graph: -r <width> <height> <freq>");
		System.out.println("Solve:     -s <filename>");
		System.out.println("Solve random graph: -sr <width> <height> <freq>");
	}

	private static void solve(String[] args) {
		// -s "map.txt"
		if (args.length != 2) {
			return;
		}

		String filename = args[1];
		new Solver(filename);
	}

	private static void randomize(String[] args) {
		char[][] map = generateRandom(args);

		if (map != null) {
			System.out.println(new Graph(map));
		}
	}

	private static void benchmark(String[] args) {
		// -b 100 100 20 1000
		if (args.length != 5) {
			return;
		}

		int width = Integer.parseInt(args[1]);
		int height = Integer.parseInt(args[2]);
		double freq = Double.parseDouble(args[3]);
		int iterations = Integer.parseInt(args[4]);

		Benchmark b = new Benchmark();
		b.run(width, height, freq, iterations);
	}
}