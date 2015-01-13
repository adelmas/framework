package fr.ensicaen.wali.player;

import java.util.Scanner;

import fr.ensicaen.framework.board.Board;
import fr.ensicaen.framework.board.Case;
import fr.ensicaen.framework.board.Coordinates;
import fr.ensicaen.framework.board.Piece;
import fr.ensicaen.framework.player.Action;
import fr.ensicaen.framework.player.Player;

public class ActionPUT extends Action {

	public ActionPUT(String action, int type, Player player, Board board,Scanner scan) {
		super(action, type, player, board, scan);
	}

	@Override
	public void getParameters() {
		Scanner scan = getScanner();
		
		System.out.println("Entrez les coordonnées du pion à poser :");
		
		setCoordinates(new Coordinates(scan.nextInt(), scan.nextInt()));	
	}

	
	
	@Override
	public boolean doAction() {
		Board board = getBoard();
		
		if (board.isEmpty(new Coordinates(getCoordinate(0), getCoordinate(1)))) {
			board.getCase(new Coordinates(getCoordinate(0), getCoordinate(1))).addPiece(new Piece(getPlayer(), 1));
			return true;
		}
		return false;
	}

	@Override
	public void undo() {
		System.out.println("PUT.undo()");
		Board board = getBoard();
		Coordinates coord = new Coordinates(getCoordinate(0), getCoordinate(1));
		Case c = board.getCase(coord);
		c.removePiece(c.getFirstPiece());
	}

	@Override
	public void redo() {
		System.out.println("PUT.redo()");
		Board board = getBoard();
		
		board.getCase(new Coordinates(getCoordinate(0), getCoordinate(1))).addPiece(new Piece(getPlayer(), 1));

	}

	public void setParameters(Coordinates co) {
		
	}

}
