package model.pieces.sidekicks;

import model.game.Direction;
import model.game.Game;
import exceptions.UnallowedMovementException;

public class SideKickP1 extends SideKick {

	public SideKickP1(Game game, String name) {
		super(game.getPlayer1(), game, name);
	}

	@Override
	public void moveDown() throws UnallowedMovementException {
		throw new UnallowedMovementException("your SideKick is not allowed to move in this direction",this, Direction.DOWN);
	}

	@Override
	public void moveDownRight() throws UnallowedMovementException {
		throw new UnallowedMovementException("your SideKick is not allowed to move in this direction",this, Direction.DOWNRIGHT);
	}

	@Override
	public void moveDownLeft() throws UnallowedMovementException {
		throw new UnallowedMovementException("your SideKick is not allowed to move in this direction",this, Direction.DOWNLEFT);
	}
}
