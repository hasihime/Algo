import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 빙산 {

	private static int N;
	private static int M;
	private static int[][] map;
	private static boolean[][] visited;
	private static int[] dx = { -1, 1, 0, 0 };
	private static int[] dy = { 0, 0, -1, 1 };
	private static int[][] newmap;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} // 입력 끝

		int time = 0;
		while (true) {
			// 빙산이 두조각인지 먼저 확인한다.
			visited = new boolean[N][M];
			int icecnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] > 0 && !visited[i][j]) {
						dfs(i, j);
						icecnt++;
					}
				}
			} // 끝
			
			//빙산이 두개로 나눠지면 시간 출력
			if (icecnt >= 2) {
				System.out.println(time);
				return;
			}
			//빙산 개수가 0이면 다 녹은거니 0출력
			else if(icecnt==0) {
				System.out.println(0);
				return;
			}

			// 바닷물을 녹인다.
			newmap = new int[N][M];
			breakice();
			map = newmap;
			time++;
		}

	}

	private static void breakice() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] > 0) {
					int seacnt = 0;
					for (int d = 0; d < dx.length; d++) {
						int nr = i + dx[d];
						int nc = j + dy[d];
						if (map[nr][nc] == 0)
							seacnt++;
					}
					newmap[i][j] = Math.max(0, map[i][j] - seacnt);
				}
			}
		}
	}

	private static void dfs(int row, int col) {
		visited[row][col] = true;

		for (int d = 0; d < dx.length; d++) {
			int nr = row + dx[d];
			int nc = col + dy[d];
			if (map[nr][nc] > 0 && !visited[nr][nc]) {
				dfs(nr, nc);
			}
		}

	}

}
