package pacman.rule;

import gameframework.base.MoveStrategyRandom;
import gameframework.base.ObservableValue;
import gameframework.base.Overlap;
import gameframework.game.GameMovableDriverDefaultImpl;
import gameframework.game.GameUniverse;
import gameframework.game.OverlapRulesApplierDefaultImpl;

import java.awt.Point;
import java.util.Vector;

import pacman.entity.Enemy;
import pacman.entity.Hero;
import pacman.entity.Jail;
import pacman.entity.Pacgum;
import pacman.entity.Pacman;
import pacman.entity.SuperPacgum;
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

	public void overlapRule(Pacman p, Enemy e) {
		/*if (!p.isVulnerable()) {
			if (g.isActive()) {
				g.setAlive(false);
				MoveStrategyStraightLine strat = new MoveStrategyStraightLine(
						g.getPosition(), ghostStartPos);
				GameMovableDriverDefaultImpl ghostDriv = (GameMovableDriverDefaultImpl) g
						.getDriver();
				ghostDriv.setStrategy(strat);

			}*/
		if (e.isActive()) {
			
			System.out.println("Collision !!");
		}
		
	}
	

	

	public void overlapRule(Enemy e, SuperPacgum spg) {
	}

	public void overlapRule(Enemy e, Pacgum spg) {
	}

	public void overlapRule(Enemy e, TeleportPairOfPoints teleport) {
		e.setPosition(teleport.getDestination());
	}

	public void overlapRule(Pacman p, TeleportPairOfPoints teleport) {
		p.setPosition(teleport.getDestination());
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

	public void overlapRule(Pacman p, SuperPacgum spg) {
		score.setValue(score.getValue() + 5);
		universe.removeGameEntity(spg);
		pacgumEatenHandler();
		// On supprime l'appeurement des méchants
	}

	/*
	 * Ici le h�ro rencontre un bonus vie qui lui redonne de la vie (que � lui)
	 * 
	 */
	
	
	public void overlapRule (Hero h, Pacgum pg){
		//TODO : d�finir (pour le moment 50) le nombre de HP � rajouter
		if (h.getMaxHealthPointUnit() - h.getHealthPointUnit() <= 50){
			 h.addHealthPoint(h.getMaxHealthPointUnit() - h.getHealthPointUnit());
		}
		else{
			 h.addHealthPoint(50);
		}
		System.out.println(h.getHealthPointUnit() + " Hp / "+h.getMaxHealthPointUnit() + " Hp\n");
		universe.removeGameEntity(pg);
	}
	
	public void overlapRule (Hero h, SuperPacgum spg){
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
