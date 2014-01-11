package pacman;

import java.util.Observable;

import javax.swing.JLabel;

import pacman.entity.Hero;

public class HeroObserver extends Observable implements ObserverGameDetails {
	
	private GameDetailsFrame frame;
	private Hero h;
	
	public HeroObserver(GameDetailsFrame frame,Hero h){
		super();
		this.frame = frame;
		this.h=h;
	}
	
	public void updateFrameHeroHealth(){
		frame.getHeroHealth2().setText(""+h.getHealthPointHero());
	}
	
	public void updateFrameForceHero(){
		frame.getNumberForceHero().setText(""+h.strike());
	}
	
	

}
