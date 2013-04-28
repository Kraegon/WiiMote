package control;

import java.util.LinkedList;
import java.util.Random;

import data.GameColour;


public class GameLogic {
	private static GameLogic INSTANCE;
	private int currentPoint;
	private LinkedList<GameColour> sequence;
	private boolean isDisplaying;
	private boolean isPlaying;
	private Random random;
	
	public GameLogic(){
		sequence = new LinkedList<GameColour>();
		random = new Random();
		isDisplaying = false;
		isPlaying = false;
		currentPoint = 0;
	}
	
	public static GameLogic getInstance(){
		if(INSTANCE == null)
			INSTANCE = new GameLogic();
		return INSTANCE;
	}
	
	public void generateNext(){
		sequence.add(new GameColour(random.nextInt(4)));
	}
	
	public void printSequence(LinkedList<GameColour> toPrint){
		for(int i = 0; i < toPrint.size(); i++){
			System.out.println(i + " : " + toPrint.get(i));
		}
	}
	
	public void highlightSequence(){
		isDisplaying = true;
		for(GameColour e : sequence){
			switch(e.getColour()){
			case 0: //RED - up
				ViewLogic.getInstance().selectUpwards();
				break;
			case 1:// BLUE - down
				ViewLogic.getInstance().selectDownwards();
				break;
			case 2:// GREEN - right
				ViewLogic.getInstance().selectRight();
				break;
			case 3:// YELLOW - left
				ViewLogic.getInstance().selectLeft();
				break;
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e1) {}
			ViewLogic.getInstance().unhighlightAll();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e1) {}
		}
		isDisplaying = false;
	}
	
	public boolean checkInput(){
		boolean isCorrect = false;
		if(ViewLogic.getInstance().getHighlighted().getGameColour().getColour() == sequence.get(currentPoint).getColour()){
			isCorrect = true;
		}
		currentPoint ++;
		currentPoint %= sequence.size();
		return isCorrect;
	}
	
	public void reset(){
		sequence = new LinkedList<GameColour>();
		currentPoint = 0;
	}
	public void togglePlaying(){
		isPlaying = !isPlaying;
	}
	public boolean isPlaying(){
		return isPlaying;
	}
	public int getcurrentPoint(){
		return currentPoint;
	}
	
	public boolean isDisplaying(){
		return isDisplaying;
	}
}
