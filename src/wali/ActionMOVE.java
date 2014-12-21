package wali;

import java.io.InputStream;
import java.util.Scanner;

import framework.player.Action;
import framework.player.Player;
import framework.board.Board;
import framework.board.Case;
import framework.board.Piece;

public class ActionMOVE extends Action {
	
	private int _oldX = 0, _oldY = 0;
	
	public ActionMOVE(String action, int type, Player player, Scanner scan) {
		super(action, type, player, scan);
	}

	@Override
	public void getParameters()
	{
		Scanner scan = getScanner();
		
		System.out.println("Entrez les coordonnées du pion à déplacer :");
		
		_oldX = scan.nextInt();
		_oldY = scan.nextInt();
		
		System.out.println("Vers ?");
		
		setX(scan.nextInt());
		setY(scan.nextInt());
	}
	
	@Override
	public boolean doAction(Board board) {
		int x = getX(), y = getY();
		if (board.isEmpty(x, y) && !board.isEmpty(_oldX, _oldY) && (Math.abs(_oldX - x) == 1 || Math.abs(_oldY - y) == 1) && Math.abs(_oldX - x) != Math.abs(_oldY - y)) {
			Case c = board.getCase(_oldX, _oldY);
			if(c.getFirstPiece().getPlayer().equals(super.getPlayer())) {
				board.getCase(getX(), getY()).addPiece(new Piece(getPlayer(), 1));
				c.removePiece(c.getFirstPiece());
				return true;
			}
		}
		return false;
	}
}
