package wali.player;

import java.io.InputStream;
import java.util.Scanner;

import framework.player.Action;
import framework.player.Player;
import framework.board.Board;
import framework.board.Case;
import framework.board.Piece;

public class ActionREMOVE extends Action {
	
	public ActionREMOVE(String action, int type, Player player, Board board, Scanner scan) {
		super(action, type, player, board, scan);
	}

	@Override
	public void getParameters()
	{
		Scanner scan = getScanner();
		
		System.out.println("Entrez les coordonnées du pion à supprimer :");
		
		setX(scan.nextInt());
		setY(scan.nextInt());	
	}
	
	@Override
	public boolean doAction() {
		Board board = getBoard();
		
		if (!board.isEmpty(getX(), getY()) && board.getCase(getX(), getY()).getFirstPiece().getPlayer() != getPlayer()) {
			Case c = board.getCase(getX(), getY());
			c.removePiece(c.getFirstPiece());
			return true;
		}
		return false;
	}

	@Override
	public void undo() {
		System.out.println("REMOVE.undo()");
	}

	@Override
	public void redo() {
		
	}
}
