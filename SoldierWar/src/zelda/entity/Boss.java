package zelda.entity;

import java.awt.Canvas;

import utils.AgeFactory;

public class Boss extends AbstractEntity {
	
	
	public Boss (Canvas defaultCanvas, AgeFactory factory, String soldatType, String name
			,int healthPoint,int force){
		super(defaultCanvas, factory,soldatType, name,healthPoint,force);
	}
}
