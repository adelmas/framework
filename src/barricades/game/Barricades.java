package barricades.game;

import java.util.LinkedList;
import java.util.List;

import barricades.player.ActionMOVE;
import barricades.player.ActionPUT;
import framework.board.Board;
import framework.board.Case;
import framework.board.Coordinates;
import framework.board.Piece;
import framework.game.*;
import framework.player.Action;
import framework.player.Player;
import barricades.board.*;

import framework.board.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.event.UndoableEditEvent;
import javax.swing.undo.UndoManager;

import barricades.player.ActionREMOVE;
import barricades.player.HumanPlayer;
import barricades.board.Graph;

public class Barricades extends Game {
	
	
	
	private static Player player;

	@Override
	public void init() {

		
	}

	@Override
	public boolean isGameOver() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void play() {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		Game g = new Barricades();
		Board b = new BoardBarricades();
		g.setBoard(b);
		
		g.init();
		
		player = new HumanPlayer("player1", 1, 1, "X", 10);
		boolean valide = false;
		ActionPUT act_put =  new ActionPUT("PUT", 0, player, b, player.getScanner());
		act_put.getParameters();
		act_put.doAction();
		
		if (b.getCase(new Coordinates(1)).isEmpty()) {
			System.out.println("vide");
		}
		else {
			System.out.println("ok");
		}

	}

	@Override
	public void gameOver() {
		// TODO Auto-generated method stub
		
	}

}
