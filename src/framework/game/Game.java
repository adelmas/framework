package framework.game;
import framework.board.*;
import framework.action.*;

import java.util.List;

import framework.joueur.Player;

public abstract class Game {
	private Board board;
	List<Player> players;
	private int currentPlayer = 0, nbPlayers = 0;
	
	public abstract void init();
	public abstract boolean isGameOver();
	
	public void setBoard(Board b) {
		board = b;
	}
	
	public Board getBoard() {
		return board;
	}
	
	public void setPlayers(List<Player> players) {
		this.players = players;
		nbPlayers = players.size();
	}
	
	public abstract void play();
	
	public Player getCurrentPlayer() {
		return players.get(currentPlayer);
	}
	public void nextPlayer() {
		currentPlayer = (currentPlayer + 1) % nbPlayers;
	}
	
	public String toString() {
		String str = "";
		
		return str + board.toString();
	}
}
