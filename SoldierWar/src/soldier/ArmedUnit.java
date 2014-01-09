package soldier;

import utils.VisitorClassicForArmedUnit;
import utils.VisitorFunForArmedUnit;
import utils.AgeFactory;

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
}