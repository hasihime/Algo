import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 불 {
	private static int r;
	private static int c;
	private static char[][] map;
	private static int[] dr = { -1, 1, 0, 0 };
	private static int[] dc = { 0, 0, -1, 1 };
	private static boolean[][] jmap;
	private static boolean[][] fmap;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		map = new char[r][c];
		jmap = new boolean[r][c];
		fmap = new boolean[r][c];

		for (int i = 0; i < r; i++) {
			String input = br.readLine();
			map[i] = input.toCharArray();
		}

		Queue<Integer> jr = new LinkedList<Integer>();
		Queue<Integer> jc = new LinkedList<Integer>();
		Queue<Integer> fr = new LinkedList<Integer>();
		Queue<Integer> fc = new LinkedList<Integer>();
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (map[i][j] == 'J') {
					jr.add(i);
					jc.add(j);
				} else if (map[i][j] == 'F') {
					fr.add(i);
					fc.add(j);
				}
			}
		}




		int result = 0;
		
		while (result<=10000) {
			
			int size = jr.size();
			int fsize = fr.size();
			
			//불 이동
			for (int i = 0; i < fsize; i++) {
				int cfr = fr.poll();
				int cfc = fc.poll();
				fmap[cfr][cfc] = true;
				// 다음 불
				for (int j = 0; j < dc.length; j++) {
					int nr = cfr + dr[j];
					int nc = cfc + dc[j];
					if (nr >= 0 && nr < r && nc >= 0 && nc < c && !fmap[nr][nc]
							&& (map[nr][nc] == '.' || map[nr][nc] == 'J')) {
						map[nr][nc] = 'F';
						fr.add(nr);
						fc.add(nc);
					}
				}
			} // for 끝
			
			
			for (int i = 0; i < size; i++) {
				int cjr = jr.poll();
				int cjc = jc.poll();
				jmap[cjr][cjc] = true;
				
				// 사람 먼저
				for (int j = 0; j < dc.length; j++) {
					int nr = cjr + dr[j];
					int nc = cjc + dc[j];
					if (nr >= 0 && nr < r && nc >= 0 && nc < c && !jmap[nr][nc] && map[nr][nc] == '.') {
						map[nr][nc] = 'J';
						jr.add(nr);
						jc.add(nc);
					}
					if (nr < 0 || nr >= r || nc < 0 || nc >= c) {
						System.out.println(result+1);
						return;
					}
				}
			}
			

			result++;
		}
		System.out.println("IMPOSSIBLE");
	}
}
