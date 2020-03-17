import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/4485
public class 녹색옷을입은애가젤다지 {

	private static BufferedReader br;
	private static int n;
	private static int[][] map;
	private static StringTokenizer st;
	private static StringBuilder sb;
	private static int[][] cost;
	private static int[] dr = { -1, 1, 0, 0 };
	private static int[] dc = { 0, 0, -1, 1 };
	private static int result;

	public static void main(String[] args) throws Exception {
		input();

	}

	private static void input() throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int idx = 1;
		while (true) {
			n = Integer.parseInt(br.readLine());
			if (n == 0) {
				System.out.println(sb);
				return;
			}
			sb.append("Problem ").append(idx).append(": ");
			map = new int[n][n];
			cost = new int[n][n];

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					cost[i][j] = 1000000000;
				}
			}
			cost[0][0] = map[0][0];
			go();
			idx++;
		}

	}// input 끝

	private static void go() {
		PriorityQueue<node> pq = new PriorityQueue<node>(new Comparator<node>() {
			@Override
			public int compare(node o1, node o2) {
				return o1.cost - o2.cost;
			}
		});
		pq.add(new node(0, 0, cost[0][0]));
		result = Integer.MAX_VALUE;
		
		while(!pq.isEmpty()) {
			node curnode=pq.poll();
			int curr=curnode.row;
			int curc=curnode.col;
			

			for (int d = 0; d < dr.length; d++) {
				int nr = curr + dr[d];
				int nc = curc + dc[d];
				if (nr >= 0 && nr < n && nc >= 0 && nc < n) {
					if (cost[nr][nc] > cost[curr][curc] + map[nr][nc]) {
						cost[nr][nc] = cost[curr][curc] + map[nr][nc];
						pq.add(new node(nr, nc, cost[nr][nc]));
					}
				}
			}
			
		}
		result=cost[n-1][n-1];
		sb.append(result).append("\n");
	}


	public static class node {
		int row;
		int col;
		int cost;

		public node(int row, int col, int cost) {
			this.row = row;
			this.col = col;
			this.cost = cost;
		}
	}
}