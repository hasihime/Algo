import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class 전력난 {
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb=new StringBuilder();
		while (true) {
			st = new StringTokenizer(br.readLine(), " ");
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());

			if (n==0&&m==0) {
				break;
			}
			
			parent = new int[n];
			for (int i = 0; i < n; i++) {
				parent[i] = i;
			}

			int[][] earr = new int[m][3];
			long origin = 0;
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				earr[i][0] = Integer.parseInt(st.nextToken());
				earr[i][1] = Integer.parseInt(st.nextToken());
				earr[i][2] = Integer.parseInt(st.nextToken());
				origin += earr[i][2];
			}
			Arrays.sort(earr, new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					int a = o1[2];
					int b = o2[2];
					return a - b;
				}
			});

			int cnt = 0;
			long result = 0;
			for (int i = 0; i < m; i++) {
				if (cnt == n - 1) {
					break;
				}
				if (!find(earr[i][0], earr[i][1])) {
					continue;
				} else {
					union(earr[i][0], earr[i][1]);
					result += earr[i][2];
					cnt++;
				}
			}
			sb.append(origin-result).append("\n");
		}
		System.out.println(sb);
	}

	public static int getParent(int rootA) {
		if (rootA == parent[rootA]) {
			return rootA;
		}
		return parent[rootA] = getParent(parent[rootA]);
	}

	public static void union(int a, int b) {
		int rootA = getParent(a);
		int rootB = getParent(b);
		if (rootA < rootB) {
			parent[rootB] = rootA;
		} else {
			parent[rootA] = rootB;
		}
	}

	public static boolean find(int a, int b) {
		int rootA = getParent(a);
		int rootB = getParent(b);
		if (rootA == rootB) {
			return false;
		}
		return true;
	}

}
