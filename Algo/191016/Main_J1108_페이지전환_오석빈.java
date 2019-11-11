import java.util.Arrays;
import java.util.Scanner;

public class Main_J1108_페이지전환_오석빈 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		int[][] map = new int[501][501];

		for (int i = 0; i < 501; i++) {
			Arrays.fill(map[i], 100000000);
		}

		int maxpage = 1;
		for (int i = 0; i < n; i++) {

			int start = sc.nextInt();
			int dest = sc.nextInt();
			int temp = dest > start ? dest : start;
			maxpage = maxpage < temp ? temp : maxpage;

			map[start][dest] = 1;
		}

		int maxpagemap[][]=new int [maxpage+1][maxpage+1];
		
		for (int i = 0; i <= maxpage; i++) {
			for (int j = 0; j <= maxpage; j++) {
				maxpagemap[i][j]=map[i][j];
			}
		}
		
		for (int k = 1; k <= maxpage; k++) {
			for (int i = 1; i <= maxpage; i++) {
				if (k == i)
					continue;
				for (int j = 1; j <= maxpage; j++) {
					if (i == j)
						continue;
					if (maxpagemap[i][j] > maxpagemap[i][k] + maxpagemap[k][j]) {
						maxpagemap[i][j] = maxpagemap[i][k] + maxpagemap[k][j];
					}
				}
			}
		}


		

		int sum = 0;
		for (int i = 1; i <= maxpage; i++) {
			for (int j = 1; j <= maxpage; j++) {
				if (i == j) {
					continue;
				} else {
					sum += maxpagemap[i][j];
				}
			}
		}

		float result = (float) sum / (float) (maxpage* (maxpage - 1));

		System.out.printf("%.3f", result);
		System.out.println();

	}
}
