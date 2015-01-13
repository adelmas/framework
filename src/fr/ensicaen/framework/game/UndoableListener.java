package fr.ensicaen.framework.game;

import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;

public class UndoableListener implements UndoableEditListener {
	private UndoManager _undoManager;
	
	public UndoableListener(UndoManager manager) {
		_undoManager = manager;
	}
	
	public void undoableEditHappened(UndoableEditEvent evt) {
		_undoManager.addEdit(evt.getEdit());
	}

}
