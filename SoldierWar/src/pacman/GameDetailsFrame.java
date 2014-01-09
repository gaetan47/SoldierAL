package pacman;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import pacman.entity.Hero;

public class GameDetailsFrame extends JFrame{
	
	private JLabel heroHealth,heroHealth2;


	private Hero h;
	
	public GameDetailsFrame(Hero h){
		super();
		this.h =h;
		build();
	}
	
	public void build(){
		setLayout(null);
		
		
		
		heroHealth = new JLabel("HeroLife");
		heroHealth.setBounds(10, 10, 100, 10);
		add(heroHealth);
		
		heroHealth2 = new JLabel(""+h.getHealthPointUnit());
		heroHealth2.setBounds(110, 10, 100, 10);
		add(heroHealth2);
		
		
		
		setResizable(false);
		setTitle("Game Details");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(600, 0, 400, 400);
		setVisible(true);

	}
	
	public JLabel getHeroHealth2() {
		return heroHealth2;
	}
	
	

}
