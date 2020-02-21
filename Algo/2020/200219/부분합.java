import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 부분합 {

	private static int N;
	private static long M;
	private static int[] arr;

	public static void main(String[] args) throws NumberFormatException, IOException {
		input();
		go();
	}

	private static void go() {
		int r = 0;
		int l = 0;
		long sum = 0;
		int cnt = 100001;
		while(r!=N) {
			//sum이  M보다 작은 경우
			if (sum<M) {
				sum+=arr[r];
				r++;
			}else  {
				sum-=arr[l];
				l++;
				cnt=Math.min(cnt, (r-l)+1);
			}
		}
		while(sum>=M) {
			sum-=arr[l];
			l++;
			cnt=Math.min(cnt, r-l+1);
		}
		System.out.println(cnt==100001?0:cnt);
	}

	private static void input() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Long.parseLong(st.nextToken());
		arr = new int[N];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
	}
}
