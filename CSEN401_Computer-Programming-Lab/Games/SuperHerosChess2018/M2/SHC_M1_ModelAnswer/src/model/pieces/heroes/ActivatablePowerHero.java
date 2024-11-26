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

public abstract class ActivatablePowerHero extends Hero {

	private boolean powerUsed;

	public ActivatablePowerHero(Player player, Game game, String name) {
		super(player, game, name);
	}

	public boolean isPowerUsed() {
		return powerUsed;
	}

	public void setPowerUsed(boolean powerUsed) {
		this.powerUsed = powerUsed;
	}
	public void usePower(Direction d, Piece target, Point newPos) throws PowerAlreadyUsedException, InvalidPowerDirectionException, WrongTurnException, InvalidPowerTargetException {
		if(this.getOwner()==this.getGame().getCurrentPlayer()) {
			if(target==null) {
				throw new InvalidPowerTargetException(this,target);
			}
			
		
		if(this.isPowerUsed()==true) {
			throw new PowerAlreadyUsedException("Power Already Used",this);
		}
	}
		else {
			throw new WrongTurnException(this);
		}
		}
}
