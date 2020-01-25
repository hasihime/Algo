import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 행성탐사 {

	private static int N;
	private static int M;
	private static int findcnt;
	private static char[][] map;
	private static int[][][] memomap;

	private static int[][][] rowmemomap;

	public static void main(String[] args) throws IOException {
		// 메모이제이션. 그냥 돌리면 터짐.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		findcnt = Integer.parseInt(br.readLine());
		map = new char[N][M];
		// J 정글 ,O 바다, I얼음
		memomap = new int[3][N][M];
		// 열 고정하고 행의 누적 개수를 메모하는 맵
		rowmemomap = new int[3][N][M];

		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}

		// (0,0)애서 (i,j)의 누적 합 메모 맵을 작성한다.
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 'J') {
					getrowmap(i, j, 0);
					summap(i, j, 0);
				} else if (map[i][j] == 'O') {
					getrowmap(i, j, 1);
					summap(i, j, 1);
				} else if (map[i][j] == 'I') {
					getrowmap(i, j, 2);
					summap(i, j, 2);
				}
			}
		}

		for (int i = 0; i < findcnt; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int sr = Integer.parseInt(st.nextToken()) - 1;
			int sc = Integer.parseInt(st.nextToken()) - 1;
			int er = Integer.parseInt(st.nextToken()) - 1;
			int ec = Integer.parseInt(st.nextToken()) - 1;

			for (int t = 0; t < 3; t++) {
				int num1 = memomap[t][er][ec];
				int num2 = sr > 0 ? memomap[t][sr - 1][ec] : 0;
				int num3 = sc > 0 ? memomap[t][er][sc - 1] : 0;
				int num4 = sr > 0 && sc > 0 ? memomap[t][sr - 1][sc - 1] : 0;
				sb.append(num1 - num2 - num3 + num4).append(" ");
			}
			sb.append("\n");
		}

		System.out.println(sb);
	}

	private static void getrowmap(int row, int col, int type) {
		for (int t = 0; t < 3; t++) {
			int pr = row - 1;
			if (row != 0) {
				rowmemomap[t][row][col] = rowmemomap[t][pr][col];
			}
			if (t == type) {
				rowmemomap[t][row][col]++;
				if (col == 0) {
					memomap[t][row][col] = rowmemomap[t][row][col];
				}
			}
		}

	}

	private static void summap(int row, int col, int type) {
		// 왼쪽, 위만 보고 가장 큰 수 +1 값으로 채운다.
		for (int t = 0; t < 3; t++) {
			int cur = 0;
			for (int i = 0; i < 2; i++) {
				int nr = row;
				int nc = col - 1;
				if (nr >= 0 && nc >= 0) {
					cur = memomap[t][nr][nc];
				}
			}
			
				cur += rowmemomap[t][row][col];
			
				
			memomap[t][row][col] = cur;
				
			
		}

	}

}
