import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJO_3109_빵집_오석빈 {
	static int R;
	static int C;
	static boolean possible[][];
	static char arr [][];
	static int dir [][]= {{-1,1},{0,1},{1,1}};
	static int count=0;
	static boolean flag=false;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		R=Integer.parseInt(st.nextToken());
		C=Integer.parseInt(st.nextToken());
		arr =new char [R][C];
		possible=new boolean[R][C];

		
		for (int i = 0; i < R; i++) {
			String temp=br.readLine();
			for (int j = 0; j < C; j++) {
				char c=temp.charAt(j);
				arr[i][j]=c;
				if (c=='x') {
					possible[i][j]=true;
				}
			}
		}
		for (int i = 0; i < R; i++) {
			flag=false;
			dfs(i,0);
		}
		System.out.println(count);
		
	}
	private static void dfs(int r, int c) {

		if (c==C-1) {
			count++;
			possible[r][c]=true;
			flag=true;
			return;
		}
		
		for (int i = 0; i < 3; i++) {
			int nr=r+dir[i][0];
			int nc=c+dir[i][1];
			
			if (nr>=0&&nr<R
					&&!possible[nr][nc]) {
				if (flag) {
					break;
				}
				possible[r][c]=true;
				dfs(nr, nc);
			}
		}
		return;
	}
}
