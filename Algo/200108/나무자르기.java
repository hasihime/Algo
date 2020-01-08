import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 나무자르기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] treearr = new int[N];
		
		st = new StringTokenizer(br.readLine(), " ");
		int maxH = 0;
		
		for (int i = 0; i < N; i++) {
			treearr[i] = Integer.parseInt(st.nextToken());
			maxH = maxH < treearr[i] ? treearr[i] : maxH;
		}

		int start = 0;
		int end = maxH;
		while (start <= end) {
			int mid = (start + end) / 2;
			long curH = 0;
			for (int i = 0; i < N; i++) {
				if (treearr[i] > mid) {
					curH += (treearr[i] - mid);
				}
			}
			if (curH < M) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		} // while 끝
		System.out.println(end);

	}

}
