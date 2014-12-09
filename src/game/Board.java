package game;

public class Board {
	protected char[][] board;
	protected int width = 0, height = 0;
	
	public Board(int w, int h) {
		board = new char[w][h];
		width = w; height = h;
	}
	
	public void setCase(char car, int x, int y) {
		try {
			board[x][y] = car;
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Position hors du plateau.");
		}
	}
	
	public boolean isEmpty(int x, int y) {
		if (board[x][y] == 0)
			return true;
		return false;
	}
	
	public String toString() {
		String strGrid = "";
		
		for (int i=0; i<board.length; i++) {
			for (int j=0; j<board[i].length; j++)
				strGrid += board[i][j] + "|";
			strGrid += "\n";
		}
		
		return "Board[" + width + "][" + height + "]:\n" + strGrid;
	}
}
