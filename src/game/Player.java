package game;
import java.util.List;

public abstract class Player {
	protected String name = "";
	protected int couleur = 0;
	protected int type = 0;
	protected char symbol;
	
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
	
	public String toString() {
		return "Player: " + name + "\n";
	}
	
}
