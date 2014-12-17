package framework.joueur;
import java.util.List;

import framework.action.Action;

public abstract class Player {
	private String name = "";
	private int couleur = 0;
	private int type = 0;
	private String symbol;
	private int score = 0;
	
	public Player(String name, int couleur, int type, String symbol, int score) {
		this.name = name;
		this.couleur = couleur;
		this.type = type;
		this.symbol = symbol;
		this.score = score;
	}
	
	public abstract Action getAction(List<Action> listActions);
	
	public String getName() {
		return name;
	}
	
	public String getSymbol() {
		return symbol;
	}
	
	public String toString() {
		return "Player: " + name + "\n";
	}
	public int getCouleur() {
		return couleur;
	}
	
	public void increaseScore(int i) {
		score += i;
	}
	public void decreaseScore(int i) {
		score -= i;
	}
	public void setScore(int s) {
		score = s;
	}
	public int getScore() {
		return score;
	}
	
	public int throwDice(){
		return (int) Math.round(Math.random()*6);	
		
	}
	
	
}
