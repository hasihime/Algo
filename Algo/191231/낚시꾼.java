import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class shark {
	int row;
	int col;
	int spesed;
	int direction;
	int size;

	public shark(int row, int col, int spesed, int direction, int size) {
		super();
		this.row = row;
		this.col = col;
		this.spesed = spesed;
		this.direction = direction;
		this.size = size;
	}

}

public class 낚시꾼 {
	private static int R;
	private static int C;
	private static int N;

	private static int[] dx = { -1, 1, 1, -1 };
	private static int[][] size;
	private static shark[] sarr = new shark[10001];
	private static shark[] nsarr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		size = new int[R][C];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int tr = Integer.parseInt(st.nextToken()) - 1;
			int tc = Integer.parseInt(st.nextToken()) - 1;
			int speed = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());
			if (dir == 0 || dir == 1) {
				speed %= (R - 1) * 2;
			} else {
				speed %= (C - 1) * 2;
			}
			sarr[s] = new shark(tr, tc, speed, dir, s);
			size[tr][tc] = s;
		}

		int result = 0;

		for (int t = 0; t < C; t++) {
			// 상어를 찾는다.
			for (int i = 0; i < R; i++) {
				if (size[i][t] != 0) {
					result+=size[i][t];
					sarr[size[i][t]]=null;
					break;
				}
			}
			// 상어들 이동시킨다.
			nsarr=new shark[10001];
			move();
			// 이동 끝
			sarr=nsarr;
			size = new int[R][C];

			// 배열에 옮긴다.
			for (int i = 1; i < 10001; i++) {
				if (sarr[i]==null) {
					continue;
				}
				
				shark s = sarr[i];
				int r = s.row;
				int c = s.col;
				int ss=s.size;

				if (size[r][c] == 0) {
					size[r][c] = ss;
				} else {
					if (size[r][c] < s.size) {
						sarr[size[r][c]]=null;
						size[r][c] = s.size;
					}
				}
			} // for 끝


		} // while끝
		System.out.println(result);
	}

	private static void move() {

		for (int i = 1; i < 10001; i++) {
			if (sarr[i]==null) {
				continue;
			}
			shark s = sarr[i];
			int r = s.row;
			int c = s.col;
			int d = s.direction;
			int sspeed = s.spesed;

			switch (s.direction / 2) {
			// 위아래인 경우
			case 0:
				while (sspeed > 0) {
					if (d == 0 && r == 0) {
						d = 1;
					} else if (d == 1 && r == R - 1) {
						d = 0;
					}
					r += dx[d];
					sspeed--;
				}
				// 이동이 끝났으면 맵에 체크
				nsarr[i]=new shark(r, c, s.spesed, d, i);
				break;
			// 좌우인경우
			case 1:
				while (sspeed > 0) {
					if (d == 3 && c == 0) {
						d = 2;
					} else if (d == 2 && c == C - 1) {
						d = 3;
					}
					c += dx[d];
					sspeed--;
				}
				// 이동이 끝났으면 맵에 체크
				nsarr[i]=new shark(r, c, s.spesed, d, i);
				break;
			}// 스위치 끝
		} // for문 끝
	}

}
