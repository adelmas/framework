package fr.ensicaen.framework.graphics;

import javax.swing.*;

public abstract class Frame extends JFrame {
	
	public Frame(){
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public Frame(int width, int height){
		this.setSize(width,height);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
}
