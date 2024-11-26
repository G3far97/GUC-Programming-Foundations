package model.pieces.heroes;

import model.game.Direction;
import model.game.Game;
import model.game.Player;
import model.pieces.Piece;

public class Speedster extends NonActivatablePowerHero {

	public Speedster(Player player, Game game, String name) {
		super(player, game, name);
	}

	@Override
	public String toString() {
		return "S";
	}
	@Override
	public void moveDown() {
		if(this.getPosI()==this.getGame().getBoardHeight()-2) {
			this.getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(null);
			this.getGame().getCellAt(0,this.getPosJ()).setPiece(this);
			this.setPosI(0);
		}
		else if(this.getPosI()==this.getGame().getBoardHeight()-1) {
			this.getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(null);
			this.getGame().getCellAt(1,this.getPosJ()).setPiece(this);
			this.setPosI(1);
			}
		else {
			this.getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(null);
			this.getGame().getCellAt(this.getPosI()+2,this.getPosJ()).setPiece(this);
			this.setPosI(this.getPosI()+2);
		}
	}
	@Override
	public void moveDownLeft() {
		if(this.getPosI()==this.getGame().getBoardHeight()-2 && this.getPosJ()==1) {
		     this.getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(null);
			 this.getGame().getCellAt(0,this.getGame().getBoardWidth()-1).setPiece(this);
			 this.setPosI(0);
			 this.setPosJ(this.getGame().getBoardWidth()-1);
		}
		else if(this.getPosI()==this.getGame().getBoardHeight()-1 && this.getPosJ()==0) {
		     this.getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(null);
			 this.getGame().getCellAt(1,this.getGame().getBoardWidth()-2).setPiece(this);
			 this.setPosI(1);
			 this.setPosJ(this.getGame().getBoardWidth()-2);
		}
		else if(this.getPosI()==this.getGame().getBoardHeight()-2 && this.getPosJ()==0) {
		     this.getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(null);
			 this.getGame().getCellAt(0,this.getGame().getBoardWidth()-2).setPiece(this);
			 this.setPosI(0);
			 this.setPosJ(this.getGame().getBoardWidth()-2);
		}
		else if(this.getPosI()==this.getGame().getBoardHeight()-1 && this.getPosJ()==1) {
		     this.getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(null);
			 this.getGame().getCellAt(1,this.getGame().getBoardWidth()-1).setPiece(this);
			 this.setPosI(1);
			 this.setPosJ(this.getGame().getBoardWidth()-1);
		}
		else if(this.getPosJ()==1) {
		     this.getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(null);
			 this.getGame().getCellAt(this.getPosI()+2,this.getGame().getBoardWidth()-1).setPiece(this);
			 this.setPosI(this.getPosI()+2);
			 this.setPosJ(this.getGame().getBoardWidth()-1);
		}
		else if(this.getPosJ()==0) {
		     this.getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(null);
			 this.getGame().getCellAt(this.getPosI()+2,this.getGame().getBoardWidth()-2).setPiece(this);
			 this.setPosI(this.getPosI()+2);
			 this.setPosJ(this.getGame().getBoardWidth()-2);
		}
		else if(this.getPosI()==this.getGame().getBoardHeight()-2) {
		     this.getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(null);
			 this.getGame().getCellAt(0,this.getPosJ()-2).setPiece(this);
			 this.setPosI(0);
			 this.setPosJ(this.getPosJ()-2);
			 
		}
		else if(this.getPosI()==this.getGame().getBoardHeight()-1) {
		     this.getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(null);
			 this.getGame().getCellAt(1,this.getPosJ()-2).setPiece(this);
			 this.setPosI(1);
			 this.setPosJ(this.getPosJ()-2);
		}
		else {
		     this.getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(null);
			 this.getGame().getCellAt(this.getPosI()+2,this.getPosJ()-2).setPiece(this);
			 this.setPosI(this.getPosI()+2);
			 this.setPosJ(this.getPosJ()-2);
		}
	}
	@Override
	public void moveDownRight() {
		if(this.getPosI()==this.getGame().getBoardHeight()-2 && this.getPosJ()==this.getGame().getBoardWidth()-2) {
		     this.getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(null);
			 this.getGame().getCellAt(0,0).setPiece(this);
			 this.setPosI(0);
			 this.setPosJ(0);
		}
		else if(this.getPosI()==this.getGame().getBoardHeight()-1 && this.getPosJ()==this.getGame().getBoardWidth()-1) {
		     this.getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(null);
			 this.getGame().getCellAt(1,1).setPiece(this);
			 this.setPosI(1);
			 this.setPosJ(1);
		}
		else if(this.getPosI()==this.getGame().getBoardHeight()-2 && this.getPosJ()==this.getGame().getBoardWidth()-1) {
		     this.getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(null);
			 this.getGame().getCellAt(0,1).setPiece(this);
			 this.setPosI(0);
			 this.setPosJ(1);
		}
		else if(this.getPosI()==this.getGame().getBoardHeight()-1 && this.getPosJ()==this.getGame().getBoardWidth()-2) {
		     this.getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(null);
			 this.getGame().getCellAt(1,0).setPiece(this);
			 this.setPosI(1);
			 this.setPosJ(0);
		}
		else if(this.getPosJ()==this.getGame().getBoardWidth()-2) {
		     this.getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(null);
			 this.getGame().getCellAt(this.getPosI()+2,0).setPiece(this);
			 this.setPosI(this.getPosI()+2);
			 this.setPosJ(0);
		}
		else if(this.getPosJ()==this.getGame().getBoardWidth()-1) {
		     this.getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(null);
			 this.getGame().getCellAt(this.getPosI()+2,1).setPiece(this);
			 this.setPosI(this.getPosI()+2);
			 this.setPosJ(1);
		}
		else if(this.getPosI()==this.getGame().getBoardHeight()-2) {
		     this.getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(null);
			 this.getGame().getCellAt(0,this.getPosJ()+2).setPiece(this);
			 this.setPosI(0);
			 this.setPosJ(this.getPosJ()+2);
		}
		else if(this.getPosI()==this.getGame().getBoardHeight()-1) {
		     this.getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(null);
			 this.getGame().getCellAt(1,this.getPosJ()+2).setPiece(this);
			 this.setPosI(1);
			 this.setPosJ(this.getPosJ()+2);
		}
		else {
		     this.getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(null);
			 this.getGame().getCellAt(this.getPosI()+2,this.getPosJ()+2).setPiece(this);
			 this.setPosI(this.getPosI()+2);
			 this.setPosJ(this.getPosJ()+2);
		}
	}
	@Override
	public void moveLeft() {
		if(this.getPosJ()==1) {
			this.getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(null);
			this.getGame().getCellAt(this.getPosI(),this.getGame().getBoardWidth()-1).setPiece(this);
			this.setPosJ(this.getGame().getBoardWidth()-1);
		}
		else if(this.getPosJ()==0) {
			this.getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(null);
			this.getGame().getCellAt(this.getPosI(),this.getGame().getBoardWidth()-2).setPiece(this);
			this.setPosJ(this.getGame().getBoardWidth()-2);;
		}
		else {
			this.getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(null);
			this.getGame().getCellAt(this.getPosI(),this.getPosJ()-2).setPiece(this);
			this.setPosJ(this.getPosJ()-2);
		}
	}
	@Override
	public void moveRight() {
		if(this.getPosJ()==this.getGame().getBoardWidth()-1) {
			this.getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(null);
			this.getGame().getCellAt(this.getPosI(),1).setPiece(this);
			this.setPosJ(1);
		}
		else if(this.getPosJ()==this.getGame().getBoardHeight()-2) {
			this.getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(null);
			this.getGame().getCellAt(this.getPosI(),0).setPiece(this);
			this.setPosJ(0);
		}
		else {
			this.getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(null);
			this.getGame().getCellAt(this.getPosI(),this.getPosJ()+2).setPiece(this);
			this.setPosJ(this.getPosJ()+2);
		}
	}
	@Override
	public void moveUp() {
		if(this.getPosI()==0) {
			this.getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(null);
			this.getGame().getCellAt(this.getGame().getBoardHeight()-2,this.getPosJ()).setPiece(this);
			this.setPosI(this.getGame().getBoardHeight()-2);
		}
		else if(this.getPosI()==1) {
			this.getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(null);
			this.getGame().getCellAt(this.getGame().getBoardHeight()-1,this.getPosJ()).setPiece(this);
			this.setPosI(this.getGame().getBoardHeight()-1);
		}
		else {
			this.getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(null);
			this.getGame().getCellAt(this.getPosI()-2,this.getPosJ()).setPiece(this);
			this.setPosI(this.getPosI()-2);
		}
	}
	@Override
	public void moveUpLeft() {
		if(this.getPosI()==1 && this.getPosJ()==1) {
			 this.getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(null);
			 this.getGame().getCellAt(this.getGame().getBoardHeight()-1,this.getGame().getBoardWidth()-1).setPiece(this);
			 this.setPosI(this.getGame().getBoardHeight()-1);
			 this.setPosJ(this.getGame().getBoardWidth()-1);
		}
		else if(this.getPosI()==0 && this.getPosJ()==0) {
			 this.getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(null);
			 this.getGame().getCellAt(this.getGame().getBoardHeight()-2,this.getGame().getBoardWidth()-2).setPiece(this);
			 this.setPosI(this.getGame().getBoardHeight()-2);
			 this.setPosJ(this.getGame().getBoardWidth()-2);
		}
		else if(this.getPosI()==1 && this.getPosJ()==0) {
			 this.getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(null);
			 this.getGame().getCellAt(this.getGame().getBoardHeight()-1,this.getGame().getBoardWidth()-2).setPiece(this);
			 this.setPosI(this.getGame().getBoardHeight()-1);
			 this.setPosJ(this.getGame().getBoardWidth()-2);
		}
		else if(this.getPosI()==0 && this.getPosJ()==1) {
			 this.getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(null);
			 this.getGame().getCellAt(this.getGame().getBoardHeight()-2,this.getGame().getBoardWidth()-1).setPiece(this);
			 this.setPosI(this.getGame().getBoardHeight()-2);
			 this.setPosJ(this.getGame().getBoardWidth()-1);
		}
		else if(this.getPosJ()==1) {
			 this.getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(null);
			 this.getGame().getCellAt(this.getPosI()-2,this.getGame().getBoardWidth()-1).setPiece(this);
			 this.setPosI(this.getPosI()-2);
			 this.setPosJ(this.getGame().getBoardWidth()-1);
		}
		else if(this.getPosJ()==0) {
			 this.getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(null);
			 this.getGame().getCellAt(this.getPosI()-2,this.getGame().getBoardWidth()-2).setPiece(this);
			 this.setPosI(this.getPosI()-2);
			 this.setPosJ(this.getGame().getBoardWidth()-2);
		}
		else if(this.getPosI()==1) {
			 this.getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(null);
			 this.getGame().getCellAt(this.getGame().getBoardHeight()-1,this.getPosJ()-2).setPiece(this);
			 this.setPosI(this.getGame().getBoardHeight()-1);
			 this.setPosJ(this.getPosJ()-2);
		}
		else if(this.getPosI()==0) {
			 this.getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(null);
			 this.getGame().getCellAt(this.getGame().getBoardHeight()-2,this.getPosJ()-2).setPiece(this);
			 this.setPosI(this.getGame().getBoardHeight()-2);
			 this.setPosJ(this.getPosJ()-2);
		}
		else {
			 this.getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(null);
			 this.getGame().getCellAt(this.getPosI()-2,this.getPosJ()-2).setPiece(this);
			 this.setPosI(this.getPosI()-2);
			 this.setPosJ(this.getPosJ()-2);
		}
	}
	@Override
	public void moveUpRight() {
		if(this.getPosI()==1 && this.getPosJ()==this.getGame().getBoardWidth()-2) {
			 this.getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(null);
			 this.getGame().getCellAt(this.getGame().getBoardHeight()-1,0).setPiece(this);
			 this.setPosI(this.getGame().getBoardHeight()-1);
			 this.setPosJ(0);
		}
		else if(this.getPosI()==0 && this.getPosJ()==this.getGame().getBoardWidth()-1) {
			 this.getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(null);
			 this.getGame().getCellAt(this.getGame().getBoardHeight()-2,1).setPiece(this);
			 this.setPosI(this.getGame().getBoardHeight()-2);
			 this.setPosJ(1);
		}
		else if(this.getPosI()==1 && this.getPosJ()==this.getGame().getBoardWidth()-1) {
			 this.getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(null);
			 this.getGame().getCellAt(this.getGame().getBoardHeight()-1,1).setPiece(this);
			 this.setPosI(this.getGame().getBoardHeight()-1);
			 this.setPosJ(1);
		}
		else if(this.getPosI()==0 && this.getPosJ()==this.getGame().getBoardWidth()-2) {
			 this.getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(null);
			 this.getGame().getCellAt(this.getGame().getBoardHeight()-2,0).setPiece(this);
			 this.setPosI(this.getGame().getBoardHeight()-2);
			 this.setPosJ(0);
		}
		else if(this.getPosJ()==this.getGame().getBoardWidth()-2) {
			 this.getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(null);
			 this.getGame().getCellAt(this.getPosI()-2,0).setPiece(this);
			 this.setPosI(this.getPosI()-2);
			 this.setPosJ(0);

			 
		}
		else if(this.getPosJ()==this.getGame().getBoardWidth()-1) {
			 this.getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(null);
			 this.getGame().getCellAt(this.getPosI()-2,1).setPiece(this);
			 this.setPosI(this.getPosI()-2);
			 this.setPosJ(1);
		}
		else if(this.getPosI()==1) {
			 this.getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(null);
			 this.getGame().getCellAt(this.getGame().getBoardHeight()-1,this.getPosJ()+2).setPiece(this);
			 this.setPosI(this.getGame().getBoardHeight()-1);
			 this.setPosJ(this.getPosJ()+2);
		}
		else if(this.getPosI()==0) {
			 this.getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(null);
			 this.getGame().getCellAt(this.getGame().getBoardHeight()-2,this.getPosJ()+2).setPiece(this);
			 this.setPosI(this.getGame().getBoardHeight()-2);
			 this.setPosJ(this.getPosJ()+2);
		}
		else {
			 this.getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(null);
			 this.getGame().getCellAt(this.getPosI()-2,this.getPosJ()+2).setPiece(this);
			 this.setPosI(this.getPosI()-2);
			 this.setPosJ(this.getPosJ()+2);
		}
	}
	public Piece demandedPiece(Piece start,Direction r) {
		switch(r) {
		case DOWN :if(start.getPosI()==this.getGame().getBoardHeight()-2) {
			return this.getGame().getCellAt(0,start.getPosJ()).getPiece();
		}
		else if(start.getPosI()==this.getGame().getBoardHeight()-1) {
			return this.getGame().getCellAt(1,start.getPosJ()).getPiece();
		}
		else {
			return this.getGame().getCellAt(start.getPosI()+2,start.getPosJ()).getPiece();
		}
		case DOWNLEFT :if(start.getPosI()==this.getGame().getBoardHeight()-2 && start.getPosJ()==1) {
			return this.getGame().getCellAt(0,this.getGame().getBoardWidth()-1).getPiece();
		}
		else if(start.getPosI()==this.getGame().getBoardHeight()-1 && start.getPosJ()==0) {
			return this.getGame().getCellAt(1,this.getGame().getBoardWidth()-2).getPiece();
		}
		else if(start.getPosI()==this.getGame().getBoardHeight()-2 && start.getPosJ()==0) {
			return this.getGame().getCellAt(0,this.getGame().getBoardWidth()-2).getPiece();
		}
		else if(start.getPosI()==this.getGame().getBoardHeight()-1 && start.getPosJ()==1) {
			return this.getGame().getCellAt(1,this.getGame().getBoardWidth()-1).getPiece();
		}
		else if(start.getPosJ()==1) {
			return this.getGame().getCellAt(start.getPosI()+2,this.getGame().getBoardWidth()-1).getPiece();
		}
		else if(start.getPosJ()==0) {
			return this.getGame().getCellAt(start.getPosI()+2,this.getGame().getBoardWidth()-2).getPiece();
		}
		else if(start.getPosI()==this.getGame().getBoardHeight()-2) {
			return this.getGame().getCellAt(0,start.getPosJ()-2).getPiece();
		}
		else if(start.getPosI()==this.getGame().getBoardHeight()-1) {
			return this.getGame().getCellAt(1,start.getPosJ()-2).getPiece();
		}
		else {
			return this.getGame().getCellAt(start.getPosI()+2,start.getPosJ()-2).getPiece();
		}
		case DOWNRIGHT :if(start.getPosI()==this.getGame().getBoardHeight()-2 && start.getPosJ()==this.getGame().getBoardWidth()-2) {
			return this.getGame().getCellAt(0,0).getPiece();
		}
		else if(start.getPosI()==this.getGame().getBoardHeight()-1 && start.getPosJ()==this.getGame().getBoardWidth()-1) {
			return this.getGame().getCellAt(1,1).getPiece();
		}
		else if(start.getPosI()==this.getGame().getBoardHeight()-2 && start.getPosJ()==this.getGame().getBoardWidth()-1) {
			return this.getGame().getCellAt(0,1).getPiece();
		}
		else if(start.getPosI()==this.getGame().getBoardHeight()-1 && start.getPosJ()==this.getGame().getBoardWidth()-2) {
			return this.getGame().getCellAt(1,0).getPiece();
		}
		else if(start.getPosJ()==this.getGame().getBoardWidth()-2) {
			return this.getGame().getCellAt(start.getPosI()+2,0).getPiece();
		}
		else if(start.getPosJ()==this.getGame().getBoardWidth()-1) {
			return this.getGame().getCellAt(start.getPosI()+2,1).getPiece();
		}
		else if(start.getPosI()==this.getGame().getBoardHeight()-2) {
			return this.getGame().getCellAt(0,start.getPosJ()+2).getPiece();
		}
		else if(start.getPosI()==this.getGame().getBoardHeight()-1) {
			return this.getGame().getCellAt(1,start.getPosJ()+2).getPiece();
		}
		else {
			return this.getGame().getCellAt(start.getPosI()+2,start.getPosJ()+2).getPiece();
		}
		case LEFT :if(start.getPosJ()==1) {
			return this.getGame().getCellAt(start.getPosI(),this.getGame().getBoardWidth()-1).getPiece();
		}
		else if(start.getPosJ()==0) {
			return this.getGame().getCellAt(start.getPosI(),this.getGame().getBoardWidth()-2).getPiece();
		}
		else {
			return this.getGame().getCellAt(start.getPosI(),start.getPosJ()-2).getPiece();
		}
		case RIGHT :if(start.getPosJ()==this.getGame().getBoardWidth()-2) {
			return this.getGame().getCellAt(start.getPosI(),0).getPiece();
		}
		else if(start.getPosJ()==this.getGame().getBoardWidth()-1) {
			return this.getGame().getCellAt(start.getPosI(),1).getPiece();
		}
		else {
			return this.getGame().getCellAt(start.getPosI(),start.getPosJ()+2).getPiece();
		}
		case UP :if(start.getPosI()==1) {
			return this.getGame().getCellAt(this.getGame().getBoardHeight()-1,start.getPosJ()).getPiece();
		}
		else if(start.getPosI()==0) {
			return this.getGame().getCellAt(this.getGame().getBoardHeight()-2,start.getPosJ()).getPiece();
		}
		else {
			return this.getGame().getCellAt(start.getPosI()-2,start.getPosJ()).getPiece();
		}
		case UPLEFT :if(start.getPosI()==1 && start.getPosJ()==1) {
			return this.getGame().getCellAt(this.getGame().getBoardHeight()-1,this.getGame().getBoardWidth()-1).getPiece();
		}
		else if(start.getPosI()==0 && start.getPosJ()==0) {
			return this.getGame().getCellAt(this.getGame().getBoardHeight()-2,this.getGame().getBoardWidth()-2).getPiece();
		}
		else if(start.getPosI()==1 && start.getPosJ()==0) {
			return this.getGame().getCellAt(this.getGame().getBoardHeight()-1,this.getGame().getBoardWidth()-2).getPiece();
		}
		else if(start.getPosI()==0 && start.getPosJ()==1) {
			return this.getGame().getCellAt(this.getGame().getBoardHeight()-2,this.getGame().getBoardWidth()-1).getPiece();
		}
		else if(start.getPosJ()==1) {
			return this.getGame().getCellAt(start.getPosI()-2,this.getGame().getBoardWidth()-1).getPiece();
		}
		else if(start.getPosJ()==0) {
			return this.getGame().getCellAt(start.getPosI()-2,this.getGame().getBoardWidth()-2).getPiece();
		}
		else if(start.getPosI()==1) {
			return this.getGame().getCellAt(this.getGame().getBoardHeight()-1,start.getPosJ()-2).getPiece();
		}
		else if(start.getPosI()==0) {
			return this.getGame().getCellAt(this.getGame().getBoardHeight()-2,start.getPosJ()-2).getPiece();
		}
		else {
			return this.getGame().getCellAt(start.getPosI()-2,start.getPosJ()-2).getPiece();
		}
		case UPRIGHT :if(start.getPosI()==1 && start.getPosJ()==this.getGame().getBoardWidth()-2) {
			return this.getGame().getCellAt(this.getGame().getBoardHeight()-1,0).getPiece();
		}
		else if(start.getPosI()==0 && start.getPosJ()==this.getGame().getBoardWidth()-1) {
			return this.getGame().getCellAt(this.getGame().getBoardHeight()-2,1).getPiece();
		}
		else if(start.getPosI()==1 && start.getPosJ()==this.getGame().getBoardWidth()-1) {
			return this.getGame().getCellAt(this.getGame().getBoardHeight()-1,1).getPiece();
		}
		else if(start.getPosI()==0 && start.getPosJ()==this.getGame().getBoardWidth()-2) {
			return this.getGame().getCellAt(this.getGame().getBoardHeight()-2,0).getPiece();
		}
		else if(start.getPosJ()==this.getGame().getBoardWidth()-2) {
			return this.getGame().getCellAt(start.getPosI()-2,0).getPiece();
		}
		else if(start.getPosJ()==this.getGame().getBoardWidth()-1) {
			return this.getGame().getCellAt(start.getPosI()-2,1).getPiece();
		}
		else if(start.getPosI()==1) {
			return this.getGame().getCellAt(this.getGame().getBoardHeight()-1,start.getPosJ()+2).getPiece();
		}
		else if(start.getPosI()==0) {
			return this.getGame().getCellAt(this.getGame().getBoardHeight()-2,start.getPosJ()+2).getPiece();
		}
		else {
			return this.getGame().getCellAt(start.getPosI()-2,start.getPosJ()+2).getPiece();
		}
		}
		return start;
	}
}
