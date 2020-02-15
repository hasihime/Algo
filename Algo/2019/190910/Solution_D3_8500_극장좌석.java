package hw_190910;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 극장좌석 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {

			int N = Integer.parseInt(br.readLine());

			int[] input = new int[N];
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				input[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(input);
			int record = 0;
			for (int i = 0; i < N; i++) {
				int count = 0;
				for (int j = record; j < 100010000; j++) {
					if (count != input[i]) {
						count++;
					} else {
						record = j+1;
						for (int k = j+1; k < j+1 + input[i]; k++) {
						}
						if (i==N-1) {
							record+=input[i];
						}
						break;
					}
				}
			}
			System.out.println("#"+t+" "+record);
		

		}

	}

}
