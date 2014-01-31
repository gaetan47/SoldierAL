package zelda.entity;

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
import java.util.ArrayList;

import soldier.ArmedUnit;
import soldier.ArmedUnitSoldier;
import soldier.ArmedUnitSquad;
import utils.AgeFactory;
import zelda.ObserverGameDetails;

public abstract class AbstractEntity extends GameMovable implements Drawable, GameEntity,
	Overlappable {
		protected ArrayList<ObserverGameDetails> obs = new ArrayList<ObserverGameDetails>();
		protected static DrawableImage image = null;
		protected boolean movable = true;
		protected boolean active = true;
		private final SpriteManagerDefaultImpl spriteManager;
		public static final int RENDERING_SIZE = 16;

		protected ArmedUnit unit;
		private boolean isEnemy = false;
		
		// Constructeur pour un héro
		public AbstractEntity(Canvas defaultCanvas, AgeFactory factory, String soldatType, String name
				,int healthPoint,int force, boolean isHero) {
			unit = new ArmedUnitSoldier(factory, soldatType, name,healthPoint,force);
			if (isHero){
				spriteManager = new SpriteManagerDefaultImpl("images/zelda_sprite.png",
					defaultCanvas, RENDERING_SIZE, 12);
				spriteManager.setTypes(
						//
						"up",
						"right",
						"down",
						"left");
			}else{
				spriteManager = new SpriteManagerDefaultImpl("images/boss.png",
					defaultCanvas, RENDERING_SIZE, 3);
				spriteManager.setTypes(
						//
						"down",
						"left",
						"right",
						"up");
			}
			
		}
		

		// Constructeur pour une armée ou un soldat seul
		public AbstractEntity(Canvas defaultCanvas, AgeFactory factory, String soldatType, String name, int numberOfSoldiers) {
			isEnemy = true;
			if (numberOfSoldiers == 1){
				unit = new ArmedUnitSoldier(factory, soldatType, name);
				spriteManager = new SpriteManagerDefaultImpl("images/soldier.gif",
						defaultCanvas, RENDERING_SIZE, 3);
			}else{
				unit = new ArmedUnitSquad(factory, name);
				// On remplit l'armée 
				for (int i = 0; i < numberOfSoldiers; i++){
					ArmedUnit soldier = new ArmedUnitSoldier(factory, soldatType, name+"Solider"+i);
					((ArmedUnitSquad)unit).addUnit(soldier);
				}
				spriteManager = new SpriteManagerDefaultImpl("images/army.gif",
						defaultCanvas, RENDERING_SIZE, 3);
			}
			
			spriteManager.setTypes(
					//
					"inactive");
		}

		public boolean isActive() {
			return active;
		}

		public void setAlive(boolean aliveState) {
			active = aliveState;
		}

		public void draw(Graphics g) {
			String spriteType = "";

			if (!isEnemy) {
			
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
			}else{
				spriteType = "inactive";
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
		
		public float getHealthPointsUnit() {
			return unit.getHealthPoints();
		}

		public float getMaxHealthPointUnit(){
			return unit.getMaxHealthPoints();
		}
		
		public void addHealthPoints(float f){
			unit.addHealthPoints(f);
			for (ObserverGameDetails ho : obs)
				ho.updateFrameHeroHealth();
			
		}
		
		public void addEquipment(String weaponType) {
			unit.addEquipment(weaponType);
			for (ObserverGameDetails ho : obs){
				if (weaponType=="Offensive"){
					ho.updateFrameSwordHero();
					ho.updateFrameFriendSword();
				}
				if (weaponType=="Defensive"){
					ho.updateFrameShieldHero();
					ho.updateFrameFriendShield();
				}
				ho.updateFrameForceHero();
				
			}
		}
		
		public float strike(){
			return unit.strike();
		}
		
		public void parry(float force){
			unit.parry(force);
			for (ObserverGameDetails ho : obs){
				ho.updateFrameHeroHealth();
				
				ho.updateFrameForceHero();
			}
			if (unit.getClass() == ArmedUnitSquad.class){
				for (ObserverGameDetails ho : obs){
					ho.updateFrameFriendHealth(unit);
				}
			}
		}
		
		public void addObserver(ObserverGameDetails observer){
			this.obs.add(observer);		
		}
		
		public String getName(){
			return unit.getName();
		}
		
		
		public float getTotalStrength(){
			return unit.getTotalStrength();
		}
		
		public float getSwordStrength(){
			return unit.getSwordStrength();
		}
		
		public float getShieldDefense(){
			return unit.getShieldDefense();
		}
		
	
	
}
