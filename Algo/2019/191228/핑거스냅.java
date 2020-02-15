import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 핑거스냅 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		boolean[] issosu = new boolean[100001];

		for (int i = 2; i < 100001; i++) {
			if (issosu[i]) {
				continue;
			} else {
				for (int j = i + i; j < 100001; j = j + i) {
					issosu[j] = true;
				}
			}
		}
		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			boolean[] memo = new boolean[1000001];
			st = new StringTokenizer(br.readLine(), " ");
			int n = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int result = 10000000;

			int size = 0;
			for (int j = a; j <= b; j++) {
				if (!issosu[j]) {
					size++;
				}
			}
			// 소수가 하나도 없으면 -1출력
			if (size == 0) {
				System.out.println(-1);
				continue;
			}
			int sosuarr[] = new int[size];
			int temp = 0;
			for (int j = a; j <= b; j++) {
				if (!issosu[j]) {
					sosuarr[temp] = j;
					temp++;
				}
			}

			// a,b사이 소수가 여러개면 그 중 가까운 것을 찾는다.
			Queue<Integer> q = new LinkedList<Integer>();
			memo[n] = true;
			q.add(n);
			int cnt = 0;
			boolean flag = false;
			while (!q.isEmpty()) {
				int qsize = q.size();
				for (int j = 0; j < qsize; j++) {
					int cur = q.poll();
					if (isElement(cur, sosuarr)) {
						result = cnt;
						flag = true;
						break;
					}
					if (!memo[cur / 2]) {
						q.add(cur / 2);
						memo[cur / 2] = true;
					}
					if (!memo[cur / 3]) {
						q.add(cur / 3);
						memo[cur / 3] = true;
					}
					if (cur < 1000000) {
						if (!memo[cur + 1]) {
							q.add(cur + 1);
							memo[cur + 1] = true;
						}
					}

					if (cur > 0) {
						if (!memo[cur - 1]) {
							q.add(cur - 1);
							memo[cur - 1] = true;
						}
					}

				}
				if (flag) {
					break;
				}
				cnt++;
			}
			System.out.println(result);
		}

	}

	private static boolean isElement(int cur, int[] sosuarr) {
		for (int i = 0; i < sosuarr.length; i++) {
			if (sosuarr[i] == cur) {
				return true;
			}
		}

		return false;
	}

}
