package soldier;

public class SuperSoldier extends SoldierAbstract {
	
	private int maxHealthPoints;

	
	public SuperSoldier(String nom, int healthpoints, int force) {
		super(nom, healthpoints,force);
		maxHealthPoints = healthpoints;
	}

	public void heal() { //XXX resurrection allowed
		healthPoints = maxHealthPoints;
	}	

	public int getMaxHealthPoints() {
		return maxHealthPoints;
	}
	public void setMaxHealthPoints(int maxHealthPoints) {
		this.maxHealthPoints = maxHealthPoints;
	}
}		



