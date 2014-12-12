package action;

import board.Board;

public abstract class Action {
	private String action = "";
	private int type = 0, x = 0, y = 0;
	private String symbol;
	
	public Action(String action, int type, int x, int y, String symbol) {
		this.action = action;
		this.type = type;
		this.x = x;
		this.y = y;
		this.symbol = symbol;
	}
	
	public abstract void doAction(Board board);
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public String getSymbol() {
		return symbol;
	}
	
	public String toString() {
		return "Action() " + action + "(" + type + ") " + x + ", " + y + "\n";
	}
}
