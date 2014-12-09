package game;

public abstract class Player {
	protected String name = "";
	protected int couleur = 0;
	protected int type = 0;
	
	public Player(String name, int couleur, int type) {
		this.name = name;
		this.couleur = couleur;
		this.type = type;
	}
	
	public abstract void think();
	
	public String getName() {
		return name;
	}
	
}
