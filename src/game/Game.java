package game;
import java.util.List;

public abstract class Game {
	protected Board board;
	
	public abstract void init();
	public abstract boolean isGameOver();
	public abstract void play();
	
	public void setBoard(Board b) {
		board = b;
	}
	
	public String toString() {
		return board.toString();
	}
}
