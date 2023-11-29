import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

abstract class DayWrapper {
	void main() {
		InputStream inputStream = DayWrapper.class.getResourceAsStream("day" + getDay() + ".txt");
		Scanner scanner = new Scanner(inputStream);
		PrintStream output = new PrintStream(System.out);
		String result = run(scanner).toString();
		output.println(result);
	}

	abstract Object run(Scanner input);

	abstract int getDay();
}
