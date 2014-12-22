package wali.graphics;

import framework.game.Game;
import framework.graphics.Panel;

import javax.imageio.ImageIO;
import javax.swing.*;

import framework.board.*;

import java.awt.*;
import java.io.*;
import java.util.Observable;
import java.util.Observer;

public class PanelWali extends Panel {
	Board _board = null;
	
	PanelWali(){
		super();
	}
	
	void init(Game g) {
		g.addObserver(this);
		_board = g.getBoard();
	}
	
	public void paintComponent(Graphics g){	
		try {
			Image grid = ImageIO.read(new File("resources/grid.jpg"));
			Image stone = ImageIO.read(new File("resources/stone.png"));
			Image stick = ImageIO.read(new File("resources/stick.png"));
			g.drawImage(grid, 0,0,this);
			
			if (_board != null) {
				for(int i=0;i<5;i++){
					for(int j=0;j<6;j++){
						Case c = _board.getCase(new Coordinates(i, j));
						if(!c.isEmpty() && c.getFirstPiece().getPlayer().getColor() == 1)
							g.drawImage(stone,26+j*81,30+i*81,this);
						else if(!c.isEmpty() && c.getFirstPiece().getPlayer().getColor() == 2)
							g.drawImage(stick,26+j*81,30+i*81,this);
					}
				}
			}
		} catch (IOException e) {
			System.out.println("Erreur chargement image");
		}
		
	}

	public void update(Observable arg0, Object arg1) {
		repaint();
		System.out.println("update()");
	}

	/*@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getY(), y = e.getX(),x_mat,y_mat;
		int taille = 83, decal = 26;
		if((e.getX() < decal) || (e.getX() > decal + 6*taille) || (e.getY() < decal) || (e.getY() > decal+6*taille))
			System.out.println("hors limites");
		else{
			x_mat = (x-decal)/taille;
			y_mat = (y-decal)/taille;
			_board.setCase(new Case("stone"),x_mat,y_mat);
			System.out.println("x = "+x_mat+"\ty = "+y_mat);
			
		}
		this.validate();
		this.repaint();
	}*/


	
}
