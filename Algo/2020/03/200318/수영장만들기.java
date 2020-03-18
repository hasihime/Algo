import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 수영장만들기 {
//https://www.acmicpc.net/problem/1113
	private static int row;
	private static int col;
	private static int[][] map;
	private static int[] dr = { -1, 1, 0, 0 };
	private static int[] dc = { 0, 0, -1, 1 };
	private static boolean[][] visited;
	private static int[][] copymap;

	public static void main(String[] args) throws IOException {
		input();
		go();
	}

	private static void go() {
		LinkedList<matrix> area = new LinkedList<matrix>();
		int result = 0;
		//높이가 1인 부분부터 다 넣어본다.
		for (int h = 1; h <= 9; h++) {
			visited = new boolean[row + 2][col + 2];
			for (int i = 1; i <= row; i++) {
				for (int j = 1; j <= col; j++) {
					if (map[i][j] == h && !visited[i][j]) {
						//백업용 맵
						copymap = new int[row + 2][col + 2];
						copymap(map, copymap);
						visited[i][j] = true;
						result += bfs(i, j, h);
					}
				}
			}
		}
		System.out.println(result);
	}

	private static void copymap(int[][] from, int[][] to) {
		for (int i = 1; i <= row; i++) {
			for (int j = 1; j <= col; j++) {
				to[i][j] = from[i][j];
			}
		}
	}

	private static int bfs(int currow, int curcol, int height) {
		Queue<matrix> q = new LinkedList<matrix>();
		q.add(new matrix(currow, curcol));
		boolean flag = true;
		int cnt = 1;
		while (!q.isEmpty()) {
			matrix cm = q.poll();
			int cr = cm.row;
			int cc = cm.col;
			map[cr][cc]++;
			for (int d = 0; d < dr.length; d++) {
				int nr = cr + dr[d];
				int nc = cc + dc[d];
				if (!visited[nr][nc]) {
					//bfs 돌리다 0인 부분 만나면 물이 다 빠져나감.
					if (map[nr][nc] == 0) {
						flag = false;
						visited[nr][nc] = true;
					}//height와 같은 부분을 만나면 물을 1 채우고 큐에 넣는다. 
					else if (map[nr][nc] == height) {
						visited[nr][nc] = true;
						cnt++;
						q.add(new matrix(nr, nc));
					}//height보다 낮은 부분을 만나면 큐에만 넣는다.
					else if(map[nr][nc] < height) {
						q.add(new matrix(nr, nc));
						visited[nr][nc] = true;
					}// 그 외는 높은 부분은 벽이 못가니 넘어감.
				}
			}
		} // while 끝
		//flag가 false인 것은 0인 부분을 만나 물이 다 빠져나갔다는 것.
		if (!flag) {
			//넣은 물의 양은 0으로 초기화, map도 백업한다.
			cnt = 0;
			copymap(copymap, map);
		}
		return cnt;
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		map = new int[row + 2][col + 2];
		for (int i = 1; i <= row; i++) {
			String line = br.readLine();
			for (int j = 1; j <= col; j++) {
				map[i][j] = Integer.parseInt(line.substring(j - 1, j));
			}
		}

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
