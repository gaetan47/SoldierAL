package zelda;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameDetailsFrame extends JFrame{
	

	private static final long serialVersionUID = 1L;
	private JLabel heroHealth,heroForce,heroDef,heroSword; // Label "Hero Life", label "Force"

	private JLabel numberHp; // Points de vie restant
	private JLabel maxNumberHp; // Points de vie maximum
	private JLabel numberForceHero; // Puissance h�ro
	private JLabel result;


	

	

	private JPanel friendArmy[];
	private JLabel lifeFriendArmy[];
	private JLabel nameFriendArmy[];
	private JLabel forceFriendArmy[];
	private JLabel swordFriendArmy[];
	private JLabel shieldFriendArmy[];



	private float healthpoint;
	private float maxHealthPoint;
	private float forceHero;
	
	public GameDetailsFrame(float healthpoint,float maxHealthPoint, float forceHero){
		super();
		friendArmy = new JPanel[6];
		lifeFriendArmy = new JLabel[6];
		nameFriendArmy = new JLabel[6];
		forceFriendArmy = new JLabel[6];
		swordFriendArmy= new JLabel[6];
		shieldFriendArmy= new JLabel[6];
		this.healthpoint = healthpoint;
		this.maxHealthPoint = maxHealthPoint;
		this.forceHero = forceHero;
		build();
	}
	
	public void build(){
		setLayout(null);
		
		result = new JLabel("");
		result.setBounds(150, 320, 100, 30);
		add(result);
		
		
		heroHealth = new JLabel("HeroLife :");
		heroHealth.setBounds(10, 10, 80, 10);
		add(heroHealth);
		
		heroForce = new JLabel("Force :");
		heroForce.setBounds(10, 30, 80, 10);
		add(heroForce);
		
		heroDef = new JLabel("Shield : no");
		heroDef.setBounds(240, 30, 150, 12);
		add(heroDef);
		
		heroSword = new JLabel("Sword : no");
		heroSword.setBounds(240, 10, 150, 12);
		add(heroSword);
		
		numberHp = new JLabel(""+healthpoint);
		numberHp.setBounds(90, 10, 70, 10);
		add(numberHp);
		
		maxNumberHp = new JLabel("/ "+maxHealthPoint);
		maxNumberHp.setBounds(160, 10, 80, 10);
		add(maxNumberHp);
		
		numberForceHero = new JLabel(""+ forceHero);
		numberForceHero.setBounds(90, 30, 80, 10);
		add(numberForceHero);
		

		
		
		/*Affichage des alli�s*/

		int largPanel = 130;
		int hautPanel = 80;
		int id=0;
		int haut = 70;
		for (int i=0;i<2;i++){
			for (int j=0;j<3;j++){
				//JPanel p = new JPanel();
				JPanel p = createPanel(id);
				p.setBounds(j*largPanel+2, haut,largPanel, hautPanel);
				friendArmy[id] = p;
				id++;
			}
			haut+=(hautPanel+60);
		}
		
		
		for (int i=0;i<6 ;i++){
			add(friendArmy[i]);
		}
		
		
		
		setResizable(false);
		setTitle("Game Details");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(600, 0, 400, 400);
		setVisible(true);

	}
	
	public JLabel getHeroHealth2() {
		return numberHp;
	}
	
	public JLabel getNumberForceHero() {
		return numberForceHero;
	}
	
	public JPanel createPanel(int id){
		JPanel p = new JPanel();
		p.setLayout(null);
		JLabel label = new JLabel("No Army");
		label.setBounds(10, 0, 100,20);
		nameFriendArmy[id] = label;
		JLabel label2 = new JLabel("Life :");
		label2.setBounds(10, 20, 50, 15);
		JLabel label4 = new JLabel("");
		label4.setBounds(70, 20, 60, 15);
		lifeFriendArmy[id] = label4;
		JLabel label3 = new JLabel("Force :");
		label3.setBounds(10, 35, 50, 15);
		JLabel label5 = new JLabel("");
		label5.setBounds(70, 35, 50, 15);
		forceFriendArmy[id] = label5;
		JLabel label6 = new JLabel("Sword :");
		label6.setBounds(10, 50, 50, 15);
		JLabel label7 = new JLabel("");
		label7.setBounds(70, 50, 50, 15);
		swordFriendArmy[id] = label7;
		JLabel label8 = new JLabel("Shield :");
		label8.setBounds(10, 65, 50, 15);
		JLabel label9 = new JLabel("");
		label9.setBounds(70, 65, 50, 15);
		shieldFriendArmy[id] = label9;
		p.add(label);
		p.add(label2);
		p.add(label3);
		p.add(label4);
		p.add(label5);
		p.add(label6);
		p.add(label7);
		p.add(label8);
		p.add(label9);
		return p;
	}

	public JPanel[] getFriendArmy() {
		return friendArmy;
	}

	public void setFriendArmy(JPanel[] friendArmy) {
		this.friendArmy = friendArmy;
	}
	
	public JLabel[] getLifeFriendArmy() {
		return lifeFriendArmy;
	}

	public void setLifeFriendArmy(JLabel[] lifeFriendArmy) {
		this.lifeFriendArmy = lifeFriendArmy;
	}
	
	public JLabel[] getNameFriendArmy() {
		return nameFriendArmy;
	}

	public void setNameFriendArmy(JLabel[] nameFriendArmy) {
		this.nameFriendArmy = nameFriendArmy;
	}
	public JLabel[] getForceFriendArmy() {
		return forceFriendArmy;
	}

	public void setForceFriendArmy(JLabel[] forceFriendArmy) {
		this.forceFriendArmy = forceFriendArmy;
	}
	

	
	public JLabel[] getSwordFriendArmy() {
		return swordFriendArmy;
	}

	public void setSwordFriendArmy(JLabel[] swordFriendArmy) {
		this.swordFriendArmy = swordFriendArmy;
	}
	
	public JLabel[] getShieldFriendArmy() {
		return shieldFriendArmy;
	}

	public void setShieldFriendArmy(JLabel[] shieldFriendArmy) {
		this.shieldFriendArmy = shieldFriendArmy;
	}
	
	public JLabel getHeroSword() {
		return heroSword;
	}

	public void setHeroSword(JLabel heroSword) {
		this.heroSword = heroSword;
	}

	public JLabel getHeroDef() {
		return heroDef;
	}

	public void setHeroDef(JLabel heroDef) {
		this.heroDef = heroDef;
	}
	
	public JLabel getResult() {
		return result;
	}

	public void setResult(JLabel result) {
		this.result = result;
	}

	
	
	

}
