package pacman;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import pacman.entity.Hero;

public class GameDetailsFrame extends JFrame{
	
	private JLabel heroHealth,heroForce; // Label "Hero Life", label "Force"
	private JLabel numberHp; // Points de vie restant
	private JLabel maxNumberHp; // Points de vie maximum
	private JLabel numberForceHero; // Puissance héro

	private float healthpoint;
	private float maxHealthPoint;
	private float forceHero;
	
	public GameDetailsFrame(float healthpoint,float maxHealthPoint, float forceHero){
		super();
		this.healthpoint = healthpoint;
		this.maxHealthPoint = maxHealthPoint;
		this.forceHero = forceHero;
		build();
	}
	
	public void build(){
		setLayout(null);
		
		
		
		heroHealth = new JLabel("HeroLife :");
		heroHealth.setBounds(10, 10, 80, 10);
		add(heroHealth);
		
		heroForce = new JLabel("Force :");
		heroForce.setBounds(10, 30, 80, 10);
		add(heroForce);
		
		numberHp = new JLabel(""+healthpoint);
		numberHp.setBounds(90, 10, 50, 10);
		add(numberHp);
		
		maxNumberHp = new JLabel("/ "+maxHealthPoint);
		maxNumberHp.setBounds(140, 10, 50, 10);
		add(maxNumberHp);
		
		numberForceHero = new JLabel(""+ forceHero);
		numberForceHero.setBounds(90, 30, 80, 10);
		add(numberForceHero);
		
		
		
		setResizable(false);
		setTitle("Game Details");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(600, 0, 400, 400);
		setVisible(true);

	}
	
	public JLabel getHeroHealth2() {
		return numberHp;
	}
	
	

}
