package pacman.entity;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import soldier.ArmedUnit;
import soldier.ArmedUnitSoldier;
import soldier.ArmedUnitSquad;
import utils.AgeFactory;
import gameframework.base.Drawable;
import gameframework.base.DrawableImage;
import gameframework.base.Overlappable;
import gameframework.game.GameEntity;
import gameframework.game.GameMovable;
import gameframework.game.SpriteManagerDefaultImpl;

public abstract class AbstractEntity extends GameMovable implements Drawable, GameEntity,
	Overlappable {

		protected static DrawableImage image = null;
		protected boolean movable = true;
		protected boolean active = true;
		private final SpriteManagerDefaultImpl spriteManager;
		public static final int RENDERING_SIZE = 16;

		private ArmedUnit unit;
		
		// Constructeur pour un h�ro ou boss (Super)
		public AbstractEntity(Canvas defaultCanvas, AgeFactory factory, String soldatType, String name
				,int healthPoint,int force) {
			unit = new ArmedUnitSoldier(factory, soldatType, name,healthPoint,force);
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
		
		
		// Constructeur pour un soldat (un param��tre de plus)
		public AbstractEntity(Canvas defaultCanvas, AgeFactory factory, String soldatType, String name) {
			unit = new ArmedUnitSoldier(factory, soldatType, name);
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

		// Constructeur pour une arm��e
		public AbstractEntity(Canvas defaultCanvas, AgeFactory factory, String name) {
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
		
		public float getHealthPointUnit() {
			return unit.getHealthPoints();
		}

		public float getMaxHealthPointUnit(){
			return unit.getMaxHealthPoints();
		}
		
		public void addHealthPoint(float f){
			unit.addHealthPoints(f);
			
		}
		
		public float strike(){
			return unit.strike();
		}
		
		public void parry(float force){
			unit.parry(force);
		}
}
