package model.pieces.sidekicks;

import exceptions.OccupiedCellException;
import exceptions.UnallowedMovementException;
import exceptions.WrongTurnException;
import model.game.Direction;
import model.game.Game;

public class SideKickP1 extends SideKick {

	public SideKickP1(Game game, String name) {
		super(game.getPlayer1(), game, name);
	}
	@Override
	public void move(Direction r) throws UnallowedMovementException, OccupiedCellException, WrongTurnException {
		super.move(r);
		if(this.demandedPiece(this, r)==null) {
			switch(r) {
			case LEFT : moveLeft();this.getGame().switchTurns();break;
			case RIGHT : moveRight();this.getGame().switchTurns();break;
			case UP : moveUp();this.getGame().switchTurns();break;
			case UPLEFT : moveUpLeft();this.getGame().switchTurns();break;
			case UPRIGHT : moveUpRight();this.getGame().switchTurns();break;
			default : throw new UnallowedMovementException(this,r);
			
			}
		}
		else if(!((this.demandedPiece(this, r).isFriendly()))) {
			switch(r) {
			case LEFT : moveLeft();this.attack(this.demandedPiece(this, r));this.getGame().switchTurns();break;
			case RIGHT : moveRight();this.attack(this.demandedPiece(this, r));this.getGame().switchTurns();break;
			case UP : moveUp();this.attack(this.demandedPiece(this, r));this.getGame().switchTurns();break;
			case UPLEFT : moveLeft();this.attack(this.demandedPiece(this, r));this.getGame().switchTurns();break;
			case UPRIGHT :  moveUpRight();this.attack(this.demandedPiece(this, r));this.getGame().switchTurns();break;
			default : throw new UnallowedMovementException(this,r);
			}
		}
		else {
			throw new OccupiedCellException(this,r);
		}
	}
}
