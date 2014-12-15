import action.Action;
import board.Board;
import board.*;
import joueur.*;
import game.*;

public class ActionPUT extends Action {

	public ActionPUT(String action, int type, int x, int y, Player player) {
		super(action, type, x, y, player);
	}

	@Override
	public void doAction(Board board) {
		if (board.isEmpty(getX(), getY()))
			board.getCase(getX(), getY()).addPiece(new Piece(getPlayer(), 1));
	}

}
