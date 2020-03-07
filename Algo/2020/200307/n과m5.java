import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class n과m5 {
	private static int n;
	private static int k;
	private static int[] arr;
	private static int[] permu;
	private static boolean[] visited;
	private static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		input();
		makepermu(0);
		System.out.println(sb);
	}

	private static void makepermu(int tar) {
		if (tar==k) {
			output();
			return;
		}
		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				visited[i]=true;
				permu[tar]=arr[i];
				makepermu(tar+1);
				visited[i]=false;
			}
		}
	}

	private static void output() {
		for (int i = 0; i < k; i++) {
			sb.append(permu[i]).append(" ");
		}
		sb.append("\n");
	}

	private static void input() throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		sb=new StringBuilder();
		n=Integer.parseInt(st.nextToken());
		k=Integer.parseInt(st.nextToken());
		
		arr=new int[n];
		permu=new int[k];
		
		st=new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			 arr[i]=Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		visited=new boolean[n];
		
	}
}
