package pacman.entity;

import java.awt.Canvas;

import pacman.ObserverGameDetails;

import soldier.ArmedUnit;
import soldier.ArmedUnitSquad;
import sun.management.counter.Units;
import utils.AgeFactory;
import utils.MiddleAgeFactory;

public class Hero extends AbstractEntity {
	private static ArmedUnit heroSquad;
	private static ArmedUnit hero;
	
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
		for (ObserverGameDetails ho : obs)
			ho.updateFrameForceHero();
	}
	
	public float getHealthPointHero(){
		if (hero == null){
			return getHealthPointUnit();
		}
		else{
			return hero.getHealthPoints();
		}
	}
	
	public float getMaxHealthPointHero(){
		if (hero == null){
			return getMaxHealthPointUnit();
		}
		else{
			return hero.getMaxHealthPoints();
		}
	}
	
	public void addHealthPointHero(float healthPoint){
		if (hero == null){
		 addHealthPoint(healthPoint);
		}
		else{
		 hero.addHealthPoints(healthPoint);
		}
		for (ObserverGameDetails ho : obs)
			ho.updateFrameHeroHealth();
	}
	
}
