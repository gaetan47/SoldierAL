package pacman.entity;

import gameframework.base.DrawableImage;

import java.awt.Canvas;
import java.awt.Point;

public class Sword extends Weapon {

	public Sword(Canvas defaultCanvas, Point pos) {
		image = new DrawableImage("images/superpacgum.gif", defaultCanvas);
		position = pos;
	}
}
