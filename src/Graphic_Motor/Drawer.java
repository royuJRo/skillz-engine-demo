package Graphic_Motor;

import java.awt.*;
import java.net.URL;
import javax.swing.ImageIcon;

import Game.*;

public final class Drawer implements Drawable {
	
	private GameObject object;
	
	private boolean isDrawing;
	
	public Drawer(GameObject go) {
		object = go;
		isDrawing = true;
	}
	

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		if(isDrawing) {
			Graphics2D graphics = (Graphics2D)g;
			graphics.drawImage(getImage(),object.getLocation().col,object.getLocation().row, object.Width, object.High,null);
			//graphics.fillRect(object.getLocation().col, object.getLocation().row, object.Width, object.High);
		}
		}
	
	public URL getImagePath() {
		return this.getClass().getResource(object.getPicPath());
	}
	
	public Image getImage() {
		ImageIcon icon = new ImageIcon(getImagePath());
		return icon.getImage();
	}
	
	public void stopDrawing() {
		isDrawing = false;
	}
		
	
}
interface Drawable{
	public void draw(Graphics g);
}