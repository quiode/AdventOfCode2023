import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
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

		// calculate all numbers
		int width = characters.get(0).size();
		String currentNumber = "";
		boolean validNumber = false;
		int numberSum = 0;

		for (int i = 0; i < characters.size(); i++) {
			for (int j = 0; j < width; j++) {
				Character currentCharacter = characters.get(i).get(j);
				if (Character.isDigit(currentCharacter)) {
					// add to current number
					currentNumber += currentCharacter;
					// check if any symbol is within reach
					if (i > 0) {
						// check top
						if (j > 0) {
							// left
							if (characters.get(i - 1).get(j - 1) != '.'
									&& !Character.isDigit(characters.get(i - 1).get(j - 1))) {
								validNumber = true;
							}
						}
						// on top
						if (characters.get(i - 1).get(j) != '.' && !Character.isDigit(characters.get(i - 1).get(j))) {
							validNumber = true;
						}
						// right
						if (j < width - 1) {
							if (characters.get(i - 1).get(j + 1) != '.'
									&& !Character.isDigit(characters.get(i - 1).get(j + 1))) {
								validNumber = true;
							}
						}
					}
					if (i < characters.size() - 1) {
						// check bottom
						if (j > 0) {
							// left
							if (characters.get(i + 1).get(j - 1) != '.'
									&& !Character.isDigit(characters.get(i + 1).get(j - 1))) {
								validNumber = true;
							}
						}
						// below
						if (characters.get(i + 1).get(j) != '.' && !Character.isDigit(characters.get(i + 1).get(j))) {
							validNumber = true;
						}
						// right
						if (j < width - 1) {
							if (characters.get(i + 1).get(j + 1) != '.'
									&& !Character.isDigit(characters.get(i + 1).get(j + 1))) {
								validNumber = true;
							}
						}
					}
					// check left
					if (j > 0) {
						if (characters.get(i).get(j - 1) != '.' && !Character.isDigit(characters.get(i).get(j - 1))) {
							validNumber = true;
						}
					}
					// check right
					if (j < width - 1) {
						if (characters.get(i).get(j + 1) != '.' && !Character.isDigit(characters.get(i).get(j + 1))) {
							validNumber = true;
						}
					}
				} else {
					if (validNumber) {
						// add number
						numberSum += Integer.valueOf(currentNumber);
					}
					// clear current number
					currentNumber = "";
					validNumber = false;
				}
			}
			if (validNumber) {
				// add number
				numberSum += Integer.valueOf(currentNumber);
			}
			// clear current number
			currentNumber = "";
			validNumber = false;
		}

		return numberSum;
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
