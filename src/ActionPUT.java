import joueur.Player;
import action.Action;
import board.Board;
import board.Piece;

public class ActionPUT extends Action {

	public ActionPUT(String action, int type, int x, int y, Player player) {
		super(action, type, x, y, player);
	}

	@Override
	public void doAction(Board board) {
		Board boardm = (BoardMorpion) board;
		if (boardm.isEmpty(getX(), getY()))
			boardm.getCase(getX(), getY()).addPiece(new Piece(getPlayer(), 1));
	}

}
