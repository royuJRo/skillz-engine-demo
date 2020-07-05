package Graphic_Motor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import Game.*;
import bots.*;

public class GameFrame extends JFrame implements KeyListener , ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3316761471768842277L;
	
	protected Game game;
	protected SkillzBot myBotCode, enemyBotCode;
	
	
	private Thread botsAlogoritmsThread;
	private GraphicMotor graphicMotor;
	private VideoMaker vm;
	private int algoDelay = 1000; //The speed of switching between turns.
	public boolean running;
	private int endGameTurn;
	
	public GameFrame() {
		super("Yuval Rotman's Skillz demo machine");
		//setLocation(Window.WIDTH, Window.HEIGHT);
		this.game = new Game(); // Initialize the main game object
		this.graphicMotor = new GraphicMotor(game, true); // the game graphic motor.
		this.graphicMotor.loadObjects(); // loading the initial objects to the gmotor.
		
		/*
		 * bot's initialize
		 */
		this.myBotCode = new MyBot();
		this.enemyBotCode = new EnemyBot();
		
		this.vm = new VideoMaker();
		this.botsAlogoritmsThread = new Thread(this.vm);
		
		addKeyListener(this);
		
		setLayout(new BorderLayout());
		add(this.graphicMotor, BorderLayout.NORTH);
		add(new ControlPanel(this), BorderLayout.SOUTH);
		/*
		 * Configuration of frame parameters
		 */
		pack();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setFocusable(true);
		
		running = false;
		endGameTurn =1000;
	}
	
	
	
	public void turnOn() {
		setVisible(true);
	}
	public void turnOff() {
		setVisible(false);
		this.graphicMotor.setRunningStatus(false);
		this.graphicMotor.mouseLocationTimer.stop();
		running = false;
	}
	
	/**
	 * 
	 * @return The finished turn number.(After it execute it.)
	 */
	public int runNextTurn() {
		long startTime, endTime, turnTime;
		
		if(game.turn<=endGameTurn) {
			startTime = System.currentTimeMillis();
			/*run my self bot's turn code*/
			try {
			myBotCode.doTurn(this.game);
			}catch (Exception e) {
				System.out.println(e);
				System.out.println("bot crushed...");
				// enemy wins
				JOptionPane.showInternalMessageDialog(null, "Enemy won that match...", "Final results", JOptionPane.OK_CANCEL_OPTION);
				pause();
				endGameTurn = game.turn;
			}
			endTime = System.currentTimeMillis();
			turnTime = endTime - startTime;
			
			if(turnTime > 1000) {
				// my enemy wins
				System.out.println("My bot got time out...");
				// enemy wins
				JOptionPane.showInternalMessageDialog(null, "Enemy won that match...", "Final results", JOptionPane.OK_CANCEL_OPTION);
				pause();
				endGameTurn = game.turn;
			}
			
			startTime = System.currentTimeMillis();
			/*run my enemy bot's turn code*/
			try {
			enemyBotCode.doTurn(Game.getCostumizedGameObjectForEnemy(this.game));
			}catch (Exception e) {
				System.out.println(e);
				System.out.println("Enemy's bot crushed...");
				// I wins
				JOptionPane.showInternalMessageDialog(null, "I won that match...", "Final results", JOptionPane.OK_CANCEL_OPTION);
				pause();
				endGameTurn = game.turn;
			
				
			}
			endTime = System.currentTimeMillis();
			turnTime = endTime - startTime;
			
			if(turnTime > 1000) {
				// my self wins
				System.out.println("Enemy's bot got time out...");
				// I wins
				JOptionPane.showInternalMessageDialog(null, "I won that match...", "Final results", JOptionPane.OK_CANCEL_OPTION);
				pause();
				endGameTurn = game.turn;
			}
			
			int turn = game.turn; // assuming the current game's turn.
			game.moveToNextTurn(); // moving the next turn
			return turn; // returning turn num
			
		}
		else {
			System.out.println("Game already ended...") ;
			return game.turn;
		}
		
		
	}
	
	private Player getNextTurnAndGetWinner(Game game) {
			long startTime, endTime, turnTime;
			
				startTime = System.currentTimeMillis();
				/*run my self bot's turn code*/
				try {
				
				myBotCode.doTurn(game);
				}catch (Exception e) {
					// enemy wins
					return game.getEnemy();
				}
				endTime = System.currentTimeMillis();
				turnTime = endTime - startTime;
				
				if(turnTime > 1000) {
					// my enemy wins
					return game.getEnemy();
				}
				
				startTime = System.currentTimeMillis();
				/*run my enemy bot's turn code*/
				try {
				enemyBotCode.doTurn(Game.getCostumizedGameObjectForEnemy(game));
				}catch (Exception e) {
					// I wins
					return game.getMyself();
				}
				endTime = System.currentTimeMillis();
				turnTime = endTime - startTime;
				
				if(turnTime > 1000) {
					// I wins
					return game.getMyself();
				}
				
				if(game.getMyself().score>=Game.WINNING_SCORE) {
					// I wins
					return game.getMyself();
				}
				else if(game.getEnemy().score>=Game.WINNING_SCORE) {
					// enemy wins
					return game.getEnemy();
				}
				
				if(game.turn == 1000) {
					if(game.getMyself().score>game.getEnemy().score)
					{
						// I wins
						return game.getMyself();
					}
					else if(game.getMyself().score<game.getEnemy().score) {
						// enemy wins
						return game.getEnemy();
					}
					else {
						// tiko
						return new Player("No one.{Tiko}");
					}
				}
				
				
				
				game.moveToNextTurn(); // moving the next turn
				game.turn++;
				return null;
	}
	
	public Player getWinner() {
		Game tempGame = new Game();
		Player Winner = null;
		while(Winner == null) {
			Winner = getNextTurnAndGetWinner(tempGame);
		}
		
		return Winner;
	}
	
	private class VideoMaker implements Runnable{
		private boolean killingCondition;
		
		public VideoMaker() {killingCondition = false;}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			@SuppressWarnings("unInitialize")
			int turn = -1; // first initializing 
			System.out.println("start running");
			long currentTime, delayedTime;
			 
			
			currentTime = delayedTime = System.currentTimeMillis();
			while(killingCondition == false) {
				currentTime = System.currentTimeMillis();
				if(currentTime >= delayedTime) {
					if(running) {
						// The algorithm to graphic of turn stuff
						turn = runNextTurn();
						
						if(game.getMyself().score>=Game.WINNING_SCORE) {
							// I wins
							JOptionPane.showInternalMessageDialog(null, "I won that match...", "Final results", JOptionPane.OK_CANCEL_OPTION);
							pause();
							endGameTurn = game.turn;
						}
						else if(game.getEnemy().score>=Game.WINNING_SCORE) {
							// enemy wins
							JOptionPane.showInternalMessageDialog(null, "Enemy won that match...", "Final results", JOptionPane.OK_CANCEL_OPTION);
							pause();
							endGameTurn = game.turn;
						}
						
						if(turn == 1000) {
							if(game.getMyself().score>game.getEnemy().score)
							{
								// I wins
								JOptionPane.showInternalMessageDialog(null, "I won that match...", "Final results", JOptionPane.OK_CANCEL_OPTION);
								pause();
								endGameTurn = game.turn;
							}
							else if(game.getMyself().score<game.getEnemy().score) {
								// enemy wins
								JOptionPane.showInternalMessageDialog(null, "Enemy won that match...", "Final results", JOptionPane.OK_CANCEL_OPTION);
								pause();
								endGameTurn = game.turn;
							}
							else {
								// tiko
								JOptionPane.showInternalMessageDialog(null, "No one won that match...(tiko)", "Final results", JOptionPane.OK_CANCEL_OPTION);
								pause();
								endGameTurn = game.turn;
							}
						}
					}
					if(game.turn<endGameTurn) {
						game.turn++;
					}
					currentTime = delayedTime = System.currentTimeMillis();
					delayedTime += algoDelay;
				}
			}
		}
		
		public void kill() {
			killingCondition = true;
		}
		
	}
	
	public void play() {
		if(running == false) {
		running = true;
		this.botsAlogoritmsThread.start();
		}
	}
	
	public void pause() {
		if(running == true) {
		running = false;
		this.vm.kill();
		this.vm = new VideoMaker();
		this.botsAlogoritmsThread = new Thread(this.vm);
		}
	}
	
	public void speedUp() {
		algoDelay -= 100;
	}
	
	public void speedDown() {
		algoDelay += 100;
	}
	
	public void moveToTurn(int turnNum) {
		this.remove(this.graphicMotor);
		this.graphicMotor = new GraphicMotor(this.game = new Game());
		this.graphicMotor.loadObjects();
		this.add(graphicMotor);
		for(int i = 1; i<turnNum;i++) {
			runNextTurn();
			game.turn++;
		}
		revalidate();	
		
	}

	
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == e.VK_SPACE) {
			if(running == false) {
				play();
			}
			else {
				pause();
				
			}
		}
		
	}
	

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if(e.getKeyCode() == e.VK_SPACE) {
			if(running == false) {
				running = true;
				this.botsAlogoritmsThread.start();
			}
			else {
				running = false;
				this.vm.kill();
				this.vm = new VideoMaker();
				this.botsAlogoritmsThread = new Thread(this.vm);
				
			}
		}
		if (e.getKeyCode() == e.VK_BACK_SPACE) {
			//reInitializeGameFrame();
		}
	}



	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		setFocusable(true);
	}

	

}
