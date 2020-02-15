import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 여행가자 {
	private static int N;
	private static int M;
	private static int[][] map;
	private static int[] parents;

	public static int getparent(int a) {
		if (a == parents[a])
			return a;
		return parents[a] = getparent(parents[a]);
	}

	public static void union(int a, int b) {
		int roota = getparent(a);
		int rootb = getparent(b);

		if (roota < rootb) {
			parents[rootb] = roota;
		} else {
			parents[roota] = rootb;
		}
	}

	public static boolean find(int a, int b) {
		int roota = getparent(a);
		int rootb = getparent(b);
		if (roota == rootb) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		map = new int[N + 1][N + 1];

		parents = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			parents[i]=i;
		}
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
					union(i, j);
				}
			}
			map[i][i] = 1;
		}

		int go[] = new int[M];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < M; i++) {
			go[i] = Integer.parseInt(st.nextToken());
		}

		boolean flag = true;
		for (int i = 1; i < M; i++) {
			if (find(go[i - 1], go[i])) {
				flag = false;
				break;
			}
		}
		System.out.println(flag ? "YES" : "NO");
	}
}
