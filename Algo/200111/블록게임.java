import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 블록게임 {

	private static int M;
	private static int N;
	private static int K;
	private static char[][] map;
	private static int[][] blockmap;
	private static int[] dr = { -1, 1, 0, 0 };
	private static int[] dc = { 0, 0, -1, 1 };
	private static int[] sr = { -1, -1, 1, 1 };
	private static int[] sc = { -1, 1, 1, -1 };
	private static int[][] blockmap2;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		// 0.5단위로 들어오니깐 2배를 해준다.
		K = (int) (Double.parseDouble(st.nextToken()) * (2.0));

		map = new char[((2 * N) + 1)][((2 * M) + 1)];

		for (int i = 0; i < ((2 * N) + 1); i++) {
			String input = br.readLine();
			for (int j = 0; j < ((2 * M) + 1); j++) {
				map[i][j] = input.charAt(j);
			}
		} // 입력 끝

		blockmap = new int[N][M];
		// 블록의 연결 유무를 판단한다.
		int blocknum = 1;

		for (int i = 1; i < (2 * N) + 1; i++) {
			for (int j = 1; j < (2 * M) + 1; j++) {
				if (map[i][j] == 'B' && blockmap[i / 2][j / 2] == 0) {
					findblock(i, j, blocknum);
					blocknum++;
				}
			}
		}

		// 2배 사이즈 맵을 만든다.
		make2map();
		// 45도로 공을 쏜다.
		int result = shoot();

		System.out.println(result);

	}

	private static void make2map() {
		blockmap2 = new int[(2 * N) + 1][2 * M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				blockmap2[(2 * i)][(2 * j)] = blockmap[i][j];
				blockmap2[(2 * i) + 1][(2 * j)] = blockmap[i][j];
				blockmap2[(2 * i)][(2 * j) + 1] = blockmap[i][j];
				blockmap2[(2 * i) + 1][(2 * j) + 1] = blockmap[i][j];
			}
		}

	}

	private static int shoot() {
		int bb = 0;
		int dir = 0;
		int nr = (2 * N);
		int nc = K;
		while (true) {

			// 벽이면 방향을 바꿔준다.
			nr += sr[dir];
			nc += sc[dir];
			if (nr == (2 * N)+2) {
				break;
			}
			// 블록을 만난다면
			if (nr >= 0 && nr < 2 * N && nc >= 0 && nc < 2 * M && blockmap2[nr][nc] != 0) {
				bb++;
				// 나머지 블록은 0으로 만든다.
				breakblock(nr, nc, blockmap2[nr][nc]);
			}

			if (nr == 0 || nc == 0 || nc == 2 * M) {
				dir++;
				dir%=4;
			}
		}

		return bb;
	}

	private static void breakblock(int row, int col, int blocknum) {
		blockmap2[row][col] = 0;
		for (int i = 0; i < dr.length; i++) {
			int nr = row + dr[i];
			int nc = col + dc[i];
			if (nr >= 0 && nr < 2 * N && nc >= 0 && nc < 2 * M && blockmap2[nr][nc] == blocknum) {
				breakblock(nr, nc, blocknum);
			}
		}

	}

	private static void findblock(int row, int col, int paintnum) {
		blockmap[row / 2][col / 2] = paintnum;
		for (int i = 0; i < dr.length; i++) {
			int nr = row + dr[i];
			int nc = col + dc[i];
			int n2r = nr + dr[i];
			int n2c = nc + dc[i];
			if (map[nr][nc] == '.' && blockmap[n2r / 2][n2c / 2] != paintnum) {
				findblock(n2r, n2c, paintnum);
			}
		}
	}

}
