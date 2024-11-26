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

public class Super extends ActivatablePowerHero {

	public Super(Player player, Game game, String name) {
		super(player, game, name);
	}

	@Override
	public String toString() {
		return "P";
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
	public void usePower(Direction d, Piece target, Point newPos) throws PowerAlreadyUsedException, InvalidPowerDirectionException, WrongTurnException, InvalidPowerTargetException{
		super.usePower(d, null, null);
		switch(d) {
		case DOWN : if(this.getGame().getCellAt(this.getPosI()+2,this.getPosJ())!=null && !(this.getGame().getCellAt(this.getPosI()+2,this.getPosJ()).getPiece().isFriendly())) {
			this.attack(this.getGame().getCellAt(this.getPosI()+2,this.getPosJ()).getPiece());
			this.setPowerUsed(true);
			this.getGame().switchTurns();
			break;
		}
		else if(this.getGame().getCellAt(this.getPosI()+1,this.getPosJ())!=null && !(this.getGame().getCellAt(this.getPosI()+1,this.getPosJ()).getPiece().isFriendly())) {
			this.attack(this.getGame().getCellAt(this.getPosI()+1,this.getPosJ()).getPiece());
			this.setPowerUsed(true);
			this.getGame().switchTurns();
			break;
		}
		case LEFT : if(this.getGame().getCellAt(this.getPosI(),this.getPosJ()-2)!=null && !(this.getGame().getCellAt(this.getPosI(),this.getPosJ()-2).getPiece().isFriendly())) {
			this.attack(this.getGame().getCellAt(this.getPosI(),this.getPosJ()-2).getPiece());
			this.setPowerUsed(true);
			this.getGame().switchTurns();
			break;
		}
		else if(this.getGame().getCellAt(this.getPosI(),this.getPosJ()-1)!=null && !(this.getGame().getCellAt(this.getPosI(),this.getPosJ()-1).getPiece().isFriendly())) {
			this.attack(this.getGame().getCellAt(this.getPosI(),this.getPosJ()-1).getPiece());
			this.setPowerUsed(true);
			this.getGame().switchTurns();
			break;
		}
		case RIGHT : if(this.getGame().getCellAt(this.getPosI(),this.getPosJ()+2)!=null && !(this.getGame().getCellAt(this.getPosI(),this.getPosJ()+2).getPiece().isFriendly())) {
			this.attack(this.getGame().getCellAt(this.getPosI(),this.getPosJ()+2).getPiece());
			this.setPowerUsed(true);
			this.getGame().switchTurns();
			break;
		}
		else if(this.getGame().getCellAt(this.getPosI(),this.getPosJ()+1)!=null && !(this.getGame().getCellAt(this.getPosI(),this.getPosJ()+1).getPiece().isFriendly())) {
			this.attack(this.getGame().getCellAt(this.getPosI(),this.getPosJ()+1).getPiece());
			this.setPowerUsed(true);
			this.getGame().switchTurns();
			break;
		}
		case UP : if(this.getGame().getCellAt(this.getPosI()-2,this.getPosJ())!=null && !(this.getGame().getCellAt(this.getPosI()-2,this.getPosJ()).getPiece().isFriendly())) {
			this.attack(this.getGame().getCellAt(this.getPosI()-2,this.getPosJ()).getPiece());
			this.setPowerUsed(true);
			this.getGame().switchTurns();
			break;
		}
		else if(this.getGame().getCellAt(this.getPosI()-1,this.getPosJ())!=null && !(this.getGame().getCellAt(this.getPosI()-1,this.getPosJ()).getPiece().isFriendly())) {
			this.attack(this.getGame().getCellAt(this.getPosI()-1,this.getPosJ()).getPiece());
			this.setPowerUsed(true);
			this.getGame().switchTurns();
			break;
		}
		default : throw new InvalidPowerDirectionException("This direction is not allowed by the game rules",this,d);
		}
	}
	
}
