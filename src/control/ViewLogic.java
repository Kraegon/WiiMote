package control;

import gui.Frame;
import data.ColourButton;

public class ViewLogic {
	private Frame frame;
	private static ViewLogic INSTANCE;
	private int selectedIndx;
	
	public ViewLogic(){
		frame = new Frame();
	}
	
	public static ViewLogic getInstance(){
		if(INSTANCE == null)
			INSTANCE = new ViewLogic();
		return INSTANCE;
	}
	
	public Frame getFrame(){
		return frame;
	}
	public ColourButton getHighlighted(){
		return frame.getGamePanel().getCurrentlyHighlighted();
	}
	public void selectUpwards(){
		frame.getGamePanel().highlightButton(0);
	}
	public void selectDownwards(){
		frame.getGamePanel().highlightButton(1);
	}
	public void selectLeft(){
		frame.getGamePanel().highlightButton(2);
	}
	public void selectRight(){
		frame.getGamePanel().highlightButton(3);
	}
	public void unhighlightAll(){
		frame.getGamePanel().unhighlightAll();
	}
}
