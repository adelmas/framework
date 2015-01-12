package barricades.board;

import java.util.LinkedList;

public class Graph {
	private LinkedList<Node> _list;
	
	public Graph() {
		_list = new LinkedList<Node>();
	}
	
	public LinkedList<Node> getList() {
		return _list;
	}
	
	public void addNode(Node n) {
		_list.add(n);
	}
	
	public Node getNode(int num) {
		for (Node n : _list) {
			if (n.getNum() == num)
				return n;
		}
		return null;
	}
	
	public boolean isEmpty(int num) {
		if (getNode(num) == null) {
			return true;
		}
		return false;
	}
	
	public int getWidth() {
		return _list.size();
	}
	
	public String toString() {
		return _list.toString();
	}
}
