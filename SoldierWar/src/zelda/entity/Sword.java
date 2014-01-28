package zelda.entity;

import gameframework.base.DrawableImage;

import java.awt.Canvas;
import java.awt.Point;

public class Sword extends Weapon {

	public Sword(Canvas defaultCanvas, Point pos) {
		image = new DrawableImage("images/sword.gif", defaultCanvas);
		position = pos;
	}
}
