import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 줄세우기7570 {

	private static int n;
	private static int[] arr;
	private static int[] d;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		input();
		go();
	}

	private static void go() {
		int max=0;
		for (int i = 1; i <= n; i++) {
			d[arr[i]] = d[arr[i] - 1] + 1;
			max = Math.max(d[arr[i]], max);
		}
		System.out.println(n-max);
	}

	private static void input() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		arr = new int[n + 1];
		d = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

	}

}
