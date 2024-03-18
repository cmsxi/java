package omok;

import java.io.*;

public class Omok {
	public static void main(String[] args) throws IOException {
		Player user = new Player("사용자", "O");
		Player computer = new Player("컴퓨터", "X");
		Board board = new Board(19);
		play(board, user, computer);
	}

	private static void play(Board board, Player user, Player computer) throws IOException {
		board.print();
		while(board.nextTurn) {
			board.placeStone(user);
			if(!board.nextTurn)
				break;
			board.placeStone(computer);
		}
	}
}
