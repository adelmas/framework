package barricades.board;

import framework.board.Case;
import framework.board.Coordinates;
import java.util.LinkedList;

public class Node {
	private String _name = "";
	private LinkedList<Node> _children = new LinkedList<Node>();
	private Case _case;
	private boolean _neighbor;
	private int _num = 0;
	private Coordinates _coord;
	
	public Node(Case c, Coordinates coord, boolean neighbor, int num) {
		_coord = coord;
		_case = c;
		_neighbor = neighbor;
		_num = num;
	}
	
	public void addChild(Node n) {
		_children.add(n);
	}
	
	public LinkedList<Node> getChildrenList() {
		if (hasChildren()) {
			return _children;
		}
		return null;
	}
	
	public boolean hasChildren() {
		return !_children.isEmpty();
	}
	
	public Coordinates getCoordinates() {
		return _coord;
	}
	
	public boolean isNeighbor() {
		return _neighbor;
	}
	public void setNeighbor(boolean b) {
		_neighbor = b;
	}
	
	public String getName() {
		return _name;
	}
	
	public int getNum() {
		return _num;
	}
	
	public Case getCase() {
		return _case;
	}
	public void setCase(Case c) {
		_case = c;
	}
	
	public String toString() {
		String str = "";
		
		str = "Node " + _num + " " +  _coord.toString() + "[" + _neighbor + "]\n";
		for (Node n : _children)
			str += "\t" + n.getNum() + "\n";
		return str;
	}
}
