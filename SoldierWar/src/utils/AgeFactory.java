package utils;

import soldier.Soldier;
import soldier.SoldierAbstract;

public interface AgeFactory {
	SoldierAbstract getSimpleSoldier(String name);
	SoldierAbstract getComplexSoldier(String name);
	public SoldierAbstract getSuperSoldier(String name, int healthpoint, int force);
	Soldier getDefensiveWeapon(Soldier s);
	Soldier getOffensiveWeapon(Soldier s);
}
