import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 상범빌딩 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb=new StringBuilder();
		int[] dz= {-1,1,0,0,0,0};
		int[] dr= {0,0,-1,1,0,0};
		int[] dc= {0,0,0,0,-1,1};
		
		while (true) {
			st = new StringTokenizer(br.readLine());
			int h = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			char[][][] map=new char[h][n][m];
			boolean[][][] visited=new boolean[h][n][m];
			if (n==0||m==0||h==0) {
				System.out.println(sb);
				return;
			}
			int sr=0;
			int sc=0;
			int sz=0;
			int er=0;
			int ec=0;
			int ez=0;
			for (int k = 0; k < h; k++) {
				if (k!=0) 	br.readLine();
				for (int i = 0; i < n; i++) {
					map[k][i]=br.readLine().toCharArray();
					for (int j = 0; j < m; j++) {
						if (map[k][i][j]=='S') {
							sz=k;
							sr=i;
							sc=j;
						}else if(map[k][i][j]=='E'){
							ez=k;
							er=i;
							ec=j;
						}
					}
				}
			}
			Queue<Integer>qz=new LinkedList<Integer>();
			Queue<Integer>qr=new LinkedList<Integer>();
			Queue<Integer>qc=new LinkedList<Integer>();
			qz.add(sz);
			qr.add(sr);
			qc.add(sc);
			visited[sz][sr][sc]=true;
			int t=0;
			boolean flag=false;
			
			while (!qr.isEmpty()) {
				int size=qr.size();
				while (size!=0) {
					int cz=qz.poll();
					int cr=qr.poll();
					int cc=qc.poll();
					if (cz==ez&&cr==er&&cc==ec) {
						sb.append("Escaped in ").append(t).append(" minute(s).").append("\n");
						flag=true;
						break;
					}
					for (int d = 0; d < dr.length; d++) {
						int nz=dz[d]+cz;
						int nr=dr[d]+cr;
						int nc=dc[d]+cc;
						if (nz>=0&&nz<h&&nr>=0&&nr<n&&nc>=0&&nc<m
								&&map[nz][nr][nc]!='#'
								&&!visited[nz][nr][nc]) {
							visited[nz][nr][nc]=true;
							qz.add(nz);
							qr.add(nr);
							qc.add(nc);
						}
					}
					
					size--;
				}//내부 while 끝
				if (flag) break;
				t++;
			}// while 끝
			if (!flag) {
				sb.append("Trapped!").append("\n");
			}
			br.readLine();
		} //입력 while 끝

	}

}
