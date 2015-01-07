package wali.game;

import framework.game.*;
import framework.graphics.Frame;
import framework.graphics.Panel;
import framework.board.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.LinkedList;

import javax.swing.event.UndoableEditEvent;
import javax.swing.undo.UndoManager;

import wali.board.BoardWali;
import wali.graphics.FrameWali;
import wali.graphics.PanelWali;
import wali.player.ActionMOVE;
import wali.player.ActionPUT;
import wali.player.ActionREMOVE;
import wali.player.HumanPlayer;
import framework.player.Action;
import framework.player.Player;

public class TestGame extends Game implements MouseListener {
	private UndoManager _undoManager = new UndoManager();
	private UndoableListener _undoListener = new UndoableListener(_undoManager);
	private int _phase = 0 ;
	private int _nbCoups = 0;
	private Coordinates _click_move;
	private boolean _isCapture = false;
	
	public void init() {
		FrameWali f = new FrameWali(this);
		PanelWali pWali = f.getPanel();
		pWali.addMouseListener(this);
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
	
	public void coup(Coordinates co){
		//List<Player> players = getPlayers();
		Player player = getCurrentPlayer();
		List<Action> actions;
		Board board = getBoard();
		boolean valide = false;
		ActionPUT act_put;

		if (_phase == 0) {
			System.out.println("Phase "+_phase+" : pose");
			actions = new LinkedList<Action>();
			if(_phase == 0){
				if(co == null) {
					act_put = new ActionPUT("PUT", 0, player, board, player.getScanner());	
				}
				else{
					act_put = new ActionPUT("PUT", 0, player, board, null);
					act_put.setCoordinates(co);
				}
				actions.add(act_put);

			}
		}
		else {
			System.out.println("Phase "+_phase+" : deplacement");
			actions = new LinkedList<Action>();
			ActionMOVE act_mov;
			if(co == null){
				act_mov = new ActionMOVE("MOVE", 0, player, board, player.getScanner());
			}
			else {
				act_mov = new ActionMOVE("MOVE", 0, player, board, null);
				act_mov.setOldX(_click_move.getFirstCoordinate());
				act_mov.setOldY(_click_move.getSecondCoordinate());
				act_mov.setCoordinates(co);
			}
			actions.add(act_mov);
		}
			
			
			Action action = player.getAction(actions);		
			
			/* Action registration */
			_undoListener.undoableEditHappened(new UndoableEditEvent(this, action));
			
			/* ----- Controle des actions ----- */
			if (_phase == 0) {
				valide = plusDeDeuxPions((BoardWali)board, action.getCoordinate(0), action.getCoordinate(1), player);
				}
			if (valide == true) {
				System.out.println("Action invalide (Game)");
				return;
			}

			/* Execution de l'action si elle est valide */
			if (!action.doAction()) {
				System.out.println("Action invalide (Action) " + action.toString());
				System.out.println(toString());
				return;
			}
		
			if (_phase == 1) {
				if (plusDeDeuxPions((BoardWali)getBoard(), action.getCoordinate(0), action.getCoordinate(1), player)) {
					System.out.println("ON EST LAAAA");
					_isCapture = true;
				}
			
		}
		setChanged();
		notifyObservers();
		System.out.println(toString());
	
		if (!_isCapture)
			nextPlayer();
		_nbCoups++;
		if (_nbCoups >= 6 && _phase == 0) {
			_phase = 1;
		}
	}
	
	public void capture(Coordinates co){
		Board board = getBoard();
		Player player = getCurrentPlayer();
		if(!board.isEmpty(co)){
			LinkedList<Action> actions = new LinkedList<Action>();
			actions.add(new ActionREMOVE("REMOVE",0,getCurrentPlayer(),getBoard(),null));
			Action action = player.getAction(actions);
			action.setCoordinates(co);
			Player targetPlayer = board.getCase(co).getFirstPiece().getPlayer();
			if (action.doAction()) {
				targetPlayer.decreaseScore(1);
				setChanged();
				notifyObservers();
				if (isGameOver())
					return; /* TODO : méthode gameOver() */
				nextPlayer();
				_undoListener.undoableEditHappened(new UndoableEditEvent(this, action));
				System.out.println("Score " + targetPlayer.getName() + " : " + targetPlayer.getScore());
				_isCapture = false;
			}
		}		
	}
	
	public void play() {
		
		while (true) {
			coup(null);
			if (isGameOver()) {
				break;
			}
		}
		System.out.println(getCurrentPlayer().getName() + " remporte la partie !");
	}

	public void mouseClicked(MouseEvent e) {
		int x = e.getY(), y = e.getX(),x_mat,y_mat;
		int taille = 83, decal = 26;
		if(_phase  == 0){
			if((e.getX() < decal) || (e.getX() > decal + 6*taille) || (e.getY() < decal) || (e.getY() > decal+6*taille)){
				System.out.println("hors limites");
			}
			else{
				x_mat = (x-decal)/taille;
				y_mat = (y-decal)/taille;
				Coordinates click = new Coordinates(x_mat,y_mat);
				coup(click);
			}	
		}
		else if(_phase == 1 && _isCapture){
			if((e.getX() < decal) || (e.getX() > decal + 6*taille) || (e.getY() < decal) || (e.getY() > decal+6*taille)){
				System.out.println("hors limites");
			}
			else{
				x_mat = (x-decal)/taille;
				y_mat = (y-decal)/taille;
				Coordinates click = new Coordinates(x_mat,y_mat);
				System.out.println(click.toString());
				capture(click);
			}
		}
	}
	
	public void mouseEntered(MouseEvent arg0) {

	}

	public void mouseExited(MouseEvent arg0) {

	}

	public void mousePressed(MouseEvent e) {
		if(_phase == 1) {
			int x = e.getY(), y = e.getX(),x_mat,y_mat;
			int taille = 83, decal = 26;
			if((e.getX() < decal) || (e.getX() > decal + 6*taille) || (e.getY() < decal) || (e.getY() > decal+6*taille))
				System.out.println("hors limites");
				else{
					x_mat = (x-decal)/taille;
					y_mat = (y-decal)/taille;
					_click_move = new Coordinates(x_mat,y_mat);
					System.out.println("MOUSE PRESSED : "+_click_move.toString());
				}
		}
	}

	public void mouseReleased(MouseEvent e) {
		if(_phase == 1){
			int x = e.getY(), y = e.getX(),x_mat,y_mat;
			int taille = 83, decal = 26;
			if((e.getX() < decal) || (e.getX() > decal + 6*taille) || (e.getY() < decal) || (e.getY() > decal+6*taille)){
				System.out.println("hors limites");
			}
			else{
				x_mat = (x-decal)/taille;
				y_mat = (y-decal)/taille;
				System.out.println("x = "+x_mat+"\ty = "+y_mat);
				Coordinates click = new Coordinates(x_mat,y_mat);
				System.out.println("MOUSE RELEASED : "+click.toString());
				coup(click);
			}	
		}
	}
	
	public static void main(String[] args) {
		List<Player> listPlayers = new LinkedList<Player>();
		Game g = new TestGame();
		
		listPlayers.add(new HumanPlayer("player1", 1, 1, "X", 12));
		listPlayers.add(new HumanPlayer("player2", 2, 2, "O", 12));

		Board b = new BoardWali();
		g.setBoard(b);
		g.init();
		g.setPlayers(listPlayers);
		g.play();
	}
}

