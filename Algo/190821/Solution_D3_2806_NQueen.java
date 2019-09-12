
import java.util.Scanner;

public class Solution_D3_2806_NQueen_오석빈 {

	static boolean map[][];
	static int N;
	static int count;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			N = sc.nextInt();
			map = new boolean[N][N];
			count = 0;
			setQueen(0);
			System.out.println("#"+t+" "+count);

		}

	}

	private static void setQueen(int startrow) {
		if (startrow == N) {
			count++;
			return;
		}

		for (int c = 0; c < N; c++) {
			// 1. 재귀확인
			if (check(startrow, c) == false) {
				continue;
			} else {
				// 2. true로 바꾼다
				map[startrow][c] = true;
				// 3. 다음 재귀로 간다.
				setQueen(startrow+1);
				//4. false로 바꾼다
				map[startrow][c] = false;
			}
		}
	}

	private static boolean check(int row, int col) {

		// 가로 세로 1
		for (int i = 0; i < N; i++) {
			if (map[row][i] == true || map[i][col]) {
				return false;
			}
		}
		// 좌상 대각선
		int a = row, b = col;
		while (true) {
			if (a - 1 >= 0 && b - 1 >= 0) {
				--a;
				--b;
				if (map[a][b] == true) {
					return false;
				}
			} else
				break;
		}
		// 우상 대각선
		a = row;
		b = col;
		while (true) {
			

			if (a - 1 >= 0 && b + 1 < N) {
				--a;
				++b;
				if (map[a][b] == true) {
					return false;
				}
					
			} else
				break;
		}
//		// 우하 대각선
//		a = row;
//		b = col;
//		while (true) {
//			if (a + 1 < N && b + 1 < N) {
//				if (map[++a][++b] == true) {
//					return false;
//				}
//			} else
//				break;
//		}
//		// 좌하 대각선
//		a = row;
//		b = col;
//		while (true) {
//			if (a - 1 >= 0 && b + 1 < N) {
//				if (map[--a][++b] == true) {
//					return false;
//				}
//			} else
//				break;
//		}
		return true;
}
}