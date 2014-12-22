package framework.player;
import java.util.Scanner;

import javax.swing.undo.AbstractUndoableEdit;

import framework.board.Board;
import framework.board.Coordinates;

public abstract class Action extends AbstractUndoableEdit {
	private String _action = "";
	private int _type = 0;
	private Player _player;
	private Scanner _scanner;
	private Board _board;
	private Coordinates _coord;
	
	public Action(String action, int type, Player player, Board board, Scanner scan) {
		_action = action;
		_type = type;
		_player = player;
		_scanner = scan;
		_board = board;
		_coord = new Coordinates();
	}
	
	public abstract void undo();
	public abstract void redo();
	
	public boolean canUndo() { return true; }
	public boolean canRedo() { return true; }
	
	public Board getBoard() {
		return _board;
	}
	public Scanner getScanner()
	{
		return _scanner;
	}
	
	public Integer getCoordinate(int i)
	{
		return _coord.getCoordinate(i);
	}
	
	public String getAction()
	{
		return _action;
	}
	
	public Player getPlayer() 
	{
		return _player;
	}

	public void setCoordinates(Coordinates coord) {
		_coord = coord;
	}
	
	public void setAction(String action)
	{
		_action = action;
	}
	
	public void setPlayer(Player player)
	{
		_player = player;
	}
	
	public abstract boolean doAction();
	
	public void give_up()
	{
		_action = "GIVE_UP";
	}

	public void skip()
	{
		_action = "SKIP";
	}	
	
	public abstract void getParameters();
	
	public String toString() {
		return "Action() " + _action + "(" + _type + ") " + _coord.toString() + "\n";
	}
}