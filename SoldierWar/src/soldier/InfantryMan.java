package soldier;

public class InfantryMan extends SoldierAbstract {
	private static final int FORCE = 15;
	private static final int HEALTHPOINTS = 100;
	private int maxHealthPoints;

	public InfantryMan(String nom) {
		super(nom, HEALTHPOINTS, FORCE);
		maxHealthPoints = HEALTHPOINTS;
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