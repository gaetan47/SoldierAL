package zelda.entity;

import gameframework.base.DrawableImage;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public abstract class AbstractBonus {
	protected DrawableImage image = null;
	protected Point position;
	public final int RENDERING_SIZE = 16;
	
	public Point getPosition() {
		return position;
	}
	
	public void draw(Graphics g) {
		g.drawImage(image.getImage(), (int) getPosition().getX(),
				(int) getPosition().getY(), RENDERING_SIZE, RENDERING_SIZE,
				null);
	
	}
	public Rectangle getBoundingBox() {
		return (new Rectangle((int) position.getX(), (int) position.getY(),
				RENDERING_SIZE, RENDERING_SIZE));
	}
}
