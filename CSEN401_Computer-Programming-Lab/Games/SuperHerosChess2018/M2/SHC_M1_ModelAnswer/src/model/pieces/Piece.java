package model.pieces;

import exceptions.OccupiedCellException;
import exceptions.UnallowedMovementException;
import exceptions.WrongTurnException;
import model.game.Direction;
import model.game.Game;
import model.game.Player;
import model.pieces.heroes.Armored;
import model.pieces.sidekicks.SideKick;

public abstract class Piece implements Movable {

	private String name;
	private Player owner;
	private Game game;
	private int posI;
	private int posJ;

	public Piece(Player p, Game g, String name) {
		this.owner = p;
		this.game = g;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getPosI() {
		return posI;
	}

	public void setPosI(int i) {
		posI = i;
	}

	public int getPosJ() {
		return posJ;
	}

	public void setPosJ(int j) {
		posJ = j;
	}

	public Game getGame() {
		return game;
	}

	public Player getOwner() {
		return owner;
	}
	public void attack(Piece target) {
		if(target!=null) {
		if(target instanceof Armored) {
			if(((Armored) target).isArmorUp()==false) {
				this.getGame().getCurrentPlayer().setPayloadPos(this.getGame().getCurrentPlayer().getPayloadPos()+1);
				target.getOwner().getDeadCharacters().add(target);
			}
			else {
				((Armored) target).setArmorUp(false);
			}
		}
		else if(target instanceof SideKick) {
			target.getOwner().getDeadCharacters().add(target);
			this.getGame().getCurrentPlayer().setSideKilled(this.getGame().getCurrentPlayer().getSideKilled()+1);
			if(this.getGame().getCurrentPlayer().getSideKilled()%2==0) {
				this.getGame().getCurrentPlayer().setPayloadPos(this.getGame().getCurrentPlayer().getPayloadPos()+1);
			}
		}
		else {
			this.getGame().getCurrentPlayer().setPayloadPos(this.getGame().getCurrentPlayer().getPayloadPos()+1);
			target.getOwner().getDeadCharacters().add(target);

		}
		this.getGame().checkWinner();
	}
}
	@Override
	public void move(Direction r) throws UnallowedMovementException, OccupiedCellException, WrongTurnException {
		if(this.getOwner()==this.getGame().getCurrentPlayer()) {
		if(this.demandedPiece(this, r)==null) {
			switch(r) {
			case DOWN : moveDown();this.getGame().switchTurns();break;
			case DOWNLEFT : moveDownLeft();this.getGame().switchTurns();break;
			case DOWNRIGHT : moveDownRight();this.getGame().switchTurns();break;
			case LEFT : moveLeft();this.getGame().switchTurns();break;
			case RIGHT : moveRight();this.getGame().switchTurns();break;
			case UP : moveUp();this.getGame().switchTurns();break;
			case UPLEFT : moveUpLeft();this.getGame().switchTurns();break;
			case UPRIGHT : moveUpRight();this.getGame().switchTurns();break;
			}
		}
		else if(!((this.demandedPiece(this, r).isFriendly()))) {
			switch(r) {
			case DOWN : moveDown();this.attack(this.demandedPiece(this, r));this.getGame().switchTurns();break;
			case DOWNLEFT : moveDownLeft();this.attack(this.demandedPiece(this, r));this.getGame().switchTurns();break;
			case DOWNRIGHT : moveDownRight();this.attack(this.demandedPiece(this, r));this.getGame().switchTurns();break;
			case LEFT : moveLeft();this.attack(this.demandedPiece(this, r));this.getGame().switchTurns();break;
			case RIGHT : moveRight();this.attack(this.demandedPiece(this, r));this.getGame().switchTurns();break;
			case UP : moveUp();this.attack(this.demandedPiece(this, r));this.getGame().switchTurns();break;
			case UPLEFT : moveUpLeft();this.attack(this.demandedPiece(this, r));this.getGame().switchTurns();break;
			case UPRIGHT : moveUpRight();this.attack(this.demandedPiece(this, r));this.getGame().switchTurns();break;
			}
		}
			else {
				throw new OccupiedCellException("Occupied Cell",this,r);
			}
		}
		else {
			throw new WrongTurnException(this);
		}
	}

	@Override
	public void moveDown() {
		if(this.getPosI()==this.getGame().getBoardHeight()-1) {
			this.getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(null);
			this.getGame().getCellAt(0,this.getPosJ()).setPiece(this);
			this.setPosI(0);

		}
		else {
			this.getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(null);	
			this.getGame().getCellAt(this.getPosI()+1,this.getPosJ()).setPiece(this);
			this.setPosI(this.getPosI()+1);
		}
	}

	@Override
	public void moveDownLeft() {
		if(this.getPosI()==this.getGame().getBoardHeight()-1 && this.getPosJ()==0) {
			this.getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(null);	
			this.getGame().getCellAt(0,this.getGame().getBoardWidth()-1).setPiece(this);
			this.setPosI(0);
			this.setPosJ(this.getGame().getBoardWidth()-1);
		}
		else if(this.getPosJ()==0) {
			this.getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(null);	
			this.getGame().getCellAt(this.getPosI()+1,this.getGame().getBoardWidth()-1).setPiece(this);
			this.setPosI(this.getPosI()+1);
			this.setPosJ(this.getGame().getBoardWidth()-1);
		}
		else if(this.getPosI()==this.getGame().getBoardHeight()-1) {
			this.getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(null);	
			this.getGame().getCellAt(0,this.getPosJ()-1).setPiece(this);
			this.setPosI(0);
			this.setPosJ(this.getPosJ()-1);
		}
		else {
			this.getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(null);	
			this.getGame().getCellAt(this.getPosI()+1,this.getPosJ()-1).setPiece(this);
			this.setPosI(this.getPosI()+1);
			this.setPosJ(this.getPosJ()-1);
		}
	}

	@Override
	public void moveDownRight() {
		if(this.getPosI()==this.getGame().getBoardHeight()-1 && this.getPosJ()==this.getGame().getBoardWidth()-1) {
			this.getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(null);	
			this.getGame().getCellAt(0,0).setPiece(this);
			this.setPosI(0);
			this.setPosJ(0);
		}
		else if(this.getPosJ()==this.getGame().getBoardWidth()-1) {
			this.getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(null);	
			this.getGame().getCellAt(this.getPosI()+1,0).setPiece(this);
			this.setPosI(this.getPosI()+1);
			this.setPosJ(0);
		}
		else if(this.getPosI()==this.getGame().getBoardHeight()-1) {
			this.getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(null);	
			this.getGame().getCellAt(0,this.getPosJ()+1).setPiece(this);
			this.setPosI(0);
			this.setPosJ(this.getPosJ()+1);
		}
		else {
			this.getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(null);	
			this.getGame().getCellAt(this.getPosI()+1,this.getPosJ()+1).setPiece(this);
			this.setPosI(this.getPosI()+1);
			this.setPosJ(this.getPosJ()+1);
		}
	}

	@Override
	public void moveLeft() {
		if(this.getPosJ()==0) {
			this.getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(null);
			this.getGame().getCellAt(this.getPosI(),this.getGame().getBoardWidth()-1).setPiece(this);
			this.setPosJ(this.getGame().getBoardWidth()-1);
		}
		else {
			this.getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(null);
			this.getGame().getCellAt(this.getPosI(),this.getPosJ()-1).setPiece(this);
			this.setPosJ(this.getPosJ()-1);
		}
	}

	@Override
	public void moveRight() {
		if(this.getPosJ()==this.getGame().getBoardWidth()-1) {
			this.getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(null);
			this.getGame().getCellAt(this.getPosI(),0).setPiece(this);
			this.setPosJ(0);;
		}
		else {
			this.getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(null);
			this.getGame().getCellAt(this.getPosI(),this.getPosJ()+1).setPiece(this);
			this.setPosJ(this.getPosJ()+1);
		}
	}

	@Override
	public void moveUp() {
		if(this.getPosI()==0) {
			this.getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(null);
			this.getGame().getCellAt(this.getGame().getBoardHeight()-1,this.getPosJ()).setPiece(this);
			this.setPosI(this.getGame().getBoardHeight()-1);
		}
		else {
			this.getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(null);
			this.getGame().getCellAt(this.getPosI()-1,this.getPosJ()).setPiece(this);
			this.setPosI(this.getPosI()-1);
		}
	}

	@Override
	public void moveUpLeft() {
		if(this.getPosI()==0 && this.getPosJ()==0) {
			this.getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(null);
			this.getGame().getCellAt(this.getGame().getBoardHeight()-1,this.getGame().getBoardWidth()-1).setPiece(this);
			this.setPosI(this.getGame().getBoardHeight()-1);
			this.setPosJ(this.getGame().getBoardWidth()-1);
		}
		else if(this.getPosJ()==0) {
			this.getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(null);
			this.getGame().getCellAt(this.getPosI()-1,this.getGame().getBoardWidth()-1).setPiece(this);
			this.setPosI(this.getPosI()-1);
			this.setPosJ(this.getGame().getBoardWidth()-1);
		}
		else if(this.getPosI()==0) {
			this.getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(null);
			this.getGame().getCellAt(this.getGame().getBoardHeight()-1,this.getPosJ()-1).setPiece(this);
			this.setPosI(this.getGame().getBoardHeight()-1);
			this.setPosJ(this.getPosJ()-1);
		}
		else {
			this.getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(null);
			this.getGame().getCellAt(this.getPosI()-1,this.getPosJ()-1).setPiece(this);
			this.setPosI(this.getPosI()-1);
			this.setPosJ(this.getPosJ()-1);
		}
	}

	@Override
	public void moveUpRight() {
		if(this.getPosI()==0 && this.getPosJ()==this.getGame().getBoardWidth()-1) {
			this.getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(null);
			this.getGame().getCellAt(this.getGame().getBoardHeight()-1,0).setPiece(this);
			this.setPosI(this.getGame().getBoardHeight()-1);
			this.setPosJ(0);
		}
		else if(this.getPosJ()==this.getGame().getBoardWidth()-1) {
			this.getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(null);
			this.getGame().getCellAt(this.getPosI()-1,0).setPiece(this);
			this.setPosI(this.getPosI()-1);
			this.setPosJ(0);
		}
		else if(this.getPosI()==0) {
			this.getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(null);
			this.getGame().getCellAt(this.getGame().getBoardHeight()-1,this.getPosJ()+1).setPiece(this);
			this.setPosI(this.getGame().getBoardHeight()-1);
			this.setPosJ(this.getPosJ()+1);
		}
		else {
			this.getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(null);
			this.getGame().getCellAt(this.getPosI()-1,this.getPosJ()+1).setPiece(this);
			this.setPosI(this.getPosI()-1);
			this.setPosJ(this.getPosJ()+1);
		}
	}
	public Piece demandedPiece(Piece start,Direction r) {
		switch(r) {
		case DOWN :if(start.getPosI()==this.getGame().getBoardHeight()-1) {
			return this.getGame().getCellAt(0,start.getPosJ()).getPiece();
		}
		else {
			return this.getGame().getCellAt(start.getPosI()+1,start.getPosJ()).getPiece();
		}
		case DOWNLEFT :if(start.getPosI()==this.getGame().getBoardHeight()-1 && start.getPosJ()==0) {
			return this.getGame().getCellAt(0,this.getGame().getBoardWidth()-1).getPiece();
		}
		else if(start.getPosJ()==0) {
			return this.getGame().getCellAt(start.getPosI()+1,this.getGame().getBoardWidth()-1).getPiece();
		}
		else if(start.getPosI()==this.getGame().getBoardHeight()-1) {
			return this.getGame().getCellAt(0,start.getPosJ()-1).getPiece();
		}
		else {
			return this.getGame().getCellAt(start.getPosI()+1,start.getPosJ()-1).getPiece();
		}
		case DOWNRIGHT :if(start.getPosI()==this.getGame().getBoardHeight()-1 && start.getPosJ()==this.getGame().getBoardWidth()-1) {
			return this.getGame().getCellAt(0,0).getPiece();
		}
		else if(start.getPosJ()==this.getGame().getBoardWidth()-1) {
			return this.getGame().getCellAt(start.getPosI()+1,0).getPiece();
		}
		else if(start.getPosI()==this.getGame().getBoardHeight()-1) {
			return this.getGame().getCellAt(0,start.getPosJ()+1).getPiece();
		}
		else {
			return this.getGame().getCellAt(start.getPosI()+1,start.getPosJ()+1).getPiece();
		}
		case LEFT :if(start.getPosJ()==0) {
			return this.getGame().getCellAt(start.getPosI(),this.getGame().getBoardWidth()-1).getPiece();
		}
		else {
			return this.getGame().getCellAt(start.getPosI(),start.getPosJ()-1).getPiece();
		}
		case RIGHT :if(start.getPosJ()==this.getGame().getBoardWidth()-1) {
			return this.getGame().getCellAt(start.getPosI(),0).getPiece();
		}
		else {
			return this.getGame().getCellAt(start.getPosI(),start.getPosJ()+1).getPiece();
		}
		case UP :if(start.getPosI()==0) {
			return this.getGame().getCellAt(this.getGame().getBoardHeight()-1,start.getPosJ()).getPiece();
		}
		else {
			return this.getGame().getCellAt(start.getPosI()-1,start.getPosJ()).getPiece();
		}
		case UPLEFT :if(start.getPosI()==0 && start.getPosJ()==0) {
			return this.getGame().getCellAt(this.getGame().getBoardHeight()-1,this.getGame().getBoardWidth()-1).getPiece();
		}
		else if(start.getPosJ()==0) {
			return this.getGame().getCellAt(start.getPosI()-1,this.getGame().getBoardWidth()-1).getPiece();
		}
		else if(start.getPosI()==0) {
			return this.getGame().getCellAt(this.getGame().getBoardHeight()-1,start.getPosJ()-1).getPiece();
		}
		else {
			return this.getGame().getCellAt(start.getPosI()-1,start.getPosJ()-1).getPiece();
		}
		case UPRIGHT :if(start.getPosI()==0 && start.getPosJ()==this.getGame().getBoardWidth()-1) {
			return this.getGame().getCellAt(this.getGame().getBoardHeight()-1,0).getPiece();
		}
		else if(start.getPosJ()==this.getGame().getBoardWidth()-1) {
			return this.getGame().getCellAt(start.getPosI()-1,0).getPiece();
		}
		else if(start.getPosI()==0) {
			return this.getGame().getCellAt(this.getGame().getBoardHeight()-1,start.getPosJ()+1).getPiece();
		}
		else {
			return this.getGame().getCellAt(start.getPosI()-1,start.getPosJ()+1).getPiece();
		}
		}
		return start;
	}
	public boolean isFriendly() {
		return this.getGame().getCurrentPlayer()==this.getOwner();
	}
}
