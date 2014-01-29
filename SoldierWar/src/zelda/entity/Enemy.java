package zelda.entity;

import java.awt.Canvas;

import utils.AgeFactory;

public class Enemy extends AbstractEntity {
	
	//Armée ennemi ou ennemi seul
	public Enemy (Canvas defaultCanvas, AgeFactory factory, String soldatType, String name, int numberOfSoldiers){
		super(defaultCanvas, factory, soldatType, name, numberOfSoldiers);
	}
	
	

	// on régénère l'unitée
	public void heal() {  
		unit.heal();
	}
	
}
