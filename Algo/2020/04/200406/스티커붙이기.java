import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 스티커붙이기 {
	private static int row;
	private static int col;
	private static int sticker;
	private static int[][] map;
	private static int[][] curarr;
	private static int result;
	private static int[][][] sarr;

	// https://www.acmicpc.net/problem/18808
	public static void main(String[] args) throws IOException {
		input();
		go();

	}

	private static void go() {
		// 붙일 수 있는 갯수.
		result = 0;
		int idx = 0;
		while (idx < sticker) {
			curarr = sarr[idx];
			// 회전하지 않고 붙일수 있으면 붙이고 다음 스티커 이동
			if (noturn()) {
				idx++;
				continue;
			}
			// 만약 회전을 안한상태에서 못붙이면 90도 돌리면서 붙여본다.
			else {
				// 스티커를 붙였는지 확인하는 플래그
				boolean flag = false;
				// 각 턴마다 90도씩 돌아가는 스티커.
				for (int i = 0; i < 3; i++) {
					if (turn90()) {
						idx++;
						flag = true;
						break;
					}
				}
				// flag가 거짓이면 그냥 다음 스티커 붙이고 버린다.
				if (!flag)
					idx++;
				else
					continue;
			}
		}
		System.out.println(result);
	}

	private static boolean turn90() {
		int[][] copymap = new int[curarr[0].length][curarr.length];
		for (int i = 0; i < curarr[0].length; i++) {
			for (int j = 0; j < curarr.length; j++) {
				copymap[i][j] = curarr[curarr.length - 1 - j][i];
			}
		}
		curarr = copymap;
		for (int i = 0; i <= row - curarr.length; i++) {
			for (int j = 0; j <= col - curarr[0].length; j++) {
				// 빈칸이면 붙여본다.
				if (map[i][j] + curarr[0][0] <= 1) {
					if (ispossible(i, j, curarr)) {
						attach(i, j, curarr);
						return true;
					}
				}
			}
		}
		return false;
	}

	private static boolean noturn() {
		// map의 행 열 돌리면서 빈칸에 색종이 붙일수 있는지 확인.
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (map[i][j] + curarr[0][0] <= 1) {
					// 빈칸이면 붙여본다.
					if (ispossible(i, j, curarr)) {
						attach(i, j, curarr);
						return true;
					}
				}
			}
		}
		return false;
	}

	private static void attach(int curr, int curc, int[][] ks) {
		int srow = ks.length;
		int scol = ks[0].length;
		for (int i = curr; i < curr + srow; i++) {
			for (int j = curc; j < curc + scol; j++) {
				if (ks[i - curr][j - curc] == 1) {
					map[i][j] = 1;
					result++;
				}
			}
		}

	}

	private static boolean ispossible(int curr, int curc, int[][] curarr) {
		int srow = curarr.length;
		int scol = curarr[0].length;
		// 스티커 idx번째의 가로와 세로가 칸을 넘어가면 무효.
		if (row < curr + srow || col < curc + scol)
			return false;
		else {
			// 붙여보자.
			for (int i = curr; i < curr + srow; i++) {
				for (int j = curc; j < curc + scol; j++) {
					if (map[i][j] + curarr[i - curr][j - curc] == 2)
						return false;
				}
			}
			return true;
		}
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		sticker = Integer.parseInt(st.nextToken());

		// 맵 생성
		map = new int[row][col];
		// 스티커 배열 만들기.
		// 0번째 스티커
		sarr = new int[sticker][][];
		for (int i = 0; i < sticker; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			sarr[i] = new int[r][c];
			for (int x = 0; x < r; x++) {
				st = new StringTokenizer(br.readLine());
				for (int y = 0; y < c; y++) {
					sarr[i][x][y] = Integer.parseInt(st.nextToken());
				}
			}
		}
	}

}
