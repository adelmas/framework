package barricades.game;

import framework.board.Board;
import framework.board.Case;
import framework.board.Coordinates;
import framework.board.Piece;
import framework.game.*;
import barricades.board.*;

public class Barricades extends Game {
	
	
	
	@Override
	public void init() {
		BoardBarricades board = (BoardBarricades)getBoard();
		Graph g = new Graph();
		Case c = new Case();
		c.addPiece(new Piece(null, 1));
		g.addNode(new Node(c, new Coordinates(1, 1), true, 1));
		
		c = new Case();
		c.addPiece(new Piece(null, 2));
		g.addNode(new Node(c, new Coordinates(2, 2), true, 2));
		
		c = new Case();
		c.addPiece(new Piece(null, 3));
		Node n = new Node(c, new Coordinates(3, 3), true, 3);
		g.addNode(n);
		g.getNode(1).addChild(n);
		
		System.out.println(g.toString());
		
		board.addPoint(1, new Coordinates(200, 200));
		System.out.println(board.getMap().toString());
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
