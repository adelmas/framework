import game.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import board.Board;

public class BoardMorpion extends Board {

	
	public BoardMorpion(int h, int w) {
		super(h, w);
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
				switch(getBoard()[i][j].getType()){
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
