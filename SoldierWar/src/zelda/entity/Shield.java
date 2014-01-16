package zelda.entity;

import gameframework.base.DrawableImage;

import java.awt.Canvas;
import java.awt.Point;

public class Shield extends Weapon {

	public Shield(Canvas defaultCanvas, Point pos) {
		image = new DrawableImage("images/superpacgum.gif", defaultCanvas);
		position = pos;
	}
}
