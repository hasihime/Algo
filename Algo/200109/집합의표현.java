import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 집합의표현 {

	private static int N;
	private static int M;
	private static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		parent = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");

			int flag = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (flag == 0) {
					union(a, b);
			} else {
				if (find(a, b)) {
					System.out.println("NO");
				}else {
					System.out.println("YES");
				}
			}
		}
	}

	private static Integer getparent(int a) {
		if (a == parent[a]) {
			return a;
		} else
			return parent[a] = getparent(parent[a]);
	}

	private static void union(int a, int b) {
		int parentA = getparent(a);
		int parentB = getparent(b);
		if (parentA < parentB) {
			parent[parentB] = parentA;
		} else {
			parent[parentA] = parentB;
		}
	}

	private static boolean find(int a, int b) {
		int parentA = getparent(a);
		int parentB = getparent(b);
		if (parentA == parentB) {
			return false;
		} else
			return true;
	}
}
