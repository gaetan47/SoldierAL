package zelda;

import java.util.HashMap;
import java.util.Observable;

import javax.swing.JLabel;

import soldier.ArmedUnit;
import zelda.entity.Enemy;
import zelda.entity.Hero;

public class HeroObserver extends Observable implements ObserverGameDetails {
	
	private GameDetailsFrame frame;
	private Hero h;
	
	public HeroObserver(GameDetailsFrame frame,Hero h){
		super();
		this.frame = frame;
		this.h=h;
	}
	
	public void updateFrameHeroHealth(){
		frame.getHeroHealth2().setText(""+h.getHealthPointsHero());
	}
	
	public void updateFrameForceHero(){
		frame.getNumberForceHero().setText(""+h.strike());
	}
	
	public void updateFrameSwordHero(){
		frame.getHeroSword().setText("Sword : yes (+15)" );
	}
	
	public void updateFrameShieldHero(){
		frame.getHeroDef().setText("Shield : yes (+10)" );
	}

	
	public void updateFrameFriends(Enemy enemy){
		JLabel tmpName[] = frame.getNameFriendArmy();
		JLabel tmpLife [] =frame.getLifeFriendArmy();
		JLabel tmpForce [] =frame.getForceFriendArmy();
		for (int i =0;i<6; i++){
			if (tmpLife[i].getText() == ""){
				tmpName[i].setText(enemy.getName());
				tmpForce[i].setText(""+enemy.strike());
				tmpLife[i].setText(""+enemy.getHealthPointsUnit());
				break;
			}
		}
		
	}
	
	public void updateFrameFriendHealth(ArmedUnit enemy){
		HashMap<String, Float> tmpMap = enemy.getHealthAndName();
		JLabel tmpLife [] =frame.getLifeFriendArmy();
		JLabel tmpName[] = frame.getNameFriendArmy();
		JLabel tmpForce [] =frame.getForceFriendArmy();
		JLabel tmpSword [] =frame.getSwordFriendArmy();
		JLabel tmpShield [] =frame.getShieldFriendArmy();
		for (int i =0;i<6; i++){
		
			if ( tmpMap.containsKey(tmpName[i].getText())){
				if (tmpMap.get(tmpName[i].getText())<= 0){
					tmpLife[i].setText("");
					tmpName[i].setText("No Army");
					tmpForce[i].setText("");
					tmpSword[i].setText("");
					tmpShield[i].setText("");
					
				}
				else{
					tmpLife[i].setText(""+tmpMap.get(tmpName[i].getText()));
				}
				
			}
		}
		
	}
	
	
	public void updateFrameFriendSword(){
		
		JLabel tmpName[] = frame.getNameFriendArmy();
		JLabel tmpSword [] =frame.getSwordFriendArmy();
		for (int i =0;i<6; i++){
			if (!tmpName[i].getText().equals("No Army")){
				tmpSword[i].setText("(+15)");
			}

			
		}
		
	}
	
	public void updateFrameFriendShield(){
		
		JLabel tmpName[] = frame.getNameFriendArmy();
		JLabel tmpShield [] =frame.getShieldFriendArmy();
		for (int i =0;i<6; i++){
			if (!tmpName[i].getText().equals("No Army")){
				tmpShield[i].setText("(+10)");
			}

			
		}
		
	}
}