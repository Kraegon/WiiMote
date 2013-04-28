package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

import control.ViewLogic;

import data.ColourButton;

public class GamePanel extends JPanel{
	private ArrayList<ColourButton> buttons;
	private Point2D gameLocation;
	
	public GamePanel(){
		gameLocation = new Point2D.Double(250, 250);
		int spacing = 50;
		int size = 200;
	
		buttons = new ArrayList<ColourButton>();
		buttons.add(new ColourButton(Color.RED, gameLocation, size, new int[]{1,1}));
		buttons.add(new ColourButton(Color.BLUE, new Point2D.Double(gameLocation.getX() + spacing, gameLocation.getY() + spacing),size,  new int[]{-1,-1}));
		buttons.add(new ColourButton(Color.YELLOW, new Point2D.Double(gameLocation.getX(), gameLocation.getY() + spacing), size, new int[]{1,-1}));
		buttons.add(new ColourButton(Color.GREEN, new Point2D.Double(gameLocation.getX() + spacing, gameLocation.getY()), size, new int[]{-1,1}));
		Timer t = new Timer(1000/60, new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				repaint();
			}
		});
		t.start();
	}
	
	public void paintComponent(Graphics gTemp){
		super.paintComponent(gTemp);
		Graphics2D g = (Graphics2D) gTemp;
		g.rotate(Math.toRadians(45), gameLocation.getX(), gameLocation.getY());
		for(ColourButton b : buttons){
			if(b.isHighlighted())
				b.drawHighlighted(g);
			else
				b.draw(g);
		}
	}
	
	public void highlightButton(int index){
		for(ColourButton b : buttons){
			if(b.isHighlighted()){
				b.toggleHighlight();
			}
		}
		buttons.get(index).toggleHighlight();
	}
	public void unhighlightAll(){
		for(ColourButton b : buttons){
			if(b.isHighlighted()){
				b.toggleHighlight();
			}
		}
	}
	public ColourButton getCurrentlyHighlighted(){
		for(ColourButton b : buttons){
			if(b.isHighlighted()){
				return b;
			}
		}
		return null;
	}
	public void selectButton(int button){
		buttons.get(button).toggleHighlight();
	}
}
