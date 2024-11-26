package model.pieces.heroes;

import java.awt.Point;

import exceptions.InvalidPowerDirectionException;
import exceptions.InvalidPowerTargetException;
import exceptions.PowerAlreadyUsedException;
import exceptions.WrongTurnException;
import model.game.Direction;
import model.game.Game;
import model.game.Player;
import model.pieces.Piece;

public class Ranged extends ActivatablePowerHero {

	public Ranged(Player player, Game game, String name) {
		super(player, game, name);
	}

	public String toString() {
		return "R";
	}
	public void usePower(Direction d, Piece target, Point newPos) throws PowerAlreadyUsedException, InvalidPowerDirectionException, WrongTurnException, InvalidPowerTargetException{
		super.usePower(d, target, null);
		switch(d) {
		case DOWN : for(int i = this.getPosI()+1;i<this.getGame().getBoardHeight();i++) {
			if(this.getGame().getCellAt(i,this.getPosJ())!=null) {
				if(!this.getGame().getCellAt(i,this.getPosJ()).getPiece().isFriendly()) {
					this.attack(target);
					this.setPowerUsed(true);
					this.getGame().switchTurns();
					break;
				}
				else {
					this.setPowerUsed(true);
					this.getGame().switchTurns();
					throw new InvalidPowerDirectionException("This direction will result in hitting a friendly piece",this,d);
				}
			}
		}
		throw new InvalidPowerDirectionException("This direction will result in hitting no enemies",this,d);
		case LEFT :  for(int j = this.getPosJ()-1;j>0;j--) {
			if(this.getGame().getCellAt(this.getPosI(),j)!=null) {
				if(!this.getGame().getCellAt(this.getPosI(),j).getPiece().isFriendly()) {
					this.attack(target);
					this.setPowerUsed(true);
					this.getGame().switchTurns();
					break;
				}
				else {
					this.setPowerUsed(true);
					this.getGame().switchTurns();
					throw new InvalidPowerDirectionException("This direction will result in hitting a friendly piece",this,d);
				}
			}
		}
		this.setPowerUsed(true);
		this.getGame().switchTurns();
		throw new InvalidPowerDirectionException("This direction will result in hitting no enemies",this,d);

		case RIGHT :  for(int j = this.getPosJ()+1;j<this.getGame().getBoardWidth();j++) {
			if(this.getGame().getCellAt(this.getPosI(),j)!=null) {
				if(!this.getGame().getCellAt(this.getPosI(),j).getPiece().isFriendly()) {
					this.attack(target);
					this.setPowerUsed(true);
					this.getGame().switchTurns();
					break;
				}
				else {
					this.setPowerUsed(true);
					this.getGame().switchTurns();
					throw new InvalidPowerDirectionException("This direction will result in hitting a friendly piece",this,d);
				}
			}
		}
		this.setPowerUsed(true);
		this.getGame().switchTurns();
		throw new InvalidPowerDirectionException("This direction will result in hitting no enemies",this,d);

		case UP :  for(int i = this.getPosI()-1;i>0;i--) {
			if(this.getGame().getCellAt(i,this.getPosJ())!=null) {
				if(!this.getGame().getCellAt(i,this.getPosJ()).getPiece().isFriendly()) {
					this.attack(target);
					this.setPowerUsed(true);
					this.getGame().switchTurns();
					break;
				}
				else {
					this.setPowerUsed(true);
					this.getGame().switchTurns();
					throw new InvalidPowerDirectionException("This direction will result in hitting a friendly piece",this,d);
				}
			}
		}
		this.setPowerUsed(true);
		this.getGame().switchTurns();
		throw new InvalidPowerDirectionException("This direction will result in hitting no enemies",this,d);
		default : throw new InvalidPowerDirectionException("This direction is not allowed",this,d);
		}
		
	}
}
