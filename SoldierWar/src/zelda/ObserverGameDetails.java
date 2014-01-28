package zelda;

import soldier.ArmedUnit;
import zelda.entity.Enemy;

public interface ObserverGameDetails {

	public void updateFrameHeroHealth();
	public void updateFrameForceHero();
	public void updateFrameFriends(Enemy enemy);
	public void updateFrameFriendHealth(ArmedUnit enemy);
	public void updateFrameSwordHero();
	public void updateFrameShieldHero();
	public void updateFrameFriendSword();
	public void updateFrameFriendShield();
	public void updateWin();
	public void updateLoose();
	
	
}


