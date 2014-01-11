package pacman.rule;

import gameframework.base.MoveStrategyRandom;
import gameframework.base.ObservableValue;
import gameframework.base.Overlap;
import gameframework.game.GameMovableDriverDefaultImpl;
import gameframework.game.GameUniverse;
import gameframework.game.OverlapRulesApplierDefaultImpl;

import java.awt.Point;
import java.util.Random;
import java.util.Vector;

import pacman.entity.AbstractBonus;
import pacman.entity.Enemy;
import pacman.entity.Hero;
import pacman.entity.Jail;
import pacman.entity.Heart;
import pacman.entity.SuperHeart;
import pacman.entity.TeleportPairOfPoints;

public class PacmanOverlapRules extends OverlapRulesApplierDefaultImpl {
	protected GameUniverse universe;
	protected Vector<Enemy> vEnemy = new Vector<Enemy>();

	protected Point pacManStartPos;
	protected Point ghostStartPos;
	private final ObservableValue<Integer> score;
	private final ObservableValue<Integer> life;
	private final ObservableValue<Boolean> endOfGame;
	private int totalNbGums = 0;
	private int nbEatenGums = 0;

	public PacmanOverlapRules(Point pacPos, Point gPos,
			ObservableValue<Integer> life, ObservableValue<Integer> score,
			ObservableValue<Boolean> endOfGame) {
		pacManStartPos = (Point) pacPos.clone();
		ghostStartPos = (Point) gPos.clone();
		this.life = life;
		this.score = score;
		this.endOfGame = endOfGame;
	}

	public void setUniverse(GameUniverse universe) {
		this.universe = universe;
	}


	public void addEnemy(Enemy enemy) {
		vEnemy.addElement(enemy);
	}

	@Override
	public void applyOverlapRules(Vector<Overlap> overlappables) {
		super.applyOverlapRules(overlappables);
	}	

	public void overlapRule(Enemy e, SuperHeart spg) {
	}

	public void overlapRule(Enemy e, AbstractBonus spg) {
	}

	public void overlapRule(Enemy e, TeleportPairOfPoints teleport) {
		e.setPosition(teleport.getDestination());
	}


	public void overlapRule(Enemy e, Jail jail) {
		if (!e.isActive()) {
			e.setAlive(true);
			MoveStrategyRandom strat = new MoveStrategyRandom();
			GameMovableDriverDefaultImpl ghostDriv = (GameMovableDriverDefaultImpl) e
					.getDriver();
			ghostDriv.setStrategy(strat);
			e.setPosition(ghostStartPos);
		}
	}


	/*
	 * Ici le h�ro rencontre un bonus vie qui lui redonne de la vie (que � lui)
	 * 
	 */
	public void overlapRule (Hero hero, Enemy enemy){
		Random random = new Random();
		boolean isHeroFirst;
		while (hero.getHealthPointUnit() > 0 && enemy.getHealthPointUnit() > 0){
			// On choisit aléatoirement si notre héro ou l'ennemi commence à attaquer
			isHeroFirst = random.nextBoolean();
			if (isHeroFirst){
				
				enemy.parry(hero.strike());
				hero.parry(enemy.strike());
			}else{
				hero.parry(enemy.strike());
				enemy.parry(hero.strike());
			}
			System.out.println("Vie du Héro : " + hero.getHealthPointUnit() + "/" + hero.getMaxHealthPointUnit()+"\n");
			System.out.println("Vie de l'Ennemi : " + enemy.getHealthPointUnit() + "/" + enemy.getMaxHealthPointUnit()+"\n");
		}
		if (hero.getHealthPointUnit() > 0){
			System.out.println("le héro "+ hero.strike());
			enemy.heal();
			hero.addEntity(enemy);
			universe.removeGameEntity(enemy);
			System.out.println("l'enemy est ajouté au héro "+ hero.strike());
		}else{
			universe.removeGameEntity(hero);
		}
			
	}
		
	
	public void overlapRule (Hero hero, Heart heart){
		//TODO : d�finir (pour le moment 50) le nombre de HP � rajouter
		if (hero.getMaxHealthPointUnit() - hero.getHealthPointUnit() <= 50){
			 hero.addHealthPoint(hero.getMaxHealthPointUnit() - hero.getHealthPointUnit());
		}
		else{
			 hero.addHealthPoint(50);
		}
		System.out.println(hero.getHealthPointUnit() + " Hp / "+hero.getMaxHealthPointUnit() + " Hp\n");
		universe.removeGameEntity(heart);
	}
	
	public void overlapRule (Hero h, SuperHeart spg){
		h.addHealthPoint(-30);
		System.out.println(h.getHealthPointUnit() + " Hp / "+h.getMaxHealthPointUnit() + " Hp\n");
		universe.removeGameEntity(spg);
	}

	private void pacgumEatenHandler() {
		nbEatenGums++;
		if (nbEatenGums >= totalNbGums) {
			endOfGame.setValue(true);
		}
	}
}
