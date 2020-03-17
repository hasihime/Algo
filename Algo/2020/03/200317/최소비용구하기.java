import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
//https://www.acmicpc.net/problem/1916
public class 최소비용구하기 {
	private static int n;
	private static ArrayList<node>[] dlist;
	private static int[] dist;
	private static int start;
	private static int end;
	private static boolean[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		input();
		go();
	}

	private static void go() {
		dist[start] = 0;
		PriorityQueue<node> pq = new PriorityQueue<node>(new Comparator<node>() {
			@Override
			public int compare(node o1, node o2) {
				return o1.cost - o2.cost;
			}
		});
		for (int i = 0; i < dlist[start].size(); i++) {
			pq.add(new node(start, 0));
		}
		while (!pq.isEmpty()) {
			node curnode = pq.poll();
			int target = curnode.y;
			if (dist[target]<curnode.cost)	continue;
			for (int i = 0; i < dlist[target].size(); i++) {
				node nn = dlist[target].get(i);
				if (dist[nn.y] > dist[target] + nn.cost) {
					dist[nn.y] = dist[target] + nn.cost;
					pq.add(nn);
				}
			}
		}
		System.out.println(dist[end]);
	}

	private static void input() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		int e = Integer.parseInt(br.readLine());
		dlist = new ArrayList[n + 1];
		dist = new int[n + 1];

		for (int i = 0; i <= n; i++) {
			dlist[i] = new ArrayList<node>();
			dist[i] = 1000000000;
		}

		for (int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			dlist[start].add(new node(end, cost));
		}
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		br.close();
	}

	public static class node {
		int y;
		int cost;

		public node(int y, int cost) {
			this.y = y;
			this.cost = cost;
		}
	}
}
