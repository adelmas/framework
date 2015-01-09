package barricades.game;

import framework.board.Board;
import framework.game.*;
import barricades.board.*;

public class Barricades extends Game {
	
	
	
	@Override
	public void init() {
		BoardBarricades board = (BoardBarricades)getBoard();
		System.out.println(board.getCoordinates(3));
	}

	@Override
	public boolean isGameOver() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void play() {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		Game g = new Barricades();
		Board b = new BoardBarricades();
		g.setBoard(b);
		
		g.init();
		
		
	}

	@Override
	public void gameOver() {
		// TODO Auto-generated method stub
		
	}

}
