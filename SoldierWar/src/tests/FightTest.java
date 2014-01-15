package tests;

import gameframework.base.ObservableValue;
import gameframework.game.GameDefaultImpl;
import gameframework.game.GameUniverse;
import gameframework.game.GameUniverseDefaultImpl;
import gameframework.game.MoveBlockerChecker;
import gameframework.game.MoveBlockerCheckerDefaultImpl;
import gameframework.game.OverlapProcessor;
import gameframework.game.OverlapProcessorDefaultImpl;

import java.awt.Canvas;
import java.awt.Point;
import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import utils.AgeFactory;
import utils.MiddleAgeFactory;
import zelda.entity.Enemy;
import zelda.entity.Hero;
import zelda.rule.ZeldaMoveBlockers;
import zelda.rule.ZeldaOverlapRules;

public class FightTest {
	private Hero hero;
	private Enemy enemy;
	private static AgeFactory factory;
	private static Canvas canvas;
	private static GameUniverse universe;
	private static ZeldaOverlapRules overlapRules;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		/*Création du héro*/
		factory = new MiddleAgeFactory();
		
		GameDefaultImpl g = new GameDefaultImpl();
		canvas = g.getCanvas();
		
		OverlapProcessor overlapProcessor = new OverlapProcessorDefaultImpl();

		MoveBlockerChecker moveBlockerChecker = new MoveBlockerCheckerDefaultImpl();
		moveBlockerChecker.setMoveBlockerRules(new ZeldaMoveBlockers());
		
		overlapRules = new ZeldaOverlapRules(new Point(0,0),
				new Point(0,0), new ObservableValue<Integer>(200), new ObservableValue<Integer>(0), new ObservableValue<Boolean>(false), null);
		overlapProcessor.setOverlapRules(overlapRules);

		universe = new GameUniverseDefaultImpl(moveBlockerChecker, overlapProcessor);
		overlapRules.setUniverse(universe);

	}

	@Before
	public void setUpEntities(){
		hero = new Hero(canvas, factory, "Super", "link", 200, 20);
		enemy = new Enemy(canvas, factory, "Simple", "toto");
	}
	
	
	/**
	 * Test si l'un des deux personnage est bien tué à la fin de la bataille
	 * et l'autre à encore de la vie
	 */
	@Test
	public void overlapRuleHeroVsEnemyTest() {
		
		Random random = new Random();
		boolean isHeroFirst;
		while (hero.getHealthPointsHero() > 0 && enemy.getHealthPointsUnit() > 0){
			// On choisit aléatoirement si notre héros ou l'ennemi commence à attaquer
			isHeroFirst = random.nextBoolean();
			if (isHeroFirst){
				enemy.parry(hero.strike());
				hero.parry(enemy.strike());
			}else{
				hero.parry(enemy.strike());
				enemy.parry(hero.strike());
			}
			System.out.println("Vie du Héro : " + hero.getHealthPointsHero() + "/" + hero.getMaxHealthPointsHero()+"\n");
			System.out.println("Vie de l'Ennemi : " + enemy.getHealthPointsUnit() + "/" + enemy.getMaxHealthPointUnit()+"\n");
		}
		System.out.println("Vie du Héro final : " + hero.getHealthPointsHero() + "/" + hero.getMaxHealthPointsHero()+"\n");
		System.out.println("Vie de l'Ennemi final: " + enemy.getHealthPointsUnit() + "/" + enemy.getMaxHealthPointUnit()+"\n");
		if (!(hero.getHealthPointsHero() == 0 && enemy.getHealthPointsUnit() >= 0) && !(hero.getHealthPointsHero() >= 0 && enemy.getHealthPointsUnit() == 0)){
			Assert.fail("Le résultat du combat n'est pas correct");
		}
	}	
	

	
	
	/**
	 * Test l'ajout de l'ennemi battu au combat au hero
	 */
	@Test
	public void addEnemyToHeroTest(){
		Hero hero = new Hero(canvas, factory, "Super", "link", 200, 20);
		Assert.assertEquals(20, hero.strike(), 0.5);
		Float enemyStrength = enemy.strike();
		Assert.assertEquals(15, enemyStrength, 0.5);
		overlapRules.overlapRule(hero, enemy);
		// on mémorise le nombre de points de vie du héros
		float heroPV = hero.getHealthPointsHero();
		// La force de l'ennemi viens s'ajouter à celle du héros
		Assert.assertEquals(35, hero.strike(), 0.5);
		// l'ennemi est régénéré aprés le combat pour être ajouté a l'armée du héros
		Assert.assertEquals(100, enemy.getMaxHealthPointUnit(), 0.5);
		Assert.assertEquals(100, enemy.getHealthPointsUnit(), 0.5);
		// La vie totale est celle que possédais le hero + la vie de base de l'ennemi (100)
		Assert.assertEquals(heroPV+100, hero.getHealthPointsUnit(), 0.5);
		

	}

	@Test
	public void getHealthPointsTest(){
		Assert.assertEquals(200, hero.getHealthPointsUnit(), 0.5);
		Assert.assertEquals(200, hero.getHealthPointsHero(), 0.5);
		hero.addEntity(enemy);
		Assert.assertEquals(300, hero.getHealthPointsUnit(), 0.5);
		Assert.assertEquals(200, hero.getHealthPointsHero(), 0.5);
	}
}
