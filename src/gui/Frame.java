package gui;

import javax.swing.JFrame;

public class Frame extends JFrame{
	private GamePanel gamePanel = new GamePanel();
	
	public Frame(){
		super("Simon");
		setContentPane(gamePanel);
		setSize(520,600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public GamePanel getGamePanel(){
		return gamePanel;
	}
}
