package zelda;

import java.util.Observable;

import javax.swing.JLabel;

import soldier.ArmedUnit;
import zelda.entity.Enemy;
import zelda.entity.Hero;

public class HealthObserver extends Observable implements ObserverGameDetails {
	
	private JLabel l;
	private Hero h;
	
	public HealthObserver(JLabel l,Hero h){
		super();
		this.l = l;
		this.h=h;
	}
	
	public void updateFrameHealth(){
		l.setText(""+h.getHealthPointsUnit());
	}

	@Override
	public void updateFrameHeroHealth() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateFrameForceHero() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateFrameFriends(Enemy enemy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateFrameFriendHealth(ArmedUnit enemy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateFrameDefHero() {
		// TODO Auto-generated method stub
		
	}

}
