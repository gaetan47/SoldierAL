package weapon;
import soldier.Soldier;

public class SoldierWithSword extends SoldierArmed<Sword> {

	public SoldierWithSword(Soldier s) {
		super(s, new Sword());
	}

	@Override
	public int getMaxHealthPoints() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setMaxHealthPoints(int maxHealthPoints) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addHealthPoints(float f) {
		// TODO Auto-generated method stub
		
	}
}
