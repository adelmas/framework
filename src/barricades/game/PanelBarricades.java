package barricades.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.Observable;

import javax.imageio.ImageIO;

import barricades.board.Node;
import framework.board.Board;
import framework.game.Game;
import framework.graphics.*;

public class PanelBarricades extends Panel{
	private Board _board = null;
	
	public PanelBarricades() {
		super();
	}
	
	void init(Game g){
		g.addObserver(this);
		_board = g.getBoard();
	}
	
	
	

	public void paintComponent(Graphics g) {
		try {
			Color plr1 = Color.cyan;
			Color plr2 = Color.red;
			
			Image map = ImageIO.read(new File("resources/moissy2.jpg"));
			g.drawImage(map,0,0,this);
			
			///if(_board != null){
				int width = 5, height = 5;
				g.setColor(plr1);	
			    g.fillRect(50,105,width,height);
			    g.setColor(plr2);
			    g.fillRect(200, 30, width, height);
			    
			    for (Node n : _board.getGraph()) {
			    	int id = n.getCase().getFirstPiece().getPlayer().getColor();
			    	
			    	
			    }
			//}
		}
		catch(IOException e){ System.out.println("Erreur chargement image"); }
	}

	public void update(Observable arg0, Object arg1) {
		repaint();
	}
}
