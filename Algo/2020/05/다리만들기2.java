import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 다리만들기2 {

	private static int row;
	private static int col;
	private static int[][] map;
	private static int[] dr = { -1, 1, 0, 0 };
	private static int[] dc = { 0, 0, -1, 1 };
	private static int inum;
	private static boolean[][] visited;
	private static int[][] nummap;
	private static int[][] matrix;
	private static int[] parent;
	static final int INF = 1000000000;
	private static int result = 0;
	private static int icnt;

	public static void main(String[] args) throws Exception {
		input();
		// bfs로 섬의 갯수 파악.
		findisland();
		// 인접행렬 만들고 각 섬의 최단 경로 만들기.
		getmatrix();
		// mst구하기
		getmst();
		System.out.println(result);
	}

	private static void getmst() {
		parent = new int[inum + 1];
		for (int i = 1; i <= inum; i++) {
			parent[i] = i;
		}
		// earr 만들기
		LinkedList<int[]> elist = new LinkedList<int[]>();
		for (int i = 1; i <= inum; i++) {
			for (int j = i; j <= inum; j++) {
				if (matrix[i][j] > 1 && matrix[i][j] < INF) {
					elist.add(new int[] { i, j, matrix[i][j] });
				}
			}
		}
		Collections.sort(elist, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2] - o2[2];
			}
		});

		int cnt = 0;
		for (int i = 0; i < elist.size(); i++) {
			if (cnt == icnt - 1) {
				break;
			}
			int[] carr = elist.get(i);
			if (!find(carr[0], carr[1])) {
				continue;
			} else {
				union(carr[0], carr[1]);
				cnt++;
				result += carr[2];
			}
		}
		int idx=getparent(parent[1]);
		for (int i = 2; i <= inum; i++) {
			if (idx!=getparent(parent[i])) {
				result=-1;
				break;
			}
		}
	}

	private static void getmatrix() {
		matrix = new int[inum + 1][inum + 1];
		// inf로 설정
		for (int i = 1; i <= inum ; i++) {
			for (int j = 1; j <= inum; j++) {
				matrix[i][j] = INF;
			}
		}

		// 각 섬에서 경로 구하기.
		visited = new boolean[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (nummap[i][j] != 0 && !visited[i][j]) {
					getdistance(i, j, nummap[i][j]);
				}
			}
		}

	}

	private static void getdistance(int crow, int ccol, int cnum) {

		Queue<Integer> qr = new LinkedList<Integer>();
		Queue<Integer> qc = new LinkedList<Integer>();
		qr.add(crow);
		qc.add(ccol);

		while (!qr.isEmpty()) {
			int cr = qr.poll();
			int cc = qc.poll();
			visited[cr][cc]=true;
			// 섬의 인접점 추가.
			for (int i = 0; i < 4; i++) {
				int nr = cr + dr[i];
				int nc = cc + dc[i];
				if (nr >= 0 && nr < row && nc >= 0 && nc < col && 
						!visited[nr][nc] && map[nr][nc] == 1) {
					qr.add(nr);
					qc.add(nc);
				}
			}

			// 현재 섬에서 4방향 거리로 쭉 탐색
			for (int d = 0; d < 4; d++) {
				int nr = cr;
				int nc = cc;
				int dist = 0;
				while (true) {
					nr += dr[d];
					nc += dc[d];
					dist++;
					if (nr < 0 || nr >= row || nc < 0 || nc >= col || 
							nummap[nr][nc] == cnum) {
						break;
					}
					// break가 안되면 방문처리 한다.
					// 만약 다른 섬을 발견하면
					if (nummap[nr][nc] != 0) {
						if (dist > 2) 
						matrix[cnum][nummap[nr][nc]] = Math.min(dist - 1, matrix[cnum][nummap[nr][nc]]);
						break;
					}

				}
			}
		}

	}

	private static void findisland() {
		nummap = new int[row][col];
		inum = 0;
		visited = new boolean[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (map[i][j] != 0 && !visited[i][j]) {
					inum++;
					check(i, j, inum);
				}
			}
		}
		// 섬의 개수
	}

	private static void check(int crow, int ccol, int num) {

		Queue<Integer> qr = new LinkedList<Integer>();
		Queue<Integer> qc = new LinkedList<Integer>();
		qr.add(crow);
		qc.add(ccol);

		while (!qr.isEmpty()) {
			int cr = qr.poll();
			int cc = qc.poll();
			nummap[cr][cc] = num;
			for (int i = 0; i < 4; i++) {
				int nr = cr + dr[i];
				int nc = cc + dc[i];
				if (nr >= 0 && nr < row && nc >= 0 && nc < col && !visited[nr][nc] && map[nr][nc] == 1) {
					visited[nr][nc] = true;
					qr.add(nr);
					qc.add(nc);
				}
			}
		}
	}

	private static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		map = new int[row][col];

		for (int i = 0; i < row; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < col; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		br.close();
	}

	private static int getparent(int a) {
		if (a == parent[a]) {
			return a;
		}
		return parent[a] = getparent(parent[a]);
	}

	private static void union(int a, int b) {
		int roota = getparent(a);
		int rootb = getparent(b);
		if (roota < rootb) {
			parent[rootb] = roota;
		} else {
			parent[roota] = rootb;
		}
	}

	private static boolean find(int a, int b) {
		int roota = getparent(a);
		int rootb = getparent(b);

		if (roota == rootb) {
			return false;
		}
		return true;
	}
}
