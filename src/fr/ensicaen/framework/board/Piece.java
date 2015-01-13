package fr.ensicaen.framework.board;

import fr.ensicaen.framework.player.Player;

public class Piece {
	
	/* Attributes */
	
	private Player _player;
	private int _type;
	
	/* Constructor */
	
	public Piece(Player player, int type){
		_player = player;
		_type = type;
	}
	
	/* Method */

	public Player getPlayer(){
		return _player;
	}
	
	public int getType(){
		return _type;
	}
	
}