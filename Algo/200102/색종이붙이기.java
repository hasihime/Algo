import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 색종이붙이기 {

	private static int[][] map;
	private static int[] papernum = { 0, 5, 5, 5, 5, 5 };
	private static int result;
	private static int[] row1;
	private static int[] col1;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		map = new int[10][10];
		row1 = new int[100];
		col1 = new int[100];
		for (int i = 0; i < 100; i++) {
			row1[i]=-1;
		}
		int row1cnt = 0;
		for (int i = 0; i < 10; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 10; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
					row1[row1cnt] = i;
					col1[row1cnt] = j;
					row1cnt++;
				}

			}
		}

		result = 100;

		dfs(0, 0);
		System.out.println(result == 100 ? -1 : result);
	}

	private static void dfs(int row1idx, int cnt) {
		if (row1idx==100||row1[row1idx] == -1) {
				result = result > cnt ? cnt : result;
				return;
		}

		if (cnt >= result) {
			return;
		}

			int nr = row1[row1idx];
			int nc = col1[row1idx];
			if (map[nr][nc] == 1) {
				for (int k = 5; k > 0; k--) {
					if (papernum[k] != 0) {
						if (isfilled(nr, nc, k)) {
							fillpaper(nr, nc, k, 2);
							papernum[k]--;
							dfs(row1idx + 1, cnt + 1);
							fillpaper(nr, nc, k, 1);
							papernum[k]++;
						}
					}

				}
			}else {
				dfs(row1idx+1,cnt);
		}

	}

	private static void fillpaper(int startrow, int startcol, int papersize, int fillnum) {
		for (int i = startrow; i < startrow + papersize; i++) {
			for (int j = startcol; j < startcol + papersize; j++) {
				map[i][j] = fillnum;
			}
		}
	}

	private static boolean isfilled(int row, int col, int papernum) {
		if (row + papernum > 10 || col + papernum > 10) {
			return false;
		}
		for (int i = row; i < row + papernum; i++) {
			for (int j = col; j < col + papernum; j++) {
				if (map[i][j] != 1) {
					return false;
				}
			}
		}
		return true;
	}

}
