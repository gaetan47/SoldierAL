package zelda;

import gameframework.base.MoveStrategyKeyboard;
import gameframework.base.MoveStrategyRandom;
import gameframework.base.ObservableValue;
import gameframework.game.CanvasDefaultImpl;
import gameframework.game.Game;
import gameframework.game.GameLevelDefaultImpl;
import gameframework.game.GameMovableDriverDefaultImpl;
import gameframework.game.GameUniverseDefaultImpl;
import gameframework.game.GameUniverseViewPortDefaultImpl;
import gameframework.game.MoveBlockerChecker;
import gameframework.game.MoveBlockerCheckerDefaultImpl;
import gameframework.game.OverlapProcessor;
import gameframework.game.OverlapProcessorDefaultImpl;

import java.awt.Canvas;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import utils.AgeFactory;
import utils.MiddleAgeFactory;
import zelda.entity.Boss;
import zelda.entity.Enemy;
import zelda.entity.Heart;
import zelda.entity.Hero;
import zelda.entity.Jail;
import zelda.entity.Shield;
import zelda.entity.SuperHeart;
import zelda.entity.Sword;
import zelda.entity.TeleportPairOfPoints;
import zelda.entity.Wall;
import zelda.rule.BossMovableDriver;
import zelda.rule.ZeldaMoveBlockers;
import zelda.rule.ZeldaOverlapRules;

public class GameLevelOne extends GameLevelDefaultImpl {
	Canvas canvas;


	// 0 : Pacgums; 1 : Walls; 2 : SuperPacgums; 3 : Doors; 4 : Jail; 5 : empty
	// Note: teleportation points are not indicated since they are defined by
	// directed pairs of positions.
	static int[][] tab = { 
		    { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1, 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1 },
			{ 1, 5, 1, 1, 1, 1, 5, 1, 1, 1, 1, 1, 5, 1, 1, 5, 1, 1, 1, 1, 1, 5, 1, 1, 1, 1, 2, 1 },
			{ 1, 5, 1, 1, 1, 1, 5, 1, 1, 1, 1, 1, 5, 1, 1, 5, 1, 1, 1, 1, 1, 5, 1, 1, 1, 1, 5, 1 },
			{ 1, 2, 1, 1, 1, 1, 5, 1, 1, 1, 1, 1, 5, 1, 1, 5, 1, 1, 1, 1, 1, 5, 1, 1, 1, 1, 5, 1 },
			{ 1, 5, 5, 5, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 5, 1 },
			{ 1, 5, 1, 1, 1, 1, 5, 1, 1, 5, 1, 1, 1, 1, 1, 1, 1, 1, 5, 1, 1, 5, 1, 1, 1, 1, 5, 1 },
			{ 1, 5, 1, 1, 1, 1, 5, 1, 1, 5, 1, 1, 1, 1, 1, 1, 1, 1, 5, 1, 1, 5, 1, 1, 1, 1, 5, 1 },
			{ 1, 5, 5, 5, 5, 5, 5, 1, 1, 5, 5, 5, 5, 1, 1, 5, 5, 5, 5, 1, 1, 7, 5, 5, 5, 5, 5, 1 },
			{ 1, 1, 1, 1, 1, 1, 5, 1, 1, 1, 1, 1, 5, 1, 1, 5, 1, 1, 1, 1, 1, 5, 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 1, 5, 1, 1, 1, 1, 1, 5, 1, 1, 5, 1, 1, 1, 1, 1, 5, 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 1, 5, 1, 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1, 1, 5, 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 1, 5, 1, 1, 5, 1, 1, 1, 3, 3, 1, 1, 1, 5, 1, 1, 5, 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 1, 5, 1, 1, 5, 1, 4, 4, 4, 4, 4, 4, 1, 5, 1, 1, 5, 1, 1, 1, 1, 1, 1 },
			{ 5, 6, 5, 5, 5, 5, 5, 5, 5, 5, 1, 4, 4, 4, 4, 4, 4, 1, 5, 5, 5, 5, 5, 0, 5, 5, 5, 5 },
			{ 1, 1, 1, 1, 1, 1, 5, 1, 1, 5, 1, 4, 4, 4, 4, 4, 4, 1, 5, 1, 1, 5, 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 1, 5, 1, 1, 5, 1, 1, 1, 1, 1, 1, 1, 1, 5, 1, 1, 5, 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 1, 5, 1, 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1, 1, 5, 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 1, 5, 1, 1, 5, 1, 1, 1, 1, 1, 1, 1, 1, 5, 1, 1, 5, 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 1, 5, 1, 1, 5, 1, 1, 1, 1, 1, 1, 1, 1, 5, 1, 1, 5, 1, 1, 1, 1, 1, 1 },
			{ 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1, 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1 },
			{ 1, 5, 1, 1, 1, 1, 5, 1, 1, 1, 1, 1, 5, 1, 1, 5, 1, 1, 1, 1, 1, 5, 1, 1, 1, 1, 5, 1 },
			{ 1, 5, 1, 1, 1, 1, 5, 1, 1, 1, 1, 1, 5, 1, 1, 5, 1, 1, 1, 1, 1, 5, 1, 1, 1, 1, 5, 1 },
			{ 1, 2, 5, 5, 1, 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1, 1, 5, 5, 2, 1 },
			{ 1, 1, 1, 5, 1, 1, 5, 1, 1, 5, 1, 1, 1, 1, 1, 1, 1, 1, 5, 1, 1, 0, 1, 1, 5, 1, 1, 1 },
			{ 1, 1, 1, 5, 1, 1, 5, 1, 1, 5, 1, 1, 1, 1, 1, 1, 1, 1, 5, 1, 1, 5, 1, 1, 5, 1, 1, 1 },
			{ 1, 5, 5, 5, 5, 5, 5, 1, 1, 6, 5, 5, 5, 1, 1, 5, 5, 5, 5, 1, 1, 5, 7, 5, 5, 5, 5, 1 },
			{ 1, 5, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 5, 1, 1, 5, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 5, 1 },
			{ 1, 5, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 5, 1, 1, 5, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 5, 1 },
			{ 1, 5, 5, 5, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } };

