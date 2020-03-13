import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
//https://www.acmicpc.net/problem/1753
public class 최단경로 {
	final static int INF = 100000000;
	private static int n;
	private static int[] dist;
	private static ArrayList[] list;
	private static int m;
	private static int startnode;
	private static boolean[] visited;

	public static class node{
		int v;
		int cost;
		public node(int v, int cost) {
			super();
			this.v = v;
			this.cost = cost;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		input();
		go();
		output();

	}

	private static void output() {
		StringBuilder sb=new StringBuilder();
		for (int i = 1; i <=n; i++) {
			if (dist[i]!=INF) {
				sb.append(dist[i]).append("\n");
			}else {
				sb.append("INF").append("\n");
			}
		}
		System.out.println(sb);
		
	}

	private static void go() {
		visited=new boolean[n+1];
		dist[startnode]=0;
		PriorityQueue<node>q=new PriorityQueue<node>(new Comparator<node>() {
			@Override
			public int compare(node o1, node o2) {
				return o1.cost-o2.cost;
			}
		});
		q.add(new node(startnode, 0));
		while(!q.isEmpty()) {
			node cur=q.poll();
			int curn=cur.v;
			if (visited[curn]) continue;
			visited[curn]=true;
			for (int i = 0; i < list[curn].size(); i++) {
				node nn=(node) list[curn].get(i);
					if (dist[nn.v]>dist[curn]+nn.cost) {
						dist[nn.v]=dist[curn]+nn.cost;
						q.add(new node(nn.v, dist[nn.v]));
					}
			}
		}
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		startnode=Integer.parseInt(br.readLine());
		dist=new int[n+1];
		for (int i = 1; i <= n; i++) 
			dist[i]=INF;
		list=new ArrayList[n+1];
		for (int i = 1; i <= n; i++) 
			list[i]=new ArrayList<node>();
		//입력 받기
			for (int i = 0; i <m; i++) {
				st=new StringTokenizer(br.readLine());
				int s=Integer.parseInt(st.nextToken());
				int e=Integer.parseInt(st.nextToken());
				int cost=Integer.parseInt(st.nextToken());
				//비어있으면 그냥 넣기
					list[s].add(new node(e, cost));
			}
			br.close();
	}

	
}
