import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 개리멘더링2 {

	private static int N;
	private static int[][] map;
	private static int result;
	private static int d1r;
	private static int d1c;
	private static int d2r;
	private static int d2c;
	private static int d3r;
	private static int d3c;
	private static int[] dr = { 1, 1, -1, -1 };
	private static int[] dc = { -1, 1, 1, -1 };
	private static int[] sr = { -1, 1, 0, 0 };
	private static int[] sc = { 0, 0, -1, 1 };
	private static int[][] visited;
	private static int d5;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		result = 1000000000;
		for (int i = 0; i < N ; i++) {
			for (int j = 0; j < N; j++) {
				peekd1(i, j);

			}
		}
		System.out.println(result);
	}

	private static void peekd1(int row, int col) {
		d1r = row;
		d1c = col;
		while (true) {
			d1r++;
			d1c--;
			if (d1r == N || d1c == -1) {
				break;
			}
			peekd2(row, col);
		}

	}

	private static void peekd2(int row, int col) {
		d2r = row;
		d2c = col;
		while (true) {
			d2r++;
			d2c++;
			if (d2r == N || d2c == N) {
				break;
			}
			d3r = (d2r + d1r) - row;
			d3c = (d2c + d1c) - col;
			if (d3r >= N || d3c >= N) {
				continue;
			} else {
				getsum(row, col);
			}
		}

	}

	private static void getsum(int row, int col) {
		visited = new int[N][N];

		// 구역 5부터 칠한다.
		d5 = 0;
		area5(row, col);
		// 내부를 채운다.
		dfs(row, col);
		// 각 구역별로 2중for문 돌린다. 대신 5인 구간은 넘긴다.
		int d1 = 0;
		d1 = sumd1(col, d1);
		int d2=0;
		d2 = sumd2(col, d2);
		int d3=0;
		d3 = sumd3(d3);
		int d4=0;
		d4 = sumd4(d4);
		
		int tempmax=Math.max(d1, Math.max(d2, Math.max(d3,Math.max(d4, d5))));
		int tempmin=Math.min(d1, Math.min(d2, Math.min(d3,Math.min(d4, d5))));
		result=result>(tempmax-tempmin)?(tempmax-tempmin):result;
	}

	private static int sumd4(int d4) {
		for (int i = d2r+1; i <N; i++) {
			for (int j = d3c; j < N; j++) {
				if (visited[i][j] != 5) {
					d4 += map[i][j];
				}
			}
		}
		return d4;
	}

	private static int sumd3(int d3) {
		for (int i = d1r; i <N; i++) {
			for (int j = 0; j < d3c; j++) {
				if (visited[i][j] != 5) {
					d3 += map[i][j];
				}
			}
		}
		return d3;
	}

	private static int sumd2(int col, int d2) {
		for (int i = 0; i <= d2r; i++) {
			for (int j = col+1; j < N; j++) {
				if (visited[i][j] != 5) {
					d2 += map[i][j];
				}
			}
		}
		return d2;
	}

	private static int sumd1(int col, int d1) {
		for (int i = 0; i < d1r; i++) {
			for (int j = 0; j <= col; j++) {
				if (visited[i][j] != 5) {
					d1 += map[i][j];
				}
			}
		}
		return d1;
	}

	private static void area5(int row, int col) {
		// 좌하
		int nr = row;
		int nc = col;
		while (true) {
			if (nr == d1r && nc == d1c) {
				break;
			}
			visited[nr][nc] = 5;
			d5+=map[nr][nc];
			nr += dr[0];
			nc += dc[0];
		}
		while (true) {
			if (nr == d3r && nc == d3c) {
				break;
			}
			visited[nr][nc] = 5;
			d5+=map[nr][nc];
			nr += dr[1];
			nc += dc[1];
		}
		while (true) {
			if (nr == d2r && nc == d2c) {
				break;
			}
			visited[nr][nc] = 5;
			d5+=map[nr][nc];
			nr += dr[2];
			nc += dc[2];
		}
		while (true) {
			if (nr == row && nc == col) {
				break;
			}
			visited[nr][nc] = 5;
			d5+=map[nr][nc];
			nr += dr[3];
			nc += dc[3];
		}
	}

	private static void dfs(int row, int col) {

		for (int i = row + 1; i < d3r; i++) {
			int cnt = 0;
			for (int j = 0; j < N; j++) {
				if (visited[i][j] == 5) {
					cnt++;
					continue;
				}
				if (cnt == 1) {
					visited[i][j] = 5;
					d5+=map[i][j];
				} else if (cnt == 2) {
					break;
				}

			}
		}
	}

}
