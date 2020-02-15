package hw_190916;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_2630_색종이만들기 {
	static int N = 0;
	static int map[][];
	static int count[] = new int[2];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		StringTokenizer st;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[0][0] != map[i][j]) {
					findpaper(N);
					return;
				}
			}
		}
		count[map[0][0]]++;
		System.out.println(count[0]);
		System.out.println(count[1]);

	}

	private static void findpaper(int n) {
		if (divide1(n) && divide2(n) && divide3(n) && divide4(n)) {
			System.out.println(count[0]);
			System.out.println(count[1]);
			return;
		}

		
		divide1(n / 2);
		divide2(n / 2);
		divide3(n / 2);
		divide4(n / 2);

	}

	private static boolean divide1(int n) {
		for (int i = 0; i < n / 2; i++) {
			for (int j = 0; j < n / 2; j++) {
				if (map[0][0] != map[i][j]) {
					return false;
				}
			}
		}
		count[map[0][0]]++;
		return true;
	}

	private static boolean divide2(int n) {
		for (int i = 0; i < n / 2; i++) {
			for (int j = n / 2; j < n; j++) {
				if (map[0][n / 2] != map[i][j]) {
					return false;
				}
			}
		}
		count[map[0][n / 2]]++;
		return true;

	}

	private static boolean divide3(int n) {
		for (int i = n / 2; i < n; i++) {
			for (int j = 0; j < n / 2; j++) {
				if (map[n / 2][0] != map[i][j]) {
					return false;
				}
			}
		}

		count[map[n / 2][0]]++;
		return true;
	}

	private static boolean divide4(int n) {
		for (int i = n / 2; i < n; i++) {
			for (int j = n / 2; j < n; j++) {
				if (map[n / 2][N / 2] != map[i][j]) {
					return false;
				}
			}
		}
		count[map[n / 2][n / 2]]++;
		return true;
	}

}
