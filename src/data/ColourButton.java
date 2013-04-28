package data;

import input.Controller;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import control.GameLogic;

public class ColourButton {
	private Point2D location;
	private Color colour;
	private GameColour representation;
	private boolean isHighlighted;
	private int size;
	private int[] scale;
	
	public ColourButton(Color colour, Point2D location, int size, int[] scale){
		this.colour = colour;
		this.location = location;
		this.scale = scale;
		this.size = size;
		if(colour.equals(Color.RED))
			representation = new GameColour(GameColour.RED);
		if(colour.equals(Color.YELLOW))
			representation = new GameColour(GameColour.YELLOW);
		if(colour.equals(Color.BLUE))
			representation = new GameColour(GameColour.BLUE);
		if(colour.equals(Color.GREEN))
			representation = new GameColour(GameColour.GREEN);
	}
	
	public Shape buttonShape(){
		Ellipse2D button = new Ellipse2D.Double(-size, -size, size * 2, size * 2);
		Rectangle2D buildShapeA = new Rectangle2D.Double(-size * 2,0,size * 4,size);
		Rectangle2D buildShapeB = new Rectangle2D.Double(0,-size * 2, size, size * 4);
		Ellipse2D buildShapeC = new Ellipse2D.Double(-(size/2),-(size/2),size,size);
		
		Area buttonArea = new Area(button);
		buttonArea.subtract(new Area(buildShapeA));
		buttonArea.subtract(new Area(buildShapeB));
		buttonArea.subtract(new Area(buildShapeC));
		return buttonArea;
	}
	
	public void update(){
		toggleHighlight();
	}
	
	public void toggleHighlight(){
		isHighlighted = !isHighlighted;
	}
	public GameColour getGameColour(){
		return representation;
	}
	public void draw(Graphics2D g){
		g.translate(location.getX(), location.getY());
		g.setPaint(colour);
		g.scale(scale[0], scale[1]);
		g.fill(buttonShape());
		g.scale(scale[0], scale[1]);
		g.setPaint(Color.BLACK);
		g.translate(-location.getX(), -location.getY());
	}
	
	public void drawHighlighted(Graphics2D g){
		g.translate(location.getX(), location.getY());
		g.setPaint(new Color(colour.getRed(), colour.getGreen(), colour.getBlue(), 200));
		g.scale(scale[0], scale[1]);
		g.fill(buttonShape());
		if(GameLogic.getInstance().isDisplaying())
			g.setPaint(Color.WHITE);
		else if(!GameLogic.getInstance().isDisplaying())
			g.setPaint(Color.BLACK);
		if(Controller.getInstance().isAHeld() && !GameLogic.getInstance().isDisplaying())
			g.setPaint(Color.ORANGE);
		g.setStroke(new BasicStroke(10, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND));
		g.draw(buttonShape());
		g.scale(scale[0], scale[1]);
		g.setPaint(Color.BLACK);
		g.translate(-location.getX(), -location.getY());
	}

	public boolean isHighlighted() {
		return isHighlighted;
	}
}
