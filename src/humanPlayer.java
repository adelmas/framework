import java.util.List;
import java.util.Scanner;

import game.Action;
import game.Player;


public class humanPlayer extends Player {

	public humanPlayer(String name, int couleur, int type, char symbol) {
		super(name, couleur, type, symbol);
	}

	@Override
	public Action getAction(List<Action> listActions) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Action ?");
		
		/* Coordonnées en entrée */
		int x = scan.nextInt();
		int y = scan.nextInt();

		return new ActionPUT("PUT", 1, x, y, symbol);
	}
	
}
