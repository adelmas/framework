package framework.joueur;
import java.util.List;

import framework.action.Action;

public abstract class Player {
	private String _name = "";
	private int _color = 0;
	private int _type = 0;
	private String _symbole;
	private int _score = 0;
	
	public Player(String name, int color, int type, String symbol, int score) {
		_name = name;
		_color = color;
		_type = type;
		_symbole = symbol;
		_score = score;
	}
	
	public abstract Action getAction(List<Action> listActions);
	
	public String getName() {
		return _name;
	}
	
	public String getSymbol() {
		return _symbole;
	}
	
	public String toString() {
		return "Player: " + _name + "\n";
	}
	public int getColor() {
		return _color;
	}
	
	public void increaseScore(int i) {
		_score += i;
	}
	public void decreaseScore(int i) {
		_score -= i;
	}
	public void setScore(int s) {
		_score = s;
	}
	public int getScore() {
		return _score;
	}
	
	public int throwDice(){
		return (int) Math.round(Math.random()*6);	
		
	}
	
	
}
