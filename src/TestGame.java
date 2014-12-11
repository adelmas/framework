import game.*;
import java.util.List;
import java.util.LinkedList;

public class TestGame extends Game {
	public void init() {
		System.out.println("init()");
	}
	
	public boolean isGameOver() {
		int i, over = 1;
		String piece = "0";
		Board b = getBoard();
		
		/* Line / col */
		for (i=0; i<b.getHeight(); i++) {
			piece = b.getCase(i, 0).getType();
			if (piece == "vide")
				continue;
			for (int j=0; j<b.getWidth(); j++) {
				if (b.getCase(i, j).getType() != piece)
					over = 0;
			}
			if (over == 1)
				return true;
			over = 1;
			for (int k=0; k<b.getHeight(); k++) {
				if (b.getCase(k, i).getType() != piece)
					over = 0;
			}
			if (over == 1)
				return true;
		}
		
		/* Diag */
		over = 1;
		piece = b.getCase(0,0).getType();
		if (piece != "vide") {
			for (i=0; i<b.getWidth(); i++) {
				if (b.getCase(i, i).getType() != piece)
					over = 0;
			}
			if (over == 1)
				return true;
		}
		
		piece = b.getCase(b.getWidth()-1, 0).getType();
		if (piece != "vide") {
			over = 1;
			for (i=0; i<b.getWidth(); i++) {
				if (b.getCase(b.getWidth()-(i+1), i).getType() != piece)
					over = 0;
			}
			if (over == 1)
				return true;
		}
		
		return false;
	}

	public static void main(String[] args) {
		List<Player> listPlayers = new LinkedList<Player>();
		Game g = new TestGame();
		
		listPlayers.add(new HumanPlayer("player1", 1, 1, "X"));
		listPlayers.add(new HumanPlayer("player2", 2, 2, "O"));

		g.init();
		g.setBoard(new Board(3, 3));
		g.setPlayers(listPlayers);

		g.play();

	}

}
