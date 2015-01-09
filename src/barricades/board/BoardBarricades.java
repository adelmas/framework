package barricades.board;

import java.util.HashMap;

import framework.board.*;

public class BoardBarricades extends Board {
	private Graph _graph;

	private HashMap<Integer, Coordinates> _map;
	
	public BoardBarricades() {
		_graph = new Graph();
		_map = new HashMap<Integer, Coordinates>();
		Case c = new Case();
		c.addPiece(new Piece(null, 1));
		_graph.addNode(new Node(c, new Coordinates(1, 1), true, 1));
		
		c = new Case();
		c.addPiece(new Piece(null, 2));
		_graph.addNode(new Node(c, new Coordinates(2, 2), true, 2));
		
		c = new Case();
		c.addPiece(new Piece(null, 3));
		Node n = new Node(c, new Coordinates(3, 3), true, 3);
		_graph.addNode(n);
		_graph.getNode(1).addChild(n);
		
		System.out.println(_graph.toString());
		
		addPoint(1, new Coordinates(200, 200));
		System.out.println(getMap().toString());
	}
	
	@Override
	public Case getCase(Coordinates coord) {
		if (coord.getSize() == 1) {
			return _graph.getNode(coord.getFirstCoordinate()).getCase();
		}
		else if (coord.getSize() == 2) {
			// TODO Convertir coord souris -> numéro sommet
		}
		return null;
	}
	
	public HashMap<Integer, Coordinates> getMap() {
		return _map;
	}
	public void setMap(HashMap<Integer, Coordinates> map) {
		_map = map;
	}
	public void resetMap() {
		_map = new HashMap<Integer, Coordinates>();
	}
	public void addPoint(int num, Coordinates coord) {
		_map.put(num, coord);
	}
	public Coordinates getCoordinates(int num) {
		if (_graph.getNode(1) != null) {
			return _graph.getNode(num).getCoordinates();
		}
		return null;
	}
	public int getNum(Coordinates coord) {
		for (int num : _map.keySet()) {
			if (_map.get(num).equals(coord)) {
				return num;
			}
		}
		return -1;
	}
	
	public void addNode(int num, Coordinates coord) {
		_graph.addNode(new Node(new Case(), coord, true, num));
	}
	
	@Override
	public int getHeight() {
		return -1;
	}

	@Override
	public int getWidth() {
		return _graph.getWidth();
	}

	@Override
	public void setCase(Case c, Coordinates coord) {
		_graph.getNode(coord.getFirstCoordinate()).setCase(c);
	}

	@Override
	public boolean isEmpty(Coordinates coord) {
		return _graph.isEmpty(coord.getFirstCoordinate());
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		// Recharger la map
	}

}
