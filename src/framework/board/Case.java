package framework.board;

import java.util.LinkedList;
import java.util.List;

public class Case {

	/* Attributes */
	
	private String _typeCase;
	private List<Piece> _pieces;
	
	/* Constructor */
	
	public Case() {
		_pieces = new LinkedList<Piece>();
		_typeCase = "vide";
	}
	
	public Case(String type){
		_pieces = new LinkedList<Piece>();
		_typeCase = type;
	}
	
	/* Method */
	
	public String getType(){
		return _typeCase;
	}
	
	public void setType(String typ){
		_typeCase = typ;
	}
	
	public List<Piece> getPieces(){
		return _pieces;
	}
	
	public Piece getFirstPiece() {
		return _pieces.get(0);
	}
	
	public void removePiece(Piece P){
		_pieces.remove(P);
	}
	
	public void removePieces() {
		_pieces = new LinkedList<Piece>();
	}

	public void addPiece(Piece P){
		_pieces.add(P);
	}
	
	public boolean isEmpty(){
		return _pieces.isEmpty();
	}
}