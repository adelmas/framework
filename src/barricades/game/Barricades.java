package barricades.game;

import java.util.LinkedList;
import java.util.List;

import barricades.player.ActionMOVE;
import barricades.player.ActionPUT;
import framework.board.Board;
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
import barricades.graphics.FrameBarricades;

public class Barricades extends Game {
	private PanelBarricades pBarricades;
		
	
	private static Player player;

	@Override
	public void init() {
		FrameBarricades f = new FrameBarricades(this);
		pBarricades = f.getPanel();
		System.out.println("init");
		LinkedList<JButton> list = f.getButtons();
		for(JButton b : list){
			System.out.println("ajout des boutons");
			//b.addActionListener(this);
		}
	}

	@Override
	public boolean isGameOver() {
		return false;
	}

	@Override
	public void play() {
				
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
		ActionMOVE act_move = new ActionMOVE("MOVE", 0, player, b, player.getScanner());
		act_move.getParameters();
		act_move.doAction();
		ActionREMOVE act_remove = new ActionREMOVE("REMOVE", 0, player, b, player.getScanner());
		act_remove.getParameters();
		act_remove.doAction();
		
		
		if (b.getCase(new Coordinates(2)).isEmpty()) {
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
