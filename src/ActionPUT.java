import game.*;

public class ActionPUT extends Action {

	public ActionPUT(String action, int type, int x, int y, char symbol) {
		super(action, type, x, y, symbol);
	}

	@Override
	public void doAction(Board board) {
		if (board.isEmpty(x, y))
			board.setCase(symbol, x, y);
	}

}
