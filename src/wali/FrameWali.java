package wali;

import framework.game.Game;
import framework.graphics.Frame;

import java.awt.GridLayout;
import java.awt.BorderLayout;

import javax.swing.*;

public class FrameWali extends Frame {

	private JPanel _container; 
	private PanelWali pWali;
	private JButton new_game;
	private JButton undo;
	private JButton redo;
	
	FrameWali(Game g) {
		super();
		_container = new JPanel();
		setTitle("Test Wali");
		setResizable(false);
		setSize(540,514);
		setLocationRelativeTo(null);
		pWali = new PanelWali();
		pWali.init(g);
		initFrame();
		setVisible(true);
	}
	
	public void initFrame(){
		_container.setLayout(new BorderLayout());

		_container.add(pWali, BorderLayout.CENTER);
		JPanel buttons = new JPanel();
		GridLayout gl = new GridLayout(1,3);
		buttons.setLayout(gl);
		gl.setHgap(10);
		
		new_game = new JButton("New game");
		undo = new JButton("Undo");
		redo = new JButton("Redo");
		buttons.add(new_game);
		buttons.add(undo);
		buttons.add(redo);
		_container.add(buttons,BorderLayout.SOUTH);
		this.setContentPane(_container);
	}
}