package barricades.game;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import barricades.player.ActionMOVE;
import barricades.player.ActionPUT;
import framework.board.Board;
import framework.board.Case;
import framework.board.Coordinates;
import framework.game.*;
import framework.player.Action;
import framework.player.Player;
import barricades.board.*;
import framework.board.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
		BoardBarricades b = (BoardBarricades) getBoard();
		
		loadMapFromFile("resources/map.txt");
		
		System.out.println(b);
	
		FrameBarricades f = new FrameBarricades(this);
		pBarricades = f.getPanel();
		System.out.println("init");
		LinkedList<JButton> list = f.getButtons();
	}


	@Override
	public boolean isGameOver() {
		return false;
	}

	@Override
	public void play() {
				
	}
	
	public void loadMapFromFile(String file) {
		BoardBarricades b = (BoardBarricades) getBoard();
		
		try {
			BufferedReader buf = new BufferedReader(new FileReader(file));
			try {
				String line = "";
				int phase = 0;
				while ((line = buf.readLine()) != null) {
					line = line.trim();
					if (line.equals("")) {
						phase = 1;
						continue;
					}
					String[] split = line.split("\t");
					if (phase == 0) {
						b.addNode(Integer.parseInt(split[0]), new Coordinates(Integer.parseInt(split[1]), Integer.parseInt(split[2])));
					}
					else {
						int numFather = 0, numChild = 0;
						for (int i=0; i<split.length; i++) {
							if (i == 0) {
								numFather = Integer.parseInt(split[i]);
							}
							else {
								numChild = Integer.parseInt(split[i]);
								b.addChild(numFather, numChild);
							}
						}
					}
				}
			} finally {
				buf.close();
			}
		} catch (IOException ioe) {
			System.out.println("Error loadMapFromFile : File not found");
		}
	}
	
	public static void main(String[] args) {
		Game g = new Barricades();
		BoardBarricades b = new BoardBarricades();
		
		Player player;
		
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
