package fr.ensicaen.wali.player;

import java.util.List;
import java.util.Scanner;

import fr.ensicaen.framework.player.Action;
import fr.ensicaen.framework.player.Player;


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
		if(action.getScanner() != null){
			action.getParameters();
		}
		
		return action;
	}
	
	
}
