package framework.board;

public abstract class Board {
		
	public Board(){};
	
	public abstract Case getCase(Coordinates coord);
	
	public abstract int getHeight();
	
	public abstract int getWidth();
	
	public abstract void setCase(Case c, Coordinates coord);
	
	public abstract boolean isEmpty(Coordinates coord);	

}
