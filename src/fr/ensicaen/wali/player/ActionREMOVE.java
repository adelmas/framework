package fr.ensicaen.wali.player;

import java.util.Scanner;

import fr.ensicaen.framework.board.Board;
import fr.ensicaen.framework.board.Case;
import fr.ensicaen.framework.board.Coordinates;
import fr.ensicaen.framework.board.Piece;
import fr.ensicaen.framework.player.Action;
import fr.ensicaen.framework.player.Player;

public class ActionREMOVE extends Action {
	
	public ActionREMOVE(String action, int type, Player player, Board board, Scanner scan) {
		super(action, type, player, board, scan);
	}

	@Override
	public void getParameters()
	{
		Scanner scan = getScanner();
		if(scan != null){
			System.out.println("Entrez les coordonnées du pion à supprimer :");
			setCoordinates(new Coordinates(scan.nextInt(), scan.nextInt()));
		}
	}
	
	@Override
	public boolean doAction() {
		Board board = getBoard();
		Coordinates coord = new Coordinates(getCoordinate(0), getCoordinate(1));
		
		
		if (!board.isEmpty(coord) && board.getCase(coord).getFirstPiece().getPlayer() != getPlayer()) {
			Case c = board.getCase(coord);
			setPlayer(c.getFirstPiece().getPlayer());
			c.removePiece(c.getFirstPiece());
			return true;
		}
		return false;
	}

	@Override
	public void undo() {
		System.out.println("REMOVE.undo()");
		Board board = getBoard();
		
		board.getCase(new Coordinates(getCoordinate(0), getCoordinate(1))).addPiece(new Piece(getPlayer(), 1));
	}

	@Override
	public void redo() {
		System.out.println("REMOVE.redo()");
		Board board = getBoard();
		Coordinates coord = new Coordinates(getCoordinate(0), getCoordinate(1));
		
		Case c = board.getCase(coord);
		c.removePiece(c.getFirstPiece());
	}
}
