package fr.ensicaen.barricades.player;

import java.util.Scanner;

import fr.ensicaen.framework.board.Board;
import fr.ensicaen.framework.board.Case;
import fr.ensicaen.framework.board.Coordinates;
import fr.ensicaen.framework.board.Piece;
import fr.ensicaen.framework.player.Action;
import fr.ensicaen.framework.player.Player;

public class ActionMOVE extends Action {
	
	private int _oldNum;
	
	public ActionMOVE(String action, int type, Player player, Board board, Scanner scan) {
		super(action, type, player, board, scan);
	}

	public void setoldNum(int oldNum){
		_oldNum = oldNum;
	}
	
	@Override
	public void getParameters()
	{
		Scanner scan = getScanner();
		
		if(scan != null){
			System.out.println("Entrez les coordonnées du pion à déplacer :");
			
			_oldNum = scan.nextInt();
			
			System.out.println("Vers ?");
			
			setCoordinates(new Coordinates(scan.nextInt()));
		}
	}
	
	@Override
	public boolean doAction() {
		int Num = getCoordinate(0);
		Board board = getBoard();
		
		if (!board.isEmpty(new Coordinates(Num)) && !board.isEmpty(new Coordinates(_oldNum)) && (Math.abs(_oldNum - Num) <= 1)&& board.getCase(new Coordinates(Num)).getPieces().size() == 0 && board.getCase(new Coordinates(_oldNum)).getPieces().size() != 0) {
			Case c = board.getCase(new Coordinates(_oldNum));
			/*if(c.getFirstPiece().getPlayer().equals(super.getPlayer())) {*/
				board.getCase(new Coordinates(getCoordinate(0))).addPiece(new Piece(getPlayer(), 1));
				c.removePiece(c.getFirstPiece());
				return true;
			/*}*/
		}
		return false;
	}

	@Override
	public void undo() {
		System.out.println("MOVE.undo()");
		int tempNum=getCoordinate(0);
		int Num = _oldNum;
		_oldNum=tempNum;
		
		
		super.setCoordinates(new Coordinates(Num));
		Board board = getBoard();
		Case c = board.getCase(new Coordinates(_oldNum));
		board.getCase(new Coordinates(Num)).addPiece(new Piece(getPlayer(), 1));
		c.removePiece(c.getFirstPiece());
	}

	@Override
	public void redo() {
		System.out.println("MOVE.redo()");
		int tempNum=getCoordinate(0);
		int Num = _oldNum;
		_oldNum=tempNum;
		
		super.setCoordinates(new Coordinates(Num));
		Board board = getBoard();
		Case c = board.getCase(new Coordinates(_oldNum));
		board.getCase(new Coordinates(Num)).addPiece(new Piece(getPlayer(), 1));
		c.removePiece(c.getFirstPiece());
	}
}
