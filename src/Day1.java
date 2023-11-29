import java.util.Scanner;

public class Day1 extends DayWrapper {

	@Override
	Object run(Scanner input) {
		return input.nextLine();
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
