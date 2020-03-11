import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15OZ4qAPICFAYD
public class 익퍼_최단경로 {

	private static int t;
	private static int n;
	private static int[][] arr;
	private static int[] permu;
	private static boolean[] visited;
	private static int result;
	private static StringBuilder sb;
	private static BufferedReader br;

	public static void main(String[] args) throws NumberFormatException, IOException {
		inputT();
		for (int i = 1; i <= t; i++) {
			inputN();
			makepermu(0);
			sb.append("#").append(i).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}

	private static void makepermu(int target) {
		if (target==n) {
			go();
			return;
		}
		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				permu[target]=i+2;
				visited[i]=true;
				makepermu(target+1);
				visited[i]=false;
			}
		}
	}

	private static void go() {
		int curd=0;
		//처음 거리는 구한다.
		curd+=Math.abs(arr[0][0]-arr[permu[0]][0])+Math.abs(arr[0][1]-arr[permu[0]][1]);
		if(check(curd)) return;
		for (int i = 0; i < n-1; i++) {
			curd+=Math.abs(arr[permu[i]][0]-arr[permu[i+1]][0])+
					Math.abs(arr[permu[i]][1]-arr[permu[i+1]][1]);
			if(check(curd)) return;
		}
		//마지막 회사
		curd+=Math.abs(arr[permu[n-1]][0]-arr[1][0])+
				Math.abs(arr[permu[n-1]][1]-arr[1][1]);
		if(check(curd)) return;
		result=Math.min(result, curd);
	}

	private static boolean check(int curd) {
		if (curd>=result) 
			return true;
		else
		return false;
	}

	private static void inputN() throws NumberFormatException, IOException {
		
		n=Integer.parseInt(br.readLine());
		arr=new int[n+2][2];
		StringTokenizer st=new StringTokenizer(br.readLine());
		for (int i = 0; i < n+2; i++) {
			arr[i][0]=Integer.parseInt(st.nextToken());
			arr[i][1]=Integer.parseInt(st.nextToken());
		}
		permu=new int[n];
		visited=new boolean[n];
		result=Integer.MAX_VALUE;
	}

	private static void inputT() throws NumberFormatException, IOException {
		sb=new StringBuilder();
		br=new BufferedReader(new InputStreamReader(System.in));
		t=Integer.parseInt(br.readLine());
	}

}
