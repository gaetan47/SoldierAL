package zelda.rule;

import gameframework.game.IllegalMoveException;
import gameframework.game.MoveBlockerRulesApplierDefaultImpl;
import zelda.entity.Boss;
import zelda.entity.Wall;

public class ZeldaMoveBlockers extends MoveBlockerRulesApplierDefaultImpl {

	public void moveBlockerRule(Boss b, Wall w) throws IllegalMoveException {
		// The default case is when a ghost is active and not able to cross a
		// wall
		if (b.isActive()) {
			throw new IllegalMoveException();
			// When a boss is not active, it is able to cross a wall
		}
	}
}
