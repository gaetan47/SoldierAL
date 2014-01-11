package pacman;

import java.util.Observable;

import javax.swing.JLabel;

import pacman.entity.Hero;

public class HealthObserver extends Observable implements ObserverGameDetails {
	
	private JLabel l;
	private Hero h;
	
	public HealthObserver(JLabel l,Hero h){
		super();
		this.l = l;
		this.h=h;
	}
	
	public void updateFrameHealth(){
		l.setText(""+h.getHealthPointUnit());
	}

}
