import java.util.Arrays;
import java.util.Scanner;

public class Main_2074_홀수마방진_오석빈 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);


		int N = sc.nextInt();
		int map[][] = new int[N][N];

		map[0][N / 2] = 1;
		int r = 0;
		int c = N / 2;

		for (int i = 2; i <= N * N; i++) {

			if (i % N != 1) {
				int nr = r - 1;
				int nc = c - 1;
				if (nr < 0 || nc < 0) {
					if (nr < 0) {
						nr = N - 1;
					}
					if (nc < 0) {
						nc = N - 1;
					}
				}
				map[nr][nc] = i;
				r = nr;
				c = nc;
			} else {
				int nr = r + 1;
				if (nr == N) {
					nr = 0;
				}
				while (map[nr][c] != 0) {
					nr++;
				}
				map[nr][c] = i;
				r = nr;
			}

		}
		for (int i = 0; i < N; i++) {
			StringBuilder sb=new StringBuilder();
			for (int j = 0; j < N; j++) {
				if (j==0) {
					sb.append(map[i][j]);
				}else
				sb.append(" "+map[i][j]);
			}
			System.out.println(sb);
		}


	}

}
