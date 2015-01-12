package barricades.graphics;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JPanel;

import wali.graphics.PanelWali;
import barricades.game.PanelBarricades;
import framework.game.Game;
import framework.graphics.*;

public class FrameBarricades extends Frame{
	private JPanel _container;
	private PanelBarricades _panelBarricades;
	private JButton new_game;
	private JButton undo;
	private JButton redo;
	
	public FrameBarricades(Game g) {
		super();
		_container = new JPanel();
		setTitle("Test Wali");
		setResizable(false);
		setSize(255,305);
		setLocationRelativeTo(null);
		_panelBarricades = new PanelBarricades();
		initFrame();
		setVisible(true);		
	}
	
	public void initFrame(){
		_container.setLayout(new BorderLayout());
		_container.add(_panelBarricades,BorderLayout.CENTER);
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
	
	public PanelBarricades getPanel() {
		return _panelBarricades;
	}
	
	public LinkedList<JButton> getButtons(){
		LinkedList<JButton> list = new LinkedList<JButton>();
		list.add(new_game);
		list.add(undo);
		list.add(redo);
		return list;
	}
}
