package framework.action;
import framework.board.Board;
import framework.joueur.Player;

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
	
	public int getX()
	{
		return _x;
	}
	
	public int getY()
	{
		return _y;
	}
	
	public String getAction()
	{
		return _action;
	}
	
	public Player getPlayer() 
	{
		return _player;
	}
	
	public void setX(int x)
	{
		_x = x;
	}
	
	public void setY(int y)
	{
		_y = y;
	}
	
	public void setAction(String action)
	{
		_action = action;
	}
	
	public void setPlayer(Player player)
	{
		_player = player;
	}
	
	public abstract void doAction(Board board);
	
	public void give_up()
	{
		_action = "GIVE_UP";
	}

	public void skip()
	{
		_action = "SKIP";
	}	
	
	public String toString() {
		return "Action() " + _action + "(" + _type + ") " + _x + ", " + _y + "\n";
	}
}