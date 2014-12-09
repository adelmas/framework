package game;

public abstract class Action {
	protected String action = "";
	protected int type = 0, x = 0, y = 0;
	protected char symbol;
	
	public Action(String action, int type, int x, int y, char symbol) {
		this.action = action;
		this.type = type;
		this.x = x;
		this.y = y;
		this.symbol = symbol;
	}
	
	public abstract void doAction(Board board);
	
	public String toString() {
		return "Action() " + action + "(" + type + ") " + x + ", " + y + "\n";
	}
}
