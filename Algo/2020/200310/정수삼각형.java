import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//https://www.acmicpc.net/problem/1932

public class 정수삼각형 {

	private static int n;
	private static int[][] dp;
	private static BufferedReader br;
	private static StringTokenizer st;
	private static int[][] input;

	public static void main(String[] args) throws NumberFormatException, IOException {
		input();
		go();
		
	}

	private static void go() throws IOException {
		int idx =1;
		while(idx<n) {
			int i=0;
			//왼쪽 줄 채우기.
			dp[idx][i]=input[idx][i]+dp[idx-1][i];
			//오른쪽 줄 채우기.
			dp[idx][idx]=input[idx][idx]+dp[idx-1][idx-1];
			//가운데 채우기
			for (i = 1;  i< idx; i++) {
				dp[idx][i]=Math.max(dp[idx-1][i-1], dp[idx-1][i])+input[idx][i];
			}
			idx++;
		}
		
		int result=-1;
		for (int i = 0; i < n; i++) {
			result=Math.max(dp[n-1][i], result);
		}
		System.out.println(result);
	}

	private static void input() throws NumberFormatException, IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		n=Integer.parseInt(br.readLine());
		dp=new int[501][501];
		input=new int[n][n];
		int idx=1;
		for (int i = 0; i < n; i++) {
			st=new StringTokenizer(br.readLine());
			for (int j = 0; j < idx; j++) {
				input[i][j]=Integer.parseInt(st.nextToken());
			}
			idx++;
		}
		dp[0][0]=input[0][0];
	}

}
