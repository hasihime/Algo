import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 아맞다우산 {

	private static int row;
	private static int col;
	private static char[][] map;
	private static int scnt;
	private static int srow;
	private static int scol;
	private static int erow;
	private static int ecol;
	private static int[] permu;
	private static boolean[] permuvisited;
	private static ArrayList<int[]> slist;
	private static int result;
	private static int[] dr= {-1,1,0,0};
	private static int[] dc= {0,0,-1,1};
	private static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine()," ");
		col=Integer.parseInt(st.nextToken());
		row=Integer.parseInt(st.nextToken());
		srow=0;
		scol=0;
		erow=0;
		ecol=0;
		result=100000000;
		slist=new ArrayList<int[]>();
		
		map=new char[row][col];
		for (int i = 0; i < row; i++) {
			map[i]=br.readLine().toCharArray();
			for (int j = 0; j < col; j++) {
				if (map[i][j]=='X') {
					int[] temp= {i,j};
					slist.add(temp);
				}else if (map[i][j]=='S') {
					srow=i;
					scol=j;
				}else if(map[i][j]=='E') {
					erow=i;
					ecol=j;
				}
			}
		}
		scnt=slist.size();
		
		//물건의 순열을 구한다.
		permu=new int[scnt];
		permuvisited=new boolean[scnt];
		
		getpermu(0);
		System.out.println(result);
	}

	private static void getpermu(int target) {
		if (target==scnt) {
			//해당 물건 순서로 bfs를 탐색한다.
			bfs();
			
			return;
		}
		for (int i = 0; i < scnt; i++) {
			if (!permuvisited[i]) {
				permuvisited[i]=true;
				permu[target]=i;
				getpermu(target+1);
				permuvisited[i]=false;
			}
		}
		
	}

	private static void bfs() {
		Queue<Integer>qr=new LinkedList<Integer>();
		Queue<Integer>qc=new LinkedList<Integer>();
		qr.add(srow);
		qc.add(scol);
		int curcnt=0;
		
		for (int i = 0; i < scnt; i++) {
			visited=new boolean[row][col];
			int[] initial=slist.get(permu[i]);
			boolean flag=false;
			while (!qr.isEmpty()) {
				int size=qr.size();
				for (int j = 0; j < size; j++) {
					int cr=qr.poll();
					int cc=qc.poll();
				
					if (cr==initial[0]&&cc==initial[1]) {
						flag=true;
						break;
					}
					visited[cr][cc]=true;
					for (int d = 0; d < dr.length; d++) {
						int nr=cr+dr[d];
						int nc=cc+dc[d];
						if (map[nr][nc]!='#'&&map[nr][nc]!='E'&&!visited[nr][nc]) {
							visited[nr][nc]=true;
							qr.add(nr);
							qc.add(nc);
						}
					}
				}
				if (flag) {
					break;
				}
				curcnt++;
				if (result<=curcnt) {
					return;
				}
				
			}//while 끝
			qr=new LinkedList<Integer>();
			qc=new LinkedList<Integer>();
			qr.add(initial[0]);
			qc.add(initial[1]);
		}//for 끝
		
		//e로 향해 간다.
		qr=new LinkedList<Integer>();
		qc=new LinkedList<Integer>();
		if (slist.size()!=0) {
			int[] initial=slist.get(permu[scnt-1]);
			qr.add(initial[0]);
			qc.add(initial[1]);
		}else if (slist.size()==0) {
			qr.add(srow);
			qc.add(scol);
		}{
			
		}
		visited=new boolean[row][col];
		boolean eflag=false;
		
		while (!qr.isEmpty()) {
			int size=qr.size();
			for (int j = 0; j < size; j++) {
				int cr=qr.poll();
				int cc=qc.poll();
				if (cr==erow&&cc==ecol) {
					eflag=true;
					break;
				}
				visited[cr][cc]=true;
				for (int d = 0; d < dr.length; d++) {
					int nr=cr+dr[d];
					int nc=cc+dc[d];
					if (map[nr][nc]!='#'&&!visited[nr][nc]) {
						visited[nr][nc]=true;
						qr.add(nr);
						qc.add(nc);
					}
				}
			}
			if (eflag) {
				break;
			}
			curcnt++;
			if (result<=curcnt) {
				return;
			}
		}//while 끝
		
		result=result>curcnt?curcnt:result;
	}

}
