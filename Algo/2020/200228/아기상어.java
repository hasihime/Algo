import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 아기상어 {
	public static int[] dr = { -1, 1, 0, 0 };
	public static int[] dc = { 0, 0, -1, 1 };
	private static int n;
	private static int[][] map;
	private static shark s;

	public static void main(String[] args) throws NumberFormatException, IOException {
		input();
		bfs();
	}

	private static void bfs() {
		while (true) {
			Queue<Integer> qr = new LinkedList<Integer>();
			Queue<Integer> qc = new LinkedList<Integer>();
			qr.add(s.row);
			qc.add(s.col);
			int mcnt = 0;
			boolean[][] visited = new boolean[n][n];
			visited[s.row][s.col] = true;
			boolean flag = false;
			while (!qr.isEmpty()) {
				int size = qr.size();
				while (size>0) {
					int cr = qr.poll();
					int cc = qc.poll();
					if (map[cr][cc] != 0 && map[cr][cc] < s.size) {
						int mr=cr;
						int mc=cc;
						size--;
						while(size>0) {
							size--;
							cr = qr.poll();
							cc = qc.poll();
							if (map[cr][cc] == 0 || map[cr][cc] >= s.size) {
								continue;
							}
							if(mr>cr) {
								mr=cr;
								mc=cc;
							}else if(mr==cr) {
								if (mc>cc) {
									mc=cc;
									mr=cr;
								}
							}
						}
						map[mr][mc] = 0;
						s.setRow(mr);
						s.setCol(mc);
						s.setCnt(s.cnt + mcnt);
						s.setFeed(s.feed + 1);
						if (s.feed == s.size) {
							s.setSize(s.size + 1);
							s.setFeed(0);
						}
						flag=true;
						break;
					}
					
					for (int d = 0; d < 4; d++) {
						int nr = cr + dr[d];
						int nc = cc + dc[d];
						if (nr >= 0 && nr < n && nc >= 0 && nc < n && !visited[nr][nc]) {
							// 크기가 같으면 지나갈수 있다. -> 큐에 넣는다
							if (map[nr][nc] <= s.size ) {
								qr.add(nr);
								qc.add(nc);
								visited[nr][nc] = true;
							}
						}
					} // for 끝
					size--;
				} // for size 끝
				mcnt++;
				if (flag) {
					break;
				}
			} // 내부 while 끝
			if (!flag) {
				System.out.println(s.cnt);
				return;
			}
		}
	}

	private static void input() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9) {
					s = new shark(i, j, 2, 0, 0);
					map[i][j]=0;
				}
			}
		}
	}

	public static class shark {
		int row;
		int col;
		int size;
		int feed;
		int cnt;

		public shark(int row, int col, int size, int feed, int cnt) {
			super();
			this.row = row;
			this.col = col;
			this.size = size;
			this.feed = feed;
			this.cnt = cnt;
		}

		public int getCnt() {
			return cnt;
		}

		public void setCnt(int cnt) {
			this.cnt = cnt;
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

		public int getSize() {
			return size;
		}

		public void setSize(int size) {
			this.size = size;
		}

		public int getFeed() {
			return feed;
		}

		public void setFeed(int feed) {
			this.feed = feed;
		}

	}
}
