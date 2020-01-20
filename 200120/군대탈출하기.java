import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 군대탈출 {

	private static int n;
	private static int m;
	private static int[][] map;
	private static int[] dr = { -1, 1, 0, 0 };
	private static int[] dc = { 0, 0, -1, 1 };
	private static int[] jr = { -2, 2, 0, 0 };
	private static int[] jc = { 0, 0, -2, 2 };
	private static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		result=1000000000;

		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 레벨을 이분탐색 후 bfs?
		int start = 0;
		int end = 1000000000;
		while (start <= end) {
			int mid = (start + end) / 2;

			if (bfs(mid)) {
				result=mid;
				end=mid-1;
			}else {
				start=mid+1;
			}
		}
		System.out.println(result);
	}

	private static boolean bfs(int mid) {
		Queue<Integer> qr = new LinkedList<Integer>();
		Queue<Integer> qc = new LinkedList<Integer>();
		Queue<Integer> qj = new LinkedList<Integer>();
		qr.add(0);
		qc.add(0);
		qj.add(1);
		boolean[][][] visited = new boolean[n][m][2];
		visited[0][0][1]=true;
		while (!qr.isEmpty()) {
			int cr=qr.poll();
			int cc=qc.poll();
			int cj=qj.poll();
			if (cr==n-1&&cc==m-1) {
				return true;
			}
			//점프 횟수 가능하면 하자
			if (cj==1) {
				for (int d = 0; d < dr.length; d++) {
					int nr=cr+jr[d];
					int nc=cc+jc[d];
					if (nr>=0&&nr<n&&nc>=0&&nc<m&&!visited[nr][nc][cj-1]
							&&map[nr][nc]<=mid) {
						visited[nr][nc][cj-1]=true;
						qr.add(nr);
						qc.add(nc);
						qj.add(cj-1);
					}
				}
			}
			for (int d = 0; d < dr.length; d++) {
				int nr=cr+dr[d];
				int nc=cc+dc[d];
				if (nr>=0&&nr<n&&nc>=0&&nc<m&&!visited[nr][nc][cj]
						&&map[nr][nc]<=mid) {
					visited[nr][nc][cj]=true;
					qr.add(nr);
					qc.add(nc);
					qj.add(cj);
				}
			}
		}
		return false;

	}

}
