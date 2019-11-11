import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_2105_디저트카페 {
	static int N = 0;
	static int map[][];
	static int result = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			result = Integer.MIN_VALUE;
			map = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 0; i < N - 2; i++) {
				for (int j = 1; j < N - 1; j++) {
					get(i, j);

				}
			}
			result = result < 0 ? -1 : result;
			System.out.println("#" + t + " " + result);
		}

	}

	private static void get(int row, int col) {

		int lrow = row;
		int lcol = col;

		while (true) {
			// 좌하 방향
			lrow++;
			lcol--;
			if (lrow >= 0 && lcol >= 0) {
				int rrow = row;
				int rcol = col;
				while (true) {
					rrow++;
					rcol++;
					if (rrow < N && rcol < N) {
						int drow = (lrow + rrow) - row;
						int dcol = (lcol + rcol) - col;
						if (dcol >= N || drow >= N) {
							break;
						}

						int temp[] = new int[2 * N];// 디저트 담을 것
						temp[0] = map[row][col];
						boolean num[]=new boolean[101];
						int tindex = 1;
						// 우하
						int vrow = row;
						int vcol = col;
						boolean tfalg = true;// 중복 디저트 확인용
						for (int i = 0; i < rrow - row; i++) {
							vrow += 1;
							vcol += 1;
							for (int j = 0; j < tindex; j++) {
								if (temp[j] == map[vrow][vcol]) {
									tfalg = false;
									break;
								}
							}
							if (!tfalg) {
								break;
							} else {
								temp[tindex] = map[vrow][vcol];
								tindex++;
							}
						}
						if (!tfalg) {
							continue;
						}
						// 좌하

						for (int i = 0; i < drow - rrow; i++) {
							vrow += 1;
							vcol -= 1;
							for (int j = 0; j < tindex; j++) {
								if (temp[j] == map[vrow][vcol]) {
									tfalg = false;
									break;
								}
							}
							if (!tfalg) {
								break;
							} else {
								temp[tindex] = map[vrow][vcol];
								tindex++;
							}
						}
						if (!tfalg) {
							continue;
						}
						// 좌상

						for (int i = 0; i < drow - lrow; i++) {
							vrow -= 1;
							vcol -= 1;
							for (int j = 0; j < tindex; j++) {
								if (temp[j] == map[vrow][vcol]) {
									tfalg = false;
									break;
								}
							}
							if (!tfalg) {
								break;
							} else {
								temp[tindex] = map[vrow][vcol];
								tindex++;
							}
						}
						if (!tfalg) {
							continue;
						}

						// 우상
						for (int i = 0; i < lrow - row - 1; i++) {
							vrow -= 1;
							vcol += 1;
							for (int j = 0; j < tindex; j++) {
								if (temp[j] == map[vrow][vcol]) {
									tfalg = false;
									break;
								}
							}
							if (!tfalg) {
								break;
							} else {
								temp[tindex] = map[vrow][vcol];
								tindex++;
							}
						}
						if (!tfalg) {
							continue;
						} else {
							result = result < tindex ? tindex : result;
						}

					} else {
						break;
					}

				}

			} else {
				break;
			}

		}
	}

}
