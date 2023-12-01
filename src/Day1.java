import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Day1 extends DayWrapper {
	static class Pair {
		final String str;
		final int value;

		public Pair(String str, int value) {
			this.str = str;
			this.value = value;
		}

		@Override
		public boolean equals(Object o) {
			if (o == null)
				return false;
			String rep = o.toString();
			return rep.equals(str);
		}
	}

	final Pair[] digits = { new Pair("one", 1), new Pair("two", 2), new Pair("three", 3), new Pair("four", 4),
			new Pair("five", 5), new Pair("six", 6), new Pair("seven", 7), new Pair("eight", 8), new Pair("nine", 9) };

	@Override
	Object run(Scanner input) {
		int totalValue = 0;
		while (input.hasNextLine()) {
			String nextLine = input.nextLine();
			String number = "";

			// find first digit
			outer: for (int i = 0; i < nextLine.length(); i++) {
				for (Pair digit : this.digits) {
					if (nextLine.startsWith(digit.str, i) || nextLine.startsWith(digit.value + "", i)) {
						number += digit.value;
						break outer;
					}
				}
			}
			// last digit
			outer: for (int i = nextLine.length() - 1; i >= 0; i--) {
				for (Pair digit : this.digits) {
					if (nextLine.startsWith(digit.str, i) || nextLine.startsWith(digit.value + "", i)) {
						number += digit.value;
						break outer;
					}
				}
			}

			totalValue += Integer.valueOf(number);
		}
		return totalValue;
	}

	@Override
	int getDay() {
		return 1;
	}

	public static void main(String[] args) {
		Day1 day = new Day1();
		day.main();
	}
}
