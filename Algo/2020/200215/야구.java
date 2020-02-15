import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.BitSet;
import java.util.StringTokenizer;

public class 야구 {

	private static boolean[] visited;
	private static int result;
	private static int n;
	private static int[][] hitarr;
	private static int[] hito9;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		hitarr = new int[n][10];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= 9; j++) {
				hitarr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 타순을 정하자.
		hito9 = new int[9];
		hito9[3] = 1;
		visited = new boolean[10];
		result = 0;
		makepermu(0);
		System.out.println(result);
	}

	private static void makepermu(int target) {
		if (target == 3) {
			makepermu(target + 1);
			return;
		}

		if (target == 9) {
			playgame();
			return;
		}
		for (int i = 2; i <= 9; i++) {
			if (!visited[i]) {
				hito9[target] = i;
				visited[i] = true;
				makepermu(target + 1);
				visited[i] = false;
			}
		}

	}

	private static void playgame() {
		int inning = 0;
		int tasun = 0;
		int score = 0;
		while (inning < n) {
			boolean[] basearr = new boolean[4];
			int base = 0;
			int outcnt = 0;
			while (outcnt < 3) {
				int curhit = hitarr[inning][hito9[tasun]];
				if (curhit == 0)
					outcnt++;
				else if (curhit == 4) {
					for (int i = 0; i < 4; i++) {
						if (basearr[i]) {
							score++;
							basearr[i] = false;
						}
					}
					score++;
				} else if (curhit == 1) {
					if (basearr[3]) {
						score++;
						basearr[3]=false;
					}
					if (basearr[2]) {
						basearr[3]=true;
						basearr[2]=false;
					}
					if (basearr[1]) {
						basearr[2]=true;
						basearr[1]=false;
					}
					basearr[1]=true;
				} else if (curhit == 2) {
					if (basearr[3]) {
						score++;
						basearr[3]=false;
					}
					if (basearr[2]) {
						score++;
						basearr[2]=false;
					}
					if (basearr[1]) {
						basearr[3]=true;
						basearr[1]=false;
					}
					basearr[2]=true;
				} else if (curhit == 3) {
					if (basearr[3]) {
						score++;
						basearr[3]=false;
					}
					if (basearr[2]) {
						score++;
						basearr[2]=false;
					}
					if (basearr[1]) {
						score++;
						basearr[1]=false;
					}
					basearr[3]=true;
				}
				tasun = (tasun + 1) % 9;
			} // 이닝 끝
			inning++;
		} // while 끝
			// 경기끝
		result = Math.max(result, score);
	}

}
