import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 다오의데이트 {

	private static int c;
	private static int r;
	private static char[][] map;
	private static int o;
	private static int[] dr = { -1, 1, 0, 0 };
	private static int[] dc = { 0, 0, -1, 1 };
	private static boolean[][] visited;
	private static char[][] oarr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		map = new char[r][c];
		for (int i = 0; i < r; i++) {
			map[i] = br.readLine().toCharArray();
		}

		int sr = 0;
		int sc = 0;
		int er = 0;
		int ec = 0;
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (map[i][j] == 'D') {
					sr = i;
					sc = j;
				} else if (map[i][j] == 'Z') {
					er = i;
					ec = j;
				}
			}
		}

		o = Integer.parseInt(br.readLine());
		oarr = new char[o][2];
		for (int i = 0; i < o; i++) {
			st = new StringTokenizer(br.readLine());
			oarr[i][0] = st.nextToken().charAt(0);
			oarr[i][1] = st.nextToken().charAt(0);
		}
		Queue<Integer> qr = new LinkedList<Integer>();
		Queue<Integer> qc = new LinkedList<Integer>();
		qr.add(sr);
		qc.add(sc);
		Queue<String> qs = new LinkedList<String>();
		qs.add("");
		String result = "NO";
		visited = new boolean[r][c];
		int turn = 0;
		while (turn<o) {
			int size = qr.size();
			for (int t = 0; t < size; t++) {
				int cr = qr.poll();
				int cc = qc.poll();
				String co = qs.poll();
				visited[cr][cc] = true;
				if (cr == er && cc == ec) {
					System.out.println("YES");
					System.out.println(co);
					return;
				}

				for (int i = 0; i < 2; i++) {
					char inputo = oarr[turn][i];
					int dir = convert(inputo);

					int nr = cr + dr[dir];
					int nc = cc + dc[dir];
					String no=co;
					if (nr >= 0 && nr < r && nc >= 0 && nc < c && map[nr][nc] != '@') {
						qr.add(nr);
						qc.add(nc);
						no = no + "" + inputo;
						qs.add(no);
					}
				}
			}

			turn++;
		} // while 끝
		while (!qr.isEmpty()) {
			int cr = qr.poll();
			int cc = qc.poll();
			String co = qs.poll();
			visited[cr][cc] = true;
			if (cr == er && cc == ec) {
				System.out.println("YES");
				System.out.println(co);
				return;
			}
		}
		System.out.println(result);
	}// 메인 끝

	private static int convert(char inputo) {
		if (inputo == 'W') {
			return 0;
		} else if (inputo == 'S') {
			return 1;
		} else if (inputo == 'A') {
			return 2;
		} else {
			return 3;
		}
	}

}
