import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JO_1113_119구급대 {

	static boolean visited[][];
	static int map[][];
	static int N=0,M=0;
	static int destrow=0,destcol=0;
	static int dir[][]={{-1,0},{1,0},{0,-1},{0,1}};
	static int result=Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine()," ");
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		 st=new StringTokenizer(br.readLine()," ");
		destrow=Integer.parseInt(st.nextToken());
		destcol=Integer.parseInt(st.nextToken());
		map=new int[N][M];
		visited=new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st=new StringTokenizer(br.readLine()," ");
			for (int j = 0; j < M; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		visited[0][0]=true;
		dfs(0,0,-1,-1);
		
		System.out.println(result);
	}

	private static void dfs(int currow, int curcol,int turndir,int turncnt) {
		if (currow==destrow&&curcol==destcol) {
			
			result=result>turncnt?turncnt:result;
			return;
		}
		if (turncnt>result) {
			return;
		}
		for (int i = 0; i < 4; i++) {
			int nrow=currow+dir[i][0];
			int ncol=curcol+dir[i][1];
			
			if (nrow>=0&&nrow<N&&ncol>=0&&ncol<M
					&&map[nrow][ncol]==1&&!visited[nrow][ncol]) {
				visited[nrow][ncol]=true;
				if (turndir==i) {
					dfs(nrow,ncol,i,turncnt);
				}else {
					dfs(nrow,ncol,i,turncnt+1);
				}
				visited[nrow][ncol]=false;
			}
		}
		
	}

}
