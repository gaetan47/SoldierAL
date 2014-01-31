package zelda.rule;

import gameframework.base.MoveStrategyRandom;
import gameframework.base.ObservableValue;
import gameframework.base.Overlap;
import gameframework.game.GameLevelDefaultImpl;
import gameframework.game.GameMovableDriverDefaultImpl;
import gameframework.game.GameUniverse;
import gameframework.game.OverlapRulesApplierDefaultImpl;

import java.awt.Point;
import java.util.Random;
import java.util.Vector;

import zelda.entity.AbstractBonus;
import zelda.entity.Boss;
import zelda.entity.Enemy;
import zelda.entity.Heart;
import zelda.entity.Hero;
import zelda.entity.Jail;
import zelda.entity.Shield;
import zelda.entity.BadHeart;
import zelda.entity.Sword;
import zelda.entity.TeleportPairOfPoints;

public class ZeldaOverlapRules extends OverlapRulesApplierDefaultImpl {
	protected GameUniverse universe;
	protected Vector<Enemy> vEnemy = new Vector<Enemy>();

	protected Point pacManStartPos;
	protected Point ghostStartPos;
	@SuppressWarnings("unused")
	private final ObservableValue<Integer> score;
	@SuppressWarnings("unused")
	private final ObservableValue<Integer> life;
	private final ObservableValue<Boolean> endOfGame;
	protected boolean managePacmanDeath;
	private GameLevelDefaultImpl game;

	public ZeldaOverlapRules(Point pacPos, Point gPos,
			ObservableValue<Integer> life, ObservableValue<Integer> score,
			ObservableValue<Boolean> endOfGame, GameLevelDefaultImpl game) {
		pacManStartPos = (Point) pacPos.clone();
		ghostStartPos = (Point) gPos.clone();
		this.life = life;
		this.score = score;
		this.endOfGame = endOfGame;
		this.game = game;
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

	public void overlapRule(Enemy e, BadHeart spg) {
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
			System.out.println(hero.getTotalStrength());
		}else{
			universe.removeGameEntity(hero);

			hero.youLoose();
			game.end();
			
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

			hero.youWin();
		}else{
			universe.removeGameEntity(hero);
			endOfGame.setValue(true);

			hero.youLoose();
		}
		game.end();

	}


	public void overlapRule (Hero hero, Heart heart){
		Random random = new Random();
		// Random number between 50 and 200
		int renderHealthPoints = random.nextInt(150) + 50; 
		if (hero.getMaxHealthPointsHero() - hero.getHealthPointsHero() <= renderHealthPoints){
			hero.addHealthPointsHero(hero.getMaxHealthPointsHero() - hero.getHealthPointsHero());
		}
		else{
			hero.addHealthPointsHero(renderHealthPoints);
		}
		universe.removeGameEntity(heart);
	}

	public void overlapRule (Hero h, BadHeart spg){
		Random random = new Random();
		// Random number between 50 and 200
		int takenHealthPoints = random.nextInt(150) + 50; 
		h.addHealthPointsHero(-takenHealthPoints);
		universe.removeGameEntity(spg);
	}

	public void overlapRule (Hero hero, Sword sword){
		System.out.println(hero.getTotalStrength());

		System.out.println("attaque du hero avant: "+hero.strike());
		hero.addEquipment("Offensive");
		System.out.println("attaque du hero après: "+hero.strike());
		System.out.println(hero.getTotalStrength());
		System.out.println(hero.getSwordStrength());


		universe.removeGameEntity(sword);
	}

	public void overlapRule (Hero hero, Shield shield){
		System.out.println("c'etait un bouclier");
		hero.addEquipment("Defensive");
		universe.removeGameEntity(shield);
	}
}
