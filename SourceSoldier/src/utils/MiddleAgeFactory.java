package utils;

import soldier.*; 
import weapon.*;

public class MiddleAgeFactory implements AgeFactory {
	public SoldierAbstract getSimpleSoldier(String name) {
		return new InfantryMan(name);
	}
 
	public SoldierAbstract getComplexSoldier(String name) {
		return new Horseman(name);
	}
 
	public Soldier getDefensiveWeapon(Soldier s) {
		return new SoldierWithShield(s);
	}
 
	public Soldier getOffensiveWeapon(Soldier s) {
		return new SoldierWithSword(s);
	}
}
