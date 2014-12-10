package game;
import java.util.List;

public abstract class Game {
	private Board board;
	List<Player> players;
	private int currentPlayer = 0, nbPlayers = 0;
	
	public abstract void init();
	public abstract boolean isGameOver();
	
	public void setBoard(Board b) {
		board = b;
	}
	
	public Board getBoard() {
		return board;
	}
	
	public void setPlayers(List<Player> players) {
		this.players = players;
		nbPlayers = players.size();
	}
	
	public void play() {
		while (!isGameOver()) {
			Player player = players.get(currentPlayer);
			Action action = player.getAction(null);
			
			System.out.println(action.toString());
			action.doAction(board);
			
			currentPlayer = (currentPlayer + 1) % nbPlayers;
			System.out.println(toString());
		}
	}
	
	public String toString() {
		String str = "";
		
		return str + board.toString();
	}
}
