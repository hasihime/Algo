import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 피자굽기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int D = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());

		int[] oven = new int[D + 1];
		int[] maxoven = new int[D + 1];
		
		// 지름을 키로 최대 깊이기 몇까지
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= D; i++) {
			oven[i] = Integer.parseInt(st.nextToken());
			if (i == 1) {
				maxoven[i] = oven[i];
			} else {
				if (maxoven[i - 1] <= oven[i])
					maxoven[i] = maxoven[i - 1];
				else
					maxoven[i] = oven[i];
			}
		}

		int result=D;
		int doughcnt=N;
		st = new StringTokenizer(br.readLine(), " ");
		int cursize=Integer.parseInt(st.nextToken());
		for (int i = D; i >= 0; i--) {
			if (maxoven[i]>=cursize) {
				result=i;
				doughcnt--;
				if (doughcnt==0) {
					System.out.println(result);
					return;
				}
				cursize=Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(0);

	}

}
