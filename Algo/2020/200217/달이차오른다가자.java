import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 달이차오른다가자 {

	private static int R;
	private static int C;
	private static char[][] map;
	private static metrix sm;
	private static ArrayList<metrix> dlist;
	private static Queue<metrix> qm;
	private static Queue<Integer> qcnt;
	private static Queue<Integer> qk;
	private static boolean[][][] visited;
	private static int result;
	private static int[] dr = { -1, 1, 0, 0 };
	private static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		input();
		bfs();

	}

	private static void bfs() {
		int t = 0;
		while (!qm.isEmpty()) {
			int size = qm.size();
			while (size > 0) {
				metrix cm = qm.poll();
				int cnt = qcnt.poll();
				int ck = qk.poll();
				// 1도착 여부
				for (int i = 0; i < dlist.size(); i++) {
					if (cm.row == dlist.get(i).row&&cm.col==dlist.get(i).col) {
						System.out.println(cnt);
						return;
					}
				}
				for (int d = 0; d < dr.length; d++) {
					int nr = cm.row + dr[d];
					int nc = cm.col + dc[d];
					if (nr < 0 || nr >= R || nc < 0 || nc >= C)
						continue;
					if (map[nr][nc] == '#')
						continue;
					if (visited[ck][nr][nc])
						continue;
					// 열쇠인경우
					if ('a' <= map[nr][nc] && map[nr][nc] <= 'f') {
						// a면 100000 b면 010000 c면 001000 d면 000100 e면 000010 f면 000001
						String input = Integer.toBinaryString(ck);
						if (input.length() != 6) {
							int len = input.length();
							for (int i = 0; i < 6 - len; i++) {
								input = "0" + input;
							}
						}
						char[] nck = input.toCharArray();
						if (nck[map[nr][nc] - 'a'] == '0') {
							nck[map[nr][nc] - 'a'] = '1';
						}
						int nk = 0;
						for (int i = 5; i >= 0; i--) {
							if (nck[i] == '1') {
								nk += Math.pow(2, (5 - i));
							}
						}
						visited[ck][nr][nc] = true;
						visited[nk][nr][nc] = true;
						qm.add(new metrix(nr, nc));
						qcnt.add(cnt + 1);
						qk.add(nk);
					}
					// 문인경우
					else if ('A' <= map[nr][nc] && map[nr][nc] <= 'F') {
						String input = Integer.toBinaryString(ck);
						if (input.length() != 6) {
							int len = input.length();
							for (int i = 0; i < 6 - len; i++) {
								input = "0" + input;
							}
						}
						char[] nck = input.toCharArray();
						// 문에 해당하는 키가 있으면 열고 큐에 넣는다.
						if (nck[map[nr][nc] - 'A'] == '1') {
							visited[ck][nr][nc] = true;
							qm.add(new metrix(nr, nc));
							qcnt.add(cnt + 1);
							qk.add(ck);
						}
					}
					// 나머지는 큐에 넣는다.
					else {
						visited[ck][nr][nc] = true;
						qm.add(new metrix(nr, nc));
						qcnt.add(cnt + 1);
						qk.add(ck);
					}

				}
				size--;
			} //
			t++;
		}
		System.out.println(-1);
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		dlist = new ArrayList<metrix>();
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] == '0') {
					sm = new metrix(i, j);
				} else if (map[i][j] == '1') {
					dlist.add(new metrix(i, j));
				}
			}
		}
		qm = new LinkedList<metrix>();
		qcnt = new LinkedList<Integer>();
		qk = new LinkedList<Integer>();
		visited = new boolean[64][R][C];
		qm.add(sm);
		qcnt.add(0);
		qk.add(0);
		result = 0;
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
