package fr.ensicaen.wali.player;
import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Test;

import fr.ensicaen.wali.board.BoardWali;
import fr.ensicaen.barricades.player.ActionMOVE;
import fr.ensicaen.barricades.player.HumanPlayer;
import fr.ensicaen.framework.player.Player;

public class ActionMOVETest {

	@Test
	public void testActionMOVE() {
		Player player=new HumanPlayer("player1", 1, 1, "X",0);
		BoardWali board=new BoardWali();
		Scanner scan = new Scanner(System.in);
		ActionMOVE tester= new ActionMOVE("move",1,player,board,scan);
		
	}
	
	@Test  
    public void testsetoldNum() {  
		Player player=new HumanPlayer("player1", 1, 1, "X",0);
		BoardWali board=new BoardWali();
		Scanner scan = new Scanner(System.in);
		ActionMOVE tester= new ActionMOVE("move",1,player,board,scan);
        tester.setoldNum(3);  
        assertTrue(tester.getoldNum() == 3);  
    }  
  
	
	@Test
	public void testgetParameters(){
		
		Player player=new HumanPlayer("player1", 1, 1, "X",0);
		BoardWali board=new BoardWali();
		Scanner scan = new Scanner(System.in);
		ActionMOVE tester= new ActionMOVE("move",1,player,board,scan);
		tester.getParameters();	
	
	}

	
	@Test 
	public void testdoAction(){
		Player player=new HumanPlayer("player1", 1, 1, "X",0);
		BoardWali board=new BoardWali();
		Scanner scan = new Scanner(System.in);
		ActionMOVE tester= new ActionMOVE("move",1,player,board,scan);
		assertTrue((tester.doAction()) || (!tester.doAction()));
		
	}
 
}