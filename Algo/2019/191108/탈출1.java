import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class metrix {
	int row;
	int col;
	int time;

	public metrix(int row, int col, int time) {
		super();
		this.row = row;
		this.col = col;
		this.time = time;
	}

}

public class 탈출1 {

	public static void main(String[] args) throws NumberFormatException, IOException {

		int dir[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		int row = Integer.parseInt(st.nextToken());
		int col = Integer.parseInt(st.nextToken());
		char map[][] = new char[row][col];
		Queue<metrix> floor = new LinkedList<metrix>();
		Queue<metrix> hh = new LinkedList<metrix>();

		for (int i = 0; i < row; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < col; j++) {
				if (map[i][j] == 'S') {
					hh.add(new metrix(i, j, 0));
				}
				if (map[i][j] == '*') {
					floor.add(new metrix(i, j, 0));
				}
			}
		}

		int t = 0;
		while (true) {

			while (t == hh.peek().time) {
				metrix hm = hh.poll();
				if (map[hm.row][hm.col] == '*') {
					if (!hh.isEmpty()) {
						continue;
					} else if (hh.isEmpty()) {
						System.out.println("KAKTUS");
						return;
					}
				}
				for (int d = 0; d < dir.length; d++) {
					int newr = hm.row + dir[d][0];
					int newc = hm.col + dir[d][1];
					int curtime = hm.time;
					if (newr >= 0 && newr < row && newc >= 0 && newc < col) {
						if (map[newr][newc] == '.') {
							map[newr][newc] = 'S';
							hh.add(new metrix(newr, newc, curtime + 1));
						}
						if (map[newr][newc] == 'D') {
							System.out.println(hm.time + 1);
							return;
						}
					}
				}
				if (hh.isEmpty()) {
					System.out.println("KAKTUS");
					return;
				}
			}

			if (!floor.isEmpty()) {
				
	
			while (t == floor.peek().time) {
				metrix fm = floor.poll();
				for (int d = 0; d < dir.length; d++) {
					int newr = fm.row + dir[d][0];
					int newc = fm.col + dir[d][1];
					int curtime = fm.time;
					if (newr >= 0 && newr < row && newc >= 0 && newc < col) {
						if (map[newr][newc] == '.' || map[newr][newc] == 'S') {
							map[newr][newc] = '*';
							floor.add(new metrix(newr, newc, curtime + 1));
						}
					}
				}
				if (floor.isEmpty()) {
					break;
				}

			}
			}
			t++;
		}
	}// 메인 끝

}
