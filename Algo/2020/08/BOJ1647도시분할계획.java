import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ1647도시분할계획 {
	private static int n;
	private static int m;
	private static int[][] arr;
	private static int[] parent;

	public static void main(String[] args) throws IOException {
		initailize();
		int result = 0;
		result = extracted();
		System.out.println(result);
	}

	private static void initailize() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[m][3];
		parent = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			parent[i] = i;
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
			arr[i][2] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2] - o2[2];
			}
		});
	}

	private static int extracted() {
		int idx = 0;
		int result = 0;
		for (int i = 0; i < m; i++) {
			if (idx == n - 2)
				return result;
			if (union(arr[i][0], arr[i][1])) {
				result += arr[i][2];
				idx++;
			}
		}
		return -1;
	}

	private static int getparent(int a) {
		if (parent[a] == a) {
			return a;
		}
		return parent[a] = getparent(parent[a]);
	}

	private static boolean union(int a, int b) {
		int roota = getparent(a);
		int rootb = getparent(b);
		if (roota != rootb) {
			if (roota < rootb) {
				parent[rootb] = roota;
			} else {
				parent[roota] = rootb;
			}
			return true;
		}
		return false;
	}

}
