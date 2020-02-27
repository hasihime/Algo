import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 연구소 {

	private static int N;
	private static int M;
	private static int[][] map;
	private static int[][] cmap;
	private static int[] dr = { -1, 1, 0, 0 };
	private static int[] dc = { 0, 0, -1, 1 };
	private static int result;
	private static int minv;
	private static int[][] initv;

	public static void main(String[] args) throws IOException {
		input();
		go(0);
		System.out.println(result);
	}

	private static void go(int cnt) {
		if (cnt == 3) {
			// 맵 백업
			backup(map, cmap);
			// 벽 설치 끝
			bfs();
			// 맵 백업
			backup(cmap, map);
			return;
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) {
					map[i][j] = 1;
					go(cnt + 1);
					map[i][j] = 0;
				}
			}
		}
	}

	private static void backup(int[][] origin, int[][] copy) {
		// TODO Auto-generated method stub
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				copy[i][j] = origin[i][j];
			}
		}

	}

	private static void bfs() {
		Queue<Integer> qr = new LinkedList<Integer>();
		Queue<Integer> qc = new LinkedList<Integer>();
		boolean[][] curvisited = new boolean[N][M];
		for (int i = 0; i < 10; i++) {
			if (initv[i][0] == 1000) {
				break;
			}
			qr.add(initv[i][0]);
			qc.add(initv[i][1]);
			curvisited[initv[i][0]][initv[i][1]] = true;
		}
		int curv = qr.size();
		while (!qr.isEmpty()) {
			if (minv <= curv) {
				return;
			}

			int cr = qr.poll();
			int cc = qc.poll();
			for (int d = 0; d < 4; d++) {
				int nr = cr + dr[d];
				int nc = cc + dc[d];
				if (nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] == 0 && !curvisited[nr][nc]) {
					qr.add(nr);
					qc.add(nc);
					curv++;
					map[nr][nc] = 2;
					curvisited[nr][nc] = true;
				}
			}
		}
		int curcnt = gets();
		result = Math.max(result, curcnt);
	}

	private static int gets() {
		int a = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) {
					a++;
				}
			}
		}
		return a;
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		cmap = new int[N][M];
		initv = new int[10][2];
		for (int i = 0; i < 10; i++) {
			initv[i][0] = 1000;
		}
		result = 0;
		minv = Integer.MAX_VALUE;
		int idx = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					initv[idx][0] = i;
					initv[idx][1] = j;
					idx++;
				}
			}
		}
	}
}
