package pacman;

import pacman.entity.Enemy;
import soldier.ArmedUnit;

public interface ObserverGameDetails {

	public void updateFrameHeroHealth();
	public void updateFrameForceHero();
	public void updateFrameFriends(Enemy enemy);
	public void updateFrameFriendHealth(ArmedUnit enemy);
	public void updateFrameDefHero();
	
}
