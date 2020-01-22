import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class 인구이동 {
	static public class map {
		int row;
		int col;

		public map(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}

		public int getRow() {
			return row;
		}

		public int getCol() {
			return col;
		}

	}

	private static int N;
	private static int R;
	private static int L;
	private static int[][] map;
	private static int[][] visited;
	private static int[] dr = { -1, 1, 0, 0 };
	private static int[] dc = { 0, 0, -1, 1 };
	private static int[][] backup;
	private static LinkedList[] arealist;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		backup = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				backup[i][j] = map[i][j];
			}
		}

		int time = 0;
		while (true) {
			int num = 1;
			arealist = new LinkedList[N * N];
			for (int i = 0; i < N * N; i++) {
				arealist[i] = new LinkedList<map>();
			}

			visited = new int[N][N];
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					if (visited[j][k] == 0) {
						dfs(j, k, num);
						num++;
					}
				}
			}

			// 각 구역별로 국경 개방.
			for (int i = 0; i < num - 1; i++) {
				LinkedList<map> temp = arealist[i];
				int total = 0;
				int gesu = 0;
				for (int j = 0; j < temp.size(); j++) {
					map x=temp.get(j);
					total+=map[x.row][x.col];
				}
				int people = total / temp.size();
				// 다시 돌면서 평균으로 채워준다.
				for (int j = 0; j < temp.size(); j++) {
					map x=temp.get(j);
					map[x.row][x.col]=people;
				}
			}

			// 이전 맵과 비교
			boolean flag = true;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (backup[i][j] != map[i][j]) {
						flag = false;
						break;
					}
					if (!flag)
						break;
				}
			}
			if (flag) {
				System.out.println(time);
				return;
			} else {
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						backup[i][j] = map[i][j];
					}
				}
			}
			time++;
		}

	}

	private static void dfs(int row, int col, int color) {
		arealist[color - 1].add(new map(row, col));
		visited[row][col] = color;
		for (int d = 0; d < dc.length; d++) {
			int nr = row + dr[d];
			int nc = col + dc[d];
			if (nr >= 0 && nr < N && nc >= 0 && nc < N && visited[nr][nc] == 0) {
				int gap = Math.abs(map[row][col] - map[nr][nc]);
				if (gap >= L && gap <= R) {
					dfs(nr, nc, color);
				}
			}
		}

	}
}
