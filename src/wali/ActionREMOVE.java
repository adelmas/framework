package wali;

import framework.joueur.Player;

import framework.action.Action;
import framework.board.Board;
import framework.board.Case;
import framework.board.Piece;

public class ActionREMOVE extends Action {
	
	public ActionREMOVE(String action, int type, int x, int y, Player player) {
		super(action, type, x, y, player);
	}

	@Override
	public boolean doAction(Board board) {
		if (!board.isEmpty(getX(), getY()) && board.getCase(getX(), getY()).getFirstPiece().getPlayer() != getPlayer()) {
			Case c = board.getCase(getX(), getY());
			c.removePiece(c.getFirstPiece());
			return true;
		}
		return false;
	}
}
