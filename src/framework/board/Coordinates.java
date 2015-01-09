package framework.board;

import java.util.LinkedList;
import java.util.List;

public class Coordinates {
	private List<Integer> _coord;
	
	public Coordinates(Integer ... coord) {
		_coord = new LinkedList<Integer>();
		for (Integer i : coord) {
			_coord.add(i);
		}
	}
	
	public Integer getCoordinate(int i) {
		if (i < _coord.size()) {
			return _coord.get(i);
		}
		return -1;
	}
	
	public int getSize() {
		return _coord.size();
	}
	
	public Integer getFirstCoordinate() {
		if (!_coord.isEmpty()) {
			return _coord.get(0);
		}
		return -1;
	}
	
	public Integer getSecondCoordinate() {
		if (!_coord.isEmpty()) {
			return _coord.get(1);
		}
		return -1;
	}
	
	public void setCoordinate(int i, Integer val) {
		_coord.set(i, val);
	}
	
	public String toString() {
		return _coord.toString();
	}

}
