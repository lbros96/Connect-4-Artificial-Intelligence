import java.io.FileNotFoundException;
import java.util.ArrayList;

//Luke Brosnahan
//14313466
public class GameBoard {
	private ArrayList<ArrayList<Character>> Board = new ArrayList<ArrayList<Character>>();
	private int height = 0;
	private int width = 0;
	private String spec = "";

	public GameBoard(String spec) {
		this.setSpec(spec);
		String columnHeights[] = spec.split(" ");

		for (int i = 0; i < columnHeights.length; i++) {
			if (Integer.parseInt(columnHeights[i]) > this.height) {
				this.height = Integer.parseInt(columnHeights[i]);
			}
		}
		this.width = columnHeights.length;

		for (int i = 0; i < this.width; i++) {
			Board.add(new ArrayList());
			for (int j = 0; j < this.height; j++) {
				if (j >= Integer.parseInt(columnHeights[i])) {
					Board.get(i).add('#');
				} else {
					Board.get(i).add(' ');
				}
			}
		}

	}

	public void addStars(String star) {
		String starPos[] = star.split(" ");
		for (int i = 0; i < starPos.length; i++) {
			String k[] = starPos[i].split("");
			int x = k[0].charAt(0) - 65;
			int y = Integer.parseInt(k[1]);
			System.out.println("x = " + x + " y = " + y + " height = " + this.height + " width = "
					+ this.width);
			if (x < 0 || x >= this.width || y < 0 || y >= this.height) {
				System.out.println("One of the positions was not on the board");
				return;
			} else {
				this.setTile(x, y, '*');
				System.out.println(x);
			}
		}
	}

	public String toString() {
		String str = "";

		for (int j = this.height - 1; j >= 0; j--) {
			str += j + "\t";
			for (int i = 0; i < this.width; i++) {
				str += "|";
				str += this.getTile(i, j);
			}
			str += "|\n";
		}

		str += "\t ";
		for (int i = 0; i < this.width; i++) {
			str += (char) (65 + i);
			str += " ";
		}
		return str;
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

	public void setTile(int x, int y, char symbol) {
		this.Board.get(x).set(y, symbol);
	}

	public char getTile(int x, int y) {
		return this.Board.get(x).get(y);
	}

	public int insertColumn(int col) {
		if (col >= this.width) {
			return -1;
		} else if (!Board.get(col).contains(' ') && !Board.get(col).contains('*')) {
			// System.out.println("This column is full");
			return -1;
		} else {
			int i = 0;
			while (i < this.height) {
				if (!Board.get(col).get(i).equals(' ') && !Board.get(col).get(i).equals('*')) {
					i++;
				} else {
					if (Board.get(col).get(i).equals('*')) {
						return i;
					}

					else {
						return i;
					}
				}
			}
		}
		return -1;
	}

	public boolean boardFull() {
		boolean full = true;
		for (int i = 0; i < this.width; i++) {
			for (int j = 0; j < this.height; j++) {
				if (this.getTile(i, j) == ' ')
					full = false;
			}
		}
		return full;
	}

	public int rowOfFour(char symbol1, char symbol2) {
		for (int i = 0; i < this.width; i++) {
			for (int j = 0; j < this.height; j++) {
				if (this.getTile(i, j) == symbol1 && (this.width - i) >= 4) {
					if (this.getTile(i + 1, j) == symbol1) {
						if (this.getTile(i + 2, j) == symbol1) {
							if (this.getTile(i + 3, j) == symbol1) {
								return 1;
							}
						}
					}
				}

				if (this.getTile(i, j) == symbol1 && (this.height - j) >= 4) {
					if (this.getTile(i, j + 1) == symbol1) {
						if (this.getTile(i, j + 2) == symbol1) {
							if (this.getTile(i, j + 3) == symbol1) {
								return 1;
							}
						}
					}
				}

				if (this.getTile(i, j) == symbol1 && (this.height - j) >= 4
						&& (this.width - i) >= 4) {
					if (this.getTile(i + 1, j + 1) == symbol1) {
						if (this.getTile(i + 2, j + 2) == symbol1) {
							if (this.getTile(i + 3, j + 3) == symbol1) {
								return 1;
							}
						}
					}
				}

				if (this.getTile(i, j) == symbol1 && (j - 3) >= 0 && (this.width - i) >= 4) {
					if (this.getTile(i + 1, j - 1) == symbol1) {
						if (this.getTile(i + 2, j - 2) == symbol1) {
							if (this.getTile(i + 3, j - 3) == symbol1) {
								return 1;
							}
						}
					}
				}

				if (this.getTile(i, j) == symbol2 && (this.width - i) >= 4) {
					if (this.getTile(i + 1, j) == symbol2) {
						if (this.getTile(i + 2, j) == symbol2) {
							if (this.getTile(i + 3, j) == symbol2) {
								return 2;
							}
						}
					}
				}

				if (this.getTile(i, j) == symbol2 && (this.height - j) >= 4) {
					if (this.getTile(i, j + 1) == symbol2) {
						if (this.getTile(i, j + 2) == symbol2) {
							if (this.getTile(i, j + 3) == symbol2) {
								return 2;
							}
						}
					}
				}

				if (this.getTile(i, j) == symbol2 && (this.height - j) >= 4
						&& (this.width - i) >= 4) {
					if (this.getTile(i + 1, j + 1) == symbol2) {
						if (this.getTile(i + 2, j + 2) == symbol2) {
							if (this.getTile(i + 3, j + 3) == symbol2) {
								return 2;
							}
						}
					}
				}

				if (this.getTile(i, j) == symbol2 && (j - 3) >= 0 && (this.width - i) >= 4) {
					if (this.getTile(i + 1, j - 1) == symbol2) {
						if (this.getTile(i + 2, j - 2) == symbol2) {
							if (this.getTile(i + 3, j - 3) == symbol2) {
								return 2;
							}
						}
					}
				}
			}
		}
		return 0;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public ArrayList getBoard() {
		return this.Board;
	}

	public void createCopy(GameBoard board) {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				this.setTile(x, y, board.getTile(x, y));
			}
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		Game connect4 = new Game();
		GameBoard board1 = new GameBoard("5 5 5 5 5 5");
		board1.addStars("A3 B0");
		GameBoard board2 = new GameBoard("6 7 6 8 7 8 7 6");
		board2.addStars("C5 E0");
		GameBoard board3 = new GameBoard("8 9 8 8 8 7 9 9 8");
		board3.addStars("H4 D4");
		GameBoard board4 = new GameBoard("11 10 10 9 8 10 9 11 7 10 11");
		board4.addStars("G8 H7");
		HumanPlayer Imogen = new HumanPlayer("Thomas", 'X');
		ComputerPlayer Robo = new ComputerPlayer("Robo", 'O');
		System.out.println(board1);
		// System.out.println(board2);
		// System.out.println(board3);
		// System.out.println(board4);
		int result = connect4.humanVcomputer(board1, Imogen, Robo, 2000);
	}

}
