package pacman.entity;

import java.awt.Canvas;

import utils.AgeFactory;

public class Enemy extends AbstractEntity {
	
	//Arm?e ennemi
	public Enemy (Canvas defaultCanvas, AgeFactory factory, String name){
		super(defaultCanvas, factory,name);
	}
	//Ennemi seul
	public Enemy (Canvas defaultCanvas, AgeFactory factory, String soldatType, String name){
		super(defaultCanvas, factory,soldatType,name);
	}
	
}
