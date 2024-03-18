package omok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Board {
	int size;
	String[][] map;
	boolean nextTurn = true;

	Board(int size) {
		this.size = size;
//		this.idx = 0;
		map = new String[size][size];
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				map[row][col] = ".";
			}
		}
	}

	public void print() {
		int idx = 0;
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				if (col == 0) {
					System.out.printf("%2d", idx++);
				}
				System.out.print(" " + map[row][col]);
			}
			System.out.println();
			if (row == size - 1) {
				System.out.print("   ");
				for (int x = 65; x < 65 + size; x++) {
					System.out.print((char) x + " ");
				}
				System.out.println();
			}
		}
	}

	public void placeStone(Player player) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 좌표 입력받기
		System.out.print(player.name + "> ");
		String[] input = (br.readLine()).split(" ");

		int col = (input[0].toUpperCase().charAt(0)) - 65;
		int row = Integer.parseInt(input[1]);

		// 범위 내의 좌표인지 확인
		if (col >= this.size || col < 0 || row < 0 || row >= this.size) {
			System.out.println("범위를 벗어난 영역입니다. 다시 입력해주세요.\n");
			placeStone(player);
		}

		// 해당 자리가 비워져있는지 확인
		if (!".".equals(map[row][col])) {
			System.out.println("돌이 놓여져 있는 자리입니다. 다시 입력해주세요.\n");
			placeStone(player);
		}

		// 돌 놓기 및 출력
		map[row][col] = player.stone;
		print();

		this.nextTurn = !isOmok(row, col, player);
	}

	public boolean isOmok(int row, int col, Player player) {
		player.h_count = 0;
		player.v_count = 0;
		player.rd_count = 0;
		player.ld_count = 0;

//		System.out.println(row + " " + col);
		for (int i = 1; i < 5; i++) {

			// 가로 오목인지
			if (col - i >= 0 && map[row][col - i].equals(player.stone)) {
				player.h_count++;
			}
			if (col + i < size && map[row][col + i].equals(player.stone)) {
				player.h_count++;
			}

			// 세로 오목인지
			if (row - i >= 0 && map[row - i][col].equals(player.stone)) {
				player.v_count++;
			}
			if (row + i < size && map[row + i][col].equals(player.stone)) {
				player.v_count++;
			}

			// 오른쪽 대각 오목인지
			if (col - i >= 0 && row + i < size && map[row + i][col - i].equals(player.stone)) {
				player.rd_count++;
			}
			if (col + i < size && row + i < size && map[row + i][col + i].equals(player.stone)) {
				player.rd_count++;
			}
			
			// 왼쪽 대각 오목인지
			if (col + i < size && row - i >= 0 && map[row - i][col + i].equals(player.stone)) {
				player.ld_count++;
			}
			if (col - i >= 0 && row - i >= 0 && map[row - i][col - i].equals(player.stone)) {
				player.ld_count++;
			}

			if (player.h_count >= 4 || player.v_count >= 4 || player.rd_count >= 4 || player.ld_count >= 4) {
				return true;
			}
		}

//		System.out.println(player.h_count + " " + player.v_count + " " + player.rd_count + " " + player.ld_count);
		return false;
	}

}
