package zelda.entity;

import java.awt.Canvas;

import utils.AgeFactory;

public class Enemy extends AbstractEntity {
	
	//Arm?e ennemi
	public Enemy (Canvas defaultCanvas, AgeFactory factory, String soldatType, String name, int numberOfSoldiers){
		super(defaultCanvas, factory, soldatType, name, numberOfSoldiers);
	}
	
	//Ennemi seul
	public Enemy (Canvas defaultCanvas, AgeFactory factory, String soldatType, String name){
		super(defaultCanvas, factory, soldatType, name);
	}
	

	// on régénère l'unitée
	public void heal() {  
		unit.heal();
	}
	
}
