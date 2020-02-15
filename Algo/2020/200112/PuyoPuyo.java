import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PuyoPuyo {
	private static int dr[] = { -1, 1, 0, 0 };
	private static int dc[] = { 0, 0, -1, 1 };
	private static char[][] map;
	private static int cnt;
	private static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new char[12][6];
		for (int i = 0; i < 12; i++) {
			map[i] = br.readLine().toCharArray();
		}

		int combo = 0;

		while (true) {
			boolean flag = false;
			for (int i = 0; i < 12; i++) {
				for (int j = 0; j < 6; j++) {
					if (map[i][j] != '.') {
						cnt = 1;
						visited=new boolean[12][6];
						isbreak(i, j, map[i][j]);
						if (cnt>=4) {
							gobreak(i, j, map[i][j]);
							flag = true;
						}
					}
				}
			} // for끝
			if (flag) {
				combo++;
				// 끌어내린다.
				gravity();
			} else {
				break;
			}
		}
		System.out.println(combo);

	}

	private static void gravity() {

		for (int i = 0; i < 6; i++) {
			char[] temp = new char[12];
			for (int j = 0; j < 12; j++) {
				temp[j] = '.';
			}

			int idx = 0;
			for (int j = 11; j >= 0; j--) {
				if (map[j][i] != '.') {
					temp[idx] = map[j][i];
					idx++;
				}
			}
			for (int j = 11; j >= 0; j--) {
				map[j][i] = temp[11 - j];
			}
		}

	}

	private static void gobreak(int row, int col, char txt) {
		map[row][col] = '.';
		for (int i = 0; i < dc.length; i++) {
			int nr = row + dr[i];
			int nc = col + dc[i];
			if (nr >= 0 && nr < 12 && nc >= 0 && nc < 6 && map[nr][nc] == txt) {

				gobreak(nr, nc, txt);
			}
		}

	}

	private static void isbreak(int row, int col, char txt) {
		visited[row][col]=true;
		for (int i = 0; i < dc.length; i++) {
			int nr = row + dr[i];
			int nc = col + dc[i];
			if (nr >= 0 && nr < 12 && nc >= 0 && nc < 6&&
					!visited[nr][nc]&& map[nr][nc] == txt) {
				cnt++;
				isbreak(nr, nc, txt);
			}
		}
	}

}
