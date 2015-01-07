package barricades.board;

import framework.board.*;

public class BoardBarricades extends Board {
	Graph _graph;
	
	public BoardBarricades() {
		_graph = new Graph();
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

}
