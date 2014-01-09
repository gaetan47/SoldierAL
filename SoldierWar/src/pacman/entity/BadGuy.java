package pacman.entity;

import gameframework.base.Drawable;
import gameframework.base.DrawableImage;
import gameframework.base.Overlappable;
import gameframework.game.GameEntity;
import gameframework.game.GameMovable;
import gameframework.game.SpriteManagerDefaultImpl;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import soldier.ArmedUnit;
import soldier.ArmedUnitSoldier;
import soldier.ArmedUnitSquad;
import utils.AgeFactory;


public class BadGuy extends GameMovable implements Drawable, GameEntity,
Overlappable {

	protected static DrawableImage image = null;
	protected boolean movable = true;
	protected boolean active = true;
	private final SpriteManagerDefaultImpl spriteManager;
	public static final int RENDERING_SIZE = 16;

	private ArmedUnit unit;



	// Constructeur pour un soldat (un paramètre de plus)
	public BadGuy(Canvas defaultCanvas, AgeFactory factory, String soldatType, String name) {
		unit = new ArmedUnitSoldier(factory, soldatType, name);
		// TODO : changer l'image
		spriteManager = new SpriteManagerDefaultImpl("images/pac1.gif",
				defaultCanvas, RENDERING_SIZE, 6);
		// TODO : changer les types
		spriteManager.setTypes(
				//
				"left",
				"right",
				"up",
				"down",//
				"inactive-left", "inactive-right", "inactive-up",
				"inactive-down", //
				"unused");
	}

	// Constructeur pour une armée
	public BadGuy(Canvas defaultCanvas, AgeFactory factory, String name) {
		unit = new ArmedUnitSquad(factory, name);
		// TODO : changer l'image
		spriteManager = new SpriteManagerDefaultImpl("images/ghost.gif",
				defaultCanvas, RENDERING_SIZE, 6);
		// TODO : changer les types
		spriteManager.setTypes(
				//
				"left",
				"right",
				"up",
				"down",//
				"inactive-left", "inactive-right", "inactive-up",
				"inactive-down", //
				"unused");
	}

	public boolean isActive() {
		return active;
	}

	public void setAlive(boolean aliveState) {
		active = aliveState;
	}

	public void draw(Graphics g) {
		String spriteType = "";
		Point tmp = getSpeedVector().getDirection();
		movable = true;

		if (!isActive()) {
			spriteType = "inactive-";
		}

		if (tmp.getX() == -1) {
			spriteType += "left";
		} else if (tmp.getY() == 1) {
			spriteType += "down";
		} else if (tmp.getY() == -1) {
			spriteType += "up";
		} else {
			spriteType += "right";
		}

		spriteManager.setType(spriteType);
		spriteManager.draw(g, getPosition());
	}

	@Override
	public void oneStepMoveAddedBehavior() {
		if (movable) {
			spriteManager.increment();
		}
	}

	public Rectangle getBoundingBox() {
		return (new Rectangle(0, 0, RENDERING_SIZE, RENDERING_SIZE));
	}
	
	public ArmedUnit getUnit() {
		return unit;
	}

	public void setUnit(ArmedUnit unit) {
		this.unit = unit;
	}
}


