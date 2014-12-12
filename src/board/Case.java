package board;

import java.util.LinkedList;
import java.util.List;

public class Case {

	/* Attributes */
	
	private String _typeCase;
	private List<Piece> _Pieces;
	
	/* Constructor */
	
	public Case(){
		_Pieces = new LinkedList<Piece>();
		_typeCase = "vide";
	}
	
	public Case(String type){
		_Pieces = new LinkedList<Piece>();
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
		return _Pieces;
	}
	
	public void removePiece(Piece P){
		_Pieces.remove(P);
	}

	public void addPiece(Piece P){
		_Pieces.add(P);
	}
	
	public boolean isEmpty(){
		return _Pieces.isEmpty();
	}
}
