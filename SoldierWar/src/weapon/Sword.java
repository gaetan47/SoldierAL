package weapon;
public class Sword extends WeaponAbstract {
	private static final float DEFENSE = 0;
	private static final float ATTACK = 15;
	private static final float RESISTANCE = 1;

	public Sword() {
		super(DEFENSE, ATTACK, RESISTANCE);
	}

	public void fix() {
		resistance = RESISTANCE;
	}
}
