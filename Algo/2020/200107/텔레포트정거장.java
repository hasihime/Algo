import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 텔레포트정거장 {

	private static int N;
	private static int M;
	private static int S;
	private static int E;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		String[] strteleport = new String[300001];
		boolean[] visited = new boolean[300001];
		st = new StringTokenizer(br.readLine(), " ");
		S = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int source = Integer.parseInt(st.nextToken());
			int dest = Integer.parseInt(st.nextToken());
			if (strteleport[source]==null) {
				strteleport[source] = "" + dest;
			} else {
				strteleport[source] += " " + dest;
			}
			if (strteleport[dest]==null) {
				strteleport[dest] = "" + source;
			} else {
				strteleport[dest] += " " + source;
			}
		}
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(S);
		visited[S] = true;
		for (int t = 0; t < N; t++) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int cur = q.poll();
				if (cur == E) {
					System.out.println(t);
					break;
				}

				//-1좌표
				int pre = cur - 1;
				if (pre > 0 && !visited[pre]) {
					q.add(pre);
					visited[pre] = true;
				}
				
				//+1좌표
				int next = cur + 1;
				if (next <= N && !visited[next]) {
					q.add(next);
					visited[next] = true;
				}

				//cur이 텔레포트 가능하면
				if (strteleport[cur] !=null) {
					st = new StringTokenizer(strteleport[cur], " ");
					int x=st.countTokens();
					for (int j = 0; j < x; j++) {
						int tele = Integer.parseInt(st.nextToken());
						if (!visited[tele]) {
							q.add(tele);
							visited[tele] = true;
						}
					}
				}

			}
		}

	}

}
