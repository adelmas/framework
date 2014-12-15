package joueur;
import java.util.List;

import action.Action;

public abstract class Player {
	private String name = "";
	private int couleur = 0;
	private int type = 0;
	private String symbol;
	
	public Player(String name, int couleur, int type, String symbol) {
		this.name = name;
		this.couleur = couleur;
		this.type = type;
		this.symbol = symbol;
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
	
	
	public int throwDice(){
		return (int) Math.round(Math.random()*6);	
		
	}
	
	
}
