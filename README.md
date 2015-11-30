Labyrinth Solver
==

The program reads a labyrinth from a file or generates one, after which it finds the shortest path between the start and goal nodes. These nodes are placed in the labyrinth as described in the format section at the bottom.
There is also a benchmarking mode, which allows performance comparison of the implemented algorithms.

How to build:
--
mvn package


How to run:
--
java -jar target/tiralabra-1.0-SNAPSHOT.jar


Command line parameters:
--
	Benchmark algorithms     -b <width> <height> <wall freq> <iterations>
	Generate random graph    -r <width> <height> <wall freq>
	Solve                    -s <filename>
	Solve random graph       -sr <width> <height> <wall freq>


The labyrinth file format:
--
	################
	#s####...###.###
	#.###..#..##.###
	#...##...###..t#
	####..###...####
	################
	
\# : wall  
. : floor  
s : start  
t : goal
