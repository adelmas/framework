package wali.game;

import framework.game.*;
import framework.graphics.Frame;
import framework.board.*;

import java.util.List;
import java.util.LinkedList;

import javax.swing.event.UndoableEditEvent;
import javax.swing.undo.UndoManager;

import wali.board.BoardWali;
import wali.graphics.FrameWali;
import wali.player.ActionMOVE;
import wali.player.ActionPUT;
import wali.player.ActionREMOVE;
import wali.player.HumanPlayer;

import framework.player.Action;
import framework.player.Player;

public class TestGame extends Game {
	private UndoManager _undoManager = new UndoManager();
	private UndoableListener _undoListener = new UndoableListener(_undoManager);
	
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
		while(isValid(x+i,y) && !boardW.getCase(new Coordinates(x+i, y)).isEmpty() && boardW.getCase(new Coordinates(x+i, y)).getFirstPiece().getPlayer() == play){
			comptVert++;
			i++;
		}
		i = 1;
		while(isValid(x-i,y) && !boardW.getCase(new Coordinates(x-i, y)).isEmpty() && boardW.getCase(new Coordinates(x-i, y)).getFirstPiece().getPlayer() == play){
			comptVert++;
			i++;
		}
		// décompte horizontal
		i = 1;
		while(isValid(x,y+i) && !boardW.getCase(new Coordinates(x, y+i)).isEmpty() && boardW.getCase(new Coordinates(x, y+i)).getFirstPiece().getPlayer() == play){
			comptHori++;
			i++;
		}
		i = 1;
		while(isValid(x,y-i) && !boardW.getCase(new Coordinates(x, y-i)).isEmpty() && boardW.getCase(new Coordinates(x, y-i)).getFirstPiece().getPlayer() == play){
			comptHori++;
			i++;
		}

		return (Math.max(comptHori, comptVert)>=2);
	}
	
	public boolean isGameOver() {
		for (Player player : getPlayers()) {
			if (player.getScore() == 0)
				return true;
		}
		return false;
	}
	
	public void play() {
		int phase = 0, nb = 0;
		List<Action> actions;
		boolean valide = false;
		Board board = getBoard();
		
		while (1==1) {
			Player player = getCurrentPlayer();
			
			if (phase == 0) {
				System.out.println("Phase "+phase+" : pose");
				actions = new LinkedList<Action>();
				actions.add(new ActionPUT("PUT", 0, player, board, player.getScanner()));
			}
			else {
				System.out.println("Phase "+phase+" : deplacement");
				actions = new LinkedList<Action>();
				actions.add(new ActionMOVE("MOVE", 0, player, board, player.getScanner()));
			}
			
			System.out.println("----------\n"+player.getName());
			Action action = player.getAction(actions);
			
			/* Action registration */
			_undoListener.undoableEditHappened(new UndoableEditEvent(this, action));
			
			/* ----- Controle des actions ----- */
			
			if (phase == 0) {
				valide = plusDeDeuxPions((BoardWali)board, action.getCoordinate(0), action.getCoordinate(1), player);
			}
			if (valide == true) {
 				System.out.println("Action invalide (Game)");
 				continue;
			}
			
			/* Execution de l'action si elle est valide */
			if (!action.doAction()) {
				System.out.println("Action invalide (Action)");
				continue;
			}
			
			if (phase == 1) {
				if (plusDeDeuxPions((BoardWali)getBoard(), action.getCoordinate(0), action.getCoordinate(1), player)) {
					actions = new LinkedList<Action>();
					actions.add(new ActionREMOVE("REMOVE", 0, player, board, player.getScanner()));
					Player targetPlayer;
					do {
						action = player.getAction(actions);
						targetPlayer = board.getCase(new Coordinates(action.getCoordinate(0), action.getCoordinate(1))).getFirstPiece().getPlayer();
					} while (!action.doAction());
					targetPlayer.decreaseScore(1);
					_undoListener.undoableEditHappened(new UndoableEditEvent(this, action));
					
					System.out.println("Score " + targetPlayer.getName() + " : " + targetPlayer.getScore());
				}
			}
			
			_undoManager.undo();
			
			setChanged();
			notifyObservers();
			System.out.println(toString());
			if (isGameOver()) {
				break;
			}
			
			nextPlayer();
			nb++;
			if (nb >= 6 && phase == 0) {
				phase = 1;
			}
		}
		System.out.println(getCurrentPlayer().getName() + " remporte la partie !");
	}

	public static void main(String[] args) {
		List<Player> listPlayers = new LinkedList<Player>();
		Game g = new TestGame();
		
		listPlayers.add(new HumanPlayer("player1", 1, 1, "X", 12));
		listPlayers.add(new HumanPlayer("player2", 2, 2, "O", 12));

		g.init();
		
		Board b = new BoardWali();
		g.setBoard(b);
		g.setPlayers(listPlayers);

		Frame f = new FrameWali(g);
		g.play();

	}


}

