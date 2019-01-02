import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

//Luke Brosnahan
//14313466
public class ComputerPlayer {
	private String Name;
	private char Symbol;

	public ComputerPlayer(String name, char symbol) {
		this.Name = name;
		this.Symbol = symbol;
	}

	public void inputMove(GameBoard board, int trials) {

		// if possible pick an immediate winner
		for (int x = 0; x < board.getWidth(); x++) {
			int y = board.insertColumn(x);
			if (y == -1)
				continue;
			char temp = board.getTile(x, y);

			if (temp == ' ') {
				board.setTile(x, y, this.Symbol);

				// if the move did not result in a row of four change it back
				if (board.rowOfFour(this.Symbol, '/') != 1) {
					board.setTile(x, y, temp);
				}
				// otherwise leave the move is finished
				else {

					return;
				}
			}
		}

		ArrayList<ArrayList> record = new ArrayList();
		for (int i = 0; i < board.getWidth(); i++) {
			record.add(new ArrayList());
		}

		// Otherwise do the monte carlo method
		for (int i = 0; i < trials; i++) {

			// Setup a new trial game with the current board state
			GameBoard mcBoard = new GameBoard(board.getSpec());
			mcBoard.createCopy(board);
			ComputerPlayer computer = new ComputerPlayer("computer", this.Symbol);
			char opponentSymbol = ' ';
			if (this.Symbol == 'X')
				opponentSymbol = 'O';
			else if (this.Symbol == 'O')
				opponentSymbol = 'X';
			ComputerPlayer opponent = new ComputerPlayer("opponent", opponentSymbol);

			// place the first piece and record the column
			int col;
			int row;
			while (true) {
				col = ThreadLocalRandom.current().nextInt(board.getWidth());
				row = mcBoard.insertColumn(col);
				if (row == -1) {

				} else {
					if (mcBoard.getTile(col, row) == '*') {
						mcBoard.setTile(col, row, (char) (this.Symbol + 32));
						break;
					} else {
						mcBoard.setTile(col, row, this.Symbol);
						break;
					}
				}
			}

			// check that the first move did not result in a win (although this
			// should never
			// happen because if there was a move that would end the game they
			// would have played it
			// if (mcBoard.boardFull() && (mcBoard.rowOfFour(computer.Symbol,
			// opponent.Symbol) == 0)) {
			// board.setTile(col, row, this.Symbol);
			// return;
			// } else if (mcBoard.rowOfFour(computer.Symbol, opponent.Symbol) ==
			// 1) {
			// board.setTile(col, row, this.Symbol);
			// return;
			// } else if (mcBoard.rowOfFour(computer.Symbol, opponent.Symbol) ==
			// 2) {
			// board.setTile(col, row, this.Symbol);
			// return;
			// }

			while (true) {
				boolean winningMoveComputer = false;
				boolean winningMoveOpponent = false;
				// first check if there is an immediate winner
				// if possible pick and immediate winner
				for (int x = 0; x < mcBoard.getWidth(); x++) {
					int y = mcBoard.insertColumn(x);
					if (y == -1)
						continue;
					char temp = mcBoard.getTile(x, y);

					if (temp == ' ') {
						mcBoard.setTile(x, y, opponent.Symbol);
						if (mcBoard.rowOfFour(opponent.Symbol, '/') != 1) {
							mcBoard.setTile(x, y, temp);
						} else {
							winningMoveOpponent = true;
							break;
						}
					}
				}
				if (!winningMoveOpponent) {
					opponent.pickRandomMove(mcBoard);
				}

				// Check if the game is over
				if (mcBoard.boardFull()
						&& (mcBoard.rowOfFour(computer.Symbol, opponent.Symbol) == 0)) {
					record.get(col).add("draw");
					break;
				} else if (mcBoard.rowOfFour(computer.Symbol, opponent.Symbol) == 1) {
					record.get(col).add("win");
					break;
				} else if (mcBoard.rowOfFour(computer.Symbol, opponent.Symbol) == 2) {
					record.get(col).add("loss");
					break;
				}

				// Computers turn to try and pick an immediate winner
				for (int x = 0; x < mcBoard.getWidth(); x++) {
					int y = mcBoard.insertColumn(x);
					if (y == -1)
						continue;
					char temp = mcBoard.getTile(x, y);

					if (temp == ' ') {
						mcBoard.setTile(x, y, computer.Symbol);
						if (mcBoard.rowOfFour(computer.Symbol, '/') != 1) {
							mcBoard.setTile(x, y, temp);
						} else {
							winningMoveComputer = true;
							break;
						}
					}
				}

				// otherwise pick a random move
				if (!winningMoveComputer) {
					computer.pickRandomMove(mcBoard);
				}
				if (mcBoard.boardFull()
						&& (mcBoard.rowOfFour(computer.Symbol, opponent.Symbol) == 0)) {
					record.get(col).add("draw");
					break;
				} else if (mcBoard.rowOfFour(computer.Symbol, opponent.Symbol) == 1) {
					record.get(col).add("win");
					break;
				} else if (mcBoard.rowOfFour(computer.Symbol, opponent.Symbol) == 2) {
					record.get(col).add("loss");
					break;
				}

			}

		}
		// System.out.println(record);
		ArrayList<Double> ratios = new ArrayList<Double>();
		for (int index = 0; index < record.size(); index++) {
			ratios.add((double) 0);
			double totalTrials = record.get(index).size();
			double totalWins = 0;
			for (int z = 0; z < record.get(index).size(); z++) {
				if (record.get(index).get(z) == "win") {
					totalWins += 1;
				}
			}
			// System.out.println("Total trials = " + totalTrials);
			// System.out.println("Total wins = " + totalWins);
			if (totalTrials != 0) {
				double ratio = totalWins / totalTrials;
				ratios.set(index, ratio);
			}
		}
		double bestRatio = 0;
		int bestPos = 0;
		for (int pos = 0; pos < ratios.size(); pos++) {
			if ((double) ratios.get(pos) > bestRatio) {
				bestRatio = (double) ratios.get(pos);
				bestPos = pos;
			}
		}

		// finally insert the tile in the column
		int height = board.insertColumn(bestPos);
		while (true) {
			if (height == -1) {
				bestPos += 1;
				height = board.insertColumn(bestPos);
			} else {
				break;
			}
		}
		if (board.getTile(bestPos, height) == '*') {
			board.setTile(bestPos, height, (char) (this.Symbol + 32));
			System.out.println("The computer has played its move \n");
		} else {
			board.setTile(bestPos, height, this.Symbol);
			System.out.println("The computer has played its move \n");
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
