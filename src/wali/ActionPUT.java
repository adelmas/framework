package wali;

import framework.joueur.Player;
import framework.action.Action;
import framework.board.Board;
import framework.board.Piece;

public class ActionPUT extends Action {

	public ActionPUT(String action, int type, int x, int y, Player player) {
		super(action, type, x, y, player);
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
