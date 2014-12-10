package game;
import java.util.List;

public abstract class Player {
	private String name = "";
	private int couleur = 0;
	private int type = 0;
	private char symbol;
	
	public Player(String name, int couleur, int type, char symbol) {
		this.name = name;
		this.couleur = couleur;
		this.type = type;
		this.symbol = symbol;
	}
	
	public abstract Action getAction(List<Action> listActions);
	
	public String getName() {
		return name;
	}
	
	public char getSymbol() {
		return symbol;
	}
	
	public String toString() {
		return "Player: " + name + "\n";
	}
	
}
