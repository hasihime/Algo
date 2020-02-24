import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 이차원배열합 {
	private static int[][] arr;
	private static int[][] order;
	private static int[][] summap;
	private static int r;
	private static int c;
	private static StringBuilder sb;
	private static int n;

	public static void main(String[] args) throws IOException {
		sb=new StringBuilder();
		input();
		makeacc();
		go();
		System.out.println(sb);
	}

	private static void go() {
		for (int i = 0; i < n; i++) {
			int cur=0;
			int r1=order[i][0];
			int c1=order[i][1];
			int r2=order[i][2];
			int c2=order[i][3];
			cur+=summap[r2][c2];
			if (c1>=1) cur-=summap[r2][c1-1];
			if(r1>=1)cur-=summap[r1-1][c2];
			if(r1>=1&&c1>=1)cur+=summap[r1-1][c1-1];
			sb.append(cur).append("\n");
		}
		
	}

	private static void makeacc() {
		summap[0][0]=arr[0][0];
		for (int i = 1; i < c; i++) {
			summap[0][i]=summap[0][i-1]+arr[0][i];
		}
		for (int i = 1; i < r; i++) {
			summap[i][0]=summap[i-1][0]+arr[i][0];
			for (int j = 1; j < c; j++) {
				summap[i][j]=summap[i][j-1]+summap[i-1][j]-summap[i-1][j-1]+arr[i][j];
			}
		}
	}

	private static void input() throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		r=Integer.parseInt(st.nextToken());
		c=Integer.parseInt(st.nextToken());
		arr=new int[r][c];
		summap=new int[r][c];
		for (int i = 0; i < r; i++) {
			st=new StringTokenizer(br.readLine());
			for (int j = 0; j < c; j++) {
				arr[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		n=Integer.parseInt(br.readLine());
		order=new int[n][4];
		for (int i = 0; i < n; i++) {
			st=new StringTokenizer(br.readLine());
			order[i][0]=Integer.parseInt(st.nextToken())-1;
			order[i][1]=Integer.parseInt(st.nextToken())-1;
			order[i][2]=Integer.parseInt(st.nextToken())-1;
			order[i][3]=Integer.parseInt(st.nextToken())-1;
		}
	}
}
