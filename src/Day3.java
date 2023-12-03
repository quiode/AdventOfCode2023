import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Stream;

public class Day3 extends DayWrapper {

	@Override
	Object run(Scanner input) {
		// convert to arraylists
		List<List<Character>> characters = new ArrayList<>();
		while (input.hasNextLine()) {
			String line = input.nextLine();
			char[] chars = line.toCharArray();
			List<Character> charsList = new ArrayList<>(chars.length);
			for (char c : chars) {
				charsList.add(c);
			}
			characters.add(charsList);
		}

		int gearSum = 0;
		int width = characters.get(0).size();
		// find all gears
		for (int i = 0; i < characters.size(); i++) {
			for (int j = 0; j < width; j++) {
				// check if character could be a gear
				if (characters.get(i).get(j) == '*') {
					// check if it has any two numbers adjacent
					Set<Integer> adjacentNumbers = new HashSet<>();
					// top number
					if (i > 0) {
						if (j > 0 && Character.isDigit(characters.get(i - 1).get(j - 1))) {
							adjacentNumbers.add(numberFromIndex(j - 1, characters.get(i - 1)));
						}
						if (Character.isDigit(characters.get(i - 1).get(j))) {
							adjacentNumbers.add(numberFromIndex(j, characters.get(i - 1)));
						}
						if (j < width - 1 && Character.isDigit(characters.get(i - 1).get(j + 1))) {
							adjacentNumbers.add(numberFromIndex(j + 1, characters.get(i - 1)));
						}
					}
					// left number
					if (j > 0) {
						if (Character.isDigit(characters.get(i).get(j - 1))) {
							adjacentNumbers.add(numberFromIndex(j - 1, characters.get(i)));
						}
					}
					// bottom number
					if (i < characters.size() - 1) {
						if (j > 0 && Character.isDigit(characters.get(i + 1).get(j - 1))) {
							adjacentNumbers.add(numberFromIndex(j - 1, characters.get(i + 1)));
						}
						if (Character.isDigit(characters.get(i + 1).get(j))) {
							adjacentNumbers.add(numberFromIndex(j, characters.get(i + 1)));
						}
						if (j < width - 1 && Character.isDigit(characters.get(i + 1).get(j + 1))) {
							adjacentNumbers.add(numberFromIndex(j + 1, characters.get(i + 1)));
						}
					}
					// right number
					if (j < width - 1) {
						if (Character.isDigit(characters.get(i).get(j + 1))) {
							adjacentNumbers.add(numberFromIndex(j + 1, characters.get(i)));
						}
					}

					if (adjacentNumbers.size() == 2) {
						gearSum += adjacentNumbers.stream().reduce(1, (prev, cur) -> prev * cur);
					}
				}
			}
		}

		return gearSum;
	}

	int numberFromIndex(int j, List<Character> list) {
		int startIndex = j;
		int endIndex = j;
		while (startIndex > 0 && Character.isDigit(list.get(startIndex - 1))) {
			startIndex--;
		}
		while (endIndex < list.size() - 2 && Character.isDigit(list.get(endIndex + 1))) {
			endIndex++;
		}

		String wholeNUmber = "";
		for (int k = startIndex; k <= endIndex; k++) {
			wholeNUmber += list.get(k);
		}

		return Integer.valueOf(wholeNUmber);
	}

	@Override
	int getDay() {
		return 3;
	}

	public static void main(String[] args) {
		Day3 day3 = new Day3();
		day3.main();
	}

}
