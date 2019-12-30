import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 미세먼지안녕 {

	static int[][] map;
	static int[][] dustmap;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int N;
	static int M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		int result = 0;

		// 초기 환경 설정
		map = new int[N][M];
		int aircleaner = -1;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
			if (map[i][0] == -1) {
				aircleaner = i;
			}
		}

		int t = 0;
		while (t < T) {
			// 미세먼지가 확산된다.
			// 미세먼지 퍼지는 맵을 따로 만들자
			dustmap = new int[N][M];
			dustmap[aircleaner][0] = -1;
			dustmap[aircleaner - 1][0] = -1;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] > 0) {
						spread(i, j);
					}
				}
			}

			map = dustmap;
			// 먼지 이동
			// 위쪽부터
			moveup(aircleaner - 1);
			movedown(aircleaner);

			t++;
		} // while 끝

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				result+=map[i][j];
			}
		}
		System.out.println(result+2);

	}// 메인 끝

	private static void movedown(int row) {
		for (int i = row + 1; i < N - 1; i++) {
			map[i][0] = map[i + 1][0];
		}
		for (int i = 0; i < M - 1; i++) {
			map[N - 1][i] = map[N - 1][i + 1];
		}
		for (int i = N-1; i > row; i--) {
			map[i][M-1] = map[i - 1][M-1];
		}
		for (int i = M - 1; i > 1; i--) {
			map[row][i] = map[row][i - 1];
		}
		map[row][1] = 0;
	}

	private static void moveup(int row) {
		for (int i = row - 1; i > 0; i--) {
			map[i][0] = map[i - 1][0];
		}
		for (int i = 0; i < M - 1; i++) {
			map[0][i] = map[0][i + 1];
		}
		for (int i = 0; i < row ; i++) {
			map[i][M - 1] = map[i + 1][M - 1];
		}
		for (int i = M - 1; i > 1; i--) {
			map[row][i] = map[row][i - 1];
		}
		map[row][1] = 0;
	}

	public static void spread(int row, int col) {
		int gocount = 0;
		int dusttotal = map[row][col];
		for (int i = 0; i < 4; i++) {
			int nr = row + dx[i];
			int nc = col + dy[i];

			if (nr >= 0 && nr < N && nc >= 0 && nc < M && dustmap[nr][nc] != -1) {
				dustmap[nr][nc] += dusttotal / 5;
				gocount++;
			}
		} // for end
		dustmap[row][col] += dusttotal - (dusttotal / 5) * gocount;
	}

}