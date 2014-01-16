package soldier;

public interface Soldier {
	public String getName();
	public float getHealthPoints();
	public void addHealthPoints(float f);
	public boolean alive();
	public void heal();
	public float getTotalStrength();
	public float getSwordStrength();
	public float getShieldDefense();
	
	/**
	 * @param force the force of the attack the receiver has to parry
	 * @return true iff the receiver is still alive after the attack
	 */
	public boolean parry(float force);
	public float strike();
	
	public int getMaxHealthPoints();
	public void setMaxHealthPoints(int maxHealthPoints);

}
