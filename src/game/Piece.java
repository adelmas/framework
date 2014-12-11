package game;

import game.Player;

public class Piece {
	
	/* Attributes */
	
	private Player _player;
	private String _typePiece;
	
	/* Constructor */
	
	public Piece(Player player, String type){
		_player = player;
		_typePiece = type;
	}
	
	/* Method */

	public Player getPlayer(){
		return _player;
	}
	
	public String getTypePiece(){
		return _typePiece;
	}
	
}
