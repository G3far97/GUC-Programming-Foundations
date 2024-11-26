package model.pieces.sidekicks;

import exceptions.OccupiedCellException;
import exceptions.UnallowedMovementException;
import exceptions.WrongTurnException;
import model.game.Direction;
import model.game.Game;

public class SideKickP2 extends SideKick {

	public SideKickP2(Game game, String name) {
		super(game.getPlayer2(), game, name);
	}
	@Override
	public void move(Direction r) throws UnallowedMovementException, OccupiedCellException, WrongTurnException {
		super.move(r);
		if(this.demandedPiece(this, r)==null) {
			switch(r) {
			case DOWN : moveDown();this.getGame().switchTurns();break;
			case DOWNLEFT : moveDownLeft();this.getGame().switchTurns();break;
			case DOWNRIGHT : moveDownRight();this.getGame().switchTurns();break;
			case LEFT : moveLeft();this.getGame().switchTurns();break;
			case RIGHT : moveRight();this.getGame().switchTurns();break;
			default : throw new UnallowedMovementException(this,r);
			
			}
		}
		else if(!((this.demandedPiece(this, r).isFriendly()))) {
			switch(r) {
			case DOWN : moveDown();this.attack(this.demandedPiece(this, r));this.getGame().switchTurns();break;
			case DOWNLEFT : moveDownLeft();this.attack(this.demandedPiece(this, r));this.getGame().switchTurns();break;
			case DOWNRIGHT : moveDownRight();this.attack(this.demandedPiece(this, r));this.getGame().switchTurns();break;
			case LEFT : moveLeft();this.attack(this.demandedPiece(this, r));this.getGame().switchTurns();break;
			case RIGHT : moveRight();this.attack(this.demandedPiece(this, r));this.getGame().switchTurns();break;
			default : throw new UnallowedMovementException(this,r);
			}
		}
		else {
			throw new OccupiedCellException(this,r);
		}
	}
}
