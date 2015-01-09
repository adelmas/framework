package framework.game;
import framework.board.*;

import java.util.List;
import java.util.Observable;

import framework.player.Player;

public abstract class Game extends Observable {
	private Board _board;
	static List<Player> _players;
	private static int _currentPlayer = 0;
	private int _nbPlayers = 0;
	
	public abstract void init();
	public abstract boolean isGameOver();
	public abstract void gameOver();
	
	public void setBoard(Board b) {
		_board = b;
	}
	
	public Board getBoard() {
		return _board;
	}
	
	public void setPlayers(List<Player> players) {
		_players = players;
		_nbPlayers = players.size();
	}
	public List<Player> getPlayers() {
		return _players;
	}
	
	public abstract void play();
	
	public static Player getCurrentPlayer() {
		return _players.get(_currentPlayer);
	}
	public void nextPlayer() {
		_currentPlayer = (_currentPlayer + 1) % _nbPlayers;
	}
	public void prevPlayer() {
		if (_currentPlayer > 0) {
			_currentPlayer -= 1;
		}
		else if (_currentPlayer == 0) {
			_currentPlayer = _nbPlayers - 1;
		}
	}
	
	public void resetPlayers(int score) {
		_currentPlayer = 0;
		for (Player p : _players) {
			p.setScore(score);
		}
	}
	
	public String toString() {
		String str = "";
		
		return str + _board.toString();
	}

}
