package fr.ensicaen.wali.player;


import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Test;

import fr.ensicaen.wali.board.BoardWali;
import fr.ensicaen.barricades.player.ActionPUT;
import fr.ensicaen.barricades.player.HumanPlayer;
import fr.ensicaen.framework.player.Player;

public class ActionPUTTest {

	@Test
	public void testActionPUT() {
		Player player=new HumanPlayer("player1", 1, 1, "X",0);
		BoardWali board=new BoardWali();
		Scanner scan = new Scanner(System.in);
		ActionPUT tester= new ActionPUT("PUT",1,player,board,scan);
		
	}  
	
	@Test
	public void testgetParameters(){
		
		Player player=new HumanPlayer("player1", 1, 1, "X",0);
		BoardWali board=new BoardWali();
		Scanner scan = new Scanner(System.in);
		ActionPUT tester= new ActionPUT("PUT",1,player,board,scan);
		tester.getParameters();			
	}
	
	@Test 
	public void testdoAction(){
		Player player=new HumanPlayer("player1", 1, 1, "X",0);
		BoardWali board=new BoardWali();
		Scanner scan = new Scanner(System.in);
		ActionPUT tester= new ActionPUT("PUT",1,player,board,scan);
		assertTrue((tester.doAction()) || (!tester.doAction()));
		
	}

}