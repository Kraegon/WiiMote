package data;

public class GameColour{
	public static int RED = 0;
	public static int BLUE = 1;
	public static int GREEN = 2;
	public static int YELLOW = 3;
	
	private int colour;
	
	public GameColour(int colour){
		this.colour = colour;
	}
	
	public int getColour() {
		return colour;
	}

	public void setColour(int colour) {
		this.colour = colour;
	}
	public String toString(){
		switch(colour){
		case 0:
			return "[RED]";
		case 1:
			return "[BLUE]";
		case 2:
			return "[GREEN]";
		case 3:
			return "[YELLOW]";	
		}
		return "[NaC]";
	}
}
