package action;

import board.Board;
import joueur.Player;

public abstract class Action {
	private String _action = "";
	private int _type = 0, _x = 0, _y = 0;
	private Player _player;
	
	public Action(String action, int type, int x, int y, Player player) {
		_action = action;
		_type = type;
		_x = x;
		_y = y;
		_player = player;
	}
	
	public abstract void doAction(Board board);
	
	public int getX() {
		return _x;
	}
	public int getY() {
		return _y;
	}
	public Player getPlayer() {
		return _player;
	}
	
	public String toString() {
		return "Action() " + _action + "(" + _type + ") " + _x + ", " + _y + "\n";
	}
}
