import action.Action;
import board.Board;
import board.Case;
import game.*;

public class ActionPUT extends Action {

	public ActionPUT(String action, int type, int x, int y, String symbol) {
		super(action, type, x, y, symbol);
	}

	@Override
	public void doAction(Board board) {
		if (board.isEmpty(getX(), getY()))
			board.setCase(new Case(getSymbol()), getX(), getY());
	}

}
