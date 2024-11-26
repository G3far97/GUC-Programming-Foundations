package model.pieces.sidekicks;

import model.game.Cell;
import model.game.Game;
import model.game.Player;
import model.pieces.Piece;
import model.pieces.heroes.Armored;
import model.pieces.heroes.Medic;
import model.pieces.heroes.Ranged;
import model.pieces.heroes.Speedster;
import model.pieces.heroes.Super;
import model.pieces.heroes.Tech;

public abstract class SideKick extends Piece {

	public SideKick(Player player, Game game, String name) {
		super(player, game, name);
	}

	public void attack(Piece target) {

		Cell c = getGame().getCellAt(getPosI(), getPosJ());

		if (!(target instanceof SideKick)) {

			if (target instanceof Armored && !((Armored) target).isArmorUp()) {
				if(this.getOwner()==this.getGame().getPlayer1()){
					c.setPiece(new Armored(getOwner(), getGame(), "Wonder Woman"));
				}
				else{
					c.setPiece(new Armored(getOwner(), getGame(), "Captain America"));
			
				}
			}

			if (target instanceof Medic) {
				if(this.getOwner()==this.getGame().getPlayer1()){
					c.setPiece(new Medic(getOwner(), getGame(), "Green Lantern"));
				}
				else{
					c.setPiece(new Medic(getOwner(), getGame(),"Vision"));
				}
			}

			if (target instanceof Ranged) {
				if(this.getOwner()==this.getGame().getPlayer1()){
					c.setPiece(new Ranged(getOwner(), getGame(), "Green Arrow"));
				}
				else{
					c.setPiece(new Ranged(getOwner(), getGame(), "Hawk Eye"));
				}
			}

			if (target instanceof Speedster) {
				if(this.getOwner()==this.getGame().getPlayer1()){
					c.setPiece(new Speedster(getOwner(), getGame(), "Flash"));
				}
				else{
					c.setPiece(new Speedster(getOwner(), getGame(), "Quick Silver"));
				}
			}

			if (target instanceof Super) {
				if(this.getOwner()==this.getGame().getPlayer1()){
					c.setPiece(new Super(getOwner(), getGame(), "Superman"));
				}
				else{
					c.setPiece(new Super(getOwner(), getGame(), "Hulk"));
				}
			}

			if (target instanceof Tech) {
				if(this.getOwner()==this.getGame().getPlayer1()){
					c.setPiece(new Tech(getOwner(), getGame(), "Batman"));
				}
				else{
					c.setPiece(new Tech(getOwner(), getGame(), "Ironman"));
				}
			}

		}

		super.attack(target);

	}

	@Override
	public String toString() {
		return "K";
	}
}
