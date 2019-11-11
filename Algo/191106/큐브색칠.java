import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class 큐브색칠 {

	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());

			long linearr[] = new long[n];

			// a부터 X까지 점의 값을 구함
			for (int i = a; i < x; i++)
				linearr[(i - a) % n]++;

			// 0부터 a-1까지 칠함
			for (int i = a - 1; i >= 0; i--)
				linearr[(a - i) % n]++;


//			System.out.println(Arrays.toString(line));

			long sidearr[] = new long[n]; 	// 선을 저장할 배열 생성
			long sidesum = 0; 				// 라인의 색의 개수 더함
			long sidemok = (y - b - 1) / n; // b+1부터 Y까지 라인의 반복되는 몫을 구함
			long sidenmg = (y - b - 1) % n; // b+1부터 Y까지 나머지를 구함

			// 한 줄에서 나올수 있는 색 합을 구함
			// 그냥 x로 하니깐 틀리는 테케가 있음
			for (int i = 0; i < n; i++) {
				sidesum += linearr[i];
			}

			for (int i = 0; i < n; i++) { // i번째 숫자는 b의 line[i]에 라인의 합에 몫을 곱을 더한다.
				sidearr[i] = (sidesum * sidemok) + linearr[i];
			}
			
			//몫보다 작은 값은 일일히 구한다.
			for (int i = 1; i <= sidenmg; i++) { // 0번 라인은 만들어졌으니 1번 라인부터 시작
				for (int j = 0; j < n; j++) { // 몫으로 안되는 나머지를 계산한다.
					sidearr[(i + j) % n] += linearr[j]; // i번째 줄+j번째를 n의 mod 값의 인덱스에 라인 값을 더한다.
				}
			}

			// 0부터 b까지 라인을 구함
			sidemok = (b) / n;
			sidenmg = (b) % n;

			for (int i = 0; i < n; i++) {
				sidearr[i] += (sidesum * sidemok);
			}
			for (int i = 1; i <= sidenmg; i++) {
				
				//i번째 줄의 modn의 인덱스 값을 더함
				for (int j = 0; j < n; j++) {
					sidearr[(i + j) % n] += linearr[j];
				}
			}

//			System.out.println(Arrays.toString(sidearr));

			long prismarr[] = new long[n]; // 면의 색의 수를 저장할 배열 생성
			long prismsum = 0; // 면의 색의 개수 더함
			long prismmok = (z - c - 1) / n; // c부터 z까지 면의 반복되는 몫을 구함
			long prismnmg = (z - c - 1) % n; // c부터 z까지 나머지를 구함

			// 한 면에서 나올수 있는 색 합을 구함
			// 그냥 x*y로 하니깐 틀리는 테케가 있음
			for (int i = 0; i < n; i++) {
				prismsum += sidearr[i];
			}
			for (int i = 0; i < n; i++) {
				prismarr[i] += (prismsum * prismmok) + sidearr[i];
			}
			for (int i = 1; i <= prismnmg; i++) {
				for (int j = 0; j < n; j++) {
					prismarr[(i + j) % n] += sidearr[j];
				}
			}

			// 0부터 c까지 면을 구함
			prismmok = (c) / n;
			prismnmg = (c) % n;

			for (int i = 0; i < n; i++) {
				prismarr[i] += (prismsum * prismmok); 		// i번째 숫자는 line[i]에 라인 개수*면의 수와 몫을 곱을 더한다.
			}
			for (int i = 1; i <= prismnmg; i++) { 			// 0번 면은 만들어졌으니 1번 면부터 시작
				for (int j = 0; j < n; j++) { 				// 몫으로 안되는 나머지를 계산한다.
					prismarr[(i + j) % n] += sidearr[j]; 	// i번째 면+j번줄을 n의 mod 값의 인덱스에 면의 값을 더한다.
				}
			}

			for (int i = 0; i < n; i++) {
				sb.append(prismarr[i]);
				if (i < n - 1) {
					sb.append(" ");
				}
			}

			sb.append("\n");
		} // T끝

		System.out.println(sb);
	}
}
