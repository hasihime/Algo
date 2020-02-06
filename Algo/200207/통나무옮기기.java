import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class 통나무옮기기 {

	private static char[][] map;
	private static boolean[][][] visited;
	private static int[] dr = { -1, 1, 0, 0, -1, -1, 1, 1 };
	private static int[] dc = { 0, 0, -1, 1, -1, 1, -1, 1 };
	private static int n;
	private static boolean[][] endmark;
	private static int[] curR;
	private static int[] curC;
	private static Integer curD;
	private static int[] tempr;
	private static int[] tempc;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new char[n][n];
		visited = new boolean[n][n][3];
		endmark = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			map[i] = br.readLine().toCharArray();
		}
		int fr[] = new int[3];
		int fc[] = new int[3];
		int fidx = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] == 'B') {
					fr[fidx] = i;
					fc[fidx] = j;
					fidx++;
				} else if (map[i][j] == 'E') {
					endmark[i][j] = true;
				}
			}
		}
		// dir 0이면 세로 1이면 가로
		int fdir = 0;
		if (fr[0] == fr[1]) {
			fdir = 1;
		}

		markingV(fr, fc, fdir);
		Queue<int[]> qr = new LinkedList<int[]>();
		Queue<int[]> qc = new LinkedList<int[]>();
		Queue<Integer> qd = new LinkedList<Integer>();
		qr.add(fr);
		qc.add(fc);
		qd.add(fdir);
		int t = 0;
		while (!qr.isEmpty()) {
			int size = qr.size();
			for (int i = 0; i < size; i++) {
				curR = qr.poll();
				curC = qc.poll();
				curD = qd.poll();
				if (isend(curR, curC)) {
					System.out.println(t);
					return;
				}
				// 상하좌우 이동
				for (int d = 0; d < 4; d++) {
					if (canmove(d)) {
						tempr = new int[3];
						tempc = new int[3];
						move(d);
						markingV(tempr, tempc, curD);
						qr.add(tempr);
						qc.add(tempc);
						qd.add(curD);
					}
				}
				// 회전 가능 확인
				if (canturn()) {
					tempr = new int[3];
					tempc = new int[3];
					turn();
					markingV(tempr, tempc, 2);
					markingV(tempr, tempc, curD);
					qr.add(tempr);
					qc.add(tempc);
					qd.add(curD);
				}
			}
			t++;
		} // while 끝
		System.out.println(0);

	}

	private static void turn() {
		// 세로면가로로
		if (curD == 0) {
			tempr[0] = curR[1];
			tempr[1] = curR[1];
			tempr[2] = curR[1];
			tempc[0] = curC[1] - 1;
			tempc[1] = curC[1];
			tempc[2] = curC[1] + 1;
			curD = 1;

		}
		// 가로면 세로로
		else {
			tempc[0] = curC[1];
			tempc[1] = curC[1];
			tempc[2] = curC[1];
			tempr[0] = curR[1] - 1;
			tempr[1] = curR[1];
			tempr[2] = curR[1] + 1;
			curD = 0;
		}
	}

	private static boolean canturn() {
		int vcnt = 0;
		for (int d = 0; d < dr.length; d++) {
			int nr = curR[1]+dr[d];
			int nc = curC[1]+dc[d];
			if (nr < 0 || nr >= n || nc < 0 || nc >= n || map[nr][nc] == '1') {
				return false;
			}else {
				if (visited[nr][nc][2]) {
					vcnt++;
				}
			}
		}
		if (vcnt == 4) {
			return false;
		}
		return true;
	}

	private static boolean canmove(int d) {
		int vcnt = 0;
		for (int i = 0; i < 3; i++) {
			int nr = curR[i] + dr[d];
			int nc = curC[i] + dc[d];
			if (nr < 0 || nr >= n || nc < 0 || nc >= n || map[nr][nc] == '1') {
				return false;
			} else {
				if (visited[nr][nc][curD]) {
					vcnt++;
				}
			}
		}
		if (vcnt == 3) {
			return false;
		}
		return true;
	}

	private static void move(int d) {
		for (int i = 0; i < 3; i++) {
			int nr = curR[i] + dr[d];
			int nc = curC[i] + dc[d];
			tempr[i] = nr;
			tempc[i] = nc;
		}

	}

	private static void markingV(int[] fr, int[] fc, int dir) {
		for (int i = 0; i < 3; i++) {
			visited[fr[i]][fc[i]][dir] = true;
		}

	}

	private static boolean isend(int[] curR, int[] curC) {
		for (int i = 0; i < 3; i++) {
			if (!endmark[curR[i]][curC[i]]) {
				return false;
			}
		}
		return true;
	}

}
