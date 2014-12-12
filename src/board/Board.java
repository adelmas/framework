package board;


public abstract class Board {
	
	/* Attributes */
	
	private Case[][] _board;
	private int _width;
	private int _height;
	
	/* Constructor */
	
	public Board(int h,int w){
		_width = w;
		_height = h;
		_board = new Case[h][w];
		
		for(int i = 0 ; i < _height ; i++){
			for(int j = 0 ; j < _width ; j++){
				_board[i][j] = new Case("vide");
			}
		}
	}
	
	/* Method */
	
	public Case[][] getBoard() {
		return _board;
	}
	
	public Case getCase(int h, int w){
		return _board[h][w];
	}	
	
	public int getHeight(){
		return _height;
	}
	
	public int getWidth(){
		return _width;
	}
	
	public void setCase(Case c, int x, int y) {
		try 
		{
			_board[x][y] = c;
		} 
		catch (ArrayIndexOutOfBoundsException e) 
		{
			System.out.println("error : position out of grid");
		}
	}
	
	public boolean isEmpty(int x, int y) {
		try {
			return (_board[x][y].isEmpty());
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}
	}	
	
	public abstract void initBoard(String fich);
	
	public abstract String toString();
	
}
