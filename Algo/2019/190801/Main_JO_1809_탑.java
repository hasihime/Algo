package hw_day190801;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 탑_오석빈 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(in.readLine(), " ");
		int[] arr = new int[n + 1];

		for (int i = 1; i < n + 1; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		arr[0] = 100000001;
		int A[] = new int[n + 1];

		for (int i = 1; i < n + 1; i++) {
			for (int j = i; j >= 0; j--) {
				if (arr[i] < arr[j]) {
					sb.append(j + " ");
					break;
				}
			}
		}
		System.out.println(sb);
	}
}
