import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Day4 extends DayWrapper {

	static class Card {
		private List<Integer> winningNumbers = new LinkedList<>();
		private List<Integer> ownNumbers = new LinkedList<>();

		void addWinningNumber(int number) {
			winningNumbers.add(number);
		}

		void addOwnNumber(int number) {
			ownNumbers.add(number);
		}

		int countMatchingNumbers() {
			int count = 0;

			// create set
			Set<Integer> winningSet = new HashSet<Integer>(winningNumbers);

			for (Integer ownNUmber : ownNumbers) {
				if (winningSet.contains(ownNUmber)) {
					count++;
				}
			}

			return count;
		}

		int cardPoints() {
			int count = countMatchingNumbers();

			if (count == 0) {
				return 0;
			} else {
				count -= 1;
				return 1 << count;
			}
		}

		@Override
		public String toString() {
			return winningNumbers + ";" + ownNumbers;
		}
	}

	@Override
	Object run(Scanner input) {
		List<Card> cards = new LinkedList<>();

		while (input.hasNextLine()) {
			Scanner lineScanner = new Scanner(input.nextLine());
			Card card = new Card();
			// skip beginning
			lineScanner.skip("Card\\s*\\d+:");
			// parse winning numbers
			while (lineScanner.hasNextInt()) {
				card.addWinningNumber(lineScanner.nextInt());
			}
			// skip separator
			lineScanner.skip("\\s*\\|\\s*");
			// get own numbers
			while (lineScanner.hasNextInt()) {
				card.addOwnNumber(lineScanner.nextInt());
			}
			lineScanner.close();
			cards.add(card);
		}

		int sum = 0;
		// add all points
		for (Card card : cards) {
			sum += card.cardPoints();
		}

		return sum;
	}

	@Override
	int getDay() {
		// TODO Auto-generated method stub
		return 4;
	}

	public static void main(String[] args) {
		Day4 day4 = new Day4();
		day4.main();
	}

}
