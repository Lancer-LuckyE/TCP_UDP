import java.lang.*;

public class XO_game {
	private char[][] game_state;

	XO_game() {
		game_state = new char[3][3];
		for (int i = 0; i < game_state.length; i++) {
			for (int j = 0; j < game_state[i].length; j++) {
				game_state[i][j] = '-';
			}
		}
	}

	public String toString() {
		String str = new String();
		for (int i = 0; i < game_state.length; i++) {
			for (int j = 0; j < game_state[i].length; j++) {
				str += game_state[i][j] + " ";
			}
			str += "\n";
		}
		return str;
	}

	private boolean move(byte row, byte col, char player) {
		boolean result = false;
		if (row >= 0 && row < 3 && col >= 0 && col < 3) {
			if (game_state[row][col] == '-') {
				game_state[row][col] = player;
				return true;
			}
		}
		return result;
	}

	public boolean x_move(byte row, byte col) {
		return move(row, col, 'X');
	}

	public boolean o_move(byte row, byte col) {
		return move(row, col, 'O');
	}

	public char game_result() {
		int[] row1 = { 0, 1, 2, 0, 0, 0, 0, 0 };
		int[] row2 = { 0, 1, 2, 1, 1, 1, 1, 1 };
		int[] row3 = { 0, 1, 2, 2, 2, 2, 2, 2 };
		int[] col1 = { 0, 0, 0, 0, 1, 2, 0, 2 };
		int[] col2 = { 1, 1, 1, 0, 1, 2, 1, 1 };
		int[] col3 = { 2, 2, 2, 0, 1, 2, 2, 0 };

		for (int i = 0; i < row1.length; i++) {
			if (game_state[row1[i]][col1[i]] == game_state[row2[i]][col2[i]]
					&& game_state[row2[i]][col2[i]] == game_state[row3[i]][col3[i]]) {
				return game_state[row1[i]][col1[i]];
			}
		}
		// check if the game actually ended
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (game_state[i][j] == '-') {
					return '-'; //dont done yet 
				}
			}
		}
		// must be a draw
		return 'd';
	}
}
