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
import pacman.entity.Boss;
import pacman.entity.Enemy;
import pacman.entity.Hero;
import pacman.entity.Jail;
import pacman.entity.Heart;
import pacman.entity.Shield;
import pacman.entity.SuperHeart;
import pacman.entity.Sword;
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
	protected boolean managePacmanDeath;

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
		managePacmanDeath = true;
		super.applyOverlapRules(overlappables);
	}	

	public void overlapRule(Enemy e, SuperHeart spg) {
	}

	public void overlapRule(Enemy e, AbstractBonus spg) {
	}

	/* Useless car les enemis de bougent pas
	public void overlapRule(Enemy e, TeleportPairOfPoints teleport) {
		e.setPosition(teleport.getDestination());
	}*/

	public void overlapRule(Hero hero, TeleportPairOfPoints teleport) {
		hero.setPosition(teleport.getDestination());
	}

	public void overlapRule(Boss boss, TeleportPairOfPoints teleport) {
		boss.setPosition(teleport.getDestination());
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
	 * Ici le héro rencontre un bonus vie qui lui redonne de la vie (que à lui)
	 * 
	 */
	public void overlapRule (Hero hero, Enemy enemy){
		Random random = new Random();
		boolean isHeroFirst;
		while (hero.getHealthPointsUnit() > 0 && enemy.getHealthPointsUnit() > 0){
			// On choisit aléatoirement si notre héro ou l'ennemi commence à attaquer
			isHeroFirst = random.nextBoolean();
			if (isHeroFirst){

				enemy.parry(hero.strike());
				hero.parry(enemy.strike());
			}else{
				hero.parry(enemy.strike());
				enemy.parry(hero.strike());
			}
		
		}
		if (hero.getHealthPointsUnit() > 0){
			
			enemy.heal();
			hero.addEntity(enemy);
			universe.removeGameEntity(enemy);
		}else{
			universe.removeGameEntity(hero);
			// TODO: display "You lose"
		}

	}
	
	public void overlapRule (Hero hero, Boss boss){
		Random random = new Random();
		boolean isHeroFirst;
		while (hero.getHealthPointsUnit() > 0 && boss.getHealthPointsUnit() > 0){
			// On choisit aléatoirement si notre héro ou l'ennemi commence à attaquer
			isHeroFirst = random.nextBoolean();
			if (isHeroFirst){

				boss.parry(hero.strike());
				hero.parry(boss.strike());
			}else{
				hero.parry(boss.strike());
				boss.parry(hero.strike());
			}
		
		}
		if (hero.getHealthPointsUnit() > 0){
			universe.removeGameEntity(boss);
			// TODO: display "You win"
		}else{
			universe.removeGameEntity(hero);
			// TODO: display "You lose"
		}

	}


	public void overlapRule (Hero hero, Heart heart){
		//TODO : définir (pour le moment 50) le nombre de HP à rajouter
		if (hero.getMaxHealthPointsHero() - hero.getHealthPointsHero() <= 50){
			hero.addHealthPointsHero(hero.getMaxHealthPointsHero() - hero.getHealthPointsHero());
		}
		else{
			hero.addHealthPointsHero(50);
		}
		universe.removeGameEntity(heart);
	}

	public void overlapRule (Hero h, SuperHeart spg){
		h.addHealthPointsHero(-30);
		universe.removeGameEntity(spg);
	}

	public void overlapRule (Hero hero, Sword sword){
		System.out.println("attaque du hero avant: "+hero.strike());
		hero.addEquipment("Offensive");
		System.out.println("attaque du hero après: "+hero.strike());
		universe.removeGameEntity(sword);
	}

	public void overlapRule (Hero hero, Shield shield){
		System.out.println("c'etait un bouclier");
		hero.addEquipment("Defensive");
		universe.removeGameEntity(shield);
	}
}
