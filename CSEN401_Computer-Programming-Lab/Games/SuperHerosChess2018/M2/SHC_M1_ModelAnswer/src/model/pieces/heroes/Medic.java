package model.pieces.heroes;

import java.awt.Point;

import exceptions.InvalidPowerDirectionException;
import exceptions.InvalidPowerTargetException;
import exceptions.OccupiedCellException;
import exceptions.PowerAlreadyUsedException;
import exceptions.UnallowedMovementException;
import exceptions.WrongTurnException;
import model.game.Direction;
import model.game.Game;
import model.game.Player;
import model.pieces.Piece;

public class Medic extends ActivatablePowerHero {

	public Medic(Player player, Game game, String name) {
		super(player, game, name);
	}

	@Override
	public String toString() {
		return "M";
	}
	@Override
	public void move(Direction r) throws UnallowedMovementException, OccupiedCellException, WrongTurnException {
		super.move(r);
		if(this.demandedPiece(this, r)==null) {
			switch(r) {
			case DOWN : moveDown();this.getGame().switchTurns();break;
			case LEFT : moveLeft();this.getGame().switchTurns();break;
			case RIGHT : moveRight();this.getGame().switchTurns();break;
			case UP : moveUp();this.getGame().switchTurns();break;
			default : throw new UnallowedMovementException(this,r);
			}
		}
		else if(!((this.demandedPiece(this, r).isFriendly()))) {
			switch(r) {
			case DOWN : moveDown();this.attack(this.demandedPiece(this, r));this.getGame().switchTurns();break;
			case LEFT : moveLeft();this.attack(this.demandedPiece(this, r));this.getGame().switchTurns();break;
			case RIGHT : moveRight();this.attack(this.demandedPiece(this, r));this.getGame().switchTurns();break;
			case UP : moveUp();this.attack(this.demandedPiece(this, r));this.getGame().switchTurns();break;
			default : throw new UnallowedMovementException(this,r);
			}
		}
		else {
			throw new OccupiedCellException(this,r);
		}
	}
	public void usePower(Direction d, Piece target, Point newPos) throws PowerAlreadyUsedException, InvalidPowerDirectionException, WrongTurnException, InvalidPowerTargetException {
		super.usePower(d, target, null);
		if(!this.getOwner().getDeadCharacters().contains(target)) {
			throw new InvalidPowerTargetException("The target piece has not been eliminated before, so it cannot be revived",this,target);
		}
		else {
		if(target.isFriendly()) {
			if(this.getGame().getCellAt(target.getPosI(),target.getPosJ()).getPiece()==null) {
				switch(d) {
		case DOWN : this.getGame().getCellAt(this.demandedPiece(this, d).getPosI(),this.demandedPiece(this, d).getPosJ()).setPiece(target);break;
		case DOWNLEFT : this.getGame().getCellAt(this.demandedPiece(this, d).getPosI(),this.demandedPiece(this, d).getPosJ()).setPiece(target);break;
		case DOWNRIGHT : this.getGame().getCellAt(this.demandedPiece(this, d).getPosI(),this.demandedPiece(this, d).getPosJ()).setPiece(target);break;
		case LEFT : this.getGame().getCellAt(this.demandedPiece(this, d).getPosI(),this.demandedPiece(this, d).getPosJ()).setPiece(target);break;
		case RIGHT : this.getGame().getCellAt(this.demandedPiece(this, d).getPosI(),this.demandedPiece(this, d).getPosJ()).setPiece(target);break;
		case UP : this.getGame().getCellAt(this.demandedPiece(this, d).getPosI(),this.demandedPiece(this, d).getPosJ()).setPiece(target);break;
		case UPLEFT : this.getGame().getCellAt(this.demandedPiece(this, d).getPosI(),this.demandedPiece(this, d).getPosJ()).setPiece(target);break;
		case UPRIGHT : this.getGame().getCellAt(this.demandedPiece(this, d).getPosI(),this.demandedPiece(this, d).getPosJ()).setPiece(target);break;
		}
		}
			else {
				throw new InvalidPowerTargetException("The target location is occupied",this,target);

			}
	}
		else {
			throw new InvalidPowerTargetException("The target piece belongs to the enemy team",this,target);
		}
	}
	}
}
