package wali;

import framework.game.*;
import framework.action.*;
import framework.board.*;

import java.util.List;
import java.util.LinkedList;

import framework.joueur.Player;

public class TestGame extends Game {
	public void init() {
		System.out.println("init()");
	}
	
	/* Obsolète */
	public boolean isGameOver() {
		return false;
	}
	
	public void play() {
		int phase = 0, nb = 0;
		List<Action> actions;
		
		while (1==1) {
			Player player = getCurrentPlayer();
			
			if (phase == 0) {
				System.out.println("Phase "+phase+" : pose");
				actions = new LinkedList<Action>();
				actions.add(new ActionPUT("PUT", 0, 0, 0, player));
			}
			else {
				System.out.println("Phase "+phase+" : capture");
				actions = new LinkedList<Action>();
				actions.add(new ActionPUT("MOVE", 0, 0, 0, player)); /* ActionPUT -> ActionMOVE */
			}
			
			System.out.println("----------\n"+player.getName());
			Action action = player.getAction(actions);
			
			action.doAction(getBoard());
			
			System.out.println(toString());
			if (isGameOver())
				break;
			
			nextPlayer();
			nb++;
			if (nb > 24 && phase == 0)
				phase = 1;
		}
		System.out.println(getCurrentPlayer().getName() + " remporte la partie !");
	}

	public static void main(String[] args) {
		List<Player> listPlayers = new LinkedList<Player>();
		Game g = new TestGame();
		
		listPlayers.add(new HumanPlayer("player1", 1, 1, "X"));
		listPlayers.add(new HumanPlayer("player2", 2, 2, "O"));

		g.init();
		g.setBoard(new BoardMorpion(6, 6));
		g.setPlayers(listPlayers);

		g.play();

	}

}
