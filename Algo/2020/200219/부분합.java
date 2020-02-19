import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 부분합 {

	private static int N;
	private static int M;
	private static int[] arr;
	public static void main(String[] args) throws NumberFormatException, IOException {
		input();
		Arrays.sort(arr);
		//뒤에서부터 더하면서 온다?
		go();
	}
	private static void go() {
		int r=N-1;
		int l=N=1;
		int sum=0;
		int cnt=0;
		while (r>=0) {
			if(arr[r]>M) {
				r--;
				continue;
			}else {
				sum+=arr[r];
				cnt++;
				l=r-1;
				int tsum=0;
				while(l>=0) {
					if (sum+arr[l]<=M) {
						cnt++;
						tsum+=arr[l];
						sum+=arr[l];
					}
					if (sum==M) {
						System.out.println(cnt);
						return;
					}
					l--;
				}
				sum-=tsum;
			}
		}
		System.out.println(-1);
	}
	private static void input() throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine()," ");
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		arr=new int[N];
		st=new StringTokenizer(br.readLine()," ");
		for (int i = 0; i < N; i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
	}
}
