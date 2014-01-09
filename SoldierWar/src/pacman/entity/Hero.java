package pacman.entity;

import java.awt.Canvas;

import utils.AgeFactory;

public class Hero extends AbstractEntity {
	
	
	public Hero (Canvas defaultCanvas, AgeFactory factory, String soldatType, String name
			,int healthPoint,int force){
		super(defaultCanvas, factory,soldatType, name,healthPoint,force);
	}
}
