package fr.ensicaen.wali.player;
import static org.junit.Assert.*;
import fr.ensicaen.framework.board.Board;
import fr.ensicaen.framework.player.Action;
import fr.ensicaen.framework.player.Player;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;

import fr.ensicaen.wali.board.BoardWali;
import fr.ensicaen.barricades.player.ActionMOVE;
import fr.ensicaen.barricades.player.HumanPlayer;
import junit.framework.Assert;
public class HumanPlayerTest {

	@Test
	public void testGetAction() {
		List<Action> list=new LinkedList<Action>();			
		Player tester =new HumanPlayer("player1", 1, 1, "X",0);
		BoardWali board=new BoardWali();
		Scanner scan = new Scanner(System.in);
		ActionMOVE action= new ActionMOVE("move",1,tester,board,scan);
		ActionMOVE action2= new ActionMOVE("move",1,tester,board,scan);
		list.add(action);
		list.add(action2);
		tester.getAction(list);
	}

	@Test
	public void testHumanPlayer() {
		List<HumanPlayer> listPlayers = new LinkedList<HumanPlayer>();
		listPlayers.add(new HumanPlayer("player1", 1, 1, "X",0));
		listPlayers.add(new HumanPlayer("player2", 2, 2, "O",0));
		assertFalse(listPlayers.isEmpty());
	}

	@Test
	public void testPlayer() {
		List<Player> listPlayers = new LinkedList<Player>();
		listPlayers.add(new HumanPlayer("player1", 1, 1, "X",0));
		listPlayers.add(new HumanPlayer("player2", 2, 2, "O",0));
		
	}

	@Test
	public void testToString() {
		List<Player> listPlayers = new LinkedList<Player>();
		listPlayers.add(new HumanPlayer("player1", 1, 1, "X",0));
		listPlayers.add(new HumanPlayer("player2", 2, 2, "O",0));
		listPlayers.toString();
	}

	@Test
	public void testThrowDice() {
		Player tester = new HumanPlayer("player1", 1, 1, "X",0);
		assertTrue(tester.throwDice()<6 && tester.throwDice()>0);
	}
	
	
	
	

}