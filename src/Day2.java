import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.transform.OutputKeys;

public class Day2 extends DayWrapper {
	static class CubeCount {
		final int red;
		final int green;
		final int blue;

		public CubeCount(int red, int green, int blue) {
			this.red = red;
			this.green = green;
			this.blue = blue;
		}

		@Override
		public String toString() {
			return "(%d, %d, %d)".formatted(red, green, blue);
		}
	}

	List<CubeCount> readLine(String line) {
		Scanner scanner = new Scanner(line);
		scanner.useDelimiter("[;$]");
		// create list
		List<CubeCount> cubeCount = new LinkedList<>();
		// skip name
		scanner.skip("Game\\s\\d+:");

		// define patterns
		String digit = "(\\d+)";
		String whitespace = "\\s+";
		Pattern red = Pattern.compile(digit + whitespace + "red");
		Pattern green = Pattern.compile(digit + whitespace + "green");
		Pattern blue = Pattern.compile(digit + whitespace + "blue");

		// read each statement in the line
		while (scanner.hasNext()) {
			String nextPattern = scanner.next().trim();
			int redValue = 0;
			int blueValue = 0;
			int greenValue = 0;
			Matcher redMatcher = red.matcher(nextPattern);
			if (redMatcher.find()) {
				redValue = Integer.valueOf(redMatcher.group(1));
			}
			Matcher greenMatcher = green.matcher(nextPattern);
			if (greenMatcher.find()) {
				greenValue = Integer.valueOf(greenMatcher.group(1));
			}
			Matcher blueMatcher = blue.matcher(nextPattern);
			if (blueMatcher.find()) {
				blueValue = Integer.valueOf(blueMatcher.group(1));
			}

			cubeCount.add(new CubeCount(redValue, greenValue, blueValue));
		}

		scanner.close();
		return cubeCount;
	}

	@Override
	Object run(Scanner input) {
		List<List<CubeCount>> cubeCount = new LinkedList<>();
		// read values
		while (input.hasNextLine()) {
			String line = input.nextLine();
			// read line
			List<CubeCount> lineCount = readLine(line);
			// add
			cubeCount.add(lineCount);
		}

		int redMax = 12;
		int greenMax = 13;
		int blueMax = 14;

		// count all invalid lines
		int invalidSum = 0;
		int gameCount = 1;
		for (List<CubeCount> lineCount : cubeCount) {
			boolean isInvalid = false;

			for (CubeCount singleCount : lineCount) {
				if (singleCount.red > redMax) {
					isInvalid = true;
				}
				if (singleCount.green > greenMax) {
					isInvalid = true;
				}
				if (singleCount.blue > blueMax) {
					isInvalid = true;
				}
			}

			if (!isInvalid) {
				invalidSum += gameCount;
			}
			gameCount++;
		}

		return invalidSum;
	}

	@Override
	int getDay() {
		return 2;
	}

	public static void main(String[] args) {
		Day2 day2 = new Day2();
		day2.main();
	}

}
