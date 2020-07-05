package Graphic_Motor;

import Game.Player;

public class FrameMannger {
	
	private GameFrame frame;

	public FrameMannger() {
		// TODO Auto-generated constructor stub
		frame = new GameFrame();
	}
	
	public void turnOn() {
		frame.turnOn();
	}
	
	public void turnOff() {
		frame.turnOff();
	}
	
	public void setFrame(GameFrame gf) {
		frame = gf;
	}
	
	public Player getWinner() {
		return frame.getWinner();
	}
	

}
