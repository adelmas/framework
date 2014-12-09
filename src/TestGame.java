import game.*;
import java.util.List;
import java.util.LinkedList;

public class TestGame extends Game {
	public void init() {
		System.out.println("init()");
	}
	
	public boolean isGameOver() {
		return false;
	}

	public static void main(String[] args) {
		List<Player> listPlayers = new LinkedList<Player>();
		Game g = new TestGame();
		
		listPlayers.add(new humanPlayer("player1", 1, 1, 'x'));
		listPlayers.add(new humanPlayer("player2", 2, 2, 'o'));

		g.init();
		g.setBoard(new Board(5, 5));
		g.setPlayers(listPlayers);

		g.play();

	}

}
