package board;

public abstract class Board {
		
	public Board(){};
	
	public abstract Case[][] getBoard();
	
	public abstract Case getCase(int h, int w);
	
	public abstract int getHeight();
	
	public abstract int getWidth();
	
	public abstract void setCase(Case c, int x, int y);
	
	public abstract boolean isEmpty(int x, int y);	

}
