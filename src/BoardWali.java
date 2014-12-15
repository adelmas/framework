import board.*;

public class BoardWali extends Board{
	
	/* Attributes */
	
	private Case[][] _board;
	private int _width;
	private int _height;
	
	/* Constructor */
	
	public BoardWali(){
		super();
		_width = 6;
		_height = 5;
		_board = new Case[_height][_width];
		
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
}
