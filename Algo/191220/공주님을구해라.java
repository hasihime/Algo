import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 공주님을구해라 {
	private static int N;
	private static int M;
	private static int[][] map;
	private static int T;
	private static int[] dx = { 0, 0, -1, 1 };
	private static int[] dy = { -1, 1, 0, 0 };
	private static boolean[][] visited;
	static Queue<int[]> q;

	// TODO Auto-generated method stub

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		visited = new boolean[N][M];
		int gr = 0;
		int gc = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					gr = i;
					gc = j;
				}
			}
		}

		q = new LinkedList<int[]>();
		visited[0][0] = true;

		q.add(new int[] { 0, 0 });
		int a = bfs(N - 1, M - 1);
		q.add(new int[] { 0, 0 });
		visited = new boolean[N][M];
		int b = bfs(gr, gc);

		if (a == 1000000000 && b == 1000000000) {
			System.out.println("fail");
		} else {
			int x = a < b ? a : b;
			if (x <= T) {
				System.out.println(x);
			} else {
				System.out.println("fail");
			}
		}

	}// 메인 끝

	private static int bfs(int tr, int tc) {
		int time = 0;
		while (q.isEmpty()) {
			int size = q.size();

			for (int t = 0; t < size; t++) {

				int[] output = q.poll();
				int row = output[0];
				int col = output[1];
				if (row == tr && col == tc) {
					if (tr == N - 1 && tc == M - 1) {
						return time;
					} else {
						time += (N - tr - 1);
						time += (M - tc - 1);
						return time;
					}
				}

				for (int i = 0; i < 4; i++) {
					int nr = row + dx[i];
					int nc = col + dy[i];
					if (nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] != 1 && !visited[nr][nc]) {
						q.add(new int[] { nr, nc });
						visited[nr][nc] = true;
					}
				}
			}
			time++;
		}
		return 1000000000;

	}

}
