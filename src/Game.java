import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

//Luke Brosnahan
//14313466
public class Game {
	public Game() {

	}

	public int humanVhuman(GameBoard board, HumanPlayer human, HumanPlayer human2, int trials) {
		int result = -1;

		while (true) {

			human.inputMove(board);
			System.out.println(board);
			System.out.println("--------------------");
			if (board.boardFull()
					&& (board.rowOfFour(human.getSymbol(), human2.getSymbol()) == 0)) {
				System.out.println("It's a draw.");
				result = 0;
				break;
			} else if (board.rowOfFour(human.getSymbol(), human2.getSymbol()) == 1) {
				System.out.println(human.getSymbol() + "'s win.");
				result = 1;
				break;
			} else if (board.rowOfFour(human.getSymbol(), human2.getSymbol()) == 2) {
				System.out.println(human2.getSymbol() + "'s win.");
				result = 2;
				break;
			}

			human2.inputMove(board);
			System.out.println(board);
			System.out.println("--------------------");
			if (board.boardFull()
					&& (board.rowOfFour(human.getSymbol(), human2.getSymbol()) == 0)) {
				System.out.println("It's a draw.");
				result = 0;
				break;
			} else if (board.rowOfFour(human.getSymbol(), human2.getSymbol()) == 1) {
				System.out.println(human.getSymbol() + "'s win.");
				result = 1;
				break;
			} else if (board.rowOfFour(human.getSymbol(), human2.getSymbol()) == 2) {
				System.out.println(human2.getSymbol() + "'s win.");
				result = 2;
				break;
			}

		}
		return result;
	}

	public int humanVcomputer(GameBoard board, HumanPlayer human, ComputerPlayer computer,
			int trials) {
		int result = -1;

		while (true) {

			human.inputMove(board);
			System.out.println(board);
			System.out.println("--------------------");
			if (board.boardFull()
					&& (board.rowOfFour(human.getSymbol(), computer.getSymbol()) == 0)) {
				System.out.println("It's a draw.");
				result = 0;
				break;
			} else if (board.rowOfFour(human.getSymbol(), computer.getSymbol()) == 1) {
				System.out.println(human.getSymbol() + "'s win.");
				result = 1;
				break;
			} else if (board.rowOfFour(human.getSymbol(), computer.getSymbol()) == 2) {
				System.out.println(computer.getSymbol() + "'s win.");
				result = 2;
				break;
			}

			computer.inputMove(board, trials);
			System.out.println(board);
			System.out.println("--------------------");
			if (board.boardFull()
					&& (board.rowOfFour(human.getSymbol(), computer.getSymbol()) == 0)) {
				System.out.println("It's a draw.");
				result = 0;
				break;
			} else if (board.rowOfFour(human.getSymbol(), computer.getSymbol()) == 1) {
				System.out.println(human.getSymbol() + "'s win.");
				result = 1;
				break;
			} else if (board.rowOfFour(human.getSymbol(), computer.getSymbol()) == 2) {
				System.out.println(computer.getSymbol() + "'s win.");
				result = 2;
				break;
			}

		}
		return result;
	}

	public int computerVcomputer(GameBoard board, ComputerPlayer player, ComputerPlayer computer,
			int trials1, int trials2) {
		int result = -1;

		while (true) {

			player.inputMove(board, trials1);
			System.out.println(board);
			System.out.println("--------------------");
			if (board.boardFull()
					&& (board.rowOfFour(player.getSymbol(), computer.getSymbol()) == 0)) {
				System.out.println("It's a draw.");
				result = 0;
				break;
			} else if (board.rowOfFour(player.getSymbol(), computer.getSymbol()) == 1) {
				System.out.println(player.getSymbol() + "'s win.");
				result = 1;
				break;
			} else if (board.rowOfFour(player.getSymbol(), computer.getSymbol()) == 2) {
				System.out.println(computer.getSymbol() + "'s win.");
				result = 2;
				break;
			}

			computer.inputMove(board, trials2);
			System.out.println(board);
			System.out.println("--------------------");
			if (board.boardFull()
					&& (board.rowOfFour(player.getSymbol(), computer.getSymbol()) == 0)) {
				System.out.println("It's a draw.");
				result = 0;
				break;
			} else if (board.rowOfFour(player.getSymbol(), computer.getSymbol()) == 1) {
				System.out.println(player.getSymbol() + "'s win.");
				result = 1;
				break;
			} else if (board.rowOfFour(player.getSymbol(), computer.getSymbol()) == 2) {
				System.out.println(computer.getSymbol() + "'s win.");
				result = 2;
				break;
			}

		}

		return result;
	}

	// Main contains the systematic experimentation
	public static void main(String[] args) throws FileNotFoundException {
		Game connect4 = new Game();
		HumanPlayer human = new HumanPlayer("Luke", 'X');
		ComputerPlayer player = new ComputerPlayer("AIPlayer", 'X');
		ComputerPlayer computer = new ComputerPlayer("Robo", 'O');

		PrintWriter pw = new PrintWriter(new File("Connect4Results.csv"));
		StringBuilder sb = new StringBuilder();
		sb.append("Board Config");
		sb.append(',');
		sb.append("Player1 Trials");
		sb.append(',');
		sb.append("Player2 Trials");
		sb.append(',');
		sb.append("Player1 Win Ratio");
		sb.append(',');
		sb.append("Player2 Win Ratio");
		sb.append(',');
		sb.append("Draw Ratio");
		sb.append('\n');

		ArrayList<String> BoardConfigurations = new ArrayList<String>();
		BoardConfigurations.add("5 5 5 5 5 5");
		BoardConfigurations.add("A3 B0");
		BoardConfigurations.add("6 7 6 8 7 8 7 6");
		BoardConfigurations.add("C5 E0");
		BoardConfigurations.add("8 9 8 8 8 7 9 9 8");
		BoardConfigurations.add("H4 D4");
		BoardConfigurations.add("11 10 10 9 8 10 9 11 7 10 11");
		BoardConfigurations.add("G8 H7");

		ArrayList<Integer> trials = new ArrayList<Integer>();
		trials.add(80);
		trials.add(150);
		trials.add(500);
		trials.add(2000);

		for (int config = 0; config < BoardConfigurations.size(); config += 2) {
			for (int player1Trials = 0; player1Trials < trials.size(); player1Trials++) {
				for (int player2Trials = 0; player2Trials < trials.size(); player2Trials++) {
					double player1Wins = 0;
					double player2Wins = 0;
					double draws = 0;
					for (int i = 0; i < 100; i++) {
						GameBoard board = new GameBoard(BoardConfigurations.get(config));
						board.addStars(BoardConfigurations.get(config + 1));
						System.out.println(board);
						int result = connect4.computerVcomputer(board, player, computer,
								trials.get(player1Trials), trials.get(player2Trials));
						if (result == 1) {
							player1Wins += 1;
						} else if (result == 2) {
							player2Wins += 1;
						} else if (result == 0) {
							draws += 1;
						}
					}
					System.out.println("Player1 Wins = " + player1Wins + " Player2Wins = "
							+ player2Wins + " Draws =" + draws);
					sb.append(BoardConfigurations.get(config));
					sb.append(',');
					sb.append(trials.get(player1Trials));
					sb.append(',');
					sb.append(trials.get(player2Trials));
					sb.append(',');
					sb.append(player1Wins / 100);
					sb.append(',');
					sb.append(player2Wins / 100);
					sb.append(',');
					sb.append(draws / 100);
					sb.append('\n');
				}
			}
		}

		pw.write(sb.toString());
		pw.close();
		System.out.println("done!");
	}
}
