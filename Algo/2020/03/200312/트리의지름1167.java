import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
//https://www.acmicpc.net/problem/1167
public class 트리의지름1167 {
	public static class node {
		int y;
		int cost;

		public node(int y, int cost) {
			super();
			this.y = y;
			this.cost = cost;
		}
	}

	private static int n;
	private static int result;
	private static ArrayList[] ns;
	private static int[] select;
	private static boolean[] visited;
	private static int[] dist;

	public static void main(String[] args) throws IOException {
		input();
		//일단 가장 긴 노드 하나를 고른다.
		dist = bfs(ns, 1, n);
		int p1=1;
		for (int i = 2; i <= n; i++) {
			if(dist[p1]<dist[i])
				p1=i;
		}
		//p1에서 가장 긴 점 찾기
		dist = bfs(ns, p1, n);
		Arrays.sort(dist);
		System.out.println(dist[n]);
	}

	private static int[] bfs(ArrayList[] d, int start, int n) {
		boolean[] visited=new boolean[n+1];
		dist=new int[n+1];
		Queue<Integer>q=new LinkedList<Integer>();
		q.add(start);
		visited[start]=true;
		while(!q.isEmpty()) {
			int curv=q.poll();
			for (int i = 0; i < ns[curv].size(); i++) {
				node curnode=(node)ns[curv].get(i);
				int y=curnode.y;
				int cost=curnode.cost;
				if (!visited[y]) {
					q.add(y);
					visited[y]=true;
					dist[y]=dist[curv]+cost;
				}
			}
		}
		
		return dist;
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		result = 0;
		ns =  (ArrayList<node>[]) new ArrayList[n + 1];
		
		for (int i = 0; i <= n; i++) {
			ns[i] = new ArrayList<node>();
		}
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int cur = Integer.parseInt(st.nextToken());
			while (true) {
				int x = Integer.parseInt(st.nextToken());
				if (x == -1)
					break;
				int w = Integer.parseInt(st.nextToken());
				ns[cur].add(new node(x, w));
			}
		}
		br.close();
	}

}
