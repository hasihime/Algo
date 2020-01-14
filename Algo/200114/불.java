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

		int startjr = 0;
		int startjc = 0;
		int startfr = 0;
		int startfc = 0;
		boolean flag = false;
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (map[i][j] == 'J') {
					startjr = i;
					startjc = j;
					flag = true;
					break;
				} else if (map[i][j] == 'F') {
					startfr = i;
					startfc = j;
					flag = true;
					break;
				}
			}
			if (flag) {
				break;
			}
		}

		Queue<Integer> jr = new LinkedList<Integer>();
		Queue<Integer> jc = new LinkedList<Integer>();
		Queue<Integer> fr = new LinkedList<Integer>();
		Queue<Integer> fc = new LinkedList<Integer>();

		jr.add(startjr);
		jc.add(startjc);
		fr.add(startfr);
		fc.add(startfc);

		int result = 0;

		while (jr.isEmpty()) {
			int size=jr.size();
			for (int i = 0; i < size; i++) {
				int cjr=jr.poll();
				int cjc=jc.poll();
				int cfr=fr.poll();
				int cfc=fc.poll();
				
				if (cjr<0||cjr==r||cjc<-1||cjc==c) {
					System.out.println(result);
					return;
				}
				//사람 먼저
				for (int j = 0; j < dc.length; j++) {
					int nr=cjr+dr[j];
					int nc=cjc+dc[j];
					if (nr>=0&&nr<r&&nc>=0&&nc<c&&!jmap[nr][nc]&&map[nr][nc]=='.') {
						jmap[nr][nc]=true;
						jr.add(nr);
						jc.add(nc);
					}
					
				}
				
			}
			

		}

	}
}
