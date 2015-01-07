package wali.player;

import java.io.InputStream;
import java.util.Scanner;

import framework.player.Action;
import framework.player.Player;
import framework.board.Board;
import framework.board.Case;
import framework.board.Coordinates;
import framework.board.Piece;

public class ActionMOVE extends Action {
	
	private int _oldX = 0, _oldY = 0;
	
	public ActionMOVE(String action, int type, Player player, Board board, Scanner scan) {
		super(action, type, player, board, scan);
	}

	public void setOldX(int oldX){
		_oldX = oldX;
	}
	
	public void setOldY(int oldY){
		_oldY = oldY;
	}
	
	@Override
	public void getParameters()
	{
		Scanner scan = getScanner();
		
		if(scan != null){
			System.out.println("Entrez les coordonnées du pion à déplacer :");
			
			_oldX = scan.nextInt();
			_oldY = scan.nextInt();
			
			System.out.println("Vers ?");
			
			setCoordinates(new Coordinates(scan.nextInt(), scan.nextInt()));
		}
	}
	
	@Override
	public boolean doAction() {
		int x = getCoordinate(0), y = getCoordinate(1);
		Board board = getBoard();
		
		if (board.isEmpty(new Coordinates(x, y)) && !board.isEmpty(new Coordinates(_oldX, _oldY)) && (Math.abs(_oldX - x) == 1 || Math.abs(_oldY - y) == 1) && Math.abs(_oldX - x) != Math.abs(_oldY - y)) {
			Case c = board.getCase(new Coordinates(_oldX, _oldY));
			if(c.getFirstPiece().getPlayer().equals(super.getPlayer())) {
				board.getCase(new Coordinates(getCoordinate(0), getCoordinate(1))).addPiece(new Piece(getPlayer(), 1));
				c.removePiece(c.getFirstPiece());
				return true;
			}
		}
		return false;
	}

	@Override
	public void undo() {
		System.out.println("MOVE.undo()");
		int tempx=getCoordinate(0), tempy=getCoordinate(1);
		int x = _oldX, y = _oldY;
		_oldX=tempx;
		_oldY=tempy;
		
		
		super.setCoordinates(new Coordinates(x, y));
		Board board = getBoard();
		Case c = board.getCase(new Coordinates(_oldX, _oldY));
		board.getCase(new Coordinates(x, y)).addPiece(new Piece(getPlayer(), 1));
		c.removePiece(c.getFirstPiece());
	}

	@Override
	public void redo() {
		System.out.println("MOVE.redo()");
		int tempx=getCoordinate(0), tempy=getCoordinate(1);
		int x = _oldX, y = _oldY;
		_oldX=tempx;
		_oldY=tempy;
		
		super.setCoordinates(new Coordinates(x, y));
		Board board = getBoard();
		Case c = board.getCase(new Coordinates(_oldX, _oldY));
		board.getCase(new Coordinates(x, y)).addPiece(new Piece(getPlayer(), 1));
		c.removePiece(c.getFirstPiece());
	}
}
