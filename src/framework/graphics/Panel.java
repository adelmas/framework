package framework.graphics;

import javax.swing.*;

import framework.game.Game;

import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

public abstract class Panel extends JPanel implements Observer {
	
	public abstract void init(Game g);
	public abstract void paintComponent(Graphics g);
	public abstract void drawGameOverBox(Game g);
	public abstract void update(Observable arg0, Object arg1);
	
}
