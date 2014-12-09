package game;

public class Board {
	protected int[][] board;
	protected int width = 0, height = 0;
	
	public Board(int w, int h) {
		board = new int[w][h];
		width = w; height = h;
	}
	
	public String toString() {
		return "Board[" + width + "][" + height + "]";
	}
}
