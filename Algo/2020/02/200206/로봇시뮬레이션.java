import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 로봇시뮬레이션 {
	public static class robot {
		int row;
		int col;
		int dir;

		public robot(int row, int col, int dir) {
			super();
			this.row = row;
			this.col = col;
			this.dir = dir;
		}

		public int getRow() {
			return row;
		}

		public void setRow(int row) {
			this.row = row;
		}

		public int getCol() {
			return col;
		}

		public void setCol(int col) {
			this.col = col;
		}

		public int getDir() {
			return dir;
		}

		public void setDir(int dir) {
			this.dir = dir;
		}

	}

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int[] dd = { 0, 1, 2, 3 };
	static char[] dir = { 'N', 'E', 'S', 'W' };
	private static int[][] map;
	private static int m;
	private static int n;
	private static boolean flag;
	private static StringBuilder sb;
	private static robot[] robotarr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();

		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int o = Integer.parseInt(st.nextToken());
		robotarr = new robot[r + 1];
		for (int i = 1; i <= r; i++) {
			st = new StringTokenizer(br.readLine());
			int cc = Integer.parseInt(st.nextToken()) - 1;
			int cr = n - Integer.parseInt(st.nextToken());
			char cd = st.nextToken().charAt(0);
			int ccd = 0;
			switch (cd) {
			case 'N':
				ccd = 0;
				break;
			case 'E':
				ccd = 1;
				break;
			case 'S':
				ccd = 2;
				break;
			case 'W':
				ccd = 3;
				break;

			}
			map[cr][cc] = i;
			robotarr[i] = new robot(cr, cc, ccd);
		}
		while (o > 0) {
			st = new StringTokenizer(br.readLine());
			int curR = Integer.parseInt(st.nextToken());
			char order = st.nextToken().charAt(0);
			int cnt = Integer.parseInt(st.nextToken());

			for (int i = 0; i < cnt; i++) {
				flag=true;
				switch (order) {
				case 'F':
					move(curR,robotarr[curR]);
					break;
				case 'L':
					int td=robotarr[curR].dir;
					if ((td == 0)) {
						robotarr[curR].setDir(3);
					} else {
						robotarr[curR].setDir((td - 1));
					}
					break;
				case 'R':
					int ttd=robotarr[curR].dir;
					robotarr[curR].setDir((ttd + 1) % 4);
					break;
				}//스위치 끝
				if (!flag) {
					break;
				}
			}//for 끝
			if (!flag) {
				System.out.println(sb);
				return;
			}
			o--;
		} // while끝
		System.out.println("OK");
	}

	private static void move(int robotnum,robot crb) {
		int nr=crb.row;
		int nc=crb.col;
		switch (crb.dir) {
		case 0:
			nr--;
			break;
		case 1:
			nc++;
			break;
		case 2:
			nr++;
			break;
		case 3:
			nc--;
			break;
		}
		if (nr<0||nr>=n||nc<0||nc>=m) {
			sb.append("Robot ").append(robotnum).append(" crashes into the wall");
			flag=false;
			return;
		}else if (map[nr][nc]!=0) {
			sb.append("Robot ").append(robotnum).append(" crashes into robot ")
			.append(map[nr][nc]);
			flag=false;
			return;
		}else {
			map[crb.row][crb.col]=0;
			map[nr][nc]=robotnum;
			robotarr[robotnum].setRow(nr);
			robotarr[robotnum].setCol(nc);
		}
	}

}
