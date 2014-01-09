package pacman.entity;

import gameframework.base.Drawable;
import gameframework.base.DrawableImage;
import gameframework.base.Overlappable;
import gameframework.game.GameEntity;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class SuperHeart extends AbstractBonus implements Drawable, GameEntity, Overlappable {


	public SuperHeart(Canvas defaultCanvas, Point pos) {
		image = new DrawableImage("images/superpacgum.gif", defaultCanvas);
		position = pos;
	}

}
