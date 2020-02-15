import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 서강그라운드 {
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader
				(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int[] item = new int[n];
		int[][] map = new int[n][n];
		int result = 0;
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < n; i++) {
			item[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i == j)
					continue;
				map[i][j] = 20;
			}
		}

		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a1 = Integer.parseInt(st.nextToken()) - 1;
			int a2 = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken());
			map[a1][a2] = d;
			map[a2][a1] = d;
		}

		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				if (k == i)
					continue;
				for (int j = 0; j < n; j++) {
					if (i == j)
						continue;
					if (map[i][j] > map[i][k] + map[k][j]) {
						map[i][j] = map[i][k] + map[k][j];
					}
				}
			}
		}
		
		for (int i = 0; i < n; i++) {
			int cur = 0;
			for (int j = 0; j < n; j++) {
				if (map[i][j] <= m) 
					cur += item[j];
			}
			result = result < cur ? cur : result;
		}
		System.out.println(result);
	}
}
