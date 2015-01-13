package barricades.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.Observable;

import javax.imageio.ImageIO;

import barricades.board.BoardBarricades;
import barricades.board.Node;
import framework.board.Board;
import framework.board.Coordinates;
import framework.game.Game;
import framework.graphics.*;

public class PanelBarricades extends Panel{
	private BoardBarricades _board = null;
	
	public PanelBarricades() {
		super();
	}
	
	void init(Game g){
		g.addObserver(this);
		_board = (BoardBarricades) g.getBoard();
	}

	public void paintComponent(Graphics g) {
		try {
			Color plr1 = Color.black;
			Color plr2 = Color.red;
			int x,y,width = 5, height = 5;
			
			Image map = ImageIO.read(new File("resources/moissy2.jpg"));
			g.drawImage(map,0,0,this);
			
			if(_board != null){
				
				/*g.setColor(plr1);	
			    g.fillRect(50,105,width,height);
			    g.setColor(plr2);
			    g.fillRect(200, 30, width, height);*/
			    
			    for (Node n : _board.getGraph().getList()) {
			    	//int id = n.getCase().getFirstPiece().getPlayer().getColor();
			    	int id = 0;
			    	if (!n.getCase().isEmpty()) {
			    		id = n.getCase().getFirstPiece().getPlayer().getColor();
			    	}
			    	Coordinates coord = n.getCoordinates();
		    		x = coord.getFirstCoordinate();
		    		y = coord.getSecondCoordinate();
		    		
			    	if(id == 1){
			    		g.setColor(plr1);
			    		g.fillRect(x,y,width,height);
			    	}
			    	else if (id == 2){
			      		g.setColor(plr2);
			    		g.fillRect(x,y,width,height);
			    	}
			    }
			}
		}
		catch(IOException e){ System.out.println("Erreur chargement image"); }
	}

	public void update(Observable arg0, Object arg1) {
		repaint();
	}
}
