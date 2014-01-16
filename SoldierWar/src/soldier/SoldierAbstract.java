package soldier;

public abstract class SoldierAbstract implements Soldier {
	protected String name;
	protected float healthPoints;
	protected float force;


	public SoldierAbstract(String nom, float healthPoints, float force) {
		this.name = nom;
		this.healthPoints = healthPoints;
		this.force = force;
	}

	public String getName() {
		return name;
	}

	public float getHealthPoints() {
		return healthPoints;
	}
	
	public void addHealthPoints(float f){
		healthPoints+=f;
	}

	public boolean alive() {
		return getHealthPoints() > 0;
	}

	public boolean parry(float force) { //default: no parry effect
		healthPoints = (getHealthPoints() > force) ? 
				               getHealthPoints() - force : 0;
	    return healthPoints > 0;
	}

	public float strike() {
		return alive() ? force : 0; 
	} 
	
	public float getForce() {
		return force;
	}

	public void setForce(float force) {
		this.force = force;
	}
	
	public float getTotalStrength(){
		return force;
	}
	
	public float getSwordStrength(){
		return 0;
	}
	
	public float getShieldDefense(){
		return 0;
	}
}
