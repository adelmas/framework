package fr.ensicaen.wali.player;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Test;

import fr.ensicaen.wali.board.BoardWali;
import fr.ensicaen.barricades.player.ActionREMOVE;
import fr.ensicaen.barricades.player.HumanPlayer;
import fr.ensicaen.framework.player.Player;

public class ActionREMOVETest {

	@Test
	public void testActionPUT() {
		Player player=new HumanPlayer("player1", 1, 1, "X",0);
		BoardWali board=new BoardWali();
		Scanner scan = new Scanner(System.in);
		ActionREMOVE tester= new ActionREMOVE("REMOVE",1,player,board,scan);
		assertEquals(tester.getPlayer(),player);
		
	}  
	
	@Test
	public void testgetParameters(){
		
		Player player=new HumanPlayer("player1", 1, 1, "X",0);
		BoardWali board=new BoardWali();
		Scanner scan = new Scanner(System.in);
		ActionREMOVE tester= new ActionREMOVE("REMOVE",1,player,board,scan);
		tester.getParameters();	
	
	}
	

    
}