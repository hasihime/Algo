import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 공항활주로 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			st = new StringTokenizer(br.readLine(), " ");

			int n = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());

			int map[][] = new int[n][n];
			boolean used[][] = new boolean[n][n];

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int result = 0;
			for (int i = 0; i < n; i++) {
				int curnum = map[i][0];
				boolean possible = true;
				for (int j = 1; j < n; j++) {
					if (curnum == map[i][j])
						continue;
					else if (curnum < map[i][j]) {
						if (Math.abs(curnum - map[i][j]) != 1) {
							possible = false;
							break;
						}
						boolean flag = true;
						for (int k = 0; k < x; k++) {
							if (j - k - 1 < 0) {
								flag = false;
								break;
							}
							if (map[i][j - k-1] != curnum || used[i][j - k-1]) {
								flag = false;
								break;
							}
						}
						if (!flag) {
							possible = false;
							break;
						} else if (flag) {
							for (int k = 0; k < x; k++) {
								used[i][j - k-1] = true;
							}
						}
						curnum = map[i][j];
					} else if (curnum > map[i][j]) {
						if (Math.abs(curnum - map[i][j]) != 1) {
							possible = false;
							break;
						}
						boolean flag = true;
						for (int k = 1; k < x; k++) {
							if (j + k >= n) {
								flag = false;
								break;
							}
							if (map[i][j + k] != map[i][j] || used[i][j + k]) {
								flag = false;
								break;
							}
						}
						if (!flag) {
							possible = false;
							break;
						} else if (flag) {
							for (int k = 0; k < x; k++) {
								used[i][j + k] = true;
							}
						}
						curnum = map[i][j];
					}
					if (!possible) {
						break;
					}
				}
				if (possible) {
					result++;
				}
			}

			used = new boolean[n][n];
			for (int i = 0; i < n; i++) {
				int curnum = map[0][i];
				boolean possible = true;
				for (int j = 1; j < n; j++) {
					if (curnum == map[j][i])
						continue;
					else if (curnum < map[j][i]) {
						if (Math.abs(curnum-map[j][i])!=1) {
							possible=false;
							break;
						}
						boolean flag = true;
						for (int k = 0; k < x; k++) {
							if (j - k - 1 < 0) {
								flag = false;
								break;
							}
							if (map[j - k-1][i] != curnum || used[j - k-1][i]) {
								flag = false;
								break;
							}
						}
						if (!flag) {
							possible = false;
							break;
						} else if (flag) {
							for (int k = 0; k < x; k++) {
								used[j - k-1][i] = true;
							}
						}
						curnum = map[j][i];
					} else if (curnum > map[j][i]) {
						if (Math.abs(curnum-map[j][i])!=1) {
							possible=false;
							break;
						}
						boolean flag = true;
						for (int k = 1; k < x; k++) {
							if (j + k >= n) {
								flag = false;
								break;
							}
							if (map[j + k][i] != map[j][i] || used[j + k][i]) {
								flag = false;
								break;
							}
						}
						if (!flag) {
							possible = false;
							break;
						} else if (flag) {
							for (int k = 0; k < x; k++) {
								used[j + k][i] = true;
							}
						}
						curnum = map[j][i];
					}
					if (!possible) {
						break;
					}
				}
				if (possible) {
					result++;
				}
			}	
			sb.append(result);
			sb.append("\n");

		} // T 끝
		System.out.println(sb);
	}// 메인 끝

}
