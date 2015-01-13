package wali.graphics;

import framework.game.Game;
import framework.graphics.Frame;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.util.LinkedList;

import javax.swing.*;

public class FrameWali extends Frame {

	private JPanel _container; 
	private PanelWali _pWali;
	private JButton _newGame;
	private JButton _undo;
	private JButton _redo;
	private JButton _giveup;
	
	public FrameWali(Game g) {
		super();
		_container = new JPanel();
		setTitle("Test Wali");
		setResizable(false);
		setSize(540,514);
		setLocationRelativeTo(null);
		_pWali = new PanelWali();
		_pWali.init(g);
		initFrame();
		setVisible(true);
	}
	
	public void initFrame(){
		_container.setLayout(new BorderLayout());

		_container.add(_pWali, BorderLayout.CENTER);
		JPanel buttons = new JPanel();
		GridLayout gl = new GridLayout(1,4);
		buttons.setLayout(gl);
		gl.setHgap(5);
		
		_newGame = new JButton("New game");
		_undo = new JButton("Undo");
		_redo = new JButton("Redo");
		_giveup = new JButton("Give up");
		buttons.add(_newGame);
		buttons.add(_undo);
		buttons.add(_redo);
		buttons.add(_giveup);
		_container.add(buttons,BorderLayout.SOUTH);
		this.setContentPane(_container);
	}
	
	public PanelWali getPanel(){
		return _pWali;
	}
	
	public LinkedList<JButton> getButtons(){
		LinkedList<JButton> list = new LinkedList<JButton>();
		list.add(_newGame);
		list.add(_undo);
		list.add(_redo);
		list.add(_giveup);
		return list;
	}
	
}