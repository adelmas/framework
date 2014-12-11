package game;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Board {
	
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
	
	public boolean isEmpty(int x, int y){
		return (_board[x][y].isEmpty());	
	}	
	
	public void initBoard(String fich){
		
		File file = new File(fich);
		FileInputStream fis = null;
		
		try 
		{
			fis = new FileInputStream(file);
			
			System.out.println(fis.available() + " bytes sont disponibles pour la copie");
			
			int content;			
			int i = 0;
			while ((content = fis.read()) != -1) {
				switch(content){
					case 'O':
						_board[i/_width][i%_width].setType("O");
						i++;
						break;
					case 'X':
						_board[i/_width][i%_width].setType("X");
						i++;
						break;
					case '.':
						_board[i/_width][i%_width].setType("vide");
						i++;
						break;	
				}
			}
		} 
		catch (FileNotFoundException e) {e.printStackTrace();} 
		catch (IOException e) {e.printStackTrace();}
		try {fis.close();} catch (IOException e) {e.printStackTrace();}
	}
	
	public String toString(){

		String str = "";
		
		for (int i = 0 ; i < _height ; i++ ){
			for (int j = 0 ; j < _width ; j++){
				switch(_board[i][j].getType()){
					case "O":
						str += "O";
					break;
					case "X":
						str += "X";
					break;
					case "vide":
						str += " ";
					break;
				}
				str += "|";
			}
			str += "\n";
		}
		return str;
	}
}
