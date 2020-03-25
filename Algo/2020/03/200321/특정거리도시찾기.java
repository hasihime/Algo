import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
//https://www.acmicpc.net/problem/18352
public class 특정거리도시찾기 {
	private static int n;
	private static int m;
	private static int k;
	private static int start;
	private static ArrayList[] list;

	public static void main(String[] args) throws IOException {
		input();
		go();
	}

	private static void go() {
//		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(new Comparator<Integer>() {
//
//			@Override
//			public int compare(Integer o1, Integer o2) {
//				return o1 - o2;
//			}
//		});
		Queue<Integer>q=new LinkedList<Integer>();
		q.add(start);
		int dist[] = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			dist[i] = 1000000000;
		}
		dist[start] = 0;
//		pq.add(start);
		while (!q.isEmpty()) {
				int cur = q.poll();
				for (int j = 0; j < list[cur].size(); j++) {
					int next = (int) list[cur].get(j);
					if (dist[next] > dist[cur] + 1) {
						dist[next] = dist[cur] + 1;
						q.add(next);
					}
			}
		} // while 끝
		StringBuilder sb = new StringBuilder();
		int idx = 0;
		for (int i = 1; i <= n; i++) {
			if (dist[i] == k) {
				idx++;
				sb.append(i).append("\n");
			}
		}
		System.out.println(idx == 0 ? -1 : sb);
		return;
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		start = Integer.parseInt(st.nextToken());

		list = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++) {
			list[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			list[s].add(e);
		}
	}
}
