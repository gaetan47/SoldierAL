package tests;

import java.awt.Canvas;
import java.util.Random;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import pacman.entity.Enemy;
import pacman.entity.Hero;
import soldier.ArmedUnitSoldier;
import utils.AgeFactory;
import utils.MiddleAgeFactory;

public class FightTest {
	private static ArmedUnitSoldier hero;
	private static ArmedUnitSoldier enemy;
	static Canvas canvas;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		/*Création du héro*/
		AgeFactory factory = new MiddleAgeFactory();
		hero = new ArmedUnitSoldier(factory, "Simple", "link");
		enemy = new ArmedUnitSoldier(factory, "Simple", "toto");
	}

	/**
	 * Test si l'un des deux personnage est bien tué à la fin de la bataille
	 * et l'autre à encore de la vie
	 */
	@Test
	public void overlapRuleHeroVsEnemyTest() {
		
		
		Random random = new Random();
		boolean isHeroFirst;
		while (hero.getHealthPoints() > 0 && enemy.getHealthPoints() > 0){
			// On choisit aléatoirement si notre héro ou l'ennemi commence à attaquer
			isHeroFirst = random.nextBoolean();
			if (isHeroFirst){
				enemy.parry(hero.strike());
				hero.parry(enemy.strike());
			}else{
				hero.parry(enemy.strike());
				enemy.parry(hero.strike());
			}
			System.out.println("Vie du Héro : " + hero.getHealthPoints() + "/" + hero.getMaxHealthPoints()+"\n");
			System.out.println("Vie de l'Ennemi : " + enemy.getHealthPoints() + "/" + enemy.getMaxHealthPoints()+"\n");
		}
		System.out.println("Vie du Héro final : " + hero.getHealthPoints() + "/" + hero.getMaxHealthPoints()+"\n");
		System.out.println("Vie de l'Ennemi final: " + enemy.getHealthPoints() + "/" + enemy.getMaxHealthPoints()+"\n");
		if (!(hero.getHealthPoints() == 0 && enemy.getHealthPoints() >= 0) && !(hero.getHealthPoints() >= 0 && enemy.getHealthPoints() == 0)){
			Assert.fail("Le résultat du combat n'est pas correct");
		}
	}	
	
	@Test
	public void addEnemyToHeroTest(){
		
	}

}
