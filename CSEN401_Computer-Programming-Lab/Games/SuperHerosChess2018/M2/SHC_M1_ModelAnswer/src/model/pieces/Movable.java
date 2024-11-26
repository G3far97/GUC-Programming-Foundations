package model.pieces;

import exceptions.InvalidPowerTargetException;
import exceptions.OccupiedCellException;
import exceptions.UnallowedMovementException;
import exceptions.WrongTurnException;
import model.game.Direction;

public interface Movable {
	public void move(Direction r) throws UnallowedMovementException, OccupiedCellException, WrongTurnException;
	public void moveDown();
	public void moveDownLeft();
	public void moveDownRight();
	public void moveLeft();
	public void moveRight();
	public void moveUp();
	public void moveUpLeft();
	public void moveUpRight();
}
