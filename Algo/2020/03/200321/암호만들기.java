import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 암호만들기 {
	private static int r;
	private static int n;
	private static char[] arr;
	private static char[] combi;
	private static StringBuilder sb;

//	https://www.acmicpc.net/problem/1759
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		input();
		makecombi(0, 0);
		System.out.println(sb);
	}

	private static void makecombi(int cur, int target) {
		if (target == r) {
			// 유효성 검사
			if (isvalify()) {
				for (int i = 0; i < r; i++) {
					sb.append(combi[i]);
				}
				sb.append("\n");
			}
			return;
		}
		for (int i = cur; i < n; i++) {
			combi[target] = arr[i];
			makecombi(i + 1, target + 1);
		}

	}

	private static boolean isvalify() {
		int ja = 0;
		int mo = 0;
		for (int i = 0; i < r; i++) {
			if (combi[i] == 'a' || combi[i] == 'e' ||combi[i] == 'i' || 
					combi[i] == 'o' ||combi[i] == 'u') {
				mo++;
			} else {
				ja++;
			}
		}
		if (mo >= 1 && ja >= 2) {
			return true;
		} else
			return false;
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		r = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		arr = new char[n];
		combi = new char[r];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = st.nextToken().charAt(0);
		}
		Arrays.sort(arr);

		br.close();
	}

}
