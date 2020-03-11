import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Maaaaaaaaaze {

	private static BufferedReader br;
	private static StringTokenizer st;
	private static int[][][][] map;
	private static int[] comarr;
	private static int[] roratearr;
	private static boolean[] comvisited;
	private static int result;
	static int[] dr = { -1, 1, 0, 0, 0, 0 };
	static int[] dc = { 0, 0, -1, 1, 0, 0 };
	static int[] dz = { 0, 0, 0, 0, -1, 1 };
	private static boolean is12;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		// 판번호,회전번호,가로,세로
		map = new int[5][4][5][5];
		comarr = new int[5];
		comvisited = new boolean[5];
		roratearr = new int[5];
		result = 10000;
		is12=false;
		input();
		maketurntable();
		makelayer(0);
		if (result==10000) {
			result=-1;
		}
		System.out.println(result);
	}

	private static void maketurntable() {
		for (int k = 0; k < 5; k++) {
			rotate(k);
		}

	}

	private static void rotate(int k) {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				map[k][1][i][j] = map[k][0][4 - j][i];
			}
		}
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				map[k][2][i][j] = map[k][1][4 - j][i];
			}
		}
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				map[k][3][i][j] = map[k][2][4 - j][i];
			}
		}

	}

	private static void makelayer(int target) {
		if (is12) {
			return;
		}
		if (target == 5) {
//			System.out.println("조합"+Arrays.toString(comarr));
			makerotate(0);
			return;
		}
		for (int i = 0; i < 5; i++) {
			if (!comvisited[i]) {
				comvisited[i] = true;
				comarr[target] = i;
				makelayer(target + 1);
				comvisited[i] = false;
			}
		}

	}

	private static void makerotate(int target) {
		if (is12) {
			return;
		}
		if (target == 5) {
//			System.out.println("회전번호"+Arrays.toString(roratearr));
			go();
			return;
		}
		for (int i = 0; i < 4; i++) {
			roratearr[target] = i;
			makerotate(target + 1);
		}

	}

	private static void go() {
		// 층,가로,세로
		int[][][] curmap = new int[5][5][5];
		for (int k = 0; k < map.length; k++) {
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					curmap[k][i][j] = map[comarr[k]][roratearr[k]][i][j];
				}
			}
		}
		Queue<Integer> qr = new LinkedList<Integer>();
		Queue<Integer> qc = new LinkedList<Integer>();
		Queue<Integer> qz = new LinkedList<Integer>();
		boolean[][][] mapvisited = new boolean[5][5][5];
		if (curmap[0][0][0]==1) {
			qr.add(0);
			qc.add(0);
			qz.add(0);
			mapvisited[0][0][0] = true;
		}else {
			return;
		}
		
		int curtime = 0;
		while (!qr.isEmpty()) {
			if (curtime >= result) {
				return;
			}
			int size = qr.size();
			for (int i = 0; i < size; i++) {
				int cr = qr.poll();
				int cc = qc.poll();
				int cz = qz.poll();
				if (cr == 4 && cc == 4 && cz == 4) {
					result = Math.min(result, curtime);
					if (curtime==12) {
						is12=true;
					}
					return;
				}

				for (int d = 0; d < 6; d++) {
					int nr = cr + dr[d];
					int nc = cc + dc[d];
					int nz = cz + dz[d];
					if (nr >= 0 && nr < 5 && nc >= 0 && nc < 5 && nz >= 0 && nz < 5 && 
							!mapvisited[nz][nr][nc]
							&& curmap[nz][nr][nc] == 1) {
						mapvisited[nz][nr][nc] = true;
						qr.add(nr);
						qc.add(nc);
						qz.add(nz);
					}

				}
			}
			curtime++;
		} // while끝

	}

	private static void input() throws IOException {
		for (int k = 0; k < map.length; k++) {
			for (int i = 0; i < 5; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 5; j++) {
					map[k][0][i][j] = Integer.parseInt(st.nextToken());
				}
			}
		}

	}

}
