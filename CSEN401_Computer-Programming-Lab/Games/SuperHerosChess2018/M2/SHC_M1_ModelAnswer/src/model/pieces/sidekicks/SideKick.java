package model.pieces.sidekicks;

import model.game.Game;
import model.game.Player;
import model.pieces.Piece;
import model.pieces.heroes.Hero;

public abstract class SideKick extends Piece {

	public SideKick(Player player, Game game, String name) {
		super(player, game, name);
	}

	@Override
	public String toString() {
		return "K";
	}
	public void attack(Piece target) {
		if(target instanceof Hero) {
			super.attack(target);
			this.getGame().getCellAt(this.getPosI(),this.getPosJ()).setPiece(target);
		}
	}
}
