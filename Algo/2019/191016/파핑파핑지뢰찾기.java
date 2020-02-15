import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 파핑파핑지뢰찾기 {
	static char map[][];
	static int N = 0;
	static boolean visited[][];
	static int dir[][] = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 }, { 1, 1 } };


	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new char[N][N];
			visited=new boolean[N][N];
			
			for (int j = 0; j < N; j++) {
				String line = br.readLine();
				for (int k = 0; k < N; k++) {
					char input = line.charAt(k);
					if (input == '.') {
						map[j][k] += '0';
					} else {
						map[j][k] = input;
						for (int x = 0; x < 8; x++) {
							int newr = j + dir[x][0];
							int newc = k + dir[x][1];

							if (newr >= 0 && newr < N && newc >= 0 && newc < N && map[newr][newc] != '*') {
								map[newr][newc] += 1;
							}
						}
					}
				}
			}

			int result = 0;

			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					if (map[j][k] == '0' && !visited[j][k]) {
						result++;
						visited[j][k] = true;
						dfs(j, k);
					}

				}
			}
			
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					 if (map[j][k] >= '0'&&map[j][k] != '*'&&!visited[j][k]) {
						result++;
						visited[j][k] = true;
					}

				}
			}
			System.out.println("#"+t+" "+result);
		}

	}

	private static void dfs(int j, int k) {
		if (map[j][k] != '0') {
			return;
		}
		for (int i = 0; i < 8; i++) {
			int newr = j + dir[i][0];
			int newc = k + dir[i][1];
			if (newr >= 0 && newr < N && newc >= 0 && newc < N 
					&& !visited[newr][newc] ) {
				if (map[newr][newc] != '0') {
					visited[newr][newc]=true;
				}
				if (map[newr][newc] == '0') {
					visited[newr][newc]=true;
					dfs(newr, newc);
				}
			}
		}
		

	}

}
