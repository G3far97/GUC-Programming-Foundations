package model.game;

import java.util.Arrays;
import java.util.Collections;

import model.pieces.heroes.Armored;
import model.pieces.heroes.Hero;
import model.pieces.heroes.Medic;
import model.pieces.heroes.Ranged;
import model.pieces.heroes.Speedster;
import model.pieces.heroes.Super;
import model.pieces.heroes.Tech;
import model.pieces.sidekicks.SideKickP1;
import model.pieces.sidekicks.SideKickP2;

public class Game {

	private final int payloadPosTarget = 6;
	private final int boardWidth = 6;
	private final int boardHeight = 7;
	private Player player1;
	private Player player2;
	private Player currentPlayer;
	private Cell[][] board;

	public Game(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
		currentPlayer = player1;
		board = new Cell[boardHeight][boardWidth];
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public Player getPlayer1() {
		return player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public int getPayloadPosTarget() {
		return payloadPosTarget;
	}

	@Override
	public String toString() {
		String s = "";
		System.out.println("      " + getPlayer2().getName());
		System.out.print("| ");
		for (int i = 0; i < board[0].length; i++)
			System.out.print("--");
		System.out.println("|");
		for (int i = 0; i < board.length; i++) {
			System.out.print("| ");
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == null)
					System.out.print("n ");
				else
					System.out.print(board[i][j] + " ");
			}
			System.out.println("|");
		}
		System.out.print("| ");
		for (int i = 0; i < board[0].length; i++)
			System.out.print("--");
		System.out.println("|");
		System.out.println("    " + getPlayer1().getName());
		return s;
	}

	public int getBoardWidth() {
		return boardWidth;
	}

	public int getBoardHeight() {
		return boardHeight;
	}
	public void assemblePieces() {
		SideKickP1 sd1 = new SideKickP1(this,"sd1");
		SideKickP2 sd2 = new SideKickP2(this,"sd2");
		for(int j = 0;j<this.getBoardWidth();j++){
			board[2][j] = new Cell(sd2);
			board[4][j] = new Cell(sd1);
		}
		Armored armored1 = new Armored(player1,this,"armored1");
		Medic medic1 = new Medic(player1,this,"medic1");
		Ranged ranged1 = new Ranged(player1,this,"ranged1");
		Speedster speedster1 = new Speedster(player1,this,"speedster1");
		Super super1 = new Super(player1,this,"super1");
		Tech tech1 = new Tech(player1,this,"tech1");
		Hero[] heros1 = {armored1,medic1,ranged1,speedster1,super1,tech1};
		Collections.shuffle(Arrays.asList(heros1));
		Armored armored2 = new Armored(player2,this,"armored2");
		Medic medic2 = new Medic(player2,this,"medic2");
		Ranged ranged2 = new Ranged(player2,this,"ranged2");
		Speedster speedster2 = new Speedster(player2,this,"speedster2");
		Super super2 = new Super(player2,this,"super2");
		Tech tech2 = new Tech(player2,this,"tech2");
		Hero[] heros2 = {armored2,medic2,ranged2,speedster2,super2,tech2};
		Collections.shuffle(Arrays.asList(heros2));
		for(int j = 0 ; j<this.getBoardWidth();j++) {
			board[1][j] = new Cell(heros2[j]);
			board[5][j] = new Cell(heros1[j]);
		}	
	}
	public Cell getCellAt(int i, int j) {
		return board[i][j];
	}
	public void switchTurns() {
		if(this.currentPlayer==player1) {
			this.currentPlayer=player2;
		}
		else {
			this.currentPlayer=player1;
		}
	}
	public boolean checkWinner() {
		return this.currentPlayer.getPayloadPos()==6;
	}

}
