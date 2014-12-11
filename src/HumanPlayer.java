import java.util.List;
import java.util.Scanner;

import game.Action;
import game.Player;


public class HumanPlayer extends Player {

	public HumanPlayer(String name, int couleur, int type, String symbol) {
		super(name, couleur, type, symbol);
	}

	@Override
	public Action getAction(List<Action> listActions) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Action ?");
		
		/* Coordonnees en entree */
		int x = scan.nextInt();
		int y = scan.nextInt();

		return new ActionPUT("PUT", 1, x, y,  getSymbol());
	}
	
}
