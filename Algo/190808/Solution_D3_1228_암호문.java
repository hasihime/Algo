package hw_190808;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_D3_1228_암호문1_오석빈 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb ;

		for (int t = 1; t <= 10; t++) {
			st = new StringTokenizer(in.readLine(), " ");
			sb = new StringBuilder();
			
			int n = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(in.readLine(), " ");
			List<String> list = new LinkedList<String>();
			for (int i = 0; i < n; i++) {
				list.add(st.nextToken());
			}
			int m = Integer.parseInt(in.readLine());
			st = new StringTokenizer(in.readLine(), " ");
			for (int i = 0; i < m; i++) {
				String c = st.nextToken();
				int inputn = Integer.parseInt(st.nextToken());
				int cn = Integer.parseInt(st.nextToken());
				for (int j = 0; j < cn; j++) {
					list.add(inputn, st.nextToken());
					inputn++;
				}
			}

			sb.append("#" + t + " ");
			for (int i = 0; i < 10; i++) {
				sb.append(list.get(i) + " ");
			}
			System.out.println(sb);

		}
	}
}
