package pacman.entity;

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
		//TODO : Modifier l'image en vie
		image = new DrawableImage("images/pacgum.gif", defaultCanvas);
		position = pos;
	}
}
