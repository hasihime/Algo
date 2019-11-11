import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class 성수의프로그래밍강좌시청 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine(), " ");

			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());

			int input[] = new int[N];
			int select[] = new int[K];

			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				input[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(input);
			
			float result=0;
			
			for (int i = 0; i < K; i++) {
				select[i]=input[N-K+i];
			}
			
			for (int i = 0; i < K; i++) {
				result=(result+(float) select[i])/2;
			}
			System.out.printf("#%d %6f \n",t,result);
			
		}

	}

}
