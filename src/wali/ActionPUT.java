package wali;

import java.io.InputStream;
import java.util.Scanner;

import framework.player.Action;
import framework.player.Player;
import framework.board.Board;
import framework.board.Piece;

public class ActionPUT extends Action {

	public ActionPUT(String action, int type, Player player, Scanner scan) {
		super(action, type, player, scan);
	}

	@Override
	public void getParameters() {
		Scanner scan = getScanner();
		
		System.out.println("Entrez les coordonnées du pion à poser :");
		
		setX(scan.nextInt());
		setY(scan.nextInt());	
	}

	
	@Override
	public boolean doAction(Board board) {
		if (board.isEmpty(getX(), getY())) {
			board.getCase(getX(), getY()).addPiece(new Piece(getPlayer(), 1));
			return true;
		}
		return false;
	}

}
