package wali.board;

import framework.board.*;

public class BoardWali extends Board {
	
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
	
	public Case getCase(Coordinates coord){
		return _board[coord.getCoordinate(0)][coord.getCoordinate(1)];
	}	
	
	public int getHeight(){
		return _height;
	}
	
	public int getWidth(){
		return _width;
	}
	
	public void setCase(Case c, Coordinates coord) {
		try 
		{
			_board[coord.getCoordinate(0)][coord.getCoordinate(1)] = c;
		} 
		catch (ArrayIndexOutOfBoundsException e) 
		{
			System.out.println("error : position out of grid");
		}
	}
	
	public boolean isEmpty(Coordinates coord) {
		try {
			return (_board[coord.getCoordinate(0)][coord.getCoordinate(1)].isEmpty());
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}
	}
	
	public String toString(){

		String str = "";
		
		for (int i = 0 ; i < getHeight() ; i++ ){
			for (int j = 0 ; j < getWidth() ; j++){
				Case c = getCase(new Coordinates(i, j));
				if (c.isEmpty())
					str += " ";
				else if (c.getFirstPiece().getPlayer().getColor() == 1)
					str += "X";
				else
					str += "O";
				
				str += "|";
			}
			str += "\n";
		}
		return str;
	}

}
