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
		while(isValid(x+i,y) && boardW.getBoard()[x+i][y].getPieces().get(0).getPlayer() == play){
			comptVert++;
			i++;
		}
		i = 1;
		while(isValid(x-i,y) && boardW.getBoard()[x-i][y].getPieces().get(0).getPlayer() == play){
			comptVert++;
			i++;
		}
		// décompte horizontal
		i = 1;
		while(isValid(x,y+i) && boardW.getBoard()[x][y+i].getPieces().get(0).getPlayer() == play){
			comptHori++;
			i++;
		}
		i = 1;
		while(isValid(x,y-i) && boardW.getBoard()[x][y-i].getPieces().get(0).getPlayer() == play){
			comptHori++;
			i++;
		}
		return(Math.max(comptHori, comptVert)>2);
	}
	
	/* Obsolète */
	public boolean isGameOver() {
		int i, over = 1;
		String piece = "0";
		Board b = getBoard();
		
		/* Line / col */
		for (i=0; i<b.getHeight(); i++) {
			over = 1;
			piece = b.getCase(i, i).getType();
			if (piece == "vide")
				continue;
			for (int j=0; j<b.getWidth(); j++) {
				if (b.getCase(i, j).getType() != piece) {
					over = 0;
					break;
				}
			}
			if (over == 1)
				return true;
			over = 1;
			for (int k=0; k<b.getHeight(); k++) {
				if (b.getCase(k, i).getType() != piece) {
					over = 0;
					break;
				}
			}
			if (over == 1)
				return true;
		}
		
		/* Diag */
		over = 1;
		piece = b.getCase(0,0).getType();
		if (piece != "vide") {
			for (i=0; i<b.getWidth(); i++) {
				if (b.getCase(i, i).getType() != piece) {
					over = 0;
					break;
				}
			}
			if (over == 1)
				return true;
		}
		
		piece = b.getCase(b.getWidth()-1, 0).getType();
		if (piece != "vide") {
			over = 1;
			for (i=0; i<b.getWidth(); i++) {
				if (b.getCase(b.getWidth()-(i+1), i).getType() != piece) {
					over = 0;
					break;
				}
			}
			if (over == 1)
				return true;
		}
		
		return false;
	}
	
	public void play() {
		while (1==1) {
			Player player = getCurrentPlayer();
			Action action = player.getAction(null);
			
			action.doAction(getBoard());
			
			System.out.println(toString());
			if (isGameOver())
				break;
			
			nextPlayer();
		}
		System.out.println(getCurrentPlayer().getName() + " remporte la partie !");
	}

	public static void main(String[] args) {
		List<Player> listPlayers = new LinkedList<Player>();
		Game g = new TestGame();
		
		listPlayers.add(new HumanPlayer("player1", 1, 1, "X"));
		listPlayers.add(new HumanPlayer("player2", 2, 2, "O"));

		g.init();
		g.setBoard(new BoardMorpion(3, 3));
		g.setPlayers(listPlayers);

		g.play();

	}

}
