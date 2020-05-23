import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/18809
public class Gaaaaaaaaaarden {

	private static int row;
	private static int col;
	private static int gseed;
	private static int rseed;
	private static int[][] map;
	private static int cansize;
	private static int[] permu;
	private static boolean[] checkper;
	private static int[] seedarr;
	private static int seedtotal;
	private static int result;
	private static LinkedList<matrix> canSeed;
	private static matrix[] canarr;
	private static int[] dr = { -1, 1, 0, 0 };
	private static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		input();
		go();
		System.out.println(result);

	}

	private static void go() {
		permu = new int[cansize];
		for (int i = 0; i < cansize; i++) {
			permu[i] = -1;
		}
		seedtotal = gseed + rseed;
		checkper = new boolean[seedtotal];
		seedarr = new int[seedtotal];
		// 배양액의 총갯수< 씨를 뿌릴 땅이 적으면
		if (seedtotal >= cansize) {
			makepermu(0);
		} else{
			makepermu2(0,0);
		}
	}

	private static void makepermu(int cur) {
		if (cur == cansize) {
			// 씨 뿌리기 시작.
			int temp = bloom();
			result = result < temp ? temp : result;
			return;
		}

		for (int i = 0; i < seedtotal; i++) {
			if (!checkper[i]) {
				checkper[i] = true;
				permu[cur] = i;
				makepermu(cur + 1);
				checkper[i] = false;
			}
		}
	}

	private static void makepermu2(int cur,int idx) {
		if (idx == cansize) {
			// 씨 뿌리기 시작.
			int numcnt=0;
			for (int i = 0; i < cansize; i++) {
				
			}
			int temp = bloom();
			result = result < temp ? temp : result;
			return;
		}

		for (int i = 0; i < seedtotal; i++) {
			if (!checkper[i]) {
				checkper[i] = true;
				permu[cur] = i;
				makepermu2(cur + 1,idx+1);
				permu[cur] = -1;
				checkper[i] = false;
			}
		}
		makepermu2(cur + 1,idx+1);

	}

	private static int bloom() {
		int fcnt = 0;
		// g=3 r=4;
		Queue<matrix> gq = new LinkedList<matrix>();
		Queue<matrix> rq = new LinkedList<matrix>();
		int[][] tempmap = new int[row][col];

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				tempmap[i][j] = map[i][j];
			}
		}

		for (int i = 0; i < cansize; i++) {
			if (permu[i] > -1) {
				if (permu[i] < gseed) {
					tempmap[canarr[i].row][canarr[i].col] = 3;
					gq.add(canarr[i]);
				} else {
					tempmap[canarr[i].row][canarr[i].col] = 4;
					rq.add(canarr[i]);
				}
			}
		}
		boolean[][] gvisited = new boolean[row][col];
		boolean[][] rvisited = new boolean[row][col];
//		int turnmap[][]=new int[row][col]; 
//		int turn=0;
		while (!gq.isEmpty() || !rq.isEmpty()) {
			// 초록색 먼저 퍼뜨리고
			int gqsize = gq.size();
			while (gqsize != 0) {
				matrix curm = gq.poll();
				int cr = curm.row;
				int cc = curm.col;
				gvisited[cr][cc] = true;
				if (tempmap[cr][cc] == 5) {
					gqsize--;
					continue;
				}

				for (int d = 0; d < 4; d++) {
					int nr = cr + dr[d];
					int nc = cc + dc[d];
					if (nr >= 0 && nr < row && nc >= 0 && nc < col) {
						if (tempmap[nr][nc] == 1 || tempmap[nr][nc] == 2) {
							tempmap[nr][nc] = 3;
							gq.add(new matrix(nr, nc));
						}
					}
				}
				gqsize--;
			}
			// 빨간색 퍼뜨리고
			int rqsize = rq.size();
			while (rqsize != 0) {
				matrix curm = rq.poll();
				int cr = curm.row;
				int cc = curm.col;
				for (int d = 0; d < 4; d++) {
					int nr = cr + dr[d];
					int nc = cc + dc[d];
					if (nr >= 0 && nr < row && nc >= 0 && nc < col) {
						if (tempmap[nr][nc] == 1 || tempmap[nr][nc] == 2) {
							tempmap[nr][nc] = 4;
							rq.add(new matrix(nr, nc));
						} else if (tempmap[nr][nc] == 3) {
							// g체크가 아직 false라면 지금 막 퍼진거니깐 꽃이 핌.
							if (!gvisited[nr][nc]) {
								fcnt++;
								tempmap[nr][nc] = 5;
							}
						}
					}
				}
				rqsize--;
			}
		}
		return fcnt;
	}

	private static void input() throws Exception {
		result = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		gseed = Integer.parseInt(st.nextToken());
		rseed = Integer.parseInt(st.nextToken());
		canSeed = new LinkedList<matrix>();
		map = new int[row][col];

		for (int i = 0; i < row; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < col; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				// 배양액을 뿌릴 수 있는 땅을 구한다.
				if (map[i][j] == 2) {
					canSeed.add(new matrix(i, j));
				}
			}
		}

		cansize = canSeed.size();
		canarr = new matrix[cansize];
		for (int i = 0; i < cansize; i++) {
			canarr[i] = canSeed.get(i);
		}
		canSeed.remove();
		br.close();
	}

	public static class matrix {
		int row;
		int col;

		public matrix(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
}
