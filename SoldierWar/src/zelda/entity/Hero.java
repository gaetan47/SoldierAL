package zelda.entity;

import java.awt.Canvas;

import soldier.ArmedUnit;
import soldier.ArmedUnitSquad;
import utils.AgeFactory;
import zelda.ObserverGameDetails;

public class Hero extends AbstractEntity {
	private ArmedUnit heroSquad;
	private ArmedUnit hero;

	public Hero (Canvas defaultCanvas, AgeFactory factory, String soldatType, String name
			,int healthPoint,int force){
		super(defaultCanvas, factory,soldatType, name,healthPoint,force);
	}

	public void addEntity(Enemy enemy){
		// get singleton
		if (heroSquad == null){
			heroSquad = new ArmedUnitSquad(unit.getAge(), "heroSquad");
			((ArmedUnitSquad)heroSquad).addUnit(unit);
			// On garde un pointeur direct sur notre héro dans l'armé et on remplace l'unité qu'était notre hero par une armée avec l'ennemi
			hero = unit;
			unit = heroSquad;
		}
		// la visibilitée de "unit" dans AbstractEntity est protected on y a alors accès directement
		((ArmedUnitSquad)unit).addUnit(enemy.unit);
		for (ObserverGameDetails ho : obs){
			ho.updateFrameForceHero();
			ho.updateFrameFriends(enemy);
			
		}
	}

	public float getHealthPointsHero(){
		if (hero == null){
			return getHealthPointsUnit();
		}
		else{
			return hero.getHealthPoints();
		}
	}

	public float getMaxHealthPointsHero(){
		if (hero == null){
			return getMaxHealthPointUnit();
		}
		else{
			return hero.getMaxHealthPoints();
		}
	}

	public void addHealthPointsHero(float healthPoint){
		if (hero == null){
			addHealthPoints(healthPoint);
		}
		else{
			hero.addHealthPoints(healthPoint);
		}
		for (ObserverGameDetails ho : obs)
			ho.updateFrameHeroHealth();
	}
	
	public void youWin(){
		for (ObserverGameDetails ho : obs)
			ho.updateWin();
	}
	
	public void youLoose(){
		for (ObserverGameDetails ho : obs)
			ho.updateLoose();
		
	}

}
