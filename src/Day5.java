import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingDeque;

public class Day5 extends DayWrapper {
	static class Range {
		private final int destinationStart;
		private final int sourceStart;
		private final int length;

		Range(int destinationStart, int sourceStart, int length) {
			this.destinationStart = destinationStart;
			this.sourceStart = sourceStart;
			this.length = length;
		}

		boolean includes(int value) {
			if (value < sourceStart) {
				return false;
			}

			if (value >= sourceStart + length) {
				return false;
			}

			return true;
		}

		int convertValue(int value) {
			if (!includes(value)) {
				return -1;
			}

			int diff = value - sourceStart;
			return destinationStart + diff;
		}

		@Override
		public String toString() {
			return "(%d, %d, %d)".formatted(destinationStart, sourceStart, length);
		}
	}

	static class Map {
		private final List<Range> ranges = new LinkedList<>();

		int convertValue(int from) {
			// check if value is included in ranges
			for (Range range : ranges) {
				if (range.includes(from)) {
					return range.convertValue(from);
				}
			}
			// if not, just return 1/1 mapping
			return from;
		}

		void addRange(Range range) {
			ranges.add(range);
		}

		@Override
		public String toString() {
			return ranges.toString();
		}
	}

	@Override
	Object run(Scanner input) {
		// read input
		List<Integer> startingSeeds = new LinkedList<>();
		input.skip("seeds:");
		while (input.hasNextInt()) {
			startingSeeds.add(input.nextInt());
		}

		List<Map> maps = new LinkedList<>();
		// skip line
		input.nextLine();
		// read all maps
		while (input.hasNextLine()) {
			// skip line
			input.nextLine();
			input.nextLine();
			Map map = new Map();
			// skip map title
			// read lines
			while (input.hasNextInt()) {
				String line = input.nextLine();
				Scanner lineScanner = new Scanner(line);
				Range range = new Range(lineScanner.nextInt(), lineScanner.nextInt(), lineScanner.nextInt());
				lineScanner.close();
				map.addRange(range);
			}
			maps.add(map);
		}

		// go through all seeds
		int min = Integer.MAX_VALUE;
		for (Integer seed : startingSeeds) {
			// follow the path to find the value
			int current = seed;
			for (Map map : maps) {
				current = map.convertValue(current);
			}

			min = Math.min(min, current);
		}

		return min;
	}

	@Override
	int getDay() {
		// TODO Auto-generated method stub
		return 5;
	}

	public static void main(String[] args) {
		Day5 day5 = new Day5();
		day5.main();
	}

}
