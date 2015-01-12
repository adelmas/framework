package framework.graphics;

import javax.swing.*;

import java.awt.Graphics;
import java.util.Observer;

public abstract class Panel extends JPanel implements Observer {
	
	public abstract void paintComponent(Graphics g);
	
}
