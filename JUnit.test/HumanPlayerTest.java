import static org.junit.Assert.*;
import game.Game;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import joueur.*
;
import junit.framework.Assert;
public class HumanPlayerTest {

	@Test
	public void testGetAction() {
		Player tester =new HumanPlayer("player1", 1, 1, "X");
		tester.getAction(null);
	}

	@Test
	public void testHumanPlayer() {
		List<HumanPlayer> listPlayers = new LinkedList<HumanPlayer>();
		listPlayers.add(new HumanPlayer("player1", 1, 1, "X"));
		listPlayers.add(new HumanPlayer("player2", 2, 2, "O"));
	}

	@Test
	public void testPlayer() {
		List<Player> listPlayers = new LinkedList<Player>();
		listPlayers.add(new HumanPlayer("player1", 1, 1, "X"));
		listPlayers.add(new HumanPlayer("player2", 2, 2, "O"));
		
	}

	@Test
	public void testToString() {
		List<Player> listPlayers = new LinkedList<Player>();
		listPlayers.add(new HumanPlayer("player1", 1, 1, "X"));
		listPlayers.add(new HumanPlayer("player2", 2, 2, "O"));
		listPlayers.toString();
	}

	@Test
	public void testThrowDice() {
		Player tester = new HumanPlayer("player1", 1, 1, "X");
		assertTrue(tester.throwDice()<6 && tester.throwDice()>0);
	}

}
