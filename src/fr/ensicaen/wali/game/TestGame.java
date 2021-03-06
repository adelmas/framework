package fr.ensicaen.wali.game;

import fr.ensicaen.framework.board.*;
import fr.ensicaen.framework.game.*;
import fr.ensicaen.framework.player.Action;
import fr.ensicaen.framework.player.Player;
import fr.ensicaen.wali.board.BoardWali;
import fr.ensicaen.wali.graphics.FrameWali;
import fr.ensicaen.wali.graphics.PanelWali;
import fr.ensicaen.wali.player.ActionMOVE;
import fr.ensicaen.wali.player.ActionPUT;
import fr.ensicaen.wali.player.ActionREMOVE;
import fr.ensicaen.wali.player.HumanPlayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.event.UndoableEditEvent;
import javax.swing.undo.UndoManager;

public class TestGame extends Game implements MouseListener, ActionListener {
	private int _phase = 0 ;
	private int _nbCoups = 0;
	private Coordinates _moveCoordinates;
	private boolean _isCapture = false;
	private PanelWali pWali;
		
	public void init() {
		FrameWali f = new FrameWali(this);
		pWali = f.getPanel();
		pWali.addMouseListener(this);
		LinkedList<JButton> list = f.getButtons();
		for(JButton b : list){
			b.addActionListener(this);
		}
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
			if (player.getScore() == 0){
				return true;
			}
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
				act_mov.setOldX(_moveCoordinates.getFirstCoordinate());
				act_mov.setOldY(_moveCoordinates.getSecondCoordinate());
				act_mov.setCoordinates(co);
			}
			actions.add(act_mov);
		}
			
			
		Action action = player.getAction(actions);		
			
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
		
		/* Action registration */
		getUndoableListener().undoableEditHappened(new UndoableEditEvent(this, action));
		
		if (_phase == 1) {
			if (plusDeDeuxPions((BoardWali)getBoard(), action.getCoordinate(0), action.getCoordinate(1), player)) {
				_isCapture = true;
			}	
		}
		
		setChanged();
		notifyObservers();
		System.out.println(toString());
	
		if (!_isCapture){
			nextPlayer();
		}
		_nbCoups++;
		updatePhase();
	}
	
	public void updatePhase() {
		if (_nbCoups >= 6) {
			_phase = 1;
		}
		else {
			_phase = 0;
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
				if (isGameOver()) {
					gameOver();
					return;
				}
				nextPlayer();
				getUndoableListener().undoableEditHappened(new UndoableEditEvent(this, action));
				System.out.println("Score " + targetPlayer.getName() + " : " + targetPlayer.getScore());
				_isCapture = false;
			}
		}		
	}
	
	public void play() {
		
		while (true) {
			coup(null);
			if (isGameOver()) {
				gameOver();
				break;
			}
		}
	}

	public Coordinates pixelToCoordinates(int x, int y){
    	Coordinates co;
    	int taille = 83, decal = 26;
    	if(x < decal || x > decal + 6*taille || y < decal || y > decal + 6*taille) {
    		System.out.println("Hors limites");
    		return null;
    	}
    	else {
    		int x_mat = (x-decal)/taille;
    		int y_mat = (y-decal)/taille;
    		co = new Coordinates(x_mat, y_mat);
    	}
    	return co;
    }
	
	public void mouseClicked(MouseEvent e) {
		int x = e.getY(), y = e.getX();
		Coordinates click = pixelToCoordinates(x,y);
		if(_phase  == 0){
			coup(click);
		}	
		else if(_phase == 1 && _isCapture){
			capture(click);
		}
	}
	
	public void mouseEntered(MouseEvent arg0) {

	}

	public void mouseExited(MouseEvent arg0) {

	}

	public void mousePressed(MouseEvent e) {
		if(_phase == 1) {
			int x = e.getY(), y = e.getX();
			_moveCoordinates = pixelToCoordinates(x,y);
		}
	}

	public void mouseReleased(MouseEvent e) {
		if(_phase == 1){
			int x = e.getY(), y = e.getX();
			Coordinates click = pixelToCoordinates(x,y);
			coup(click);
		}	
	}
	
	public static void main(String[] args) {
		List<Player> listPlayers = new LinkedList<Player>();
		Game g = new TestGame();
		
		g.setStartScore(3);
		listPlayers.add(new HumanPlayer("player1", 1, 1, "X", g.getStartScore()));
		listPlayers.add(new HumanPlayer("player2", 2, 2, "O", g.getStartScore()));

		Board b = new BoardWali();
		g.setBoard(b);
		g.init();
		g.setPlayers(listPlayers);
		g.play();
	}

	@Override
	public void gameOver() {
		setGameOver(true);
		setChanged();
		notifyObservers();
	}

	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		UndoManager undoManager = getUndoManager();
		if((b.getText().equals("New game"))){
			resetPlayers(getStartScore());
			Board board = getBoard();
			board.reset();
			undoManager.discardAllEdits();
			_nbCoups = 0;
			_phase = 0;
			_isCapture = false;
			setGameOver(false);
		}
		else if((b.getText().equals("Undo"))){
			setGameOver(false);
			if (undoManager.canUndo()) {
				undoManager.undo();
				prevPlayer();
				if (_nbCoups != 0) {
					_nbCoups--;
				}
				updatePhase();	
			}
		}
		else if((b.getText().equals("Give up"))){
			getCurrentPlayer().setScore(0);
			nextPlayer();
			setGameOver(true);
		}
		else{
			setGameOver(false);
			if (undoManager.canRedo()) {
				undoManager.redo();
				nextPlayer();
				_nbCoups++;
				updatePhase();
			}
		}
			
		setChanged();
		notifyObservers();
	}
}


