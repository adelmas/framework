import game.*;
import board.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class BoardMorpion extends Board {
	
	private int _height,_width;
	private Case[][] _board;
	
	public BoardMorpion(int h, int w) {
		super();
		_height = h;
		_width = w;
		_board = new Case[_height][_width];
		
		for(int i = 0 ; i < _height ; i++){
			for(int j = 0 ; j < _width ; j++){
				_board[i][j] = new Case("vide");
			}
		}
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
	
	public Case getCase(int h, int w){
		return _board[h][w];
	}
	
	public Case[][] getBoard() {
		return _board;
	}
	
	public int getWidth(){
		return _width;
	}
	
	public int getHeight(){
		return _height;
	}
	
	public boolean isEmpty(int x, int y) {
		try {
			return (getBoard()[x][y].isEmpty());
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}
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
						getBoard()[i/getHeight()][i%getWidth()].setType("O");
						i++;
						break;
					case 'X':
						getBoard()[i/getHeight()][i%getWidth()].setType("X");
						i++;
						break;
					case '.':
						getBoard()[i/getHeight()][i%getWidth()].setType("vide");
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
		
		for (int i = 0 ; i < getHeight() ; i++ ){
			for (int j = 0 ; j < getWidth() ; j++){
				Case c = getCase(i, j);
				if (c.isEmpty())
					str += " ";
				else if (c.getFirstPiece().getPlayer().getCouleur() == 1)
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
