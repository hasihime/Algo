import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class 움직이는미로탈출 {
	private static char[][] map;
	private static int[] dr = { -1, -1, -1, 0, 0, 0, 1, 1, 1 };
	private static int[] dc = { -1, 0, 1, -1, 0, 1, -1, 0, 1 };

	static class wall {
		int row;
		int col;

		public wall(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new char[8][8];
		for (int i = 0; i < 8; i++) {
			map[i] = br.readLine().toCharArray();
		}
		Queue<wall> qw = new LinkedList<wall>();

		for (int i = 7; i >=0; i--) {
			for (int j = 0; j < 8; j++) {
				if (map[i][j] == '#') {
					qw.add(new wall(i, j));
				}
			}
		}

		map[7][0] = 'P';
		Queue<Integer> qr = new LinkedList<Integer>();
		Queue<Integer> qc = new LinkedList<Integer>();
		qr.add(7);
		qc.add(0);
		// 벽이 다 사라지면 그 뒤로는 무조건 갈 수 있으니깐 종료
		// 왼쪽 아래서 오른쪽 위까지 가는데 벽보다 무조건 빨리 갈 수는 없으니깐 벽이 사라지는 조건만 생각
		int result = 1;
		while (!qw.isEmpty()) {

			// 먼저 사람을 8방+제자리로 퍼뜨린다.
			int size = qr.size();
			for (int i = 0; i < size; i++) {
				int cr = qr.poll();
				int cc = qc.poll();
				if (cr==0) {
					System.out.println(1);
					return;
				}
				if (map[cr][cc] == 'P') {
				for (int d = 0; d < dr.length; d++) {
					int nr = cr + dr[d];
					int nc = cc + dc[d];
					if (nr >= 0 && nr < 8 && nc >= 0 && nc < 8 && map[nr][nc] != '#') {
						qr.add(nr);
						qc.add(nc);
						map[nr][nc] = 'P';
					}
				}
				}
			} // 사람 이동 끝
				// 벽이 남아있는동안 사람이 없다면 이동 불가능이니 끝
			if (qr.isEmpty()) {
				result = 0;
				break;
			}
			// 벽을 아래로 내린다.
			int wcnt = qw.size();
			for (int j = 0; j < wcnt; j++) {
				wall curw = qw.poll();
				int cwr = curw.row;
				int cwc = curw.col;
				if (cwr  < 7) {
					map[cwr + 1][cwc] = '#';
					map[cwr][cwc] = '.';
					qw.add(new wall(cwr + 1, cwc));
				} else if (cwr  == 7) {
					map[cwr][cwc] = '.';
					continue;
				}
			}
		} // while 끝

		System.out.println(result);
	}

}
