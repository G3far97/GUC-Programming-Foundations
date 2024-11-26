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

public class Tech extends ActivatablePowerHero {

	public Tech(Player player, Game game, String name) {
		super(player, game, name);
	}

	@Override
	public String toString() {
		return "T";
	}
	@Override
	public void move(Direction r) throws UnallowedMovementException, OccupiedCellException, WrongTurnException {
		super.move(r);
		if(this.demandedPiece(this, r)==null) {
			switch(r) {
			case DOWNLEFT : moveDownLeft();this.getGame().switchTurns();break;
			case DOWNRIGHT : moveDownRight();this.getGame().switchTurns();break;
			case UPLEFT : moveUpLeft();this.getGame().switchTurns();break;
			case UPRIGHT : moveUpRight();this.getGame().switchTurns();break;
			default : throw new UnallowedMovementException(this,r);
			}
		}
		else if(!((this.demandedPiece(this, r).isFriendly()))) {
			switch(r) {
			case DOWNLEFT : moveDownLeft();this.attack(this.demandedPiece(this, r));this.getGame().switchTurns();break;
			case DOWNRIGHT : moveDownRight();this.attack(this.demandedPiece(this, r));this.getGame().switchTurns();break;
			case UPLEFT : moveUpLeft();this.attack(this.demandedPiece(this, r));this.getGame().switchTurns();break;
			case UPRIGHT : moveUpRight();this.attack(this.demandedPiece(this, r));this.getGame().switchTurns();break;
			default : throw new UnallowedMovementException(this,r);
			}
		}
		else {
			throw new OccupiedCellException(this,r);
		}
	}
	public void usePower(Direction d, Piece target, Point newPos) throws PowerAlreadyUsedException, InvalidPowerDirectionException, WrongTurnException, InvalidPowerTargetException{
		super.usePower(d, target, newPos);
		if(!target.isFriendly()) {
			if(newPos == null) {
			if(target instanceof ActivatablePowerHero) {
				if(((ActivatablePowerHero) target).isPowerUsed() == false) {
					((ActivatablePowerHero) target).setPowerUsed(true);
				}
				else {
					throw new InvalidPowerTargetException("The enemy has already used its power and cannot be hacked",this,target);
				}
			}
			else if(target instanceof Armored) {
				if(((Armored) target).isArmorUp()==true) {
					((Armored) target).setArmorUp(false);
				}
				else {
					throw new InvalidPowerTargetException("The enemy has already used its power and cannot be hacked",this,target);
				}
			}
			else {
				throw new InvalidPowerTargetException("The enemy has already used its power and cannot be hacked",this,target);
			}
		}
			else {
				throw new InvalidPowerTargetException("The target piece doesn't belong to the same team",this,target);
			}
		}
		else {
			if(newPos == null) {
			if(target instanceof ActivatablePowerHero) {
				if(((ActivatablePowerHero) target).isPowerUsed() == true) {
					((ActivatablePowerHero) target).setPowerUsed(false);
				}
				else {
					throw new InvalidPowerTargetException("The target piece did not use its power yet",this,target);

				}
			}
			else if(target instanceof Armored) {
				if(((Armored) target).isArmorUp()==false) {
					((Armored) target).setArmorUp(true);
				}
				else {
					throw new InvalidPowerTargetException("The target piece did not use its power yet",this,target);
				}
			}
			else {
				throw new InvalidPowerTargetException("Non Activatable Power Hero",this,target);

			}
		}
			else {
				if(this.getGame().getCellAt(newPos.x,newPos.y)==null) {
					this.getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(null);
					this.getGame().getCellAt(newPos.x,newPos.y).setPiece(this);
				}
				else {
					throw new InvalidPowerTargetException("the target location is occupied",this,target);
				}
			}
	}
	}
	
}
