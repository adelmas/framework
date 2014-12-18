package wali;

import java.util.List;
import java.util.Scanner;

import framework.action.Action;
import framework.joueur.Player;


public class HumanPlayer extends Player {

	public HumanPlayer(String name, int couleur, int type, String symbol, int score) {
		super(name, couleur, type, symbol, score);
	}

	@Override
	public Action getAction(List<Action> listActions) {
		Scanner scan = new Scanner(System.in);
		String strActions = "";
		
		for (int i=0; i<listActions.size(); i++)
			strActions += listActions.get(i) + " ";
		
		System.out.println("Action ? " + strActions);
		
		/* Coordonnees en entree */
		int x = scan.nextInt();
		int y = scan.nextInt();
		
		Action action = listActions.get(0);
		if (action instanceof ActionPUT)
			action = new ActionPUT("PUT", 1, x, y, this);
		else if (action instanceof ActionMOVE) {
			System.out.println("Vers ?");
			int to_x = scan.nextInt();
			int to_y = scan.nextInt();
			action = new ActionMOVE("MOVE", 0, x, y, to_x, to_y, this);
		}
		else if (action instanceof ActionREMOVE)
			action = new ActionREMOVE("REMOVE", 0, x, y, this);

		return action;
	}
	
}
