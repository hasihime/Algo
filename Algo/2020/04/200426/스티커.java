import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 스티커 {
	//https://www.acmicpc.net/submit/9465
	private static BufferedReader br;
	private static int N;
	private static int[][] dp;
	private static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			input();
			go();
		}
		System.out.println(sb);
	}

	private static void go() {
		if(N>1) {
			dp[1][1]+=dp[0][0];
			dp[0][1]+=dp[1][0];
		}
			for (int i = 2; i < N+2; i++) {
				dp[1][i]+=Math.max(dp[0][i-1], dp[0][i-2]);
				dp[0][i]+=Math.max(dp[1][i-1], dp[1][i-2]);
			}
			sb.append(Math.max(dp[0][N+1],dp[1][N+1])).append("\n");
	}

	private static void input() throws Exception {
		N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		dp = new int[2][N+2];
		for (int i = 0; i < 2; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				dp[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}

}
