import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 선물의집 {
 static int dir[][]= {{-1,0},{1,0},{0,-1},{0,1}};
 static int N=0;
 static int map[][];
 static boolean visited[][];
 static int Max=Integer.MIN_VALUE;
 
public  static void main(String[] args) throws NumberFormatException, IOException {
 BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
 StringTokenizer st;
 N=Integer.parseInt(br.readLine());
 
 map=new int[N][N];
 visited=new boolean[N][N];
 for (int i = 0; i < N; i++) {
  st=new StringTokenizer(br.readLine()," ");
  for (int j = 0; j < N; j++) {
   map[i][j]=Integer.parseInt(st.nextToken());
  }
 }
 visited[0][0]=true;
 if (map[0][0]==2) {
  dfs(0,0,1);
 }else {
  dfs(0,0,0);
 }
 visited[0][0]=false;
 System.out.println(Max);
 
}

private static void dfs(int x, int y, int count) {
 if(x==N-1&&y==N-1) {
  if (Max<count) {
   Max=count;
  }
  return;
 }
 
 
 int nX,nY;
 
 for (int d = 0; d < 4; d++) {
  nX=x+dir[d][0];
  nY=y+dir[d][1];
  if(nX>=0&&nX<N&&nY>=0&&nY<N
    &&map[nX][nY]!=1&&!visited[nX][nY]) {
   visited[nX][nY]=true;
   dfs(nX,nY,map[nX][nY]==2?count+1:count);
   visited[nX][nY]=false;
  }
 }
 return;
}
}