import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 열쇠 {
	private static HashSet<Character> kset;
	private static HashSet<metrix> dset;
	private static char[][] map;
	private static int m;
	private static int n;
	private static BufferedReader br;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	private static StringTokenizer st;

	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		while (T > 0) {
			// 맵 만들기
			initializemap();
			// 키를 입력받아 set에 넣는다
			keyinput();
			// bfs 탐색
			bfs();
			T--;
		} // while 끝
	}

	private static void bfs() {
		Queue<metrix> q = new LinkedList<metrix>();
		q.add(new metrix(0, 0));
		boolean[][] visited = new boolean[n + 2][m + 2];
		visited[0][0] = true;
		int count = 0;

		while (!q.isEmpty()) {
			metrix curm = q.poll();

			for (int d = 0; d < 4; d++) {
				int nr = curm.row + dr[d];
				int nc = curm.col + dc[d];

				if (nr < 0 || nr > n + 1 || nc < 0 || nc > m + 1 || visited[nr][nc] || map[nr][nc] == '*')
					continue;
				// 키를 발견한 경우.
				if ('a' <= map[nr][nc] && map[nr][nc] <= 'z') {
					// 키 셋에 넣어준다.
					kset.add(map[nr][nc]);
					// 이 키로 열수 있는 문을 다 열어주고 큐에 문 좌표를 넣어준다.
					for (metrix cm : dset) {
						if (map[cm.row][cm.col] == map[nr][nc] - 32) {
							if (visited[cm.row][cm.col])
								continue;
							visited[cm.row][cm.col] = true;
							q.add(new metrix(cm.row, cm.col));
							// 문을 열고 셋에서 지워버린다.
							// dset.remove(new metrix(cm.row, cm.row));
						}
					}
				}
				// 문을 발견한 경우
				else if ('A' <= map[nr][nc] && map[nr][nc] <= 'Z') {
					// 이 문을 열수 없다면 door set에 넣어준다.
					boolean pass = false;
					for (Character ck : kset) {
						char t = map[nr][nc];
						// 지금 문이 키가 있으면 지나갈수 있는것.
						if (t == ck - 32) {
							pass = true;
							break;
						}
					}
					if (!pass) {
						dset.add(new metrix(nr, nc));
						continue;
					}
				}
				// 문서를 발견한 경우
				else if (map[nr][nc] == '$') {
					count++;
				}
				// 통과할 수 있으면 그다음 좌표 넣어준다.
				visited[nr][nc] = true;
				q.add(new metrix(nr, nc));
			}
		} // BFS 끝
		System.out.println(count);
	}

	private static void keyinput() throws IOException {
		String kline = br.readLine();
		boolean[] karr = new boolean[26];
		if (!kline.equals("0")) {
			for (int i = 0; i < kline.length(); i++) {
				kset.add(kline.charAt(i));
			}
		}
	}

	private static void initializemap() throws IOException {
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		kset = new HashSet<Character>();
		dset = new HashSet<metrix>();
		// map을 상하좌우 전부 지나갈수 있는 길로 감싼다.
		map = new char[n + 2][m + 2];
		for (int i = 0; i < n + 2; i++) {
			for (int j = 0; j < m + 2; j++) {
				map[i][j] = '.';
			}
		}
		for (int i = 1; i <= n; i++) {
			String line = br.readLine();
			for (int j = 1; j <= m; j++) {
				map[i][j] = line.charAt(j - 1);
			}
		}
	}

	public static class metrix {
		int row;
		int col;

		public metrix(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}
	}
}
