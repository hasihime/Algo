import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 직사각형탈출 {

	private static int result;
	private static int[] dr = { -1, 1, 0, 0 };
	private static int[] dc = { 0, 0, -1, 1 };
	private static int rr;
	private static int rc;
	private static int[][] map;
	private static int r;
	private static int c;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		map = new int[r][c];
		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < c; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		rr = Integer.parseInt(st.nextToken());
		rc = Integer.parseInt(st.nextToken());
		boolean[][] visited = new boolean[r][c];
		int sr = Integer.parseInt(st.nextToken())-1;
		int sc = Integer.parseInt(st.nextToken())-1;
		Queue<Integer> qr = new LinkedList<Integer>();
		Queue<Integer> qc = new LinkedList<Integer>();
		qr.add(sr);
		qc.add(sc);
		int er = Integer.parseInt(st.nextToken())-1;
		int ec = Integer.parseInt(st.nextToken())-1;
		result = -1;
		int time = 0;
		while (!qr.isEmpty()) {
			int size = qr.size();

			for (int i = 0; i < size; i++) {
				int cr = qr.poll();
				int cc = qc.poll();
				if (cr == er && cc == ec) {
					System.out.println(time);
					return;
				}
				for (int d = 0; d < dr.length; d++) {
					int nr=cr+dr[d];
					int nc=cc+dc[d];
					if (nr>=0&&nr<r&&
							nc>=0&&nc<c&&
							!visited[nr][nc]
									&&map[nr][nc]!=1) {
						if (check(nr,nc)) {
							visited[nr][nc]=true;
							qr.add(nr);
							qc.add(nc);
						}
					}
				}
				
			}
			time++;
		} // while끝
		System.out.println(result);
	}// 메인 끝

	private static boolean check(int row, int col) {
		for (int i = row; i < row+rr; i++) {
			for (int j = col; j < col+rc; j++) {
				if (i<0||i>=r||j<0||j>=c||map[i][j]==1) {
					return false;
				}
			}
		}
		return true;
	}

}
