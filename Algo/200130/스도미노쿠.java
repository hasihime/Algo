import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 스도미노쿠 {

	private static int[][] map;
	private static int N;
	private static StringTokenizer st;
	private static BufferedReader br;
	private static ArrayList<on> originnum;
	private static int[] blankarr;
	private static int[] dr = { -1, 1, 0, 0 };
	private static int[] dc = { 0, 0, -1, 1 };

	public static class on {
		int row;
		int col;

		public on(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}

	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine(), " ");

		int T = 1;
		while (true) {
			N = Integer.parseInt(st.nextToken());
			blankarr = new int[81];
			if (N == 0)
				break;
			map = new int[9][9];
			makemap();

			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					if (map[i][j] != 0) {
						blankarr[i * 9 + j] = -1;
					} else {
						blankarr[i * 9 + j] = 1;
					}
				}
			}
			// 만들자.
			makeblock(0);

			T++;
		}

	}

	private static void makeblock(int idx) {
		for (int i = idx; i < 81; i++) {
			if (blankarr[i] == -1) {
				// 블록을 넣자.
				for (int d = 0; d < 4; d++) {
					if (ispossible(i / 9, i % 9, d)) {
						//숫자 조합을 만들자.
						findnum(i / 9, i % 9, d);
						//숫자 넣고 다음거로
						putblock(i / 9, i % 9, 1, d);
						makeblock(idx + 1);
						putblock(i / 9, i % 9, -1, d);
					}

				}

			}
		}

	}

	private static void findnum(int row, int col, int d) {
		int nr = row + dr[d];
		int nc = col + dc[d];
		boolean rc[]=new boolean[10];
		boolean nrc[]=new boolean[10];
		if(!checkgaro()&&!checksero()&&!checkarea()) {
			
		}
		
	}

	private static boolean checkarea() {
		// TODO Auto-generated method stub
		return false;
	}

	private static boolean checksero() {
		// TODO Auto-generated method stub
		return false;
	}

	private static boolean checkgaro() {
		// TODO Auto-generated method stub
		return false;
	}

	// 그 맵에 넣을수 있는지.
	private static boolean ispossible(int row, int col, int d) {
		int nr = row + dr[d];
		int nc = col + dc[d];
		if (nr >= 0 && nr < 9 && nc >= 0 && nc < 9 && map[nr][nc] == 0) {
			return true;
		}
		return false;
	}

	private static void putblock(int row, int col, int visited, int dir) {
		int nr = row + dr[dir];
		int nc = col + dc[dir];
		blankarr[row*9+col]=visited;
		blankarr[nr*9+nc]=visited;
	}

	private static void makemap() throws IOException {
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int num1 = Integer.parseInt(st.nextToken());
			String sn1 = st.nextToken();
			int rn1 = sn1.substring(0, 1).charAt(0) - 'A';
			int cn1 = Integer.parseInt(sn1.substring(1)) - 1;
			map[rn1][cn1] = num1;
			originnum.add(new on(rn1, cn1));
			int num2 = Integer.parseInt(st.nextToken());
			String sn2 = st.nextToken();
			int rn2 = sn2.substring(0, 1).charAt(0) - 'A';
			int cn2 = Integer.parseInt(sn2.substring(1)) - 1;
			map[rn2][cn2] = num2;
			originnum.add(new on(rn2, cn2));
		}
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= 9; i++) {
			String sn1 = st.nextToken();
			int rn1 = sn1.substring(0, 1).charAt(0) - 'A';
			int cn1 = Integer.parseInt(sn1.substring(1)) - 1;
			map[rn1][cn1] = i;
			originnum.add(new on(rn1, cn1));
		}

	}

}
