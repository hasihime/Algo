import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;
//https://www.acmicpc.net/problem/17845
public class 수강과목 {
	private static int t;
	private static int n;
	private static int[][] arr;
	private static int result;
	private static int[][] dp;

	public static void main(String[] args) throws IOException {
		input();
		go();
	}

	private static void go() {
		result = 0;
		int idx = 0;
		for (int i = 1; i <=t; i++) {
			for (int j = 1; j <= n; j++) {
				if (i >= arr[j][1]) {
					dp[i][j]=Math.max(dp[i][j-1], dp[i-arr[j][1]][j-1]+arr[j][0]);
				} else {
					dp[i][j]=dp[i][j-1];
				}
			}
		}
		System.out.println(dp[t][n]);
	}

	public static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		t = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		arr = new int[n+1][2];
		dp=new int[t+1][n+1];
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}

	}

}
