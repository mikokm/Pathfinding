package fi.miko.tiralabra;

import fi.miko.tiralabra.algorithms.Graph;
import fi.miko.tiralabra.algorithms.GraphUtils;

public class Main {
	public static void main(String[] args) {
		boolean ret = false;
		if (args.length >= 1) {
			switch (args[0]) {
			case "-b":
				ret = benchmark(args);
				break;
			case "-r":
				ret = randomize(args);
				break;
			case "-s":
				ret = solve(args);
				break;
			case "-sr":
				ret = solveRandom(args);
				break;
			}
		}

		if (!ret) {
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

	private static boolean solveRandom(String[] args) {
		char[][] map = generateRandom(args);

		if (map == null) {
			return false;
		}

		Solver s = new Solver();
		s.solve(map);

		return true;
	}

	private static void usage() {
		System.out.println("Usage:");
		System.out.println("Benchmark algorithms     -b <width> <height> <freq> <iterations>");
		System.out.println("Generate random graph    -r <width> <height> <freq>");
		System.out.println("Solve                    -s <filename>");
		System.out.println("Solve random graph       -sr <width> <height> <freq>");
	}

	private static boolean solve(String[] args) {
		// -s "map.txt"
		if (args.length != 2) {
			return false;
		}

		String filename = args[1];
		new Solver(filename);
		return true;
	}

	private static boolean randomize(String[] args) {
		char[][] map = generateRandom(args);

		if (map == null) {
			return false;
		}

		System.out.println(new Graph(map));
		return true;
	}

	private static boolean benchmark(String[] args) {
		// -b 100 100 20 1000
		if (args.length != 5) {
			return false;
		}

		int width = Integer.parseInt(args[1]);
		int height = Integer.parseInt(args[2]);
		double freq = Double.parseDouble(args[3]);
		int iterations = Integer.parseInt(args[4]);

		Benchmark b = new Benchmark();
		b.run(width, height, freq, iterations);
		return true;
	}
}