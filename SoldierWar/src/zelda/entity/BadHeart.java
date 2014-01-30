package zelda.entity;

import gameframework.base.Drawable;
import gameframework.base.DrawableImage;
import gameframework.base.Overlappable;
import gameframework.game.GameEntity;

import java.awt.Canvas;
import java.awt.Point;

public class BadHeart extends AbstractBonus implements Drawable, GameEntity, Overlappable {


	public BadHeart(Canvas defaultCanvas, Point pos) {
		image = new DrawableImage("images/dead.gif", defaultCanvas);
		position = pos;
	}

}
