package wali;

import framework.joueur.Player;
import framework.action.Action;
import framework.board.Board;
import framework.board.Case;
import framework.board.Piece;

public class ActionMOVE extends Action {
	
	// Attributes
	private int oldX = 0, oldY = 0;
	
	public ActionMOVE(String action, int type, int curX, int curY, int x, int y, Player player) {
		super(action, type, x, y, player);
		oldX = curX;
		oldY = curY;
	}

	@Override
	public void doAction(Board board) {
		if (board.isEmpty(getX(), getY()) && !board.isEmpty(oldX, oldY))
		{
			Case c = board.getCase(oldX, oldY);
			if(c.getFirstPiece().getPlayer().equals(super.getPlayer()))
			{
			board.getCase(getX(), getY()).addPiece(new Piece(getPlayer(), 1));
	
			c.removePiece(c.getFirstPiece());
			}
		}
	}
}
