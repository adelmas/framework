package wali;

import java.util.List;
import java.util.Scanner;

import framework.player.Action;
import framework.player.Player;


public class HumanPlayer extends Player {

	public HumanPlayer(String name, int couleur, int type, String symbol, int score) {
		super(name, couleur, type, symbol, score, new Scanner(System.in));
	}

	@Override
	public Action getAction(List<Action> listActions) {
		String strActions = "";
		
		for (int i=0; i<listActions.size(); i++)
			strActions += listActions.get(i) + " ";
		
		Action action = listActions.get(0);
		action.getParameters();

		return action;
	}
	
	
}
