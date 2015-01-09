package barricades.board;

import framework.board.*;

public class BoardBarricades extends Board {
	private Graph _graph;
	
	public BoardBarricades() {
		_graph = new Graph();
		Case c = new Case();
		//c.addPiece(new Piece(null, 1));
		_graph.addNode(new Node(c, new Coordinates(1, 1), true, 1));
		
		c = new Case();
		c.addPiece(new Piece(null, 2));
		_graph.addNode(new Node(c, new Coordinates(2, 2), true, 2));
		
		_graph.getNode(1).addChild(new Node(c, new Coordinates(3, 3), true, 3));
		
		
		
		System.out.println(_graph.toString());
		
	}
	
	@Override
	public Case getCase(Coordinates coord) {
		_graph.getNode(coord.getFirstCoordinate()).getCase();
		return null;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setCase(Case c, Coordinates coord) {
		_graph.getNode(coord.getFirstCoordinate()).setCase(c);
	}

	@Override
	public boolean isEmpty(Coordinates coord) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

}
