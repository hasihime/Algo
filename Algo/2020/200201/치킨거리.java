import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 치킨거리 {

	private static int[][] map;
	private static int[][] copymap;
	private static home[] thome;
	private static home[] tck;
	private static home[] home;
	private static home[] ck;
	private static int result;
	private static int n;
	private static int m;
	private static int[] com;
	private static int idx;
	private static int cidx;

	public static class home {
		int row;
		int col;

		public home(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		copymap = new int[n][n];
		thome = new home[n * n];
		tck = new home[n * n];
		idx = 0;
		cidx = 0;

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				copymap[i][j] = map[i][j];
				if (map[i][j] == 1) {
					thome[idx] = new home(i, j);
					idx++;
				} else if (map[i][j] == 2) {
					tck[cidx] = new home(i, j);
					cidx++;
				}
			}
		}

		home = new home[idx];
		ck = new home[cidx];
		for (int i = 0; i < idx; i++) {
			home[i] = thome[i];
		}
		for (int i = 0; i < cidx; i++) {
			ck[i] = tck[i];
		}

		result = 1000000000;
		com = new int[m];
		makecome(0, 0);

		System.out.println(result);
	}

	private static void makecome(int cur, int target) {
		if (target == m) {
			getguri();
			return;
		}

		for (int i = cur; i < cidx; i++) {
			com[target] = i;
			makecome(i + 1, target + 1);
		}

	}

	private static void getguri() {
		int tempresult = 0;
		for (int i = 0; i < idx; i++) {
			if (tempresult > result) {
				return;
			}
			int cur = Integer.MAX_VALUE;
			for (int j = 0; j < m; j++) {
				int now = Math.abs(ck[com[j]].row - home[i].row);
				now += Math.abs(ck[com[j]].col - home[i].col);
				cur = Math.min(cur, now);
			}
			tempresult += cur;
		}
		result = Math.min(result, tempresult);
	}

}
