package zelda.entity;

import gameframework.base.Drawable;
import gameframework.base.DrawableImage;
import gameframework.base.Overlappable;
import gameframework.game.GameEntity;

import java.awt.Canvas;
import java.awt.Point;

/*
 * 
 * Le bonus vie QUE pour notre H�ro
 * 
 */


public class Heart extends AbstractBonus implements Drawable, GameEntity, Overlappable {


	public Heart(Canvas defaultCanvas, Point pos) {
		image = new DrawableImage("images/heart.gif", defaultCanvas);
		position = pos;
	}
}
