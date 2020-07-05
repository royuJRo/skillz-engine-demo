package Graphic_Motor;

import Game.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.*;

import javax.swing.ImageIcon;
import javax.swing.Timer;

/**
 * 
 * @author yuval
 * Graphic pannel which update for each 80 millisecond.
 *
 */
public class GraphicMotor extends Canvas implements Runnable, MouseMotionListener, ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -149303210171796060L;
	public static final int HIGHET = 515;
	public static final int WIDTH = 830;
	
	private boolean running;
	private Game mainGame;
	private Thread thread;
	public Timer mouseLocationTimer;
	
	private ArrayList<GameObject> gameObjectsStock;
	
	private int mouseX = 0, mouseY = 0; // values of mouse and the initialized.
	
	public GraphicMotor(Game game, boolean runningStatus) {
		mainGame = game;
		running = runningStatus;
		thread = new Thread(this);
		if(runningStatus)
			thread.start();
		
		gameObjectsStock = new ArrayList<GameObject>();
		addMouseMotionListener(this);
		
		this.mouseLocationTimer = new Timer(100, this);
		this.mouseLocationTimer.addActionListener(this);
		this.mouseLocationTimer.start();
		
		setSize(WIDTH, HIGHET);
	}
	
	public GraphicMotor(Game game) {
		mainGame = game;
		running = false;
		thread = new Thread(this);
		
		gameObjectsStock = new ArrayList<GameObject>();
		addMouseMotionListener(this);
		
		this.mouseLocationTimer = new Timer(20, this);
		this.mouseLocationTimer.addActionListener(this);
		this.mouseLocationTimer.start();
		
		setSize(WIDTH, HIGHET);
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int delay = 80;
		long lastTime = System.currentTimeMillis();
		long currentTime = System.currentTimeMillis();
		
		while(this.isRunning()) {
			if((currentTime-lastTime)>=delay) {
				timerMotorTick();// excute tick actions.
				
				lastTime = System.currentTimeMillis();
				currentTime = System.currentTimeMillis();
			}
			else currentTime = System.currentTimeMillis();
		}
		
	}
	
	public void timerMotorTick() {
		this.update();
		this.repaint();
		//System.out.println("tick");
	}
	
	@Override
	public void paint(Graphics g) {
		draw(g);
		drawMouseLocation(g);
	}
	
	private void draw(Graphics g) {
		
		// background drawing
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(getBackgroundImage(),0,0, WIDTH, HIGHET,null);
		
		drawTurnNum(g);
		//objects drawing
		for (GameObject gameObject : gameObjectsStock) {
			Drawer d = new Drawer(gameObject);
			d.draw(g);
		}
	}
	
	public void update() {
		for(GameObject g : gameObjectsStock) {
			g.update();
		}
	}

	public boolean isRunning() {
		return running;
	}

	public void loadObjects() {
		for(GameObject g: gameObjectsStock) {
			if(g instanceof Pirate && ((Pirate)g).isAlive() == false) {
				gameObjectsStock.remove(g);
			}
		}
		
		for(Pirate g: mainGame.getAllMyLivingPirates()) {
			if(!gameObjectsStock.contains(g))
				gameObjectsStock.add(g);
		}
		
		for(Pirate g: mainGame.getAllEnemyLivingPirates()) {
			if(!gameObjectsStock.contains(g))
				gameObjectsStock.add(g);
		}
	}

	public void setRunningStatus(boolean running) {
		this.running = running;
	}
	
	public void addGameObject(GameObject gameObject) {
		this.gameObjectsStock.add(gameObject);
	}
	
	public void removeGameObject(GameObject gameObject) {
		this.gameObjectsStock.add(gameObject);
	}
	
	public boolean isGameObjectExist(GameObject gameObject) {
		return gameObjectsStock.contains(gameObject);
	}
	
	public GameObject[] getGameObjectsArray() {
		GameObject[] array = new GameObject[this.gameObjectsStock.size()];
		array = this.gameObjectsStock.toArray(array);
		return array;
	}
	
	public static Drawer draw(GameObject gObj, Graphics g) {
		Drawer drawerInterface = new Drawer(gObj);
		drawerInterface.draw(g);
		return drawerInterface;
	}
	
	public void drawMouseLocation(Graphics g) {
		Graphics2D graphics = (Graphics2D)g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);
		graphics.setColor(Color.black);
		graphics.setFont(new Font("Serif", Font.BOLD, 14));
		graphics.drawString("Mouse Location [col ="+mouseX+", row ="+mouseY+"]", 0, 15);
	}
	
	public void drawTurnNum(Graphics g) {
		Graphics2D graphics = (Graphics2D)g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);
		graphics.setColor(Color.black);
		graphics.setFont(new Font("Serif", Font.BOLD, 14));
		graphics.drawString("Turn : "+mainGame.turn, WIDTH-100, 15);
	}
	
	public Image getBackgroundImage() {
		ImageIcon ii = new ImageIcon(getClass().getResource("/images/bg.jpg"));
		return ii.getImage();
	}
	
	
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		mouseX = e.getX();
		mouseY = e.getY();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
			//timer ticked
			this.repaint(); // repaint graphic
			setFocusable(false);
		
	}
	

}
