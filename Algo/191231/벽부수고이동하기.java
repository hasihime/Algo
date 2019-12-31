import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 벽부수고이동하기 {
	private static int[][] map;
	private static int R;
	private static int C;
	private static boolean[][][] visited;
	private static int[] dx = { 0, 0, -1, 1 };
	private static int[] dy = { -1, 1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];

		String[] wall = new String[1000000];
		int wallcnt = 0;

		for (int i = 0; i < R; i++) {
			String[] line = br.readLine().split("");
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(line[j]);
				if (map[i][j] == 1) {
					wall[wallcnt] = i + " " + j;
					wallcnt++;
				}
			}
		} // 입력 끝;

		// 처음은 그냥 탐색
		int result = 100000000;
		int time = 0;
		visited = new boolean[2][R][C];
		Queue<Integer> qr = new LinkedList<Integer>();
		Queue<Integer> qc = new LinkedList<Integer>();
		Queue<Integer> qk = new LinkedList<Integer>();

		qr.add(0);
		qc.add(0);
		qk.add(1);
		boolean flag = false;
		visited[0][0][0] = true;

		while (!qr.isEmpty()) {
			int size = qr.size();
			for (int i = 0; i < size; i++) {
				int r = qr.poll();
				int c = qc.poll();
				int k = qk.poll();

				if (r == R - 1 && c == C - 1) {
					result = time;
					flag = true;
					break;
				}

				for (int d = 0; d < 4; d++) {
					int nr = r + dy[d];
					int nc = c + dx[d];
					if (k == 1) {
						if (nr >= 0 && nr < R && nc >= 0 && nc < C && !visited[0][nr][nc] && map[nr][nc] == 0) {
							qr.add(nr);
							qc.add(nc);
							qk.add(1);
							visited[0][nr][nc] = true;
						}
						if (nr >= 0 && nr < R && nc >= 0 && nc < C && !visited[0][nr][nc] && map[nr][nc] == 1) {
							qr.add(nr);
							qc.add(nc);
							qk.add(0);
							visited[1][nr][nc] = true;
						}
					} else {
						if (nr >= 0 && nr < R && nc >= 0 && nc < C && !visited[1][nr][nc] && map[nr][nc] == 0) {
							qr.add(nr);
							qc.add(nc);
							qk.add(0);
							visited[1][nr][nc] = true;
						}
					}

				}
			}
			if (flag) {
				break;
			}
			time++;
		} // while끝

		if (result == 100000000) {
			System.out.println(-1);
		} else {
			System.out.println(result + 1);
		}
	}
}