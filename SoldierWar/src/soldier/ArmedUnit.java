package soldier;

import java.util.HashMap;

import utils.AgeFactory;
import utils.VisitorClassicForArmedUnit;
import utils.VisitorFunForArmedUnit;

public interface ArmedUnit {
	public String getName();
	public float getHealthPoints();
	public float getMaxHealthPoints();
	public void addHealthPoints(float f);
	public AgeFactory getAge();
	public boolean alive();
	public void heal();
	public boolean parry(float force); // true if the receiver is still alive after the strike
	public float strike();
	public void addEquipment(String weaponType);  
	public void accept(VisitorClassicForArmedUnit v);
	public <T> T accept(VisitorFunForArmedUnit<T> v);
	public int numberOfFriend();
	public HashMap<String, Float> getHealthAndName();
	public float getTotalStrength();
	public float getSwordStrength();
	public float getShieldDefense();
	
}