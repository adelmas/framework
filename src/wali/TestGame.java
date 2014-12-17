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
	
	public boolean isValid(int x, int y){
		return(x < 5 && x >= 0 && y < 6 && y >=0);
	}
	
	public boolean plusDeDeuxPions(BoardWali boardW, int x, int y, Player play){
		int comptVert = 0;
		int comptHori = 0;
		// décompte vertical
		int i = 1;
		while(isValid(x+i,y) && !boardW.getBoard()[x+i][y].isEmpty() && boardW.getBoard()[x+i][y].getFirstPiece().getPlayer() == play){
			comptVert++;
			i++;
		}
		i = 1;
		while(isValid(x-i,y) && !boardW.getBoard()[x+i][y].isEmpty() && boardW.getBoard()[x-i][y].getFirstPiece().getPlayer() == play){
			comptVert++;
			i++;
		}
		// décompte horizontal
		i = 1;
		while(isValid(x,y+i) && !boardW.getBoard()[x+i][y].isEmpty() && boardW.getBoard()[x][y+i].getFirstPiece().getPlayer() == play){
			comptHori++;
			i++;
		}
		i = 1;
		while(isValid(x,y-i) && !boardW.getBoard()[x+i][y].isEmpty() && boardW.getBoard()[x][y-i].getFirstPiece().getPlayer() == play){
			comptHori++;
			i++;
		}
		return(Math.max(comptHori, comptVert)>2);
	}
	
	/* Obsolète */
	public boolean isGameOver() {
		return false;
	}
	
	public void play() {
		int phase = 0, nb = 0;
		List<Action> actions;
		boolean valide = false;
		
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
			
			/* Controle des actions */
			if (phase == 0)
				valide = plusDeDeuxPions((BoardWali)getBoard(), action.getX(), action.getY(), player);
			
			if (valide == false)
				System.out.println("Action invalide");
			
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
		g.setBoard(new BoardWali());
		g.setPlayers(listPlayers);

		g.play();

	}

}