	public static final int SPRITE_SIZE = 16;
	public static final int NUMBER_OF_ENEMIES = 6;
	public static int HERO_LIFE = 1000;
	public static int HERO_STRENGTH = 50;
	public static int BOSS_LIFE = 2000;
	public static int BOSS_STRENGTH = 200;

	@Override
	protected void init() {
		OverlapProcessor overlapProcessor = new OverlapProcessorDefaultImpl();

		MoveBlockerChecker moveBlockerChecker = new MoveBlockerCheckerDefaultImpl();
		moveBlockerChecker.setMoveBlockerRules(new ZeldaMoveBlockers());
		
		// TODO : règles à initialiser ici
		ZeldaOverlapRules overlapRules = new ZeldaOverlapRules(new Point(14 * SPRITE_SIZE, 17 * SPRITE_SIZE),
				new Point(14 * SPRITE_SIZE, 15 * SPRITE_SIZE), new ObservableValue<Integer>(HERO_LIFE), score[0], endOfGame, this);
		overlapProcessor.setOverlapRules(overlapRules);

		universe = new GameUniverseDefaultImpl(moveBlockerChecker, overlapProcessor);
		overlapRules.setUniverse(universe);
		

		gameBoard = new GameUniverseViewPortDefaultImpl(canvas, universe);
		((CanvasDefaultImpl) canvas).setDrawingGameBoard(gameBoard);

		
		// Filling up the universe with basic non movable entities and inclusion in the universe
		for (int i = 0; i < 31; ++i) {
			for (int j = 0; j < 28; ++j) {
				if (tab[i][j] == 0) {
					universe.addGameEntity(new Heart(canvas, new Point(j * SPRITE_SIZE, i * SPRITE_SIZE)));
				}
				if (tab[i][j] == 1) {
					universe.addGameEntity(new Wall(canvas, j * SPRITE_SIZE, i * SPRITE_SIZE));
				}
				if (tab[i][j] == 2) {
					universe.addGameEntity(new SuperHeart(canvas, new Point(j * SPRITE_SIZE, i * SPRITE_SIZE)));
				}
				if (tab[i][j] == 4) {
					universe.addGameEntity(new Jail(new Point(j * SPRITE_SIZE, i * SPRITE_SIZE)));
				}
				if (tab[i][j] == 6) {
					universe.addGameEntity(new Sword(canvas, new Point(j * SPRITE_SIZE, i * SPRITE_SIZE)));
				}
				if (tab[i][j] == 7) {
					universe.addGameEntity(new Shield(canvas, new Point(j * SPRITE_SIZE, i * SPRITE_SIZE)));
				}
			}
		}

		// Two teleport points definition and inclusion in the universe
		// (west side to east side)
		universe.addGameEntity(new TeleportPairOfPoints(new Point(0 * SPRITE_SIZE, 14 * SPRITE_SIZE), new Point(
				25 * SPRITE_SIZE, 14 * SPRITE_SIZE)));
		// (east side to west side)
		universe.addGameEntity(new TeleportPairOfPoints(new Point(27 * SPRITE_SIZE, 14 * SPRITE_SIZE), new Point(
				2 * SPRITE_SIZE, 14 * SPRITE_SIZE)));
		
		
		
		/* Création du héro */
		AgeFactory age = new MiddleAgeFactory();
		Hero myHero = new Hero(canvas, age, "Super", "Link", HERO_LIFE, HERO_STRENGTH);
		
		GameMovableDriverDefaultImpl pacDriver = new GameMovableDriverDefaultImpl();
		MoveStrategyKeyboard keyStr = new MoveStrategyKeyboard();
		pacDriver.setStrategy(keyStr);
		pacDriver.setmoveBlockerChecker(moveBlockerChecker);
		canvas.addKeyListener(keyStr);
		myHero.setDriver(pacDriver);
		myHero.setPosition(new Point(14 * SPRITE_SIZE, 17 * SPRITE_SIZE));
		universe.addGameEntity(myHero);
		GameDetailsFrame frame = new GameDetailsFrame(myHero.getHealthPointsHero(), myHero.getMaxHealthPointUnit(), myHero.strike());
		myHero.addObserver(new HeroObserver(frame, myHero));

		
		/* Création du boss */		
		Boss boss = new Boss(canvas, age, "Super", "Ganondorf", BOSS_LIFE, BOSS_STRENGTH);
		GameMovableDriverDefaultImpl bossDriv = new BossMovableDriver();
		MoveStrategyRandom ranStr = new MoveStrategyRandom();
		bossDriv.setStrategy(ranStr);
		bossDriv.setmoveBlockerChecker(moveBlockerChecker);
		((BossMovableDriver)bossDriv).setSpeedVectorSpeed(8);
		boss.setDriver(bossDriv);
		boss.setPosition(new Point(14 * SPRITE_SIZE, 15 * SPRITE_SIZE));
		universe.addGameEntity(boss);
		
		// Ghosts definition and inclusion in the universe
		List<Point> enemyPos = new ArrayList<Point>();
		enemyPos.add(new Point(SPRITE_SIZE, 2 * SPRITE_SIZE));
		enemyPos.add(new Point(15 * SPRITE_SIZE, SPRITE_SIZE));
		enemyPos.add(new Point(20 * SPRITE_SIZE, 29 * SPRITE_SIZE));
		enemyPos.add(new Point(6 * SPRITE_SIZE, 17 * SPRITE_SIZE));
		enemyPos.add(new Point(12 * SPRITE_SIZE, 5 * SPRITE_SIZE));
		enemyPos.add(new Point(18 * SPRITE_SIZE, 20 * SPRITE_SIZE));

		Enemy enemy;
		Random random = new Random();
		String soldierType;
		List<String> soldierTypeList = new ArrayList<String>();
		soldierTypeList.add("Simple");
		soldierTypeList.add("Complex");
		
		for (int t = 0; t < NUMBER_OF_ENEMIES; ++t) {
			if (t >= enemyPos.size())
				break;
			
			// On choisit le type de soldat aléatoirement
			soldierType = soldierTypeList.get(random.nextInt(soldierTypeList.size()));
			if (t >= 2){ // on crée une armée
				enemy = new Enemy(canvas, age, soldierType, "totoArmy"+t+"x3", 3);
				
			}else{
				enemy = new Enemy(canvas, age, soldierType, "toto"+t+"x1", 1);
			}
			
			enemy.setPosition(enemyPos.get(t));
			universe.addGameEntity(enemy);
			(overlapRules).addEnemy(enemy);
		}

	}

	public GameLevelOne(Game g) {
		super(g);
		canvas = g.getCanvas();
	}

}
