import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

//Luke Brosnahan
//14313466
public class HumanPlayer {
	private String Name;
	private char Symbol;

	public HumanPlayer(String name, char symbol) {
		this.Name = name;
		this.Symbol = symbol;
	}

	public void inputMove(GameBoard board) {
		boolean correctInput = false;
		Scanner scanner = new Scanner(System.in);

		while (true) {

			System.out.print(
					"Please input the capital letter of column you would like to place a move: ");
			String input = scanner.nextLine();
			if (input.isEmpty()) {
				System.out.println("You didn't input anything.");
				continue;
			}
			int x = input.charAt(0) - 65;
			char temp = ' ';

			if (input.length() > 1) {
				System.out.println("Incorrect input.");
				continue;
			} else {
				int y = board.insertColumn(x);
				if (y == -1) {
					System.out.println("This column could not be inserted into");
				} else {
					if (board.getTile(x, y) == '*') {
						board.setTile(x, y, (char) (this.Symbol + 32));
						System.out.println("Inserted at column: " + x + " height: " + y);
						break;
					} else {
						board.setTile(x, y, this.Symbol);
						System.out.println("Inserted at column: " + x + " height: " + y);
						break;
					}
				}
			}
		}

	}

	public void pickRandomMove(GameBoard board) {
		while (true) {
			int col = ThreadLocalRandom.current().nextInt(board.getWidth());
			int row = board.insertColumn(col);
			if (row == -1) {

			} else {
				if (board.getTile(col, row) == '*') {
					board.setTile(col, row, (char) (this.Symbol + 32));
					break;
				} else {
					board.setTile(col, row, this.Symbol);
					break;
				}
			}
		}
	}

	public char getSymbol() {
		return this.Symbol;
	}

}
