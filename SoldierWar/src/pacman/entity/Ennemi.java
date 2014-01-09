package pacman.entity;

import java.awt.Canvas;

import utils.AgeFactory;

public class Ennemi extends AbstractEntity {
	
	//Armée ennemi
	public Ennemi (Canvas defaultCanvas, AgeFactory factory, String name){
		super(defaultCanvas, factory,name);
	}
	//Ennemi seul
	public Ennemi (Canvas defaultCanvas, AgeFactory factory, String soldatType, String name){
		super(defaultCanvas, factory,soldatType,name);
	}
	
}
