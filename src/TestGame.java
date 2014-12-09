import game.*;

public class TestGame extends Game {
	public void init() {
		System.out.println("init()");
	}
	
	public void play() {
		System.out.println("play()");
	}
	
	public boolean isGameOver() {
		System.out.println("isGameOver()");
		return false;
	}

	public static void main(String[] args) {
		Game g = new TestGame();
		g.init();
		g.setBoard(new Board(5, 5));
		
		System.out.println(g.toString());
		
		g.isGameOver();
	}

}
